/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS.ADMIN;

import Models.Service;
import Models.mail;
import Services.ECOservice;
import util.MyConnection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author louay
 */
public class AdminserviceController implements Initializable {

    @FXML
    private AnchorPane service_form;
    @FXML
    private TableView<Service> service_tableView;
    @FXML
    private TableColumn<?, ?> service_col_service;
    @FXML
    private TableColumn<?, ?> service_col_PPK;
    @FXML
    private TableColumn<?, ?> service_col_DU;
    private TextField service_id;
    private ComboBox<?> service_clothType;
    private ComboBox<?> service_service;
    @FXML
    private TextField service_PPK;
    @FXML
    private ImageView service_imageView;
    @FXML
    private Button service_importBtn;
    @FXML
    private Button service_insertBtn;
    @FXML
    private Button service_updateBtn;
    @FXML
    private Button service_clearBtn;
    @FXML
    private Button service_deleteBtn;
    @FXML
    private ComboBox<String> service_type;
     private File selectedFile;
    @FXML
    private AnchorPane ADMINSERVICE1;
    @FXML
    private BarChart<?, ?> dashbaord_IPD_chart;
    @FXML
    private LineChart<?, ?> dashbaord_NOO_chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> services = FXCollections.observableArrayList();
        services.add("pressing");
        services.add("maintenance");
        services.add("delivery");

        // Assuming service_type is a ComboBox in your FXML file
        service_type.setItems(services);
        servicesShowData();
        // Set up a listener for the TableView's selection model
        service_tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Call servicesSelectData() with the selected Service object
            servicesSelectData(newValue);
        });
         // TO DISPLAY THE CHART OF NUMBER OF ORDERS
      List<String> recipients = getAllEmails();
displayNOOChart(recipients);

    sendEmailAutomatically();
        // TO DISPLAY THE CHART OF INCOME PER DAY
        displayIPDChart();
        
         

    }




// TO SHOW YOUR DATA IN OUR TABLEVIEW
    int id;
    ObservableList<Service> servicesListData = FXCollections.observableArrayList();
    ECOservice eco = new ECOservice();
    Alert alert;
    Connection cnx = MyConnection.getInstance().getCnx();





public void servicesSelectData(Service selectedService) {
   // Service selectedService = service_tableView.getSelectionModel().getSelectedItem();
    
    if (selectedService != null) {
        id = selectedService.getServiceId();
        service_type.setValue(selectedService.getServiceName());
        service_PPK.setText(String.valueOf(selectedService.getPrice()));

        // Load and display the image
        String imageUrl = selectedService.getImg();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image selectedImage = new Image( imageUrl, 103, 115, false, true);
            service_imageView.setImage(selectedImage);
        } else {
            // Handle the case where there is no image URL
            service_imageView.setImage(null);
        }
    }
}

public void servicesShowData() {
    servicesListData.clear(); // Clear the list before adding new data
    servicesListData.addAll(eco.getAll());

    service_col_service.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
    service_col_PPK.setCellValueFactory(new PropertyValueFactory<>("price"));
    service_col_DU.setCellValueFactory(new PropertyValueFactory<>("img"));

    service_tableView.setItems(servicesListData);
}

    @FXML
    private void servicesSelectData(MouseEvent event) {
    }

    @FXML
    private void servicesImportBtn(ActionEvent event) throws IOException {
        
     
         FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Choisir une image");
// Définir les filtres pour n'afficher que les fichiers image
fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter( "*.png", "*.jpg", "*.gif")
);

// Ouvrir la boîte de dialogue de sélection de fichier
File imageFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

if (imageFile != null) {
    // Load the selected image and set it to the ImageView
    Image image = new Image(imageFile.toURI().toString(),103, 115, false, true);
    service_imageView.setImage(image);
}

}
         
         
        
        
    

    @FXML
   
      public void servicesInsertBtn() {
 
        if (
                 service_type.getSelectionModel().getSelectedItem() == null
                || service_PPK.getText().isEmpty() ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        } else {
            try{
        Service serv =new Service ();
         
            double price = Double.parseDouble(service_PPK.getText());
        serv.setServiceName((String)service_type.getSelectionModel().getSelectedItem());
             serv.setPrice(price);
             
            serv.setImg(service_imageView.getImage().impl_getUrl());
            eco.ajouter(serv);
            alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                   servicesShowData();

        }
            
            
            
         catch (Exception e) {
                e.printStackTrace();
            }}
        }

    

    @FXML
