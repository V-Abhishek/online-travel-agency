/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.custom_tags;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.log4j.Logger;

/**
 *
 * @author Abhishek
 */
public class CurrentDateFormat extends SimpleTagSupport {

    private static final Logger logger = Logger.getLogger(CurrentDateFormat.class);

    private String format;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            if (!format.equalsIgnoreCase("")) {
                DateFormat dateFormat = new SimpleDateFormat(format);
                Date date = new Date();
                out.print(dateFormat.format(date));
            } else {
                format = "yyyy-MM-dd";
                DateFormat dateFormat = new SimpleDateFormat(format);
                Date date = new Date();
                out.print(dateFormat.format(date));
            }

        } catch (Exception ex) {
            logger.error(ex);
            System.out.println(ex.getMessage());
        }
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
