package GUI_Events;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import Models.Event;
import Services.Eventservice;
import javafx.scene.control.cell.TextFieldListCell;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MyEventsController implements Initializable {

    @FXML
    private AnchorPane ancmyevents;
    @FXML
    private VBox vboxmyev;
    @FXML
    private GridPane gridmyev;

    
    ObservableList<Event> Cardlistdatamy = FXCollections.observableArrayList(); 
    private Eventservice eventService = new Eventservice();
    private void menuDisplayCard(int userId) {
        try {
            Cardlistdatamy.clear();

            // Utilisez le userId pour charger les événements de l'utilisateur connecté
            List<Event> userEvents = eventService.afficherEventsByUser(userId);

            Cardlistdatamy.addAll(userEvents);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    
    }
    
    
    
    public void menuDisplayCardmy(int userId) {
    try {
        // Utilisez le userId pour charger les événements de l'utilisateur connecté
        List<Event> userEvents = eventService.afficherEventsByUser(userId);
        ObservableList<Event> tempList = FXCollections.observableArrayList(userEvents);

        int row = 0;
        int column = 0;

        gridmyev.getRowConstraints().clear();
        gridmyev.getColumnConstraints().clear();

        for (int q = 0; q < tempList.size(); q++) {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("CardEvent.fxml"));

            AnchorPane pane = load.load();
            CardEventController card = load.getController();

            // Définir l'ID de l'événement
            card.setEventId(tempList.get(q).getIdEvent());

            // Passez les données nécessaires à la carte, y compris le chemin de l'image
            card.setData(tempList.get(q).getIdEvent() ,tempList.get(q).getNomEvent(), tempList.get(q).getPrixTicket(), tempList.get(q).getImage());

            if (column == 1) {
                column = 0;
                row += 1;
            }
            gridmyev.add(pane, column++, row);
        }

        // Supprimez les données de la liste principale après avoir terminé l'itération
        Cardlistdatamy.clear();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

    
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
//    private ListView<Event> listmyevents; // Utilisez le type d'objet Event ici, pas de wildcard
//
//    // Vous devez définir userId avec l'ID de l'utilisateur connecté
//   private int userId;
//
//    @Override
//   public void initialize(URL location, ResourceBundle resources) {
//       // Assurez-vous que userId est défini avec l'ID de l'utilisateur connecté
//        userId = 2; 
//
//       Eventservice eventService = new Eventservice();
//        List<Event> userEvents = eventService.afficherEventsByUser(userId);
//
//      // Convertissez la liste d'événements en ObservableList pour le ListView
//      ObservableList<Event> observableUserEvents = FXCollections.observableArrayList(userEvents);
// 
//          listmyevents.setCellFactory(param -> new ImageCell());
//
//      listmyevents.setItems(observableUserEvents);



      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         int userId = 1; // Remplacez ceci par la valeur appropriée

        menuDisplayCardmy(userId);
    }    
    

 
    
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    