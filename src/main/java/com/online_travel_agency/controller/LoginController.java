/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.online_travel_agency.dao.AirlinerDAO;
import com.online_travel_agency.dao.CustomerDAO;
import com.online_travel_agency.dao.FlightDAO;
import com.online_travel_agency.pojo.Airliner;
import com.online_travel_agency.pojo.Customer;
import com.online_travel_agency.pojo.Flight;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import static com.online_travel_agency.utility.Constants.ADMIN_USERNAME;
import static com.online_travel_agency.utility.Constants.ADMIN_PASSWORD;
import org.apache.log4j.Logger;

/**
 *
 * @author Abhishek
 */
public class LoginController extends AbstractController {

    public LoginController() {
    }
    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            if (action.equalsIgnoreCase("airlinerLogin")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String password = request.getParameter("psw") != null ? request.getParameter("psw") : "";
                if (!email.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    AirlinerDAO airlinerDAO = new AirlinerDAO();
                    Airliner airliner = airlinerDAO.authenticateAirliner(email, password);
                    if (airliner != null) {
                        if (airliner.getStatus() != Byte.MIN_VALUE) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("airliner", airliner);
                            modelAndView = new ModelAndView("airliner-home");
                        } else {
                            modelAndView = new ModelAndView("error", "message", "Your account is not active. Please contact customer care.");
                        }
                    } else {
                        modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                    }
                } else {
                    modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                }
            }
            if (action.equalsIgnoreCase("customerLogin")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String password = request.getParameter("psw") != null ? request.getParameter("psw") : "";
                if (!email.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    CustomerDAO customerDAO = new CustomerDAO();
                    Customer customer = customerDAO.authenticateCustomer(email, password);
                    if (customer != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("customer", customer);
                        modelAndView = new ModelAndView("customer-home");
                    } else {
                        modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                    }
                } else {
                    modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                }
            }
            if (action.equalsIgnoreCase("adminLogin")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String password = request.getParameter("psw") != null ? request.getParameter("psw") : "";
                if (!email.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    if (email.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("admin", "admin");
                        modelAndView = new ModelAndView("admin-home");
                    } else {
                        modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                    }
                } else {
                    modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                }
            }
            if (action.equalsIgnoreCase("reserveTicket")) {
                String id = request.getParameter("id") != null ? request.getParameter("id") : "";
                if (!id.equalsIgnoreCase("")) {
                    modelAndView = new ModelAndView("customer-login", "flightId", id);
                } else {
                    modelAndView = new ModelAndView("error", "message", "Requested operation cannot be performed");
                }
            }
            if (action.equalsIgnoreCase("homeCustLogin")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String password = request.getParameter("psw") != null ? request.getParameter("psw") : "";
                String flightId = request.getParameter("flightId") != null ? request.getParameter("flightId") : "";
                if (!email.equalsIgnoreCase("") && !password.equalsIgnoreCase("") && !flightId.equalsIgnoreCase("")) {
                    CustomerDAO customerDAO = new CustomerDAO();
                    Customer customer = customerDAO.authenticateCustomer(email, password);
                    if (customer != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("customer", customer);
                        FlightDAO flightDAO = new FlightDAO();
                        Flight flight = flightDAO.getFlight(Integer.parseInt(flightId));
                        if (flight != null) {
                            modelAndView = new ModelAndView("book-ticket", "flight", flight);
                        } else {
                            modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                        }
                    } else {
                        modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                    }
                } else {
                    modelAndView = new ModelAndView("error", "message", "Please recheck your Username and Password");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }

}
