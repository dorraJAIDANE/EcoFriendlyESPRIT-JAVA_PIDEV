/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Dorra
 */
public class SearchDocumentDTO {

    private String documentName;
       private String documentType;

       private int idNiveau;
       private int topic;
       private int idSemestre;
       private String isvalid;
    public SearchDocumentDTO() {
    }

    public SearchDocumentDTO(String documentName, String documentType, int idNiveau, int topic, int idSemestre,String isvalid) {
        this.documentName = documentName;
        this.documentType = documentType;
        this.idNiveau = idNiveau;
        this.topic = topic;
        this.idSemestre = idSemestre;
        this.isvalid=isvalid;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    @Override
    public String toString() {
        return "SearchDocumentDTO{" + "documentName=" + documentName + ", documentType=" + documentType + ", idNiveau=" + idNiveau + ", topic=" + topic + ", idSemestre=" + idSemestre + '}';
    }

    public void setIdTopic(Topic topic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
