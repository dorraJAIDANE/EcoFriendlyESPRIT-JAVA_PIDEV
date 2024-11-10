/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Models.Event;
import Services.Eventservice;
import Models.Event;
import GUI_Events.CardEventController ;
import java.sql.PreparedStatement;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import Models.Participation;
import Models.user;
import Services.Participationservice;
import Services.userService;
import Services.Participationservice;

/**
 * FXML Controller class
 *
 * @author Mekni
 */
public class AfficheDetailsController implements Initializable {

    private TextField affname;
    private TextField affduration;
    private TextField afflocation;
    private TextField affticketor;
    private TextField affnbmaxpar;
    private TextField affeventtype;
    private DatePicker affdate;
    private TextArea affdesc;
    private ImageView affimaimp;

    /**
     * Initializes the controller class.
     */
    
    
   
    private AnchorPane mainformaffiche;
    
    
    
    AfficheEventController cc=new AfficheEventController();
    
     private Event event;
    int selectedEventId;
    @FXML
    private ImageView imahgejoin;
    @FXML
    private Label labeleventnamejoin;
    @FXML
    private Label pricejoin;
    private AnchorPane main_form;
    private TextField upeventname;
    private TextField lieueventup;
    private TextField dureeup;
    private DatePicker datedebutup;
    private TextField ticketpriceup;
    private TextField nbmaxparticipationup;
    private ComboBox<String> eventtypeup = new ComboBox<>();
    private TextArea eventdescriptionup;
    private ImageView viewimage;
    @FXML
    private Button buttonpayer;
    
    
   
        

    
    
    
    
    
    
    
//    
//    
//    
//    
//    
public void loadEventDetails(int eventId) {
    System.out.println("ID de l'événement : " + eventId); // Imprimez l'ID de l'événement
    Eventservice eventService = new Eventservice();
    Event event = eventService.getEventById(eventId);

    if (event != null) {
        // Assurez-vous que l'objet event n'est pas nul
        affname.setText(event.getNomEvent());
        affduration.setText(event.getDurée());
        afflocation.setText(event.getLieuEvent());
        affticketor.setText(String.valueOf(event.getPrixTicket()));
        affnbmaxpar.setText(String.valueOf(event.getNbmaxParticipant()));
        affeventtype.setText(event.getTypeEvent());
        // Utilisez un format de date pour afficher correctement la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        affdate.setValue(LocalDate.parse(dateFormat.format(event.getDateDebutEvent().getTime()), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        affdesc.setText(event.getDescriptionEvent());
       
         selectedEventId = event.getIdEvent(); 
        // Chargez l'image depuis le chemin de l'événement
        String imagePath = event.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            Image image = new Image("file:" + imagePath, 1000, 500, false, true);
            affimaimp.setImage(image);
        } else {
            // Gérez le cas où le chemin de l'image est manquant
            // Par exemple, vous pouvez afficher une image par défaut ou masquer l'image
            //affname.setText(event.getNomEvent());
        }
    } else {
        // Gérez le cas où l'événement n'a pas été trouvé par son ID
        // Par exemple, affichez un message d'erreur dans les champs de texte
        clear();
        // Affichez une image par défaut ou masquez l'image
       // affimaimp.setImage();
    }
}



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
    
    
    
    

               
                        
                        
                        
                        
                        
