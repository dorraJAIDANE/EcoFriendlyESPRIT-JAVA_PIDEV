/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    
    static final String URL = "jdbc:mysql://localhost:3306/ecofriendlyesprit";
    static final String USER = "root";
    static final String PASSWORD = "";
    
    public Connection cnx;
    static MyConnection instance;
  static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            
        }
    }
    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connexion avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }
  
    public Connection getCnx() {
        return cnx;
    }

    
}
