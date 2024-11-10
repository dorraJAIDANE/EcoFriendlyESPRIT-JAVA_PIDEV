/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author louay
 */
public class recue {
   
    private int ticketNumber;
    private Orders order;
    private Date creationDate;
    private Date completionDate;
    private double totalAmount;
    private boolean isPaid;

    public recue(int ticketNumber, Orders order, Date creationDate, Date completionDate, double totalAmount, boolean isPaid) {
        this.ticketNumber = ticketNumber;
        this.order = order;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }


   
    
    
    
}