private void showAlert(AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           eventtypeup.getItems().addAll("Conférences académiques", "Compétitions académiques", "Événements culturels", "Activités sportives", "Événements de loisir");

    }    


    
    int selectedeven=-1;
   public void loadEventDetails22(int eventId) {
    
       System.out.println("ID de l'événement : " + eventId); // Imprimez l'ID de l'événement
        selectedeven=eventId;
    Eventservice eventService = new Eventservice();
    Event event = eventService.getEventById(eventId);
   
    if (event != null) {
        // Assurez-vous que l'objet event n'est pas nul
        // Chargez l'image depuis le chemin de l'événement
        String imagePath = event.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            Image image = new Image("file:" + imagePath, 1000, 500, false, true);
            // Remplissez les trois éléments avec les valeurs de l'événement
            imahgejoin.setImage(image);
            //imahgejoin.setFitWidth(300);
            imahgejoin.setFitHeight(1000);
            labeleventnamejoin.setText(event.getNomEvent());
            pricejoin.setText(String.valueOf(event.getPrixTicket()));
        } else {
            // Gérez le cas où le chemin de l'image est manquant
            // Par exemple, vous pouvez afficher une image par défaut ou masquer l'image
            imahgejoin.setImage(null);
            labeleventnamejoin.setText("Image non disponible");
            pricejoin.setText("Prix non disponible");
        }
    } else {
        // Gérez le cas où l'événement n'a pas été trouvé par son ID
        // Par exemple, affichez un message d'erreur dans les champs de texte
        imahgejoin.setImage(null);
        labeleventnamejoin.setText("Événement non trouvé");
        pricejoin.setText("");
    
    }
   }


    public void setEventToUpdate(int selectedEventId) {
        this.selectedEventId = selectedEventId;
    }
    public void updateEvent(ActionEvent event) {
    int eventId = selectedEventId;
    int userId = 2;

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

    
//    @FXML
//    public void updateEvent(ActionEvent event) {
//      //  int eventtoupdate = 0;
//    // Récupérez l'ID de l'événement et l'ID de l'utilisateur
//    int eventId = selectedEventId;
//    int userId = 2;
//
//    // Récupérez les nouvelles informations de l'événement depuis les champs de texte et les contrôles de l'interface utilisateur
//    String newNomEvent = upeventname.getText();
//    String newLieuEvent = lieueventup.getText();
//    String newDuree = dureeup.getText();
//    LocalDate newDateDebut = datedebutup.getValue();
//    String newPrixTicket = ticketpriceup.getText();
//    String newNbMaxParticipants = nbmaxparticipationup.getText();
//    String newTypeEvent = eventtypeup.getText();
//    String newDescriptionEvent = eventdescriptionup.getText();
//    String newImagePath = getData.path;
//
//    // Vérifiez que tous les champs obligatoires sont remplis
//    if (newNomEvent.isEmpty() || newLieuEvent.isEmpty() || newDuree.isEmpty() || newDateDebut == null
//        || newPrixTicket.isEmpty() || newNbMaxParticipants.isEmpty() || newTypeEvent.isEmpty() || newDescriptionEvent.isEmpty() ) {
//        // Affichez une boîte de dialogue d'alerte pour informer l'utilisateur de remplir tous les champs
//        showAlert(AlertType.ERROR, "Champs vides", "Veuillez remplir tous les champs obligatoires.");
//        return; // Arrêtez la mise à jour si des champs sont vides
//    }
//
//    // Créez l'objet Event mis à jour
//    Event updatedEvent = new Event();
//    updatedEvent.setIdEvent(eventId);
//    updatedEvent.setNomEvent(newNomEvent);
//    updatedEvent.setLieuEvent(newLieuEvent);
//    updatedEvent.setDurée(newDuree);
//    updatedEvent.setDateDebutEvent(java.sql.Date.valueOf(newDateDebut));
//    updatedEvent.setPrixTicket(Double.parseDouble(newPrixTicket));
//    updatedEvent.setNbmaxParticipant(Integer.parseInt(newNbMaxParticipants));
//    updatedEvent.setTypeEvent(newTypeEvent);
//    updatedEvent.setDescriptionEvent(newDescriptionEvent);
//    updatedEvent.setImage(newImagePath);
//
//    // Appelez la méthode de service pour mettre à jour l'événement
//    Eventservice eventService = new Eventservice();
//    eventService.updateEvent(updatedEvent, userId);
//
//    showAlert(AlertType.INFORMATION, "Succès", "L'événement a été mis à jour avec succès.");
//    
//    
//}

    
    
    
    
    
 
   
    @FXML
    private AnchorPane afficheform;
    @FXML
    private AnchorPane joinanchro;

                        

      
@FXML
    public void payer(ActionEvent event) {
    int eventId = selectedeven;
    int userId = 1; // Remplacez cela par l'ID de l'utilisateur actuel

    System.out.println("ID de l'événement à payer : " + eventId); // Affichez l'ID de l'événement

    if (eventId == -1) {
        showAlert(Alert.AlertType.ERROR, "Événement non sélectionné", "Veuillez sélectionner un événement à payer.");
        return;
    }

    // Déclarez les variables qrCodeData et filePath
    String qrCodeData = null;
    String filePath = null;

    // Appelez la méthode de service pour obtenir l'objet Event
    Eventservice eventService = new Eventservice();
    Event event1 = eventService.getEventById(eventId);

    if (event == null) {
        // Gérez le cas où l'événement n'existe pas (par exemple, affichez une alerte ou lancez une exception).
        showAlert(Alert.AlertType.ERROR, "Événement introuvable", "L'événement avec l'ID " + eventId + " n'existe pas.");
        return;
    }
 Participationservice part1 = new Participationservice();
    // Vérifiez si le nombre de participations actuelles est inférieur au nombre maximum autorisé
     int currentParticipations = part1.countParticipationsByEventId(eventId);

    if (currentParticipations >= event1.getNbmaxParticipant()) {
        showAlert(Alert.AlertType.ERROR, "Événement complet", "L'événement a atteint son nombre maximum de participations.");
        return;
    }

    // Appelez la méthode de service pour obtenir le montant du portefeuille de l'utilisateur
    userService userService = new userService();
    double prixTicket = eventService.getPrixTicketById(eventId);
    double userWallet = userService.getUserWalletById(userId);

    System.out.println("Prix du ticket : " + prixTicket);

    if (userWallet >= prixTicket) {
        // L'utilisateur a suffisamment de fonds pour payer le ticket de l'événement

        // Créez une boîte de dialogue de confirmation
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation de paiement");
        confirmationDialog.setHeaderText("Confirmez le paiement ?");
        confirmationDialog.setContentText("Voulez-vous vraiment effectuer le paiement de l'événement ?");

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationDialog.getButtonTypes().setAll(okButton, cancelButton);

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == okButton) {
            // Mise à jour du portefeuille de l'utilisateur
            double newWalletAmount = userWallet - prixTicket;
            userService.updateUserWallet(userId, newWalletAmount);
            System.out.println("Nouveau solde du portefeuille après le paiement : " + newWalletAmount);

            // Le paiement a réussi, vous pouvez afficher un message de confirmation
            showAlert(Alert.AlertType.INFORMATION, "Paiement réussi", "Le paiement de l'événement a été effectué avec succès.");

            // Maintenant, générez une participation
            Participationservice part = new Participationservice();
            Participation participation = new Participation();
            participation.setEvent(event1);
            String eventName = event1.getNomEvent();
            participation.setuser(userService.getUserById(userId));
            part.ajouterParticipation(participation);

            // Générez un code QR pour cette participation (comme vous l'avez fait précédemment)

            String username = userService.getUsernameById(userId);

            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "EventQr";
            String qrCodeFileName = username + "-" + eventName + ".png";

            File qrCodeFile = new File(desktopPath, qrCodeFileName);
            int fileSuffix = 1;
            while (qrCodeFile.exists()) {
                // Si le fichier existe, ajoutez un suffixe numérique pour le rendre unique
                qrCodeFileName = username + "-" + eventName + "-" + fileSuffix + ".png";
                qrCodeFile = new File(desktopPath, qrCodeFileName);
                fileSuffix++;
            }

            String qrCodeFilePath = desktopPath + File.separator + qrCodeFileName;

            part.generateQRCode(username + "-" + eventName, qrCodeFilePath);

            participation.setCodeQR(qrCodeFilePath);

            // Maintenant, ajoutez la participation à la base de données
         //   part.ajouterParticipation(participation);
        }
    } else {
        // L'utilisateur n'a pas suffisamment de fonds pour payer le ticket
        showAlert(Alert.AlertType.ERROR, "Solde insuffisant", "Votre solde de portefeuille est insuffisant pour effectuer le paiement.");
    
}
}
    
    
    
    
    
    
    //AfficheEventController aa= new AfficheEventController();
