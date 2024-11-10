/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACES;

import Models.HistoriqueDocument;
import java.util.List;

/**
 *
 * @author Dorra
 */
public interface HistoriqueDocumentsService {
    List<HistoriqueDocument> getAllHistoriqueDocuments() ;
     List<HistoriqueDocument> gethistoriquedocumentByIdU(int idUser);
     List<HistoriqueDocument> gethistoriquedocumentByIdDoc(int id_doc);
    void addhistorique(HistoriqueDocument h);
    public Boolean isExistHistorique(int idDoc,int idU,String op);
    
}
