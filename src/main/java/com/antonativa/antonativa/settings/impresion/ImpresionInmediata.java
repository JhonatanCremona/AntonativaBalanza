package com.antonativa.antonativa.settings.impresion;

import com.antonativa.antonativa.models.Producto;
import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.layout.element.Image;
public class ImpresionInmediata {
    private final static Logger LOGGER = Logger.getLogger("settings.impresioninmediata.ImpresionInmediata");

    public static void imprimirEtiqueta(Producto producto) {
        ImpresionInmediata printer = new ImpresionInmediata();

        try {
            ByteArrayOutputStream documentoBytes = printer.crearDocumentoiText(producto);
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

        printerJob.print();

    }

    private ByteArrayOutputStream crearDocumentoiText(Producto producto) {

        ByteArrayOutputStream documentoBytes = new ByteArrayOutputStream();

        PdfWriter pdfWriter = new PdfWriter(documentoBytes);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);

        Document documento = new Document(pdfDoc);
        documento.setMargins(8F, 0F, 0F, 15F);

        documento.add(new Paragraph(producto.toString()).setFontSize(12F));
        documento.add(crearCodigoBarras(pdfDoc, producto));

        documento.close();

        return documentoBytes;
    }

    /**
     * Metodo para crear un codigo de barras en base a los datos del producto.
     *
     * @param pdfDoc Documento pdf que se va a utilizar para imprimir el codigo de barras.
     * @param producto Objeto que va a aportar los datos del codigo.
     * @return Una celda con el codigo de barras.
     */
    private Cell crearCodigoBarras(PdfDocument pdfDoc, Producto producto) {

        Barcode128 barcode = new Barcode128(pdfDoc);

        barcode.setCode(generarFormatoCodigo(producto));

        //Se determina el tipo de codigo de barras 128
        barcode.setCodeType(Barcode128.CODE128);

        barcode.setBarHeight(45f);

        PdfFormXObject barcodeObject = barcode.createFormXObject(null, null, pdfDoc);

        return new Cell().add(new Image(barcodeObject));

    }

    private String generarFormatoCodigo(Producto producto) {

        String peso = producto.getPesoNeto().substring(0, 5).trim();

        StringBuilder codigo = new StringBuilder();

            for (int i = 0; i < 10; i++) {

                if (i < producto.getNombre().length()) {
                    codigo.append(producto.getNombre().charAt(i));
                } else {
                    codigo.append(' ');
                }

            }

        for (int i = 0; i < 11; i++) {

            if (i < producto.getLote().length()) {
                codigo.append(producto.getLote().charAt(i));
            } else {
                codigo.append(' ');
            }

        }

        for (int i = 0; i < 5; i++) {

            if (i < peso.length()) {
                codigo.append(peso.charAt(i));
            } else {
                codigo.append(' ');
            }

        }

        codigo.append(producto.getUnidades());

        return codigo.toString();

    }

}
