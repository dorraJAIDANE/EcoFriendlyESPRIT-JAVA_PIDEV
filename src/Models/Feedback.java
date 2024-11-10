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
public class Feedback {
    private int feeds;
    private String oreder_id;
    private String service_id;
    private String comments;

    // Constructors, getters, setters, and other methods can be added here

    public Feedback(int feeds, String oreder_id, String service_id, String comments) {
        this.feeds = feeds;
        this.oreder_id = oreder_id;
        this.service_id = service_id;
        this.comments = comments;
    }

    public int getfeeds() {
        return feeds;
    }

    public void setfeeds(int feeds) {
        this.feeds = feeds;
    }

    public String getOreder_id() {
        return oreder_id;
    }

    public void setOreder_id(String oreder_id) {
        this.oreder_id = oreder_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
}

