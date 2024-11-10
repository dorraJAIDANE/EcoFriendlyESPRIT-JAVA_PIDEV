/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import static GUI.UserSession.getCurrentUser;
import Models.Commentaire;
import Models.Post;
import Models.getData;
import Models.user;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;


/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class ForumInterfaceController implements Initializable {

    private AnchorPane forum_form;
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
    private Button buttoninterfaceaddpost;
    @FXML
    private GridPane gridForum;
    @FXML
    private Button close;
    @FXML
    private ScrollPane Scrollpane;
    @FXML
    private Button buttonMinus;
    private double x = 0;
    private double y = 0;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button buttoninterfaceaddpost1;
    @FXML
    private GridPane gridForum1;
    @FXML
    private Button buttoninterfaceaddpost2;
    @FXML
    private GridPane gridForum2;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Tab tabCodingProblems;
    @FXML
    private Tab tabEspritProblems;
    @FXML
    private Tab tabSaleExchange;
    @FXML
    private Button buttonMyPosts1;
    @FXML
    private Button buttonMyComments1;
    @FXML
    private Button buttonMyComments2;
    @FXML
    private Button buttonMyPosts2;
    @FXML
    private ScrollPane Scrollpane2;
    @FXML
    private Button buttonMyComments3;
    @FXML
    private Button buttonMyPosts3;
    @FXML
    private Button dasborbprofile;
    
    public GridPane getGridPaneSaleExchange() {
        return gridForum;
    }

    public GridPane getGridPaneCodingProblems() {
        return gridForum1;
    }

    public GridPane getGridPaneEspritProblems() {
        return gridForum2;
    }

    /**
     * Initializes the controller class.
     */
    //private GridPane TargetGridPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuDisplayCard();
     
    } 
    private AddPostController addPostController; // Ajoutez ce champ

    // Ajoutez cette méthode pour initialiser le contrôleur
    public void setForumInterfaceController(AddPostController  addPostController) {
        this.addPostController = addPostController;
    }
    
    ObservableList<Post> Cardlistdata = FXCollections.observableArrayList();
    public ObservableList<Post> AffichePost() {
   

    try {
        Statement st = cnx.createStatement();
        String sql = "SELECT * FROM post"; 

        ResultSet rs = st.executeQuery(sql);

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


      private int currentPage = 0;
      private int cardsPerPage = 9;
   
   
 public void scrollToMiddle() {
    scrollpane.setVvalue(0.5);
}
 
  

    public void setAddPostController(AddPostController addPostController) {
        this.addPostController = addPostController;
    }
    public void menuDisplayCard() {
    int row = 0;
    int column = 0;
    int row1 = 0;
    int column1 = 0;
    int row2 = 0;
    int column2 = 0;
    try {
        ObservableList<Post> tempList = FXCollections.observableArrayList();
        tempList.addAll(AffichePost());

        gridForum.getRowConstraints().clear();
        gridForum.getColumnConstraints().clear();
        gridForum1.getRowConstraints().clear();
        gridForum1.getColumnConstraints().clear();
        gridForum2.getRowConstraints().clear();
        gridForum2.getColumnConstraints().clear();

        for (int q = 0; q < tempList.size(); q++) {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/GUI/ItemPhoto.fxml"));
            AnchorPane pane = load.load();
            ItemPhotoController card = load.getController();

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
            String sujet = getSubjectFromDatabase(postId);

            if (column == 3) {
                column = 0;
                row += 1;
            }
            if (column1 == 3) {
                column1 = 0;
                row1 += 1;
            }
            if (column2 == 3) {
                column2 = 0;
                row2 += 1;
            }


            switch (sujet) {
                case "Sale & Exchange":
                    gridForum.add(pane, column++, row);
                    break;
                case "Coding Problems":
                    gridForum1.add(pane, column1++, row1);
                    break;
                case "Esprit Problems":
                    gridForum2.add(pane, column2++, row2);
                    break;
            }
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

    
    private String getSubjectFromDatabase(int postId) {
    String sujet = "";
    try {
        PreparedStatement ps = cnx.prepareStatement("SELECT `subject` FROM post WHERE `idPost`=?");
        ps.setInt(1, postId);  // Set the value for the parameter
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            sujet = rs.getString("subject");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return sujet;
}




    @FXML
    private void switchForm(ActionEvent event) {
        /*if (event.getSource() == dashboard_btn) {
            dashbord_form.setVisible(true);
            forum_form.setVisible(false);
            transport_form.setVisible(false);
            biblio_form.setVisible(false);
            event_form.setVisible(false);
            services_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #FF0000);");
            forum_btn.setStyle("-fx-background-color:transparent");
            transport_btn.setStyle("-fx-background-color:transparent");
            biblio_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:transparent");
            services_btn.setStyle("-fx-background-color:transparent");*/

            /*homeTotalEmployees();
            homeEmployeeTotalPresent();
            homeTotalInactive();
            homeChart();*/

       /* } else if (event.getSource() == forum_btn) {
            dashbord_form.setVisible(false);
            forum_form.setVisible(true);
            transport_form.setVisible(false);
            biblio_form.setVisible(false);
            event_form.setVisible(false);
            services_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent");
            forum_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #FF0000);");
            transport_btn.setStyle("-fx-background-color:transparent");
            biblio_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:transparent");
            services_btn.setStyle("-fx-background-color:transparent");*/

            /*addEmployeeGendernList();
            addEmployeePositionList();
            addEmployeeSearch();*/

        /*} else if (event.getSource() == transport_btn) {
            dashbord_form.setVisible(false);
            forum_form.setVisible(false);
            transport_form.setVisible(true);
            biblio_form.setVisible(false);
            event_form.setVisible(false);
            services_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent");
            forum_btn.setStyle("-fx-background-color:transparent");
            transport_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #FF0000);");
            biblio_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:transparent");
            services_btn.setStyle("-fx-background-color:transparent");

            //salaryShowListData();

        }else if (event.getSource() == biblio_btn) {
            dashbord_form.setVisible(false);
            forum_form.setVisible(false);
            transport_form.setVisible(false);
            biblio_form.setVisible(true);
            event_form.setVisible(false);
            services_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent");
            forum_btn.setStyle("-fx-background-color:transparent");
            transport_btn.setStyle("-fx-background-color:transparent");
            biblio_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #FF0000);");
            event_btn.setStyle("-fx-background-color:transparent");
            services_btn.setStyle("-fx-background-color:transparent");

            //salaryShowListData();

        }else if (event.getSource() == event_btn) {
            dashbord_form.setVisible(false);
            forum_form.setVisible(false);
            transport_form.setVisible(false);
            biblio_form.setVisible(false);
            event_form.setVisible(true);
            services_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent");
            forum_btn.setStyle("-fx-background-color:transparent");
            transport_btn.setStyle("-fx-background-color:transparent");
            biblio_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #FF0000);");
            services_btn.setStyle("-fx-background-color:transparent");

            //salaryShowListData();

        }else if (event.getSource() == services_btn) {
            dashbord_form.setVisible(false);
            forum_form.setVisible(false);
            transport_form.setVisible(false);
            biblio_form.setVisible(false);
            event_form.setVisible(false);
            services_form.setVisible(true);

            dashboard_btn.setStyle("-fx-background-color:transparent");
            forum_btn.setStyle("-fx-background-color:transparent");
            transport_btn.setStyle("-fx-background-color:transparent");
            biblio_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:transparent");
            services_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #FF0000);");

            //salaryShowListData();

        }*/
    }

    @FXML
    private void logout() {
        /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
        }*/
    }

    @FXML
    public void AddbtnInterface(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AddPost.fxml"));
        Parent root = loader.load();
        AddPostController addPostController = loader.getController();
        addPostController.setForumInterfaceController(this);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }
    /*private int getPostIdFromButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        int postId = (int) button.getUserData(); 
        return postId;
    }*/
    
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
    private void close() {
        System.exit(0);
    }
    public void minimize() {
        Stage stage = (Stage) forum_form.getScene().getWindow();
    if (stage != null) {
        stage.setIconified(true);
    }
    }

    @FXML
    private void MyPosts1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MyPostsInterface.fxml"));
        Parent root = loader.load();
       // AddPostController addPostController = loader.getController();
        //addPostController.setForumInterfaceController(this);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    private static user currentUser = getCurrentUser();
    int id = currentUser.getIduser();
    @FXML
    private void MyComments1(ActionEvent event) throws IOException {
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
    
    /*public void seUserId(int postId) {
        this.idUser = postId;
    }*/

// Méthode pour obtenir l'ID de l'événement
    public Integer getIdUser() {
        return id;
    }

    @FXML
    private void profile(ActionEvent event) {
         try {      
            dasborbprofile.getScene().getWindow().hide();

            Parent root = FXMLLoader.load(getClass().getResource("/GUI/profile.fxml"));
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
