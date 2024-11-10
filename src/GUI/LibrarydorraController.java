/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import API.GoogleDriveService;
import CONTROLLERS.CLIENT.AffichageDocumentsController;
import CONTROLLERS.CLIENT.CardDocController;
import static GUI.UserSession.getCurrentUser;
import INTERFACES.DocumentService;
import INTERFACES.NiveauService;
import INTERFACES.SemestreService;
import INTERFACES.TopicService;
import Models.Document;
import Models.HistoriqueDocument;
import Models.Niveau;
import Models.SearchDocumentDTO;
import Models.Semestre;
import Models.Topic;
import Models.user;
import Services.DocumentServiceImp;
import Services.NiveauServiceImp;
import Services.SemestreServiceImp;
import Services.TopicServiceImp;
//import com.google.api.client.http.FileContent;
//import com.google.api.services.drive.Drive;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class LibrarydorraController implements Initializable {

    @FXML
    private AnchorPane aanchorpane2_d;
    @FXML
    private AnchorPane aanchorpane3_d;
    @FXML
    private AnchorPane aanchorpane4_d;
    @FXML
    private ScrollPane ascrollpane_d;
    @FXML
    private GridPane agridpane_d;
    @FXML
    private AnchorPane aanchorpane5_d;
    @FXML
    private Button order_receiptBtn;
    @FXML
    private ComboBox<Niveau> aniveau_d;
    @FXML
    private ComboBox<Topic> atopic_d;
    @FXML
    private ComboBox<String> atype_d;
    @FXML
    private TextField anomdoc_d;
    @FXML
    private ComboBox<Semestre> asemestre_d;
    @FXML
    private Separator aseparator_d;
Alert alert;
        private static user currentUser=getCurrentUser();

    private NiveauService niveauService=new NiveauServiceImp();
    private SemestreService semestreService=new SemestreServiceImp();
    private TopicService topicService=new TopicServiceImp();
    private DocumentService documentService=new DocumentServiceImp();
    @FXML
    private ImageView adocImage_d;
    @FXML
    private Button uploadDoc_d;
    @FXML
    private Button modifbutton;
    @FXML
    private AnchorPane dorra1;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Label username;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button biblio_btn;
    @FXML
    private Button event_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private Button services_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private AnchorPane dorra2;
    @FXML
    private AnchorPane sanchorpane2_d;
    @FXML
    private AnchorPane sanchorpane3_d;
    @FXML
    private AnchorPane sanchorpane4_d;
    @FXML
    private ScrollPane sscrollpane_d;
    @FXML
    private GridPane sgridpane_d;
    @FXML
    private AnchorPane sanchorpane5_d;
    @FXML
    private Button order_receiptBtn1;
   @FXML
    private ComboBox<Niveau> sniveau_d;
    @FXML
    private ComboBox<Topic> stopic_d;
    @FXML
    private ComboBox<String> stype_d;
    @FXML
    private TextField snomdoc_d;
    @FXML
    private ComboBox<Semestre> ssemestre_d;
    @FXML
    private Separator sseparator_d;
    @FXML
    private AnchorPane anchorpanedownload_d;
    @FXML
    private Label nomdoc_d;
    @FXML
    private Label semestre_d;
    @FXML
    private Label level_d;
    @FXML
    private Label topic_d;
    @FXML
    private Label type_d;
    @FXML
    private ImageView imageviewuplo_d;
    @FXML
    private Button dasborbprofile;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    this.initializeCards();
      this.initCombobox();
       anchorpanedownload_d.setVisible(false);
        sanchorpane5_d.setVisible(true);
        this.initComboboxA ();
        
    List<Document> filteredDocuments = documentService. searchDocuments(new SearchDocumentDTO());
        this.initializeCardssearch(filteredDocuments);
    }    

   

    @FXML
    private void aajouter_d(ActionEvent event) {
     
       if (adocImage_d.getImage() == null ||   anomdoc_d.getText().isEmpty() || atype_d.getSelectionModel().isEmpty()||aniveau_d.getSelectionModel().isEmpty()||asemestre_d.getSelectionModel().isEmpty()|| atopic_d.getSelectionModel().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Attention !");
    alert.setContentText("Veuillez remplir tous les champs.");
    alert.showAndWait();
    return;
}

   Document d= new Document();
      d.setDocumentCreationDate(new Date());
      d.setDocumentImage(adocImage_d.getImage().impl_getUrl());
      d.setDocumentType((String) atype_d.getSelectionModel().getSelectedItem());
      d.setIdNiveau((Niveau) aniveau_d.getSelectionModel().getSelectedItem()) ;
      d.setIdSemestre((Semestre) asemestre_d.getSelectionModel().getSelectedItem());
      d.setDocumentName((String)anomdoc_d.getText());
      d.setDocumentUrl("");
      d.setIdTopic((Topic) atopic_d.getSelectionModel().getSelectedItem());
     Document savedDoc=documentService.addDocument(d);
     
     if(savedDoc!=null){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Bienvenue");
    alert.setContentText("Le produit a été ajouté avec succès !");
    alert.showAndWait();
    this.onDestroy();
    this.initializeCards();
     }   
     
     
     
  
    }

    @FXML
    private void atélécharger_d(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();

fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
);


File imageFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

if (imageFile != null) {
  
    Image image = new Image(imageFile.toURI().toString());
    adocImage_d.setImage(image);
}}
    
    
    
    private void initCombobox(){
          ObservableList<Semestre> semestres=FXCollections.observableArrayList( semestreService.getAllSemestres());
      ObservableList<Topic> topics=FXCollections.observableArrayList( topicService.getAllTopics());
      ObservableList<Niveau> niveaus= FXCollections.observableArrayList(niveauService.getAllNiveaus());
      ObservableList<String> types=  FXCollections.observableArrayList();
      types.add("TD");
       types.add("Devoirs");
      types.add("Corrige TD");
      types.add("Cours");

      aniveau_d.setConverter(new StringConverter<Niveau>() {
    @Override
    public String toString(Niveau niveau) {
        return niveau.getNiveauName(); 
    }

    @Override
    public Niveau fromString(String string) {
       
        return null;
    }
});
      atopic_d.setConverter(new StringConverter<Topic>() {
    @Override
    public String toString(Topic topic) {
        return topic.getTopicName(); 
    }

    @Override
    public Topic fromString(String string) {
      
        return null;
    }
});
      asemestre_d.setConverter(new StringConverter<Semestre>() {
    @Override
    public String toString(Semestre semestre) {
        return semestre.getSemestreName(); 
    }

    @Override
    public Semestre fromString(String string) {
       
        return null;
    }
});

      aniveau_d.setItems( niveaus);
      atopic_d.setItems(topics);
      asemestre_d.setItems(semestres);
      atype_d.setItems( types);
    }
    
    private void onDestroy(){
       anomdoc_d.setText("");
       atype_d.setValue("");
       aniveau_d.setValue(null);
       asemestre_d.setValue(null);
       atopic_d.setValue(null);
       adocImage_d.setImage(null);
       agridpane_d.getChildren().clear();

    }
    
     public void initializeCards() {

        try{
            List<Document>  document= documentService.getAllDocuments();
            int row = 0 ;
            int column = 1;
            for(int i = 0 ; i<document.size();i++){
               
 if(document.get(i) != null && "valid".equals(document.get(i).getIsvalid())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CardDoc.fxml"));
                AnchorPane pane = loader.load();
                CardDocController controller = loader.getController();
                controller.setDocument(document.get(i));
                controller.getCard_moreInfo_D().setVisible(false);
                controller.getCard_updateinfo_D1().setVisible(true);
                agridpane_d.add(pane,column,row);
 }
                column+=1;
                if(column>2){
                    column = 1;
                    row +=1 ;
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AffichageDocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        }    }    
public void initializeCardsdorra() {

        try{
            List<Document>  document= documentService.getAllDocuments();
            int row = 0 ;
            int column = 1;
            for(int i = 0 ; i<document.size();i++){
               
 if(document.get(i) != null && "valid".equals(document.get(i).getIsvalid())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CardDoc.fxml"));
                AnchorPane pane = loader.load();
                CardDocController controller = loader.getController();
                controller.setDocument(document.get(i));
                controller.getCard_moreInfo_D().setVisible(true);
                controller.getCard_updateinfo_D1().setVisible(false);
               
                sgridpane_d.add(pane,column,row);
 }
                column+=1;
                if(column>2){
                    column = 1;
                    row +=1 ;
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AffichageDocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        }    }    
    
     Document docToUpdate=new Document();
      public void setDocument(Document d){
      // docToUpdate=d;
      

    }

    @FXML
    private void amodif_d(ActionEvent event) {
        docToUpdate.setDocumentCreationDate(new Date());
      docToUpdate.setDocumentImage(adocImage_d.getImage().impl_getUrl());
      docToUpdate.setDocumentType((String) atype_d.getSelectionModel().getSelectedItem());
      docToUpdate.setIdNiveau((Niveau) aniveau_d.getSelectionModel().getSelectedItem()) ;
      docToUpdate.setIdSemestre((Semestre) asemestre_d.getSelectionModel().getSelectedItem());
      docToUpdate.setDocumentName((String)anomdoc_d.getText());
      docToUpdate.setDocumentUrl("");
      docToUpdate.setIdTopic((Topic) atopic_d.getSelectionModel().getSelectedItem());
        System.out.println(docToUpdate.toString());
        if (adocImage_d.getImage() == null ||   anomdoc_d.getText().isEmpty() || atype_d.getSelectionModel().getSelectedItem()==null||aniveau_d.getSelectionModel().getSelectedItem()==null||asemestre_d.getSelectionModel().getSelectedItem()==null|| atopic_d.getSelectionModel().getSelectedItem()==null) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Attention !");
    alert.setContentText("Veuillez remplir tous les champs.");
    alert.showAndWait();
    return;
}

      
      docToUpdate.setDocumentCreationDate(new Date());
      docToUpdate.setDocumentImage(adocImage_d.getImage().impl_getUrl());
      docToUpdate.setDocumentType((String) atype_d.getSelectionModel().getSelectedItem());
      docToUpdate.setIdNiveau((Niveau) aniveau_d.getSelectionModel().getSelectedItem()) ;
      docToUpdate.setIdSemestre((Semestre) asemestre_d.getSelectionModel().getSelectedItem());
      docToUpdate.setDocumentName((String)anomdoc_d.getText());
      docToUpdate.setDocumentUrl("");
      docToUpdate.setIdTopic((Topic) atopic_d.getSelectionModel().getSelectedItem());
     Document savedDoc=documentService.updateDocument(docToUpdate);
     
     if(savedDoc!=null){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Bienvenue");
    alert.setContentText("Le document  a été ajouté avec succès !");
    alert.showAndWait();
    this.onDestroy();
    this.initializeCards();
     }    
    }


    @FXML
    private void areloadmodif_d(ActionEvent event) {
        modifbutton.setVisible(true);
          docToUpdate=CardDocController.getDoctomodif();
        System.err.println(docToUpdate.toString());
         anomdoc_d.setText(docToUpdate.getDocumentName());
       atype_d.setValue(docToUpdate.getDocumentType());
       aniveau_d.setValue(niveauService.getNiveauById(docToUpdate.getIdNiveau().getIdNiveau()));
       asemestre_d.setValue(semestreService.getSemestreById(docToUpdate.getIdSemestre().getIdSemestre()));
       atopic_d.setValue(topicService.getTopicById(docToUpdate.getIdTopic().getIdTopic()));
       Image image = new Image(docToUpdate.getDocumentImage());
       adocImage_d.setImage(image);
     
       
    }

    @FXML
    private void adeletePopUp_d(ActionEvent event) {
       
    if (docToUpdate == null) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the item first");
        alert.showAndWait();
    } else {
        int id = docToUpdate.getIdDoc();
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Service ID: " + id);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                documentService.DeleteDocument(id);
              
              this.onDestroy();
              this.initializeCards();
              

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

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

 @FXML
private void uploadDocD(ActionEvent event) throws GeneralSecurityException {
    // Obtain the selected file using FileChooser
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Documents", "*.pdf", "*.docx"));
    java.io.File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

//    if (selectedFile != null) {
//        try {
//          
//            Drive driveService = GoogleDriveService.initializeDriveService();
//
//          
//            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
//            fileMetadata.setName(selectedFile.getName());
//
//          
//            FileContent mediaContent = new FileContent("application/pdf", selectedFile);
//
//           
//            com.google.api.services.drive.model.File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
//                .setFields("id")
//                .execute();
//
//            System.out.println("File uploaded with ID: " + uploadedFile.getId());
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Upload error: " + e.getMessage());
//        }
//    }
}


    @FXML
    private void uploadDocD(MouseEvent event) {
    }

    @FXML
    private void retouraff_d(ActionEvent event) {
        dorra2.setVisible(true);
           dorra1.setVisible(false);
               

    }

    @FXML
    private void buttonDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXMLDocument.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void transportDashboard(ActionEvent event) {
    }

    @FXML
    private void biblioDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/librarydorra.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void eventDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI_Events/FXML.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
         try {
            if (option.get().equals(ButtonType.OK)) {

                logout_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/AuthentificationInterface.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                /*root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });*/

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void servicesDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mainform.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void forumDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ForumInterface.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

   

 
    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//         anchorpanedownload_d.setVisible(false);
//        sanchorpane5_d.setVisible(true);
//        this.initCombobox();
//        
//    List<Document> filteredDocuments = documentService. searchDocuments(new SearchDocumentDTO());
//        this.initializeCardssearch(filteredDocuments);
//
//    }    
//    

    @FXML
    private void srechercher_d(ActionEvent event) {

    int niveau =(sniveau_d.getValue()!=null)? sniveau_d.getValue().getIdNiveau() : 0 ;
    int topic =(stopic_d.getValue()!=null)? stopic_d.getValue().getIdTopic():0;
    String type = stype_d.getValue();
    String nomdoc = snomdoc_d.getText();
    int semestre =(ssemestre_d.getValue()!=null)?  ssemestre_d.getValue().getIdSemestre():0;
        SearchDocumentDTO sd=new SearchDocumentDTO(nomdoc,type,niveau,topic,semestre,"valid");
        
    List<Document> filteredDocuments = documentService. searchDocuments(sd);
        System.out.println(filteredDocuments.toString());
   
    sgridpane_d.getChildren().clear();
this.initializeCardssearch(filteredDocuments);
}

        
        public void initializeCards(List<Document>  document) {
                sgridpane_d.getChildren().clear();

            DocumentServiceImp dosr=new DocumentServiceImp();
            document.addAll(dosr.getAllDocuments());
        try{
            int row = 0 ;
            int column = 1;
            for(int i = 0 ; i<document.size();i++){
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CardDoc.fxml"));
                AnchorPane pane = loader.load();
                CardDocController controller = loader.getController();
                controller.setDocument(document.get(i));
              //  if(dosr.getValidDocuments(document.get(i)))
              
               if(document.get(i) != null && "valid".equals(document.get(i).getIsvalid())){
                controller.getCard_moreInfo_D().setVisible(true);
                controller.getCard_updateinfo_D1().setVisible(false);
               
                sgridpane_d.add(pane,column,row);
               }
                column+=1;
                if(column>2){
                    column = 1;
                    row +=1 ;
                
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AffichageDocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        }    }
         public void initializeCardssearch(List<Document>  document) {
               // sgridpane_d.getChildren().clear();

           // DocumentServiceImp dosr=new DocumentServiceImp();
           // document.addAll(dosr.getAllDocuments());
        try{
            int row = 0 ;
            int column = 1;
            for(int i = 0 ; i<document.size();i++){
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CardDoc.fxml"));
                AnchorPane pane = loader.load();
                CardDocController controller = loader.getController();
                controller.setDocument(document.get(i));
              //  if(dosr.getValidDocuments(document.get(i)))
              
               if(document.get(i) != null && "valid".equals(document.get(i).getIsvalid())){
                controller.getCard_moreInfo_D().setVisible(true);
                controller.getCard_updateinfo_D1().setVisible(false);
               
                sgridpane_d.add(pane,column,row);
               }
                column+=1;
                if(column>2){
                    column = 1;
                    row +=1 ;
                
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AffichageDocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        }    }    


            private void initComboboxA(){
          ObservableList<Semestre> semestres=FXCollections.observableArrayList( semestreService.getAllSemestres());
      ObservableList<Topic> topics=FXCollections.observableArrayList( topicService.getAllTopics());
      ObservableList<Niveau> niveaus= FXCollections.observableArrayList(niveauService.getAllNiveaus());
      ObservableList<String> types=  FXCollections.observableArrayList();
      types.add("TD");
     types.add("Devoirs");
      types.add("Corrige TD");
      types.add("Cours");

      sniveau_d.setConverter(new StringConverter<Niveau>() {
    @Override
    public String toString(Niveau niveau) {
        return niveau.getNiveauName(); 
    }

    @Override
    public Niveau fromString(String string) {
        
        return null;
    }
});
      stopic_d.setConverter(new StringConverter<Topic>() {
    @Override
    public String toString(Topic topic) {
        return topic.getTopicName(); 
    }

    @Override
    public Topic fromString(String string) {
       
        return null;
    }
});
      ssemestre_d.setConverter(new StringConverter<Semestre>() {
    @Override
    public String toString(Semestre semestre) {
        return semestre.getSemestreName(); 
    }

    @Override
    public Semestre fromString(String string) {
       
        return null;
    }
});

      sniveau_d.setItems( niveaus);
      stopic_d.setItems(topics);
      ssemestre_d.setItems(semestres);
      stype_d.setItems( types);
    }

    @FXML
    private void sreset_d(ActionEvent event) {
       List<Document> filteredDocuments = documentService. searchDocuments(new SearchDocumentDTO());
       snomdoc_d.setText("");
       stype_d.setValue(null);
       sniveau_d.setValue(null);
       ssemestre_d.setValue(null);
       stopic_d.setValue(null);
        this.initializeCardssearch(filteredDocuments);
    }

    
    @FXML
    private void upload_d(ActionEvent event) {
        
        
        
        
        Document document=new Document();
        if (document != null) {
       
        String documentURL = document.getDocumentName();
        if (documentURL != null && !documentURL.isEmpty()) {
           try {
        URL url = new URL(documentURL);
        InputStream in = url.openStream();
        Files.copy(in, Paths.get("LOCAL_PATH_TO_SAVE_DOCUMENT"));
        in.close();
        System.out.println("Download successful!");
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Download error: " + e.getMessage());
    }
        } else {
            System.out.println("L'URL du document est vide ou nul.");
        }}}
        
//       Image imagetobesaved = imageviewuplo_d.getImage();
//
//if (imagetobesaved != null) {
//    File file = new File("/C:/Users/Dorra/Pictures/Screenshots/Capture%20d’écran%20(1).png");
//    try {
//        ImageIO.write(SwingFXUtils.fromFXImage(imagetobesaved, null), "png", file);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//} else {
//    System.out.println("L'objet ImageView ne contient pas d'image.");
//}
//    }
    @FXML
    private void retour_d(ActionEvent event) {
        anchorpanedownload_d.setVisible(false);
        sanchorpane5_d.setVisible(true);
        this.initializeCardsdorra();
    }

  

    @FXML
    private void rloadAffichage_d(ActionEvent event) {
            
        
        
    }

          public AnchorPane getSanchorpane5_d() {
        return sanchorpane5_d;
    }

    public void setSanchorpane5_d(AnchorPane sanchorpane5_d) {
        this.sanchorpane5_d = sanchorpane5_d;
    }

    public AnchorPane getAnchorpanedownload_d() {
        return anchorpanedownload_d;
    }

    public void setAnchorpanedownload_d(AnchorPane anchorpanedownload_d) {
        this.anchorpanedownload_d = anchorpanedownload_d;
    }

    @FXML
    private void ADD_d(ActionEvent event) {
           dorra2.setVisible(false);
           dorra1.setVisible(true);
    }

    @FXML
    private void ref_d(ActionEvent event) {
                       this.initializeCardsdorra();

    }

    @FXML
    private void profile(ActionEvent event) {
        try {      dasborbprofile.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
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
        




}



