/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static javax.management.remote.JMXConnectorFactory.connect;
import static javax.management.remote.JMXConnectorFactory.connect;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ChangepasswordController implements Initializable {

    @FXML
    private PasswordField changePass_password;
    @FXML
    private PasswordField changePass_cPassword;
    @FXML
    private Button changePass_proceedBtn;
    private TextField forgot_username;
     MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    private PreparedStatement prepare ;
    @FXML
    private TextField verifacation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
   
     public void changePassword(){
        
        Alert alert ;
        
        if(changePass_password.getText().isEmpty() || changePass_cPassword.getText().isEmpty()||verifacation.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERROR Message ");
               alert.setHeaderText(null);
               alert.setContentText("Please fill all blank field");
               alert.showAndWait();
        }else if(!changePass_password.getText().equals(changePass_cPassword.getText())){
          alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("ERROR Message ");
                alert.setHeaderText(null);
                alert.setContentText("Password does not match");
                 alert.showAndWait();
        }else if(changePass_password.getText().length() < 8){
            alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("ERROR Message ");
                 alert.setHeaderText(null);
                 alert.setContentText("Invalid Password, at least 8 characters needed");
                  alert.showAndWait();
        }else{
            
            String updateData = "UPDATE user2 SET mdpuser = ?"
                    + "WHERE mdpuser = '" + verifacation.getText() + "'";
            
            
            try{
                
                prepare = cnx.prepareStatement(updateData);
                prepare.setString(1, changePass_password.getText());
                prepare.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION Message ");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully changed Password");
                    alert.showAndWait();
               
                
              
                changePass_password.setText("");
                changePass_cPassword.setText("");
                
            }catch(Exception e){e.printStackTrace();}
            
        }
        
    }

    
}
