/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author louay
 */
public class Rating {
    private int rate_id;
    private String order;
    private String service;
    private int stars;

    // Constructors, getters, setters, and other methods can be added here

    public Rating(int rate_id, String order, String service, int stars) {
        this.rate_id = rate_id;
        this.order = order;
        this.service = service;
        this.stars = stars;
    }

    public int getrate_id() {
        return rate_id;
    }

    public void setrate_id(int rate_id) {
        this.rate_id = rate_id;
    }

    public String getorder() {
        return order;
    }

    public void setorder(String order) {
        this.order = order;
    }

    public String getservice() {
        return service;
    }

    public void setservice(String service) {
        this.service = service;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
    
}
