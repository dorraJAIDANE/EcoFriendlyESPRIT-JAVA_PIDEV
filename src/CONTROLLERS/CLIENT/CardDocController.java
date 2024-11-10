/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS.CLIENT;

import static GUI.UserSession.getCurrentUser;
import INTERFACES.HistoriqueDocumentsService;
import Models.CurrentDocument;
import Models.Document;
import Models.HistoriqueDocument;
import Models.user;
import Services.HistoriqueServiceImp;
import java.io.IOException;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class CardDocController implements Initializable {
    private static user currentUser=getCurrentUser();

    @FXML
    private AnchorPane card_form_D;
    @FXML
    private Label doc_name_d;
    @FXML
    private Label type_doc_d;
    @FXML
    private Button card_moreInfo_D;
    @FXML
    private ImageView showImage_D;

    private  Document doc=new Document();
    /**
     * Initializes the controller class.
     */
    private  static Document doctomodif=new Document();
    @FXML
    private Button card_updateinfo_D1;
    
    private HistoriqueDocumentsService historiqueService=new HistoriqueServiceImp();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void moreInfoBtnD(ActionEvent event) throws IOException {
          try{
           
                              CurrentDocument.setDocument(doc);

            Parent root = FXMLLoader.load(getClass().getResource("/GUI/PopUpDoc.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            historiqueService.addhistorique(new HistoriqueDocument(1,currentUser.getIduser(),doc.getIdDoc(),"v"));
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.show();
            
        }catch(Exception e){e.printStackTrace();}
                doctomodif=doc;
      
    }
    public void setDocument(Document d){
        doc=d;
        if (d != null && "valid".equals(d.getIsvalid())) {
            doc_name_d.setText(d.getDocumentName());
            type_doc_d.setText(d.getDocumentType());
           Image image = new Image(d.getDocumentImage());
           showImage_D.setImage(image);
        }
    }

    public static Document getDoctomodif() {
        return doctomodif;
    }

    public static void setDoctomodif(Document doctomodif) {
        CardDocController.doctomodif = doctomodif;
    }

    @FXML
    private void updateInfoBtnD(ActionEvent event) throws IOException {
    
         
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutDocuments.fxml"));
                AnchorPane pane = loader.load();
                AjoutDocumentsController ajoutDocumentsController = loader.getController();
                
                ajoutDocumentsController.setDocument(doc);
                doctomodif=doc;
    }

    public Button getCard_moreInfo_D() {
        return card_moreInfo_D;
    }

    public Button getCard_updateinfo_D1() {
        return card_updateinfo_D1;
    }
    
}
