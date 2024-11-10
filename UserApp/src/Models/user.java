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
public class user {
    private Integer iduser;
    private String nomuser;
    private String prenomuser ;
    private String mailuser ;
    private String mdpuser ;
    private String adressuser;
    private String walletuser ;
    private String classeuser ;
    private String roleuser ;
    
    public user( String nomuser, String prenomuser, String mailuser, String mdpuser, String adressuser, String walletuser, String classeuser, String roleuser) {
        this.nomuser = nomuser;
        this.prenomuser = prenomuser;
        this.mailuser = mailuser;
        this.mdpuser = mdpuser;
        this.adressuser = adressuser;
        this.walletuser = walletuser;
        this.classeuser = classeuser;
        this.roleuser = roleuser;
    }
    public user (){
        
    }

         public user ( int iduser ,String nomuser, String prenomuser, String mailuser, String classeuser, String adressuser) {
            this.iduser = iduser;
             this.nomuser = nomuser;
            this.prenomuser = prenomuser;
            this.mailuser = mailuser;
            this.classeuser = classeuser;
            this.adressuser = adressuser;
         }
    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    // Getters et Setters pour nomuser
    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    // Getters et Setters pour prenomuser
    public String getPrenomuser() {
        return prenomuser;
    }

    public void setPrenomuser(String prenomuser) {
        this.prenomuser = prenomuser;
    }

    // Getters et Setters pour mailuser
    public String getMailuser() {
        return mailuser;
    }

    public void setMailuser(String mailuser) {
        this.mailuser = mailuser;
    }

    // Getters et Setters pour mdpuser
    public String getMdpuser() {
        return mdpuser;
    }

    public void setMdpuser(String mdpuser) {
        this.mdpuser = mdpuser;
    }

    // Getters et Setters pour adressuser
    public String getAdressuser() {
        return adressuser;
    }

    public void setAdressuser(String adressuser) {
        this.adressuser = adressuser;
    }

    // Getters et Setters pour walletuser
    public String getWalletuser() {
        return walletuser;
    }

    public void setWalletuser(String walletuser) {
        this.walletuser = walletuser;
    }

    // Getters et Setters pour classeuser
    public String getClasseuser() {
        return classeuser;
    }

    public void setClasseuser(String classeuser) {
        this.classeuser = classeuser;
    }

    // Getters et Setters pour roleuser
    public String getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(String roleuser) {
        this.roleuser = roleuser;
    }
}
