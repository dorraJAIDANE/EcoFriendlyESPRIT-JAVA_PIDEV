/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS.CLIENT;
import APIs.SMSManager;
import GUI.UserSession;
import static GUI.UserSession.getCurrentUser;
import Models.Orders;
import Models.Service;
import Models.user;
import Services.ECOservice;
import Services.OrderService;
import util.MyConnection;
//import java.awt.Insets;
import javafx.geometry.Insets;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.*;
import java.util.Base64;
import java.io.*;
import java.util.HashMap;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.StageStyle;
import static javax.management.remote.JMXConnectorFactory.connect;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author louay
 */
public class FXMLController implements Initializable {
  @FXML
    private Button biblio_btn;

    @FXML
    private StackPane carouselContainer;

    @FXML
    private AnchorPane caroussel;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button event_btn;

    @FXML
    private Button forum_btn;

    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button nextButton;


    @FXML
    private TextField order_amount;

    @FXML
    private Label order_change;

    @FXML
    private TableColumn<?, ?> order_col_orderID;

    @FXML
    private TableColumn<?, ?> order_col_service;

    @FXML
    private TableColumn<?, ?> order_col_type;


    @FXML
    private GridPane order_gridPane;

    @FXML
    private Button order_payBtn;

    @FXML
    private Button order_rating;

    @FXML
    private Button order_receiptBtn;

    @FXML
    private Button order_refreshBtn;

    @FXML
    private Button order_removeBtn;

    @FXML
    private ScrollPane order_scrollPane;

    @FXML
    private TableView<Orders> order_tableView;

    @FXML
    private Label order_total;

    @FXML
    private Button prevButton;

    @FXML
    private Button services_btn;

    @FXML
    private Button sms;

    @FXML
    private Button transport_btn;

    @FXML
    private Label username;
    @FXML
    private AnchorPane dashboardmain_form;
    private AnchorPane service_form;
    @FXML
    private TextField numrotel;
    @FXML
    private Button HISTO;
    @FXML
    private AnchorPane HISTO_form;
    @FXML
    private ScrollPane ordercardsscrollpane;
    @FXML
    private GridPane customers_gridPane;
    @FXML
    private TextField service_reclamation;
    @FXML
    private ComboBox<?> comments;
    @FXML
    private TextField order_reclamation;
    @FXML
    private Button goback;

    
    //    DISPLAY TOTAL AMOUNT
    private float totalP;
    private static user currentUser=getCurrentUser();

    OrderService Osrv = new OrderService();
    ObservableList<Orders> ordersList = FXCollections.observableArrayList();
    @FXML
    private TextField servicerechercher;
    @FXML
    private Button servicesearch;
    @FXML
    private Button gotobasket;
    @FXML
    private AnchorPane myserviceform;
    @FXML
    private AnchorPane serviceform;
    private AnchorPane payform;
    @FXML
    private AnchorPane basketform;
    @FXML
    private Button backtomyservice;
        private Connection connect;
    @FXML
    private Button SubmitRating;
    @FXML
    private Button dasborbprofile;
    @FXML
    private Button logout_btn1;

    
    
    
    
// TO SHOW YOUR DATA IN OUR TABLEVIEW
//       int id;
//public void selectedOrderfromTBV(Orders selectedOrder) {
//    if (selectedOrder != null) {
//        id = selectedOrder.getOrderId(); // Assuming 'id' is a class variable
//        order_col_orderID.setText(String.valueOf(selectedOrder.getOrderId())); // Assuming order_col_orderID is a Label or Text component
//        order_col_type.setText(selectedOrder.getService().getServiceName()); // Assuming order_col_type is a Label or Text component

          // display orders in tableview
    
  
    public void OrderShowData() {
        ordersList.clear(); // Clear the list before adding new data
        ordersList.addAll(Osrv.getorderbyidUser(currentUser.getIduser()));
        order_col_orderID.setCellValueFactory(new PropertyValueFactory<>("num_order"));
        order_col_type.setCellValueFactory(new PropertyValueFactory<>("status"));
        order_col_service.setCellValueFactory(new PropertyValueFactory<>("priceorder"));

// Set the items of the TableView
        order_tableView.setItems(ordersList);

    }
    
    public void displayTotal() {
      totalP=  Osrv.orderTotal();
        order_total.setText("$" + totalP);
    }

    private SMSManager smsManager = new SMSManager();

