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

    public class service {
    private int service;
    private float prix;

    public service(int service, float prix) {
        this.service = service;
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }
    
}
