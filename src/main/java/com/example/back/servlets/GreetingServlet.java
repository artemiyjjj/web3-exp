package com.example.back.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GreetingServlet", value = "/www")
public class GreetingServlet extends HttpServlet {
    private String handleServlet;
    private String mainPage;


    @Override
    public void init() {
        handleServlet = "/controllerServlet";
        mainPage = "/index.html";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher;
        PrintWriter out = response.getWriter();
        out.println("Hello");
//        dispatcher = context.getRequestDispatcher(mainPage);
//        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(handleServlet);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            response.setStatus(500);
            PrintWriter out = response.getWriter();
            out.println("Troubles with request handling.");
        }
    }
}
