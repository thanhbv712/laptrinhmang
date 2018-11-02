/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import model.User;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.DBConnection;

/**
 *
 * @author buith
 */
public class LoginController extends Controller {
    public LoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (this.existUser(request)) {
            response.sendRedirect("home");
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(userName, password);
        
        
        if(this.db.checkLogin(user)){
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home");
        } else {
            request.setAttribute("errMessage", "Invalid username/password!");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);//forwarding the request
        }
    }
}
