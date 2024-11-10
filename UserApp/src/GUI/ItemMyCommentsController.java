/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.UserSession.getCurrentUser;
import Models.Commentaire;
import Models.user;
import java.io.IOException;
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
import javafx.stage.Stage;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class ItemMyCommentsController implements Initializable {

    @FXML
    private Label labelNom;
    @FXML
    private Label labelprenom;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;
    @FXML
    private Label labeldescription;
    @FXML
    private Label labeldate;
     MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
private int idComment;    
     public void initialize(Commentaire commentaire){
        this.idComment = commentaire.getIdComment();
        labelNom.setText(commentaire.getNomUser());
        labelprenom.setText(commentaire.getPrenomUser());
        labeldescription.setText(commentaire.getDescription());
        labeldate.setText(commentaire.getDateCreation().toString());
    }

    @FXML
    private void Edit(ActionEvent event) {
        String description = labeldescription.getText();
        String date = labeldate.getText();
        int CommentIdToDelete = getCommentIdFromDatabase(description, date);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifyComment.fxml"));
            Parent root = loader.load();

            ModifyCommentController modifycommentController = loader.getController();
            //modifyPostController.EditPostController(selectedPost);
            modifycommentController.initData(CommentIdToDelete);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int getCommentIdFromDatabase(String description, String date) {
    String selectQuery = "SELECT `idComment` FROM commentaire WHERE `Description`= ? AND `DateCreation`= ?";
    
    try {
        PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
        selectStatement.setString(1, description);
        selectStatement.setString(2, date);
        
        ResultSet resultSet = selectStatement.executeQuery();
        
        if (resultSet.next()) {
            return resultSet.getInt("idComment");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ItemMyCommentsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return -1; // Retourne -1 si l'ID n'a pas été trouvé
}

     
    
     /*public void setPostId(int Id) {
        this.idComment = Id;
    }
       public int getId() {
        return idComment;
     }*/
       private Commentaire selectedComment;
     public void deleteComments(int Commentid) {
    
    //public void deleteEvent(int idEvent) {
    String selectQuery = "SELECT * FROM commentaire WHERE `idComment`= ?";
    String deleteQuery = "DELETE FROM commentaire WHERE `idComment`= ?";
   
    try {
        // Vérifie si l'événement existe
        PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
        selectStatement.setInt(1, Commentid);
        ResultSet resultSet = selectStatement.executeQuery();
       
       /* if (!resultSet.next()) {
            System.out.println("Post with ID " + Postid + " does not exist.");
            return;
        }*/

        // Supprime l'événement
        PreparedStatement deleteStatement = cnx.prepareStatement(deleteQuery);
        deleteStatement.setInt(1, Commentid);
        int result = deleteStatement.executeUpdate();

        if (result > 0) {
            System.out.println("commentaire with IDPost " + Commentid + " has been deleted successfully!");
        } else {
            System.out.println("Failed to delete post with ID " + Commentid);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ItemMyPostsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    private static user currentUser = getCurrentUser();
    int id = currentUser.getIduser();
private Commentaire commentaire;
    @FXML
    private void Delete(ActionEvent event) throws IOException {
        String description = labeldescription.getText();
        String date = labeldate.getText();
        int CommentIdToDelete = getCommentIdFromDatabase(description, date);
        //System.out.println("idPost est"+postIdToDelete);
        boolean deletionSucceeded=false;
        if (CommentIdToDelete == -1) {
            // Affichez un message d'erreur ou effectuez une action pour indiquer qu'aucun événement n'est sélectionné
            return;
        }
        // Appelez la méthode de service pour supprimer l'événement
        //PostService postService = new PostService();
        deleteComments(CommentIdToDelete);
        //deletePost(postIdToDelete);
        // Créez une boîte de dialogue pour afficher le résultat de la suppression
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successfully Delete");
        alert.setHeaderText("Successfully Delete");
        alert.showAndWait();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MyComments.fxml"));
            Parent root = loader.load();
            System.out.println("id User est" + id);
            MyCommentsController mycommentsController = loader.getController();
            mycommentsController.initData(id); // Passez l'ID du post au contrôleur des commentaires
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
