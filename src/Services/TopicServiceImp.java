/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static GUI.UserSession.getCurrentUser;
import INTERFACES.TopicService;
import Models.Topic;
import Models.user;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorra
 */
public class TopicServiceImp implements TopicService{
    private static user currentUser=getCurrentUser();

    @Override
public List<Topic> getAllTopics() {
    List<Topic> topics = new ArrayList<>();
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve all topics
        String selectSql = "SELECT * FROM topic";

        // Create a PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate through the result set and create Topic objects
        while (resultSet.next()) {
            Topic topic = new Topic(
                resultSet.getInt("idTopic"),
                resultSet.getString("topicName")
            );
            topics.add(topic);
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the list of topics
    return topics;
}

@Override
public Topic getTopicById(int id) {
    Topic topic = null;
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve the topic by its ID
        String selectSql = "SELECT * FROM topic WHERE idTopic = ?";

        // Create a PreparedStatement and set the parameter to the topic's ID
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, id);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if a topic was found
        if (resultSet.next()) {
            topic = new Topic(
                resultSet.getInt("idTopic"),
                resultSet.getString("topicName")
            );
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the topic (or null if not found)
    return topic;
}

    /**
     *
     * @param t
     * @return 
     */
  @Override
public void addTopic(Topic t) {
    String insertSql = "INSERT INTO topic (topicName) VALUES (?)";

    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, t.getTopicName()); // Set the topic name

        // Execute the INSERT statement
        preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(TopicServiceImp.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    @Override
    public void modifierTopic(int idTopic, String nouveauNom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimerTopic(int idTopic) {
 try {
        
        Connection connection = MyConnection.getInstance().getCnx();

       
        String deleteSql = "DELETE FROM topic WHERE idTopic = ?";

        // Create a PreparedStatement and set the parameter to the document's ID
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1, idTopic);

       
        int rowsDeleted = preparedStatement.executeUpdate();

       
     
      

      
        if (rowsDeleted > 0) {

            return true;
        } else {
         
            return false;
        }

    
    } catch (SQLException ex) {
      
        ex.printStackTrace();
    
        return false;
    }
}

        }  
            
   
   
   



