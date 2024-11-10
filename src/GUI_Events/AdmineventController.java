/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Models.Event;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mekni
 */
public class AdmineventController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    
      MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    //Add 1
    @FXML
    private Button banadm;
    
    public ObservableList<Event> getEventsFromDatabase() {
    ObservableList<Event> events = FXCollections.observableArrayList();
    String req = "SELECT idEvent, nomEvent, typeEvent, Durée, PrixTicket, LieuEvent, nbmaxParticipant, valid FROM event"; // Inclure idEvent dans la requête SQL
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
            int eventId = rs.getInt("idEvent");
            events.add(event);
        }

        rs.close();
        ps.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return events;
}

    
    
        
    
//public ObservableList<Event> getEventsFromDatabase() {
//    ObservableList<Event> events = FXCollections.observableArrayList();
//    String req = "SELECT * FROM event";
//    try {
//        PreparedStatement ps = cnx.prepareStatement(req);
//        ResultSet rs = ps.executeQuery();
//
//        
//        
//        while (rs.next()) {
//            Event event = new Event(
//                    
//                rs.getString("nomEvent"),
//                    rs.getString("typeEvent"),
//                rs.getString("Durée"),
//                    rs.getDouble("PrixTicket"),
//                rs.getString("LieuEvent"),
//                
//                rs.getInt("nbmaxParticipant"),
//                
//                rs.getString("valid")
//            );
//            events.add(event);
//        }
//
//        rs.close();
//        ps.close();
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return events;
//}
    
    
    
      public void addEmployeeShowListData() {
    ObservableList<Event> eventList = getEventsFromDatabase();
        System.out.println(eventList);
    nomev.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
    dureadm.setCellValueFactory(new PropertyValueFactory<>("Durée"));
    Prixadm.setCellValueFactory(new PropertyValueFactory<>("PrixTicket"));
    Lieuadm.setCellValueFactory(new PropertyValueFactory<>("LieuEvent"));
    nbadm.setCellValueFactory(new PropertyValueFactory<>("nbmaxParticipant"));
    typeev.setCellValueFactory(new PropertyValueFactory<>("typeEvent"));
    // Use the 'valid' field to populate another column, e.g., addEmployee_colEmpValid
    validadm.setCellValueFactory(new PropertyValueFactory<>("valid"));

    admintab.setItems(eventList);

    for (Event event : eventList) {
        System.out.println("Event Name: " + event.getNomEvent() + ", Durée: " + event.getDurée());
    }
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

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addEmployeeShowListData();
    }
}


