package com.antonativa.antonativa.settings.impresion;

import com.antonativa.antonativa.models.Etiqueta;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ImpresionInmediata {

    private final static Logger LOGGER = Logger.getLogger("settings.impresioninmediata.ImpresionInmediata");

    public static void imprimirEtiqueta(Etiqueta etiqueta) {
        ImpresionInmediata printer = new ImpresionInmediata();

        try {
            ByteArrayOutputStream documentoBytes = printer.crearDocumentoiText(etiqueta);
            printer.imprimir(documentoBytes);
        } catch (IOException | PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Error de impresion", "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }

    private void imprimir(ByteArrayOutputStream documentoBytes) throws IOException, PrinterException {

        ByteArrayInputStream bais = new ByteArrayInputStream(documentoBytes.toByteArray());

        PDDocument document = PDDocument.load(bais);

        PrintService myPrintService = this.findPrintService("SATO CG408"); //SATO CG408
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.setPageable(new PDFPageable(document));
        printerJob.setPrintService(myPrintService);

        printerJob.print();

    }

    private PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            System.out.println(printService.getName());

            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

    private ByteArrayOutputStream crearDocumentoiText(Etiqueta etiqueta) {

        ByteArrayOutputStream documentoBytes = new ByteArrayOutputStream();

        PdfWriter pdfWriter = new PdfWriter(documentoBytes);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);

        Document documento = new Document(pdfDoc);
        documento.setMargins(0F, 0F, 0F, 60F);

        documento.add(new Paragraph(etiqueta.toString()));

        documento.close();

        return documentoBytes;
    }

}
