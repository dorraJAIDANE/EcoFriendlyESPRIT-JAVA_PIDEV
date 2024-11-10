/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * 
 * @author LENOVO
 */
public class CodepromoController implements Initializable {

    @FXML
    private TextField codepromo;
    @FXML
    private TextField porcentage;
    @FXML
    private TextField codeevent;
    @FXML
    private TextField codetransport;
    @FXML
    private TextField codeservice;
    @FXML
    private Button back;
    @FXML
    private Button add;
    private double x=0;
    private double y=0;
 MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    private PreparedStatement prepare ;
 private ResultSet result ;
  private Statement statement;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void add() throws SQLException, IOException{
        Alert alert;
     if (codepromo.getText().isEmpty() || porcentage.getText().isEmpty() || codeevent.getText().isEmpty()
            || codeservice.getText().isEmpty() || codetransport.getText().isEmpty()) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR Message ");
        alert.setHeaderText(null);
        alert.setContentText("All fields are necessary to be filled");
        alert.showAndWait();}
    else if (codepromo.getText().equals(codeevent.getText())) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR Message ");
        alert.setHeaderText(null);
        alert.setContentText("promo code does not match");
        alert.showAndWait();}
      String insertData = "INSERT INTO `codepromo`(`idcodepromo`, `pourcentage`, `idevent`, `idtransport`, `idservice`)" + "VALUES(?,?,?,?,?)";
    prepare = cnx.prepareStatement(insertData);

    prepare.setString(1, codepromo.getText());
    prepare.setString(2, porcentage.getText());
    prepare.setString(3, codeevent.getText());
    prepare.setString(4, codetransport.getText());
    prepare.setString(5, codeservice.getText());
    prepare.executeUpdate();
     alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION Message ");
                alert.setHeaderText(null);
                alert.setContentText("Registered promo code Successfully!");
                alert.showAndWait();
                 Parent root = FXMLLoader.load(getClass().getResource("BACKUSERS.fxml"));
    
    }
    @FXML
    private void retu(){ 
    try {     back.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("BACKUSERS.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
                 
                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                     });
                      root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
        });
                
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        
        
        stage.show();
    } catch(Exception e){
        e.printStackTrace();
    }
     
}}
