/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.user;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
public class BACKUSERSController implements Initializable {
private double x=0;
    private double y=0;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Label username;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button biblio_btn;
    @FXML
    private Button event_btn;
    @FXML
    private Button logout_btn2;
    @FXML
    private FontAwesomeIcon buttonlogou;
    @FXML
    private Button services_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button BLOCK;
    @FXML
    private ListView<user> listviewusers;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    private PreparedStatement prepare ;
    private ResultSet result ;
    @FXML
    private Button codepromo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
    }    

    @FXML
    private void switchForm(ActionEvent event) {
    }
    public void initData() {
        //this.postId = postId;

        List<user> usersFromDB = getUserFromDatabase();
        ObservableList<user> users = FXCollections.observableArrayList(usersFromDB);
        listviewusers.setItems(users);
        
        
        listviewusers.setCellFactory(param -> new ListCell<user>() {
            @Override
            protected void updateItem(user  item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/itemlistusers.fxml"));
                        AnchorPane pane = loader.load();
                        ItemlistusersController controller = loader.getController();
                        controller.initialize(item);
                        setGraphic(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(BACKUSERSController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
 public List<user> getUserFromDatabase() {
        List<user> users = new ArrayList<> ();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM user2 ";
           
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
               
                user us = new user();
                String nom = rs.getString("nomuser");
                String prenom = rs.getString("prenomuser");
                String email = rs.getString("mailuser");
                String classeuser = rs.getString("classeuser");
                String address = rs.getString("adressuser");
                
           
                us.setPrenomuser(prenom);
                us.setNomuser(nom);
                us.setMailuser(email);
                us.setClasseuser(classeuser);
                us.setAdressuser(address);
                
                users.add(us);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @FXML
    private void logout() {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
         try {
            if (option.get().equals(ButtonType.OK)) { 

                logout_btn2.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/AuthentificationInterface.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }}

    @FXML
    private void promo() { try {     codepromo.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("codepromo.fxml"));
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
    private void blockUser() {
    user selectedUser = listviewusers.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        String sql = "UPDATE user2 SET isBlocked = ? WHERE mailuser = ?";

        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, selectedUser.getMailuser()); 
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("User blocked successfully!");
                alert.showAndWait();
            } else {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to block user.");
                alert.showAndWait();
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    } else {
        // Show error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select a user to block.");
        alert.showAndWait();
    }
}
    }
    

