/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.online_travel_agency.dao.TicketDAO;
import com.online_travel_agency.pojo.Customer;
import com.online_travel_agency.pojo.Ticket;
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
public class CustomerController extends AbstractController {

    public CustomerController() {
    }
    private static final Logger logger = Logger.getLogger(CustomerController.class);

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            Customer customer = (Customer) request.getSession(false).getAttribute("customer");
            if (action.equals("") && customer != null) {
                modelAndView = new ModelAndView("customer-home");
            }
            if (action.equals("changeProfile") && customer != null) {
                modelAndView = new ModelAndView("update-customer");
            }
            if (action.equals("searchFlight") && customer != null) {
                modelAndView = new ModelAndView("search-flights");
            }
            if (action.equals("viewTickets") && customer != null) {
                TicketDAO ticketDAO = new TicketDAO();
                ArrayList<Ticket> requestList = ticketDAO.getMyTickets(customer.getId());
                modelAndView = new ModelAndView("my-tickets", "requestList", requestList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }

}
