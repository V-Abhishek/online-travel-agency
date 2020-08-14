/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.online_travel_agency.viewgenerator;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

/**
 *
 * @author Abhishek
 */
public class TicketPDFView extends AbstractPdfView {

    private static final Logger logger = Logger.getLogger(TicketPDFView.class);

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document dcmnt, PdfWriter writer, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        try {
            Map<String, String> revenueData = (Map<String, String>) map.get("ticketPDF");
            dcmnt.add(new Paragraph("Ticket Details"));
            Table table = new Table(2);
            for (Map.Entry<String, String> entry : revenueData.entrySet()) {
                table.addCell(entry.getKey());
                table.addCell(entry.getValue());
            }
            dcmnt.add(table);
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }

    }
}
