/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Dorra
 */
public class rateddocs {
    private int idrateddoc;
    private int userId;
    private int rating;
    private Document rateddoc ;

    public rateddocs(int idrateddoc, int userId, int rating, Document rateddoc) {
        this.idrateddoc = idrateddoc;
        this.userId = userId;
        this.rating = rating;
        this.rateddoc = rateddoc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

   

  
    

    public rateddocs() {
    }

    public int getIdrateddoc() {
        return idrateddoc;
    }

    public void setIdrateddoc(int idrateddoc) {
        this.idrateddoc = idrateddoc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Document getRateddoc() {
        return rateddoc;
    }

    public void setRateddoc(Document rateddoc) {
        this.rateddoc = rateddoc;
    }


    
}
