/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Commentaire;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class CommentsBackForumController implements Initializable {

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
    private ListView<Commentaire> listViewlistComment;
    @FXML
    private Button buttonReturn;

    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    private ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
    @FXML
    private Button codepromo;
    @FXML
    private Button logout_btn2;
    @FXML
    private FontAwesomeIcon buttonlogou;
    /**
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    private int postId;
    public void initData(int postId) {
        this.postId = postId;
        System.out.println(postId);
        List<Commentaire> commentairesFromDB = getCommentairesFromDatabase(postId);
        ObservableList<Commentaire> commentaires = FXCollections.observableArrayList(commentairesFromDB);
        listViewlistComment.setItems(commentaires);
        
        
        listViewlistComment.setCellFactory(param -> new ListCell<Commentaire>() {
            @Override
            protected void updateItem(Commentaire item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ItemBackComments.fxml"));
                        AnchorPane pane = loader.load();
                        ItemBackCommentsController controller = loader.getController();
                        controller.initialize(item);
                        setGraphic(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(CommentsBackForumController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    public List<Commentaire> getCommentairesFromDatabase(int idPost) {
        // Connectez-vous à la base de données et récupérez les commentaires
        // Assurez-vous de remplacer les détails de connexion et la requête SQL

        List<Commentaire> commentaires = new ArrayList<>();
        String req = "SELECT * FROM `commentaire` WHERE `idPost`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            Statement stmt = cnx.createStatement();
            ps.setInt(1, idPost);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setIdComment(rs.getInt("idComment"));
                commentaire.setNomUser(rs.getString("nomUser"));
                commentaire.setPrenomUser(rs.getString("prenomUser"));
                commentaire.setDescription(rs.getString("Description"));
                commentaire.setDateCreation(rs.getDate("Datecreation"));
                commentaire.setIdPost(rs.getInt("idPost"));
                commentaires.add(commentaire);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentaires;
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

    @FXML
    private void close(ActionEvent event) {
         System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) AddComment_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    private void Return(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/backForum.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
}
