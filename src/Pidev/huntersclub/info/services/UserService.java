/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.huntersclub.info.services;

import Pidev.huntersclub.info.entities.user;
import Pidev.huntersclub.info.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



/**
 *
 * @author Aymen
 */
public class UserService {
       public ArrayList<user> User;
    
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    
    
      public user parseUser(String jsonText){
           try {
               user u = new user();
               JSONParser j = new JSONParser();
               Map<String,Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
               //    System.out.println(UserListJson);
               //    System.out.println(UserListJson.get("id"));
               //    System.out.println(UserListJson.get("username"));
               float id = Float.parseFloat(UserListJson.get("id").toString());
               u.setId((int) id);
               u.setUsername((UserListJson.get("username").toString()));
               u.setEmail(UserListJson.get("email").toString());
               return u;
           } catch (IOException ex) {
               System.out.println(ex.getMessage());
           }
           return u;
    }
       user u = new user();
      public user getUserById(int id){
         
        String url = Statics.BASE_URL+"getUser/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    System.out.println(req.getResponseData().toString());
                u = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
          NetworkManager.getInstance().addToQueueAndWait(req);
        return u  ;
    }
}
