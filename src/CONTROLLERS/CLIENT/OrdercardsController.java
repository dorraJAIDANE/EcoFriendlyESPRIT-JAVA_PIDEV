/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS.CLIENT;

import Models.CurrentOrder;
import Models.Orders;
import Models.Service;
import Services.OrderService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author louay
 */
public class OrdercardsController implements Initializable {

    @FXML
    private AnchorPane card_form;
    @FXML
    private Label order_number;
    @FXML
    private Label card_datOrder;
    @FXML
    private Button card_moreInfo;
    private OrderService ordserv;
    private Orders ordrmodif;
    private int order_id;
    /**
     * Initializes the controller class.
     */
     public void setData(Orders order) {
            ordrmodif =order ;
            order_id = order.getOrderId();
           card_datOrder.setText(order.getPickupDateTime().toString()); 
             order_number.setText(String.valueOf(order.getNum_order()));
}
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void moreInfoBtn(ActionEvent event) {
         try{
        //    data.cID = id;
            CurrentOrder.setCurrentOrder(ordrmodif);
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/moreInfo.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.show();
            
        }catch(Exception e){e.printStackTrace();}
        
        
    }
    
}
