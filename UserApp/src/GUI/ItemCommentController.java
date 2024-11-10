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
import javafx.scene.image.ImageView;
import Models.Commentaire;

/**
 * FXML Controller class
 *
 * @author Grati Eya
 */
public class ItemCommentController implements Initializable {


    @FXML
    private Label labelnomuser;
    @FXML
    private Label labelprenomuser;
    @FXML
    private Label labelcommentaire;
    @FXML
    private Label labeldate;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void initialize(Commentaire commentaire){
        labelnomuser.setText(commentaire.getNomUser());
        labelprenomuser.setText(commentaire.getPrenomUser());
        labelcommentaire.setText(commentaire.getDescription());
        labeldate.setText(commentaire.getDateCreation().toString());
    }

}
