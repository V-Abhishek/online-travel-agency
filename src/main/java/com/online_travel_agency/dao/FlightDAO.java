/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.dao;

import com.online_travel_agency.pojo.Aircraft;
import com.online_travel_agency.pojo.Flight;
import com.online_travel_agency.pojo.Seat;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import static com.online_travel_agency.utility.Constants.YES;
import static com.online_travel_agency.utility.Constants.NO;
import static com.online_travel_agency.utility.Constants.WINDOW;
import static com.online_travel_agency.utility.Constants.MIDDLE;
import static com.online_travel_agency.utility.Constants.AISLE;
import static com.online_travel_agency.utility.Constants.A;
import static com.online_travel_agency.utility.Constants.B;
import static com.online_travel_agency.utility.Constants.C;
import static com.online_travel_agency.utility.Constants.D;
import static com.online_travel_agency.utility.Constants.E;
import static com.online_travel_agency.utility.Constants.F;
import org.apache.log4j.Logger;

/**
 *
 * @author Abhishek
 */
public class FlightDAO {

    private static final Logger logger = Logger.getLogger(FlightDAO.class);

    public boolean isUnique(String flightNumber) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Flight WHERE flightNumber = :flightNumber");
            query.setString("flightNumber", flightNumber);
            ArrayList<Flight> flightList = (ArrayList<Flight>) query.list();
            connectionDAO.commit();
            if (!flightList.isEmpty()) {
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

    public ArrayList<Flight> getAllFlights(Aircraft aircraft) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<Flight> requestList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("Select * from flights where aircraftId = :aircraftId").addEntity(Flight.class);
            query.setParameter("aircraftId", aircraft.getAircraftId());
            requestList = (ArrayList<Flight>) query.list();
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

    public Flight addFlight(String flightNumber, String departureLocation, String arrivalLocation, double price, double flightDuration, Date date, Time time, Aircraft aircraft) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            if (aircraft != null) {
                connectionDAO.beginTransaction();
                Flight flight = new Flight();
                flight.setFlightNumber(flightNumber);
                flight.setDepartureLocation(departureLocation);
                flight.setArrivalLocation(arrivalLocation);
                flight.setIsAvailable(YES);
                flight.setPrice(price);
                flight.setFlightDuration(flightDuration);
                flight.setDate(date);
                flight.setFlightTime(time);
                flight.setAircraft(aircraft);

                connectionDAO.getSession().save(flight);
                aircraft.getFlightDirectory().add(flight);

                connectionDAO.getSession().update(aircraft);
                connectionDAO.commit();
                return flight;
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return null;
    }

    public Boolean hasConflict(Time flightTime, int aircraftId, Date date) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("SELECT * FROM travel_agency.flights WHERE aircraftId = :aircraftId AND departure_time = :departure_time AND departure_date = :departure_date").addEntity(Flight.class);
            query.setInteger("aircraftId", aircraftId);
            query.setTime("departure_time", flightTime);
            query.setDate("departure_date", date);
            ArrayList<Flight> flightList = (ArrayList<Flight>) query.list();
            connectionDAO.commit();
            if (!flightList.isEmpty()) {
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

    public int cancelFlight(int flightId) {
        int result = 0;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Flight WHERE flightId = :flightId");
            query.setInteger("flightId", flightId);
            Flight flight = (Flight) query.uniqueResult();
            if (flight != null) {
                flight.setIsAvailable(NO);
                connectionDAO.getSession().update(flight);
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

    public void generateSeats(Flight flight) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            for (int i = 1; i <= 5; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i + A);
                seat.setBooked(NO);
                seat.setSeatType(WINDOW);
                seat.setFlight(flight);
                flight.getSeatDirectory().add(seat);
                connectionDAO.getSession().save(seat);
                connectionDAO.getSession().update(flight);
            }
            for (int i = 1; i <= 5; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i + F);
                seat.setBooked(NO);
                seat.setSeatType(WINDOW);
                seat.setFlight(flight);
                flight.getSeatDirectory().add(seat);
                connectionDAO.getSession().save(seat);
                connectionDAO.getSession().update(flight);
            }
            for (int i = 1; i <= 5; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i + C);
                seat.setBooked(NO);
                seat.setSeatType(AISLE);
                seat.setFlight(flight);
                flight.getSeatDirectory().add(seat);
                connectionDAO.getSession().save(seat);
                connectionDAO.getSession().update(flight);
            }
            for (int i = 1; i <= 5; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i + D);
                seat.setBooked(NO);
                seat.setSeatType(AISLE);
                seat.setFlight(flight);
                flight.getSeatDirectory().add(seat);
                connectionDAO.getSession().save(seat);
                connectionDAO.getSession().update(flight);
            }
            for (int i = 1; i <= 5; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i + B);
                seat.setBooked(NO);
                seat.setSeatType(MIDDLE);
                seat.setFlight(flight);
                flight.getSeatDirectory().add(seat);
                connectionDAO.getSession().save(seat);
                connectionDAO.getSession().update(flight);
            }
            for (int i = 1; i <= 5; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i + E);
                seat.setBooked(NO);
                seat.setSeatType(MIDDLE);
                seat.setFlight(flight);
                flight.getSeatDirectory().add(seat);
                connectionDAO.getSession().save(seat);
                connectionDAO.getSession().update(flight);
            }
            connectionDAO.commit();

        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
    }

    public ArrayList<Flight> searchFlights(String departure_location, String arrival_location, Date date) {
        System.out.println(departure_location + arrival_location + date);
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<Flight> flightList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("SELECT * FROM travel_agency.flights WHERE departure_location = :departure_location AND arrival_location = :arrival_location AND departure_date = :departure_date").addEntity(Flight.class);
            query.setString("departure_location", departure_location);
            query.setString("arrival_location", arrival_location);
            query.setDate("departure_date", date);
            ArrayList<Flight> preList = (ArrayList<Flight>) query.list();
            if (preList != null) {
                flightList = new ArrayList<Flight>();
                for (Flight flight : preList) {
                    if (flight.getIsAvailable().equals(YES)) {
                        for (Seat seat : flight.getSeatDirectory()) {
                            if (seat.getBooked().equals(NO)) {
                                flightList.add(flight);
                                break;
                            }
                        }
                    }
                }
            }
            connectionDAO.commit();
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return flightList;
    }

    public Flight getFlight(int flightId) {
        Flight flight = null;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Flight WHERE flightId = :flightId");
            query.setInteger("flightId", flightId);
            flight = (Flight) query.uniqueResult();
            connectionDAO.commit();
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return flight;
    }

}
