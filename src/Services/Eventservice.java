/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static GUI.UserSession.getCurrentUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Event;
import Models.Participation;
import Models.user;
import util.MyConnection;


/**
 *
 * @author Mekni
 */
public class Eventservice {
        private static user currentUser=getCurrentUser();

    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    //Add 1
  
    
    //Add 2
 public void ajouterEvent(Event event, user user) {
    String req = "INSERT INTO `event` (`nomEvent`, `dateDebutEvent`, `Durée`, `LieuEvent`, `PrixTicket`, `nbmaxParticipant`, `typeEvent`, `descriptionEvent`, `image`,`iduser`) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, event.getNomEvent());
        ps.setDate(2, new java.sql.Date(event.getDateDebutEvent().getTime()));
        ps.setString(3, event.getDurée());
        ps.setString(4, event.getLieuEvent());
        ps.setDouble(5, event.getPrixTicket());
        ps.setInt(6, event.getNbmaxParticipant());
        ps.setString(7, event.getTypeEvent());
        ps.setString(8, event.getDescriptionEvent());
        ps.setString(9, event.getImage());
       ps.setInt(10, currentUser.getIduser()); 

        ps.executeUpdate();
        System.out.println("Event ajouté avec succès !");

        // Associez l'utilisateur à l'événement en définissant l'utilisateur pour cet événement
       
    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}

 
 
 
 
// ...

public void ajouterEvent11(Event event) {
    String req = "INSERT INTO `event` (`nomEvent`, `dateDebutEvent`, `Durée`, `LieuEvent`, `PrixTicket`, `nbmaxParticipant`, `typeEvent`, `descriptionEvent`, `image`, `Datecreation`, `iduser`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, event.getNomEvent());
        ps.setDate(2, new java.sql.Date(event.getDateDebutEvent().getTime()));
        ps.setString(3, event.getDurée());
        ps.setString(4, event.getLieuEvent());
        ps.setDouble(5, event.getPrixTicket());
        ps.setInt(6, event.getNbmaxParticipant());
        ps.setString(7, event.getTypeEvent());
        ps.setString(8, event.getDescriptionEvent());
        ps.setString(9, event.getImage());
        ps.setDate(10, new java.sql.Date(System.currentTimeMillis())); // Utilisez la date actuelle
        ps.setInt(11,currentUser.getIduser());

        ps.executeUpdate();
        System.out.println("Event ajouté avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}

// ...
 
 
 
public void ajouterEvent1(Event event) {
    String req = "INSERT INTO `event` (`nomEvent`, `dateDebutEvent`, `Durée`, `LieuEvent`, `PrixTicket`, `nbmaxParticipant`, `typeEvent`, `descriptionEvent`, `image`, `iduser`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, event.getNomEvent());
        ps.setDate(2, new java.sql.Date(event.getDateDebutEvent().getTime()));
        ps.setString(3, event.getDurée());
        ps.setString(4, event.getLieuEvent());
        ps.setDouble(5, event.getPrixTicket());
        ps.setInt(6, event.getNbmaxParticipant());
        ps.setString(7, event.getTypeEvent());
        ps.setString(8, event.getDescriptionEvent());
        ps.setString(9, event.getImage());
        ps.setInt(10, currentUser.getIduser()); // Utilisez getId() pour obtenir l'ID de l'utilisateur

        ps.executeUpdate();
        System.out.println("Event ajouté avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}

 
 
 
 
 
 
 
 
 
 
 


    //           Event event = new Event(); 
//            event.setIdEvent((rs.getInt(1)));
//            event.setLieuEvent(rs.getString("LieuEvent"));
//            event.setDateDebutEvent(rs.getDate("dateDebutEvent"));
//            event.setDateFinEvent(rs.getDate("dateFinEvent"));
//            event.setNbmaxParticipant(rs.getString("nbmaxParticipant"));
//            event.setPrixTicket(rs.getString("PrixTicket"));
//            event.setNomEvent(rs.getString("nomEvent"));
//            event.setTypeEvent(rs.getString("typeEvent"));
//            event.setDescriptionEvent(rs.getString("descriptionEvent"));
    
  
  public void ajouterEvent2(Event event) {
    String req = "INSERT INTO `event` (`nomEvent`, `dateDebutEvent`, `Durée`, `LieuEvent`, `PrixTicket`, `nbmaxParticipant`, `typeEvent`, `descriptionEvent`, `image`, `iduser`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, event.getNomEvent());
        ps.setDate(2, new java.sql.Date(event.getDateDebutEvent().getTime()));
        ps.setString(3, event.getDurée());
        ps.setString(4, event.getLieuEvent());
        ps.setDouble(5, event.getPrixTicket());
        ps.setInt(6, event.getNbmaxParticipant());
        ps.setString(7, event.getTypeEvent());
        ps.setString(8, event.getDescriptionEvent());
        ps.setString(9, event.getImage());
        ps.setInt(10, currentUser.getIduser()); // Utilisez getId() pour obtenir l'ID de l'utilisateur

        ps.executeUpdate();
        System.out.println("Event ajouté avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}

  ///// les evenements d un user:::
  public List<Event> afficherEventsByUser(int userId) {
    List<Event> evenements = new ArrayList<>();
    String req = "SELECT * FROM event WHERE iduser = ?"; // Remplacez iduser par le nom réel de la colonne dans votre table

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Event event = new Event(
                rs.getInt("idEvent"),
                rs.getString("nomEvent"),
                rs.getDate("dateDebutEvent"),
                rs.getString("Durée"),
                rs.getString("LieuEvent"),
                rs.getDouble("PrixTicket"),
                rs.getInt("nbmaxParticipant"),
                rs.getString("typeEvent"),
                rs.getString("descriptionEvent"),
                rs.getString("image")
            );

            evenements.add(event);
        }

    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }

    return evenements;
}
//////////////////////////////////////////////////////////////////////////////////////////////
  
  
  
  
  public List<Event> afficherEventsByUser1(int userId) {
    List<Event> evenements = new ArrayList<>();
    String req = "SELECT * FROM event WHERE iduser = ?"; // Remplacez iduser par le nom réel de la colonne dans votre table

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Event event = new Event(
                rs.getString("nomEvent"),
                rs.getDate("dateDebutEvent"),
                rs.getString("Durée"),
                rs.getString("LieuEvent"),
                rs.getDouble("PrixTicket"),
                rs.getInt("nbmaxParticipant"),
                rs.getString("typeEvent"),
                rs.getString("descriptionEvent"),
                rs.getString("image")
            );
             event.setDatecreation(rs.getDate("Datecreation")); // Récupérer la date de création depuis la base de données
            evenements.add(event);
        }

    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }

    return evenements;
}

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  //avec username 

  
  
  
  
  
  
  
  
  
  
  
  

      
public void deleteEvent(int idEvent) {
    String selectQuery = "SELECT * FROM event WHERE idEvent = ?";
    String deleteQuery = "DELETE FROM event WHERE idEvent = ?";
    
    try {
        // Vérifie si l'événement existe
        PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
        selectStatement.setInt(1, idEvent);
        ResultSet resultSet = selectStatement.executeQuery();
        
        if (!resultSet.next()) {
            System.out.println("Event with ID " + idEvent + " does not exist.");
            return;
        }

        // Supprime l'événement
        PreparedStatement deleteStatement = cnx.prepareStatement(deleteQuery);
        deleteStatement.setInt(1, idEvent);
        int result = deleteStatement.executeUpdate();

        if (result > 0) {
            System.out.println("Event with ID " + idEvent + " has been deleted successfully!");
        } else {
            System.out.println("Failed to delete event with ID " + idEvent);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}
////////////////////////////////////////
       public void updateEvent(Event updatedEvent) {
    String req = "UPDATE event SET LieuEvent=?, dateDebutEvent=?, Durée=?, nbmaxParticipant=?, PrixTicket=?, nomEvent=?, typeEvent=?, descriptionEvent=?, image=? WHERE idEvent=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, updatedEvent.getLieuEvent());
        ps.setDate(2, updatedEvent.getDateDebutEvent());
        ps.setString(3, updatedEvent.getDurée());
        ps.setInt(4, updatedEvent.getNbmaxParticipant());
        ps.setDouble(5, updatedEvent.getPrixTicket());
        ps.setString(6, updatedEvent.getNomEvent());
        ps.setString(7, updatedEvent.getTypeEvent());
        ps.setString(8, updatedEvent.getDescriptionEvent());
        ps.setString(9, updatedEvent.getImage());
        ps.setInt(10, updatedEvent.getIdEvent()); // Assurez-vous d'obtenir l'ID de l'événement que vous souhaitez mettre à jour
        
        int res = ps.executeUpdate();

        if (res == 0) {
            System.out.println("L'événement avec l'ID " + updatedEvent.getIdEvent() + " n'existe pas.");
        } else {
            System.out.println("L'événement avec l'ID " + updatedEvent.getIdEvent() + " a été mis à jour avec succès !");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}
///////////////////////////////////////////////////////////////////////////
       
       
       
       
       
       
       public void updateEvent(Event updatedEvent, int userId) {
    String req = "UPDATE event SET LieuEvent=?, dateDebutEvent=?, Durée=?, nbmaxParticipant=?, PrixTicket=?, nomEvent=?, typeEvent=?, descriptionEvent=?, image=? WHERE idEvent=? AND idUser=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, updatedEvent.getLieuEvent());
        ps.setDate(2, updatedEvent.getDateDebutEvent());
        ps.setString(3, updatedEvent.getDurée());
        ps.setInt(4, updatedEvent.getNbmaxParticipant());
        ps.setDouble(5, updatedEvent.getPrixTicket());
        ps.setString(6, updatedEvent.getNomEvent());
        ps.setString(7, updatedEvent.getTypeEvent());
        ps.setString(8, updatedEvent.getDescriptionEvent());
        ps.setString(9, updatedEvent.getImage());
        ps.setInt(10, updatedEvent.getIdEvent()); 
        ps.setInt(11, userId); // Ajoutez l'ID de l'utilisateur pour vérifier s'il a le droit de mettre à jour

        int res = ps.executeUpdate();

        if (res == 0) {
            System.out.println("L'événement avec l'ID " + updatedEvent.getIdEvent() + " n'existe pas ou vous n'avez pas le droit de le mettre à jour.");
        } else {
            System.out.println("L'événement avec l'ID " + updatedEvent.getIdEvent() + " a été mis à jour avec succès !");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}

       
       public String getTypeEventPlusDemande() {
        // Utilisez une map pour stocker les types d'événements et leur somme de participations
        Map<String, Integer> typeParticipationMap = new HashMap<>();

        String query = "SELECT e.typeEvent, COUNT(p.idParticipation) AS totalParticipations " +
                       "FROM event e " +
                       "LEFT JOIN participation p ON e.idEvent = p.idEvent " +
                       "GROUP BY e.typeEvent";

        try (PreparedStatement statement = cnx.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Parcourez les résultats de la requête et stockez-les dans la map
            while (resultSet.next()) {
                String typeEvent = resultSet.getString("typeEvent");
                int totalParticipations = resultSet.getInt("totalParticipations");
                typeParticipationMap.put(typeEvent, totalParticipations);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Trouvez le type d'événement avec la plus grande somme de participations
        String typePlusDemande = "";
        int maxParticipations = 0;

        for (Map.Entry<String, Integer> entry : typeParticipationMap.entrySet()) {
            String typeEvent = entry.getKey();
            int totalParticipations = entry.getValue();

            if (totalParticipations > maxParticipations) {
                maxParticipations = totalParticipations;
                typePlusDemande = typeEvent;
            }
        }

        return typePlusDemande;
    }

       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       

// public Event getEventById(int idEvent) {
//        String req = "SELECT * FROM event WHERE idEvent = ?";
//        Event event = null;
//
//        try {
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setInt(1, idEvent);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                // Récupérez les données de l'événement depuis le résultat de la requête
//                String nomEvent = rs.getString("nomEvent");
//                Date dateDebutEvent = rs.getDate("dateDebutEvent");
//                Integer Durée = rs.getInt("Durée");
//                String LieuEvent = rs.getString("LieuEvent");
//                Double PrixTicket = rs.getDouble("PrixTicket");
//                Integer nbmaxParticipant = rs.getInt("nbmaxParticipant");
//                String typeEvent = rs.getString("typeEvent");
//                String descriptionEvent = rs.getString("descriptionEvent");
//                // Récupérez le chemin de l'image
//                String image = rs.getString("image");
//
//                // Créez une instance de la classe Event
//                event = new Event(idEvent, nomEvent, dateDebutEvent, Durée, LieuEvent, PrixTicket, nbmaxParticipant, typeEvent, descriptionEvent, image);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return event;
//    }



          
          
          
public List<List<String>> getEventNamesByUser(int iduser) {
    List<List<String>> eventInfoList = new ArrayList<>();
    String query = "SELECT u.username, e.nomEvent, COUNT(e.idEvent) AS eventCount " +
            "FROM user u " +
            "LEFT JOIN event e ON u.iduser = e.iduser " +
            "WHERE u.iduser = ? " +
            "GROUP BY u.username, e.nomEvent";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, iduser);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String eventName = resultSet.getString("nomEvent");
                int eventCount = resultSet.getInt("eventCount");

                List<String> eventInfo = new ArrayList<>();
                eventInfo.add(username);
                eventInfo.add(eventName);
                eventInfo.add(String.valueOf(eventCount));

                eventInfoList.add(eventInfo);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return eventInfoList;
}

       public List<String> getEventNamesByUser(String username) {
    List<String> eventNames = new ArrayList<>();
    String query = "SELECT e.nomEvent " +
            "FROM user u " +
            "LEFT JOIN event e ON u.iduser = e.iduser " +
            "WHERE u.username = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setString(1, username);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String eventName = resultSet.getString("nomEvent");
                eventNames.add(eventName);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return eventNames;
}

       
       
//       public List<List<String>> getEventNamesByUser(int iduser) {
//    List<List<String>> eventInfoList = new ArrayList<>();
//    String query = "SELECT u.username, e.nomEvent " +
//            "FROM user u " +
//            "LEFT JOIN event e ON u.iduser = e.iduser " +
//            "WHERE u.iduser = ?";
//
//    try (
//         PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
//        preparedStatement.setInt(1, iduser);
//
//        try (ResultSet resultSet = preparedStatement.executeQuery()) {
//            while (resultSet.next()) {
//                String username = resultSet.getString("username");
//                String eventName = resultSet.getString("nomEvent");
//
//                List<String> eventInfo = new ArrayList<>();
//                eventInfo.add(username);
//                eventInfo.add(eventName);
//
//                eventInfoList.add(eventInfo);
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//
//    return eventInfoList;
//}

       
       
       
       
       
       
     
      
      
       public Event getEventById(int idEvent) {
        String req = "SELECT * FROM event WHERE idEvent = ?";
     Event event = new Event() ;

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idEvent);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Récupérez les données de l'événement depuis le résultat de la requête
                String nomEvent = rs.getString("nomEvent");
                Date dateDebutEvent = rs.getDate("dateDebutEvent");
                String Durée = rs.getString("Durée");
                String LieuEvent = rs.getString("LieuEvent");
                Double PrixTicket = rs.getDouble("PrixTicket");
                Integer nbmaxParticipant = rs.getInt("nbmaxParticipant");
                String typeEvent = rs.getString("typeEvent");
                String descriptionEvent = rs.getString("descriptionEvent");
                // Récupérez le chemin de l'image
                String image = rs.getString("image");
               // int iduser = rs.getInt("iduser"); 
                // Créez une instance de la classe Event
               // event = new Event(idEvent, nomEvent, dateDebutEvent, Durée, LieuEvent, PrixTicket, nbmaxParticipant, typeEvent, descriptionEvent, image);
                Date Datecreation = rs.getDate("Datecreation"); // Récupérer la date de création depuis la base de données

            // Créez une instance de la classe Event en incluant la date de création
            event = new Event(idEvent, nomEvent, dateDebutEvent, Durée, LieuEvent, PrixTicket, nbmaxParticipant, typeEvent, descriptionEvent, image);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return event;
    }


      
      
       public Event getEventById1(int idEvent) {
        String req = "SELECT * FROM event WHERE idEvent = ?";
     Event event = new Event() ;

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idEvent);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Récupérez les données de l'événement depuis le résultat de la requête
                String nomEvent = rs.getString("nomEvent");
                Date dateDebutEvent = rs.getDate("dateDebutEvent");
                String Durée = rs.getString("Durée");
                String LieuEvent = rs.getString("LieuEvent");
                Double PrixTicket = rs.getDouble("PrixTicket");
                Integer nbmaxParticipant = rs.getInt("nbmaxParticipant");
                String typeEvent = rs.getString("typeEvent");
                String descriptionEvent = rs.getString("descriptionEvent");
                // Récupérez le chemin de l'image
                String image = rs.getString("image");
               // int iduser = rs.getInt("iduser"); 
                // Créez une instance de la classe Event
               // event = new Event(idEvent, nomEvent, dateDebutEvent, Durée, LieuEvent, PrixTicket, nbmaxParticipant, typeEvent, descriptionEvent, image);
                Date Datecreation = rs.getDate("Datecreation"); // Récupérer la date de création depuis la base de données

            // Créez une instance de la classe Event en incluant la date de création
            event = new Event(idEvent, nomEvent, dateDebutEvent, Durée, LieuEvent, PrixTicket, nbmaxParticipant, typeEvent, descriptionEvent, image,Datecreation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return event;
    }


      
      
      
      public double getPrixTicketById(int eventId) {
    String query = "SELECT prixTicket FROM event WHERE idEvent = ?";

    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setInt(1, eventId);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getDouble("prixTicket");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions SQL ici
    }

    // Retournez une valeur par défaut en cas d'erreur
    return 0.0; // Ou une autre valeur appropriée
}

      
      
      
public String getEventNameById(int eventId) {
    String query = "SELECT nomEvent FROM event WHERE idEvent = ?";
    String eventName = "";

    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setInt(1, eventId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            eventName = rs.getString("nomEvent");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions SQL ici
    }

    return eventName;
}











public List<Event> getEventsByUser(int iduser) {
    List<Event> events = new ArrayList<>();
    String query = "SELECT e.idEvent, e.nomEvent, e.dateDebutEvent, e.Durée, e.LieuEvent, e.PrixTicket, e.nbmaxParticipant, e.typeEvent, e.descriptionEvent, e.Datecreation " +
            "FROM event e " +
            "WHERE e.iduser = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, iduser);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Event event = new Event();
                // Vous pouvez ignorer l'ID de l'événement si vous ne souhaitez pas l'afficher
                // event.setIdEvent(resultSet.getInt("idEvent"));
                event.setNomEvent(resultSet.getString("nomEvent"));
                event.setDateDebutEvent(resultSet.getDate("dateDebutEvent"));
                event.setDurée(resultSet.getString("Durée"));
                event.setLieuEvent(resultSet.getString("LieuEvent"));
                event.setPrixTicket(resultSet.getDouble("PrixTicket"));
                event.setNbmaxParticipant(resultSet.getInt("nbmaxParticipant"));
                event.setTypeEvent(resultSet.getString("typeEvent"));
                event.setDescriptionEvent(resultSet.getString("descriptionEvent"));
                event.setDatecreation(resultSet.getDate("Datecreation")); // Récupérer la date de création depuis la base de données
                events.add(event);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return events;
}















public List<Event> getEventsByUser1(int iduser) {
    List<Event> events = new ArrayList<>();
    String query = "SELECT e.nomEvent " +
            "FROM event e " +
            "WHERE e.iduser = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, iduser);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setNomEvent(resultSet.getString("nomEvent"));
                events.add(event);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return events;
}




public Map<String, Integer> getParticipationCountByEventType() {
    Map<String, Integer> participationCountMap = new HashMap<>();

    // Remarquez l'utilisation de LEFT JOIN pour inclure tous les types d'événements
    String query = "SELECT e.typeEvent, COUNT(*) " +
                 "FROM event e " +
                 "LEFT JOIN participation p ON p.idEvent = e.idEvent " +
                 "GROUP BY e.typeEvent";

    try (PreparedStatement ps = cnx.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            String eventType = rs.getString("typeEvent");
            int count = rs.getInt("COUNT(*)");
            participationCountMap.put(eventType, count);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return participationCountMap;
}







public List<String> getEventNamesByUser1(int iduser) {
    List<String> eventNames = new ArrayList<>();
    String query = "SELECT e.nomEvent " +
            "FROM event e " +
            "WHERE e.iduser = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, iduser);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String eventName = resultSet.getString("nomEvent");
                eventNames.add(eventName);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return eventNames;
}


public int getUserIdByEventId(int eventId) {
    String query = "SELECT iduser FROM event WHERE idEvent = ?";
    int userId = -1; // Utilisez une valeur par défaut appropriée en cas d'erreur

    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setInt(1, eventId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            userId = rs.getInt("iduser");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions SQL ici
    }

    return userId;
}








 public List<Participation> getParticipationsForEvent(int idevent) {
    // Utilisez votre connexion à la base de données
   
    
    String query = "SELECT * FROM participation WHERE idevent = ?";
    List<Participation> participations = new ArrayList<>();
    
    try (PreparedStatement ps = cnx.prepareStatement(query)) {
        ps.setInt(1, idevent);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Participation participation = new Participation();
                // Remplissez les détails de la participation à partir des résultats de la requête
                participation.setIdParticipation(rs.getInt("idParticipation"));
                // ... Remplissez les autres détails de la participation
                participations.add(participation);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return participations;
}





public void updateTicketPrice(int idevent) {
    Event event = getEventById(idevent); // Remplacez par votre méthode pour obtenir un événement par son id
    LocalDate startDate = event.getDatecreation().toLocalDate();
    LocalDate endDate = event.getDateDebutEvent().toLocalDate();

    // Calcul de la date du milieu entre la date de création et la date de début
    LocalDate halfwayDate = startDate.plusDays(ChronoUnit.DAYS.between(startDate, endDate) / 2);

    List<Participation> participations = getParticipationsForEvent(idevent); // Remplacez par votre méthode pour obtenir les participations de l'événement

    // Calcul du nombre de participations dans la première moitié de la période
    int participantsInFirstHalf = 0;

    for (Participation participation : participations) {
        LocalDate participationDate = participation.getDateParticipation().toLocalDate();

        if (participationDate.isAfter(startDate) && participationDate.isBefore(halfwayDate)) {
            participantsInFirstHalf++;
        }
    }

    // Comparaison avec la moitié du nombre maximum de places
    int halfMaxParticipants = event.getNbmaxParticipant() / 2;

    if (participantsInFirstHalf < halfMaxParticipants) {
        // Réduction du prix du billet de 20 %
        double currentPrice = event.getPrixTicket();
        double reducedPrice = currentPrice * 0.8;
        event.setPrixTicket(reducedPrice);
        
        // Enregistrez la mise à jour du prix dans la base de données (vous devez implémenter cette partie)
        updateEventTicketPrice(idevent, reducedPrice);
    }
}

public void updateEventTicketPrice(int idevent, double reducedPrice) {
    // Utilisez votre connexion à la base de données
    Connection cnx = MyConnection.getInstance().getCnx();
    
    String query = "UPDATE event SET PrixTicket = ? WHERE idEvent = ?";
    
    try (PreparedStatement ps = cnx.prepareStatement(query)) {
        ps.setDouble(1, reducedPrice);
        ps.setInt(2, idevent);
        
        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Prix du billet de l'événement mis à jour avec succès !");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public boolean isFirstPeriodOver(int eventId) {
    Event event = getEventById1(eventId); // Remplacez par votre méthode pour obtenir un événement par son id

    LocalDate startDate = event.getDateDebutEvent().toLocalDate();
    LocalDate currentDate = LocalDate.now();
    LocalDate halfwayDate = event.getDatecreation().toLocalDate().plusDays(ChronoUnit.DAYS.between(event.getDatecreation().toLocalDate(), startDate) / 2);

    return currentDate.isAfter(startDate) && currentDate.isAfter(halfwayDate);
}


}

          
          
          
          
          
          
          
          
          
          
          
          
          
    


        
