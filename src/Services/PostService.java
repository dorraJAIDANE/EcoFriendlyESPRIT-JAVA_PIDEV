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
public class PostService {
    
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    public void ajouterPost(Post p){
        String req = "INSERT INTO `Post`(`idUser`, `nomUser`, `prenomUser`,`dateCreationp`,`nbrecomment`,`title`,`descriptionp`,`image`,`subject`) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getIdUser());
            ps.setString(2, p.getNomUser());
            ps.setString(3, p.getPrenomUser());
            ps.setDate(4, p.getDateCreationp());
            ps.setInt(5, p.getNbreComment());
            ps.setString(6, p.getTitle ());
            ps.setString(7, p.getDescriptionp ());
            ps.setString(8, p.getImage ());
            ps.setString(9, p.getSubject());
            ps.executeUpdate();
            System.out.println("Post ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
     public List<Post> afficherPosts() {
        List<Post> posts = new ArrayList<>();
        String req = "SELECT * FROM `Post`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setIdPost(rs.getInt("idPost"));
                post.setIdUser(rs.getInt("idUser"));
                post.setNbreComment(rs.getInt("nbreComment"));
                post.setDateCreationp(rs.getDate("dateCreationp"));
                post.setNomUser(rs.getString("nomUser"));
                post.setPrenomUser(rs.getString("prenomUser"));
                post.setTitle(rs.getString("title"));
                post.setDescriptionp(rs.getString("descriptionp"));
                post.setImage(rs.getString("image"));
                post.setSubject(rs.getString("subject"));

                posts.add(post);
            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return posts;
    }
    
     public void modifierPost(Post p) {
    String req = "UPDATE `Post` SET `idUser`=?,  `nomUser`=?, `prenomUser`=? ,`dateCreationp`=?,`nbreComment`=?,`title`=?,`descriptionp`=?,`image`=?,`subject`=?   WHERE `idPost`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, p.getIdUser());
        ps.setString(2, p.getNomUser());
        ps.setString(3, p.getPrenomUser());
        ps.setDate(4, p.getDateCreationp());
        ps.setInt(5, p.getNbreComment());
        ps.setString(6, p.getTitle());
        ps.setString(7, p.getDescriptionp());
        ps.setString(8, p.getImage());
        ps.setString(9, p.getSubject());
        ps.setInt(10, p.getIdPost());

        int rowsAffected = ps.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Post modifié avec succès !");
        } else {
            System.out.println("Aucun post modifié. Vérifiez l'ID du post.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

     
     public void supprimerPost(int idPost) {
    String req = "DELETE FROM `Post` WHERE `idPost`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idPost);
        ps.executeUpdate();
        System.out.println("Post supprimé avec succès !");
        } 
    catch (SQLException ex) {
        Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
      public List<Post> chercherPostParNomPrenom(String nom, String prenom) {
        List<Post> posts = new ArrayList<>();
        String req = "SELECT * FROM `Post` WHERE `nomUser` LIKE ? OR `prenomUser` LIKE ? OR CONCAT(`nomUser`, ' ', `prenomUser`) LIKE ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "%" + nom + "%");
            ps.setString(2, "%" + prenom + "%");
            ps.setString(3, "%" + nom + " " + prenom + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setIdUser(rs.getInt("idUser"));
                post.setNbreComment(rs.getInt("nbreComment"));
                post.setDateCreationp(rs.getDate("dateCreationp"));
                post.setNomUser(rs.getString("nomUser"));
                post.setPrenomUser(rs.getString("prenomUser"));
                post.setTitle(rs.getString("title"));
                post.setDescriptionp(rs.getString("descriptionp"));
                post.setImage(rs.getString("image"));
                post.setSubject(rs.getString("subject"));
                posts.add(post);
            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return posts;
    }
     
    public void affecterPost(sujetdiscuss s , Post p) {
        try {
            String req ="UPDATE `Post` SET `sujetdiscuss`= ? WHERE idPost = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getIdPost());
            ps.setInt(2, s.getIdSujet());
            ps.executeUpdate();
            System.out.println("Post updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    public Post chercherPostParID(int idPost) {
        String req = "SELECT * FROM `Post` WHERE `idPost`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idPost);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Post post = new Post();
                post.setIdPost(rs.getInt("idPost"));
                post.setIdUser(rs.getInt("idUser"));
                post.setNbreComment(rs.getInt("nbreComment"));
                post.setDateCreationp(rs.getDate("dateCreationp"));
                post.setNomUser(rs.getString("nomUser"));
                post.setPrenomUser(rs.getString("prenomUser"));
                post.setTitle(rs.getString("title"));
                post.setDescriptionp(rs.getString("descriptionp"));
                post.setImage(rs.getString("image"));

                return post;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null; 
    }
    
    
}
