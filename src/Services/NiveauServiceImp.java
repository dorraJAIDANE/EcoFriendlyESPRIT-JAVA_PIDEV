
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import INTERFACES.NiveauService;
import Models.Niveau;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dorra
 */
public class NiveauServiceImp implements NiveauService{

 @Override
public List<Niveau> getAllNiveaus() {
    List<Niveau> niveaus = new ArrayList<>();
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve all niveaus
        String selectSql = "SELECT * FROM niveau";

        // Create a PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate through the result set and create Niveau objects
        while (resultSet.next()) {
            Niveau niveau = new Niveau(
                resultSet.getInt("idNiveau"),
                resultSet.getString("niveauName"),
                // You may need to set documents for each niveau if it's part of your data model
                null // Replace with documents if needed
            );
            niveaus.add(niveau);
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the list of niveaus
    return niveaus;
}
@Override
public Niveau getNiveauById(int id) {
    Niveau niveau = null;
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve the niveau by its ID
        String selectSql = "SELECT * FROM niveau WHERE idNiveau = ?";

        // Create a PreparedStatement and set the parameter to the niveau's ID
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, id);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if a niveau was found
        if (resultSet.next()) {
            niveau = new Niveau(
                resultSet.getInt("idNiveau"),
                resultSet.getString("niveauName"),
                // You may need to set documents for the niveau if it's part of your data model
                null // Replace with documents if needed
            );
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the niveau (or null if not found)
    return niveau;
}

    
}
  