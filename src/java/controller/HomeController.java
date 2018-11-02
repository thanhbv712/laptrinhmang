/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author buith
 */
public class HomeController extends Controller{
    public HomeController(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!this.existUser(req)){
            resp.sendRedirect("login");
        }else{
            req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
        } 
    }
    
}