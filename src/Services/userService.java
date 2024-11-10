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
        String req = "INSERT INTO `user2`(`nomnt;\n" +
"import java.util.ArrayList;\n" +
"import java.util.List;\n" +
"import java.util.logging.Level;\n" +
"import java.util.logging.Logger;\n" +
"import Models.user;\n" +
"import util.MyConnection;\n" +
"/**\n" +
" *\n" +
" * @author LENOVOuser`, `prenomuser`, `mailuser`,`mdpuser`,`adressuser`,`walletuser`,`classeuser`,`roleuser`) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getNomuser());
            ps.setString(2, u.getPrenomuser());
            ps.setString(3, u.getMailuser());
            ps.setString(4, u.getMdpuser());
            ps.setString(5, u.getAdressuser());
            ps.setDouble(6, u.getWalletuser());
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
                u.setWalletuser(rs.getDouble("walletuser"));
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
    String req = "UPDATE `user2` SET `nomuser`=?, `prenomuser`=?, `mailuser`=?,  `adressuser`=?, `walletuser`=?,`classeuser`=?, `roleuser`=? WHERE `iduser`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getNomuser());
        ps.setString(2, u.getPrenomuser());
        ps.setString(3, u.getMailuser());
        
        ps.setString(4, u.getAdressuser());
        ps.setDouble(5, u.getWalletuser());
        ps.setString(6, u.getClasseuser());
        ps.setString(7, u.getRoleuser());
        
        
        ps.setInt(8, u.getIduser());
       
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
                user.setWalletuser(rs.getDouble("walletuser")); 
               
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
                    utilisateur.setWalletuser(rs.getDouble("walletuser"));
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

  public int getNumberOfUsers() {
        int numberOfUsers = 0;
        try {
            String query = "SELECT COUNT(*) FROM user";
            PreparedStatement statement = cnx.prepareStatement(query);

            // Exécuter la requête et récupérer le résultat
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numberOfUsers = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberOfUsers;
    }
 
    
    public int getUserIdById(int idUser) {
    String req = "SELECT iduser FROM user2 WHERE iduser = ?";
    int userId = -1;

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idUser);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            userId = rs.getInt("iduser");
        }
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return userId;
}

    
    public user getUserByIdd(int idUser) {
    String req = "SELECT * FROM user2 WHERE iduser = ?";
    user user = null;

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idUser);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            user = new user();
            user.setIduser(rs.getInt("iduser"));
            user.setNomuser(rs.getString("nomuser"));
          //  double value = Double.parseDouble(str);
             user.setWalletuser(rs.getDouble("walletuser"));
            // Assignez d'autres propriétés de l'utilisateur en fonction de votre modèle User
        }
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return user;
}
    
    
    
    
    
    
    
    
    public user getUserById(int idUser) {
    String req = "SELECT * FROM user2 WHERE iduser = ?";
    user user = null;

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idUser);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            user = new user();
            user.setIduser(rs.getInt("iduser"));
            user.setNomuser(rs.getString("nomuser"));
            
            // Assignez d'autres propriétés de l'utilisateur en fonction de votre modèle User
        }
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return user;
}
    
    
    
    
    
    

   public String getUsernameById(int userId) {
    String req = "SELECT nomuser FROM user2 WHERE iduser = ?";
    String username = null;

    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            username = rs.getString("nomuser");
        }  
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return username;
}
 
    
public void updateUserWallet(int userId, double newWalletAmount) {
    String query = "UPDATE user2 SET walletuser = ? WHERE iduser = ?";

    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setDouble(1, newWalletAmount);
        statement.setInt(2, userId);

        // Exécutez la mise à jour de la base de données
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions SQL ici
    }
}

    public double getUserWalletById(int userId) {
    String query = "SELECT walletuser FROM user2 WHERE iduser = ?";
    double wallet = 0.0; // Valeur par défaut si l'utilisateur n'est pas trouvé ou en cas d'erreur

    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            wallet = rs.getDouble("walletuser");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions SQL ici
    }

    return wallet;
}

    
}
    
    


   