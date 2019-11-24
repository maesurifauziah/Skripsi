package com.mycompany.aplikasipencarian.modul;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


public class Modul {

    private static void searchDocument(String field, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void convertPdf(String fileName) throws FileNotFoundException, IOException, SAXException, TikaException{
      BodyContentHandler handler = new BodyContentHandler(-1);
      Metadata metadata = new Metadata();
      FileInputStream inputstream = new FileInputStream(new File(fileName));
      ParseContext pcontext = new ParseContext();
      
      //parsing the document using PDF parser
      AutoDetectParser parser = new AutoDetectParser (); 
      parser.parse(inputstream, handler, metadata,pcontext);
      
      //getting the content of the document
      System.out.println("Contents of the PDF :" + handler.toString());   
    }
    
    public static void infoConfirmation(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    public static void infoInformation(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    public static void infoWarning(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    public static void infoError(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    public static void m_PencarianDok(String field, String value){
        if(field==null){
            infoWarning("Mohon Filter Terlebih Dahulu",null,"Pencarian");
        } else {  
            infoInformation("Yang anda cari adalah " +field+" "+ value,null,"Pencarian");
           // searchDocument(field, value);
        }  
    }
    
    public static void m_SimilarityDok(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Berikut Hasil Similarity");
        alert.setHeaderText(null);
        alert.setContentText("Selesai Mencari Similarity");
        alert.showAndWait();
    }
    
   
}