private void servicesUpdateBtn(ActionEvent event) {
   // Call servicesSelectData() to ensure a valid item is selected
    Service selectedService = service_tableView.getSelectionModel().getSelectedItem();
    if (selectedService == null) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the item first");
        alert.showAndWait();
    } else {
        try {
            // Populate the fields with the selected service's data
           
            // Modify the selected service
            Service modifiedService = new Service();
            double price = Double.parseDouble(service_PPK.getText());
            modifiedService.setServiceId(selectedService.getServiceId());
            modifiedService.setServiceName((String)service_type.getSelectionModel().getSelectedItem());
            modifiedService.setPrice(price);
            modifiedService.setImg(service_imageView.getImage().impl_getUrl());

            //ECOservice eco = new ECOservice();
            eco.modifier(modifiedService);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Updated!");
            alert.showAndWait();

            // Refresh the TableView to reflect the changes
            servicesShowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    @FXML
    private void servicesClearBtn() {
        service_type.getSelectionModel().clearSelection();
        service_PPK.setText("");
        service_imageView.setImage(null);
    }

    @FXML
    private void servicesDeleteBtn(ActionEvent event) {
    //// Call servicesSelectData() to ensure a valid item is selected
    Service selectedService = service_tableView.getSelectionModel().getSelectedItem();

    if (selectedService == null) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the item first");
        alert.showAndWait();
    } else {
        int id = selectedService.getServiceId();
        try {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Service ID: " + id);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                eco.supprimer(id);
               // System.out.println("louay hna");

                // TO CLEAR ALL FIELDS
                servicesClearBtn();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();
                servicesShowData();

            } else {
                alert = new Alert(AlertType.WARNING);
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
//order income per day dashboard
 public void displayIPDChart() {
    dashbaord_IPD_chart.getData().clear();

    String sql = "SELECT DATE(pickupDateTime), SUM(priceOrder) FROM orders GROUP BY DATE(pickupDateTime) ORDER BY DATE(pickupDateTime)  ASC LIMIT 10";

    try {
        XYChart.Series chart = new XYChart.Series<>();
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (resultSet.next()) {
            // Get the date from the result set
            Date pickupDate = resultSet.getTimestamp(1);
            // Format the date to "yyyy-MM-dd" string
            String formattedDate = dateFormat.format(pickupDate);
            // Get the sum of priceOrder from the result set
            float totalPrice = resultSet.getFloat(2);
            // Add the formatted date and total price to the chart
            chart.getData().add(new XYChart.Data<>(formattedDate, totalPrice));
        }

        dashbaord_IPD_chart.getData().add(chart);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
 
 
 
 public List<String> getAllEmails() {
        List<String> emails = new ArrayList<>();

        String sql = "SELECT mailuser FROM user2";

        try {Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(sql); 
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("mailuser");
                emails.add(email);
            }
        } catch (Exception  e) {
            e.printStackTrace(); // Handle potential exceptions according to your needs
        }

        return emails;
    }
    //order numbers of orders
public XYChart.Series<String, Integer> displayNOOChart(List<String> recipientEmails) {
        XYChart.Series<String, Integer> chartData = new XYChart.Series<>();
         XYChart.Series chart = new XYChart.Series<>();

        String sql = "SELECT DATE(pickupDateTime), COUNT(DISTINCT orderId) FROM orders GROUP BY DATE(pickupDateTime) ORDER BY DATE(pickupDateTime) ASC ";

        try {
            Connection connection = MyConnection.getInstance().getCnx();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while (resultSet.next()) {
            Date date = resultSet.getTimestamp(1);
            String formattedDate = dateFormat.format(date);
            int customerCount = resultSet.getInt(2);
            chart.getData().add(new XYChart.Data<>(formattedDate, customerCount));
             chartData.getData().add(new XYChart.Data<>(formattedDate, customerCount));
            if (customerCount < 1) {
                // Prepare email content
                sendEmailAutomatically();
                Random random = new Random() ;
        int number = random.nextInt(101001) ;
                // Send email to all recipients in the list
                for (String recipient : recipientEmails) {
                    System.out.println(recipient);
                    
                      try {
                mail.envoi("Dear Our Student ", "check ours service now  your discount code"+number,recipient);
                 } catch (Exception e) {
                        e.printStackTrace(); 
                    }
                 //   sendEmail(host, port, mailFrom, password, recipient, subject, message, attachFiles);
                }
            }
        }

        dashbaord_NOO_chart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chartData;
    }
private void sendEmailAutomatically() {
    final String fromEmail = "dorra.jaidane@esprit.tn";
    final String password = "223JFT5027";
    final String toEmail = "dorrajaidanee@gmail.com";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("check our last orders ");
        message.setText("new services has been updated for you  ");

        Transport.send(message);

        System.out.println("Email sent successfully.");
    } catch (MessagingException e) {
        e.printStackTrace();
        System.out.println("Failed to send the email.");
    }

}



//  public void displayNOOChart() {
//
//
//    String sql = "SELECT DATE(pickupDateTime), COUNT(DISTINCT orderId)  FROM orders GROUP BY DATE(pickupDateTime) ORDER BY DATE(pickupDateTime) ASC ";
//
//    try {
//        XYChart.Series chart = new XYChart.Series<>();
//
//        Connection connection = MyConnection.getInstance().getCnx();
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        while (resultSet.next()) {
//            Date date = resultSet.getTimestamp(1);
//            String formattedDate = dateFormat.format(date);
//            int customerCount = resultSet.getInt(2);
//            chart.getData().add(new XYChart.Data<>(formattedDate, customerCount));
//        }
//
//        dashbaord_NOO_chart.getData().add(chart);
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//  }
}
