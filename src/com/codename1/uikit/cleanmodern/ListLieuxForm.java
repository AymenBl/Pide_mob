/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Pidev.huntersclub.info.entities.annimal;
import Pidev.huntersclub.info.entities.lieu;
import Pidev.huntersclub.info.services.AnimalService;
import Pidev.huntersclub.info.services.LieuxService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aymen
 */
public class ListLieuxForm extends BaseForm{
 private Resources theme;
    public ListLieuxForm(Form previous) {
         super("List Lieux", BoxLayout.y());
          theme = UIManager.initFirstTheme("/theme");
        setTitle("List Lieux");
        setLayout(BoxLayout.y());
          ArrayList<lieu> l =LieuxService.getInstance().getAllLieu();
          
          for (int i = 0; i < l.size(); i++) {
            lieu get = l.get(i);
            
              add(addlieu(get));
        }
        Toolbar tb = getToolbar();
      getContentPane().setScrollVisible(true);
        
        super.addSideMenu(theme);
      
          tb.setGlobalToolbar(true);
               Toolbar.setGlobalToolbar(true);
       
        tb.setBackCommand("", e -> previous.showBack());
       
           getStyle().setBgColor(0x99CCCC);
      //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       Style s = UIManager.getInstance().getComponentStyle("Title");
      TextField searchField = new TextField("", "Search Lieu");
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        tb.setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        searchField.addDataChangeListener((i1, i2) -> {
                removeAll();
            String t = searchField.getText();
            if (t.length() < 1) {
                removeAll();
                for (lieu li : l) {
                    this.addItem(li);
                }
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }

            } else {
                for (lieu l1 : l) {
                    if ((l1.getNom().toLowerCase().contains(t.toLowerCase()))) {
                        this.addItem(l1);
                    }
                }
            }
            getContentPane().animateLayout(250);
        });
        getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync();
        });
        
         Label l1;
        List<lieu> liu=LieuxService.getInstance().getAllLieu();
       // l1=new Label ("rechercher");
        
       
        for (int i=0;i<liu.size();i++){
             
           
          
            lieu get=liu.get(i);
            add(addlieu(get));  
            
            
              
        }
           }
    
    
    
       private Container addlieu(lieu l){
         Container holder = new Container(BoxLayout.x());
         Container details = new Container(BoxLayout.y());
      //   EncodedImage enc = EncodedImage.createFromImage(theme.getImage("icon.png"), false);
     
          ImageViewer imgv ;
            Label lnom = new Label(l.getNom());
          //  SpanLabel lDesc = new SpanLabel(l.getDescription_lieu());
            lnom.addPointerPressedListener((ActionListener)((evt) -> {
               
               Dialog.show("Description", "Description :"+l.getDescription_lieu(),  "ok",null );
     
           }));
       
       
           String url = "file://C:/xampp2/htdocs/PIDEV/web/"+ l.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage( deviceWidth/2,  deviceWidth/2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(theme.getImage("icon-loading-png-2.png"), false);
            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + l.getId(),url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer();
                imgv.setImage(i);

            holder.add(imgv);
            
        details.add(lnom);
//        details.add(lDesc);
        holder.add(details);
       holder.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(true).color(0xffffff)); 
      holder.getUnselectedStyle().setMarginBottom(30);
        return holder;
    }
     public void addItem(lieu l) {
         Container holder = new Container(BoxLayout.x());
         Container details = new Container(BoxLayout.y());
      //   EncodedImage enc = EncodedImage.createFromImage(theme.getImage("icon.png"), false);
     
          ImageViewer imgv ;
            Label lnom = new Label(l.getNom());
          //  SpanLabel lDesc = new SpanLabel(l.getDescription_lieu());
            lnom.addPointerPressedListener((ActionListener)((evt) -> {
               
               Dialog.show("Description", "Description :"+l.getDescription_lieu(),  "ok",null );
     
           }));
       
       
           String url = "file://C:/xampp2/htdocs/PIDEV/web/"+ l.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage( deviceWidth/2,  deviceWidth/2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(theme.getImage("icon-loading-png-2.png"), false);
            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + l.getId(),url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer();
                imgv.setImage(i);

            holder.add(imgv);
            
        details.add(lnom);
//        details.add(lDesc);
        holder.add(details);
       holder.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(true).color(0xffffff)); 
      holder.getUnselectedStyle().setMarginBottom(30);
 add(holder);
              
          
 
        
refreshTheme();

    }
}
