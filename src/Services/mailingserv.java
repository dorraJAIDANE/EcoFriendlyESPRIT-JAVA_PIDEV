/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author louay
 */
public class mailingserv {
    
 public List<String> getAllEmails() {
        List<String> emails = new ArrayList<>();

        String sql = "SELECT email FROM users"; // Assuming 'email' is the column name for emails in your 'users' table

        try {Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String email = resultSet.getString("mailuser"); 
                emails.add(email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emails;
    }
}




