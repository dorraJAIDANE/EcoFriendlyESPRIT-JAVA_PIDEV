/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS.ADMIN;

import INTERFACES.DocumentService;
import INTERFACES.TopicService;
import Models.Document;
import Models.Topic;
import Services.DocumentServiceImp;
import Services.TopicServiceImp;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra
 */
public class Supprimerdocuments_adminController implements Initializable {
    
    @FXML
    private AnchorPane sanchorpane2_d;
    @FXML
    private AnchorPane sanchorpane3_d;
    @FXML
    private AnchorPane sanchorpane4_d;
    @FXML
    private TableView<Document> treeview_d;
    @FXML
    private TableColumn<?, ?> name_doc_d;
    @FXML
    private TableColumn<?, ?> semester_d;
    @FXML
    private TableColumn<?, ?> level_d;
    @FXML
    private TableColumn<?, ?> topic_d;
    @FXML
    private TableColumn<?, ?> type_d;
    @FXML
    private TableColumn<?, ?> image_d;

    /**
     * Initializes the controller class.
     */
   
    private ObservableList<Document> documentList = FXCollections.observableArrayList();
  

    @FXML
    private ListView<String> listeview;
    @FXML
    private TextField dorratopic;
    @FXML
    private TableColumn<?, ?> valide_d;
  
   
@Override
public void initialize(URL url, ResourceBundle rb) {
           affichagelisteview();
listeview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ActionEvent ActionEvent = null;
  refrech_d(ActionEvent);
}
    @FXML
    private void delete_d(ActionEvent event) {
           DocumentServiceImp doc =new  DocumentServiceImp(); 
            Document selectedDocument = treeview_d.getSelectionModel().getSelectedItem();
    
    if (selectedDocument != null) {
        int selectedDocumentId = selectedDocument.getIdDoc();


          doc.ban(selectedDocumentId);

        
            
            doc.getValidDocuments();
            
            treeview_d.getSelectionModel().clearSelection();
            } else {
       
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Aucun document sélectionné");
        alert.setContentText("Veuillez sélectionner un document à supprimer.");
        alert.showAndWait();
    }
}
         
    

    @FXML
    private void refrech_d(ActionEvent event) {
        DocumentServiceImp doc =new  DocumentServiceImp(); 
       documentList.clear();
      List<Document> newData; 
        newData = doc.getAllDocuments();
    documentList.clear();
    documentList.addAll(newData);
    treeview_d.setItems(documentList);
        tablevieww() ;
    }

    private void tablevieww() {
       name_doc_d.setCellValueFactory(new PropertyValueFactory<>("documentName"));
    semester_d.setCellValueFactory(new PropertyValueFactory<>("idSemestre.semestreName"));
    level_d.setCellValueFactory(new PropertyValueFactory<>("idNiveau.niveauName"));
    topic_d.setCellValueFactory(new PropertyValueFactory<>("idTopic.topicName"));
    type_d.setCellValueFactory(new PropertyValueFactory<>("documentType"));
    image_d.setCellValueFactory(new PropertyValueFactory<>("documentImage"));
    valide_d.setCellValueFactory(new PropertyValueFactory<>("isvalid"));


   treeview_d.setItems(documentList);

    }
    private ObservableList<Topic> topicsList = FXCollections.observableArrayList();

      private TopicService topicService=new TopicServiceImp(); 


 @FXML
    void ajoutettopic_d(ActionEvent event) {
         Topic t = new Topic();
    t.setTopicName((String)dorratopic.getText());
    topicService.addTopic(t);
     affichagelisteview();
    }

    private void affichagelisteview(){
            System.out.println("affichagelisteview " );

        List<String> topics =topicService.getAllTopics().stream().map(t->t.getTopicName()).collect(Collectors.toList());
        listeview.getItems().clear();
        listeview.getItems().addAll(topics);
    }

    @FXML
    private void deletetopic_d(ActionEvent event) {
        String selectedItem = listeview.getSelectionModel().getSelectedItem();

if (selectedItem != null) {
    System.out.println("Selected Item: " + selectedItem);
     Topic topic =(Topic) topicService.getAllTopics().stream().filter(t->t.getTopicName().equals(selectedItem.trim())).findFirst().get();
      if(topic!=null){
              System.out.println("Selected Item: " + topic.toString());

          topicService.supprimerTopic(topic.getIdTopic());
          affichagelisteview();
      }
} 
    
    
    }      
  
     




  
    







}






















     
   
    

