/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ItemlistusersController implements Initializable {

    @FXML
    private Label prenomuser;
    @FXML
    private Label nomuser;
    @FXML
    private Label emailuser;
    @FXML
    private Label classeuser;
    @FXML
    private Label adresseuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void initialize(user us){
        nomuser.setText(us.getNomuser());
        prenomuser.setText(us.getPrenomuser());
        emailuser.setText(us.getMailuser());
        classeuser.setText(us.getClasseuser());
        adresseuser.setText(us.getAdressuser());
        
    }
    
}
