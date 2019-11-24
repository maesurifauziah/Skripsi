package com.mycompany.aplikasipencarian;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static com.mycompany.aplikasipencarian.modul.Modul.infoConfirmation;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

public class F_PencarianSimilarityDokumenController implements Initializable {

    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btnsimilarity;
    private JFXListView<String> listview;
    @FXML
    private JFXButton btnbrowser;
    @FXML
    private AnchorPane f_similarityDokumen;
    @FXML
    private JFXButton btnceksimilarity;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXTextField pathview;
    @FXML
    private JFXTextArea tfTitle;
    @FXML
    private JFXTextField tfAuthor;
    @FXML
    private JFXTextField tfNpm;
    @FXML
    private JFXTextArea tahasil;
    @FXML
    private JFXTextField tfPublisher;
    @FXML
    private JFXListView<String> lvscore;
    @FXML
    private JFXTextArea hasilkonver;
    @FXML
    private JFXListView<String> lvtitle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionHome(ActionEvent event) {
        f_similarityDokumen.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_HomePencarian.fxml"));
                f_similarityDokumen.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_PencarianSimilarityDokumenController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }

    @FXML
    private void actionPeDok(ActionEvent event) {
        f_similarityDokumen.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_PencarianDokumen.fxml"));
                f_similarityDokumen.getChildren().add(nodes[i]);
            } catch (IOException ex) {
                Logger.getLogger(F_PencarianSimilarityDokumenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void actionSimDok(ActionEvent event) {
        f_similarityDokumen.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_PencarianSimilarityDokumen.fxml"));
                f_similarityDokumen.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_PencarianSimilarityDokumenController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }

    @FXML
    private void actionBrowse(ActionEvent event) throws IOException, FileNotFoundException, SAXException, TikaException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);
      
        String dir = selectedFile.getAbsolutePath();
        pathview.setText(dir);
        
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(dir));
        ParseContext pcontext = new ParseContext();
      
        //parsing the document using PDF parser
        AutoDetectParser parser = new AutoDetectParser (); 
        parser.parse(inputstream, handler, metadata,pcontext);
      
        //getting the content of the document
        tahasil.setText(handler.toString());       
       
        String full = tahasil.getText().replaceAll("\n", "").replaceAll("\r", "");
        hasilkonver.setText(full); 
    }

    @FXML
    private void actionCekSimilarity(ActionEvent event) {
        String ttl = tfTitle.getText();
        String title = ttl.replaceAll(" ", "%20");
        
        String autr = tfAuthor.getText();
        String author = autr.replaceAll(" ", "%20");
        
        String nim = tfNpm.getText();
        String npm = nim.replaceAll(" ", "%20");
        
        String publish = tfPublisher.getText();
        String publisher = publish.replaceAll(" ", "%20");
        
        String fulltxt = hasilkonver.getText();    
        String fulltext = fulltxt.replaceAll(" ", "%20");
        
        // The input stream from the JSON response
        BufferedInputStream buffer = null;

        // URL objects
        String url = "";
        URL urlObject = null;
        URLConnection con = null;
        String response = "";

        // JSON objects
        JSONArray hitsArray = null;
        JSONObject hits = null;
        JSONObject source = null;
        JSONObject json = null;

        try {
            // get a JSON object from ElasticSearch
            url = "http://localhost:9200/skripsii/_doc/_search?q=Title:"+title+"%20OR%20Author:"+author+"%20OR%20NPM:"+npm+"%20OR%20Publisher:"+publisher+"%20OR%20Fulltext:"+fulltext;
         
            // configure the URL request
            urlObject = new URL(url);
            con = urlObject.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            buffer = new BufferedInputStream(con.getInputStream());

            while (buffer.available()>0) {
                response += (char)buffer.read();
            }

            buffer.close();

            // parse the JSON response 	
            json = new JSONObject(response);
            hits = json.getJSONObject("hits");
            hitsArray = hits.getJSONArray("hits");
            
            for (int i=0; i<hitsArray.length(); i++) {
                JSONObject h = hitsArray.getJSONObject(i);
                source = h.getJSONObject("_source"); 
                double score = h.getDouble("_score");  
                String objTitle = (source.getString("Title")); 
                
                String sscore = String.valueOf(score); 
                
                lvscore.getItems().addAll(sscore); 
                lvtitle.getItems().addAll(objTitle);
            }
        } catch(Exception e) {
          // handle the exception
        }
    } 
    
    @FXML
    private void actionLogout(ActionEvent event) {
        infoConfirmation("Yakin ingin Logout?",null,"Logout" );
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_Login.fxml"));
                f_similarityDokumen.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_HomePencarianController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }     
    }
}

