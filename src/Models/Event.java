/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mekni
 */
public class Event {
 
    private Integer idEvent;
    private String nomEvent;
    private Date dateDebutEvent;
    private String Durée; // Renommé de "Duée" en "Durée"
    private String LieuEvent;
    private Double PrixTicket;
    private Integer nbmaxParticipant;
    private String typeEvent;
    private String descriptionEvent;
    private String image;
     private user iduser;
     private user username;
    private List<Participation> participations = new ArrayList<>();
    private user user;
    private Date Datecreation;
       // Constructeur pour un événement avec ID
    private String valid;
    public Event(){
        
    }
    
    
    public Event(Integer idEvent, String nomEvent, Date dateDebutEvent, String Durée, String LieuEvent, Double PrixTicket, Integer nbmaxParticipant, String typeEvent, String descriptionEvent, String image) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.Durée = Durée;
        this.LieuEvent = LieuEvent;
        this.PrixTicket = PrixTicket;
        this.nbmaxParticipant = nbmaxParticipant;
        this.typeEvent = typeEvent;
        this.descriptionEvent = descriptionEvent;
        this.image = image;
    }
   public Event(Integer idEvent, String nomEvent, Date dateDebutEvent, String Durée, String LieuEvent, Double PrixTicket, Integer nbmaxParticipant, String typeEvent, String descriptionEvent, String image, Date Datecreation,String valid) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.Durée = Durée;
        this.LieuEvent = LieuEvent;
        this.PrixTicket = PrixTicket;
        this.nbmaxParticipant = nbmaxParticipant;
        this.typeEvent = typeEvent;
        this.descriptionEvent = descriptionEvent;
        this.image = image;
        this.Datecreation = Datecreation;
        this.valid=valid;
    }
   
   
   
   public Event(Integer idEvent, String nomEvent, Date dateDebutEvent, String Durée, String LieuEvent, Double PrixTicket, Integer nbmaxParticipant, String typeEvent, String descriptionEvent, String image, Date Datecreation) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.Durée = Durée;
        this.LieuEvent = LieuEvent;
        this.PrixTicket = PrixTicket;
        this.nbmaxParticipant = nbmaxParticipant;
        this.typeEvent = typeEvent;
        this.descriptionEvent = descriptionEvent;
        this.image = image;
        this.Datecreation = Datecreation;
    }
   
   
   
   
   
    // Constructeur pour un événement sans ID (ID généré automatiquement par la base de données)
   
    public Event( String nomEvent, Date dateDebutEvent, String Durée, String LieuEvent, Double PrixTicket, Integer nbmaxParticipant, String typeEvent, String descriptionEvent, String image, Date Datecreation,String valid) {
       
        this.nomEvent = nomEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.Durée = Durée;
        this.LieuEvent = LieuEvent;
        this.PrixTicket = PrixTicket;
        this.nbmaxParticipant = nbmaxParticipant;
        this.typeEvent = typeEvent;
        this.descriptionEvent = descriptionEvent;
        this.image = image;
        this.Datecreation = Datecreation;
        
    }
   
   
   
   
   
   
   
   
   
   
   public Event(String nomEvent, Date dateDebutEvent, String Durée, String LieuEvent, Double PrixTicket, Integer nbmaxParticipant, String typeEvent, String descriptionEvent, String image,user iduser) {
        this.nomEvent = nomEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.Durée = Durée;
        this.LieuEvent = LieuEvent;
        this.PrixTicket = PrixTicket;
        this.nbmaxParticipant = nbmaxParticipant;
        this.typeEvent = typeEvent;
        this.descriptionEvent = descriptionEvent;
        this.image = image;
        this.iduser = iduser;
        
    }
    
   
   
   public Event(String nomEvent, String typeEvent ,String Durée ,  Double PrixTicket,  String LieuEvent, Integer nbmaxParticipant ,String valid ) {
        this.nomEvent = nomEvent;
        this.typeEvent= typeEvent;
        this.Durée = Durée;
         this.PrixTicket = PrixTicket;
          this.LieuEvent = LieuEvent;
        this.nbmaxParticipant=nbmaxParticipant;
        this.valid=valid;
               
    }
   
   
   
   
   
    public Event(String nomEvent, Date dateDebutEvent, String Durée, String LieuEvent, Double PrixTicket, Integer nbmaxParticipant, String typeEvent, String descriptionEvent, String image) {
        this.nomEvent = nomEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.Durée = Durée;
        this.LieuEvent = LieuEvent;
        this.PrixTicket = PrixTicket;
        this.nbmaxParticipant = nbmaxParticipant;
        this.typeEvent = typeEvent;
        this.descriptionEvent = descriptionEvent;
        this.image = image;
        
        
    }
    
    public Event(String nomEvent,  Double PrixTicket, String image) {
        this.nomEvent = nomEvent;
        
        this.PrixTicket = PrixTicket;
      
        this.image = image;
    
        
    }
    
    
    
    
    
    
    
    
    
    
