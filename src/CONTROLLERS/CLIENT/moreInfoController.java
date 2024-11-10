/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS.CLIENT;

import Models.CurrentOrder;
import Models.Orders;
import Services.OrderService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author WINDOWS 10
 */
public class moreInfoController implements Initializable {
   @FXML
    private AnchorPane moreInfo_form;
   
    @FXML
    private ListView<String> listV;
     
    
    @FXML
    private Label moreInfo_numberOrder;
OrderService ordsrv=new OrderService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       setOrdersList();
        
    }
    OrderService ordsvr=new OrderService();
    private Orders currentOrder;
    
   public void setOrdersList() {
    Orders order = CurrentOrder.getCurrentOrder();
    List<String> listInfo = new ArrayList<>();
       System.out.println(order.toString());
    
    listInfo.add("Service: " + order.getServices().getServiceName());
    listInfo.add("Order Num: " + order.getNum_order());
    listInfo.add("Total Price: $" + order.getPriceorder());
    listInfo.add("Date Of Ordering: " + order.getPickupDateTime().toString());
    listInfo.add("Statut: " + order.getStatus());
   listInfo.add("Your Phone number: " + "55125290");


    listV.getItems().addAll(listInfo);
    
}

      
}
