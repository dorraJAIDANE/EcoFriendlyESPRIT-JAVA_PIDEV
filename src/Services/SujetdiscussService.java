/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Post;
import Models.sujetdiscuss;
import util.MyConnection;
/**
 *
 * @author Grati Eya
 */
public class SujetdiscussService {
    
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
     public void ajouterSujetDiscuss(sujetdiscuss s){
        String req = "INSERT INTO `sujetdiscuss`(`nbrePost`, `typeSujet`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, s.getNbrePost());
            ps.setString(2, s.getTypeSujet());
            ps.executeUpdate();
            System.out.println("Sujet de discussion ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public List<sujetdiscuss> afficherLesSujets() {
        List<sujetdiscuss> sujets = new ArrayList<>();
        String req = "SELECT * FROM `sujetdiscuss`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sujetdiscuss sujet = new sujetdiscuss();
                sujet.setIdSujet(rs.getInt("idSujet"));
                sujet.setNbrePost(rs.getInt("nbrePost"));
                sujet.setTypeSujet(rs.getString("typeSujet"));

                sujets.add(sujet);
            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SujetdiscussService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sujets;
    }
    

      public void modifierSujetDiscussion(sujetdiscuss sujet) {
        String req = "UPDATE `sujetdiscuss` SET `nbrePost`=?, `typeSujet`=? WHERE `idSujet`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, sujet.getNbrePost());
            ps.setString(2, sujet.getTypeSujet());
            ps.setInt(3, sujet.getIdSujet());

            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Sujet de discussion modifié avec succès !");
            } else {
                System.out.println("Aucun sujet de discussion modifié. Vérifiez l'ID du sujet.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetdiscussService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerSujetDiscuss(int idSujet) {
    String req = "DELETE FROM `sujetdiscuss` WHERE `idSujet`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idSujet);
        ps.executeUpdate();
        System.out.println("sujet de discussion est supprimé avec succès !");
        } 
    catch (SQLException ex) {
        Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
     public List<sujetdiscuss> chercherSujetParType(String typeSujet) {
        List<sujetdiscuss> sujets = new ArrayList<>();
        String req = "SELECT * FROM `sujetdiscuss` WHERE `typeSujet`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, typeSujet);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sujetdiscuss sujet = new sujetdiscuss();
                sujet.setIdSujet(rs.getInt("idSujet"));
                sujet.setNbrePost(rs.getInt("nbrePost"));
                sujet.setTypeSujet(rs.getString("typeSujet"));

                sujets.add(sujet);
            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SujetdiscussService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sujets;
    }

    public void setCnx(MyConnection Mycnx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public sujetdiscuss chercherSujetParID(int idSujet) {
        String req = "SELECT * FROM `sujetdiscuss` WHERE `idSujet`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idSujet);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                sujetdiscuss sujet = new sujetdiscuss();
                sujet.setIdSujet(rs.getInt("idSujet"));
                sujet.setNbrePost(rs.getInt("nbrePost"));
                sujet.setTypeSujet(rs.getString("typeSujet"));

                return sujet;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SujetdiscussService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null; // Si aucun sujet de discussion avec cet ID n'est trouvé
    }

    
    
}
