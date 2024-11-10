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
public class CurrentOrder {
    private static Orders currentOrder;

    public static Orders getCurrentOrder() {
        return currentOrder;
    }

    public static void setCurrentOrder(Orders currentOrder) {
        CurrentOrder.currentOrder = currentOrder;
    }
    
    
}
