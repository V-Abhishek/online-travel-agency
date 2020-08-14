/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.online_travel_agency.dao.ConnectionDAO;
import com.online_travel_agency.dao.FlightDAO;
import com.online_travel_agency.dao.SeatDAO;
import com.online_travel_agency.mail.TriggerMail;
import com.online_travel_agency.pojo.Flight;
import com.online_travel_agency.pojo.Ticket;
import com.online_travel_agency.pojo.Customer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import static com.online_travel_agency.utility.Constants.WINDOW;
import static com.online_travel_agency.utility.Constants.AISLE;
import static com.online_travel_agency.utility.Constants.MIDDLE;
import com.online_travel_agency.utility.FieldValidations;
import org.apache.log4j.Logger;

/**
 *
 * @author Abhishek
 */
public class SeatController extends AbstractController {
    
    public SeatController() {
    }
    private static final Logger logger = Logger.getLogger(SeatController.class);
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            HttpSession httpSession = request.getSession(false);
            if (httpSession != null && httpSession.getAttribute("customer") != null) {
                Customer customer = (Customer) httpSession.getAttribute("customer");
                if (action.equals("bookTicket")) {
                    String flightId = request.getParameter("id") != null ? request.getParameter("id") : "";
                    String seatNumber = request.getParameter("seatNumber") != null ? request.getParameter("seatNumber") : "";
                    String seatType = request.getParameter("seatType") != null ? request.getParameter("seatType") : "";
                    if (!flightId.equalsIgnoreCase("") && !seatNumber.equalsIgnoreCase("")) {
                        FieldValidations fv = new FieldValidations();
                        if (fv.validateAplhaNumeric(seatNumber) && fv.validateNames(seatType)) {
                            SeatDAO seatDAO = new SeatDAO();
                            int approved = seatDAO.bookSeat(Integer.parseInt(flightId), seatNumber);
                            if (approved == 1) {
                                Ticket ticket = new Ticket();
                                FlightDAO flightDAO = new FlightDAO();
                                Flight flight = flightDAO.getFlight(Integer.parseInt(flightId));
                                
                                if (flight != null) {
                                    ConnectionDAO connectionDAO = new ConnectionDAO();
                                    connectionDAO.beginTransaction();
                                    ticket.setArrivalLocation(flight.getArrivalLocation());
                                    ticket.setBookingDate(new Timestamp(System.currentTimeMillis()));
                                    ticket.setCustomer(customer);
                                    ticket.setDepartureDate(flight.getDate());
                                    ticket.setDepartureLocation(flight.getDepartureLocation());
                                    ticket.setDepartureTime(flight.getFlightTime());
                                    ticket.setFlightDuration(flight.getFlightDuration());
                                    ticket.setFlightNumber(flight.getFlightNumber());
                                    ticket.setHasEntertainment(flight.getAircraft().getHasEntertainment());
                                    ticket.setHasWifi(flight.getAircraft().getHasWifi());
                                    ticket.setIsAvailable(flight.getIsAvailable());
                                    ticket.setPrice(flight.getPrice());
                                    ticket.setSeatNumber(seatNumber);
                                    if (seatType.equalsIgnoreCase("w")) {
                                        ticket.setSeatType(WINDOW);
                                    }
                                    if (seatType.equalsIgnoreCase("a")) {
                                        ticket.setSeatType(AISLE);
                                    }
                                    if (seatType.equalsIgnoreCase("m")) {
                                        ticket.setSeatType(MIDDLE);
                                    }
                                    Date date = new Date();
                                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss");
                                    String formattedDate = sdf.format(date);
                                    String splitdate = formattedDate.split(" ")[0].split("/")[0] + formattedDate.split(" ")[0].split("/")[1] + formattedDate.split(" ")[0].split("/")[2];
                                    String splittime = formattedDate.split(" ")[1].split(":")[0] + formattedDate.split(" ")[1].split(":")[1] + formattedDate.split(" ")[1].split(":")[2];
                                    ticket.setTicketNumber(customer.getEmail().split("@")[0] + splitdate + splittime);
                                    ticket.setUserName(customer.getEmail());
                                    connectionDAO.getSession().save(ticket);
                                    customer.getBlockTickets().add(ticket);
                                    connectionDAO.getSession().update(customer);
                                    connectionDAO.commit();
                                    TriggerMail triggerMail = new TriggerMail();
                                    triggerMail.ticketConfirmationEmail(customer.getEmail(), customer.getFirstName(), flight.getFlightNumber(), flight.getDepartureLocation(), flight.getArrivalLocation(), flight.getDate(), flight.getFlightTime(), seatNumber);
                                    modelAndView = new ModelAndView("customer-success", "message", "Your Flight Ticket is successfully booked. Have a safe travel. Thank you");
                                }
                            } else {
                                modelAndView = new ModelAndView("customer-error", "message", "Cannot reserve ticket for requested flight");
                            }
                        } else {
                            modelAndView = new ModelAndView("customer-error", "message", "Cannot reserve ticket for requested flight");
                        }
                    } else {
                        modelAndView = new ModelAndView("customer-error", "message", "Cannot reserve ticket for requested flight");
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        
        return modelAndView;
    }
    
}
