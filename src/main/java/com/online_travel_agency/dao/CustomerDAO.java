/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.dao;

import com.online_travel_agency.pojo.Customer;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Abhishek
 */
public class CustomerDAO {

    private static final Logger logger = Logger.getLogger(CustomerDAO.class);

    public boolean isUnique(String email) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Customer WHERE email = :userId");
            query.setString("userId", email);
            ArrayList<Customer> customerList = (ArrayList<Customer>) query.list();
            connectionDAO.commit();
            if (!customerList.isEmpty()) {
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

    public int addNewCustomer(String firstName, String lastName, String phone, String email, String street, String city, String state, String country, String zip, String password) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            connectionDAO.beginTransaction();
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setCellphone(phone);
            customer.setEmail(email);
            customer.setStreet(street);
            customer.setCity(city);
            customer.setState(state);
            customer.setCountry(country);
            customer.setZipCode(zip);
            customer.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            connectionDAO.getSession().save(customer);
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

    public Customer authenticateCustomer(String email, String password) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Customer customer = null;
        Boolean valid = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Customer WHERE email = :userId");
            query.setString("userId", email);
            customer = (Customer) query.uniqueResult();
            connectionDAO.commit();
            if (customer != null) {
                valid = checkPass(password, customer.getPassword());
                if (!valid) {
                    customer = null;
                }
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return customer;
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        boolean valid = false;
        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            valid = true;
        }
        return valid;
    }

    public int updateCustomer(Customer customer, String firstName, String lastName, String phone, String street, String city, String state, String country, String zip) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int id = customer.getId();
        int result = 0;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Customer WHERE id = :id");
            query.setInteger("id", id);
            customer = (Customer) query.uniqueResult();
            if (customer != null) {
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setCellphone(phone);
                customer.setStreet(street);
                customer.setCity(city);
                customer.setState(state);
                customer.setCountry(country);
                customer.setZipCode(zip);
                connectionDAO.getSession().update(customer);
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
