package com.example.back.servlets;

import com.example.back.beans.services.CoordinatesService;
import com.example.back.beans.services.ShotService;
import com.example.back.entities.Coordinates;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet(name = "ControllerServlet", value = "/controllerServlet")
public class ControllerServlet extends HttpServlet {

    @EJB
    private ShotService shotService;
    @EJB
    private CoordinatesService coordinatesService;
//    @EJB
//    private RequestStopwatch timer;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
        StringBuilder jsonList = new StringBuilder();
        jsonList.append("[\n");
        try {
            shotService.getAllShots().stream().map((elem) -> jsonList.append(elem).append(",\n")).close();
        } catch (Exception e) {
            response.setStatus(400);
        }
        jsonList.append("]\n");
        PrintWriter out = response.getWriter();
        out.print(jsonList);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ServletInputStream inputStream = request.getInputStream();
        try {
            Coordinates coordinates = coordinatesService.handleCoordinates(inputStream);
            System.out.println(coordinates.toString());
            Optional<String> maybeJson = shotService.handleShot(coordinates);
            if (maybeJson.isPresent()) {
                System.out.println(maybeJson.get());
                out.println(maybeJson.get());
            }
            else {
                out.println("Some shot handle error");
                response.setStatus(400);
            }
        } catch (NoSuchElementException e) {
            out.println("Not valid coordinates.");
            response.setStatus(400);
        }
        finally {
            out.close();
        }

    }
}
