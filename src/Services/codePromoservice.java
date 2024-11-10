/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
        
        
import Models.codePromo;
import Models.Event;
import Models.Service;
import Models.transport;
import Services.userService;
import util.MyConnection;


/**
 *
 * @author LENOVO
 */
public class codePromoservice {
     MyConnection Mycnx = MyConnection.getInstance();
        Connection cnx = Mycnx.getCnx();
       public void ajouterCodePromo(codePromo P) {
       String req = "INSERT INTO `codepromo`( `pourcentage`, `idevent`,`idtransport`,`idservice`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setFloat(1, P.getPourcentage());
            ps.setInt(2, P.getIdevent().getIdEvent());
            ps.setInt(3, P.getIdtransport().getIdtransport());
            ps.setInt(4, P.getIdService().getServiceId());
            ps.executeUpdate();
            System.out.println("code promo ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
         }
}
    
    public List<codePromo> affichercodePromo(){
        List<codePromo> codes = new ArrayList<>();
//    
//         String req = "SELECT * FROM codepromo";
//        try {
//            //2
//            Statement st = cnx.createStatement();
//            //3
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                codePromo P = new codePromo();
//                P.setPourcentage(rs.getFloat(1));//(rs.getInt("id"));
//                P.setIdevent(rs.get);
//                P.setIdtransport(rs.getInt(3));
//                P.setIdservice(rs.getInt(4));
//                codes.add(P);
//            }
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
         return codes;
    }

public void supprimercodepromo(Integer idcodepromo) {
    String req = "DELETE FROM `codepromo` WHERE `idcodepromo`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idcodepromo);
        ps.executeUpdate();
        System.out.println("idcodepromo supprimé avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void mettreAJourCodePromo(codePromo u) {
   String req = "UPDATE `codepromo` SET `pourcentage`=?, `idevent`=?, `idtransport`=?, `idservice`=? WHERE `idcodepromo`=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setFloat(1, u.getPourcentage());
        ps.setInt(2, u.getIdevent().getIdEvent());



        ps.setInt(3, u.getIdtransport().getIdtransport());
        ps.setInt(4, u.getIdService().getServiceId());
        ps.setInt(5, u.getIdcodepromo() );        
        ps.executeUpdate();
        System.out.println("Utilisateur mis à jour avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public float getPourcentage(int idCodePromo) {
    String requete = "SELECT pourcentage FROM codepromo WHERE idcodepromo = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(requete);
        ps.setInt(1, idCodePromo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getFloat("pourcentage");
        }
    } catch (SQLException ex) {
        Logger.getLogger(codePromoservice.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0; 
}

public float calculerCodePromo(codePromo codePromo) {
    float prixInitial = codePromo.getPrixTotal();
    float pourcentage = codePromo.getPourcentage();
    float reduction = prixInitial * (pourcentage / 100);
    return prixInitial - reduction;
}
}
  
