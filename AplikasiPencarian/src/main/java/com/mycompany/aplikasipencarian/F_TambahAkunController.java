package com.mycompany.aplikasipencarian;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.aplikasipencarian.config.config;
import static com.mycompany.aplikasipencarian.modul.Modul.infoInformation;
import static com.mycompany.aplikasipencarian.modul.Modul.infoWarning;
import com.mycompany.aplikasipencarian.util.ConnectionUtil;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class F_TambahAkunController implements Initializable {

    @FXML
    private JFXTextField tfuser;
    @FXML
    private JFXPasswordField tfpass;
    @FXML
    private JFXButton btnMasuk;
    @FXML
    private JFXTextField npm;
    @FXML
    private JFXTextField nama;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField tlp;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextArea catatan;
    @FXML
    private JFXButton btnbatal;
    @FXML
    private JFXButton btnsimpan;
    @FXML
    private AnchorPane formtbhakn;

    Stage dialogStage = new Stage();
    Scene scene;
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public F_TambahAkunController() {
        connection = ConnectionUtil.connectdb();
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                infoWarning("Please enter correct Email and Password", null, "Failed");
            }else{
                infoInformation("Login Successfull",null,"Success" );
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
    private void actionSimpan(ActionEvent event) {
        try {
            String sql = "INSERT INTO tabel_user VALUES ('"+npm.getText()+"','"+nama.getText()+"','"+email.getText()+"','"+tlp.getText()+"','"+username.getText()+"','"+password.getText()+"','"+catatan.getText()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();            
            infoInformation("Data berhasil disimpan !", null, "Tambah Akun");           
        } catch (Exception e) {
            infoWarning("Data gagal disimpan !", null, "Tambah Akun");
        }
    }
    
}
