/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;


import GUI.UserSession;
import static GUI.UserSession.getCurrentUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import javafx.stage.StageStyle;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mekni
 */
 
public class AjoutController implements Initializable {
 
    @FXML
    private TextField eventname;
    @FXML
    private TextField lieuevent;
    @FXML
    private TextField duree;
    @FXML
    private DatePicker datedebut;
    @FXML
    private TextField ticketprice;
    @FXML
    private TextField nbmaxparticipation;
    @FXML
    private ComboBox<String> eventtype = new ComboBox<>();
  
    @FXML
    private TextArea eventdescription;
    @FXML
    private Button importeer;
    @FXML
    private ImageView viewimage;
    @FXML
    private Button importeer1;
    @FXML
    private AnchorPane main_form;

    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    @FXML
    private Button consulter1;
    @FXML
    private Button clear1;
    @FXML
    private AnchorPane afficheevent;
    @FXML
    private AnchorPane ajout;
    @FXML
    private GridPane griddd;
    @FXML
    private GridPane gridmyev;
    @FXML
    private VBox vboxxx;
    @FXML
    private VBox vboxmyev;
   @FXML
    private Label Time;
    @FXML
    private Label nomeventpop;
    @FXML
    private Label currentnbpart;
    @FXML
    private Label favuser;
    @FXML
    private Label mosttype;
    @FXML
    private AnchorPane barconatin;
    @FXML
    private AnchorPane barcontainer;
    @FXML
    private StackedBarChart<String, Number> stackbar;
    @FXML
    private Button refresh;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private Button addev;
    @FXML
    private Button buttonDashboard;
    @FXML
    private Button buttonTransport;
    @FXML
    private Button buttonBiblio;
    @FXML
    private Button buttonEvent;
    @FXML
    private Button buttonServices;
    @FXML
    private Button buttonForum;
    @FXML
    private Button logout_btn;
    @FXML
    private Button dasborbprofile;
    @FXML
    private Label username;
    
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
    
        private static user currentUser=getCurrentUser();

    
    



    



//    @FXML
//    private void AjouterEvent1(ActionEvent Event) {
//        String nomEvent = eventname.getText();
//        String lieuEvent = lieuevent.getText();
//        String dureeEvent = duree.getText();
//        LocalDate dateDebut = datedebut.getValue();
//        String prixTicket = ticketprice.getText();
//        String nbMaxParticipants = nbmaxparticipation.getText();
//        String typeEvent = eventtype.getText();
//        String descriptionEvent = eventdescription.getText();
//          String imagePath = getData.path; // Récupérez le chemin de l'image sélectionnée
//        // Créez un objet Event avec les données récupérées
//        Event event = new Event();
//        event.setNomEvent(nomEvent);
//        event.setLieuEvent(lieuEvent);
//        event.setDurée(dureeEvent);
//        event.setDateDebutEvent(java.sql.Date.valueOf(dateDebut));
//        event.setPrixTicket(Double.parseDouble(prixTicket));
//        event.setNbmaxParticipant(Integer.parseInt(nbMaxParticipants));
//        event.setTypeEvent(typeEvent);
//        event.setDescriptionEvent(descriptionEvent);
//          event.setImage(imagePath);
//             
//      Userservice userService = new Userservice();
//int currentUserId = userService.getUserIdById(2);
//User currentUser = userService.getUserById(currentUserId);
//event.setUser(currentUser);
//
//
//          
//        Eventservice eventService = new Eventservice();
//        eventService.ajouterEvent1(event);
//       
//       
//        Clear();
//    }
//

    
//
//   @FXML
//    private void Sendsms(ActionEvent event) {
//         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        String toPhoneNumber = numero.getText(); // Numéro du destinataire au format "+1234567890"
//
//        String messageText = "Détails de la location du véhicule :" ;
//              
//
//        Message message =   Message.creator(
//      
//                new PhoneNumber(toPhoneNumber),
//                new PhoneNumber(FROM_PHONE_NUMBER),
//                messageText)
//                .create();
//
//        System.out.println("SID du message Twilio : " + message.getSid());
//    }



