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
    private event idevent ;
    private transport idtransport;
    private service idservice ;
    
    
    
    

    public codePromo(int idcodepromo, float pourcentage,  event idevent, transport idtransport, service idservice) {
        this.idcodepromo = idcodepromo;
        this.pourcentage = pourcentage;
        this.idevent = idevent;
        this.idtransport = idtransport;
        this.idservice = idservice;
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

    public event getIdevent() {
        return idevent;
    }

    public void setIdevent(event idevent) {
        this.idevent = idevent;
    }

    public transport getIdtransport() {
        return idtransport;
    }

    public void setIdtransport(transport idtransport) {
        this.idtransport = idtransport;
    }

    public service getIdservice() {
        return idservice;
    }

    public void setIdservice(service idservice) {
        this.idservice = idservice;
    }

      public float getPrixTotal() {
        return idevent.getPrix() + idtransport.getPrix() + idservice.getPrix();
    }

   
}



