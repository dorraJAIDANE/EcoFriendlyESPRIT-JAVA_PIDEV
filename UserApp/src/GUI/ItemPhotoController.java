/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Commentaire;
import GUI.ForumInterfaceController;
import Services.PostService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class ItemPhotoController implements Initializable {

   
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
    private Button buttonAddComment;
    @FXML
    private Button buttonReadComments;
    
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
    }    

    @FXML
    private void Add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AddComment.fxml"));
        Parent root = loader.load();
        AddCommentController addCommentController = loader.getController();
        addCommentController.initData(idPost); // Passez l'ID du post
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    /*public int getPostIdFromLabelDescription(String description) {
    int postId = 0;
    String sql = "SELECT `idPost` FROM `Post` WHERE `descriptionp`=?";
    
    try {
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, description);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            postId = rs.getInt("idPost");
        }


    } catch (SQLException ex) {
        Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("ID du post: " + postId);
    return postId;
}*/
 


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
        /*Image image = new Image("file:" + imagePath, 190, 100, false, true);
        imguser.setImage(image);*/ // Affichez l'image
        
        // Chargez l'image pour imginsert
        Image insertImage = new Image("file:" + imagePath, 174, 105, false, true);
        imgisert.setImage(insertImage);
    }
    private int idPost;

// Méthode pour initialiser l'ID de l'événement
    public void setPostId(int postId) {
        this.idPost = postId;
    }

// Méthode pour obtenir l'ID de l'événement
    public Integer getIdPost() {
        return idPost;
    }


    @FXML
    private void showDetails(ActionEvent event) {
        int postId = getIdPost(); 

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AddComment.fxml"));
            Parent root = loader.load();
            AddCommentController detailsController = loader.getController();

            detailsController.loadEventDetails(postId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void updateNumberOfComments(int nbreCommentaires) {
        labelnbrecomment.setText(String.valueOf(nbreCommentaires) + " Comments");
    }

    public void initi(int postId) {
        this.idPost = postId;
    }
   
    
}
