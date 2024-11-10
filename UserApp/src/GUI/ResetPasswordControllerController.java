/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Models.user;
import Models.mail;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.mindrot.jbcrypt.BCrypt;
import Services.userService;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ResetPasswordControllerController implements Initializable {

    userService userService = new userService();
    @FXML
    private TextField textEmail;
    @FXML
    private Button buttonreset; 
    @FXML
    private Button BACK;
      private double x=0;
    private double y=0;
    @FXML


    
    private void Envoyer(ActionEvent event) throws SQLDataException, Exception {
       
       
        if(!userService.checkEmailExist(textEmail.getText())){
       
                Notifications.create().title("Warnning").text("No email Found").position(Pos.BOTTOM_RIGHT).showError();

       
        }else{
           
            user u = userService.findUserByEmail(textEmail.getText());
           
            Random random = new Random() ;
        int number = random.nextInt(101001) ;
           
                 String pass = String.valueOf(number);
                pass = BCrypt.hashpw(pass, BCrypt.gensalt(13));
                 pass= pass.replaceFirst("a", "y");
                u.setMdpuser(pass);
           
            userService.mettreAJouruser(u);
       
                    mail.envoi(textEmail.getText(), "Nouveau Password", " your verification code"+number);

         Notifications.create().title("Success").text("Vous avez récu un email avec votre mdp").position(Pos.BOTTOM_RIGHT).showError();

                  Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/GUI/changepassword.fxml"));
                            Stage myWindow = (Stage) textEmail.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AuthoontificationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        }
       
       
    }
    @FXML
    public void retu (){
         try {      BACK.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("AuthentificationInterface.fxml"));
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
}
