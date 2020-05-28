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
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
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
public class ListAnimalFrom extends BaseForm{
     private Resources theme;
     private Form current;
    public ListAnimalFrom(Form previous){
        super("List animal", BoxLayout.y());
           
ArrayList<annimal> Li =AnimalService.getInstance().getAllAnnimal();
    
        System.out.println("ffffffffff"+previous.getName());
          theme = UIManager.initFirstTheme("/theme");
          
       setTitle("List animal");
            
        Tabs swipe = new Tabs();

      
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
       getStyle().setBgColor(0x99CCCC);
          current =  this;
       Toolbar tb = getToolbar();
    ///    setToolbar(tb);
     //   setUIID("listanimal");
        setTitle("List animal");  
        getContentPane().setScrollVisible(true);
        
        super.addSideMenu(theme);
       
          tb.setGlobalToolbar(true);
               Toolbar.setGlobalToolbar(true);
       
        tb.setBackCommand("", e -> previous.showBack());
          
            Style s = UIManager.getInstance().getComponentStyle("Title");
      TextField searchField = new TextField("", "Search animal");
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
                for (annimal l : Li) {
                    this.addItem(l);
                }
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }

            } else {
                for (annimal l1 : Li) {
                    if ((l1.getNom_annimal().toLowerCase().contains(t.toLowerCase()))) {
                        this.addItem(l1);
                    }
                }
            }
            getContentPane().animateLayout(250);
        });
        getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync();
        });
        
       
     
       
   

        for (int i = 0; i < Li.size(); i++) {
            annimal get = Li.get(i);
            add(this.addAnnimal(get));
        }
       
        
     //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
        private Container addAnnimal(annimal a){
         Container holder = new Container(BoxLayout.x());
         Container details = new Container(BoxLayout.y());
           ImageViewer imgv ; 
            int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
          String url = "file://C:/xampp2/htdocs/PIDEV/web/"+ a.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage( deviceWidth/2,  deviceWidth/2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(theme.getImage("icon-loading-png-2.png"), false);
            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + a.getId(),url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer();
                imgv.setImage(i.fill(width, height));

            holder.add(imgv);
            Label nom=new Label("Nom de l'animal:");
            Label lnom = new Label(a.getNom_annimal());
       //     SpanLabel lDesc = new SpanLabel(a.getDescription());
       
        lnom.addPointerPressedListener((ActionListener)((evt) -> {
               
               Dialog.show("Description", "Description :"+a.getDescription(),  "ok",null );
     
           }));
       
   
      
       details.add(nom);
        details.add(lnom);
//        details.add(lDesc);
        holder.add(details);
       holder.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(true).color(0xffffff)); 
       holder.getUnselectedStyle().setMarginBottom(30);
        return holder;
    }
        
         private void addItem(annimal a){
            Container holder = new Container(BoxLayout.x());
         Container details = new Container(BoxLayout.y());
           ImageViewer imgv ; 
            int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
          String url = "file://C:/xampp2/htdocs/PIDEV/web/"+ a.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage( deviceWidth/2,  deviceWidth/2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(theme.getImage("icon-loading-png-2.png"), false);
            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + a.getId(),url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer();
                imgv.setImage(i.fill(width, height));

            holder.add(imgv);
            Label nom=new Label("Nom de l'animal:");
            Label lnom = new Label(a.getNom_annimal());
       //     SpanLabel lDesc = new SpanLabel(a.getDescription());
       
        lnom.addPointerPressedListener((ActionListener)((evt) -> {
               
               Dialog.show("Description", "Description :"+a.getDescription(),  "ok",null );
     
           }));
       
   
      
       details.add(nom);
        details.add(lnom);
//        details.add(lDesc);
        holder.add(details);
       holder.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(true).color(0xffffff)); 
       holder.getUnselectedStyle().setMarginBottom(30);
       add(holder);
       refreshTheme();
       
       }
    
      private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
      
}
