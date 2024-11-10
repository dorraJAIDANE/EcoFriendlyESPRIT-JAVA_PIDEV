/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static GUI.UserSession.getCurrentUser;
import INTERFACES.DocumentService;
import Models.Document;
import Models.Niveau;
import Models.SearchDocumentDTO;
import Models.Semestre;
import Models.Topic;
import Models.user;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dorra
 */
public class DocumentServiceImp implements DocumentService{

        private static user currentUser=getCurrentUser();

 @Override
public Document addDocument(Document d) {
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();
        // Create an SQL INSERT statement to add the document
        String insertSql = "INSERT INTO documents (documentName, documentType, documentDate, documentCreationDate, documentImage, documentUrl, idNiveau, idTopic, idSemestre, idUser) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Use the format method to convert the Date to a String
        String documentCreationDate = dateFormat.format(d.getDocumentCreationDate());

        // Create a PreparedStatement and set parameters
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, d.getDocumentName());
        preparedStatement.setString(2, d.getDocumentType());
        preparedStatement.setString(3, documentCreationDate);
        preparedStatement.setDate(4, new java.sql.Date(d.getDocumentCreationDate().getTime()) );
        preparedStatement.setString(5, d.getDocumentImage());
        preparedStatement.setString(6, d.getDocumentUrl());
        preparedStatement.setInt(7, d.getIdNiveau().getIdNiveau());
        preparedStatement.setInt(8, d.getTopic().getIdTopic());
        preparedStatement.setInt(9, d.getIdSemestre().getIdSemestre());
        preparedStatement.setInt(10, currentUser.getIduser());

        // Execute the INSERT statement
        preparedStatement.executeUpdate();

        // If your database generates an ID for the new document, you may want to retrieve it here and set it on 'd'
        // Close the PreparedStatement

        // Return the updated 'd' object, which may now include the generated ID
        return d;
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
        return null; // or throw a custom exception
    }
}


    @Override
public Document updateDocument(Document d) {
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL UPDATE statement to update the document
        String updateSql = "UPDATE documents SET documentName = ?, documentType = ?, documentDate = ?, " +
            "documentCreationDate = ?, documentImage = ?, documentUrl = ?, idNiveau = ?, idSemestre = ?, idTopic = ? " +
            "WHERE idDoc = ?";

        // Create a PreparedStatement and set parameters
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setString(1, d.getDocumentName());
        preparedStatement.setString(2, d.getDocumentType());
        preparedStatement.setString(3, d.getDocumentDate());
        preparedStatement.setDate(4, new java.sql.Date(d.getDocumentCreationDate().getTime()));
        preparedStatement.setString(5, d.getDocumentImage());
        preparedStatement.setString(6, d.getDocumentUrl());
        preparedStatement.setInt(7, d.getIdNiveau().getIdNiveau());
        preparedStatement.setInt(8, d.getIdSemestre().getIdSemestre());
        preparedStatement.setInt(9, d.getIdTopic().getIdTopic());
        preparedStatement.setInt(10, d.getIdDoc());

        // Execute the UPDATE statement
        int rowsUpdated = preparedStatement.executeUpdate();
        preparedStatement.close();

        if (rowsUpdated > 0) {
            // Update successful
            return d;
        } else {
            // Document with the specified ID was not found
            return null;
        }
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
        return null; // or throw a custom exception
    }
}


   @Override
public boolean DeleteDocument(int id) {
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL DELETE statement to delete the document by its ID
        String deleteSql = "DELETE FROM documents WHERE idDoc = ?";

        // Create a PreparedStatement and set the parameter to the document's ID
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1, id);

        // Execute the DELETE statement
        int rowsDeleted = preparedStatement.executeUpdate();

        // Check if any rows were deleted
        if (rowsDeleted > 0) {
            // Document successfully deleted
            return true;
        } else {
            // Document with the specified ID was not found
            return false;
        }

        // Close the PreparedStatement
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
        // Return an appropriate value to indicate the deletion status, e.g., false
        return false;
    }
}


  @Override
public Document getDocumentByID(int doc) {
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve the document by its ID
        String selectSql = "SELECT * FROM documents WHERE idDoc = ?";

        // Create a PreparedStatement and set the parameter to the document's ID
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, doc);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if a document was found
        if (resultSet.next()) {
            // Retrieve the document's data from the ResultSet
            Document d=new Document();
            d.setIdDoc(resultSet.getInt("idDoc"));
            d.setDocumentName(resultSet.getString("documentName"));
            d.setDocumentType(resultSet.getString("documentType"));
            d.setDocumentDate(resultSet.getString("documentDate"));
            d.setDocumentCreationDate(resultSet.getDate("documentCreationDate"));
            d.setDocumentImage(resultSet.getString("documentImage"));
            d.setDocumentUrl(resultSet.getString("documentUrl"));
            d.setIdNiveau(new Niveau(resultSet.getInt("idNiveau")));
            d.setIdSemestre(new Semestre(resultSet.getInt("idsemestre")));
            d.setIdTopic(new Topic(resultSet.getInt("idtopic")));
           
            resultSet.close();
            preparedStatement.close();

            // Return the retrieved document
            return d;
        } else {
            // Document with the specified ID was not found
            return null;
        }
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
        return null; // or throw a custom exception
    }
}

   @Override
