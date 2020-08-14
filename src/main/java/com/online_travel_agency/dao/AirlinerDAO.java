/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.dao;

import com.online_travel_agency.mail.TriggerMail;
import com.online_travel_agency.pojo.Airliner;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Abhishek
 */
public class AirlinerDAO {

    private static final Logger logger = Logger.getLogger(AirlinerDAO.class);

    public boolean isUnique(String email) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Airliner WHERE email = :userId");
            query.setString("userId", email);
            ArrayList<Airliner> airlinersList = (ArrayList<Airliner>) query.list();
            connectionDAO.commit();
            if (!airlinersList.isEmpty()) {
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

    public boolean isNameUnique(String airliner) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Airliner WHERE airliner = :airlineName");
            query.setString("airlineName", airliner);
            ArrayList<Airliner> airlinersList = (ArrayList<Airliner>) query.list();
            connectionDAO.commit();
            if (!airlinersList.isEmpty()) {
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

    public int addNewAirliner(String airlinerName, String name, String phone, String email, String street, String city, String state, String country, String zip, String password) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            connectionDAO.beginTransaction();
            Airliner airliner = new Airliner();
            airliner.setAirliner(airlinerName);
            airliner.setContactName(name);
            airliner.setCellphone(phone);
            airliner.setEmail(email);
            airliner.setStreet(street);
            airliner.setCity(city);
            airliner.setState(state);
            airliner.setCountry(country);
            airliner.setZipCode(zip);
            airliner.setStatus(Byte.MIN_VALUE);
            airliner.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            connectionDAO.getSession().save(airliner);
            connectionDAO.commit();
            result = 1;
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return result;
    }

    public ArrayList<Airliner> getAllAirlinersRequest() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<Airliner> requestList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Airliner WHERE status = -128");
            requestList = (ArrayList<Airliner>) query.list();
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

    public int activateAccounts(HashSet<Integer> ids) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            Query query = null;
            for (Integer id : ids) {
                connectionDAO.beginTransaction();
                query = connectionDAO.getSession().createQuery("FROM Airliner WHERE id = :id");
                query.setInteger("id", id);
                Airliner airliner = (Airliner) query.uniqueResult();
                if (airliner.getStatus() == Byte.MIN_VALUE) {
                    airliner.setStatus(Byte.MAX_VALUE);
                }
                connectionDAO.getSession().save(airliner);
                connectionDAO.commit();
                result = 1;
                TriggerMail triggerMail = new TriggerMail();
                triggerMail.triggerActivationMail(airliner.getEmail(), airliner.getContactName(), airliner.getAirliner());
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

    public int deActivateAccounts(HashSet<Integer> ids) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            Query query = null;
            for (Integer id : ids) {
                connectionDAO.beginTransaction();
                query = connectionDAO.getSession().createQuery("FROM Airliner WHERE id = :id");
                query.setInteger("id", id);
                Airliner airliner = (Airliner) query.uniqueResult();
                connectionDAO.getSession().delete(airliner);
                connectionDAO.commit();
                result = 1;
                TriggerMail triggerMail = new TriggerMail();
                triggerMail.triggerDeActivationMail(airliner.getEmail(), airliner.getContactName(), airliner.getAirliner());
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

    public ArrayList<Airliner> getExistingAirliners() {
        ArrayList<Airliner> requestList = null;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Airliner WHERE status = 127");
            requestList = (ArrayList<Airliner>) query.list();
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

    public Airliner authenticateAirliner(String email, String password) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Airliner airliner = null;
        Boolean valid = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Airliner WHERE email = :userId");
            query.setString("userId", email);
            airliner = (Airliner) query.uniqueResult();
            connectionDAO.commit();
            if (airliner != null) {
                valid = checkPass(password, airliner.getPassword());
                if (!valid) {
                    airliner = null;
                }
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return airliner;
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        boolean valid = false;
        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            valid = true;
        }
        return valid;
    }

    public int updateAirliner(Airliner airliner, String name, String phone, String street, String city, String state, String country, String zip) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int id = airliner.getId();
        int result = 0;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Airliner WHERE id = :id");
            query.setInteger("id", id);
            airliner = (Airliner) query.uniqueResult();
            if (airliner != null) {
                airliner.setContactName(name);
                airliner.setCellphone(phone);
                airliner.setStreet(street);
                airliner.setCity(city);
                airliner.setState(state);
                airliner.setCountry(country);
                airliner.setZipCode(zip);
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

    public int deleteAccount(int id) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            Query query = null;
            connectionDAO.beginTransaction();
            query = connectionDAO.getSession().createQuery("FROM Airliner WHERE id = :id");
            query.setInteger("id", id);
            Airliner airliner = (Airliner) query.uniqueResult();
            connectionDAO.getSession().delete(airliner);
            connectionDAO.commit();
            result = 1;
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
