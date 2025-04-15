package com.bridgelabz.loginservlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
        description = "Login servlet testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Ankush"),
                @WebInitParam(name = "password", value = "Ankush1")
            }
        )
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get user parameters for userID and password
        String user = request.getParameter("user");
        String password = request.getParameter("pass");

        // get servlet config init params
        String userId = getServletConfig().getInitParameter("user");
        String pwd = getServletConfig().getInitParameter("password");

        if(userId.equals(user) && pwd.equals(password)){
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request,response);
        } else {
            response.setContentType("text/html");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<h3>Either username and password is wrong.</h3>");
            rd.include(request, response);
        }
    }

}
