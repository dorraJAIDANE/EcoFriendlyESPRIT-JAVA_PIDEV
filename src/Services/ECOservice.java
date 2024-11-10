/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import INTERFACES.IECOservice;
import Models .Service;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author louay
 */
public class ECOservice implements IECOservice<Service>  {
    private ObservableList<Service> servicesList = FXCollections.observableArrayList();

   @Override
public void ajouter(Service t) {
    String sql = "INSERT INTO service (serviceId, serviceName, price, img) VALUES (?, ?, ?, ?)";
    
     try {Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        
        preparedStatement.setInt(1, t.getServiceId());
        preparedStatement.setString(2, t.getServiceName());
        preparedStatement.setDouble(3, t.getPrice());
        preparedStatement.setString(4, t.getImg());
        
        preparedStatement.executeUpdate();
        
        System.out.println("Service added successfully!");

    } catch (SQLException ex) {
        System.err.println("Error while adding service: " + ex.getMessage());
    }
}

    @Override
public void modifier(Service t) {
    String sql = "UPDATE service SET serviceName = ?, price = ?, img = ? WHERE serviceId = ?";
    
    try {Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        
        preparedStatement.setString(1, t.getServiceName());
        preparedStatement.setDouble(2, t.getPrice());
        preparedStatement.setString(3, t.getImg());
        preparedStatement.setInt(4, t.getServiceId());
        
        preparedStatement.executeUpdate();
        
        System.out.println("Service updated successfully!");
    } catch (SQLException ex) {
        System.err.println("Error while updating service: " + ex.getMessage());
    }
}


    @Override
public void supprimer(int id) {
        System.out.println("delete");
    String sql = "DELETE FROM service WHERE serviceId = ?";
    
    try {
        Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("Service with ID " + id + " deleted successfully!");

    } catch (SQLException ex) {
        System.err.println("Error while deleting service: " + ex.getMessage());
    }
}


    @Override
public Service getOne(int id) {
    String sql = "SELECT * FROM service WHERE serviceId = ?";
    Service service = null;
    
      try {Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            int serviceId = resultSet.getInt("serviceId");
            String serviceName = resultSet.getString("serviceName");
            double price = resultSet.getDouble("price");
            String img = resultSet.getString("img");
            
            service = new Service(serviceId, serviceName, price, img);
        }
    } catch (SQLException ex) {
        System.err.println("Error while retrieving service: " + ex.getMessage());
    }
    
    return service;
}


  @Override
public ObservableList<Service> getAll() {
    ObservableList<Service> services = FXCollections.observableArrayList();
    String sql = "SELECT * FROM service ";
    
    try  {
        Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
          //  System.out.println("1");
            int serviceId = resultSet.getInt("serviceId");
            String serviceName = resultSet.getString("serviceName");
            double price = resultSet.getDouble("price");
            String img = resultSet.getString("img");
            
            Service service = new Service(serviceId, serviceName, price, img);
            services.add(service);
        }
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        System.err.println("Error while retrieving services: " + ex.getMessage());
    }
    return services;
}


public ObservableList<Service> searchDocs(String serviceName) {
    ObservableList<Service> services = FXCollections.observableArrayList();
    String sql = "SELECT * FROM service WHERE serviceName=?";
    
    try{ Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         
        // Set the serviceName parameter
        preparedStatement.setString(1, serviceName);
        
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int serviceId = resultSet.getInt("serviceId");
                String name = resultSet.getString("serviceName");
                double price = resultSet.getDouble("price");
                String img = resultSet.getString("img");
                
                Service service = new Service(serviceId, name, price, img);
                services.add(service);
            }
             resultSet.close();
        }
        preparedStatement.close();
    } catch (SQLException ex) {
        System.err.println("Error while retrieving services: " + ex.getMessage());
    }
    return services;
}


}





