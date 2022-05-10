/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entites.Equipe;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import Service.EquipeService;


/**
 *
 * @author 21626
 */


public class ServicePdf {
    
       public void equipePDF() throws FileNotFoundException, DocumentException {

        EquipeService so = new EquipeService();
        String message = "                       La liste des Equipes \n\n";
      
        
        String file_name = "src/PDF/equipe.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<Equipe> equipe = so.afficher();
        PdfPTable table = new PdfPTable(3);

        PdfPCell cl = new PdfPCell(new Phrase("id"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("nom"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("prenom"));
        table.addCell(cl2);
  
        
        
        table.setHeaderRows(1);
        document.add(table);

        for (int i = 0; i < equipe.size(); i++) {
            table.addCell(""+ equipe.get(i).getId());
            table.addCell("" + equipe.get(i).getNom());
            table.addCell("" + equipe.get(i).getPrenom());
            

        }
        document.add(table);

        document.close();

    }
       
      
}