private void deleteEvent(ActionEvent event) {
    int eventIdToDelete = selectedEventId; // Récupérez l'ID de l'événement que vous souhaitez supprimer
boolean deletionSucceeded=false;
    if (eventIdToDelete == -1) {
        // Affichez un message d'erreur ou effectuez une action pour indiquer qu'aucun événement n'est sélectionné
        return;
    }

    // Appelez la méthode de service pour supprimer l'événement
    Eventservice eventService = new Eventservice();
     eventService.deleteEvent(eventIdToDelete);

    // Créez une boîte de dialogue pour afficher le résultat de la suppression
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Suppression d'événement");
alert.setHeaderText("Suppression de l'évenement réussie");
    
    alert.showAndWait();
    
//clear();
    // Réinitialisez les champs de texte et les contrôles d'interface utilisateur après la suppression
     
}


 public void clear(){
     affname.clear();
    affduration.clear();
    afflocation.clear();
    affticketor.clear();
    affnbmaxpar.clear();
    affeventtype.clear();
    affdate.setValue(null);
    affdesc.clear();
    affimaimp.setImage(null);
 }

 
////////////////////paiement ///////////////////
// @FXML
//    public void payer(ActionEvent event) {
//    int eventId = selectedeven;
//    int userId = 1; // Remplacez cela par l'ID de l'utilisateur actuel
//
//    System.out.println("ID de l'événement à payer : " + eventId); // Affichez l'ID de l'événement
//
//    if (eventId == -1) {
//        showAlert(AlertType.ERROR, "Événement non sélectionné", "Veuillez sélectionner un événement à payer.");
//        return;
//    }
//
//    // Déclarez les variables qrCodeData et filePath
//    String qrCodeData = null;
//    String filePath = null;
//
//    // Appelez la méthode de service pour obtenir l'objet Event
//    Eventservice eventService = new Eventservice();
//    Event event1 = eventService.getEventById(eventId);
//
//    if (event == null) {
//        // Gérez le cas où l'événement n'existe pas (par exemple, affichez une alerte ou lancez une exception).
//        showAlert(AlertType.ERROR, "Événement introuvable", "L'événement avec l'ID " + eventId + " n'existe pas.");
//        return;
//    }
// Participationservice part1 = new Participationservice();
//    // Vérifiez si le nombre de participations actuelles est inférieur au nombre maximum autorisé
//     int currentParticipations = part1.countParticipationsByEventId(eventId);
//
//    if (currentParticipations >= event1.getNbmaxParticipant()) {
//        showAlert(AlertType.ERROR, "Événement complet", "L'événement a atteint son nombre maximum de participations.");
//        return;
//    }
//
//    // Appelez la méthode de service pour obtenir le montant du portefeuille de l'utilisateur
//    Userservice userService = new Userservice();
//    double prixTicket = eventService.getPrixTicketById(eventId);
//    double userWallet = userService.getUserWalletById(userId);
//
//    System.out.println("Prix du ticket : " + prixTicket);
//
//    if (userWallet >= prixTicket) {
//        // L'utilisateur a suffisamment de fonds pour payer le ticket de l'événement
//
//        // Créez une boîte de dialogue de confirmation
//        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
//        confirmationDialog.setTitle("Confirmation de paiement");
//        confirmationDialog.setHeaderText("Confirmez le paiement ?");
//        confirmationDialog.setContentText("Voulez-vous vraiment effectuer le paiement de l'événement ?");
//
//        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
//        ButtonType cancelButton = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
//        confirmationDialog.getButtonTypes().setAll(okButton, cancelButton);
//
//        Optional<ButtonType> result = confirmationDialog.showAndWait();
//
//        if (result.isPresent() && result.get() == okButton) {
//            // Mise à jour du portefeuille de l'utilisateur
//            double newWalletAmount = userWallet - prixTicket;
//            userService.updateUserWallet(userId, newWalletAmount);
//            System.out.println("Nouveau solde du portefeuille après le paiement : " + newWalletAmount);
//
//            // Le paiement a réussi, vous pouvez afficher un message de confirmation
//            showAlert(AlertType.INFORMATION, "Paiement réussi", "Le paiement de l'événement a été effectué avec succès.");
//
//            // Maintenant, générez une participation
//            Participationservice part = new Participationservice();
//            Participation participation = new Participation();
//            participation.setEvent(event1);
//            String eventName = event1.getNomEvent();
//            participation.setUser(userService.getUserById(userId));
//            part.ajouterParticipation(participation);
//
//            // Générez un code QR pour cette participation (comme vous l'avez fait précédemment)
//
//            String username = userService.getUsernameById(userId);
//
//            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "EventQr";
//            String qrCodeFileName = username + "-" + eventName + ".png";
//
//            File qrCodeFile = new File(desktopPath, qrCodeFileName);
//            int fileSuffix = 1;
//            while (qrCodeFile.exists()) {
//                // Si le fichier existe, ajoutez un suffixe numérique pour le rendre unique
//                qrCodeFileName = username + "-" + eventName + "-" + fileSuffix + ".png";
//                qrCodeFile = new File(desktopPath, qrCodeFileName);
//                fileSuffix++;
//            }
//
//            String qrCodeFilePath = desktopPath + File.separator + qrCodeFileName;
//
//            part.generateQRCode(username + "-" + eventName, qrCodeFilePath);
//
//            participation.setCodeQR(qrCodeFilePath);
//
//            // Maintenant, ajoutez la participation à la base de données
//         //   part.ajouterParticipation(participation);
//        }
//    } else {
//        // L'utilisateur n'a pas suffisamment de fonds pour payer le ticket
//        showAlert(AlertType.ERROR, "Solde insuffisant", "Votre solde de portefeuille est insuffisant pour effectuer le paiement.");
//    
//}
//}
//

}


