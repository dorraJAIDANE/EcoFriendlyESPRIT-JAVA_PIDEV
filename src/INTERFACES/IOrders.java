/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACES;

import java.util.List;

/**
 *
 * @author louay
 */
public interface IOrders <T> {
    public void ajouterorder(T t);
    public void supprimerorder(int id);
    public T getOneorder(int id );
   public List<T> getAllorder() ;
   
}
