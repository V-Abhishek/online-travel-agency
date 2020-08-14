/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.mail;

import java.sql.Date;
import java.sql.Time;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

/**
 *
 * @author Abhishek
 */
public class TriggerMail {

    private static final Logger logger = Logger.getLogger(TriggerMail.class);

    public static void triggerActivationMail(String airlinerEmail, String name, String airlinerName) {

        final String username = "***";
        final String password = "***";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("***"));
            System.out.println("***");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(airlinerEmail));
            message.setSubject("Account Activated");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hello " + name + "," + "\n\nWe hope you are doing well.\n\nWe are writing this mail to inform you that, " + airlinerName + " is approved to host its flights on our site and your account is activated. Thank you for being a part of our network." + "\n\nThank you" + "\n\nRegards," + "\n" + "Travel World");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email sent");
        } catch (MessagingException e) {
            logger.error(e);
            System.out.println("*");
        }
    }

    public static void triggerDeActivationMail(String airlinerEmail, String name, String airlinerName) {

        final String username = "***";
        final String password = "***";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("***"));
            System.out.println("Deactivation mail");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(airlinerEmail));
            message.setSubject("Account Deactivated");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hello " + name + "," + "\n\nWe hope you are doing well.\n\nWe are sorry to inform you that, " + airlinerName + " is not approved to host its flights on our site at this moment. Please contact customer care more details." + "\n\nThank you" + "\n\nRegards," + "\n" + "Travel World");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email sent");
        } catch (MessagingException e) {
            logger.error(e);
            System.out.println("*");
        }
    }
    
    public void ticketConfirmationEmail(String email, String firstName, String flightNumber, String departureLocation, String arrivalLocation, Date date, Time flightTime, String seatNumber) {
        final String username = "***";
        final String password = "***";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("***"));
            System.out.println("Confirmation Mail");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Ticket Confirmation");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hello " + firstName + "," + "\n\nWe hope you are doing well.\n\nYour flight ticket is confirmed. Following are flight ticket details:"
                    + "\n\nFlight Number: " + flightNumber + " \nDeparture Location: " + departureLocation + "\nArrival Location: " + arrivalLocation + " \nDeparture Date: " + date.toString() + " \nDeparture Time: " + flightTime.toString() + " \nSeat Number: " + seatNumber + "\n\nMake sure to carry all the necessary documents. Have a happy and safe journey." + "\n\nThank you" + "\n\nRegards," + "\n" + "Travel World");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email sent");
        } catch (MessagingException e) {
            System.out.println("*");
            System.out.println(e.getMessage());
        }
    }
}
