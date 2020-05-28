/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.huntersclub.info.services;

import Pidev.huntersclub.info.entities.annimal;
import Pidev.huntersclub.info.entities.lieu;
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
public class SaisonService {
        public ArrayList<saison> Listsaison;
    
    private static SaisonService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private SaisonService() {
         req = new ConnectionRequest();
    }

    public static SaisonService getInstance() {
        if (instance == null) {
            instance = new SaisonService();
        }
        return instance;
    }
    
      public ArrayList<saison> parseSaison(String jsonText){
        try {
            Listsaison=new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> annimalListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(annimalListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)annimalListJson.get("root");
            
            System.out.println(list);
            for(Map<String,Object> obj : list){
                saison s = new saison();
           //     System.out.println(obj);
                float id = Float.parseFloat(obj.get("id").toString());
                s.setId((int) id);
                annimal a =new annimal();
                  Map<String,Object>  a1 =  (Map<String,Object>) obj.get("animal");
                  
               a.setId((int) Float.parseFloat(a1.get("id").toString()));
                a.setNom_annimal((a1.get("nomAnnimal").toString()));
                a.setDescription(a1.get("description").toString());
               a.setImage(a1.get("image").toString());
               
                 s.setIdA(a);
                  Map<String,Object>  L1 =  (Map<String,Object>) obj.get("lieu");
                  lieu l =new lieu();
                   l.setId((int) Float.parseFloat(L1.get("id").toString()));
                   l.setNom((L1.get("nom").toString()));
                   l.setDescription_lieu(L1.get("descriptionLieu").toString()); 
                   l.setImage(L1.get("image").toString());
                  
                 
                s.setIdL(l);
                s.setDate_debut(obj.get("dateDebut").toString());
               s.setDate_fin(obj.get("dateFin").toString());
                s.setRating((int)Float.parseFloat(obj.get("rating").toString()));
               
                             
                Listsaison.add(s);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Listsaison;
    }
      
      
       public ArrayList<saison> getAllSaison(){
        String url = Statics.BASE_URL+"listSaisonM";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               Listsaison = parseSaison(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
           NetworkManager.getInstance().addToQueueAndWait(req);
        return Listsaison  ;
    }
       
        public boolean addRating(saison s,int nbs) {
            System.out.println(nbs);
            System.out.println(s);
        String url = Statics.BASE_URL + "rating/" +s.getId()+ "/" +Statics.getCurrentUser().getId()+"/"+nbs;
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
