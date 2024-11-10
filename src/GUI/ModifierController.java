/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.codePromo;
import Models.Event;
import Models.Service;
import Models.transport;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierController implements Initializable {
 MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    @FXML
    private TextField codepromo;
    @FXML
    private TextField porcentage;
    @FXML
    private TextField codeevent;
    @FXML
    private TextField codetransport;
    @FXML
    private TextField codeService;
    @FXML
    private Button back;
    @FXML
    private Button modifier;
  private double x=0;
    private double y=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retu() {{try {      back.getScene().getWindow().hide();

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
    }
    }

    @FXML
    private void modifier(ActionEvent event) {

 
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    try {
        Statement stmt = cnx.createStatement();

        
        String sql = "UPDATE codepromo SET " +
             "pourcentage = ?, " +
             "idevent = ?, " +
             "idtransport = ?, " +
             "idService = ? " +
             "WHERE idcodepromo = ?";
     PreparedStatement pstmt = cnx.prepareStatement(sql);
            float pourcentageValue = Float.parseFloat(porcentage.getText());
        int idEvent = Integer.parseInt(codeevent.getText());
            int idTransport = Integer.parseInt(codetransport.getText());
            int idService = Integer.parseInt(codeService.getText());
int rowsAffected = pstmt.executeUpdate();
System.out.println(rowsAffected + " lignes mises à jour");

pstmt.close();
        
    

        
        stmt.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}}

 








    
    

