/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author Mekni
 */
public class Participation {
    private int idParticipation;
    private Event event; // Clé étrangère faisant référence à l'événement associé
    private user user; // Clé étrangère faisant référence à l'utilisateur associé
    private String codeQR; // Code QR lié à la participation (si nécessaire)
   private Date dateParticipation;
    public Participation(){
        // Constructeur vide
    }
    
    public Participation(int idParticipation, Event event, user user, String codeQR) {
        this.idParticipation = idParticipation;
        this.event = event;
        this.user = user;
        this.codeQR = codeQR;
    }

    
    
       public Participation(int idParticipation, Event event, user user, String codeQR , Date dateParticipation ) {
        this.idParticipation = idParticipation;
        this.event = event;
        this.user = user;
        this.codeQR = codeQR;
        this.dateParticipation=dateParticipation;
    }
    
    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public user getuser() {
        return user;
    }

    public void setuser(user user) {
        this.user = user;
    }

    public String getCodeQR() {
        return codeQR;
    }

    public void setCodeQR(String codeQR) {
        this.codeQR = codeQR;
    }
    
    public int getIduser() {
    return user.getIduser();
}
 private int count;

    public Participation(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
 public Date getDateParticipation() {
        return dateParticipation;
    }

    public void setDateParticipation(Date dateParticipation) {
        this.dateParticipation = dateParticipation;
    }
}