    @FXML
    void SMS(ActionEvent event) {
        String phoneNumber = numrotel.getText();
        int userId = currentUser.getIduser(); // Rget the current user's ID
        Osrv.updatePhoneNumberInOrdersTable(1, phoneNumber);
        System.out.println(phoneNumber);
        String receiverPhoneNumber = "+216"+numrotel.getText();
        System.out.println(receiverPhoneNumber);
        String message = "This is ECOFRIENDLY ESPRIT !!! your order has been submitted please check your teckts for more details if you have any issues please call us or visit our website !";
        smsManager.sendSMS(receiverPhoneNumber,message);
           Notifications.create().title("Success").text("Psssssst !Check Your Phone").position(Pos.BOTTOM_RIGHT).showError();


    }
    

        private float orderAmount;
    private float orderChange;

    @FXML
    public void orderAmount() {
     totalP=   Osrv.orderTotal();

        if (order_amount.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Something add money");
            alert.showAndWait();
        } else if (totalP > Float.parseFloat(order_amount.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Solde Insuffisant");
            alert.showAndWait();
            order_amount.setText("");
        } else {
            orderAmount = Float.parseFloat(order_amount.getText());
            orderChange = (orderAmount - totalP);
            order_change.setText("$" + orderChange);
        }
    }
    

    @FXML
    void orderPayBtn(ActionEvent event) {

    // TO GET THE TOTAL VALUE
    float totalP = Osrv.orderTotal();

    if (totalP == 0) {
        // Display an error message if totalP is zero
        showAlert(Alert.AlertType.ERROR, "Error Message", null, "Please order first");
    } else {
        // Confirmation Dialog
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Message");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure?");
        Optional<ButtonType> option = confirmationAlert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            // Database Operations: Update order status to "payed"
            Osrv.updateOrderStatusToPayed(1);

            // INDICATES YOUR ORDER IS SUCCESSFULLY TO BE ORDERED
            showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Ordered Successfully!");
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning Message", null, "Cancelled order");
        }
    }
}

