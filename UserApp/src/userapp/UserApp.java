/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userapp;

import Models.codePromo;
import Services.codePromoservice;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.MyConnection;

/**
 * @author LENOVO
 */
public class UserApp extends Application {
    private double x = 0;
    private double y = 0;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/AuthentificationInterface.fxml"));

        Scene scene = new Scene(root);
        stage.setMinWidth(750);
        stage.setMinHeight(416);

        stage.setTitle("ECOFRIENDLY ESPRIT");

        stage.setScene(scene);
        stage.show();
        root.setOnMousePressed((MouseEvent event) -> {
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
        });
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
          MyConnection Mycnx =  MyConnection.getInstance();
        /*user user1 = new user( "lahdhiri", "haithem", "lahdhiri.haithem@esprit.tn", "78954", "ariana", "etudiant", "500", "4SE2");
        userService userser = new userService();
        userser.ajouterUser(user1);
        userser.supprimeruser(1);
        
        user1.setIduser(3);
        user1.setPrenomuser("Eya");
        user1.setNomuser("grati");
        user1.setMailuser("eya.grati@esprit.tn");
        user1.setAdressuser("ben arous");
        user1.setClasseuser("4SE2");
        user1.setRoleuser("etudiante");
        user1.setWalletuser("250D");
        userser.mettreAJouruser(user1);
        
        
        userser.afficherUsers();*/
         // Adding a new code promo
        /* codePromo code =new codePromo(10.0f, 101,12,15);
         codePromoservice codeser = new codePromoservice();
        codePromo newCodePromo = new codePromo();
        newCodePromo.setPourcentage(10.0f);
        newCodePromo.setIdevent(101);
        newCodePromo.setIdtransport(201);
        newCodePromo.setIdservice(30);
        codeser.ajouterCodePromo(newCodePromo);//

        // Retrieving all code promos
        List<codePromo> codes = codeser.affichercodePromo();
        for (codePromo code : codes) {
            System.out.println(code);
        }

        // Updating a code promo
        codePromo updateCodePromo = new codePromo();
        updateCodePromo.setIdcodepromo(2);
        updateCodePromo.setPourcentage(15.0f);
        updateCodePromo.setIdevent(10);
        updateCodePromo.setIdtransport(21);
        updateCodePromo.setIdservice(3);
        codeser.mettreAJourCodePromo(updateCodePromo);

        // Deleting a code promo
        codeser.supprimercodepromo(1);
    }
        
    }*/
}}
