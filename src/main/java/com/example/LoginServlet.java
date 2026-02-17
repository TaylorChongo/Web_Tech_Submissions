package com.example;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String u = req.getParameter("username");
        String p = req.getParameter("password");
        PrintWriter out = res.getWriter();
        if (p.length() < 8) {
            out.println("Hello " + u + ", your password is weak. Try a strong one.");
        } else {
            out.println("Welcome " + u);
        }
    }
}