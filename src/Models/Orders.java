/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author louay
 */
public class Orders {
    private int orderId;
    private int num_order;
    private user user;
    private Date pickupDateTime;
    private String status;
    private Service services;
    private String phonenumber;
    private float priceorder;

    public float getPriceorder() {
        return priceorder;
    }

    public void setPriceorder(float priceorder) {
        this.priceorder = priceorder;
    }

    public Orders(int orderId, int num_order, user user, Date pickupDateTime, String status, Service services, String phonenumber, float priceorder) {
        this.orderId = orderId;
        this.num_order = num_order;
        this.user = user;
        this.pickupDateTime = pickupDateTime;
        this.status = status;
        this.services = services;
        this.phonenumber = phonenumber;
        this.priceorder = priceorder;
    }
   

    public Orders(int orderId, int num_order, user user, Date pickupDateTime, Date deliveryDateTime, String status, Service services, String phonenumber) {
        this.orderId = orderId;
        this.num_order = num_order;
        this.user = user;
        this.pickupDateTime = pickupDateTime;
        this.status = status;
        this.services = services;
        this.phonenumber = phonenumber;
    }

    public Orders(int orderId, user user, Date pickupDateTime, Date deliveryDateTime, String status, Service services) {
        this.orderId = orderId;
        this.user = user;
        this.pickupDateTime = pickupDateTime;
        this.status = status;
        this.services = services;
    }

    public Orders(int orderId, int num_order, user user, Date pickupDateTime, Date deliveryDateTime, String status) {
        this.orderId = orderId;
        this.num_order = num_order;
        this.user = user;
        this.pickupDateTime = pickupDateTime;

        this.status = status;
    }

    public Orders() {
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getNum_order() {
        return num_order;
    }

    public void setNum_order(int num_order) {
        this.num_order = num_order;
    }

    public user getuser() {
        return user;
    }

    public void setuser(user user) {
        this.user = user;
    }

    public Date getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(Date pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    
    public void setDeliveryDateTime(Date deliveryDateTime) {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Service getServices() {
        return services;
    }

    public void setServices(Service services) {
        this.services = services;
    }
 public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", num_order=" + num_order + ", user=" + user + ", pickupDateTime=" + pickupDateTime + ", status=" + status + ", services=" + services + ", phonenumber=" + phonenumber + ", priceorder=" + priceorder + '}';
    }

    public void setUser(user user1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
