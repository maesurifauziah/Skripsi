package com.mycompany.aplikasipencarian;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static com.mycompany.aplikasipencarian.modul.Modul.infoConfirmation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import java.io.BufferedInputStream;
import java.net.URLConnection;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

public class F_PencarianDokumenController implements Initializable {

    @FXML
    private JFXButton btnhome;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btnsimilarity;
    @FXML
    private JFXTextField tfpencarian;
    @FXML
    private JFXComboBox<String> cbfilter;
    @FXML
    private AnchorPane f_pencarianDokumen;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnCari;
    @FXML
    private JFXListView<String> lvhasil;
    @FXML
    private JFXTextField dtiiddoc;
    @FXML
    private JFXTextField dtauthor;
    @FXML
    private JFXTextField dtyear;
    @FXML
    private JFXTextField dtnpm;
    @FXML
    private JFXTextArea dttitle;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbfilter.getItems().add( "Keywords");  
        cbfilter.getItems().add( "Title");
        cbfilter.getItems().add( "Author"); 
        cbfilter.getItems().add( "NPM"); 
        cbfilter.getItems().add( "Year");  
        cbfilter.getItems().add( "Status"); 
        cbfilter.getItems().add( "Tags");
        cbfilter.getItems().add( "IDDoc");
        cbfilter.getItems().add( "Mounth");
        cbfilter.getItems().add( "Publisher");
    }    

    @FXML
    private void actionHome(ActionEvent event) {
        f_pencarianDokumen.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_HomePencarian.fxml"));
                f_pencarianDokumen.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_PencarianDokumenController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }

    @FXML
    private void actionPeDok(ActionEvent event) {
        f_pencarianDokumen.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_PencarianDokumen.fxml"));
                f_pencarianDokumen.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_PencarianDokumenController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }

    @FXML
    private void actionSimDok(ActionEvent event) {
        f_pencarianDokumen.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_PencarianSimilarityDokumen.fxml"));
                f_pencarianDokumen.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_PencarianDokumenController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }

    @FXML
    private void actionLogout(ActionEvent event) {
        infoConfirmation("Yakin ingin Logout?",null,"Logout" );
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_Login.fxml"));
                f_pencarianDokumen.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_HomePencarianController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }  
    
    @FXML
    private void actionCari(ActionEvent event) {
        String field = cbfilter.getValue();        
        String value = tfpencarian.getText();
        
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
        double score;
       
        JSONObject json = null;
        
        if(field==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pencarian");
            alert.setHeaderText(null);
            alert.setContentText("Mohon Filter Terlebih Dahulu!");
            alert.showAndWait();
        } else {  
            //MENCARI DATA DARI ELASTICSEARCH
            try {
                // get a JSON object from ElasticSearch
                //url = "localhost:9200/skrip/_search?pretty&q=title:MEMBANGUN APLIKASI PENJUALAN KUE BERBASIS WEB STUDI KASUS DI AMIH CAKE AND COOKIES%20OR%20year:2017";                                  
                
                url = "http://localhost:9200/skripsii/_search?q="+field+":"+value;
             
                
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
                
                int jmlHits = hitsArray.length();
                
                if(jmlHits==0){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Pencarian");
                   alert.setHeaderText(null);
                   alert.setContentText("Data yang anda cari tidak cocok!!!");
                   alert.showAndWait();
                } else {  
                    lvhasil.getItems().clear();
                    dtiiddoc.clear();
                    dtauthor.clear();                        
                    dtyear.clear();
                    dtnpm.clear();
                    dttitle.clear();
                    
                    String[] objTitle = new String[jmlHits];                      
                    int[] objIddoc = new int[jmlHits];                    
                    int[] objYear  = new int[jmlHits];                     
                    String[]objAuthor = new String[jmlHits];                        
                    String[] objNpm = new String[jmlHits];

                    for (int i=0; i<jmlHits; i++) {
                        JSONObject h = hitsArray.getJSONObject(i);
                        source = h.getJSONObject("_source");                        
                        score = h.getDouble("_score");  
                        //objTitle [0] = "";
                        
                        objTitle[i] = (source.getString("Title"));                        
                        objIddoc[i] = (source.getInt("IDDoc"));                        
                        objYear[i] = (source.getInt("Year"));                        
                        objAuthor[i] = (source.getString("Author"));                        
                        objNpm[i] = (source.getString("NPM"));  
                        
                        lvhasil.getItems().addAll(objTitle[i]);                          
                    }
                    
                    lvhasil.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
                        int index = lvhasil.getSelectionModel().getSelectedIndex();
                        String dtiiddocs = String.valueOf(objIddoc[index]);
                        String dtyears = String.valueOf(objYear[index]); 
                        dtiiddoc.setText(dtiiddocs);
                        dtauthor.setText(objAuthor[index]); 
                        dtyear.setText(dtyears);
                        dtnpm.setText(objNpm[index]);
                        dttitle.setText(new_val);
                    });
                }                
            } catch(Exception e) {              
            }
        }        
    } 
}
