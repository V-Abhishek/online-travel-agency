/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.online_travel_agency.dao.TicketDAO;
import com.online_travel_agency.pojo.Ticket;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Abhishek
 */
public class PDFViewController extends AbstractController {

    public PDFViewController() {
    }
    private static final Logger logger = Logger.getLogger(PDFViewController.class);

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String id = request.getParameter("id") != null ? request.getParameter("id") : "";
            if (!id.equalsIgnoreCase("")) {
                TicketDAO ticketDAO = new TicketDAO();
                Ticket ticket = ticketDAO.getMyTicket(Integer.parseInt(id));
                if (ticket != null) {
                    //System.out.println("Inside ticket not equal to null");
                    Map<String, String> ticketDate = new LinkedHashMap<String, String>();
                    ticketDate.put("Ticket Number", ticket.getTicketNumber());
                    ticketDate.put("Flight Number", ticket.getFlightNumber());
                    ticketDate.put("Departure Location", ticket.getDepartureLocation());
                    ticketDate.put("Arrival Location", ticket.getArrivalLocation());
                    ticketDate.put("Flight Duration", ticket.getFlightDuration() + "");
                    ticketDate.put("Departure Date", ticket.getDepartureDate() + "");
                    ticketDate.put("Departure Time", ticket.getDepartureTime() + "");
                    ticketDate.put("Price", ticket.getPrice() + "");
                    ticketDate.put("Account ID", ticket.getUserName());
                    ticketDate.put("Booking Date and Time", ticket.getBookingDate() + "");
                    ticketDate.put("Seat Number", ticket.getSeatNumber());
                    ticketDate.put("Seat Type", ticket.getSeatType());
                    ticketDate.put("Has Entertainment?", ticket.getHasEntertainment());
                    ticketDate.put("Has Wifi", ticket.getHasWifi());
                    modelAndView = new ModelAndView("TicketPDF", "ticketPDF", ticketDate);

                } else {
                    modelAndView = new ModelAndView("customer-error", "message", "Sorry, requested ticket does not exist");
                }
            } else {
                modelAndView = new ModelAndView("customer-error", "message", "Sorry, requested ticket does not exist");
            }

        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }

        return modelAndView;
    }

}
