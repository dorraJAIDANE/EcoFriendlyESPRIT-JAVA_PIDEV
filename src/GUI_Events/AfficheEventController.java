/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;


import static GUI.UserSession.getCurrentUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Models.Event;
import Models.Participation;
import Models.user;
import Services.Eventservice;
import Services.Participationservice;
import Services.userService;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mekni
 */
public class AfficheEventController implements Initializable {

    
    @FXML
      private AnchorPane main_form;
    @FXML
    private TextField upeventname;
    @FXML
    private TextField lieueventup;
    @FXML
    private TextField dureeup;
    @FXML
    private DatePicker datedebutup;
    @FXML
    private TextField ticketpriceup;
    @FXML
    private TextField nbmaxparticipationup;
    @FXML
    private ComboBox<String> eventtypeup = new ComboBox<>();
    @FXML
    private TextArea eventdescriptionup;
    @FXML
    private ImageView viewimage;
    @FXML
    private Button importeerup;
    @FXML
    private Button ok1;
   
    
    
        private static user currentUser=getCurrentUser();

    int selectedEventId=-1;
    
    
    public void setEventToUpdate(int selectedEventId) {
        this.selectedEventId = selectedEventId;
    }
    @FXML
    public void updateEvent(ActionEvent event) {
    int eventId = selectedEventId;
    int userId = currentUser.getIduser();

    String newNomEvent = upeventname.getText();
    String newLieuEvent = lieueventup.getText();
    String newDuree = dureeup.getText();
    LocalDate newDateDebut = datedebutup.getValue();
    String newPrixTicket = ticketpriceup.getText();
    String newNbMaxParticipants = nbmaxparticipationup.getText();
    String newTypeEvent = (String) eventtypeup.getValue();
    String newDescriptionEvent = eventdescriptionup.getText();
    String newImagePath = getData.path;

    if (newNomEvent.isEmpty() || newLieuEvent.isEmpty() || newDuree.isEmpty() || newDateDebut == null
        || newPrixTicket.isEmpty() || newNbMaxParticipants.isEmpty() || newTypeEvent.isEmpty() || newDescriptionEvent.isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Champs vides", "Veuillez remplir tous les champs obligatoires.");
        return;
    }

    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText("Confirmer la mise à jour");
    confirmationAlert.setContentText("Êtes-vous sûr de vouloir mettre à jour l'événement ?");

    Optional<ButtonType> result = confirmationAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        Event updatedEvent = new Event();
        updatedEvent.setIdEvent(eventId);
        updatedEvent.setNomEvent(newNomEvent);
        updatedEvent.setLieuEvent(newLieuEvent);
        updatedEvent.setDurée(newDuree);
        updatedEvent.setDateDebutEvent(java.sql.Date.valueOf(newDateDebut));
        updatedEvent.setPrixTicket(Double.parseDouble(newPrixTicket));
        updatedEvent.setNbmaxParticipant(Integer.parseInt(newNbMaxParticipants));
        updatedEvent.setTypeEvent(newTypeEvent);
        updatedEvent.setDescriptionEvent(newDescriptionEvent);
        updatedEvent.setImage(newImagePath);

        Eventservice eventService = new Eventservice();
        eventService.updateEvent(updatedEvent, userId);
        
      
        


        showAlert(Alert.AlertType.INFORMATION, "Succès", "L'événement a été mis à jour avec succès.");
    
        // L'utilisateur a annulé la mise à jour, vous pouvez ajouter du code ici si nécessaire
    }
}

    
    
    
    
    
    @FXML
     public void addEmployeeInsertImage() {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString(), 150, 120, false, true);
            viewimage.setImage(image);
        }
    }
                        String uri = getData.path;
    
    
    
    
    
    
    
    
    
    
                            
                        
private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

    
    
    
//
    
    
//    public void setEventToUpdate(int selectedEventId) {
//        this.selectedEventId = selectedEventId;
//    }
//    public void updateEvent(ActionEvent event) {
//    int eventId = selectedEventId;
//    int userId = 2;
//
//    String newNomEvent = upeventname.getText();
//    String newLieuEvent = lieueventup.getText();
//    String newDuree = dureeup.getText();
//    LocalDate newDateDebut = datedebutup.getValue();
//    String newPrixTicket = ticketpriceup.getText();
//    String newNbMaxParticipants = nbmaxparticipationup.getText();
//    String newTypeEvent = (String) eventtypeup.getValue();
//    String newDescriptionEvent = eventdescriptionup.getText();
//    String newImagePath = getData.path;
//
//    if (newNomEvent.isEmpty() || newLieuEvent.isEmpty() || newDuree.isEmpty() || newDateDebut == null
//        || newPrixTicket.isEmpty() || newNbMaxParticipants.isEmpty() || newTypeEvent.isEmpty() || newDescriptionEvent.isEmpty()) {
//        showAlert(Alert.AlertType.ERROR, "Champs vides", "Veuillez remplir tous les champs obligatoires.");
//        return;
//    }
//
//    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
//    confirmationAlert.setTitle("Confirmation");
//    confirmationAlert.setHeaderText("Confirmer la mise à jour");
//    confirmationAlert.setContentText("Êtes-vous sûr de vouloir mettre à jour l'événement ?");
//
//    Optional<ButtonType> result = confirmationAlert.showAndWait();
//    if (result.isPresent() && result.get() == ButtonType.OK) {
//        Event updatedEvent = new Event();
//        updatedEvent.setIdEvent(eventId);
//        updatedEvent.setNomEvent(newNomEvent);
//        updatedEvent.setLieuEvent(newLieuEvent);
//        updatedEvent.setDurée(newDuree);
//        updatedEvent.setDateDebutEvent(java.sql.Date.valueOf(newDateDebut));
//        updatedEvent.setPrixTicket(Double.parseDouble(newPrixTicket));
//        updatedEvent.setNbmaxParticipant(Integer.parseInt(newNbMaxParticipants));
//        updatedEvent.setTypeEvent(newTypeEvent);
//        updatedEvent.setDescriptionEvent(newDescriptionEvent);
//        updatedEvent.setImage(newImagePath);
//
//        Eventservice eventService = new Eventservice();
//        eventService.updateEvent(updatedEvent, userId);
//        
//      
//        
//
//
//        showAlert(Alert.AlertType.INFORMATION, "Succès", "L'événement a été mis à jour avec succès.");
//    
//        // L'utilisateur a annulé la mise à jour, vous pouvez ajouter du code ici si nécessaire
//    }
//}


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               eventtypeup.getItems().addAll("Conférences académiques", "Compétitions académiques", "Événements culturels", "Activités sportives", "Événements de loisir");

//         paginat.setPageFactory(this::changePage);
            // menuDisplayCard();
       
    }    











}
