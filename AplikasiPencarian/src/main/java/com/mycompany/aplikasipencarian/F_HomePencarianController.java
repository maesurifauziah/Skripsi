package com.mycompany.aplikasipencarian;

import com.jfoenix.controls.JFXButton;
import static com.mycompany.aplikasipencarian.modul.Modul.infoConfirmation;
import static com.mycompany.aplikasipencarian.modul.Modul.infoWarning;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class F_HomePencarianController implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btnsimilarity;
    @FXML
    private AnchorPane f_homepencarian;
    @FXML
    private JFXButton btnLogout;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionPeDok(ActionEvent event) {
        f_homepencarian.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_PencarianDokumen.fxml"));
                f_homepencarian.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_HomePencarianController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }

    @FXML
    private void actionSimDok(ActionEvent event) {
        f_homepencarian.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_PencarianSimilarityDokumen.fxml"));
                f_homepencarian.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_HomePencarianController.class.getName()).log(Level.SEVERE, null, ex);
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
                f_homepencarian.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_HomePencarianController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }       
    }
}
