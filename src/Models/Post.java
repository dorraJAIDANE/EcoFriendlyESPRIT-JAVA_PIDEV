/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Grati Eya
 */
public class Post {
    
    private int idPost, idUser,nbreComment;
    private Date dateCreationp;
    private String nomUser, prenomUser;
    private int idSujet;
    private String title, descriptionp, image,subject;
    private List<Commentaire> comments;
    public Post() {
    }

    public Post(int idPost, int idUser, 
                int nbreComment, Date dateCreationp, 
                String nomUser, String prenomUser,
                int idSujet, String title,
                String descriptionp, String image, String subject) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.nbreComment = nbreComment;
        this.dateCreationp = dateCreationp;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.idSujet = idSujet;
        this.title = title;
        this.descriptionp = descriptionp;
        this.image = image;
        this.subject = subject;
    }
    
    public Post(int idPost,  String prenomUser,String  nomUser,String subject,
               String title, String descriptionp, String image , Date dateCreationp){
        this.idPost = idPost;
        this.prenomUser = prenomUser;
        this.nomUser = nomUser;
        this.subject = subject;
        this.title = title;
        this.descriptionp = descriptionp;
        this.image = image;
        this.dateCreationp = dateCreationp;
       
    }
    
    public Post( int idUser, int nbreComment, Date dateCreationp, 
                 String nomUser, String prenomUser, String title,
                 String descriptionp, String image, String subject) {
        this.idUser = idUser;
        this.nbreComment = nbreComment;
        this.dateCreationp = dateCreationp;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.title = title;
        this.descriptionp = descriptionp;
        this.image = image;
        this.subject = subject;
    }
    public Post(Date dateCreationp, String title, String descriptionp, String image, String subject) {

        this.dateCreationp = dateCreationp;
        this.title = title;
        this.descriptionp = descriptionp;
        this.subject = subject;
    }

    public int getIdPost() {
        return idPost;
    }
    
    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public int getIdSujet() {
        return idSujet;
    }
     
    public void setIdSujet(int idSujet) {
        this.idSujet = idSujet;
    }

    public int getNbreComment() {
        return nbreComment;
    }

    public void setNbreComment(int nbreComment) {
        this.nbreComment = nbreComment;
    }

    public Date getDateCreationp() {
        return dateCreationp;
    }

    public void setDateCreationp(Date dateCreationp) {
        this.dateCreationp = dateCreationp;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }
    
     public String getTitle() {
        return title;
    }
     
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescriptionp() {
        return descriptionp;
    }
     
    public void setDescriptionp(String descriptionp) {
        this.descriptionp = descriptionp;
    }
     public String getImage() {
        return image;
    }
     
    public void setImage(String image) {
        this.image = image;
    }
     public void setSubject(String subject) {
        this.subject = subject;
    }
     public String getSubject() {
        return subject;
    }

  






    
}
