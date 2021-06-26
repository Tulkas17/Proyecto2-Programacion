/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author oscar
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.xdevapi.Table;
import dao.FacturaDao;
import dao.FacturaDaoImpl;
import dao.TiqueteDao;
import dao.TiqueteDaoImpl;
import java.awt.Color;
import java.io.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.Cell;
import static javafx.scene.text.Font.font;
import javax.swing.text.StyleConstants.ColorConstants;

/**
 *
 * @author oscar
 */
public class GenerarPDF {

    private static GenerarPDF theInstance;

    public static GenerarPDF instance() {
        if (theInstance == null) {
            theInstance = new GenerarPDF();
        }
        return theInstance;
    }

    public static void createPdf(String seq_factura) throws IOException, SQLException, DocumentException {

        FacturaDao facDao = new FacturaDaoImpl();
        TiqueteDao tiqDao = new TiqueteDaoImpl();

        Factura factura = facDao.findById(Integer.parseInt(seq_factura));
        List<Tiquete> tiquetes = tiqDao.findAll();
        Iterator<Tiquete> iter = tiquetes.iterator();
        int contador = 0;
        Tiquete tiquete = null;

        FileOutputStream archivo = new FileOutputStream("G:\\Users\\oscar\\Desktop\\Proyecto2-Programacion\\Cinema\\facturas\\" + seq_factura + ".pdf");
        Document document = new Document();
        PdfWriter.getInstance(document, archivo);
        document.open();
        java.text.DecimalFormat df = new java.text.DecimalFormat("####");

        while (iter.hasNext()) {
            tiquete = iter.next();
            if (tiquete.getFactura_seq() == Integer.parseInt(seq_factura)) {
                contador++;
            }
        }
        tiquete = null;
        iter = tiquetes.iterator();

        document.add(new Paragraph(""));
        document.add(new Paragraph("----------------------------------------------"));
        document.add(new Paragraph("Factura: #" + seq_factura));
        document.add(new Paragraph("Fecha: " + factura.getFecha()));
        document.add(new Paragraph("Cliente id: " + factura.getCliente().getId_cliente()));
        document.add(new Paragraph("Tarjeta de pago: #" + factura.getTarjeta_pago()));
        document.add(new Paragraph("Total pagado: " + contador * 3500));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(new Paragraph("(**separar los tiequetes por las marcas**)"));

        while (iter.hasNext()) {
            tiquete = iter.next();
            if (tiquete.getFactura_seq() == Integer.parseInt(seq_factura)) {
                document.add(new Paragraph("----------------------------------------------"));
                document.add(new Paragraph("Codigo tiquete: " + tiquete.getId_tiquete()));
                document.add(new Paragraph("Id del cinema: " + tiquete.getAsiento_funcion_sala_cinema()));
                document.add(new Paragraph("Numero de sala: " + tiquete.getAsiento_funcion_sala_numero()));
                document.add(new Paragraph("Fecha de la funcion: " + tiquete.getAsiento_funcion_fecha()));
                document.add(new Paragraph("Fila: #" + tiquete.getAsiento_funcion_fila()));
                document.add(new Paragraph("Numero de asiento: #" + tiquete.getAsiento_funcion_posicion()));
                document.add(new Paragraph("Valor del tiquete: " + tiquete.getMonto()));
            }
        }

        document.add(new Paragraph("----------------------------------------------"));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(new Paragraph("FIN DE LA FACTURA"));
        document.add(new Paragraph("DISFRUTE DE LA FUNCION"));

        document.add(new Paragraph(""));

        document.close();
    }
}
