/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.huntersclub.info.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author Aymen
 */
public class annimal {
    private int id;
    private String nom_annimal;
    private String description;
    private String image;
    private ImageView photo;

    public annimal() {
    }

    public annimal(int id, String nom_annimal, String description, String image, ImageView photo) {
        this.id = id;
        this.nom_annimal = nom_annimal;
        this.description = description;
        this.image = image;
        this.photo = photo;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public annimal(int id, String nom_annimal, String description, String image) {
        this.id = id;
        this.nom_annimal = nom_annimal;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_annimal() {
        return nom_annimal;
    }

    public void setNom_annimal(String nom_annimal) {
        this.nom_annimal = nom_annimal;
      //  this.photo= new ImageView(image);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /* @Override
    public String toString() {
        return  nom_annimal ;
    }
    */

    @Override
    public String toString() {
        return "annimal{" + "id=" + id + ", nom_annimal=" + nom_annimal + ", description=" + description + ", image=" + image + ", photo=" + photo + '}';
    }

  
    
    
    
}
