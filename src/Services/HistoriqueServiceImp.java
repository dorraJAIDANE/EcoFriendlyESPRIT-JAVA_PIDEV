/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static GUI.UserSession.getCurrentUser;
import INTERFACES.HistoriqueDocumentsService;
import Models.HistoriqueDocument;
import Models.user;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorra
 */
public class HistoriqueServiceImp implements HistoriqueDocumentsService{
        private static user currentUser=getCurrentUser();

      @Override
public void addhistorique(HistoriqueDocument h) {
    boolean exist=isExistHistorique(h.getIdDoc(),currentUser.getIduser(),h.getOperation());
          System.out.println("h"+h.toString());
    if(!exist){
    String insertSql = "INSERT INTO historique_doc (idUser,idDoc,operation) VALUES (?,?,?)";

    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
         preparedStatement.setInt(1, currentUser.getIduser());
         preparedStatement.setInt(2, h.getIdDoc());
         preparedStatement.setString(3, h.getOperation());// Set the topic name

        // Execute the INSERT statement
        preparedStatement.executeUpdate();
            preparedStatement.close();
    } catch (SQLException ex) {
        Logger.getLogger(TopicServiceImp.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
    
    @Override
    public List<HistoriqueDocument> getAllHistoriqueDocuments() {
        List<HistoriqueDocument> historiqueDocuments = new ArrayList<>();
        
         Connection connection = MyConnection.getInstance().getCnx();
        
        if (connection == null) {
            
            return historiqueDocuments;
        }
        
        String selectSql = "SELECT * FROM historique_doc";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                int idDoc = resultSet.getInt("idDoc");
                String operation = resultSet.getString("operation");
                HistoriqueDocument historiqueDocument = new HistoriqueDocument(id, idUser, idDoc, operation);
                historiqueDocuments.add(historiqueDocument);
            }
                resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {
          
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
        }}

@Override
public  List<HistoriqueDocument> gethistoriquedocumentByIdU(int idUser) {
           List<HistoriqueDocument> historiqueDocuments = new ArrayList<>();

    Connection connection = MyConnection.getInstance().getCnx();

    if (connection == null) {
       
        return null; 
    }

    String selectSql = "SELECT * FROM historique_doc WHERE idUser = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
        preparedStatement.setInt(1, idUser);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
           while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idU = resultSet.getInt("idUser");
                int idDoc = resultSet.getInt("idDoc");
                String operation = resultSet.getString("operation");
                HistoriqueDocument historiqueDocument = new HistoriqueDocument(id, idU, idDoc, operation);
                historiqueDocuments.add(historiqueDocument);
            }
        }
    } catch (SQLException ex) {
       
        ex.printStackTrace();
        return null; 
    } finally { 
       
    }
              return historiqueDocuments;

}


    @Override
    public  List<HistoriqueDocument> gethistoriquedocumentByIdDoc(int idDoc){
        List<HistoriqueDocument> historiqueDocuments = new ArrayList<>();

Connection connection = MyConnection.getInstance().getCnx();

    if (connection == null) {
       
        return null; 
    }

    String selectSql = "SELECT * FROM historique_doc WHERE idDoc = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
        preparedStatement.setInt(1, idDoc);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
              while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                int idDo = resultSet.getInt("idDoc");
                String operation = resultSet.getString("operation");
                HistoriqueDocument historiqueDocument = new HistoriqueDocument(id, idUser, idDo, operation);
                historiqueDocuments.add(historiqueDocument);
            }
        }
    } catch (SQLException ex) {
       
        ex.printStackTrace();
        return null; 
    } finally { 
        
    }
          return historiqueDocuments;
    }
      @Override
    public Boolean isExistHistorique(int idDoc,int idUser,String operation){

Connection connection = MyConnection.getInstance().getCnx();
    if (connection == null) {     
        return false; 
    }
    String selectSql = "SELECT * FROM historique_doc WHERE idDoc = ? and idUser = ? and operation like ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
        preparedStatement.setInt(1, idDoc);
        preparedStatement.setInt(2, idUser);
        preparedStatement.setString(3, operation);

        try (ResultSet resultSet = preparedStatement.executeQuery()){
            if (resultSet.next()) {
                               int id = resultSet.getInt("id");
                               System.err.println("id"+id);
                if(id>0)
                return true;
                else 
                    return false;
            } else {
               
                return false;
            }
            
        }
    } catch (SQLException ex) {
       
        ex.printStackTrace();
        return false; 
    } finally { 
      
    }
}


}










        
 