    @FXML
    private void AjouterEvent1(ActionEvent Event) throws IOException {
    // Récupérez les données depuis les champs de texte et les contrôles de l'interface utilisateur
    String nomEvent = eventname.getText();
    String lieuEvent = lieuevent.getText();
    String dureeEvent = duree.getText();
    LocalDate dateDebut = datedebut.getValue();
    String prixTicket = ticketprice.getText();
    String nbMaxParticipants = nbmaxparticipation.getText();
    String typeEvent =  (String) eventtype.getValue();
    String descriptionEvent = eventdescription.getText();
    String imagePath = getData.path; // Récupérez le chemin de l'image sélectionnée

    // Effectuez des contrôles de saisie
    if (nomEvent.isEmpty() || lieuEvent.isEmpty() || dureeEvent.isEmpty() || dateDebut == null ||
        prixTicket.isEmpty() || nbMaxParticipants.isEmpty() || typeEvent == null || descriptionEvent.isEmpty()) {
        showAlert(AlertType.ERROR, "Champs non remplis", "Veuillez remplir tous les champs.");
        return; // Arrêtez la création de l'événement si un champ est vide
    }

    if (nomEvent.length() < 8) {
        showAlert(AlertType.ERROR, "Erreur de saisie", "Le nom de l'événement doit comporter au moins 8 caractères.");
        return; // Arrêtez la création de l'événement si le nom est trop court
    }

    if (dateDebut.isBefore(LocalDate.now())) {
        showAlert(AlertType.ERROR, "Erreur de saisie", "La date de début de l'événement doit être une date future.");
        return; // Arrêtez la création de l'événement si la date n'est pas valide
    }

    // Créez un objet Event avec les données récupérées
    Event event = new Event();
    event.setNomEvent(nomEvent);
    event.setLieuEvent(lieuEvent);
    event.setDurée(dureeEvent);
    event.setDateDebutEvent(java.sql.Date.valueOf(dateDebut));
    event.setPrixTicket(Double.parseDouble(prixTicket));
    event.setNbmaxParticipant(Integer.parseInt(nbMaxParticipants));
    event.setTypeEvent(typeEvent);
    event.setDescriptionEvent(descriptionEvent);
    event.setImage(imagePath);
    event.setDatecreation(new java.sql.Date(System.currentTimeMillis()));

    userService userService = new userService();
    int currentUserId = userService.getUserIdById(2);
    user currentUser = userService.getUserById(currentUserId);
    event.setuser(currentUser);

    Eventservice eventService = new Eventservice();
    eventService.ajouterEvent11(event);

    showAlert(AlertType.INFORMATION, "Succès", "L'événement a été ajouté avec succès.");

    Clear();
   menuDisplayCard();
        
}

private void showAlert(AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}








    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == consulter1) {
            ajout.setVisible(false);
            afficheevent.setVisible(true);}
             if (event.getSource() == addev) {
            ajout.setVisible(true);
            afficheevent.setVisible(false);
        }
                    else{
                    
                    }
                
}





@FXML
    private void Clear() {
        eventname.clear();
        lieuevent.clear();
        duree.clear();
        datedebut.getEditor().clear();
        ticketprice.clear();
        nbmaxparticipation.clear();
        eventtype.setValue(null);

        eventdescription.clear();
        viewimage.setImage(null);
        getData.path = "";
    }

    




    public void afficherEventSelectionne(Event eventSelectionne) {
    if (eventSelectionne != null) {
        eventname.setText(eventSelectionne.getNomEvent());
        lieuevent.setText(eventSelectionne.getLieuEvent());
        duree.setText(eventSelectionne.getDurée());
        
        // Utilisez le formatage pour afficher correctement la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        datedebut.setValue(LocalDate.parse(dateFormat.format(eventSelectionne.getDateDebutEvent().getTime()), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        
        ticketprice.setText(String.valueOf(eventSelectionne.getPrixTicket()));
        nbmaxparticipation.setText(String.valueOf(eventSelectionne.getNbmaxParticipant()));
      //  eventtype.setText(eventSelectionne.getTypeEvent());
        //eventtype=PosteEquipe.getSelectionModel().getSelectedItem());
String selectedType = (String) eventtype.getSelectionModel().getSelectedItem();

        eventdescription.setText(eventSelectionne.getDescriptionEvent());

        // Chargez l'image depuis le chemin de l'événement
        String imagePath = eventSelectionne.getImage();
        Image image = new Image("file:" + imagePath, 190, 100, false, true);
        viewimage.setImage(image);
    } else {
        // Réinitialisez les champs si l'événement sélectionné est nul
        Clear();
    }
}


