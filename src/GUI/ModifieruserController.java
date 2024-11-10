/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.UserSession.getCurrentUser;
import Models.user;
import Services.userService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifieruserController implements Initializable {
    
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
     private double x=0;
    private double y=0;
    @FXML
    private TextField inputFirstname;
    @FXML
    private TextField inputLastname;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputLocation;
    @FXML
    private ComboBox<String> COMBOXclass;
    @FXML
    private Button buttoncreate;
    @FXML
    private Button buttonRETURN;
    @FXML
    private TextField wallet;
    @FXML
    private TextField role;

    
    private static user currentUser = getCurrentUser();
  @FXML
private void modifierUser(ActionEvent event) throws IOException {
    Alert alert;
    int id = currentUser.getIduser();
    String firstName = inputFirstname.getText();
    String lastName = inputLastname.getText();
    String email = inputEmail.getText();
    String location = inputLocation.getText();
    double walletText = Double.parseDouble(wallet.getText());
    String roleText = role.getText();
    String classSelected = (String) COMBOXclass.getValue();

    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || location.isEmpty()  || roleText.isEmpty() || classSelected.isEmpty() ) {
        
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("The title contains inappropriate words.");
        alert.showAndWait();
        return;
    }else  {
        
        // create user object
        user updatedUser = new user();
        updatedUser.setNomuser(firstName);
        updatedUser.setPrenomuser(lastName);
        updatedUser.setMailuser(email);
        updatedUser.setAdressuser(location);
        updatedUser.setWalletuser(walletText);
        updatedUser.setRoleuser(roleText);
        updatedUser.setClasseuser(classSelected);
        // get current user ID
        updatedUser.setIduser(id);
        
        // create an instance of userService
        userService userService = new userService();
        
        // update user
        userService.mettreAJouruser(updatedUser);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Succefully Modify User.");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/profile.fxml"));
        Parent root = loader.load();
        /*ProfileController profile = loader.getController();
        profile.loadUserProfileData();*/
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}

    @FXML
   
    
    private void Create(MouseEvent event) {
    }

    @FXML
    private void retu() {
        try {      buttonRETURN.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                 ObservableList<String> subjects = FXCollections.observableArrayList(
        "1ére année",
        "2éme année",
        "3éme année",
        "4éme année",
        "5éme année"
        );
        COMBOXclass.setItems(subjects);

          
        
      
    }

    @FXML
    private void initialize(ActionEvent event) {
    }
}



