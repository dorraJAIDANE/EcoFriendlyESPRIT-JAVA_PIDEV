
package GUI;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PromoController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifer;
    @FXML
    private Button back;
     private double x=0;
    private double y=0;
      private PreparedStatement prepare ;
 private ResultSet result ;
  private Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void ajouter() {
         {try {      back.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("codepromo.fxml"));
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
        
    }

    @FXML
    private void modifier() {
        {try {      modifer.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("modifier.fxml"));
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
        
    }

    @FXML
    private void back() {
          {try {      back.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("BACKUSERS.fxml"));
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
        
    }
    
}
