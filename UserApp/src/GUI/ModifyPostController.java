/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import API.BadWords;
import static GUI.UserSession.getCurrentUser;
import Models.Post;
import Models.getData;
import Models.user;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class ModifyPostController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button minus;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private TextField inputTitlePost;
    @FXML
    private TextField inputDescriptionPost;
    @FXML
    private Button buttonReturnPost;
    @FXML
    private ImageView importimagepost;
    @FXML
    private Button buttonimportPost;
    @FXML
    private Label labelStatus;
    @FXML
    private ComboBox<String> Subjectcombobox;
    @FXML
    private AnchorPane ModifyPost_form;
    @FXML
    private Button buttonUpdate;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    /**
     * Initializes the controller class.
     */
    private Post postToEdit;

    public void EditPostController(Post post) {
        this.postToEdit = post;
        if (postToEdit != null) {
            Subjectcombobox.setValue(postToEdit.getSubject());
            inputTitlePost.setText(postToEdit.getTitle());
            inputDescriptionPost.setText(postToEdit.getDescriptionp());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> subjects = FXCollections.observableArrayList(
        "Sale & Exchange",
        "Coding Problems",
        "Esprit Problems"
        );
         EditPostController(postToEdit);
        
        /*modifbutton.setVisible(true);
        docToUpdate=CardDocController.getDoctomodif();
        System.err.println(docToUpdate.toString());
        anomdoc_d.setText(docToUpdate.getDocumentName());
        atype_d.setValue(docToUpdate.getDocumentType());
        aniveau_d.setValue(niveauService.getNiveauById(docToUpdate.getIdNiveau().getIdNiveau()));
        asemestre_d.setValue(semestreService.getSemestreById(docToUpdate.getIdSemestre().getIdSemestre()));
        atopic_d.setValue(topicService.getTopicById(docToUpdate.getIdTopic().getIdTopic()));
        Image image = new Image(docToUpdate.getDocumentImage());
        adocImage_d.setImage(image);
        if (postToEdit != null) {
        Subjectcombobox.setValue(postToEdit.getSubject());
        inputTitlePost.setText(postToEdit.getTitle());
        inputDescriptionPost.setText(postToEdit.getDescriptionp());
        // ... (faites de même pour les autres champs)
    }*/
    } 
    
    public String getSubject() {
        String selectedSubject = Subjectcombobox.getValue();
        return selectedSubject;
    }
    
    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ModifyPost_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    private void Return(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MyPostsInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    private Image image;
    @FXML
    private void InsertImagePost(ActionEvent event) {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(ModifyPost_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 264, 174, false, true);
            importimagepost.setImage(image);
        }  
    }
    private int idPost;
    public void setId(int postId) {
        this.idPost = postId;
    }
    
    public void initData(int idPost) {
        this.idPost = idPost; // Initialisez l'ID du post
    }
    
    private static user currentUser = getCurrentUser();
    
    @FXML
    private void Update(ActionEvent event) throws IOException {
        System.out.println("idPost update" + idPost);

        String newSubject = Subjectcombobox.getValue();
        String newTitle = inputTitlePost.getText();;
        String newDescription = inputDescriptionPost.getText();
        String newImagePath = getData.path;
        try{
            Alert alert;
            if (BadWords.containsBadWords(newTitle) ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The title contains inappropriate words.");
                alert.showAndWait();
                return;
             }else if (BadWords.containsBadWords(newDescription) ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The description contains inappropriate words.");
                alert.showAndWait();
                return;
            } else if (newSubject.isEmpty() || newTitle.isEmpty() || newDescription.isEmpty() ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                return ;  
            }else{
                String sql = "UPDATE `post` SET `title`=?,`descriptionp`=?,`image`=?,`subject`=?   WHERE `idPost`=?";
                PreparedStatement statement = cnx.prepareStatement(sql);
                statement.setString(1, newTitle);
                statement.setString(2, newDescription);
                if (newImagePath != null) {
                    statement.setString(3, newImagePath);
                } else {
                    statement.setNull(3, Types.VARCHAR); // Si aucune image n'est sélectionnée
                }
                statement.setString(4, newSubject);
                statement.setInt(5, idPost);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Modify Post");
                    alert.showAndWait();

                    System.out.println("L'insertion a été réussie !");
                    
                    }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MyPostsInterface.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            }           

   }catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
}
