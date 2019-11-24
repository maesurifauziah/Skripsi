package com.mycompany.aplikasipencarian;

import com.mycompany.aplikasipencarian.util.ConnectionUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static com.mycompany.aplikasipencarian.modul.Modul.infoConfirmation;
import static com.mycompany.aplikasipencarian.modul.Modul.infoError;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class F_LoginController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane formlogin;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField tfuser;
    @FXML
    private JFXPasswordField tfpass;
    @FXML
    private JFXButton btnMasuk;
    @FXML
    private JFXButton btntbakn;
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public F_LoginController() {
        connection = ConnectionUtil.connectdb();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }   
    
    @FXML
    private void actionMsk(ActionEvent event) {
        String username = tfuser.getText().toString();
        String password = tfpass.getText().toString();    
        String sql = "SELECT * FROM tabel_user WHERE username = ? and password = ?";        
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                infoError("Please enter correct Email and Password", null, "Failed");
            }else{
                infoConfirmation("Login Successfull",null,"Success" );
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/F_HomePencarian.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
   
    @FXML
    private void actionTbAkn(ActionEvent event) {
        formlogin.getChildren().clear();
        Node [] nodes = new  Node[15];
        for(int i = 0; i<10; i++){
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("/fxml/F_TambahAkun.fxml"));
                formlogin.getChildren().add(nodes[i]);   
            } catch (IOException ex) {
                Logger.getLogger(F_LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }    
}