//
//@FXML
//public void payer(ActionEvent event) {
//    int eventId = selectedeven;
//    int userId = 1; // Remplacez cela par l'ID de l'utilisateur actuel
//
//    System.out.println("ID de l'événement zzzzzà payer : " + eventId); // Affichez l'ID de l'événement
//
//    if (eventId == -1) {
//        showAlert(AlertType.ERROR, "Événement non sélectionné", "Veuillez sélectionner un événement à payer.");
//        return;
//    }
//
//    // Appelez la méthode de service pour obtenir le prix du ticket de l'événement
//    Eventservice eventService = new Eventservice();
//    double prixTicket = eventService.getPrixTicketById(eventId);
//
//    // Appelez la méthode de service pour obtenir le montant du portefeuille de l'utilisateur
//    Userservice userService = new Userservice();
//    double userWallet = userService.getUserWalletById(userId);
//
//    System.out.println("Prix du ticket : " + prixTicket);
//
//    if (userWallet >= prixTicket) {
//        // L'utilisateur a suffisamment de fonds pour payer le ticket de l'événement
//        double newWalletAmount = userWallet - prixTicket;
//
//        // Mettez à jour le portefeuille de l'utilisateur avec la nouvelle valeur
//        userService.updateUserWallet(userId, newWalletAmount);
//
//        System.out.println("Nouveau solde du portefeuille après le paiement : " + newWalletAmount);
//
//        // Le paiement a réussi, vous pouvez afficher un message de confirmation
//        showAlert(AlertType.INFORMATION, "Paiement réussi", "Le paiement de l'événement a été effectué avec succès.");
//    } else {
//        // L'utilisateur n'a pas suffisamment de fonds pour payer le ticket
//        showAlert(AlertType.ERROR, "Solde insuffisant", "Votre solde de portefeuille est insuffisant pour effectuer le paiement.");
//    }
//}




