package ru.neoflex.findcreditiduploadpdf.utils;

import com.itextpdf.io.exceptions.ExceptionUtil;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.neoflex.findcreditiduploadpdf.model.ProcessedRequests;
import java.awt.*;
import java.io.*;

import static java.awt.font.TextAttribute.FONT;


public class GeneratePdfReport {

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);
    public static final String FONT = "./src/main/resources/helvetica_pandoge_com/HelveticaRegular/HelveticaRegular.ttf";

    //private static Font paragraphFont = new Font("resources/helvetica_pandoge_com/HelveticaRegular/HelveticaRegular.ttf",12,12);
    public static ByteArrayInputStream pdfReports(ProcessedRequests credit) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out));
            Document doc = new Document(pdfDoc);
            PdfFont f = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            preface.add(new Paragraph(new Text("Кредитный договор").setFont(f)));
            addEmptyLine(preface, 1);


            Table table = new Table(5).useAllAvailableWidth();
            Cell colid = new Cell().add(new Paragraph("Ид заявки").setFont(f));
            table.addCell(colid);
            Cell colname = new Cell().add(new Paragraph("ФИО").setFont(f));
            table.addCell(colname);
            Cell colbithday = new Cell().add(new Paragraph("Год рождения").setFont(f));
            table.addCell(colbithday);
            Cell colage = new Cell().add(new Paragraph("Сумма").setFont(f));
            table.addCell(colage);
            Cell colsurname = new Cell().add(new Paragraph("Количество месяцев").setFont(f));
            table.addCell(colsurname);

            Cell id = new Cell().add(new Paragraph(credit.getId().toString()).setFont(f))
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(id);

            Cell fio = new Cell().add(new Paragraph(credit.getFio()).setFont(f))
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(fio);

            Cell birthday = new Cell().add(new Paragraph(credit.getBirthday()).setFont(f))
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(birthday);

            Cell summa = new Cell().add(new Paragraph(credit.getSumma().toString()).setFont(f))
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(summa);

            Cell countMonth = new Cell().add(new Paragraph(credit.getCountMonth().toString()).setFont(f))
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(countMonth);

            doc.add(preface);
            doc.add(table);
            pdfDoc.close();

        } catch (Exception ex) {

            logger.error("Error occurred: {0}", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
