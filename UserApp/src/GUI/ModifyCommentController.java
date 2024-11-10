/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import API.BadWords;
import static GUI.UserSession.getCurrentUser;
import Models.getData;
import Models.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class ModifyCommentController implements Initializable {

    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private Button buttonEdit;
    @FXML
    private TextField inputDescriptionComment;
    @FXML
    private Button buttonReturnComment;
    @FXML
    private Label labelStatus;
    @FXML
    private Button close;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private int idComment;
    public void initData(int idComment) {
        this.idComment = idComment; // Initialisez l'ID du post
    }
    private static user currentUser = getCurrentUser();
    int id = currentUser.getIduser();
    @FXML
    private void Edit(ActionEvent event) throws IOException {
        System.out.println("idComment update" + idComment);

      
        String newDescription = inputDescriptionComment.getText();
        
        try{
            Alert alert;
             if (BadWords.containsBadWords(newDescription) ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The description contains inappropriate words.");
                alert.showAndWait();
                return;
            } else if (newDescription.isEmpty() ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                return ;  
            }else{
                String sql = "UPDATE `commentaire` SET `Description`=?  WHERE `idComment`=?";
                PreparedStatement statement = cnx.prepareStatement(sql);
                statement.setString(1, newDescription);
                
                statement.setInt(2, idComment);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Modify Comment");
                    alert.showAndWait();

                    System.out.println("L'insertion a été réussie !");
                    
                    }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MyComments.fxml"));
                    Parent root = loader.load();
                    //System.out.println("id User est" + id);
                    MyCommentsController mycommentsController = loader.getController();
                    mycommentsController.initData(id); // Passez l'ID du post au contrôleur des commentaires

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }           

   }catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void Return(ActionEvent event) throws IOException {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MyComments.fxml"));
                    Parent root = loader.load();
                    //System.out.println("id User est" + id);
                    MyCommentsController mycommentsController = loader.getController();
                    mycommentsController.initData(id); // Passez l'ID du post au contrôleur des commentaires

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }
    
}
