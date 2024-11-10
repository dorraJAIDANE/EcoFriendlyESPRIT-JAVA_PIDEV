/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Models.Event;
import Services.Eventservice;
import Services.Participationservice;

/**
 * FXML Controller class
 *
 * @author Mekni
 */
public class DetailsEventController implements Initializable {

    @FXML
    private AnchorPane mainformaffiche;
    @FXML
    private AnchorPane affimaimp;
    @FXML
    private ImageView affimaimpp;
    @FXML
    private Label affname;
    @FXML
    private Label affdate;
    @FXML
    private Label affduration;
    @FXML
    private Label afflocation;
    @FXML
    private Label affticketor;
    @FXML
    private Label affnbmaxpar;
    @FXML
    private Label affeventtype;
    @FXML
    private Label affdesc;
    /**
     * Initializes the controller class.
     */
    
    
    
    
    
   // AfficheEventController cc=new AfficheEventController();
    
     private Event event;
    int selectedEventId;
    @FXML
    private Label affNumberOfParticipants;
   public void loadEventDetails(int eventId) {
    System.out.println("ID de l'événement : " + eventId);
    Eventservice eventService = new Eventservice();
    Event event = eventService.getEventById(eventId);

    if (event != null) {
        affname.setText(event.getNomEvent());
        affduration.setText(event.getDurée());
        afflocation.setText(event.getLieuEvent());
        affticketor.setText(String.valueOf(event.getPrixTicket()));
        affnbmaxpar.setText(String.valueOf(event.getNbmaxParticipant()));
        affeventtype.setText(event.getTypeEvent());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (event.getDateDebutEvent() != null) {
            String formattedDate = dateFormat.format(event.getDateDebutEvent());
            affdate.setText(formattedDate);
        } else {
            affdate.setText("Date non disponible");
        }

//        if (event.getDatecreation() != null) {
//    String formattedDate1 = dateFormat.format(event.getDatecreation());
//    datecrea.setText(formattedDate1);
//} else {
//    System.out.println("Date de création n'est pas définie.");
//    datecrea.setText("Date non disponible");
//}
Participationservice participationService = new Participationservice();
int participations = participationService.countParticipationsByEventId(eventId);

// Affichez le nombre de participations dans votre interface utilisateur (par exemple, dans un Label)
affNumberOfParticipants.setText("Nombre de participants : " + participations);
        affdesc.setText(event.getDescriptionEvent());

        selectedEventId = event.getIdEvent();

        String imagePath = event.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            Image image = new Image("file:" + imagePath, 1000, 500, false, true);
            affimaimpp.setImage(image);
        } else {
            // Gérez le cas où le chemin de l'image est manquant
            // Par exemple, vous pouvez afficher une image par défaut ou masquer l'image
            //affname.setText(event.getNomEvent());
        }
    } else {
        // Gérez le cas où l'événement n'a pas été trouvé par son ID
        // Par exemple, affichez un message d'erreur dans les champs de texte
        // Affichez une image par défaut ou masquez l'image
       // affimaimp.setImage();
    }
}

    
//    
//public void loadEventDetails(int eventId) {
//    System.out.println("ID de l'événement : " + eventId); // Imprimez l'ID de l'événement
//    Eventservice eventService = new Eventservice();
//    Event event = eventService.getEventById(eventId);
//
//    if (event != null) {
//        // Assurez-vous que l'objet event n'est pas nul
//        affname.setText(event.getNomEvent());
//        affduration.setText(event.getDurée());
//        afflocation.setText(event.getLieuEvent());
//        affticketor.setText(String.valueOf(event.getPrixTicket()));
//        affnbmaxpar.setText(String.valueOf(event.getNbmaxParticipant()));
//        affeventtype.setText(event.getTypeEvent());
//        // Utilisez un format de date pour afficher correctement la date
//       // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//       // affdate.setText(LocalDate.parse(dateFormat.format(event.getDateDebutEvent().getTime()), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//       SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//
//if (event.getDateDebutEvent() != null) {
//    String formattedDate = dateFormat.format(event.getDateDebutEvent());
//    
//    affdate.setText(formattedDate);
//    
//}
//SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
//if (event.getDatecreation()!= null) {
//        String formattedDate1 = dateFormat1.format(event.getDatecreation());
//        datecrea.setText(formattedDate1);
//    
//} else {
//    // Gérez le cas où la date est nulle ou incorrecte
//    affdate.setText("Date non disponible"); // Ou tout autre texte par défaut
//}
//        affdesc.setText(event.getDescriptionEvent());
//       
//         selectedEventId = event.getIdEvent(); 
//        // Chargez l'image depuis le chemin de l'événement
//        String imagePath = event.getImage();
//        if (imagePath != null && !imagePath.isEmpty()) {
//            Image image = new Image("file:" + imagePath, 1000, 500, false, true);
//            affimaimpp.setImage(image);
//        } else {
//            // Gérez le cas où le chemin de l'image est manquant
//            // Par exemple, vous pouvez afficher une image par défaut ou masquer l'image
//            //affname.setText(event.getNomEvent());
//        }
//    } else {
//        // Gérez le cas où l'événement n'a pas été trouvé par son ID
//        // Par exemple, affichez un message d'erreur dans les champs de texte
//        
//        // Affichez une image par défaut ou masquez l'image
//       // affimaimp.setImage();
//    }
//}
//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}


