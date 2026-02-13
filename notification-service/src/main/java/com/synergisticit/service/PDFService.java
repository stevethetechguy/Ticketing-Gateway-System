package com.synergisticit.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
public class PDFService {

    public byte[] generateRejectedTicketPdf(
            String ticketId,
            String title,
            String description,
            String createdByName,
            String createdByEmail,
            String createdDate,
            String rejectionReason) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Ticket Rejection Details")
                    .setBold()
                    .setFontSize(18));

            document.add(new Paragraph("\n"));

            float[] columnWidths = {150F, 350F};
            Table table = new Table(UnitValue.createPointArray(columnWidths));

            table.addCell(new Cell().add(new Paragraph("Ticket ID")));
            table.addCell(new Cell().add(new Paragraph(ticketId)));

            table.addCell(new Cell().add(new Paragraph("Title")));
            table.addCell(new Cell().add(new Paragraph(title)));

            table.addCell(new Cell().add(new Paragraph("Description")));
            table.addCell(new Cell().add(new Paragraph(description)));

            table.addCell(new Cell().add(new Paragraph("Created By")));
            table.addCell(new Cell().add(new Paragraph(createdByName + " (" + createdByEmail + ")")));

            table.addCell(new Cell().add(new Paragraph("Created Date")));
            table.addCell(new Cell().add(new Paragraph(createdDate)));

            table.addCell(new Cell().add(new Paragraph("Rejection Reason")));
            table.addCell(new Cell().add(new Paragraph(rejectionReason)));

            document.add(table);

            document.close();

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating rejected ticket PDF", e);
        }
    }
    
    
    public byte[] generateResolvedTicketPdf(
            String ticketId,
            String title,
            String description,
            String createdByName,
            String createdByEmail,
            String createdDate,
            String resolvedReason) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Ticket Resolution Details")
                    .setBold()
                    .setFontSize(18));

            document.add(new Paragraph("\n"));

            float[] columnWidths = {150F, 350F};
            Table table = new Table(UnitValue.createPointArray(columnWidths));

            table.addCell(new Cell().add(new Paragraph("Ticket ID")));
            table.addCell(new Cell().add(new Paragraph(ticketId)));

            table.addCell(new Cell().add(new Paragraph("Title")));
            table.addCell(new Cell().add(new Paragraph(title)));

            table.addCell(new Cell().add(new Paragraph("Description")));
            table.addCell(new Cell().add(new Paragraph(description)));

            table.addCell(new Cell().add(new Paragraph("Created By")));
            table.addCell(new Cell().add(new Paragraph(createdByName + " (" + createdByEmail + ")")));

            table.addCell(new Cell().add(new Paragraph("Created Date")));
            table.addCell(new Cell().add(new Paragraph(createdDate)));

            table.addCell(new Cell().add(new Paragraph("Resolution")));
            table.addCell(new Cell().add(new Paragraph(resolvedReason)));

            document.add(table);

            document.close();

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating rejected ticket PDF", e);
        }
    }
}
