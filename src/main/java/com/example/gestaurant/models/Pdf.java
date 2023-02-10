package com.example.gestaurant.models;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.IOException;

/**
 * Simple Hello World example.
 */
public class Pdf {

    public static String DEST = "results/finance.pdf";

    public static void main(String path) throws IOException {
        if(path != null){
            DEST = path;
        }
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Pdf().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        Table tableTransactions = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        tableTransactions.addCell("Dépenses Totales : ");
        tableTransactions.addCell(String.valueOf(Service.getTotalCost() / 100) + "€");
        tableTransactions.addCell("Ventes Totales : ");
        tableTransactions.addCell(String.valueOf(Service.getTotalGain() / 100)+ "€");
        tableTransactions.addCell("Bénéfice Total : ");
        tableTransactions.addCell(String.valueOf(Service.getTotalGain() / 100 - Service.getTotalCost() / 100)+ "€");

        doc.add(tableTransactions);

        doc.close();
    }
}
