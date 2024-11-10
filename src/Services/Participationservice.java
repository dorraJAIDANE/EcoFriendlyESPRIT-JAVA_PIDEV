/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static GUI.UserSession.getCurrentUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import Models.Event;
import Models.Participation;
import Models.user;
import util.MyConnection;

/**
 *
 * @author Mekni
 */
public class Participationservice {

    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    private static user currentUser=getCurrentUser();

// public void ajouterParticipation(Participation participation) {
//        // Vérifiez si l'événement (idEvent) existe
//        if (eventExists(participation.getEvent().getIdEvent()) && userExists(participation.getUser().getIduser())) {
//            String req = "INSERT INTO `participation` (`idEvent`, `idUser`, `codeQR`) VALUES (?, ?, ?)";
//
//            try {
//                PreparedStatement ps = cnx.prepareStatement(req);
//
//                ps.setInt(1, participation.getEvent().getIdEvent());
//                ps.setInt(2, participation.getUser().getIduser());
//                ps.setString(3, participation.getCodeQR());
//                ps.executeUpdate();
//                System.out.println("Participation ajoutée avec succès !");
//            } catch (SQLException ex) {
//                Logger.getLogger(Participationservice.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            System.err.println("L'événement associé à la participation n'existe pas, ou l'utilisateur n'existe pas.");
//        }
//    }
// 
 
public int countParticipationsByEventId(int eventId) {
    // Utilisez votre connexion à la base de données
    Connection cnx = MyConnection.getInstance().getCnx();

    String query = "SELECT COUNT(*) FROM participation WHERE idEvent = ?";
    int count = 0;

    try (PreparedStatement ps = cnx.prepareStatement(query)) {
        ps.setInt(1, eventId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return count;
}

public boolean isEventFull(int eventId) {
    Eventservice eventService = new Eventservice();
    Event event = eventService.getEventById(eventId);
    
    if (event != null) {
        int maxParticipations = event.getNbmaxParticipant();
        int currentParticipations = countParticipationsByEventId(eventId);
        
        return currentParticipations >= maxParticipations;
    } else {
        // Gérez le cas où l'événement n'existe pas (par exemple, retournez false ou générez une exception).
        return false;
    }
}
public void ajouterParticipation(Participation participation) {
   userService userService = new userService();
    Eventservice eventService = new Eventservice();

    if (eventExists(participation.getEvent().getIdEvent()) && userExists(currentUser.getIduser())) {
        String username = currentUser.getNomuser();
        String eventName = eventService.getEventNameById(participation.getEvent().getIdEvent());
        String qrData = username + "-" + eventName;

        // Générez la date de participation courante
        LocalDate dateParticipation = LocalDate.now();
        java.sql.Date sqlDateParticipation = java.sql.Date.valueOf(dateParticipation); // Convertissez en java.sql.Date

        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "EventQr";
        String qrCodeFileName = username + "-" + eventName + ".png";
        String qrCodeFilePath = desktopPath + File.separator + qrCodeFileName;

        // Générez le code QR avec les données combinées
        generateQRCode(qrData, qrCodeFilePath);

        String req = "INSERT INTO `participation` (`idEvent`, `idUser`, `codeQR`, `dateParticipation`) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, participation.getEvent().getIdEvent());
            ps.setInt(2, currentUser.getIduser());
            ps.setString(3, qrData);
            ps.setDate(4, sqlDateParticipation); // Enregistrez la date de participation en tant que java.sql.Date
            ps.executeUpdate();
            System.out.println("Participation ajoutée avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(Participationservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        System.err.println("L'événement associé à la participation n'existe pas, ou l'utilisateur n'existe pas.");
    }
}

 
//    
//    public void ajouterParticipation(Participation participation) {
//    // Vérifiez si l'événement (idEvent) existe
//    Userservice userService = new Userservice();
//Eventservice eventService = new Eventservice();
//    if (eventExists(participation.getEvent().getIdEvent()) && userExists(participation.getUser().getIduser())) {
//        String username = userService.getUsernameById(participation.getUser().getIduser()); // Obtenez le nom d'utilisateurbtenez le nom d'utilisateur
//        String eventName = eventService.getEventNameById(participation.getEvent().getIdEvent()); // Obtenez le nom de l'événement
//        String qrData = username + "-" + eventName; // Concaténez le nom d'utilisateur et le nom de l'événement
//
//       String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "EventQr";
//String qrCodeFileName = username + "-" + eventName + ".png";
//String qrCodeFilePath = desktopPath + File.separator + qrCodeFileName;
//        // Générez le code QR avec les données combinées
//        generateQRCode(qrData, qrCodeFilePath);
//
//        String req = "INSERT INTO `participation` (`idEvent`, `idUser`, `codeQR`) VALUES (?, ?, ?)";
//
//        try {
//            PreparedStatement ps = cnx.prepareStatement(req);
//
//            ps.setInt(1, participation.getEvent().getIdEvent());
//            ps.setInt(2, participation.getUser().getIduser());
//            ps.setString(3, qrData); // Enregistrez les données du code QR (nom d'utilisateur + nom de l'événement)
//            ps.executeUpdate();
//            System.out.println("Participation ajoutée avec succès !");
//        } catch (SQLException ex) {
//            Logger.getLogger(Participationservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    } else {
//        System.err.println("L'événement associé à la participation n'existe pas, ou l'utilisateur n'existe pas.");
//    }
//}

public void generateQRCode(String data, String filePath) {
    int width = 300;
    int height = 300;
    String format = "png";

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix;
    try {
        bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
    } catch (WriterException e) {
        e.printStackTrace();
        return;
    }

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            int color = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
            bufferedImage.setRGB(x, y, color);
        }
    }

    File qrFile = new File(filePath);
    try {
        ImageIO.write(bufferedImage, format, qrFile);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
    
    
    
    
    
    
    
    
    
//    
//    
//    
//    
// 
//      private void generateQRCode(String data, String filePath) {
//        int width = 300;
//        int height = 300;
//        String format = "png";
//
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                int color = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
//                bufferedImage.setRGB(x, y, color);
//            }
//        }
//
//        File qrFile = new File(filePath);
//        try {
//            ImageIO.write(bufferedImage, format, qrFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
 
 
 
 
 
 
 
 
 
public void updateParticipation(Participation participation) {
    int idParticipation = participation.getIdParticipation();
    Event event = participation.getEvent();
    user user = participation.getuser();

    // Vérifiez d'abord si la participation existe
    if (participationExiste(idParticipation) && event != null && user != null) {
        // Tous les objets (participation, event et user) existent, vous pouvez maintenant effectuer la mise à jour
        String req = "UPDATE participation SET idEvent = ?, idUser = ?, codeQR = ? WHERE idParticipation = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, event.getIdEvent());
            ps.setInt(2, user.getIduser());
            ps.setString(3, participation.getCodeQR());
            ps.setInt(4, idParticipation);

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Participation mise à jour avec succès !");
            } else {
                System.err.println("Erreur lors de la mise à jour de la participation.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Participationservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        System.err.println("Erreur : La participation avec idParticipation " + idParticipation + " n'existe pas ou des objets associés (Event ou User) n'existent pas.");
    }
}





    public boolean participationExiste(int idParticipation) {
        String req = "SELECT COUNT(*) FROM participation WHERE idParticipation = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idParticipation);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Si count est supérieur à 0, la participation existe, sinon elle n'existe pas
            }
        } catch (SQLException ex) {
            Logger.getLogger(Participationservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false; // En cas d'erreur ou si la participation n'a pas été trouvée
    }

    public void deleteParticipation(int idParticipation) {
        // Recherche de la participation par ID
        Participation participation = getParticipationById(idParticipation);

        if (participation != null) {
            Event event = participation.getEvent();
            //User user = participation.getUser();

            // Suppression de la participation
            String req = "DELETE FROM participation WHERE idParticipation=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, idParticipation);

                int result = ps.executeUpdate();

                if (result > 0) {
                    System.out.println("Participation with ID " + idParticipation + " has been deleted successfully!");

                    // Vous pouvez également effectuer d'autres actions ici si nécessaire,
                    // comme la mise à jour de l'événement ou de l'utilisateur.

                } else {
                    System.err.println("Error deleting participation.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Participationservice.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("Participation with ID " + idParticipation + " does not exist.");
        }
    }

    public Participation getParticipationById(int idParticipation) {
        String req = "SELECT * FROM participation WHERE idParticipation = ?";
        Participation participation = null;

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idParticipation);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int eventId = rs.getInt("idEvent");
                int userId = rs.getInt("idUser");
                String codeQR = rs.getString("codeQR");

                // Pour obtenir l'Event associé, utilisez votre méthode getEventById
                Event event = getEventById(eventId);

                // Pour obtenir l'User associé, utilisez votre méthode getUserById
           //     User user = getUserById(userId);

                participation = new Participation(idParticipation, event, currentUser, codeQR);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Participationservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return participation;
    }

//    public User getUserById(int idUser) {
//        String req = "SELECT * FROM user WHERE iduser = ?";
//        User user = null;
//
//        try {
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.setInt(1, idUser);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String username = rs.getString("username");
//                // Récupérez les données de l'utilisateur depuis le résultat de la requête
//                // Exemple : String username = rs.getString("username");
//                // D'autres attributs de l'utilisateur
//
//                user = new User(idUser,username);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Userservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return user;
//    }

    public Event getEventById(int idEvent) {
        String req = "SELECT * FROM event WHERE idEvent = ?";
        Event event = null;

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

                // Créez une instance de la classe Event
                event = new Event(idEvent, nomEvent, dateDebutEvent, Durée, LieuEvent, PrixTicket, nbmaxParticipant, typeEvent, descriptionEvent, image);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return event;
    }


   
   
   
   
   
   
   
   
    public void afficherParticipations() {
    String req = "SELECT * FROM participation";
    List<Participation> participations = new ArrayList<>();

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idParticipation = rs.getInt("idParticipation");
            int eventId = rs.getInt("idEvent");
            int userId = rs.getInt("idUser");
            String codeQR = rs.getString("codeQR");

            // Obtenez l'Event associé en utilisant la méthode getEventById
            Event event = getEventById(eventId);

            // Obtenez l'User associé en utilisant la méthode getUserById
          //  User user = getUserById(userId);

            // Créez une instance de Participation en respectant les associations
            Participation participation = new Participation(idParticipation, event, currentUser, codeQR);

            // Ajoutez la participation à la liste
            participations.add(participation);
        }

        // Maintenant, vous pouvez afficher la liste de participations
        for (Participation participation : participations) {
            System.out.println("ID Participation: " + participation.getIdParticipation());
            System.out.println("Event: " + participation.getEvent().getNomEvent());
            System.out.println("User: " + currentUser);
            System.out.println("Code QR: " + participation.getCodeQR());
            System.out.println();
        }
    } catch (SQLException ex) {
        Logger.getLogger(Participationservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    
    
    
    
    
    
    
    
    
    // Méthode pour vérifier si un événement (idEvent) existe
private boolean eventExists(int idEvent) {
    String query = "SELECT COUNT(*) FROM event WHERE idEvent = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, idEvent);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Retourne true si l'événement existe, sinon false
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

// Méthode pour vérifier si un utilisateur (idUser) existe
private boolean userExists(int idUser) {
    String query = "SELECT COUNT(*) FROM user2 WHERE iduser = ?";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, idUser);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Retourne true si l'utilisateur existe, sinon false
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

    
    
  public String[] getEventNameAndParticipantsCountWithMostParticipants() {
    String query = "SELECT e.nomEvent, COUNT(*) AS participationCount " +
                   "FROM event e " +
                   "JOIN participation p ON e.idEvent = p.idEvent " +
                   "GROUP BY e.nomEvent " +
                   "ORDER BY participationCount DESC " +
                   "LIMIT 1";

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
            String nomEvent = resultSet.getString("nomEvent");
            int participationCount = resultSet.getInt("participationCount");
            return new String[]{nomEvent, String.valueOf(participationCount)};
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; // Retourne null en cas d'erreur ou si aucune participation n'a été trouvée
}


    
    
    
     public Map<Integer, Integer> countParticipationsByUser() {
        Connection cnx = MyConnection.getInstance().getCnx();
        Map<Integer, Integer> userParticipationCount = new HashMap<>();

        String query = "SELECT idUser, COUNT(*) AS participationCount FROM participation GROUP BY idUser";

        try (PreparedStatement ps = cnx.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int userId = rs.getInt("idUser");
                int participationCount = rs.getInt("participationCount");
                userParticipationCount.put(userId, participationCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userParticipationCount;
    }
    
   
    
    

}
    
    
    
    
    
    
    
    
    
    
    
    

