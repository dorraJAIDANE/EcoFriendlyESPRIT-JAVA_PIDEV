/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import Models.Post;
import GUI.ItemPhotoController;
import API.BadWords;
import static GUI.UserSession.getCurrentUser;
import Models.user;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.openhft.hashing.LongHashFunction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class AddCommentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane AddComment_form;
    @FXML
    private Button buttonAddComment;
    @FXML
    private TextField inputDescriptionComment;
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
    private Button logout_btn;
    @FXML
    private Button services_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private Button close;
    @FXML
    private Button minus;
    @FXML
    private Label labelStatus;
    
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    @FXML
    private Button dasborbprofile;
    @FXML
    private Button buttonReturnComment;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonAddComment.setOnAction((event) -> {
           try {
               this.Add(event);
           } catch (IOException ex) {
               Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
        user user = UserSession.getCurrentUser();
         username.setText(user.getPrenomuser());
    }  
    private double x = 0;
    private double y = 0;
    @FXML
    private void Return(ActionEvent event) throws IOException   {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root)); 
    }
    
    
    private int idPost;
     public void initData(int idPost) {
        this.idPost = idPost; // Initialisez l'ID du post
    }
     
     
    @FXML
    private void close() {
            System.exit(0);
    }


    @FXML
    private void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
         try {
            if (option.get().equals(ButtonType.OK)) {

                logout_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/AuthentificationInterface.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                /*root.setOnMousePressed((MouseEvent event) -> {
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
                });*/

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static user currentUser = getCurrentUser();
    @FXML
    private boolean Add(ActionEvent event) throws IOException {
        
            Alert alert;
            if (BadWords.containsBadWords(inputDescriptionComment.getText()) ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The description contains inappropriate words.");
                alert.showAndWait();
                return false;
            }else if (inputDescriptionComment.getText().isEmpty() ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                return false;     
            }else{
                System.out.println("Add button clicked");
                try{
            
                String inputDescriptionCommentValue = inputDescriptionComment.getText();
                String nom = currentUser.getNomuser();
                String prenom  = currentUser.getPrenomuser();
                int id = currentUser.getIduser();


                String query = "INSERT INTO commentaire (`DateCreation`,`Description`, `idPost`,`idUser`,`nomUser`,`prenomUser`) VALUES (NOW(),?,?,?,?,?)";
                PreparedStatement statement = cnx.prepareStatement(query);
                statement.setString(1, inputDescriptionCommentValue);
                statement.setInt(2, idPost);
                statement.setInt(3, id);
                statement.setString(4, nom);
                statement.setString(5, prenom);
                

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Add Comment");
                    alert.showAndWait();

                    System.out.println("L'insertion a été réussie !");
                }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
                statement.close();
        

        return false;

            
        
         }catch (SQLException e) {
                e.printStackTrace();
            }}
         return true;
    
    }
   


    @FXML
    private void minimize() {
        Stage stage = (Stage) AddComment_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void loadEventDetails(int idPost) {
    try {
        String query = "SELECT * FROM commentaire WHERE idPost = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, idPost);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            String description = rs.getString("Description");
            inputDescriptionComment.setText(description);
        }

        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   
    // private int idPost;
    public void setPostIdd(int postId) {
        this.idPost = postId;
    }
    @FXML
    private void profile(ActionEvent event) {
         try { dasborbprofile.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
                 
                    /*root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                     });
                      root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
        });*/
                
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        
        
        stage.show();
    } catch(Exception e){
        e.printStackTrace();
    }
    }

    private void buttonDashbord3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXMLDocument.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonTransport(ActionEvent event) {
         /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mainform.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));*/
    }

    @FXML
    private void buttonBiblio(ActionEvent event) {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mainform.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));*/
        
    }

    @FXML
    private void buttonEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI_Events/FXML.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonServices(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mainform.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonForum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonDashbord(ActionEvent event) {
    }

	
	
}

    
    