//
//@FXML
//public void payer(ActionEvent event) {
//    int eventId = selectedeven;
//    int userId = 4; // Remplacez cela par l'ID de l'utilisateur actuel
//
//    System.out.println("ID de l'événement à payer : " + eventId); // Affichez l'ID de l'événement
//
//    if (eventId == -1) {
//        showAlert(AlertType.ERROR, "Événement non sélectionné", "Veuillez sélectionner un événement à payer.");
//        return;
//    }
//
//    // Déclarez les variables qrCodeData et filePath
//    String qrCodeData = null;
//    String filePath = null;
//
//    // Appelez la méthode de service pour obtenir le prix du ticket de l'événement
//    Eventservice eventService = new Eventservice();
//    double prixTicket = eventService.getPrixTicketById(eventId);
//
//    // Appelez la méthode de service pour obtenir le montant du portefeuille de l'utilisateur
//    Userservice userService = new Userservice();
//    double userWallet = userService.getUserWalletById(userId);
//
//    System.out.println("Prix du ticket : " + prixTicket);
//
//    if (userWallet >= prixTicket) {
//        // L'utilisateur a suffisamment de fonds pour payer le ticket de l'événement
//
//        // Créez une boîte de dialogue de confirmation
//        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
//        confirmationDialog.setTitle("Confirmation de paiement");
//        confirmationDialog.setHeaderText("Confirmez le paiement ?");
//        confirmationDialog.setContentText("Voulez-vous vraiment effectuer le paiement de l'événement ?");
//
//        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
//        ButtonType cancelButton = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
//        confirmationDialog.getButtonTypes().setAll(okButton, cancelButton);
//
//        Optional<ButtonType> result = confirmationDialog.showAndWait();
//
//        if (result.isPresent() && result.get() == okButton) {
//            // Mise à jour du portefeuille de l'utilisateur
//            double newWalletAmount = userWallet - prixTicket;
//            userService.updateUserWallet(userId, newWalletAmount);
//            System.out.println("Nouveau solde du portefeuille après le paiement : " + newWalletAmount);
//
//            // Le paiement a réussi, vous pouvez afficher un message de confirmation
//            showAlert(AlertType.INFORMATION, "Paiement réussi", "Le paiement de l'événement a été effectué avec succès.");
//
//            // Maintenant, générez une participation
//            Participationservice part = new Participationservice();
//            Participation participation = new Participation();
//            participation.setEvent(eventService.getEventById(eventId));
//            String eventName = eventService.getEventNameById(eventId);
//            System.out.println("ID de l'événement à payer : " + eventId); 
//            participation.setUser(userService.getUserById(userId));
//            part.ajouterParticipation(participation); // Utilisez la première instance que vous avez créée
//
//            // Générez un code QR pour cette participation
//            String username = userService.getUsernameById(userId);
//      //      String eventName = eventService.getEventNameById(eventId);
//
//System.out.println("eventName");
//System.out.println("username");
//
//            qrCodeData = username + "-" + eventName;
//           String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "EventQr";
//String qrCodeFileName = username + "-" + eventName + ".png";
//
//File qrCodeFile = new File(desktopPath, qrCodeFileName);
//int fileSuffix = 1;
//while (qrCodeFile.exists()) {
//    // Si le fichier existe, ajoutez un suffixe numérique pour le rendre unique
//    qrCodeFileName = username + "-" + eventName + "-" + fileSuffix + ".png";
//    qrCodeFile = new File(desktopPath, qrCodeFileName);
//    fileSuffix++;
//}
//
//
//
//String qrCodeFilePath = desktopPath + File.separator + qrCodeFileName;
//            
//            
//            
//            part.generateQRCode(qrCodeData, qrCodeFilePath);
//
//            participation.setCodeQR(qrCodeFilePath);
//
//            // Maintenant, ajoutez la participation à la base de données
//            part.ajouterParticipation(participation); // Utilisez la première instance que vous avez créée
//        }
//    } else {
//        // L'utilisateur n'a pas suffisamment de fonds pour payer le ticket
//        showAlert(AlertType.ERROR, "Solde insuffisant", "Votre solde de portefeuille est insuffisant pour effectuer le paiement.");
//    }
//}
//}









   
 //private Event event; // Assurez-vous que l'objet Event est correctement initialisé

