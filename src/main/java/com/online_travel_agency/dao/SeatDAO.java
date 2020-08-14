/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.dao;

import com.online_travel_agency.pojo.Seat;
import static com.online_travel_agency.utility.Constants.YES;
import static com.online_travel_agency.utility.Constants.NO;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Abhishek
 */
public class SeatDAO {

    private static final Logger logger = Logger.getLogger(SeatDAO.class);

    public int bookSeat(int flightId, String seatNumber) {
        int result = 0;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("SELECT * FROM travel_agency.seats WHERE flightId = :flightId AND seat_number = :seat_number AND booking_status = :booking_status").addEntity(Seat.class);
            query.setInteger("flightId", flightId);
            query.setString("seat_number", seatNumber);
            query.setString("booking_status", NO);
            Seat seat = (Seat) query.uniqueResult();
            if (seat != null) {
                seat.setBooked(YES);
                connectionDAO.getSession().update(seat);
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
