/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Grati Eya
 */
public class sujetdiscuss {
    
    private int idSujet, nbrePost;
    private String typeSujet;
    // Constructeur par défaut
    public sujetdiscuss() {
    }

    // Constructeur avec tous les champs
    public sujetdiscuss(int idSujet, int nbrePost, String typeSujet) {
        this.idSujet = idSujet;
        this.nbrePost = nbrePost;
        this.typeSujet = typeSujet;
    }
    
     public sujetdiscuss( int nbrePost, String typeSujet) {
        this.nbrePost = nbrePost;
        this.typeSujet = typeSujet;
    }

    // Getters et Setters pour idSujet
    public int getIdSujet() {
        return idSujet;
    }

    public void setIdSujet(int idSujet) {
        this.idSujet = idSujet;
    }

    // Getters et Setters pour nbreSujet
    public int getNbrePost() {
        return nbrePost;
    }

    public void setNbrePost(int nbrePost) {
        this.nbrePost = nbrePost;
    }

    // Getters et Setters pour typeSujet
    public String getTypeSujet() {
        return typeSujet;
    }

    public void setTypeSujet(String typeSujet) {
        this.typeSujet = typeSujet;
    }
}
    
    
    

