

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.user;

/**
 *
 * @author LENOVO
 */
public class UserSession {

    private static user currentUser;

    public static void login(user user) {
        currentUser = user;
    }

    public static user getCurrentUser() {
        return currentUser;
    }
}
