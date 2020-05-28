/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.huntersclub.info.utils;

import Pidev.huntersclub.info.entities.user;

/**
 *
 * @author Aymen
 */
public class Statics {
     public static final String BASE_URL="http://localhost/PIDEV/web/app_dev.php/chasse/Api/";
   
     private static user CurrentUser;

    public static user getCurrentUser() {
        return CurrentUser;
    }

    public static void setCurrentUser(user CurrentUser) {
        Statics.CurrentUser = CurrentUser;
    }

  
   
    
}
