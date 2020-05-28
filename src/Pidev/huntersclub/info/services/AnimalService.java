/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pidev.huntersclub.info.services;

import Pidev.huntersclub.info.entities.annimal;
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
public class AnimalService {
    public ArrayList<annimal> annimal;
    
    private static AnimalService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private AnimalService() {
         req = new ConnectionRequest();
    }

    public static AnimalService getInstance() {
        if (instance == null) {
            instance = new AnimalService();
        }
        return instance;
    }
    
      public ArrayList<annimal> parseAnnimal(String jsonText){
        try {
            annimal=new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> annimalListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(annimalListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)annimalListJson.get("root");
            
            System.out.println(list);
            for(Map<String,Object> obj : list){
                annimal a = new annimal();
             //   System.out.println(obj);
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int) id);
                a.setNom_annimal((obj.get("nomAnnimal").toString()));
                a.setDescription(obj.get("description").toString());
               
               a.setImage(obj.get("image").toString());
               
                annimal.add(a);
            }
            
            
        } catch (IOException ex) {
            
        }
        return annimal;
    }
      
      
       public ArrayList<annimal> getAllAnnimal(){
        String url = Statics.BASE_URL+"listanimalmob";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                annimal = parseAnnimal(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
           NetworkManager.getInstance().addToQueueAndWait(req);
        return annimal  ;
    }
    
}
