/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Post;
import Services.PostService;
import java.io.File;
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
public class ItemListPostsController implements Initializable {

    @FXML
    private Label labelPrenomUser;
    @FXML
    private Label labelnomUser;
    @FXML
    private Label labeSubject;
    @FXML
    private Label labeltitle;
    @FXML
    private Label labelpost;
    @FXML
    private Label labeldate;
    @FXML
    private ImageView imgimg;
    @FXML
    private Button buttonComments;
 // private Post post;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();

    /**
     * Initializes the controller class.
     */
  
    private Post post;
    public void initialize(Post post) {
        
      
        System.out.println("ID du post : " + post.getIdPost());
        String imagePath;
        labelnomUser.setText(post.getNomUser());
        labelPrenomUser.setText(post.getPrenomUser());
        labeSubject.setText(post.getSubject());
        labeltitle.setText(post.getTitle());
        labelpost.setText(post.getDescriptionp());
        imagePath = post.getImage();
        Image image = new Image("file:" + imagePath, 80, 75, false, true);
        imgimg.setImage(image);
        labeldate.setText(post.getDateCreationp().toString());
     
    } 
    

    public int getPostIdFromLabelDescription(String description) {
    int postId = 0;
    String sql = "SELECT `idPost` FROM `Post` WHERE `descriptionp`=?";
    
    try {
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, description);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            postId = rs.getInt("idPost");
        } else {
            System.out.println("Aucun résultat trouvé pour la description : " + description);
        }

    } catch (SQLException ex) {
        Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("ID du post: " + postId);
    return postId;
}

    
   
    @FXML
    private void ShowComments(ActionEvent event) {
        int postId = getPostIdFromLabelDescription(labelpost.getText()); 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CommentsBackForum.fxml"));
            Parent root = loader.load();
            //int postId = post.getIdPost();
            CommentsBackForumController commentsbackController = loader.getController();
            commentsbackController.initData(postId); // Passez l'ID du post

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
