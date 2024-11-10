/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static GUI.UserSession.getCurrentUser;
import INTERFACES.IOrders;
import Models.Orders;
import Models.Service;
import Models.user;
import util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author louay
 */
public class OrderService implements IOrders<Orders> {
    private static user currentUser=getCurrentUser();
    private ObservableList<Service> servicesList = FXCollections.observableArrayList();
    private ECOservice eco=new ECOservice();
    @Override
public void ajouterorder(Orders order) {
    String insertQuery = "INSERT INTO `orders`( `num_order`, `userId`, `pickupDateTime`, `status`, `services`, `phonenumber`, `priceOrder`) VALUES (?, ?, ?, ?, ?,?,?)";
    
    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        int i=1;
        // Set values for the prepared statement
        preparedStatement.setInt(i++, order.getNum_order());
        preparedStatement.setInt(i++,currentUser.getIduser() ); // Assuming getUserId() retrieves the user's ID
        preparedStatement.setTimestamp(i++, new Timestamp(order.getPickupDateTime().getTime()));
        preparedStatement.setString(i++, order.getStatus());
        preparedStatement.setInt(i++, order.getServices().getServiceId());
                preparedStatement.setString(i++, order.getPhonenumber());

        preparedStatement.setFloat(i++, order.getPriceorder());

        // Execute the SQL statement
        preparedStatement.executeUpdate();
        
        System.out.println("Order added successfully!");
        
        // Close the resources (preparedStatement and connection)
        preparedStatement.close();
    } catch (SQLException ex) {
        System.err.println("Error while adding order: " + ex.getMessage());
    }
}


    

    @Override
public void supprimerorder(int id) {
    String deleteQuery = "DELETE FROM orders WHERE orderId = ?";
    
    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        
        // Set the ID parameter for the prepared statement
        preparedStatement.setInt(1, id);
        
        // Execute the SQL statement to delete the order
        int rowsDeleted = preparedStatement.executeUpdate();
        
        if (rowsDeleted > 0) {
            System.out.println("Order with ID " + id + " deleted successfully!");
        } else {
            System.out.println("No order found with ID " + id);
        }
        
        // Close the resources (preparedStatement and connection)
        preparedStatement.close();
       // connection.close();
    } catch (SQLException ex) {
        System.err.println("Error while deleting order: " + ex.getMessage());
    }
}

   
 @Override
public Orders getOneorder(int id) {
    Orders order = null;
    String selectQuery = "SELECT * FROM orders WHERE orderId = ?";
    
    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            int orderId = resultSet.getInt("orderId");
            int numOrder = resultSet.getInt("num_order");
            int userId = resultSet.getInt("userId");
            // Retrieve other fields from the result set
            String status = resultSet.getString("status");
            Date pickupDateTime = resultSet.getTimestamp("pickupDateTime");
            int serviceId = resultSet.getInt("services");
            String phonenumber = resultSet.getString("phonenumber");
            float priceorder=resultSet.getFloat("priceorder");
            //int idus =currentUser.getIduser();
             
            
            // Retrieve Service object (assuming you have a ServiceDAO to retrieve Service by ID)
            Service service = eco.getOne(serviceId);
            
            // Create an Orders object with retrieved data
            order = new Orders();
            order.setOrderId(orderId);
            order.setNum_order(numOrder);
            order.setuser(currentUser);
            order.setPickupDateTime(pickupDateTime);
            order.setStatus(status);
            order.setServices(service);
            order.setPhonenumber(phonenumber);
            order.setPriceorder(priceorder);
        } else {
            System.out.println("No order found with ID " + id);
        }
        
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException ex) {
        System.err.println("Error while retrieving order: " + ex.getMessage());
    }
    
    return order;
}


        

    @Override
    public List<Orders> getAllorder() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
