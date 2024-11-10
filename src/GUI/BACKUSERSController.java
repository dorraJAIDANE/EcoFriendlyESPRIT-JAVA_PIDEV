/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.user;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;
import Models.Service;
import Models.mail;
import Services.ECOservice;
import util.MyConnection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import INTERFACES.DocumentService;
import INTERFACES.TopicService;
import Models.Commentaire;
import Models.Document;
import Models.Event;
import Models.Post;
import Models.Topic;
import Services.DocumentServiceImp;
import Services.TopicServiceImp;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class BACKUSERSController implements Initializable {
private double x=0;
    private double y=0;
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
    private Button logout_btn2;
    @FXML
    private FontAwesomeIcon buttonlogou;
    @FXML
    private Button services_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button BLOCK;
    @FXML
    private ListView<user> listviewusers;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    private PreparedStatement prepare ;
    private ResultSet result ;
    @FXML
    private Button codepromo;
    @FXML
    private AnchorPane ADMINSERVICE1;
    @FXML
    private AnchorPane service_form;
    @FXML
    private TableView<Service> service_tableView;
    @FXML
    private TableColumn<?, ?> service_col_service;
    @FXML
    private TableColumn<?, ?> service_col_PPK;
    @FXML
    private TableColumn<?, ?> service_col_DU;
   @FXML
    private ComboBox<String> service_type;
    @FXML
    private TextField service_PPK;
    @FXML
    private ImageView service_imageView;
    @FXML
    private Button service_importBtn;
    @FXML
    private Button service_insertBtn;
    @FXML
    private Button service_updateBtn;
    @FXML
    private Button service_clearBtn;
    @FXML
    private Button service_deleteBtn;
    @FXML
    private BarChart<?, ?> dashbaord_IPD_chart;
    @FXML
    private LineChart<?, ?> dashbaord_NOO_chart;
    @FXML
    private AnchorPane sanchorpane2_d;
    @FXML
    private AnchorPane sanchorpane3_d;
    @FXML
    private AnchorPane sanchorpane4_d;
    @FXML
private TableView<Document> treeview_d;
    @FXML
    private TableColumn<?, ?> name_doc_d;
    @FXML
    private TableColumn<?, ?> semester_d;
    @FXML
    private TableColumn<?, ?> level_d;
    @FXML
    private TableColumn<?, ?> topic_d;
    @FXML
    private TableColumn<?, ?> type_d;
    @FXML
    private TableColumn<?, ?> image_d;
    @FXML
    private TableColumn<?, ?> valide_d;
    @FXML
    private ListView<String> listeview;
    @FXML
    private TextField dorratopic;
    @FXML
    private TableColumn<Event, String>  nomev;
    @FXML
    private TableColumn<Event, String>  typeev;
    @FXML
    private TableColumn<Event, String>  dureadm;
    @FXML
    private TableColumn<Event, String>  Lieuadm;
    @FXML
    private TableColumn<Event, Double>  Prixadm;
    @FXML
    private TableColumn<Event, Integer>  nbadm;
    @FXML
    private TableColumn<Event, String>  validadm;
    @FXML
    private TableView<Event> admintab;

    @FXML
    private Button banadm;
    @FXML
    private AnchorPane dashboard_form;
   
    @FXML
    private Label labelStatus;
    @FXML
    private ListView<Post> listViewlistPost;
    /**
     * Initializes the controller class.
     */
        private ObservableList<Document> documentList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane bibliodorra;
    @FXML
    private AnchorPane posteeya;
    @FXML
    private AnchorPane eventamine;
    @FXML
    private TableView<Event> admintab1;
    @FXML
    private TableColumn<Event, String>  nomev1;
    @FXML
    private TableColumn<Event, String>  typeev1;
    @FXML
    private TableColumn<Event, String> dureadm1;
    @FXML
    private TableColumn<Event, String>  Lieuadm1;
    @FXML
    private TableColumn<Event, String>  Prixadm1;
    @FXML
    private TableColumn<Event, String>  nbadm1;
    @FXML
    private TableColumn<Event, String>  validadm1;
    @FXML
    private Button banadm1;
    @FXML
    private AnchorPane hayhtem;
    @FXML
    private AnchorPane userhaythem;
     @FXML
    private ListView<Commentaire> listViewlistComment;
        private ObservableList<Post> posts = FXCollections.observableArrayList();

//initialize 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          affichagelisteview();
listeview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ActionEvent ActionEvent = null;
  refrech_d(ActionEvent);
        initData();
          ObservableList<String> services = FXCollections.observableArrayList();
        services.add("pressing");
        services.add("maintenance");
        services.add("delivery");

        // Assuming service_type is a ComboBox in your FXML file
        service_type.setItems(services);
        servicesShowData();
        // Set up a listener for the TableView's selection model
        service_tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Call servicesSelectData() with the selected Service object
            servicesSelectData(newValue);
        });
         // TO DISPLAY THE CHART OF NUMBER OF ORDERS
      List<String> recipients = getAllEmails();
