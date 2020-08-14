/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

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
public class FrontController extends AbstractController {

    public FrontController() {
    }
    private static final Logger logger = Logger.getLogger(FrontController.class);

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            if (action.equals("logout")) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        return new ModelAndView("home");
    }

}