// ...

//@FXML
//private void updateEventtt(ActionEvent actionEvent) {
//    // Récupérez les nouvelles informations de l'événement à partir des champs de texte et des contrôles de l'interface utilisateur
//    String newNomEvent = affname.getText();
//    String newLieuEvent = afflocation.getText();
//    String newDuree = affduration.getText();
//    LocalDate newDateDebut = affdate.getValue();
//    String newPrixTicket = affticketor.getText();
//    String newNbMaxParticipants = affnbmaxpar.getText();
//    String newTypeEvent = affeventtype.getText();
//    String newDescriptionEvent = affdesc.getText();
//
//    String newImagePath = getData.path; // Utilisez le chemin que vous avez obtenu à partir du FileChooser
//
//    // Vérifiez si l'événement actuel a une image existante et que le chemin de la nouvelle image est différent
//    if ((event.getImage() == null && newImagePath != null) || (event.getImage() != null && !event.getImage().equals(newImagePath))) {
//        // Le chemin de l'image a changé, mettez à jour l'image
//        event.setImage(newImagePath);
//    }
//
//    // Créez un objet Event avec les nouvelles informations
//    Event updatedEvent = new Event();
//    updatedEvent.setIdEvent(selectedEventId); // Assurez-vous que selectedEventId est correctement défini
//    updatedEvent.setNomEvent(newNomEvent);
//    updatedEvent.setLieuEvent(newLieuEvent);
//    updatedEvent.setDurée(newDuree);
//    updatedEvent.setDateDebutEvent(java.sql.Date.valueOf(newDateDebut));
//    updatedEvent.setPrixTicket(Double.parseDouble(newPrixTicket));
//    updatedEvent.setNbmaxParticipant(Integer.parseInt(newNbMaxParticipants));
//    updatedEvent.setTypeEvent(newTypeEvent);
//    updatedEvent.setDescriptionEvent(newDescriptionEvent);
//    updatedEvent.setImage(newImagePath);
//
//    // Appelez la méthode de service pour mettre à jour l'événement
//    Eventservice eventService = new Eventservice();
//    eventService.updateEvent(updatedEvent);
//
//    // Affichez un message de confirmation ou effectuez d'autres actions selon vos besoins
//    System.out.println("L'événement a été mis à jour avec succès !");
//}
//    ///////////////////////////////////////////////////
        
