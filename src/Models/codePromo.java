/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author LENOVO
 */
public class codePromo {
    private int  idcodepromo;
    private  float  pourcentage;
    private Event idevent ;
    private transport idtransport;
    private Service idService ;
    
    
    
    

    public codePromo(int idcodepromo, float pourcentage,  Event idevent, transport idtransport, Service idService) {
        this.idcodepromo = idcodepromo;
        this.pourcentage = pourcentage;
        this.idevent = idevent;
        this.idtransport = idtransport;
        this.idService = idService;
    }

    public codePromo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdcodepromo() {
        return idcodepromo;
    }

    public void setIdcodepromo(int idcodepromo) {
        this.idcodepromo = idcodepromo;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Event getIdevent() {
        return idevent;
    }

    public void setIdevent(Event idevent) {
        this.idevent = idevent;
    }

    public transport getIdtransport() {
        return idtransport;
    }

    public void setIdtransport(transport idtransport) {
        this.idtransport = idtransport;
    }

    public Service getIdService() {
        return idService;
    }

    public void setIdService(Service idService) {
        this.idService = idService;
    }

      public float getPrixTotal() {
        return (float) (idevent.getPrixTicket() + idtransport.getPrix() + idService.getPrice());
    }

   
}