displayNOOChart(recipients);

    sendEmailAutomatically();
        // TO DISPLAY THE CHART OF INCOME PER DAY
        displayIPDChart();
        
                  addEmployeeShowListData();
                 initDataeya2() ; 
                  
    }    
public void addEmployeeShowListData() {
    ObservableList<Event> eventList = getEventsFromDatabase();
        System.out.println(eventList);
    nomev1.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
    dureadm1.setCellValueFactory(new PropertyValueFactory<>("Durée"));
    Prixadm1.setCellValueFactory(new PropertyValueFactory<>("PrixTicket"));
    Lieuadm1.setCellValueFactory(new PropertyValueFactory<>("LieuEvent"));
    nbadm1.setCellValueFactory(new PropertyValueFactory<>("nbmaxParticipant"));
    typeev1.setCellValueFactory(new PropertyValueFactory<>("typeEvent"));
    // Use the 'valid' field to populate another column, e.g., addEmployee_colEmpValid
    validadm1.setCellValueFactory(new PropertyValueFactory<>("valid"));

    admintab1.setItems(eventList);

    for (Event event : eventList) {
        System.out.println("Event Name: " + event.getNomEvent() + ", Durée: " + event.getDurée());
    }  
}
 public ObservableList<Event> getEventsFromDatabase() {
    ObservableList<Event> events = FXCollections.observableArrayList();
    String req = "SELECT idEvent, nomEvent, typeEvent, Durée, PrixTicket, LieuEvent, nbmaxParticipant,valid FROM event"; // Inclure idEvent dans la requête SQL
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Ne pas inclure l'ID de l'événement ici
            Event event = new Event(
                rs.getString("nomEvent"),
                rs.getString("typeEvent"),
                rs.getString("Durée"),
                rs.getDouble("PrixTicket"),
                rs.getString("LieuEvent"),
                rs.getInt("nbmaxParticipant"),
                rs.getString("valid")
            );
            // Récupérez l'ID de l'événement et stockez-le séparément si nécessaire
            
            events.add(event);
        }

        rs.close();
        ps.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return events;
}

   
@FXML
private void handleEventClick() {
    Event selectedEvent = admintab.getSelectionModel().getSelectedItem();

    if (selectedEvent != null) {
        int eventId = selectedEvent.getIdEvent();
        System.out.println("ID de l'événement sélectionné : " + eventId);

        // Vous pouvez ouvrir une nouvelle fenêtre ou effectuer d'autres opérations ici en utilisant l'ID de l'événement
        // Par exemple, si vous avez une autre méthode pour ouvrir une fenêtre, vous pouvez l'appeler ici avec eventId en tant que paramètre.
    }
}

    @FXML
    private void switchForm(ActionEvent event) {
        
        if (event.getSource() == dashboard_btn) {
           // dashboard_form.setVisible(false);
          ADMINSERVICE1.setVisible(false);
            bibliodorra.setVisible(false);
            posteeya.setVisible(false);
             userhaythem.setVisible(true);
             eventamine.setVisible(false);
            

        } else if (event.getSource() == forum_btn) {
            //initDataeya2() ; 
        
  ADMINSERVICE1.setVisible(false);
            bibliodorra.setVisible(false);
            posteeya.setVisible(true);
             userhaythem.setVisible(false);
             eventamine.setVisible(false);
            
        } else if (event.getSource() == transport_btn) {
//            dashboard_form.setVisible(false);
//            order_form.setVisible(false);
//            service_form.setVisible(true);
//            customers_form.setVisible(false);

          

        } else if (event.getSource() == biblio_btn) {
 ADMINSERVICE1.setVisible(false);
            bibliodorra.setVisible(true);
            posteeya.setVisible(false);
             userhaythem.setVisible(false);
             eventamine.setVisible(false);
            

           
        }
else if (event.getSource() == event_btn) {
    addEmployeeShowListData();
  ADMINSERVICE1.setVisible(false);
            bibliodorra.setVisible(false);
            posteeya.setVisible(false);
             userhaythem.setVisible(false);
             eventamine.setVisible(true);
            
           
        }
        else if (event.getSource() == services_btn) {
  ADMINSERVICE1.setVisible(true);
            bibliodorra.setVisible(false);
            posteeya.setVisible(false);
             userhaythem.setVisible(false);
             eventamine.setVisible(false);
            
           
        }
        
    }
    public void initData() {
        //this.postId = postId;

        List<user> usersFromDB = getUserFromDatabase();
        ObservableList<user> users = FXCollections.observableArrayList(usersFromDB);
        listviewusers.setItems(users);
        
        
        listviewusers.setCellFactory(param -> new ListCell<user>() {
            @Override
            protected void updateItem(user  item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        System.out.println("eya");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/itemlistusers.fxml"));
                        AnchorPane pane = loader.load();
                        ItemlistusersController controller = loader.getController();
                        controller.initialize(item);
                        setGraphic(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(BACKUSERSController.class.getName()).log(Level.SEVERE, null, ex);
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
                java.sql.Date dateCreationp = rs.getDate("dateCreationp");
                
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
private int postId;
    public void initDataeya1(int postId) {
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
        Post selectedPost = (Post) listViewlistPost.getSelectionModel().getSelectedItem();

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

 public List<user> getUserFromDatabase() {
        List<user> users = new ArrayList<> ();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM user2 ";
           
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
               
                user us = new user();
                String nom = rs.getString("nomuser");
                String prenom = rs.getString("prenomuser");
                String email = rs.getString("mailuser");
                String classeuser = rs.getString("classeuser");
                String address = rs.getString("adressuser");
                
           
                us.setPrenomuser(prenom);
                us.setNomuser(nom);
                us.setMailuser(email);
                us.setClasseuser(classeuser);
                us.setAdressuser(address);
                
                users.add(us);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @FXML
    private void logout() {
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
        }}

    @FXML
    private void promo() { try {     codepromo.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("promo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
                 
                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                     });
                      root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
        });
                
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        
        
        stage.show();
    } catch(Exception e){
        e.printStackTrace();
    }
  
    }
@FXML
    private void blockUser() {
    user selectedUser = listviewusers.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        String sql = "UPDATE user2 SET isBlocked = ? WHERE mailuser = ?";

        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, selectedUser.getMailuser()); 
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("User blocked successfully!");
                alert.showAndWait();
            } else {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to block user.");
                alert.showAndWait();
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    } else {
        // Show error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select a user to block.");
        alert.showAndWait();
    }
}



// TO SHOW YOUR DATA IN OUR TABLEVIEW
    int id;
    ObservableList<Service> servicesListData = FXCollections.observableArrayList();
    ECOservice eco = new ECOservice();
    Alert alert;
    //Connection cnx = MyConnection.getInstance().getCnx();





public void servicesSelectData(Service selectedService) {
   // Service selectedService = service_tableView.getSelectionModel().getSelectedItem();
    
    if (selectedService != null) {
        id = selectedService.getServiceId();
        service_type.setValue(selectedService.getServiceName());
        service_PPK.setText(String.valueOf(selectedService.getPrice()));

        // Load and display the image
        String imageUrl = selectedService.getImg();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image selectedImage = new Image( imageUrl, 103, 115, false, true);
            service_imageView.setImage(selectedImage);
        } else {
            // Handle the case where there is no image URL
            service_imageView.setImage(null);
        }
    }
}

