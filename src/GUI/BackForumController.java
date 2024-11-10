/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Commentaire;
import Models.Post;
import Services.PostService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
 * @author Grati Eya
 */
public class BackForumController implements Initializable {

    @FXML
    private AnchorPane AddComment_form;
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
    @FXML
    private ListView<Post> listViewlistPost;
    
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    private ObservableList<Post> posts = FXCollections.observableArrayList();
    @FXML
    private Button buttonBlockPost;
    @FXML
    private Button logout_btn2;
    @FXML
    private FontAwesomeIcon buttonlogou;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDataeya2();
    }    

    @FXML
    private void switchForm(ActionEvent event) {
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

                logout_btn2.getScene().getWindow().hide();
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

    public void initDataeya2() {

        List<Post> postsFromDB = getPostsFromDatabase();
        ObservableList<Post> posts = FXCollections.observableArrayList(postsFromDB);
        listViewlistPost.setItems(posts);
        
        
        listViewlistPost.setCellFactory(param -> new ListCell<Post>() {
            @Override
            protected void updateItem(Post item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ItemListPosts.fxml"));
                        AnchorPane pane = loader.load();
                        ItemListPostsController controller = loader.getController();
                        controller.initialize(item);
                        setGraphic(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(BackForumController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    private List<Post> getPostsFromDatabase() {
        List<Post> posts = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String sql = "SELECT * FROM post"; // Sélectionnez tous les champs pour chaque post
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Post post = new Post();
                //int idPost = rs.getInt("idPost");
                String prenomUser = rs.getString("prenomUser");
                String nomUser = rs.getString("nomUser");
                String subject = rs.getString("subject");
                String title = rs.getString("title"); 
                String descriptionp = rs.getString("descriptionp");
                String image = rs.getString("image");
                Date dateCreationp = rs.getDate("dateCreationp");
                
                post.setPrenomUser(prenomUser);
                post.setNomUser(nomUser);
                post.setSubject(subject);
                post.setTitle(title);
                post.setDescriptionp(descriptionp);
                post.setImage(image);
                post.setDateCreationp(dateCreationp);
                posts.add(post);
                //Timestamp timestamp = rs.getTimestamp("timestamp");
                //Post post = new Post(idPost, prenomUser, nomUser, subject, title,descriptionp, image , dateCreationp);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent event) {
            Stage stage = (Stage) dashboard_form.getScene().getWindow();
    if (stage != null) {
        stage.setIconified(true);
    }
    }

    private void Delete(ActionEvent event) {
        Post selectedPost = listViewlistPost.getSelectionModel().getSelectedItem();

    if (selectedPost != null) {
        // Appeler la méthode de suppression
        deletePost(selectedPost);
    } else {
        // Aucun élément sélectionné, afficher un message d'erreur si nécessaire
    }
        
    }
    private void deletePost(Post post) {
    try {
        PreparedStatement st = cnx.prepareStatement("DELETE FROM post WHERE `idPost`=? ");
        st.setInt(1, post.getIdPost()); // Assurez-vous d'avoir un getter pour l'id du post

        int rowsAffected = st.executeUpdate();

        if (rowsAffected > 0) {
            // La suppression a réussi
            labelStatus.setText("Le post a été supprimé avec succès.");
            posts.remove(post); // Mettre à jour la liste des posts après suppression
            listViewlistPost.refresh(); // Rafraîchir la ListView
        } else {
            // Aucune ligne supprimée, le post n'a peut-être pas été trouvé
            labelStatus.setText("Le post n'a pas été trouvé.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérer l'exception (afficher un message d'erreur ou effectuer d'autres actions si nécessaire)
        labelStatus.setText("Une erreur s'est produite lors de la suppression du post.");
    }
}

    @FXML
    private void Block(ActionEvent event) {
    }

  
    
}
