/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Pidev.huntersclub.info.entities.lieu;
import Pidev.huntersclub.info.entities.saison;
import Pidev.huntersclub.info.services.LieuxService;
import Pidev.huntersclub.info.services.SaisonService;
import Pidev.huntersclub.info.services.SugestService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.List;

/**
 *
 * @author Aymen
 */
public class ListSaison extends BaseForm{
     private Resources theme;
       private int nbrstar=0 ;
    public ListSaison(Form previous) {
          super("List Lieux", BoxLayout.y());
           theme = UIManager.initFirstTheme("/theme");
        setTitle("List Saison");
        setLayout(BoxLayout.y());
//          SpanLabel sp = new SpanLabel();
//            sp.setText(SaisonService.getInstance().getAllSaison().toString());
//       add(sp);

   Toolbar tb = getToolbar();
      getContentPane().setScrollVisible(true);
        
        super.addSideMenu(theme);
        tb.addSearchCommand(e -> {});
          tb.setGlobalToolbar(true);
               Toolbar.setGlobalToolbar(true);
       
     //   tb.setBackCommand("", e -> previous.showBack());
     
          tb.addSearchCommand(e -> {});
           getStyle().setBgColor(0x99CCCC);
      List<saison> ls = SaisonService.getInstance().getAllSaison();
        for (int i = 0; i < ls.size(); i++) {
            saison get = ls.get(i);
            add(addSaison(get));
            
        }
     
       
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new NewsfeedForm(theme).showBack());
          
    }
    
      private Container addSaison(saison s){
         Container holder = new Container(BoxLayout.x());
         Container details = new Container(BoxLayout.y());
         Container images = new Container(BoxLayout.y());
      //   EncodedImage enc = EncodedImage.createFromImage(theme.getImage("icon.png"), false);
     nbrstar= s.getRating();
            ImageViewer imgv ;
            ImageViewer imgv1 ;
              Button titre= new Button("Evaluer");
            SpanLabel lnomLieu = new SpanLabel("Lieu : "+s.getIdL().getNom());
            SpanLabel LnomAnimmal = new SpanLabel("Annimal :"+ s.getIdA().getNom_annimal());
             SpanLabel DateDeb = new SpanLabel(" Date Debut : "+ s.getDate_debut());
             SpanLabel Datefin = new SpanLabel("Date Fin : "+s.getDate_fin());
             SpanLabel Rating = new SpanLabel("Rating : "+s.getRating());
       
            
            // image animmal 
           String url = "file://C:/xampp2/htdocs/PIDEV/web/"+ s.getIdA().getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage( deviceWidth/4, deviceWidth/4,0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(theme.getImage("icon-loading-png-2.png"), false);
            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + s.getIdA().getId(),url, URLImage.RESIZE_SCALE);
                imgv = new ImageViewer();
                imgv.setImage(i);

            images.add(imgv);
               // image lieu 
           String url1 = "file://C:/xampp2/htdocs/PIDEV/web/"+ s.getIdL().getImage();
          int deviceWidth1 = Display.getInstance().getDisplayWidth();
           Image placeholder1 = Image.createImage( deviceWidth/4, deviceWidth/4,0xbfc9d2);
            EncodedImage encImage1 = EncodedImage.createFromImage(theme.getImage("icon-loading-png-2.png"), false);
            Image i1 = URLImage.createToStorage(encImage1, "fileNameInStoragez" + s.getIdL().getId(),url1, URLImage.RESIZE_SCALE);
                imgv1 = new ImageViewer();
                imgv1.setImage(i1);

            images.add(imgv1);
            titre.addPointerReleasedListener(l->{
                System.out.println( s);
             new RatingForm(this, s).show();
            });
            
          
          holder.add(images);
        details.add( LnomAnimmal);
        details.add( lnomLieu);
        details.add(DateDeb);
        details.add( Datefin);
          details.add(titre);
          details.add(FlowLayout.encloseCenter(createStarRankSlider()));
        holder.add(details);
        holder.getUnselectedStyle().setBorder(RoundBorder.create().rectangle(true).color(0xffffff));   
       
         holder.getUnselectedStyle().setMarginBottom(30);
         
         
        return holder;
    }
      
     
    
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
     }

private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setProgress(nbrstar*2);
    starRank.setEnabled(false);
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
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
        System.out.println(nbrstar/2);
    });
  
    return starRank;
}
private void showStarPickingForm() {
    Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
    hi.add(FlowLayout.encloseCenter(createStarRankSlider()));
    hi.show();
}

    
    
}
