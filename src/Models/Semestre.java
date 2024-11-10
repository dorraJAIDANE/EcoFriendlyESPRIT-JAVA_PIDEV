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
public class Semestre {
      private int idSemestre;
    private String semestreName;

    public Semestre() {
    }

    public Semestre(int idSemestre, String semestreName) {
        this.idSemestre = idSemestre;
        this.semestreName = semestreName;
    }

    public Semestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

  

   

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getSemestreName() {
        return semestreName;
    }

    public void setSemestreName(String semestreName) {
        this.semestreName = semestreName;
    }
    

}
