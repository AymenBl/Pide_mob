/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Pidev.huntersclub.info.entities.saison;
import Pidev.huntersclub.info.entities.sugest;
import Pidev.huntersclub.info.services.SaisonService;
import Pidev.huntersclub.info.services.SugestService;
import Pidev.huntersclub.info.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import static com.codename1.ui.CN.*;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.ComponentAnimation;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.sun.glass.events.WheelEvent;
import java.util.ArrayList;
import com.codename1.charts.util.ColorUtil;



/**
 *
 * @author Aymen
 */
public class RatingForm extends Form{
     private Resources theme;
     private int nbrstar ;
     private ArrayList<sugest> lisug ;
     private Button btnsug ;
     private static saison s1;
     private Form Pr1 ;
public RatingForm(Form previous,saison s){
     s1 =s;
    System.out.println("detttttt: "+s1);
     Pr1 =previous;
       theme = UIManager.initFirstTheme("/theme");
        setTitle("Saison");
        getAllStyles().setTextDecoration(150);
        setLayout(BoxLayout.yCenter());
           getStyle().setBgColor(ColorUtil.CYAN);
        btnsug= new Button("Sugestion");
         Container holder = new Container(BoxLayout.y());
         Container details = new Container(BoxLayout.y());
         Container images = new Container(BoxLayout.x());
           Container sugest = new Container(BoxLayout.y());
           lisug = SugestService.getInstance().getAllsugestBySasion(s);
         
//         EncodedImage enc = EncodedImage.createFromImage(theme.getImage("icon.png"), false);
        
            ImageViewer imgv ;
            ImageViewer imgv1 ;
           /* SpanLabel lnomLieu = new SpanLabel(" Lieu : "+s.getIdL().getNom());
                        SpanLabel lDescLieu = new SpanLabel(" Description Lieu : "+s.getIdL().getDescription_lieu());

            SpanLabel LnomAnimmal = new SpanLabel(" Annimal :"+ s.getIdA().getNom_annimal());
                        SpanLabel lDescAnnimal= new SpanLabel("Description Annimal : "+s.getIdA().getDescription());

             SpanLabel DateDeb = new SpanLabel(" Date Debut : "+ s.getDate_debut());
             SpanLabel Datefin = new SpanLabel("Date Fin : "+s.getDate_fin());
             SpanLabel Rating = new SpanLabel("Rating : "+s.getRating());*/
       
            
            // image animmal 
           String url = "file://C:/xampp2/htdocs/PIDEV/web/"+ s.getIdA().getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage( deviceWidth/8,  deviceWidth/8, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(theme.getImage("icon-loading-png-2.png"), false);
            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + s.getIdA().getId(),url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer();
                imgv.setImage(i);

            images.add(imgv);
               // image lieu 
           String url1 = "file://C:/xampp2/htdocs/PIDEV/web/"+ s.getIdL().getImage();
          int deviceWidth1 = Display.getInstance().getDisplayWidth();
           Image placeholder1 = Image.createImage( deviceWidth1/8,  deviceWidth1/8, 0xbfc9d2);
            EncodedImage encImage1 = EncodedImage.createFromImage(theme.getImage("icon-loading-png-2.png"), false);
            Image i1 = URLImage.createToStorage(encImage1, "fileNameInStoragez" + s.getIdL().getId(),url1, URLImage.RESIZE_SCALE);
                imgv1 = new ImageViewer();
                imgv1.setImage(i1);

            images.add(imgv1);
            Button eval = new Button("Evaluer");
            
            eval.addActionListener(l->{ 
                System.out.println(s);
                SaisonService.getInstance().addRating(s,nbrstar);
                new ListSaison(new NewsfeedForm(theme)).showBack();
                System.out.println(nbrstar);
            });
            
            
          holder.add(images);
/*          LnomAnimmal.getAllStyles().setFgColor(ColorUtil.BLACK);
        details.add( LnomAnimmal);
        lDescAnnimal.getAllStyles().setFgColor(ColorUtil.BLACK);
        details.add(lDescAnnimal);
        lnomLieu.getAllStyles().setFgColor(ColorUtil.BLACK);
        details.add( lnomLieu);
        lDescLieu.getAllStyles().setFgColor(ColorUtil.BLACK);
        details.add(lDescLieu);
        DateDeb.getAllStyles().setFgColor(ColorUtil.BLACK);
        details.add(DateDeb);
        Datefin.getAllStyles().setFgColor(ColorUtil.BLACK);
        details.add( Datefin);*/
        holder.add(details);
        holder.add(FlowLayout.encloseCenter(createStarRankSlider()));
         add(holder);
           add(eval);
           
         
         TextArea Tamsg =new TextArea();
         sugest.add(Tamsg);
         btnsug.addActionListener((evt) -> {
             sugest sug = new sugest();
             sug.setIds(s);
             sug.setIdu(Statics.getCurrentUser());
             sug.setMsg(Tamsg.getText());
             
             SugestService.getInstance().addSugest(sug);
             new RatingForm(previous, s).show();
         });
         
         sugest.add(btnsug);
         
         for (int j = 0; j < lisug.size(); j++) {
        sugest get = lisug.get(j);
        sugest.add(this.ListSugest(get));
          }
         
         add(sugest);
         
            
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListSaison(new NewsfeedForm(theme)).showBack());
}
    
   
    
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
     }

private Slider createStarRankSlider() {
    Slider starRank = new Slider();
   
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    starRank.addActionListener(l->{ 
     nbrstar= starRank.getProgress();
        System.out.println(nbrstar);
    });
  
    return starRank;
}

    public int getNbrstar() {
        return nbrstar;
    }
    
    
     private Container ListSugest(sugest l){
         Container holder = new Container(BoxLayout.yCenter());
         Container det = new Container(BoxLayout.xCenter());
         Container rem = new Container(BoxLayout.xRight());
         
     
            Button remove = new Button("X");
            remove.getAllStyles().setBgColor(ColorUtil.red(20));
            Label lnom = new Label("         "+l.getIdu().getUsername());
            lnom.getAllStyles().setFgColor(ColorUtil.BLACK);
            lnom.setSize(new Dimension(8,10));
            
            SpanLabel Msg = new SpanLabel(l.getMsg()+"\n \n");
            Msg.getAllStyles().setFgColor(ColorUtil.BLACK);
            Msg.setSize(new Dimension(16,20));
//            lnom.addPointerPressedListener((ActionListener)((evt) -> {
//               
//               Dialog.show("Description", "Description :"+l.getDescription_lieu(),  "ok",null );
//     
//           }));
       
       remove.addActionListener((evt) -> {
         SugestService.getInstance().DeleteSugest(l);
           System.out.println(s1);
         new RatingForm(this.Pr1,RatingForm.s1).show();
       });
         
       holder.add(lnom);
       if(Statics.getCurrentUser().getId()==l.getIdu().getId()){
         rem.add(remove);
       holder.add(rem);
       }
       det.add(Msg);
        holder.add(det);
       
       holder.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(true).color(ColorUtil.WHITE)); 
      holder.getUnselectedStyle().setMarginBottom(30);
        return holder;
    }

}
