/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.online_travel_agency.dao.FlightDAO;
import com.online_travel_agency.pojo.Flight;
import com.online_travel_agency.utility.FieldValidations;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Abhishek
 */
public class SearchController extends AbstractController {

    public SearchController() {
    }
    private static final Logger logger = Logger.getLogger(SearchController.class);

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String departureLocation = request.getParameter("departureLocation") != null ? request.getParameter("departureLocation") : "";
            String arrivalLocation = request.getParameter("arrivalLocation") != null ? request.getParameter("arrivalLocation") : "";
            String date = request.getParameter("date") != null ? request.getParameter("date") : "";
            String comingFrom = request.getParameter("comingFrom") != null ? request.getParameter("comingFrom") : "";
            if (comingFrom.equalsIgnoreCase("customer")) {
                if (!departureLocation.equalsIgnoreCase("") && !arrivalLocation.equalsIgnoreCase("") && !date.equalsIgnoreCase("")) {
                    FieldValidations fv = new FieldValidations();
                    if (fv.validateNames(departureLocation) && fv.validateNames(arrivalLocation) && fv.validateDate(date) && !arrivalLocation.equalsIgnoreCase(departureLocation)) {
                        FlightDAO flightDAO = new FlightDAO();
                        ArrayList<Flight> resultList = flightDAO.searchFlights(departureLocation, arrivalLocation, Date.valueOf(date));
                        request.setAttribute("departureLocation", departureLocation);
                        request.setAttribute("arrivalLocation", arrivalLocation);
                        request.setAttribute("date", date);
                        modelAndView = new ModelAndView("search-results", "requestList", resultList);
                    } else {
                        modelAndView = new ModelAndView("customer-error", "message", "There was an error in input data");
                    }
                } else {
                    modelAndView = new ModelAndView("customer-error", "message", "There was an error in input data");
                }

            }
            if (comingFrom.equalsIgnoreCase("home")) {
                //System.out.println("Controller******" + departureLocation + arrivalLocation + date);
                if (!departureLocation.equalsIgnoreCase("") && !arrivalLocation.equalsIgnoreCase("") && !date.equalsIgnoreCase("")) {
                    FieldValidations fv = new FieldValidations();
                    if (fv.validateNames(departureLocation) && fv.validateNames(arrivalLocation) && fv.validateDate(date) && !arrivalLocation.equalsIgnoreCase(departureLocation)) {
                        FlightDAO flightDAO = new FlightDAO();
                        ArrayList<Flight> resultList = flightDAO.searchFlights(departureLocation, arrivalLocation, Date.valueOf(date));
                        request.setAttribute("departureLocation", departureLocation);
                        request.setAttribute("arrivalLocation", arrivalLocation);
                        request.setAttribute("date", date);
                        modelAndView = new ModelAndView("home-search-results", "requestList", resultList);
                    } else {
                        modelAndView = new ModelAndView("customer-error", "message", "There was an error in input data");
                    }
                } else {
                    modelAndView = new ModelAndView("customer-error", "message", "There was an error in input data");
                }

            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }

}
