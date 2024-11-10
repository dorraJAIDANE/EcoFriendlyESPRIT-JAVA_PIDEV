/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS.CLIENT;

import static GUI.UserSession.getCurrentUser;
import INTERFACES.DocumentService;
import INTERFACES.HistoriqueDocumentsService;
import INTERFACES.NiveauService;
import INTERFACES.SemestreService;
import INTERFACES.TopicService;
import Models.CurrentDocument;
import Models.Document;
import Models.HistoriqueDocument;
//import Models.currentuser;
import Models.rateddocs;
import Models.user;
import Services.DocumentServiceImp;
import Services.HistoriqueServiceImp;
import Services.NiveauServiceImp;
import Services.RatingServiceImp;
import Services.SemestreServiceImp;
import Services.TopicServiceImp;
//import com.google.api.services.drive.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

import java.util.Properties;
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
 * @author Dorra
 */
public class PopUpDocController implements Initializable {
    @FXML
private Rating ratingControlDorra;
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
    private  static Document doctomodif=new Document();
    private static user currentUser=getCurrentUser();
    private NiveauService niveauService=new NiveauServiceImp();
    private SemestreService semestreService=new SemestreServiceImp();
    private TopicService topicService=new TopicServiceImp();
    private DocumentService documentService=new DocumentServiceImp();
        private HistoriqueDocumentsService historiqueService=new HistoriqueServiceImp();

    @FXML
    private Label nbrview_d;
    @FXML
    private Label nbrdownload_d;
    @FXML
    private Button addRating;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int vue=0;
        int dw=0;
   
        System.out.println(CurrentDocument.getDocument());
if(CurrentDocument.getDocument()!=null){
     doctomodif=CurrentDocument.getDocument();
                Document docToUpdate=doctomodif;

            nomdoc_d.setText(docToUpdate.getDocumentName());
       type_d.setText(docToUpdate.getDocumentType());
       level_d.setText(niveauService.getNiveauById(docToUpdate.getIdNiveau().getIdNiveau()).getNiveauName());
       semestre_d.setText(semestreService.getSemestreById(docToUpdate.getIdSemestre().getIdSemestre()).getSemestreName());
       topic_d.setText(topicService.getTopicById(docToUpdate.getIdTopic().getIdTopic()).getTopicName());
       Image image = new Image(docToUpdate.getDocumentImage());
       imageviewuplo_d.setImage(image);  
       vue=(int) historiqueService.gethistoriquedocumentByIdDoc(CurrentDocument.getDocument().getIdDoc()).stream().filter(h->h.getOperation().equals("v")).count();
     dw=(int) historiqueService.gethistoriquedocumentByIdDoc(CurrentDocument.getDocument().getIdDoc()).stream().filter(h->h.getOperation().equals("t")).count();
     System.out.println(vue);
     nbrview_d.setText(""+vue);
        
     nbrdownload_d.setText(""+dw);
}
    
    if (vue == 1) {
            sendEmailAutomaticallyy();
        }
    
}

private void sendEmailAutomaticallyy() {
    final String fromEmail = "dorra.jaidane@esprit.tn";
    final String password = "223JFT5027";
    final String toEmail = "louay.sghaier@esprit.tn";

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
        message.setSubject("Document Viewed");
        message.setText("document a plus de vue ce jour document a le nomdocument c)");

        Transport.send(message);

        System.out.println("Email sent successfully.");
    } catch (MessagingException e) {
        e.printStackTrace();
        System.out.println("Failed to send the email.");
    }

}
    
    
    
    
    
    
    
    
    
    
    
    
    
  
        
        
    


    


   




    


        

    @FXML
    private void retour_d(ActionEvent event) {
    }

    @FXML
    private void rloadAffichage_d(ActionEvent event) {
    }

    @FXML
    private void upload_d(ActionEvent event) {
    System.out.println(CurrentDocument.getDocument());
                historiqueService.addhistorique(new HistoriqueDocument(1,currentUser.getIduser(),CurrentDocument.getDocument().getIdDoc(),"t"));


    }
    
    public void setDocument(Document d){
       
    }
    
    
    @FXML
private void addRating(ActionEvent event) {
    RatingServiceImp ratingService = new RatingServiceImp();

   double currentRating = ratingControlDorra.getRating();
    int userId = currentUser.getIduser(); // Get the ID of the current user
    int documentId = doctomodif.getIdDoc(); // Get the ID of the document being rated

    int existingRating = ratingService.getRatingByUserAndDocument(1, documentId);

    if (existingRating != -1) {
        // Update the existing rating
      rateddocs rating = new rateddocs();
       rating.setUserId(currentUser.getIduser());//update me later 
    rating.setRateddoc(doctomodif);
    rating.setRating((int) currentRating);


        boolean success = ratingService.updateRating(rating);

        if (success) {
            System.out.println("Rating updated successfully.");
        } else {
            System.out.println("Failed to update the rating.");
        }
    } else {
        // Insert a new rating
        rateddocs rating = new rateddocs();
      rating.setUserId(userId);
    rating.setRateddoc(doctomodif);
    rating.setRating((int) currentRating);


        boolean success = ratingService.addRating(rating);

        if (success) {
            System.out.println("Rating added successfully.");
        } else {
            System.out.println("Failed to add the rating.");
        }
    }
}

//@FXML
//private void addRating(ActionEvent event) {
//    RatingServiceImp ratingService = new RatingServiceImp();
//    rateddocs rating = new rateddocs();
//
//    double currentRating = ratingControlDorra.getRating();
//    int userId = currentuser.getIduser(); // Get the ID of the current user
//    int documentId = doctomodif.getIdDoc(); // Get the ID of the document being rated
//
//    rating.setUserId(userId);
//    rating.setRateddoc(doctomodif);
//    rating.setRating((int) currentRating);
//
//    boolean success = ratingService.addRating(rating);
//
//    if (success) {
//        System.out.println("Rating added successfully.");
//    } else {
//        System.out.println("Failed to add the rating.");
//    }
//}





// ...


}