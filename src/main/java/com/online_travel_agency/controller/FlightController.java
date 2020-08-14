/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.google.gson.JsonObject;
import com.online_travel_agency.dao.AircraftDAO;
import com.online_travel_agency.dao.FlightDAO;
import com.online_travel_agency.pojo.Aircraft;
import com.online_travel_agency.pojo.Flight;
import com.online_travel_agency.utility.FieldValidations;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Abhishek
 */
public class FlightController extends AbstractController {

    public FlightController() {
    }
    private static final Logger logger = Logger.getLogger(FlightController.class);

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            if (action.equals("")) {
                modelAndView = new ModelAndView("airliner-home");
            }
            if (action.equalsIgnoreCase("validateFlightNumber")) {
                String flightNumber = request.getParameter("flightNumber") != null ? request.getParameter("flightNumber") : "";
                if (!flightNumber.equalsIgnoreCase("")) {
                    FlightDAO flightDAO = new FlightDAO();
                    Boolean exist = flightDAO.isUnique(flightNumber);
                    JsonObject result = new JsonObject();
                    if (exist) {
                        result.addProperty("exists", Boolean.TRUE);
                    } else {
                        result.addProperty("exists", Boolean.FALSE);
                    }
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(result);
                    out.flush();
                }
            }
            if (action.equalsIgnoreCase("validateDeparture")) {
                String date = request.getParameter("date") != null ? request.getParameter("date") : "";
                String start = request.getParameter("start") != null ? request.getParameter("start") : "";
                HttpSession session = request.getSession(false);
                if (!start.equalsIgnoreCase("") && session != null && session.getAttribute("aircraft") != null) {
                    Aircraft aircraft = (Aircraft) session.getAttribute("aircraft");
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    long ms = sdf.parse(start).getTime();
                    Time flightTime = new Time(ms);
                    FlightDAO flightDAO = new FlightDAO();
                    Boolean exist = flightDAO.hasConflict(flightTime, aircraft.getAircraftId(), Date.valueOf(date));
                    JsonObject result = new JsonObject();
                    if (exist) {
                        result.addProperty("exists", Boolean.TRUE);
                    } else {
                        result.addProperty("exists", Boolean.FALSE);
                    }
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(result);
                    out.flush();
                }
            }
            if (action.equals("manageFlights")) {
                String aircraftId = request.getParameter("id") != null ? request.getParameter("id") : "";
                if (!aircraftId.equalsIgnoreCase("")) {
                    AircraftDAO aircraftDAO = new AircraftDAO();
                    Aircraft aircraft = aircraftDAO.getAircraft(Integer.parseInt(aircraftId));
                    if (aircraft != null) {
                        HttpSession session = request.getSession(false);
                        session.setAttribute("aircraft", aircraft);
                        FlightDAO flightDAO = new FlightDAO();
                        ArrayList<Flight> requestList = flightDAO.getAllFlights(aircraft);
                        modelAndView = new ModelAndView("manage-flights", "requestList", requestList);
                    } else {
                        modelAndView = new ModelAndView("airliner-error", "message", "Flight schedules not available for the requested aircraft");
                    }
                } else {
                    modelAndView = new ModelAndView("airliner-error", "message", "Flight schedules not available for the requested aircraft");
                }
            }

            if (action.equalsIgnoreCase("addFlight")) {
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("aircraft") != null) {
                    modelAndView = new ModelAndView("add-flight");
                } else {
                    modelAndView = new ModelAndView("airliner-error", "message", "Aircraft does not exist to add flights");
                }
            }

            if (action.equals("registerFlight")) {
                Aircraft aircraft = (Aircraft) request.getSession(false).getAttribute("aircraft");
                String flightNumber = request.getParameter("flightNumber") != null ? request.getParameter("flightNumber") : "";
                String departureLocation = request.getParameter("departureLocation") != null ? request.getParameter("departureLocation") : "";
                String arrivalLocation = request.getParameter("arrivalLocation") != null ? request.getParameter("arrivalLocation") : "";
                String price = request.getParameter("price") != null ? request.getParameter("price") : "";
                String flightDuration = request.getParameter("flightDuration") != null ? request.getParameter("flightDuration") : "";
                String date = request.getParameter("date") != null ? request.getParameter("date") : "";
                String time = request.getParameter("start") != null ? request.getParameter("start") : "";
                if (!flightNumber.equalsIgnoreCase("") && !departureLocation.equalsIgnoreCase("") && !arrivalLocation.equalsIgnoreCase("") && !price.equalsIgnoreCase("") && !flightDuration.equalsIgnoreCase("") && !date.equalsIgnoreCase("") && !time.equalsIgnoreCase("") && aircraft != null) {
                    FieldValidations fv = new FieldValidations();
                    if (fv.validateAplhaNumeric(flightNumber) && fv.validateNames(departureLocation) && fv.validateNames(arrivalLocation) && fv.validatePrice(price) && fv.validateDuration(flightDuration) && !departureLocation.equalsIgnoreCase(arrivalLocation) && fv.validateDate(date)) {
                        FlightDAO flightDAO = new FlightDAO();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        long ms = sdf.parse(time).getTime();
                        Time t = new Time(ms);
                        Flight flight = flightDAO.addFlight(flightNumber, departureLocation, arrivalLocation, Double.parseDouble(price), Double.parseDouble(flightDuration), Date.valueOf(date), t, aircraft);
                        if (flight != null) {
                            flightDAO.generateSeats(flight);
                            modelAndView = new ModelAndView("airliner-success", "message", "Flight details successfully added.");
                        } else {
                            modelAndView = new ModelAndView("airliner-error", "message", "Flight details not added");
                        }
                    } else {
                        modelAndView = new ModelAndView("airliner-error", "message", "Flight details not added");
                    }
                } else {
                    modelAndView = new ModelAndView("airliner-error", "message", "Flight details not added");
                }
            }
            if (action.equals("cancelFlight")) {
                String flightId = request.getParameter("id") != null ? request.getParameter("id") : "";
                if (!flightId.equalsIgnoreCase("")) {
                    FlightDAO flightDAO = new FlightDAO();
                    int result = flightDAO.cancelFlight(Integer.parseInt(flightId));
                    if (result == 1) {
                        modelAndView = new ModelAndView("airliner-success", "message", "Request Flight Canceled");
                    } else {
                        modelAndView = new ModelAndView("airliner-error", "message", "Requested Flight's status could not be changed");
                    }
                } else {
                    modelAndView = new ModelAndView("airliner-error", "message", "Requested Flight does not exist");
                }
            }

            if (action.equals("reserveTicket")) {
                String flightId = request.getParameter("id") != null ? request.getParameter("id") : "";
                if (!flightId.equalsIgnoreCase("")) {
                    FlightDAO flightDAO = new FlightDAO();
                    Flight flight = flightDAO.getFlight(Integer.parseInt(flightId));
                    if (flight != null) {
                        modelAndView = new ModelAndView("book-ticket", "flight", flight);
                    } else {
                        modelAndView = new ModelAndView("customer-error", "message", "Cannot reserve ticket for requested flight");
                    }
                } else {
                    modelAndView = new ModelAndView("customer-error", "message", "Cannot reserve ticket for requested flight");
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }
}
