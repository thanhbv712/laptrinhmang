/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.LoginDAO;
import bean.UserBean;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author buith
 */
public class LoginController extends HttpServlet {

    public LoginController() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(request, response); //To change body of generated methods, choose Tools | Templates.
        System.out.println("GETTT");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POSTTTTTTTTTTT");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserBean userBean = new UserBean(); //creating object for LoginBean class, which is a normal java class, contains just setters and getters. Bean classes are efficiently used in java to access user information wherever required in the application.

        userBean.setUserName(userName); //setting the username and password through the loginBean object then only you can get it in future.
        userBean.setPassword(password);

        LoginDAO loginDao = new LoginDAO(); //creating object for LoginDao. This class contains main logic of the application.

        String userValidate = loginDao.authenticateUser(userBean); //Calling authenticateUser function

        if (userValidate.equals("SUCCESS")) //If function returns success string then user will be rooted to Home page
        {
            System.out.println("SUCCESS");
            request.getSession().setAttribute("username", userBean.getUserName());
            request.setAttribute("userBean", userBean); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
//            request.remo"index.jsp?controller=home").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
            response.sendRedirect("index.jsp?controller=home");
        } else {
            request.setAttribute("errMessage", userValidate); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);//forwarding the request
        }
    }
}
