/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.user;
import Services.userService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AuthoontificationFXMLController implements Initializable {

    @FXML
    private TextField inputUsername;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelMdp;
    private PasswordField inputMdp;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonSignUp;

    /**
     * Initializes the controller class.
     */
    
   //private MyConnection cnx ; 
    private PreparedStatement prepare ;
    private ResultSet result ;
    private double x=0;
    private double y=0;
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    @FXML
    private Hyperlink login_forgotPassword;
    @FXML
    private CheckBox login_selectShowPassword;
   
    @FXML
    private TextField login_showPassword1;
    @FXML
    private PasswordField login_password;
    @FXML
    private AnchorPane login;
    
    @FXML
 
    public void loginUser() throws IOException {
    if (("admin".equals(inputUsername.getText())) || ("admin123".equals(login_password.getText()))) {
        loginadmin();
    }

    String sql = "SELECT * FROM user2 WHERE `mailuser`=? and `mdpuser`=? ";

    try {
        prepare = cnx.prepareStatement(sql);
        prepare.setString(1, inputUsername.getText());
        prepare.setString(2, login_password.getText());
        result = prepare.executeQuery();
        Alert alert;
        if (login_selectShowPassword.isSelected()) {
            login_password.setText(login_showPassword1.getText());
        } else {
            login_showPassword1.setText(login_password.getText());
        }
        if (inputUsername.getText().isEmpty() || login_password.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR Message ");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank field");
            alert.showAndWait();
        } else {
            if (result.next()) {
                if(result.getBoolean("isBlocked")) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR Message ");
                    alert.setHeaderText(null);
                    alert.setContentText("User is blocked");
                    alert.showAndWait();
                    return;
                }
                /*else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR Message ");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password");
                alert.showAndWait();
            }*/
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("INFORMATION Message ");
                alert.setHeaderText(null);
                alert.setContentText("SUCCESSFULY LOGIN ");
                alert.showAndWait();
                
                userService userService = new userService();
                String email = inputUsername.getText();

                
                user user = userService.afficherUser(email);

               
                UserSession.login(user);

                buttonLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            } 
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    @FXML
     public void showPassword() {

        if (login_selectShowPassword.isSelected()) {
            login_showPassword1.setText(login_password.getText());
            login_showPassword1.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword1.getText());
            login_showPassword1.setVisible(false);
           login_password.setVisible(true);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void Forgetpassword() {
           try {      login_forgotPassword.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("ResetPasswordController.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
                 
                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                     });
                      root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
        });
                
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        
        
        stage.show();
    } catch(Exception e){
        e.printStackTrace();
    }
    }
    
    
    @FXML            
    public void SignUp(){            
    try {      buttonSignUp.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("Singup.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
                 
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
                
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
                
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    } catch(Exception e){
        e.printStackTrace();
    }
}
   public void loginadmin() throws IOException{ 
     try{ Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("INFORMATION Message ");
                    alert.setHeaderText(null);
                    alert.setContentText("WELCOME ADMIN ");
                    alert.showAndWait();
                    buttonLogin.getScene().getWindow().hide(); 
                  Parent root = FXMLLoader.load(getClass().getResource("BACKUSERS.fxml"));
                                   // Parent root = FXMLLoader.load(getClass().getResource("/ADMIN/Adminservice.fxml"));

                     Stage stage = new Stage();
                         Scene scene = new Scene(root);
                 
                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                     });
                      root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
        });
                
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        
        
        stage.show();
     }catch (Exception e){
        e.printStackTrace();
    }
    }}
    