public void servicesShowData() {
    servicesListData.clear(); // Clear the list before adding new data
    servicesListData.addAll(eco.getAll());

    service_col_service.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
    service_col_PPK.setCellValueFactory(new PropertyValueFactory<>("price"));
    service_col_DU.setCellValueFactory(new PropertyValueFactory<>("img"));

    service_tableView.setItems(servicesListData);
}

    @FXML
    private void servicesSelectData(MouseEvent event) {
    }

    @FXML
    private void servicesImportBtn(ActionEvent event) throws IOException {
        
     
         FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Choisir une image");
// Définir les filtres pour n'afficher que les fichiers image
fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter( "*.png", "*.jpg", "*.gif")
);

// Ouvrir la boîte de dialogue de sélection de fichier
File imageFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

if (imageFile != null) {
    // Load the selected image and set it to the ImageView
    Image image = new Image(imageFile.toURI().toString(),103, 115, false, true);
    service_imageView.setImage(image);
}

}
         
         
        
        
    

    @FXML
   
      public void servicesInsertBtn() {
 
        if (
                 service_type.getSelectionModel().getSelectedItem() == null
                || service_PPK.getText().isEmpty() ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        } else {
            try{
        Service serv =new Service ();
         
            double price = Double.parseDouble(service_PPK.getText());
        serv.setServiceName((String)service_type.getSelectionModel().getSelectedItem());
             serv.setPrice(price);
             
            serv.setImg(service_imageView.getImage().impl_getUrl());
            eco.ajouter(serv);
            alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                   servicesShowData();

        }
            
            
            
         catch (Exception e) {
                e.printStackTrace();
            }}
        }

    

    @FXML
