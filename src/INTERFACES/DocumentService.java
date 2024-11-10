/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACES;

import Models.Document;
import Models.SearchDocumentDTO;
import java.util.List;

/**
 *
 * @author Dorra
 */
public interface DocumentService {
    Document addDocument(Document d); 
    Document updateDocument(Document d); 
    boolean DeleteDocument(int id); 
    Document getDocumentByID(int d); 
    List<Document> searchDocuments(SearchDocumentDTO d);
     List<Document>  getAllDocuments();
     



}
