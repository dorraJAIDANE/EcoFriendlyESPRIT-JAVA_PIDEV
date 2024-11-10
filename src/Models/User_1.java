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

public class User_1 {
    private Integer iduser;
    private String nomuser;
    private String prenomuser ;
    private String mailuser ;
    private String mdpuser ;
    private String adressuser;
    private String walletuser ;
    private String classeuser ;
    private String roleuser ;

    public User_1(Integer iduser) {
        this.iduser = iduser;
    }

    public User_1(Integer iduser, String nomuser, String prenomuser, String mailuser, String mdpuser, String adressuser, String walletuser, String classeuser, String roleuser) {
        this.iduser = iduser;
        this.nomuser = nomuser;
        this.prenomuser = prenomuser;
        this.mailuser = mailuser;
        this.mdpuser = mdpuser;
        this.adressuser = adressuser;
        this.walletuser = walletuser;
        this.classeuser = classeuser;
        this.roleuser = roleuser;
    }

    public User_1() {
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getPrenomuser() {
        return prenomuser;
    }

    public void setPrenomuser(String prenomuser) {
        this.prenomuser = prenomuser;
    }

    public String getMailuser() {
        return mailuser;
    }

    public void setMailuser(String mailuser) {
        this.mailuser = mailuser;
    }

    public String getMdpuser() {
        return mdpuser;
    }

    public void setMdpuser(String mdpuser) {
        this.mdpuser = mdpuser;
    }

    public String getAdressuser() {
        return adressuser;
    }

    public void setAdressuser(String adressuser) {
        this.adressuser = adressuser;
    }

    public String getWalletuser() {
        return walletuser;
    }

    public void setWalletuser(String walletuser) {
        this.walletuser = walletuser;
    }

    public String getClasseuser() {
        return classeuser;
    }

    public void setClasseuser(String classeuser) {
        this.classeuser = classeuser;
    }

    public String getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(String roleuser) {
        this.roleuser = roleuser;
    }

    public int getUserId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


