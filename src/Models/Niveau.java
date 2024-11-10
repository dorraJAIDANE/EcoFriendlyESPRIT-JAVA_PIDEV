/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;

/**
 *
 * @author Dorra
 */
public class Niveau {
    private int idNiveau;
    private String niveauName;
   private List<Document> documents;

    public Niveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Niveau() {
    }

    public Niveau(int idNiveau, String niveauName, List<Document> documents) {
        this.idNiveau = idNiveau;
        this.niveauName = niveauName;
        this.documents = documents;
    }

 

  

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    @Override
    public String toString() {
        return "Niveau{" + "idNiveau=" + idNiveau + ", niveauName=" + niveauName + ", documents=" + documents + '}';
    }

    public String getNiveauName() {
        return niveauName;
    }

    public void setNiveauName(String niveauName) {
        this.niveauName = niveauName;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
   
}
