/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;

/**
 *
 * @author Mekni
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Models.Event;
import GUI_Events.AfficheDetailsController;
import javafx.scene.input.MouseEvent;
public class CardEventController implements Initializable{

    @FXML
    private AnchorPane cardevent;
   @FXML
    private Label EventName;
      @FXML
    private Label pricee;
        @FXML
    private ImageView imageevent;

 
    private Image image;
  
//    public void setData(Event eventData) {
//  //  this.eventData = eventData; // Assurez-vous que vous avez un attribut 'eventdata' dans votre contrôleur
//
//    EventName.setText(eventData.getNomEvent()); // Remplissez le nom de l'événement
//    pricee.setText(String.valueOf(eventData.getPrixTicket())); // Remplissez le prix du ticket en tant que chaîne
//    String imagePath = eventData.getImage(); // Récupérez le chemin de l'image de l'événement
//    Image image = new Image("file:" + imagePath, 190, 100, false, true); // Chargez l'image depuis le chemin
//    imageevent.setImage(image); // Affichez l'image
//}
// 
//    
//    
//    
     private int idEvent;
    @FXML
    private Button details;
    @FXML
    private Button join;

 

    
public void setData(int eventId, String eventName, double ticketPrice, String imagePath) {
    this.idEvent = eventId; // Définissez l'ID de l'événement

    EventName.setText(eventName); // Remplissez le nom de l'événement
    pricee.setText(String.valueOf(ticketPrice)); // Remplissez le prix du ticket en tant que chaîne

    // Chargez l'image depuis le chemin de l'image
    Image image = new Image("file:" + imagePath, 190, 100, false, true);
    imageevent.setImage(image); // Affichez l'image
}

   

   

// Méthode pour initialiser l'ID de l'événement
//public void initialize(int eventId) {
  //  this.idEvent = idEvent;
//}

    
    
    
// Méthode pour obtenir l'ID de l'événement
public Integer getIdEvent() {
    return idEvent;
}
    
    
   // AfficheEventController aa =new AfficheEventController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private int selectedEventId = -1;

    // Vous pouvez ajouter des méthodes de gestion d'événements ici
//@FXML
//private void showDetails(ActionEvent event) {
//    // Obtenez l'ID de l'événement à partir de la carte d'événement sélectionnée
//    int idEvent = getIdEvent();
//    selectedEventId = idEvent;
//System.out.println("ID de l'événement : " + idEvent);// Mettez à jour selectedEventId avec l'ID de l'événement sélectionné
//    try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheDetails.fxml"));
//        Parent root = loader.load();
//        AfficheDetailsController detailsController = loader.getController();
//
//        // Chargez les détails de l'événement en fonction de l'ID
//        detailsController.loadEventDetails(idEvent);
//         detailsController.loadEventDetails(idEvent);
//       detailsController.getJoinanchro().setVisible(false);
//        detailsController.getJoinanchro().setManaged(false);
//        
//        detailsController.getMainformaffiche().setVisible(true);
//        detailsController.getMainformaffiche().setManaged(true);
//
//        // Affichez la vue de détails
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
//}



    
    
    
    @FXML
private void showDetails(ActionEvent event) {
    // Obtenez l'ID de l'événement à partir de la carte d'événement sélectionnée
    int idEvent = getIdEvent();
    selectedEventId = idEvent;
System.out.println("ID de l'événement : " + idEvent);// Mettez à jour selectedEventId avec l'ID de l'événement sélectionné
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsEvent.fxml"));
        Parent root = loader.load();
        DetailsEventController detailsController = loader.getController();

        // Chargez les détails de l'événement en fonction de l'ID
        detailsController.loadEventDetails(idEvent);
        // detailsController.loadEventDetails(idEvent);
      // detailsController.getJoinanchro().setVisible(false);
        //detailsController.getJoinanchro().setManaged(false);
        
        //detailsController.getMainformaffiche().setVisible(true);
        //detailsController.getMainformaffiche().setManaged(true);

        // Affichez la vue de détails
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
  
    
    
    
    
   
    
    

//@FXML
//private void Join(ActionEvent event) {
//    // Obtenez l'ID de l'événement à partir de la carte d'événement sélectionnée
//    int idEvent = getIdEvent();
//    selectedEventId = idEvent;
//System.out.println("ID de l'événement : " + idEvent);// Mettez à jour selectedEventId avec l'ID de l'événement sélectionné
//    try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheDetails.fxml"));
//        Parent root = loader.load();
//        AfficheDetailsController detailsController = loader.getController();
//
//        // Chargez les détails de l'événement en fonction de l'ID
//        detailsController.loadEventDetails(idEvent);
//
//        // Affichez la vue de détails
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
//}
//
//AfficheDetailsController aff =new AfficheDetailsController();

   // Vous pouvez ajouter des méthodes de gestion d'événements ici
@FXML
private void Join(ActionEvent event) {
    // Obtenez l'ID de l'événement à partir de la carte d'événement sélectionnée
    int idEvent = getIdEvent();
    selectedEventId = idEvent;
System.out.println("ID de l'événement : " + idEvent);// Mettez à jour selectedEventId avec l'ID de l'événement sélectionné
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheDetails.fxml"));
        Parent root = loader.load();
        AfficheDetailsController detailsController1 = loader.getController();
 detailsController1.loadEventDetails22(idEvent);
        // Chargez les détails de l'événement en fonction de l'ID
      //  detailsController.loadEventDetails(idEvent);
      // detailsController.getJoinanchro().setVisible(true);
        //detailsController.getJoinanchro().setManaged(true);
        
        //detailsController.getMainformaffiche().setVisible(false);
        //detailsController.getMainformaffiche().setManaged(false);

        // Affichez la vue de détails
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}






//public void setSelectedEventId(int eventId) {
//    selectedEventId = eventId;
//}
//public void setSelectedEventId(int eventId) {
//    selectedEventId = eventId;
//}


    public void setEventId(int eventId) {
        this.idEvent = eventId;
    }

    
    
    public void setSelectedEventId(int eventId) {
        this.idEvent = eventId;
    }
    

    
    
    
}
