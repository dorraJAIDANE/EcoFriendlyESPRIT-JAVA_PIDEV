/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Dorra
 */
public class Document {
       private int idDoc;
       private String documentName;
       private String documentType;
       private String documentDate;
       private Date documentCreationDate;
       private String documentImage;
       private String documentUrl;
       

       private Niveau idNiveau;
       private Topic topic;
       private Semestre idSemestre;
       private user idUser;
       private String isvalid;

    public Document(int idDoc, String documentName, String documentType, String documentDate, Date documentCreationDate, String documentImage, String documentUrl, Niveau idNiveau, Topic topic, Semestre idSemestre, user idUser, String isvalid) {
        this.idDoc = idDoc;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.documentCreationDate = documentCreationDate;
        this.documentImage = documentImage;
        this.documentUrl = documentUrl;
        this.idNiveau = idNiveau;
        this.topic = topic;
        this.idSemestre = idSemestre;
        this.idUser = idUser;
        this.isvalid = isvalid;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    @Override
    public String toString() {
        return "Document{" + "idDoc=" + idDoc + ", documentName=" + documentName + ", documentType=" + documentType + ", documentDate=" + documentDate + ", documentCreationDate=" + documentCreationDate + ", documentImage=" + documentImage + ", documentUrl=" + documentUrl + ", idNiveau=" + idNiveau + ", topic=" + topic + ", idSemestre=" + idSemestre + ", idUser=" + idUser + '}';
    }

    public Document(int idDoc, String documentName, String documentType, String documentDate, Date documentCreationDate, String documentImage, String documentUrl, Niveau idNiveau, Topic idTopic, Semestre idSemestre, user idUser) {
        this.idDoc = idDoc;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.documentCreationDate = documentCreationDate;
        this.documentImage = documentImage;
        this.documentUrl = documentUrl;
        this.idNiveau = idNiveau;
        this.topic = idTopic;
        this.idSemestre = idSemestre;
        this.idUser = idUser;
    }

    public Document() {
    }

    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
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

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public Date getDocumentCreationDate() {
        return documentCreationDate;
    }

    public void setDocumentCreationDate(Date documentCreationDate) {
        this.documentCreationDate = documentCreationDate;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

   

    public Topic getIdTopic() {
        return topic;
    }

    public void setIdTopic(Topic idTopic) {
        this.topic = idTopic;
    }

    public Niveau getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Niveau idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Semestre getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Semestre idSemestre) {
        this.idSemestre = idSemestre;
    }

    public user getIdUser() {
        return idUser;
    }

    public void setIdUser(user idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Document other = (Document) obj;
        if (!Objects.equals(this.isvalid, other.isvalid)) {
            return false;
        }
        return true;
    }

   

   
   
       
       


}