// Helper method to show alerts
private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
}

    

    @FXML
    void orderReceiptBtn(ActionEvent event) {
          if (totalP == 0 && order_amount.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please order first");
            alert.showAndWait();
        } else {
            //orderCustomerID();

       /*     HashMap hash = new HashMap();
            hash.put("receiptValue", username);
*/
            try {

                JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\louay\\Desktop\\validation1\\src\\APIs\\Orderreport1.jrxml");
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);

                JasperViewer.viewReport(jPrint, false);
                             
                // TO CLEAR ALL FIELDS
                totalP = 0;
                order_total.setText("$0.0");
                order_amount.setText("");
                order_change.setText("$0.0");

                // TO UPDATE THE TABLEVIEW
                    OrderShowData();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void orderRefreshBtn(ActionEvent event) {
        OrderShowData();
// orderDisplayOrder();
        displayTotal();

    }
Alert alert;
    @FXML
    void orderRemoveBtn(ActionEvent event) {OrderService ordserv=new OrderService();
Orders selectedOrder = order_tableView.getSelectionModel().getSelectedItem();

    if (selectedOrder == null) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the item first");
        alert.showAndWait();
    } else {
        int id = selectedOrder.getOrderId();
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Service ID: " + id);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                ordserv.supprimerorder(id);

                // TO CLEAR ALL FIELDS
              //  servicesClearBtn();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();
                OrderShowData();

            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    

    
    @FXML
    void orderSelectOrder(MouseEvent event) {

    }

    @FXML
    void orderrating(ActionEvent event) {

    }

    void switchForm(ActionEvent event) {

    }
    
    void orderAmount(ActionEvent event) {

    }
    
    
    // Alert alert;
    ECOservice eco = new ECOservice();
    OrderService ordserv = new OrderService();
    private int currentIndex = 0;
    private String[] imagePaths = {
        "/STYLING/pic2.png",
        "/STYLING/pic1.jpg",
        "/STYLING/pic3.jpg"
    };
    
    
    
    private void initialize() {
        showImage(currentIndex);

        nextButton.setOnAction(event -> {
            currentIndex = (currentIndex + 1) % imagePaths.length;
            showImage(currentIndex);
        });

        prevButton.setOnAction(event -> {
            currentIndex = (currentIndex - 1 + imagePaths.length) % imagePaths.length;
            showImage(currentIndex);
        });
//        order_tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//    selectedOrderfromTBV(newValue); // Call the method when an item is selected
//});

    }
    private void showImage(int index) {
        String imagePath = imagePaths[index];
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(558); 
        carouselContainer.getChildren().setAll(imageView);
    }
    

    @FXML
    private void Historic(ActionEvent event) {
        if (event.getSource() == HISTO) {
                        basketform.setVisible(false);
                          HISTO_form.setVisible(true);
                              List<Orders>LSord=Osrv.getorderbyidUser(currentUser.getIduser());
                                initializeOrderCards();
                         }
    }
    
    @FXML

   public void logout() {

        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                
                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/AuthentificationInterface.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private final ObservableList<Service> listD = FXCollections.observableArrayList();

    @FXML
    private void goback(ActionEvent event) {
         if (event.getSource() == goback) {
                        basketform.setVisible(true);
                          HISTO_form.setVisible(false);
                         }
    }
    
    public void initializeCards(List<Service> Services) {
    try {
        int row = 0;
        int column = 1;
        for (int i = 0; i < Services.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/servicescards.fxml"));
            AnchorPane pane = loader.load();
            ServicescardsController controller = loader.getController();
            controller.setData(Services.get(i));
            order_gridPane.add(pane, column, row);
            column += 1;
            if (column > 2) {
                column = 1;
                row += 1;
            }
        }
    } catch (IOException ex) {
        Logger.getLogger(ServicescardsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
   public void initializeOrderCards() {
    try {
         List<Orders> paidOrders = Osrv.getPayedOrders(1); // Retrieve paid orders
        int column = 0;
        int row = 0;

        for (int i = 0; i < paidOrders.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ordercards.fxml"));
            AnchorPane pane = loader.load();
            OrdercardsController controller = loader.getController();
            controller.setData(ordersList.get(i));

            if (column == 4) { // Change this number based on your requirements
                row++;
                column = 0;
            }

            customers_gridPane.setMinHeight(GridPane.USE_COMPUTED_SIZE);
            customers_gridPane.setPrefHeight(GridPane.USE_COMPUTED_SIZE);
            customers_gridPane.setMaxHeight(GridPane.USE_PREF_SIZE);

            customers_gridPane.setMinWidth(GridPane.USE_COMPUTED_SIZE);
            customers_gridPane.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
            customers_gridPane.setMaxWidth(GridPane.USE_PREF_SIZE);

            customers_gridPane.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(10));
        }
    } catch (IOException ex) {
        Logger.getLogger(ServicescardsController.class.getName()).log(Level.SEVERE, null, ex);
    }
}       


   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        initialize();
        List<Service> lS=eco.getAll();
        initializeCards(lS);
    
        
        // TODO
         order_amount.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                orderAmount();
            }
        });
         user user = UserSession.getCurrentUser();
         username.setText(user.getPrenomuser());
    }    

    @FXML
    private void servicesearch(ActionEvent event) {
            String serviceName=servicerechercher.getText();
            if(servicerechercher.getText().isEmpty())
            {
            showAlert(Alert.AlertType.WARNING, "error", "Add a name service ", "write a valide name ");
            }
            else {
           // System.out.println(serviceName);
            ObservableList<Service> filteredServices = eco.searchDocs(serviceName);
            System.out.println(filteredServices.toString());
            
                 order_gridPane.getChildren().clear();
                this.initializeCards(filteredServices);
            }
    }

    @FXML
    private void gotobasket(ActionEvent event) {  if (event.getSource() == gotobasket) {
                        serviceform.setVisible(false);
                          basketform.setVisible(true);
    }
}

    @FXML
    private void backtomyservice(ActionEvent event) {if (event.getSource() == backtomyservice) {
                        serviceform.setVisible(true);
                          basketform.setVisible(false);
    }
}

    @FXML
    private void SubmitRating(ActionEvent event) {
    }
    private void buttonDashbord7(ActionEvent event) throws IOException {
       
    }

    @FXML
    private void buttonTransport(ActionEvent event) {
         /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mainform.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));*/
    }

    @FXML
    private void buttonBiblio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LibrarydorraController.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

    @FXML
    private void buttonEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI_Events/FXML.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonServices(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mainform.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void buttonForum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    @FXML
    private void profile(ActionEvent event) throws IOException  {
         try {      
             dashboard_form.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/profile.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
                 
                    /*root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                     });
                      root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
        });*/
                
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        
        
        stage.show();
    } catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void buttonDashboard7(ActionEvent event)throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXMLDocument.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}