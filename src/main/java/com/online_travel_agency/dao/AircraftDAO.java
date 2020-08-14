/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.dao;

import com.online_travel_agency.pojo.Aircraft;
import com.online_travel_agency.pojo.Airliner;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Abhishek
 */
public class AircraftDAO {

    private static final Logger logger = Logger.getLogger(AircraftDAO.class);

    public boolean isUnique(String serialNo) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Aircraft WHERE serialNo = :serialNo");
            query.setString("serialNo", serialNo);
            ArrayList<Aircraft> aircraftList = (ArrayList<Aircraft>) query.list();
            connectionDAO.commit();
            if (!aircraftList.isEmpty()) {
                exist = true;
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return exist;
    }

    public int addAircraft(String serialNo, String name, String model, String hasEntertainment, String hasWifi, Airliner airliner) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            if (airliner != null) {
                connectionDAO.beginTransaction();
                Aircraft aircraft = new Aircraft();
                aircraft.setName(name);
                aircraft.setModel(model);
                aircraft.setSerialNo(serialNo);
                aircraft.setHasWifi(hasWifi);
                aircraft.setHasEntertainment(hasEntertainment);
                aircraft.setAirliner(airliner);
                connectionDAO.getSession().save(aircraft);
                airliner.getFleet().add(aircraft);
                connectionDAO.getSession().update(airliner);
                connectionDAO.commit();
                result = 1;
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return result;
    }

    public ArrayList<Aircraft> getAllAircrafts(Airliner airliner) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<Aircraft> requestList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("Select * from aircrafts where id = :id").addEntity(Aircraft.class);
            query.setParameter("id", airliner.getId());
            requestList = (ArrayList<Aircraft>) query.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return requestList;
    }

    public Aircraft getAircraft(int aircraftId) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Aircraft aircraft = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Aircraft WHERE aircraftId = :aircraftId");
            query.setInteger("aircraftId", aircraftId);
            aircraft = (Aircraft) query.uniqueResult();
            connectionDAO.commit();
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return aircraft;
    }

    public int updateAircraft(int aircraftId, String serialNumber, String name, String model, String entertainment, String wifi, Airliner airliner) {
        int result = 0;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Aircraft WHERE aircraftId = :aircraftId");
            query.setInteger("aircraftId", aircraftId);
            Aircraft aircraft = (Aircraft) query.uniqueResult();
            if (aircraft == null) {
                return result;
            } else {
                aircraft.setName(name);
                aircraft.setModel(model);
                aircraft.setHasEntertainment(entertainment);
                aircraft.setHasWifi(wifi);
                connectionDAO.getSession().update(aircraft);
                connectionDAO.commit();
                result = 1;
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return result;
    }

    public int deleteAircraft(int aircraftId) {
        int result = 0;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Aircraft WHERE aircraftId = :aircraftId");
            query.setInteger("aircraftId", aircraftId);
            Aircraft aircraft = (Aircraft) query.uniqueResult();
            if (aircraft == null) {
                return result;
            } else {
                connectionDAO.getSession().delete(aircraft);
                connectionDAO.commit();
                result = 1;
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return result;
    }
}
