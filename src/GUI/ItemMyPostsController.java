/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Post;
import Services.PostService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class ItemMyPostsController implements Initializable {

    
    @FXML
    private Label labelprenom;
    @FXML
    private Label labelnom;
    @FXML
    private Label datePost;
    @FXML
    private Label labelpost;
    @FXML
    private Label labletitrepost;
    @FXML
    private ImageView imgisert;
    @FXML
    private Label labelnbrecomment;
    @FXML
    private Button buttonReadComments;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private Date datep;
    private Image image;
    public void setData(String nomUser, String prenomUser, String imagePath, String title, String description, int nbreComment, Date datep) {
        labelnom.setText(nomUser);
        labelprenom.setText(prenomUser);
        labletitrepost.setText(title);
        labelpost.setText(description);
        labelnbrecomment.setText(String.valueOf(getNumberOfCommentsForPost(idPost)) + " Comments");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date
        datePost.setText( sdf.format(datep));

        // Chargez l'image depuis le chemin de l'image
        //Image image = new Image("file:" + imagePath, 190, 100, false, true);
        //imguser.setImage(image); // Affichez l'image
        
        // Chargez l'image pour imginsert
        Image insertImage = new Image("file:" + imagePath, 174, 105, false, true);
        imgisert.setImage(insertImage);
    }
    private int idPost;
    
     public void setPostId(int postId) {
        this.idPost = postId;
    }
      
      public int getNumberOfCommentsForPost(int postId) {
    int numberOfComments = 0;
    try {
       
        String query = "SELECT COUNT(*) FROM commentaire WHERE `idPost`= ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, postId);

        // Exécuter la requête et récupérer le résultat
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            numberOfComments = resultSet.getInt(1);
        }

      

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return numberOfComments;
}

    @FXML
    private void readComments(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CommentsDetails.fxml"));
            Parent root = loader.load();

            CommentsDetailsController commentsDetailsController = loader.getController();
            commentsDetailsController.initData(idPost); // Passez l'ID du post au contrôleur des commentaires

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     public int getId() {
        return idPost;
     }
     
   
     
    private Post selectedPost;
    @FXML
    private void Edit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifyPost.fxml"));
            Parent root = loader.load();

            ModifyPostController modifyPostController = loader.getController();
            modifyPostController.EditPostController(selectedPost);
            modifyPostController.initData(idPost);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteComments(int Postid) {
    
    //public void deleteEvent(int idEvent) {
    String selectQuery = "SELECT * FROM commentaire WHERE `idPost`= ?";
    String deleteQuery = "DELETE FROM commentaire WHERE `idPost`= ?";
   
    try {
        // Vérifie si l'événement existe
        PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
        selectStatement.setInt(1, Postid);
        ResultSet resultSet = selectStatement.executeQuery();
       
       /* if (!resultSet.next()) {
            System.out.println("Post with ID " + Postid + " does not exist.");
            return;
        }*/

        // Supprime l'événement
        PreparedStatement deleteStatement = cnx.prepareStatement(deleteQuery);
        deleteStatement.setInt(1, Postid);
        int result = deleteStatement.executeUpdate();

        if (result > 0) {
            System.out.println("commentaire with IDPost " + Postid + " has been deleted successfully!");
        } else {
            System.out.println("Failed to delete post with ID " + Postid);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ItemMyPostsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public void deletePost(int Postid) {
    
    //public void deleteEvent(int idEvent) {
    String selectQuery = "SELECT * FROM post WHERE `idPost`= ?";
    String deleteQuery = "DELETE FROM post WHERE `idPost`= ?";
   
    try {
        // Vérifie si l'événement existe
        PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
        selectStatement.setInt(1, Postid);
        ResultSet resultSet = selectStatement.executeQuery();
       
        if (!resultSet.next()) {
            System.out.println("Post with ID " + Postid + " does not exist.");
            return;
        }

        // Supprime l'événement
        PreparedStatement deleteStatement = cnx.prepareStatement(deleteQuery);
        deleteStatement.setInt(1, Postid);
        int result = deleteStatement.executeUpdate();

        /*if (result > 0) {
            System.out.println("post with ID " + Postid + " has been deleted successfully!");
        } else {
            System.out.println("Failed to delete post with ID " + Postid);
        }*/
    } catch (SQLException ex) {
        Logger.getLogger(ItemMyPostsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    @FXML
    private void Delete(ActionEvent event) throws IOException {
        int postIdToDelete = idPost; // Récupérez l'ID de l'événement que vous souhaitez supprimer
        //System.out.println("idPost est"+postIdToDelete);
        boolean deletionSucceeded=false;
        if (postIdToDelete == -1) {
            // Affichez un message d'erreur ou effectuez une action pour indiquer qu'aucun événement n'est sélectionné
            return;
        }

        // Appelez la méthode de service pour supprimer l'événement
        //PostService postService = new PostService();
        deleteComments(postIdToDelete);
        deletePost(postIdToDelete);
       
        // Créez une boîte de dialogue pour afficher le résultat de la suppression
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Successfully Delete");
        alert.setHeaderText("Successfully Delete");

        alert.showAndWait();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MyPostsInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
   
//clear();
    }
    
}
