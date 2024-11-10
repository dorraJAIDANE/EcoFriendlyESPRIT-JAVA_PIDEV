 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Post;
import Models.getData;
import Services.PostService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;
import API.BadWords;
import API.Notif;
import static GUI.UserSession.getCurrentUser;
import Models.user;


/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class AddPostController implements Initializable {

    @FXML
    private AnchorPane AddPost_form;
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
    private Button buttonAddPost;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private TextField inputTitlePost;
    @FXML
    private TextField inputDescriptionPost;
    private double x = 0;
    private double y = 0;

    /**
     * Initializes the controller class.
     */
    
    List<Post> posts;
    int index= -1;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    ResultSet rs= null;
    @FXML
    private Button close;
    @FXML
    private Button minus;
    @FXML
    private Button buttonReturnPost;
    @FXML
    private ImageView importimagepost;
    @FXML
    private Label labelStatus;
    @FXML
    private Button buttonimportPost;
    @FXML
    private ComboBox<String> Subjectcombobox;   
    @FXML
    private Button dasborbprofile;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       buttonAddPost.setOnAction((event) -> {
           try {
               this.checkField(event);
           } catch (IOException ex) {
               Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       ObservableList<String> subjects = FXCollections.observableArrayList(
        "Sale & Exchange",
        "Coding Problems",
        "Esprit Problems"
        );
        Subjectcombobox.setItems(subjects);
        
    }  
    
    

    @FXML
    private void switchForm(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    @FXML
    private void close() {
            System.exit(0);
    }
    @FXML
    public void minimize() {
        Stage stage = (Stage) AddPost_form.getScene().getWindow();
        stage.setIconified(true);
    }
    //private String subject;
    public String getSubject() {
        String selectedSubject = Subjectcombobox.getValue();
        return selectedSubject;
    }

    
    @FXML
    private void Return(ActionEvent event) throws IOException   {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
     /*public void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout_btn.getScene().getWindow().hide();
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
        }

    }*/
    private Image image;
    @FXML
    public void InsertImagePost() {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(AddPost_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 264, 174, false, true);
            importimagepost.setImage(image);
        }  

    }
    private static user currentUser = getCurrentUser();
    @FXML
     public boolean checkField(ActionEvent event) throws IOException{
                String inputTitlePostValue = inputTitlePost.getText();
                String inputDescriptionPostValue = inputDescriptionPost.getText();
                String subjectValue = Subjectcombobox.getValue();
                String nom = currentUser.getNomuser();
                String prenom  = currentUser.getPrenomuser();
                int id = currentUser.getIduser();
                int nbreCom = 0;
         try{
            Alert alert;
            if (BadWords.containsBadWords(inputTitlePostValue) || BadWords.containsBadWords(inputDescriptionPostValue)) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The title or description contains inappropriate words.");
                alert.showAndWait();
                return false;
            }else if (BadWords.containsBadWords(inputTitlePostValue) ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The title contains inappropriate words.");
                alert.showAndWait();
                return false;
             }else if (BadWords.containsBadWords(inputDescriptionPostValue) ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The description contains inappropriate words.");
                alert.showAndWait();
                return false;
            }else if (Subjectcombobox==null || inputTitlePost.getText().isEmpty() || inputDescriptionPost.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                return false;     
            }else{
                System.out.println("Add button clicked");
        
            
                


                String query = "INSERT INTO post (`dateCreationp`,`title`,`descriptionp`,`nbrecomment`,`image`,`subject`,`idUser`,`nomUser`,`prenomUser`) VALUES (NOW(),?,?,?,?,?,?,?,?)";
                PreparedStatement statement = cnx.prepareStatement(query);
                statement.setString(1, inputTitlePostValue);
                statement.setString(2, inputDescriptionPostValue);
                statement.setInt(3, nbreCom);
                
                
                // Récupérer le chemin de l'image
                String imagePath = getData.path;

                if (imagePath != null) {
                    statement.setString(4, imagePath);
                } else {
                    statement.setNull(4, Types.VARCHAR); // Si aucune image n'est sélectionnée
                }
                statement.setString(5, subjectValue);
                statement.setInt(6, id);
                statement.setString(7, nom);
                statement.setString(8, prenom);
                
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Add Post");
                    alert.showAndWait();

                    System.out.println("L'insertion a été réussie !");
                    
                    }
                Notif notif = new Notif();
                        notif.notifme(inputTitlePost.getText());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
              

            } 
        
         }catch (SQLException e) {
                e.printStackTrace();
            }
         return true;
    }

     
     private ForumInterfaceController forumInterfaceController; 

    // Ajoutez cette méthode pour initialiser le contrôleur
    public void setForumInterfaceController(ForumInterfaceController forumInterfaceController) {
        this.forumInterfaceController = forumInterfaceController;
    }
    private void profile(ActionEvent event) {
         try {      dasborbprofile.getScene().getWindow().hide();

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

    
}
