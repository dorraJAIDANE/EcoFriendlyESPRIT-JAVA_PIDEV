/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import Models.Commentaire;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class ItemBackCommentsController implements Initializable {

    @FXML
    private Label inputPrenom;
    @FXML
    private Label inputNom;
    @FXML
    private Label inputCommentaire;
    @FXML
    private Label inputDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
    public void initialize(Commentaire commentaire){
        inputNom.setText(commentaire.getNomUser());
        inputPrenom.setText(commentaire.getPrenomUser());
        inputCommentaire.setText(commentaire.getDescription());
        inputDate.setText(commentaire.getDateCreation().toString());
    }
    
}
