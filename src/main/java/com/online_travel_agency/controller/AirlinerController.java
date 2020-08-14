/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.controller;

import com.google.gson.JsonObject;
import com.online_travel_agency.dao.AircraftDAO;
import com.online_travel_agency.pojo.Aircraft;
import com.online_travel_agency.pojo.Airliner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import static com.online_travel_agency.utility.Constants.YES;
import static com.online_travel_agency.utility.Constants.NO;
import com.online_travel_agency.utility.FieldValidations;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Abhishek
 */
public class AirlinerController extends AbstractController {

    public AirlinerController() {
    }

    private static final Logger logger = Logger.getLogger(AirlinerController.class);

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            Airliner airliner = (Airliner) request.getSession(false).getAttribute("airliner");
            if (action.equals("") && airliner != null) {
                modelAndView = new ModelAndView("airliner-home");
            }
            if (action.equals("addAircrafts") && airliner != null) {
                modelAndView = new ModelAndView("add-aircrafts");
            }
            if (action.equals("registerAircraft") && airliner != null) {
                String serialNumber = request.getParameter("serialNumber") != null ? request.getParameter("serialNumber") : "";
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String model = request.getParameter("model") != null ? request.getParameter("model") : "";
                String entertainment = request.getParameter("entertainment") != null ? request.getParameter("entertainment") : "";
                String wifi = request.getParameter("wifi") != null ? request.getParameter("wifi") : "";
                if (!serialNumber.equalsIgnoreCase("") && !name.equalsIgnoreCase("") && !model.equalsIgnoreCase("") && !wifi.equalsIgnoreCase("")) {
                    FieldValidations fv = new FieldValidations();
                    if (fv.validateAplhaNumeric(serialNumber) && fv.validateNames(name) && fv.validateAplhaNumeric(model)) {
                        AircraftDAO aircraftDAO = new AircraftDAO();
                        if (entertainment.equalsIgnoreCase("YES")) {
                            entertainment = YES;
                        } else {
                            entertainment = NO;
                        }
                        if (wifi.equalsIgnoreCase("YES")) {
                            wifi = YES;
                        } else {
                            wifi = NO;
                        }
                        int result = aircraftDAO.addAircraft(serialNumber, name, model, entertainment, wifi, airliner);
                        if (result == 1) {
                            modelAndView = new ModelAndView("airliner-success", "message", "Aircraft details successfully added.");
                        } else {
                            modelAndView = new ModelAndView("airliner-error", "message", "Aircraft not added");
                        }
                    } else {
                        modelAndView = new ModelAndView("airliner-error", "message", "Aircraft not added");
                    }

                } else {
                    modelAndView = new ModelAndView("airliner-error", "message", "Aircraft not added");
                }
            }
            if (action.equalsIgnoreCase("validateSerialNumber")) {
                String serialNumber = request.getParameter("serialNumber") != null ? request.getParameter("serialNumber") : "";
                if (!serialNumber.equalsIgnoreCase("")) {
                    AircraftDAO aircraftDAO = new AircraftDAO();
                    Boolean exist = aircraftDAO.isUnique(serialNumber);
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
            if (action.equals("manageAircrafts") && airliner != null) {
                AircraftDAO aircraftDAO = new AircraftDAO();
                ArrayList<Aircraft> requestList = aircraftDAO.getAllAircrafts(airliner);
                modelAndView = new ModelAndView("manage-aircrafts", "requestList", requestList);
            }
            if (action.equalsIgnoreCase("update")) {
                String aircraftId = request.getParameter("id") != null ? request.getParameter("id") : "";
                if (!aircraftId.equalsIgnoreCase("")) {
                    AircraftDAO aircraftDAO = new AircraftDAO();
                    Aircraft aircraft = aircraftDAO.getAircraft(Integer.parseInt(aircraftId));
                    if (aircraft != null) {
                        modelAndView = new ModelAndView("update-aircraft", "aircraft", aircraft);
                    } else {
                        modelAndView = new ModelAndView("airliner-error", "message", "Aircraft you requested for does not exist");
                    }

                }
            }
            if (action.equals("updateAircraft") && airliner != null) {
                String aircraftId = request.getParameter("aircraftId") != null ? request.getParameter("aircraftId") : "";
                String serialNumber = request.getParameter("serialNumber") != null ? request.getParameter("serialNumber") : "";
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String model = request.getParameter("model") != null ? request.getParameter("model") : "";
                String entertainment = request.getParameter("entertainment") != null ? request.getParameter("entertainment") : "";
                String wifi = request.getParameter("wifi") != null ? request.getParameter("wifi") : "";
                if (!aircraftId.equalsIgnoreCase("") && !serialNumber.equalsIgnoreCase("") && !name.equalsIgnoreCase("") && !model.equalsIgnoreCase("") && !wifi.equalsIgnoreCase("")) {
                    FieldValidations fv = new FieldValidations();
                    if (fv.validateAplhaNumeric(serialNumber) && fv.validateNames(name) && fv.validateAplhaNumeric(model)) {
                        AircraftDAO aircraftDAO = new AircraftDAO();
                        if (entertainment.equalsIgnoreCase("YES")) {
                            entertainment = YES;
                        } else {
                            entertainment = NO;
                        }
                        if (wifi.equalsIgnoreCase("YES")) {
                            wifi = YES;
                        } else {
                            wifi = NO;
                        }
                        int result = aircraftDAO.updateAircraft(Integer.parseInt(aircraftId), serialNumber, name, model, entertainment, wifi, airliner);
                        if (result == 1) {
                            modelAndView = new ModelAndView("airliner-success", "message", "Aircraft details successfully updated.");
                        } else {
                            modelAndView = new ModelAndView("airliner-error", "message", "Aircraft details not updated");
                        }
                    } else {
                        modelAndView = new ModelAndView("airliner-error", "message", "Aircraft details not updated");
                    }
                } else {
                    modelAndView = new ModelAndView("airliner-error", "message", "Aircraft details not updated");
                }
            }
            if (action.equals("delete") && airliner != null) {
                String aircraftId = request.getParameter("id") != null ? request.getParameter("id") : "";
                if (!aircraftId.equalsIgnoreCase("")) {
                    AircraftDAO aircraftDAO = new AircraftDAO();
                    int result = aircraftDAO.deleteAircraft(Integer.parseInt(aircraftId));
                    if (result == 1) {
                        modelAndView = new ModelAndView("airliner-success", "message", "Aircraft details successfully deleted.");
                    } else {
                        modelAndView = new ModelAndView("airliner-error", "message", "Aircraft details not deleted");
                    }
                } else {
                    modelAndView = new ModelAndView("airliner-error", "message", "Aircraft details not deleted");
                }
            }
            if (action.equals("changeProfile") && airliner != null) {
                modelAndView = new ModelAndView("update-airliner");
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }

}
