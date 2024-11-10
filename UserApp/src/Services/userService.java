/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.user;
import util.MyConnection;
/**
 *
 * @author LENOVO
 */
public class userService {
       MyConnection Mycnx = MyConnection.getInstance();
        Connection cnx = Mycnx.getCnx();
        
        public void ajouterUser (user u ){
        String req = "INSERT INTO `user2`(`nomuser`, `prenomuser`, `mailuser`,`mdpuser`,`adressuser`,`walletuser`,`classeuser`,`roleuser`) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getNomuser());
            ps.setString(2, u.getPrenomuser());
            ps.setString(3, u.getMailuser());
            ps.setString(4, u.getMdpuser());
            ps.setString(5, u.getAdressuser());
            ps.setString(6, u.getWalletuser());
            ps.setString(7, u.getClasseuser());
            ps.setString(8, u.getRoleuser());
            ps.executeUpdate();
            System.out.println("user ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
         }}

    
     public user afficherUser(String mailuser) {
        String req = "SELECT * FROM `user2` WHERE `mailuser` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, mailuser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user u = new user();
                u.setIduser(rs.getInt("iduser"));
                u.setNomuser(rs.getString("nomuser"));
                u.setPrenomuser(rs.getString("prenomuser"));
                u.setMailuser(rs.getString("mailuser"));
                u.setMdpuser(rs.getString("mdpuser"));
                u.setAdressuser(rs.getString("adressuser"));
                u.setClasseuser(rs.getString("classeuser"));
                u.setRoleuser(rs.getString("roleuser"));
                u.setWalletuser(rs.getString("walletuser"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



public void supprimeruser(Integer iduser) {
    String req = "DELETE FROM `user2` WHERE `iduser`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, iduser);
        ps.executeUpdate();
        System.out.println("user supprimé avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void mettreAJouruser(user u) {
    String req = "UPDATE `user2` SET `nomuser`=?, `prenomuser`=?, `mailuser`=?, `mdpuser`=?, `adressuser`=?, `walletuser`=?,`classeuser`=?, `roleuser`=? WHERE `iduser`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getNomuser());
        ps.setString(2, u.getPrenomuser());
        ps.setString(3, u.getMailuser());
        ps.setString(4, u.getMdpuser());
        ps.setString(5, u.getAdressuser());
        ps.setString(6, u.getWalletuser());
        ps.setString(7, u.getClasseuser());
        ps.setString(8, u.getRoleuser());
        
        
        ps.setInt(9, u.getIduser());
       
        int rowAffected = ps.executeUpdate();
        
        if (rowAffected > 0){
        System.out.println("Utilisateur mis à jour avec succès !");
        } else {
            System.out.println("Aucun Utilisateur mis à jour !");
        }
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }
}
  public user findUserByEmail(String email)
    {
        user user = new user();
       
           
        String requete="select * from user2 where mailuser ='"+email+"' ";
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next())
            {  
               
               user.setIduser(rs.getInt("iduser"));
                user.setNomuser(rs.getString("nomuser"));
                user.setPrenomuser(rs.getString("prenomuser"));
                user.setMailuser(rs.getString("mailuser"));
                user.setRoleuser(rs.getString("roleuser"));
                user.setMdpuser(rs.getString("mdpuser"));
                user.setAdressuser(rs.getString("adressuser"));
                user.setClasseuser(rs.getString("classeuser")); 
                user.setWalletuser(rs.getString("walletuser")); 
               
            }
           
               
           
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
   }
public boolean checkEmailExist(String email)
    {
       
        int count = 0;
           
        String requete="select * from user2 where mailuser='"+email+"' ";
        try{
            Statement st = cnx.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            while(rsl.next())
            {
                count++;
            }
           if(count == 0){
                return false;
           }else{
               return true;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   }
    


    public List<user> chercherUser(String recherche) {
        List<user> utilisateursTrouves = new ArrayList<>();
        String req = "SELECT * FROM `user2` WHERE `nomuser` LIKE ? OR `prenomuser` LIKE ? OR CONCAT(`nomuser`, ' ', `prenomuser`) LIKE ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "%" + recherche + "%");
            ps.setString(2, "%" + recherche + "%");
            ps.setString(3, "%" + recherche + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user utilisateur = new user();
                    utilisateur.setNomuser(rs.getString("nomuser"));
                    utilisateur.setPrenomuser(rs.getString("prenomuser"));
                    utilisateur.setMailuser(rs.getString("mailuser"));
                    utilisateur.setMdpuser(rs.getString("mdpuser"));
                    utilisateur.setAdressuser(rs.getString("adressuser"));
                    utilisateur.setWalletuser(rs.getString("walletuser"));
                    utilisateur.setRoleuser(rs.getString("roleuser"));
                    utilisateur.setClasseuser (rs.getString("classeuser"));
                
                 utilisateursTrouves.add(utilisateur);
            }
            
            rs.close();
         } catch (SQLException ex) {
             Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return utilisateursTrouves;
    }

        public String rolePlusActif() {
    String requete = "SELECT roleuser, COUNT(*) as activity_count FROM user2 GROUP BY roleuser ORDER BY activity_count DESC LIMIT 1";
    try {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(requete);
        if (rs.next()) {
            return rs.getString("roleuser");
        }
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}



}
    
    


   