/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abhishek
 */
public class Aircraft {

    private int aircraftId;
    private String serialNo;
    private String name;
    private String model;
    private String hasEntertainment;
    private String hasWifi;
    private Airliner airliner;
    private List<Flight> flightDirectory = new ArrayList<Flight>(0);

    public Aircraft() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHasEntertainment() {
        return hasEntertainment;
    }

    public void setHasEntertainment(String hasEntertainment) {
        this.hasEntertainment = hasEntertainment;
    }

    public String getHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(String hasWifi) {
        this.hasWifi = hasWifi;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public Airliner getAirliner() {
        return airliner;
    }

    public void setAirliner(Airliner airliner) {
        this.airliner = airliner;
    }

    public List<Flight> getFlightDirectory() {
        return flightDirectory;
    }

    public void setFlightDirectory(List<Flight> flightDirectory) {
        this.flightDirectory = flightDirectory;
    }

    @Override
    public String toString() {
        return serialNo + "";
    }

}
