/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.huntersclub.info.services;

import Pidev.huntersclub.info.entities.lieu;
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
public class LieuxService {
     private static LieuxService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private   ArrayList<lieu> Lieux=new ArrayList<lieu>();

    private LieuxService() {
         req = new ConnectionRequest();
    }

    public static LieuxService getInstance() {
        if (instance == null) {
            instance = new LieuxService();
        }
        return instance;
    }
    
      public ArrayList<lieu> parseAnnimal(String jsonText){
        ArrayList<lieu> listlieu=new ArrayList<lieu>();
          try {
           
            JSONParser j = new JSONParser();
            
            Map<String,Object> lieuListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(lieuListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)lieuListJson.get("root");
            
            System.out.println(list);
            for(Map<String,Object> obj : list){
                lieu l = new lieu();
               // System.out.println(obj);
                l.setId((int) Float.parseFloat(obj.get("id").toString()));
                l.setNom((obj.get("nom").toString()));
                l.setDescription_lieu(obj.get("descriptionLieu").toString()); 
                l.setImage(obj.get("image").toString());
               
                listlieu.add(l);
            }
            
            
        } catch (IOException ex) {
            
        }
        return listlieu;
    }
      
      
       public ArrayList<lieu> getAllLieu(){
        String url = Statics.BASE_URL+"listlieuM";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Lieux = parseAnnimal(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
           NetworkManager.getInstance().addToQueueAndWait(req);
        return Lieux  ;
    }
    
}
