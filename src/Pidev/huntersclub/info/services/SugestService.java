/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.huntersclub.info.services;

import Pidev.huntersclub.info.entities.sugest;
import Pidev.huntersclub.info.entities.user;
import Pidev.huntersclub.info.entities.saison;
import Pidev.huntersclub.info.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aymen
 */
public class SugestService {
        public ArrayList<sugest> Listsugest;
    
    private static SugestService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private SugestService() {
         req = new ConnectionRequest();
    }

    public static SugestService getInstance() {
        if (instance == null) {
            instance = new SugestService();
        }
        return instance;
    }
    
      public ArrayList<sugest> parseSaison(String jsonText){
        try {
            Listsugest=new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> annimalListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(annimalListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)annimalListJson.get("root");
            
            System.out.println(list);
            for(Map<String,Object> obj : list){
                sugest s = new sugest();
           //     System.out.println(obj);
                float id = Float.parseFloat(obj.get("id").toString());
                s.setId((int) id);
                saison sa =new saison();
                  Map<String,Object>  a1 =  (Map<String,Object>) obj.get("saison");
                  
               sa.setId((int) Float.parseFloat(a1.get("id").toString()));

                 s.setIds(sa);
                 
                  Map<String,Object> u = (Map<String,Object>) obj.get("user");
              user u1 =new user();  //((int) Float.parseFloat(u.get(0).getId()), u.get(req), u.get("email"));
              u1.setId((int) Float.parseFloat(u.get("id").toString()));
              u1.setUsername(u.get("username").toString());
              u1.setEmail(u.get("email").toString());
                  
                 
                s.setIdu(u1);
                s.setMsg(obj.get("meg").toString());
               
                             
                Listsugest.add(s);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Listsugest;
    }
      
      
       public ArrayList<sugest> getAllsugestBySasion(saison s){
           System.out.println(s);
        String url = Statics.BASE_URL+"getSugestBySasion/"+s.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               Listsugest = parseSaison(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
           NetworkManager.getInstance().addToQueueAndWait(req);
        return Listsugest ;
    }
       
        public boolean addSugest(sugest s) {
            System.out.println(s);
        String url = Statics.BASE_URL + "AddSug/" +s.getMsg()+"/"+s.getIds().getId()+"/" +Statics.getCurrentUser().getId();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
        public boolean DeleteSugest(sugest s) {
            System.out.println(s);
        String url = Statics.BASE_URL + "DeleteSug/"+s.getId();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 

}
