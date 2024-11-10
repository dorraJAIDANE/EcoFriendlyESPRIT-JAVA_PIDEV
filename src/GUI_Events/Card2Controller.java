/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Models.Event;
import Services.Eventservice;

/**
 * FXML Controller class
 *
 * @author Mekni
 */
public class Card2Controller implements Initializable {

    @FXML
    private AnchorPane cardevent;
    @FXML
    private Label evname1;
    @FXML
    private Label date1;
    @FXML
    private Label prix1;
    @FXML
    private Label lieu1;
    @FXML
    private Label nb1;
    @FXML
    private Label type1;
    @FXML
    private Button delete1;
    @FXML
    private Button update1;
    @FXML
    private Label description1;
    @FXML
    private ImageView im1;

    
    
    private Image image;
    
    private int idEvent;
    @FXML
    private Label prix11;
    @FXML
    private Label nb11;
    @FXML
    private Button discount1;
    @FXML
    private Label dureee;
public void setData1(int eventId, String eventName, double ticketPrice, String imagePath ,String lieu, int nbrmax, String type, String description ,Date date , String Dur) {
    this.idEvent = eventId; // Définissez l'ID de l'événement

    evname1.setText(eventName); // Remplissez le nom de l'événement
    prix1.setText(String.valueOf(ticketPrice)); // Remplissez le prix du ticket en tant que chaîne
   lieu1.setText(eventName);
     nb1.setText(String.valueOf(nbrmax));
      type1.setText(type);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Le format de date souhaité
    date1.setText(dateFormat.format(date)); // Formatez la date en chaîne et affichez-la
     description1.setText(description); 
     dureee.setText(Dur);
    // Chargez l'image depuis le chemin de l'image
    Image image = new Image("file:" + imagePath, 190, 100, false, true);
    im1.setImage(image); // Affichez l'image
}

      public void setEventId(int eventId) {
        this.idEvent = eventId;
    }

      

    
    public void setSelectedEventId(int eventId) {
        this.idEvent = eventId;
    }
    
public Integer getIdEvent() {
    return idEvent;
}
    
    
 private AjoutController ajoutController;

    public Card2Controller(AjoutController ajoutController) {
        this.ajoutController = ajoutController;
    }

    private int selectedEventId = -1;
    
    
    
     public void setAjoutController(AjoutController ajoutController) {
        this.ajoutController = ajoutController;
    }
    
    
     public Card2Controller() {
        // Vous pouvez initialiser des variables ou effectuer d'autres opérations ici si nécessaire
    }
    
    
    

@FXML
private void deleteEvent(ActionEvent event) throws IOException {
    int idEvent = getIdEvent();
    selectedEventId = idEvent;
    int eventIdToDelete = selectedEventId; // Récupérez l'ID de l'événement que vous souhaitez supprimer

    if (eventIdToDelete == -1) {
        System.out.println("-11111111111");
    }

    // Affichez une boîte de dialogue de confirmation pour confirmer la suppression
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation");
    confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir supprimer cet événement ?");
    confirmationDialog.setContentText("Cette action ne peut pas être annulée.");

    // Ajoutez des boutons pour Confirmer ou Annuler
    ButtonType confirmButtonType = new ButtonType("Confirmer", ButtonData.OK_DONE);
    ButtonType cancelButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
    confirmationDialog.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

    Optional<ButtonType> result = confirmationDialog.showAndWait();

    if (result.isPresent() && result.get() == confirmButtonType) {
        // L'utilisateur a confirmé, appelez la méthode de service pour supprimer l'événement
        Eventservice eventService = new Eventservice();
        eventService.deleteEvent(eventIdToDelete);

        // Affichez un message de réussite
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Suppression d'événement");
        successAlert.setHeaderText("Suppression de l'événement réussie");
        successAlert.showAndWait();

        // Réinitialisez les champs de texte et les contrôles d'interface utilisateur après la suppression
        // ...
    } else {
        // L'utilisateur a annulé l'opération, ne faites rien
    }
    
             
    
}

   
    
    
    
      private int eventToUpdate;

    public void setEventToUpdate(int eventtoupdate) {
        this.eventToUpdate = eventtoupdate;
    }
    
    
        @FXML
private void updateselected(ActionEvent event) throws IOException {
    // Obtenez l'ID de l'événement à partir de la carte d'événement sélectionnée
    int idEvent = getIdEvent();
    selectedEventId = idEvent;
   
System.out.println("ID de l'événement : " + idEvent);// Mettez à jour selectedEventId avec l'ID de l'événement sélectionné
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout.fxml"));
        Parent root = loader.load();
        AfficheEventController afficheDetailsController = loader.getController();

          afficheDetailsController.setEventToUpdate(selectedEventId); 
      //  afficheDetailsController.loadEventDetails22(idEvent);
        // detailsController.loadEventDetails22(idEvent);
      // detailsController.getJoinanchro().setVisible(false);
        //detailsController.getJoinanchro().setManaged(false);
        
        //detailsController.getMainformaffiche().setVisible(true);
        //detailsController.getMainformaffiche().setManaged(true);

        if (eventToUpdate != -1) {
            // Your existing code for loading the event details and showing the form.
        } else {
            System.out.println("Invalid event ID: " + eventToUpdate);
        }
        // Affichez la vue de détails
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
       
             
    }
}
  
    
    
     private AfficheDetailsController afficheDetailsController;
    
    public Card2Controller(AfficheDetailsController afficheDetailsController) {
        this.afficheDetailsController = afficheDetailsController;
    }
    
    
  @FXML
private void DiscountButtonClick(ActionEvent event) {
   int idEvent = getIdEvent();
    selectedEventId = idEvent;
    // Appelez la méthode isFirstPeriodOver pour vérifier si la première période est terminée
    Eventservice eventservice = new Eventservice();
    boolean isFirstPeriodOver = eventservice.isFirstPeriodOver(selectedEventId);

    if (isFirstPeriodOver) {
        // La première période est terminée, demandez la confirmation de l'utilisateur
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir appliquer la réduction de prix ?");
        confirmationDialog.setContentText("Cette action ne peut pas être annulée.");

        // Ajoutez des boutons pour Confirmer ou Annuler
        ButtonType confirmButtonType = new ButtonType("Confirmer", ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        confirmationDialog.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == confirmButtonType) {
            // L'utilisateur a confirmé, appliquez la réduction de prix
            eventservice.updateTicketPrice(selectedEventId);

            // Affichez un message de réussite
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Réduction de prix");
            successAlert.setHeaderText("La réduction de prix a été appliquée avec succès.");
            successAlert.showAndWait();
        } else {
            // L'utilisateur a annulé l'opération, ne faites rien
        }
    } else {
        // La première période n'est pas encore terminée, affichez un message à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Réduction de prix");
        alert.setHeaderText("La première période n'est pas encore terminée.");
        alert.showAndWait();
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

   

    
}
