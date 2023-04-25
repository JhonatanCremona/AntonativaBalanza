package com.antonativa.antonativa.settings.impresion;

import com.antonativa.antonativa.controllers.SettingsController;
import com.antonativa.antonativa.models.Etiqueta;
import com.antonativa.antonativa.models.Settings;
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
    Settings impresoraSettings = new Settings();

    public SettingsController settingsController = new SettingsController();

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

        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.setPageable(new PDFPageable(document));

        printerJob.setPrintService(PrintServiceLookup.lookupDefaultPrintService());

        settingsController.setSettings(impresoraSettings);

        printerJob.print();

    }

    private PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {

            System.out.println(printService.getName());

            if (printService.getName().trim().equals(printerName)) {
                impresoraSettings.setEstadoImpresora(true);
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
        documento.setMargins(15F, 0F, 0F, 25F);

        documento.add(new Paragraph(etiqueta.toString()).setFontSize(15F));

        documento.close();

        return documentoBytes;
    }

}
