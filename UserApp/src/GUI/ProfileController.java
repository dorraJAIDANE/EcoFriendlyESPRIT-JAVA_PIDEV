
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.user;
import Services.userService;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */

public class ProfileController implements Initializable {

    @FXML
    private Label labelFistname;
    @FXML
    private Label labellastname;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelLocation;
    @FXML
    private Label labelClass;
 private double x=0;
    private double y=0;
      private PreparedStatement prepare ;
 private ResultSet result ;
  private Statement statement;
    @FXML
    private Button back;
    @FXML
    private Button editt;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUserProfileData();
    }  

 private void loadUserProfileData() {
    
    user user = UserSession.getCurrentUser();

    if (user != null) {
        labelFistname.setText(user.getNomuser());
        labellastname.setText(user.getPrenomuser());
        labelEmail.setText(user.getMailuser());
        labelLocation.setText(user.getAdressuser());
        labelClass.setText(user.getClasseuser());
    } else {
        // Gérez le cas où l'utilisateur n'est pas trouvé
        labelFistname.setText("N/A");
        labellastname.setText("N/A");
        labelEmail.setText("N/A");
        labelLocation.setText("N/A");
        labelClass.setText("N/A");
    }
}

    @FXML
    private void retu() {try {      back.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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

    @FXML
    private void edit(ActionEvent event) {
    }
}

