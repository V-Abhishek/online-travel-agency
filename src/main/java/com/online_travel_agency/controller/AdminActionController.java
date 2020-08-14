/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.google.gson.JsonObject;
import com.online_travel_agency.dao.AirlinerDAO;
import com.online_travel_agency.dao.CustomerDAO;
import com.online_travel_agency.pojo.Airliner;
import com.online_travel_agency.pojo.Customer;
import com.online_travel_agency.utility.FieldValidations;
import java.io.PrintWriter;
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
public class AdminActionController extends AbstractController {

    public AdminActionController() {
    }

    private static final Logger logger = Logger.getLogger(AdminActionController.class);

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            if (action.equalsIgnoreCase("registerAirliner")) {
                String airliner = request.getParameter("airliner") != null ? request.getParameter("airliner") : "";
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String street = request.getParameter("street") != null ? request.getParameter("street") : "";
                String city = request.getParameter("city") != null ? request.getParameter("city") : "";
                String state = request.getParameter("state") != null ? request.getParameter("state") : "";
                String country = request.getParameter("country") != null ? request.getParameter("country") : "";
                String zip = request.getParameter("zip") != null ? request.getParameter("zip") : "";
                String password = request.getParameter("pwd2") != null ? request.getParameter("pwd2") : "";
                if (!airliner.equalsIgnoreCase("") && !name.equalsIgnoreCase("") && !phone.equalsIgnoreCase("") && !email.equalsIgnoreCase("") && !street.equalsIgnoreCase("") && !city.equalsIgnoreCase("") && !state.equalsIgnoreCase("") && !country.equalsIgnoreCase("") && !zip.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    FieldValidations fv = new FieldValidations();
                    if (fv.validateNames(airliner) && fv.validateNames(name) && fv.validatePhoneNumber(phone) && fv.validateEmailAddress(email) && fv.validateAplhaNumeric(street) && fv.validateNames(state) && fv.validateNames(city) && fv.validateNames(country) && fv.validateZipCode(zip) && fv.validatePassword(password)) {
                        AirlinerDAO airlinerDAO = new AirlinerDAO();
                        int result = airlinerDAO.addNewAirliner(airliner, name, phone, email, street, city, state, country, zip, password);
                        if (result == 1) {
                            request.setAttribute("airliner", airliner);
                            modelAndView = new ModelAndView("airliner-thank-you", "name", name);
                        } else {
                            modelAndView = new ModelAndView("error", "message", "Your registeration data had some issues");
                        }
                    } else {
                        modelAndView = new ModelAndView("error", "message", "Your registeration data had some issues");
                    }
                } else {
                    modelAndView = new ModelAndView("error", "message", "Your registeration data had some issues");
                }
            }
            if (action.equalsIgnoreCase("registerCustomer")) {
                String firstName = request.getParameter("firstName") != null ? request.getParameter("firstName") : "";
                String lastName = request.getParameter("lastName") != null ? request.getParameter("lastName") : "";
                String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String street = request.getParameter("street") != null ? request.getParameter("street") : "";
                String city = request.getParameter("city") != null ? request.getParameter("city") : "";
                String state = request.getParameter("state") != null ? request.getParameter("state") : "";
                String country = request.getParameter("country") != null ? request.getParameter("country") : "";
                String zip = request.getParameter("zip") != null ? request.getParameter("zip") : "";
                String password = request.getParameter("pwd2") != null ? request.getParameter("pwd2") : "";
                if (!firstName.equalsIgnoreCase("") && !lastName.equalsIgnoreCase("") && !phone.equalsIgnoreCase("") && !email.equalsIgnoreCase("") && !street.equalsIgnoreCase("") && !city.equalsIgnoreCase("") && !state.equalsIgnoreCase("") && !country.equalsIgnoreCase("") && !zip.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    FieldValidations fv = new FieldValidations();
                    if (fv.validateNames(firstName) && fv.validateNames(lastName) && fv.validatePhoneNumber(phone) && fv.validateEmailAddress(email) && fv.validateAplhaNumeric(street) && fv.validateNames(state) && fv.validateNames(city) && fv.validateNames(country) && fv.validateZipCode(zip) && fv.validatePassword(password)) {
                        CustomerDAO customerDAO = new CustomerDAO();
                        int result = customerDAO.addNewCustomer(firstName, lastName, phone, email, street, city, state, country, zip, password);
                        if (result == 1) {
                            modelAndView = new ModelAndView("customer-thank-you", "name", firstName);
                        } else {
                            modelAndView = new ModelAndView("error", "message", "Your registeration data had some issues");
                        }
                    } else {
                        modelAndView = new ModelAndView("error", "message", "Your registeration data had some issues");
                    }

                } else {
                    modelAndView = new ModelAndView("error", "message", "Your registeration data had some issues");
                }
            }
            if (action.equalsIgnoreCase("validateCustomer")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                if (!email.equalsIgnoreCase("")) {
                    CustomerDAO customerDAO = new CustomerDAO();
                    Boolean exist = customerDAO.isUnique(email);
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
            if (action.equalsIgnoreCase("validateAirliner")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                if (!email.equalsIgnoreCase("")) {
                    AirlinerDAO airlinerDAO = new AirlinerDAO();
                    Boolean exist = airlinerDAO.isUnique(email);
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
            if (action.equalsIgnoreCase("validateAirlinerName")) {
                String airliner = request.getParameter("airliner") != null ? request.getParameter("airliner") : "";
                if (!airliner.equalsIgnoreCase("")) {
                    AirlinerDAO airlinerDAO = new AirlinerDAO();
                    Boolean exist = airlinerDAO.isNameUnique(airliner);
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
            if (action.equalsIgnoreCase("updateAirliner")) {
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("airliner") != null) {
                    Airliner airliner = (Airliner) session.getAttribute("airliner");
                    String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                    String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
                    String street = request.getParameter("street") != null ? request.getParameter("street") : "";
                    String city = request.getParameter("city") != null ? request.getParameter("city") : "";
                    String state = request.getParameter("state") != null ? request.getParameter("state") : "";
                    String country = request.getParameter("country") != null ? request.getParameter("country") : "";
                    String zip = request.getParameter("zip") != null ? request.getParameter("zip") : "";
                    if (airliner != null && !name.equalsIgnoreCase("") && !phone.equalsIgnoreCase("") && !street.equalsIgnoreCase("") && !city.equalsIgnoreCase("") && !state.equalsIgnoreCase("") && !country.equalsIgnoreCase("") && !zip.equalsIgnoreCase("")) {
                        FieldValidations fv = new FieldValidations();
                        if (fv.validateNames(name) && fv.validatePhoneNumber(phone) && fv.validateAplhaNumeric(street) && fv.validateNames(state) && fv.validateNames(city) && fv.validateNames(country) && fv.validateZipCode(zip)) {
                            AirlinerDAO airlinerDAO = new AirlinerDAO();
                            int result = airlinerDAO.updateAirliner(airliner, name, phone, street, city, state, country, zip);
                            if (result == 1) {
                                request.setAttribute("airliner", airliner);
                                modelAndView = new ModelAndView("airliner-success", "message", "Details updated successfully");
                            } else {
                                modelAndView = new ModelAndView("airliner-error", "message", "Details not updated");
                            }
                        } else {
                            modelAndView = new ModelAndView("airliner-error", "message", "Details not updated");
                        }
                    } else {
                        modelAndView = new ModelAndView("airliner-error", "message", "Details not updated");
                    }
                }
            }
            if (action.equalsIgnoreCase("updateCustomer")) {
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("customer") != null) {
                    Customer customer = (Customer) session.getAttribute("customer");
                    String firstName = request.getParameter("firstName") != null ? request.getParameter("firstName") : "";
                    String lastName = request.getParameter("lastName") != null ? request.getParameter("lastName") : "";
                    String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
                    String street = request.getParameter("street") != null ? request.getParameter("street") : "";
                    String city = request.getParameter("city") != null ? request.getParameter("city") : "";
                    String state = request.getParameter("state") != null ? request.getParameter("state") : "";
                    String country = request.getParameter("country") != null ? request.getParameter("country") : "";
                    String zip = request.getParameter("zip") != null ? request.getParameter("zip") : "";
                    if (customer != null && !firstName.equalsIgnoreCase("") && !lastName.equalsIgnoreCase("") && !phone.equalsIgnoreCase("") && !street.equalsIgnoreCase("") && !city.equalsIgnoreCase("") && !state.equalsIgnoreCase("") && !country.equalsIgnoreCase("") && !zip.equalsIgnoreCase("")) {
                        FieldValidations fv = new FieldValidations();
                        if (fv.validateNames(firstName) && fv.validateNames(lastName) && fv.validatePhoneNumber(phone) && fv.validateAplhaNumeric(street) && fv.validateNames(state) && fv.validateNames(city) && fv.validateNames(country) && fv.validateZipCode(zip)) {
                            CustomerDAO customerDAO = new CustomerDAO();
                            int result = customerDAO.updateCustomer(customer, firstName, lastName, phone, street, city, state, country, zip);
                            if (result == 1) {
                                request.setAttribute("customer", customer);
                                modelAndView = new ModelAndView("customer-success", "message", "Details updated successfully");
                            } else {
                                modelAndView = new ModelAndView("customer-error", "message", "Details not updated");
                            }
                        } else {
                            modelAndView = new ModelAndView("customer-error", "message", "Details not updated");
                        }
                    } else {
                        modelAndView = new ModelAndView("customer-error", "message", "Details not updated");
                    }
                }
            }
            if (action.equalsIgnoreCase("")) {
                modelAndView = new ModelAndView("home");
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }

}
