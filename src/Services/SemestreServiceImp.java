/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import INTERFACES.SemestreService;
import Models.Semestre;
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
public class SemestreServiceImp implements SemestreService{
Connection cnx;

    public SemestreServiceImp() {
        cnx = MyConnection.getInstance().getCnx();
    }
    @Override
public List<Semestre> getAllSemestres() {
    List<Semestre> semestres = new ArrayList<>();
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve all semestres
        String selectSql = "SELECT * FROM semestre";

        // Create a PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate through the result set and create Semestre objects
        while (resultSet.next()) {
            Semestre semestre = new Semestre(
                resultSet.getInt("idSemestre"),
                resultSet.getString("semestreName")
            );
            System.out.println(semestre.toString());
            semestres.add(semestre);
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the list of semestres
    return semestres;
}

  @Override
public Semestre getSemestreById(int id) {
    Semestre semestre = null;
    try {
        // Establish a connection to your database using myconnection or your preferred method
        Connection connection = MyConnection.getInstance().getCnx();

        // Create an SQL SELECT statement to retrieve the semestre by its ID
        String selectSql = "SELECT * FROM semestre WHERE idSemestre = ?";

        // Create a PreparedStatement and set the parameter to the semestre's ID
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, id);

        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if a semestre was found
        if (resultSet.next()) {
            semestre = new Semestre(
                resultSet.getInt("idSemestre"),
                resultSet.getString("semestreName")
            );
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error, throw a custom exception)
        ex.printStackTrace();
    }

    // Return the semestre (or null if not found)
    return semestre;
}

    
}
