/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.online_travel_agency.dao.AirlinerDAO;
import com.online_travel_agency.dao.TicketDAO;
import com.online_travel_agency.pojo.Airliner;
import com.online_travel_agency.pojo.Ticket;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Abhishek
 */
public class TravelAdminController extends AbstractController {

    public TravelAdminController() {
    }
    private static final Logger logger = Logger.getLogger(TravelAdminController.class);

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            if (action.equals("")) {
                modelAndView = new ModelAndView("admin-home");
            }
            if (action.equals("requestList")) {
                AirlinerDAO airlinerDAO = new AirlinerDAO();
                ArrayList<Airliner> requestList = airlinerDAO.getAllAirlinersRequest();
                modelAndView = new ModelAndView("approve-accounts", "requestList", requestList);
            }
            if (action.equals("ApAndDisapList")) {
                int result = 0;
                String approvalList = request.getParameter("approvalList");
                String disapprovalList = request.getParameter("rejectlList");
                if (approvalList.isEmpty() && disapprovalList.isEmpty()) {
                    modelAndView = new ModelAndView("error", "message", "Please recheck your inputs");
                }
                if (!approvalList.isEmpty()) {
                    HashSet<Integer> ids = new HashSet<Integer>();
                    String[] approval = approvalList.split(",");
                    for (String string : approval) {
                        ids.add(Integer.parseInt(string));
                    }
                    AirlinerDAO airlinerDAO = new AirlinerDAO();
                    result = airlinerDAO.activateAccounts(ids);
                }
                if (!disapprovalList.isEmpty()) {
                    HashSet<Integer> ids = new HashSet<Integer>();
                    String[] approval = disapprovalList.split(",");
                    for (String string : approval) {
                        ids.add(Integer.parseInt(string));
                    }
                    AirlinerDAO airlinerDAO = new AirlinerDAO();
                    result = airlinerDAO.deActivateAccounts(ids);
                }
                if (result == 1) {
                    modelAndView = new ModelAndView("admin-home");
                } else {
                    modelAndView = new ModelAndView("admin-error", "message", "Activation/Deactivation did not go well");
                }
            }

            if (action.equals("existingAirliners")) {
                AirlinerDAO airlinerDAO = new AirlinerDAO();
                ArrayList<Airliner> requestList = airlinerDAO.getExistingAirliners();
                modelAndView = new ModelAndView("existing-airliners", "requestList", requestList);
            }
            if (action.equals("viewBookings")) {
                TicketDAO ticketDAO = new TicketDAO();
                ArrayList<Ticket> requestList = ticketDAO.getAllTickets();
                modelAndView = new ModelAndView("view-all-booking", "requestList", requestList);
            }
            if (action.equals("deleteAirlinerAccounts")) {
                String[] DeleteIds = request.getParameterValues("deleteIds");
                int result = 0;
                if (DeleteIds.length > 0) {
                    AirlinerDAO airlinerDAO = new AirlinerDAO();
                    for (String DeleteId : DeleteIds) {
                        result = airlinerDAO.deleteAccount(Integer.parseInt(DeleteId));
                        if (result == 0) {
                            break;
                        }
                    }
                    if (result == 1) {
                        modelAndView = new ModelAndView("admin-home");
                    } else {
                        modelAndView = new ModelAndView("admin-error", "message", "Deactivation did not go well");
                    }
                } else {
                    modelAndView = new ModelAndView("admin-error", "message", "Deactivation did not go well");
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }

}
