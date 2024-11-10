/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS.CLIENT;

import Models.Orders;
import Models.Service;
import Services.OrderService;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author louay
 */
public class ServicescardsController implements Initializable {
    private String serviceName;
    private double price;
    private String path;
    private int id;
    @FXML
    private StackPane card_form;
    @FXML
    private Label card_name;
    @FXML
    private ImageView card_imageView;
    @FXML
    private Label card_serviceName;
    @FXML
    private Label card_price;
    @FXML
    private Hyperlink links;
    @FXML
    private Button card_addBtn;
     private Service getStomodify;
    private Image image;
    private Alert alert;
     int randomInRange = (int) (Math.random() * 100);
    OrderService Ordserv=new OrderService();
    
    
    
        public void setData(Service getS) {
            if(getS!=null){
            getStomodify=getS;
            id = getS.getServiceId();
        
            price = getS.getPrice();
            String uri = getS.getImg();
            path = getS.getImg();
            card_name.setText(getS.getServiceName());

            //card_serviceName.setText(serviceName);
            card_price.setText("$" + price);

            image = new Image(uri, 225, 102, false, true);
            card_imageView.setImage(image);
            }

        }
@FXML

void links(ActionEvent event) {

    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
@FXML
    private void addBtn(ActionEvent event) {
      Orders ord=new Orders();
              ord.setServices(getStomodify);
              ord.setNum_order(randomInRange);
              ord.setStatus("disponible");
              ord.setPriceorder((float) getStomodify.getPrice());
              ord.setPickupDateTime(new Date());
        Ordserv.ajouterorder(ord);
        
    }
    
}
