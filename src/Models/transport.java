/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


public class transport {
    private int idtransport;
    private float prix;

    public transport(int idtransport, float prix) {
        this.idtransport = idtransport;
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
        
    }

    public int getIdtransport() {
        return idtransport;
    }

    public void setIdtransport(int idtransport) {
        this.idtransport = idtransport;
    }
}
