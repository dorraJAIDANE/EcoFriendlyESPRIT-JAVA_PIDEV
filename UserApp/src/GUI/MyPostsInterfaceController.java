/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.UserSession.getCurrentUser;
import Models.Commentaire;
import Models.Post;
import Models.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class MyPostsInterfaceController implements Initializable {

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
    private Button logout_btn;
    @FXML
    private Button services_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private GridPane GridpaneMyPosts;
    @FXML
    private Button buttonReturn;
    @FXML
    private Button close;
    @FXML
    private Button dasborbprofile;
    
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuDisplayCard();
    }    

    @FXML
    private void switchForm(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    
    private static user currentUser = getCurrentUser();
    ObservableList<Post> Cardlistdata = FXCollections.observableArrayList();
    public ObservableList<Post> AffichePost() {
      int id = currentUser.getIduser();

    try {
        
        String sql = "SELECT * FROM post WHERE `idUser`=?"; 
        PreparedStatement ps = cnx.prepareStatement(sql);
        Statement stmt = cnx.createStatement();
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Créez un modèle Event à partir des données de la base de données
            Post post = new Post();

            // Récupérez toutes les colonnes de la table event
            post.setIdPost(rs.getInt("idPost"));
            post.setNomUser(rs.getString("nomUser"));
            post.setPrenomUser(rs.getString("prenomUser"));
            post.setImage(rs.getString("image"));
            post.setDateCreationp(rs.getDate("dateCreationp"));
            post.setNbreComment(rs.getInt("nbreComment"));
            post.setTitle(rs.getString("title"));
            post.setDescriptionp(rs.getString("descriptionp"));
            post.setSubject(rs.getString("subject"));
            

            Cardlistdata.add(post);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
     System.out.println("Size: " + Cardlistdata.size());

    return Cardlistdata;
}

    public void menuDisplayCard() {
    int row = 0;
    int column = 0;
    
    try {
        ObservableList<Post> tempList = FXCollections.observableArrayList();
        tempList.addAll(AffichePost());

        GridpaneMyPosts.getRowConstraints().clear();
        GridpaneMyPosts.getColumnConstraints().clear();
      

        for (int q = 0; q < tempList.size(); q++) {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/GUI/ItemMyPosts.fxml"));
            AnchorPane pane = load.load();
            ItemMyPostsController card = load.getController();

            card.setPostId(tempList.get(q).getIdPost());
            card.setData(
                    tempList.get(q).getNomUser(),
                    tempList.get(q).getPrenomUser(),
                    tempList.get(q).getImage(),
                    tempList.get(q).getTitle(),
                    tempList.get(q).getDescriptionp(),
                    tempList.get(q).getNbreComment(),
                    tempList.get(q).getDateCreationp()
            );

            int postId = tempList.get(q).getIdPost();
            //String sujet = getSubjectFromDatabase(postId);

            if (column == 3) {
                column = 0;
                row += 1;
            }
           

           
                    GridpaneMyPosts.add(pane, column++, row);
                    
                
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
    
    

    @FXML
    private void Return(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void close(ActionEvent event) {
                System.exit(0);

    }
    
    private List<Commentaire> getCommentsForPost(int postId) {
        List<Commentaire> comments = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String sql = "SELECT * FROM commentaire WHERE `idPost`=?" ;
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Commentaire comment = new Commentaire();
                comment.setIdComment(rs.getInt("idComment"));
                comment.setIdPost(rs.getInt("idPost"));
                comment.setDescription(rs.getString("Description"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    
    @FXML
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
