/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Dorra
 */
public class HistoriqueDocument {
    private int id ;
  private int idUser;
    private int idDoc;
   private String operation;

    public HistoriqueDocument(int id, int iduser, int idDoc, String operation) {
        this.id = id;
        this.idUser = idUser;
        this.idDoc = idDoc;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "HistoriqueDocument{" + "id=" + id + ", idUser=" + idUser + ", idDoc=" + idDoc + ", operation=" + operation + '}';
    }

    public HistoriqueDocument() {
    }

    public HistoriqueDocument(int iduser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public int getIduser() {
        return idUser;
    }

    public int getIdDoc() {
        return idDoc;
    }

    public String getOperation() {
        return operation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIduser(int iduser) {
        this.idUser = idUser;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
   
   
   
   
   
   
   
}