public float orderTotal() {
    String sql = "SELECT SUM(priceOrder) FROM orders WHERE userId = ?";

    float totalP = 0;

    try {Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        preparedStatement.setInt(1, currentUser.getIduser()); 
        try (ResultSet result = preparedStatement.executeQuery()) {
            if (result.next()) {
                totalP = result.getFloat(1); 
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalP;
}


   
   
public ObservableList<Orders> getorderbyidUser(int id) {
    List<Orders> ordersList = new ArrayList<>();
    String selectQuery = "SELECT * FROM orders WHERE userId = ?";
    
    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            int orderId = resultSet.getInt("orderId");
            int numOrder = resultSet.getInt("num_order");
            int userId = resultSet.getInt("userId");
            // Retrieve other fields from the result set
            String status = resultSet.getString("status");
            Date pickupDateTime = resultSet.getTimestamp("pickupDateTime");
            int serviceId = resultSet.getInt("services");
            String phonenumber = resultSet.getString("phonenumber");
            float priceorder = resultSet.getFloat("priceorder");
            
            // Retrieve User object (assuming you have a UserDAO to retrieve User by ID)
          //  user user1 = new User (userId);
            // User user = user.getUserId();
            
            // Retrieve Service object (assuming you have a ServiceDAO to retrieve Service by ID)
            Service service = eco.getOne(serviceId);
            
            // Create an Orders object with retrieved data
            Orders order = new Orders();
            order.setOrderId(orderId);
            order.setNum_order(numOrder);
            order.setuser(currentUser);
            order.setPickupDateTime(pickupDateTime);
            order.setStatus(status);
            order.setServices(service);
            order.setPhonenumber(phonenumber);
            order.setPriceorder(priceorder);
            
            ordersList.add(order);
        }
        
        resultSet.close();
        preparedStatement.close();
        //connection.close();
    } catch (SQLException ex) {
        System.err.println("Error while retrieving orders: " + ex.getMessage());
    }
    
   return FXCollections.observableArrayList(ordersList);
}

   
   

 public void updateOrderStatusToPayed(int userId) {
        String updateQuery = "UPDATE orders SET status = 'payed' WHERE userId = ?";
        try {Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery) ;
            preparedStatement.setInt(1, 1);
            preparedStatement.executeUpdate();
            System.out.println("status of order has been updeted to payed");
             preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 public List<Orders> getPayedOrders(int userId) {
    ObservableList<Orders> payedOrders = FXCollections.observableArrayList();
    String sql = "SELECT * FROM orders WHERE userId = ? AND status = 'payed'"; 

    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId); // Set the userId as a parameter

        ResultSet resultSet = preparedStatement.executeQuery(); 

        while (resultSet.next()) {
            // Retrieve order fields from the result set
            int orderId = resultSet.getInt("orderId");
            int numOrder = resultSet.getInt("num_order");
            String status = resultSet.getString("status");
            Date pickupDateTime = resultSet.getTimestamp("pickupDateTime");
            int serviceId = resultSet.getInt("services");
            String phonenumber = resultSet.getString("phonenumber");
            float priceorder = resultSet.getFloat("priceorder");

            Service service = eco.getOne(serviceId);
            
            // Create an Orders object with retrieved data
            Orders order = new Orders();
            order.setOrderId(orderId);
            order.setNum_order(numOrder);
            order.setuser(currentUser); // Set the current user as the order's user
            order.setPickupDateTime(pickupDateTime);
            order.setStatus(status);
            order.setServices(service);
            order.setPhonenumber(phonenumber);
            order.setPriceorder(priceorder);

            // Add the order to the list
            payedOrders.add(order);
        }

        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return payedOrders;
}
 
 public void updatePhoneNumberInOrdersTable(int userId, String phoneNumber) {
    String updateSql = "UPDATE orders SET phonenumber = ? WHERE userId = ?";

    try {Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement preparedStatement = connection.prepareStatement(updateSql);

        preparedStatement.setString(1, phoneNumber);
        preparedStatement.setInt(2, userId);

        // Execute the update query
        int rowsAffected = preparedStatement.executeUpdate();

       
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
 
}