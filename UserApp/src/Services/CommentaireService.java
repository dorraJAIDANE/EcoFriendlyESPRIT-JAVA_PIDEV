/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Commentaire;
import Models.Post;
import util.MyConnection;

/**
 *
 * @author Grati Eya
 */
public class CommentaireService {
    
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    public void ajouterCommentaire(Commentaire c){
        String req = "INSERT INTO `Commentaire`(`idUser`,`dateCreation`, `Description`,`idPost`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getIdUser());
            ps.setDate(2, c.getDateCreation());
            ps.setString(3, c.getDescription());
            ps.setInt(4, c.getIdPost());
            ps.executeUpdate();
            System.out.println("Commentaire ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   
    
    public List<Commentaire> afficherCommentaires() {
        List<Commentaire> commentaires = new ArrayList<>();

        String req = "SELECT * FROM `Commentaire`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idCommentaire = rs.getInt("idComment");
                int idUser = rs.getInt("idUser");
                Date dateCreation = rs.getDate("dateCreation");
                String description = rs.getString("Description");
                int idPost = rs.getInt("idPost");

                Commentaire commentaire = new Commentaire();
                commentaire.setIdComment(idCommentaire);
                commentaire.setIdUser(idUser);
                commentaire.setDateCreation(dateCreation);
                commentaire.setDescription(description);
                commentaire.setIdPost(idPost);

                commentaires.add(commentaire);
            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return commentaires;
    }

    
    
   /* public void modifierCommentaire(Commentaire c){
    String req = "UPDATE `Commentaire` SET  `dateCreation`=?, `Description`=? WHERE `idComment`=?";

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setDate(1, c.getDateCreation());
        ps.setString(2, c.getDescription());
        ps.setInt(3, c.getIdUser());
        ps.setInt(4, c.getIdPost());
        ps.setInt(5, c.getIdComment());
        int rowsAffected = ps.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Commentaire modifié avec succès !");
        } else {
            System.out.println("Aucun commentaire modifié. Vérifiez l'ID du commentaire.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
    }
}*/
    public void modifierCommentaire(Commentaire c){
    String req = "UPDATE `Commentaire` SET `dateCreation`=?, `Description`=? WHERE `idComment`=?";

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setDate(1, c.getDateCreation());
        ps.setString(2, c.getDescription());
        ps.setInt(3, c.getIdComment());
        int rowsAffected = ps.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Commentaire modifié avec succès !");
        } else {
            System.out.println("Aucun commentaire modifié. Vérifiez l'ID du commentaire.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    public void supprimerCommentaire(int idComment) {
    String req = "DELETE FROM `Commentaire` WHERE `idComment`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idComment);
        ps.executeUpdate();
        System.out.println("Commentaire supprimé avec succès !");
        } 
    catch (SQLException ex) {
        Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
    }
    } 

    public void setCnx(MyConnection Mycnx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void affecterCommentaire(Commentaire c, Post p) {
        try {
            String req ="UPDATE `Commentaire` SET `Post`= ? WHERE idComment = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getIdPost());
            ps.setInt(2, c.getIdComment());
            ps.executeUpdate();
            System.out.println("Comment updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    
}