//       @FXML
//public void updateEvent(ActionEvent event) {
//    
//    int eventId = selectedEventId ; // Remplacez ceci par l'ID de l'événement que vous souhaitez mettre à jour
//    int userId = 2; // Remplacez ceci par l'ID de l'utilisateur connecté
//
//    // Récupérez les nouvelles informations de l'événement depuis les champs de texte et les contrôles de l'interface utilisateur
//    String newNomEvent = affname.getText();
//    String newLieuEvent = afflocation.getText();
//    String newDuree = affduration.getText();
//    LocalDate newDateDebut = affdate.getValue();
//    String newPrixTicket = affticketor.getText();
//    String newNbMaxParticipants = affnbmaxpar.getText();
//    String newTypeEvent = affeventtype.getText();
//    String newDescriptionEvent = affdesc.getText();
//    String newImagePath = getData.path; // Utilisez le chemin de l'image
//
//    Event updatedEvent = new Event();
//    updatedEvent.setIdEvent(eventId); // Assurez-vous de définir l'ID de l'événement que vous souhaitez mettre à jour
//    updatedEvent.setNomEvent(newNomEvent);
//    updatedEvent.setLieuEvent(newLieuEvent);
//    updatedEvent.setDurée(newDuree);
//    updatedEvent.setDateDebutEvent(java.sql.Date.valueOf(newDateDebut));
//    updatedEvent.setPrixTicket(Double.parseDouble(newPrixTicket));
//    updatedEvent.setNbmaxParticipant(Integer.parseInt(newNbMaxParticipants));
//    updatedEvent.setTypeEvent(newTypeEvent);
//    updatedEvent.setDescriptionEvent(newDescriptionEvent);
//    updatedEvent.setImage(newImagePath);
//
//    // Appelez la méthode de service pour mettre à jour l'événement
//    Eventservice eventService = new Eventservice();
//    eventService.updateEvent(updatedEvent, userId);
//
//    System.out.println("L'événement a été mis à jour avec succès !");
//}

                       // getData a =new getData();