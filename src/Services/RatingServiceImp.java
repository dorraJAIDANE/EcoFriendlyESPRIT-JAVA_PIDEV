/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.CurrentDocument;
//import Models.currentuser;
import Models.rateddocs;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.controlsfx.control.Rating;

/**
 *
 * @author Dorra
 */
public class RatingServiceImp {

    public boolean addRating(rateddocs rating) {

        String insertRatingQuery = "INSERT INTO rateddoc (userId, rating,rateddoc) VALUES (?, ?, ?)";

        try {
            Connection connection = MyConnection.getInstance().getCnx();
            PreparedStatement preparedStatement = connection.prepareStatement(insertRatingQuery);

            preparedStatement.setInt(1, 1); // Assuming userId is an int
            preparedStatement.setInt(2, rating.getRating());

            preparedStatement.setInt(3, rating.getRateddoc().getIdDoc()); // Assuming documentId is an int

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateRating(rateddocs rating) {
    String updateRatingQuery = "UPDATE rateddoc SET rating = ? WHERE userId = ? AND rateddoc = ?";

    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(updateRatingQuery);

        preparedStatement.setInt(1, rating.getRating());
        preparedStatement.setInt(2, rating.getUserId()); // Assuming userId is an int
        preparedStatement.setInt(3, rating.getRateddoc().getIdDoc()); // Assuming rateddoc is an int

        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public int getRatingByUserAndDocument(int userId, int documentId) {
    String selectRatingQuery = "SELECT rating FROM rateddoc WHERE userId = ? AND rateddoc = ?";
    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(selectRatingQuery);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, documentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            return resultSet.getInt("rating");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return -1; // Return -1 if no rating is found for the user and document
}

//    public double getAverageRatingForDocument(CurrentDocument documentId) {
//        // Add code to retrieve and calculate the average rating for a document from your database.
//        // You can use SQL queries to calculate the average rating.
//        // Return the average rating as a double.
//    }
}