Event eventData;
    
    
    
    
    
    private int selectedEventId = -1; // Initialisez-le à une valeur qui n'aura pas de correspondance dans la base de données

    
    ObservableList<Event> Cardlistdata = FXCollections.observableArrayList(); 
  
    private AnchorPane anchroshoweven;
    
public AnchorPane getAnchroshoweven() {
    return anchroshoweven;
}

    
    public ObservableList<Event> AfficheEvent() {
    

    try {
        Statement st = cnx.createStatement();
        String sql = "SELECT * FROM event"; // Assurez-vous que votre table s'appelle "event"

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            // Créez un modèle Event à partir des données de la base de données
            Event event = new Event();

            // Récupérez toutes les colonnes de la table event
            event.setIdEvent(rs.getInt("idEvent"));
            event.setNomEvent(rs.getString("nomEvent"));
            event.setPrixTicket(rs.getDouble("PrixTicket"));
            event.setImage(rs.getString("image"));
            event.setDateDebutEvent(rs.getDate("dateDebutEvent"));
            event.setDurée(rs.getString("Durée"));
            event.setLieuEvent(rs.getString("LieuEvent"));
            event.setNbmaxParticipant(rs.getInt("nbmaxParticipant"));
            event.setTypeEvent(rs.getString("typeEvent"));
            event.setDescriptionEvent(rs.getString("descriptionEvent"));
            event.setDatecreation(rs.getDate("Datecreation"));
            Cardlistdata.add(event);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return Cardlistdata;
}


AfficheEventController aa = new AfficheEventController();
  
    
    public void menuDisplayCard() {
    try {
        ObservableList<Event> tempList = FXCollections.observableArrayList();
        tempList.addAll(AfficheEvent());

        int row = 0;
        int column = 0;

        griddd.getRowConstraints().clear();
        griddd.getColumnConstraints().clear();

        for (int q = 0; q < tempList.size(); q++) {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("CardEvent.fxml"));

            AnchorPane pane = load.load();
            CardEventController card = load.getController();

            // Définir l'ID de l'événement
            card.setEventId(tempList.get(q).getIdEvent());

            // Passez les données nécessaires à la carte, y compris le chemin de l'image
            card.setData(tempList.get(q).getIdEvent(), tempList.get(q).getNomEvent(), tempList.get(q).getPrixTicket(), tempList.get(q).getImage());

            if (column == 2) {
                column = 0;
                row += 1;
            }
            griddd.add(pane, column++, row);
        }

        // Supprimez les données de la liste principale après avoir terminé l'itération
        Cardlistdata.clear();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

     public void removeEventFromUI(Event event) {
    // Supprimez l'événement de la liste observable
    Cardlistdata.remove(event);
    
    // Rechargez la grille des cartes
    griddd.getChildren().clear(); // Supprimez toutes les cartes actuellement affichées

    // Réaffichez les cartes restantes après suppression
    menuDisplayCard();
}






    
//private void refreshEventList() {
//    // Appelez la méthode pour mettre à jour la liste des événements
//    menuDisplayCard();
//}
//    
//
//public void refreshEventList11() {
//    // Appelez la méthode pour mettre à jour la liste des événements
//    
//  
//  
//        menuDisplayCardmy(selectedEventId);
//}
//    







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
        List<Event> userEvents = eventService.afficherEventsByUser(currentUser.getIduser());
        ObservableList<Event> tempList = FXCollections.observableArrayList(userEvents);

        int row = 0;
        int column = 0;

        gridmyev.getRowConstraints().clear();
        gridmyev.getColumnConstraints().clear();

        for (int q = 0; q < tempList.size(); q++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Card2.fxml"));
            AnchorPane pane = loader.load();

            // Obtenez le contrôleur correct à partir de l'instance FXMLLoader
            Card2Controller cardController = loader.getController();

            // Définir l'ID de l'événement
            cardController.setEventId(tempList.get(q).getIdEvent());

            // Passez les données nécessaires à la carte, y compris le chemin de l'image
            cardController.setData1(tempList.get(q).getIdEvent(), tempList.get(q).getNomEvent(), tempList.get(q).getPrixTicket(), tempList.get(q).getImage(), tempList.get(q).getLieuEvent(), tempList.get(q).getNbmaxParticipant(), tempList.get(q).getTypeEvent(), tempList.get(q).getDescriptionEvent(), tempList.get(q).getDateDebutEvent(), tempList.get(q).getDurée());

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

    
    
    
    
//    public void menuDisplayCardmy(int userId) {
//    try {
//        // Utilisez le userId pour charger les événements de l'utilisateur connecté
//        List<Event> userEvents = eventService.afficherEventsByUser(userId);
//        ObservableList<Event> tempList = FXCollections.observableArrayList(userEvents);
//
//        int row = 0;
//        int column = 0;
//
//        gridmyev.getRowConstraints().clear();
//        gridmyev.getColumnConstraints().clear();
//
//        for (int q = 0; q < tempList.size(); q++) {
//            FXMLLoader load = new FXMLLoader();
//            load.setLocation(getClass().getResource("Card2.fxml"));
//
//            AnchorPane pane = load.load();
//            Card2Controller card1 = load.getController();
// card1.setAjoutController(this);
//            // Définir l'ID de l'événement
//            card1.setEventId(tempList.get(q).getIdEvent());
//
//            // Passez les données nécessaires à la carte, y compris le chemin de l'image
//            card1.setData1(tempList.get(q).getIdEvent(), tempList.get(q).getNomEvent(), tempList.get(q).getPrixTicket(), tempList.get(q).getImage(), tempList.get(q).getLieuEvent(), tempList.get(q).getNbmaxParticipant(), tempList.get(q).getTypeEvent(), tempList.get(q).getDescriptionEvent(), tempList.get(q).getDateDebutEvent(), tempList.get(q).getDurée());
//
//            if (column == 1) {
//                column = 0;
//                row += 1;
//            }
//            gridmyev.add(pane, column++, row);
//        }
//
//        // Supprimez les données de la liste principale après avoir terminé l'itération
//        Cardlistdatamy.clear();
//     
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
//}







 @FXML
 private void Refresh(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }





//    private void afficherEvenementsUtilisateurActuel(int userId) {
//    // Assurez-vous que userId est défini avec l'ID de l'utilisateur connecté
//    // userId = 2; // Remplacez 2 par l'ID de l'utilisateur connecté
//
//    Eventservice eventService = new Eventservice(); // Remplacez ServiceEvent par le nom de votre classe de service
//    List<Event> userEvents = eventService.afficherEventsByUser1(userId);
//
//    ObservableList<Event> itemsEvenements = FXCollections.observableArrayList(userEvents);
//
//    listmyevents.setItems(itemsEvenements);
//}


    // ...

//private int userId; // L'ID de l'utilisateur actuellement connecté

// Créez une méthode pour afficher les événements de l'utilisateur actuel
//    @FXML
//    private void afficherEvenementsUtilisateurActuel() {
//    // Assurez-vous que userId est défini avec l'ID de l'utilisateur connecté
//    userId = 2; // Remplacez 2 par l'ID de l'utilisateur connecté
//
//    Eventservice eventService = new Eventservice(); // Remplacez ServiceEvent par le nom de votre classe de service
//    List<Event> userEvents = eventService.afficherEventsByUser(userId);
//
//    ObservableList<String> itemsEvenements = FXCollections.observableArrayList();
//
//    for (Event event : userEvents) {
//        String eventString = "Nom de l'événement : " + event.getNomEvent() + "\n";
//        eventString += "Date de début : " + event.getDateDebutEvent() + "\n";
//        eventString += "Durée : " + event.getDurée() + "\n";
//        eventString += "Lieu : " + event.getLieuEvent() + "\n";
//        eventString += "Prix du ticket : " + event.getPrixTicket() + "\n";
//        eventString += "Nombre maximal de participants : " + event.getNbmaxParticipant() + "\n";
//        eventString += "Type d'événement : " + event.getTypeEvent() + "\n";
//        eventString += "Description : " + event.getDescriptionEvent() + "\n";
//
//        itemsEvenements.add(eventString);
//    }
//
//    listEvenement.setItems(itemsEvenements);
//}
//@FXML
//private void afficherEvenementsUtilisateurActuel() {
//    // Assurez-vous que le nom d'utilisateur actuel est défini
//    int iduser= selectedEventId;
//    String username = "nom_utilisateur"; // Remplacez "nom_utilisateur" par le nom de l'utilisateur connecté
//
//    // Utilisez la méthode getEventNamesByUser pour récupérer la liste des noms d'événements
//    Eventservice event = new Eventservice();
//    List<String> eventNames = event.getEventNamesByUser1(iduser);
//
//    // Convertissez la liste des noms d'événements en une ObservableList
//    ObservableList<String> itemsEvenements = FXCollections.observableArrayList(eventNames);
//
//    // Assurez-vous que listEvenement est l'identifiant de votre ListView dans le fichier FXML
//    listEvenement.setItems(itemsEvenements);
//}
//
//

 private void updateTime() {
    Thread thread = new Thread(() -> {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                Time.setText(format.format(new java.util.Date()));
            });
        }
    });
    thread.setDaemon(true); // Permet au thread de se terminer lorsque l'application se ferme
    thread.start();
}
public void updateMostPopularEvent() {
    Participationservice participationService = new Participationservice();
    String[] eventInfo = participationService.getEventNameAndParticipantsCountWithMostParticipants();

    if (eventInfo != null && eventInfo.length == 2) {
        String eventName = eventInfo[0].trim();
        String participantsCount = eventInfo[1].trim();

        nomeventpop.setText(eventName);
        currentnbpart.setText(participantsCount);
    }
}
 public void getUserParticipationCounts() {
        Participationservice participationService = new Participationservice(); // Créez une instance de la classe ParticipationService
          userService user = new userService();
        Map<Integer, Integer> userParticipationCounts = participationService.countParticipationsByUser();

        for (Map.Entry<Integer, Integer> entry : userParticipationCounts.entrySet()) {
            int userId = entry.getKey();
            int participationCount = entry.getValue();
            String username = currentUser.getNomuser(); // Utilisez la méthode non statique via l'instance
            System.out.println("UserID: " + userId + ", Username: " + username + ", Participation Count: " + participationCount);
            // Vous pouvez faire ce que vous voulez avec le username ici
        favuser.setText(username);
        }

 }
    
 
 
 
  public void updateMostDemandedTypeLabel() {
        // Créez une instance de EventService ou obtenez-la en fonction de votre configuration
        Eventservice eventService = new Eventservice();

        // Appelez la méthode pour obtenir le type d'événement le plus demandé
        String mostDemandedType = eventService.getTypeEventPlusDemande();

        // Définissez le texte du label avec le résultat
        mosttype.setText("Type le plus demandé : " + mostDemandedType);
    }
 