private void servicesUpdateBtn(ActionEvent event) {
   // Call servicesSelectData() to ensure a valid item is selected
    Service selectedService = service_tableView.getSelectionModel().getSelectedItem();
    if (selectedService == null) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the item first");
        alert.showAndWait();
    } else {
        try {
            // Populate the fields with the selected service's data
           
            // Modify the selected service
            Service modifiedService = new Service();
            double price = Double.parseDouble(service_PPK.getText());
            modifiedService.setServiceId(selectedService.getServiceId());
            modifiedService.setServiceName((String)service_type.getSelectionModel().getSelectedItem());
            modifiedService.setPrice(price);
            modifiedService.setImg(service_imageView.getImage().impl_getUrl());

            //ECOservice eco = new ECOservice();
            eco.modifier(modifiedService);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Updated!");
            alert.showAndWait();

            // Refresh the TableView to reflect the changes
            servicesShowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    @FXML
    private void servicesClearBtn() {
        service_type.getSelectionModel().clearSelection();
        service_PPK.setText("");
        service_imageView.setImage(null);
    }

    @FXML
    private void servicesDeleteBtn(ActionEvent event) {
    //// Call servicesSelectData() to ensure a valid item is selected
    Service selectedService = service_tableView.getSelectionModel().getSelectedItem();

    if (selectedService == null) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the item first");
        alert.showAndWait();
    } else {
        int id = selectedService.getServiceId();
        try {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Service ID: " + id);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                eco.supprimer(id);
               // System.out.println("louay hna");

                // TO CLEAR ALL FIELDS
                servicesClearBtn();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();
                servicesShowData();

            } else {
                alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//order income per day dashboard
 public void displayIPDChart() {
    dashbaord_IPD_chart.getData().clear();

    String sql = "SELECT DATE(pickupDateTime), SUM(priceOrder) FROM orders GROUP BY DATE(pickupDateTime) ORDER BY DATE(pickupDateTime)  ASC LIMIT 10";

    try {
        XYChart.Series chart = new XYChart.Series<>();
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (resultSet.next()) {
            // Get the date from the result set
            Date pickupDate = resultSet.getTimestamp(1);
            // Format the date to "yyyy-MM-dd" string
            String formattedDate = dateFormat.format(pickupDate);
            // Get the sum of priceOrder from the result set
            float totalPrice = resultSet.getFloat(2);
            // Add the formatted date and total price to the chart
            chart.getData().add(new XYChart.Data<>(formattedDate, totalPrice));
        }

        dashbaord_IPD_chart.getData().add(chart);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
 
 
 
 public List<String> getAllEmails() {
        List<String> emails = new ArrayList<>();

        String sql = "SELECT mailuser FROM user2";

        try {Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(sql); 
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("mailuser");
                emails.add(email);
            }
        } catch (Exception  e) {
            e.printStackTrace(); // Handle potential exceptions according to your needs
        }

        return emails;
    }
    //order numbers of orders
public XYChart.Series<String, Integer> displayNOOChart(List<String> recipientEmails) {
        XYChart.Series<String, Integer> chartData = new XYChart.Series<>();
         XYChart.Series chart = new XYChart.Series<>();

        String sql = "SELECT DATE(pickupDateTime), COUNT(DISTINCT orderId) FROM orders GROUP BY DATE(pickupDateTime) ORDER BY DATE(pickupDateTime) ASC ";

        try {
            Connection connection = MyConnection.getInstance().getCnx();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while (resultSet.next()) {
            Date date = resultSet.getTimestamp(1);
            String formattedDate = dateFormat.format(date);
            int customerCount = resultSet.getInt(2);
            chart.getData().add(new XYChart.Data<>(formattedDate, customerCount));
             chartData.getData().add(new XYChart.Data<>(formattedDate, customerCount));
            if (customerCount < 1) {
                // Prepare email content
                sendEmailAutomatically();
                Random random = new Random() ;
        int number = random.nextInt(101001) ;
                // Send email to all recipients in the list
                for (String recipient : recipientEmails) {
                    System.out.println(recipient);
                    
                      try {
                mail.envoi("Dear Our Student ", "check ours service now  your discount code"+number,recipient);
                 } catch (Exception e) {
                        e.printStackTrace(); 
                    }
                 //   sendEmail(host, port, mailFrom, password, recipient, subject, message, attachFiles);
                }
            }
        }

        dashbaord_NOO_chart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chartData;
    }
private void sendEmailAutomatically() {
    final String fromEmail = "dorra.jaidane@esprit.tn";
    final String password = "223JFT5027";
    final String toEmail = "louay.sghaier@esprit.tn";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("check our last orders ");
        message.setText("new services has been updated for you  ");

        Transport.send(message);

        System.out.println("Email sent successfully.");
    } catch (MessagingException e) {
        e.printStackTrace();
        System.out.println("Failed to send the email.");
    }}
@FXML
    private void delete_d(ActionEvent event) {
           DocumentServiceImp doc =new  DocumentServiceImp(); 
            Document selectedDocument = treeview_d.getSelectionModel().getSelectedItem();
    
    if (selectedDocument != null) {
        int selectedDocumentId = selectedDocument.getIdDoc();


          doc.ban(selectedDocumentId);

        
            
            doc.getValidDocuments();
            
            treeview_d.getSelectionModel().clearSelection();
            } else {
       
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Aucun document sélectionné");
        alert.setContentText("Veuillez sélectionner un document à supprimer.");
        alert.showAndWait();
    }
}
         
    

    @FXML
    private void refrech_d(ActionEvent event) {
        DocumentServiceImp doc =new  DocumentServiceImp(); 
       documentList.clear();
      List<Document> newData; 
        newData = doc.getAllDocuments();
    documentList.clear();
    documentList.addAll(newData);
    treeview_d.setItems(documentList);
        tablevieww() ;
    }

    private void tablevieww() {
       name_doc_d.setCellValueFactory(new PropertyValueFactory<>("documentName"));
    semester_d.setCellValueFactory(new PropertyValueFactory<>("idSemestre.semestreName"));
    level_d.setCellValueFactory(new PropertyValueFactory<>("idNiveau.niveauName"));
    topic_d.setCellValueFactory(new PropertyValueFactory<>("idTopic.topicName"));
    type_d.setCellValueFactory(new PropertyValueFactory<>("documentType"));
    image_d.setCellValueFactory(new PropertyValueFactory<>("documentImage"));
    valide_d.setCellValueFactory(new PropertyValueFactory<>("isvalid"));


   treeview_d.setItems(documentList);

    }
    private ObservableList<Topic> topicsList = FXCollections.observableArrayList();

      private TopicService topicService=new TopicServiceImp(); 


 @FXML
    void ajoutettopic_d(ActionEvent event) {
         Topic t = new Topic();
    t.setTopicName((String)dorratopic.getText());
    topicService.addTopic(t);
     affichagelisteview();
    }

    private void affichagelisteview(){
            System.out.println("affichagelisteview " );

        List<String> topics =topicService.getAllTopics().stream().map(t->t.getTopicName()).collect(Collectors.toList());
        listeview.getItems().clear();
        listeview.getItems().addAll(topics);
    }

    @FXML
    private void deletetopic_d(ActionEvent event) {
        String selectedItem = listeview.getSelectionModel().getSelectedItem();

if (selectedItem != null) {
    System.out.println("Selected Item: " + selectedItem);
     Topic topic =(Topic) topicService.getAllTopics().stream().filter(t->t.getTopicName().equals(selectedItem.trim())).findFirst().get();
      if(topic!=null){
              System.out.println("Selected Item: " + topic.toString());

          topicService.supprimerTopic(topic.getIdTopic());
          affichagelisteview();
      }
} 
    }


//    @FXML
//    private void servicesSelectData(MouseEvent event) {
//    }
//
//    @FXML
//    private void servicesImportBtn(ActionEvent event) {
//    }
//
//    @FXML
//    private void servicesInsertBtn(ActionEvent event) {
//    }
//
//    @FXML
//    private void servicesUpdateBtn(ActionEvent event) {
//    }
//
//    @FXML
//    private void servicesClearBtn(ActionEvent event) {
//    }
//
//    @FXML
//    private void servicesDeleteBtn(ActionEvent event) {
//    }
//
//    @FXML
//    private void delete_d(ActionEvent event) {
//    }
//
//    @FXML
//    private void refrech_d(ActionEvent event) {
//    }
//
//    @FXML
//    private void ajoutettopic_d(ActionEvent event) {
//    }
//
//    @FXML
//    private void deletetopic_d(ActionEvent event) {
//    }
//
//    @FXML
//    private void Block(ActionEvent event) {
//    }
    
}