public List<Document> searchDocuments(SearchDocumentDTO d) {
    List<Document> result = new ArrayList<>();
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement with a WHERE clause to filter based on search criteria
        String selectSql = "SELECT * FROM documents WHERE 1=1";

        // Create a list to store the parameters for the PreparedStatement
        List<Object> parameters = new ArrayList<>();

        // Check and add search criteria to the SQL query
        if (d.getDocumentName() != null && ! "".equals(d.getDocumentName())) {
            selectSql += " AND documentName = ?";
            parameters.add(d.getDocumentName());
        }
        if (d.getDocumentType() != null) {
            selectSql += " AND documentType = ?";
            parameters.add(d.getDocumentType());
        }
        if (d.getIdNiveau() > 0) {
            selectSql += " AND idNiveau = ?";
            parameters.add(d.getIdNiveau());
        }
        if (d.getTopic() > 0) {
            selectSql += " AND idTopic = ?";
            parameters.add(d.getTopic());
        }
        if (d.getIdSemestre() > 0) {
            selectSql += " AND idSemestre = ?";
            parameters.add(d.getIdSemestre());
        }
       // System.out.println(selectSql);
        // Create a PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

        // Set parameters based on the search criteria
        for (int i = 0; i < parameters.size(); i++) {
            preparedStatement.setObject(i + 1, parameters.get(i));
        }

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate through the result set and create Document objects
        while (resultSet.next()) {
            Document d1 = new Document();
            d1.setIdDoc(resultSet.getInt("idDoc"));
            d1.setDocumentName(resultSet.getString("documentName"));
            d1.setDocumentType(resultSet.getString("documentType"));
            d1.setDocumentDate(resultSet.getString("documentDate"));
            d1.setDocumentCreationDate(resultSet.getDate("documentCreationDate"));
            d1.setDocumentImage(resultSet.getString("documentImage"));
            d1.setDocumentUrl(resultSet.getString("documentUrl"));
           d1.setIsvalid(resultSet.getString("isvalid"));

             d1.setIdNiveau(new Niveau(resultSet.getInt("idNiveau")));
            d1.setIdSemestre(new Semestre(resultSet.getInt("idSemestre")));
            d1.setIdTopic(new Topic(resultSet.getInt("idtopic")));

            result.add(d1);
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the list of matching documents
    return result;
}


    

   @Override
public List<Document> getAllDocuments() {
    List<Document> documents = new ArrayList<>();
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve all documents
        String selectSql = "SELECT * FROM documents";

        // Create a PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate through the result set and create Document objects
        while (resultSet.next()) {
            Document d = new Document();
            d.setIdDoc(resultSet.getInt("idDoc"));
            d.setDocumentName(resultSet.getString("documentName"));
            d.setDocumentType(resultSet.getString("documentType"));
            d.setDocumentDate(resultSet.getString("documentDate"));
            d.setDocumentCreationDate(resultSet.getDate("documentCreationDate"));
            d.setDocumentImage(resultSet.getString("documentImage"));
            d.setDocumentUrl(resultSet.getString("documentUrl"));
            d.setIdNiveau(new Niveau(resultSet.getInt("idNiveau")));
            d.setIdSemestre(new Semestre(resultSet.getInt("idsemestre")));
            d.setIdTopic(new Topic(resultSet.getInt("idtopic")));
            d.setIsvalid(resultSet.getString("isvalid"));
            
            documents.add(d);
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the list of documents
    return documents;
}
 
public List<Document> getValidDocuments() {
    List<Document> documents = new ArrayList<>();
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve all documents
        String selectSql = "SELECT * FROM documents WHERE isvalid='valid'";

        // Create a PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate through the result set and create Document objects
        while (resultSet.next()) {
            Document d = new Document();
            d.setIdDoc(resultSet.getInt("idDoc"));
            d.setDocumentName(resultSet.getString("documentName"));
            d.setDocumentType(resultSet.getString("documentType"));
            d.setDocumentDate(resultSet.getString("documentDate"));
            d.setDocumentCreationDate(resultSet.getDate("documentCreationDate"));
            d.setDocumentImage(resultSet.getString("documentImage"));
            d.setDocumentUrl(resultSet.getString("documentUrl"));
            d.setIdNiveau(new Niveau(resultSet.getInt("idNiveau")));
            d.setIdSemestre(new Semestre(resultSet.getInt("idsemestre")));
            d.setIdTopic(new Topic(resultSet.getInt("idtopic")));
            documents.add(d);
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the list of documents
    return documents;
}
public void ban( int  iddoc){
            String updateSql = "UPDATE documents SET  isvalid='novalid' WHERE idDoc = ?";

try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL UPDATE statement to update the document

        // Create a PreparedStatement and set parameters
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setInt(1, iddoc);


        // Execute the UPDATE statement
        int rowsUpdated = preparedStatement.executeUpdate();
        preparedStatement.close();

         System.out.println("setnovalid successfuly");
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
        
    }


}
   

}