private void updateStackedBarChart(Map<String, Integer> data) {
    StackedBarChart<String, Number> eventStackedBarChart = stackbar;

    // Supprimer toutes les données précédentes
    eventStackedBarChart.getData().clear();

    // Ajouter un titre
    eventStackedBarChart.setTitle("Participations par type d'événement");

    // Créez une série pour chaque type d'événement
    for (Map.Entry<String, Integer> entry : data.entrySet()) {
        String eventType = entry.getKey();
        int count = entry.getValue();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(eventType);
        series.getData().add(new XYChart.Data<>(eventType, count));

        // Ajoutez la série au graphique empilé
        eventStackedBarChart.getData().add(series);
    }
}

 
  @Override
    public void initialize(URL url, ResourceBundle rb) {
          updateTime();
         // int userId = 2; // Remplacez ceci par la valeur appropriée
          menuDisplayCard();
        menuDisplayCardmy(currentUser.getIduser());
       // refreshEventList11();
       updateMostPopularEvent();
       getUserParticipationCounts();
 //  afficherEvenementsUtilisateurActuel(userId);
   
   updateMostDemandedTypeLabel();
    eventtype.getItems().addAll("Conférences académiques", "Compétitions académiques", "Événements culturels", "Activités sportives", "Événements de loisir");


    
    
    
   Map<String, Integer> participationData = eventService.getParticipationCountByEventType();

    // Utilisez les données pour mettre à jour le graphique empilé
    updateStackedBarChart(participationData);
    user user = UserSession.getCurrentUser();
         username.setText(user.getPrenomuser());
    
   
    
}
// Appel de la méthode pour obtenir les données de participation par type d'événement

    private void buttonDashbord6(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXMLDocument.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonTransport(ActionEvent event) {
         /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mainform.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));*/
    }

    @FXML
    private void buttonBiblio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/librarydorra.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

    @FXML
    private void buttonEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI_Events/FXML.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonServices(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mainform.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonForum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
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

                logout_btn.getScene().getWindow().hide();
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
    private void profile(ActionEvent event) {
         try {      dasborbprofile.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
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

   

    @FXML
    private void buttonDashboard(ActionEvent event) {
    }

    
   
    
    }


   







        
        
        
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

  
    
    



    


    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

