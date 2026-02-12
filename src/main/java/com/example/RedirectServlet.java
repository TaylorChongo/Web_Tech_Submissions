package com.example;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String q = req.getParameter("query");
        res.sendRedirect("https://www.google.com/search?q=" + q);
    }
}
