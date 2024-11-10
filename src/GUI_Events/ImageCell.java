/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Events;

/**
 *
 * @author Mekni
 */
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Models.Event;

public class ImageCell extends ListCell<Event> {
    private final ImageView imageView = new ImageView();
    
    @Override
    protected void updateItem(Event item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Affichez l'image de l'événement
            Image image = new Image("file:" + item.getImage()); // Utilisez le chemin de l'image réel
            imageView.setImage(image);
            imageView.setFitWidth(100); // Ajustez la largeur de l'image selon vos besoins
            imageView.setPreserveRatio(true);

            // Affichez plus d'informations sur l'événement sous l'image
            setText("Nom de l'événement : " + item.getNomEvent() + "\n"
        + "Date de début : " + item.getDateDebutEvent() + "\n"
        + "Durée : " + item.getDurée() + "\n"
        + "Lieu : " + item.getLieuEvent() + "\n"
        + "Prix du ticket : " + item.getPrixTicket() + "\n"
        + "Nombre maximal de participants : " + item.getNbmaxParticipant() + "\n"
        + "Type d'événement : " + item.getTypeEvent() + "\n"
        + "Description : " + item.getDescriptionEvent());

            setGraphic(imageView);
        }
    }
}