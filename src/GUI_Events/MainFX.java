/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mekni
 */
public class MainFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // Charger le fichier FXML avec le contrôleur associé
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Adminevent.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);
        
        // Configurer la scène et la montrer
        stage.setTitle("Our Events");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