public user getuser() {
        return user;
    }

    public void setuser(user user) {
        this.user = user;
    }
    
    
    // Getters and setters for all attributes

    public Integer getIdEvent() {
    return idEvent;
}

public void setIdEvent(Integer idEvent) {
    this.idEvent = idEvent;
}

public String getNomEvent() {
    return nomEvent;
}

public void setNomEvent(String nomEvent) {
    this.nomEvent = nomEvent;
}


public String getValid() {
    return valid;
}

public void setValid(String valid) {
    this.valid = valid;
}





public Date getDateDebutEvent() {
    return dateDebutEvent;
}

public void setDateDebutEvent(Date dateDebutEvent) {
    this.dateDebutEvent = dateDebutEvent;
}

public String getDurée() {
    return Durée;
}

public void setDurée(String Durée) {
    this.Durée = Durée;
}

public String getLieuEvent() {
    return LieuEvent;
}

public void setLieuEvent(String LieuEvent) {
    this.LieuEvent = LieuEvent;
}

public Double getPrixTicket() {
    return PrixTicket;
}

public void setPrixTicket(Double PrixTicket) {
    this.PrixTicket = PrixTicket;
}

public Integer getNbmaxParticipant() {
    return nbmaxParticipant;
}

public void setNbmaxParticipant(Integer nbmaxParticipant) {
    this.nbmaxParticipant = nbmaxParticipant;
}

public String getTypeEvent() {
    return typeEvent;
}

public void setTypeEvent(String typeEvent) {
    this.typeEvent = typeEvent;
}

public String getDescriptionEvent() {
    return descriptionEvent;
}

public void setDescriptionEvent(String descriptionEvent) {
    this.descriptionEvent = descriptionEvent;
}

public String getImage() {
    return image;
}

public void setImage(String image) {
    this.image = image;
}

    public Date getDatecreation() {
        return Datecreation;
    }

    // Méthode pour définir (set) la valeur de Datecreation
    public void setDatecreation(Date Datecreation) {
        this.Datecreation = Datecreation;
    }



  public user getIduser() {
        return iduser;
    }

    // Setter pour iduser
    public void setIduser(user user) {
        this.iduser = user;
    }
    
    
     public user getusername() {
        return username;
    }

    public void setusername(user username) {
        this.username = username;
    }
    
    
    
    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public void addParticipation(Participation participation) {
        participations.add(participation);
    }
    

    public void removeParticipation(Participation participation) {
        participations.remove(participation);
    }

    
    
    
    
 
    
    
    
    
    
    
    public void afficherParticipations() {
        if (participations.isEmpty()) {
            System.out.println("Aucune participation pour cet événement.");
        } else {
            System.out.println("Liste des participations de l'événement '" + nomEvent + "' :");
            for (Participation participation : participations) {
                System.out.println("ID de la participation : " + participation.getIdParticipation());
                System.out.println("ID de l'utilisateur : " + participation.getIduser());
                System.out.println("Code QR : " + participation.getCodeQR());
                System.out.println("-------------------------");
            }
        }
    }

    @Override
    public String toString() {
        return "Event [idEvent=" + idEvent + ", nomEvent=" + nomEvent + ", dateDebutEvent=" + dateDebutEvent
                + ", Durée=" + Durée + ", LieuEvent=" + LieuEvent + ", PrixTicket=" + PrixTicket
                + ", nbmaxParticipant=" + nbmaxParticipant + ", typeEvent=" + typeEvent
                + ", descriptionEvent=" + descriptionEvent + ", image=" + image + "]";
    }
}
