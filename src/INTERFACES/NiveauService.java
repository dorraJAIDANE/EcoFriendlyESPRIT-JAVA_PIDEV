/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACES;

import Models.Niveau;
import java.util.List;

/**
 *
 * @author Dorra
 */
public interface NiveauService {
    List<Niveau> getAllNiveaus();
    Niveau getNiveauById(int id);
}
