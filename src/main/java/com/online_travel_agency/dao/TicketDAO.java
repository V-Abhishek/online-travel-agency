/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.dao;

import com.online_travel_agency.pojo.Ticket;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Abhishek
 */
public class TicketDAO {

    private static final Logger logger = Logger.getLogger(TicketDAO.class);

    public ArrayList<Ticket> getMyTickets(int id) {
        ArrayList<Ticket> requestList = null;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("Select * from tickets where customer_id = :id").addEntity(Ticket.class);
            query.setInteger("id", id);
            requestList = (ArrayList<Ticket>) query.list();
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

    public Ticket getMyTicket(int id) {
        Ticket ticket = null;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Ticket WHERE ticketId =:ticketId");
            query.setInteger("ticketId", id);
            ticket = (Ticket) query.uniqueResult();
            connectionDAO.commit();
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return ticket;
    }

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> requestList = null;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Ticket");
            requestList = (ArrayList<Ticket>) query.list();
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

}
