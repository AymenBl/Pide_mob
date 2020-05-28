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
public class lieu {
    private int id;
    private String nom;
    private  String description_lieu;
     private String image;
    private ImageView photo;

    public lieu() {
    }

    public lieu(int id, String nom, String description_lieu, String image, ImageView photo) {
        this.id = id;
        this.nom = nom;
        this.description_lieu = description_lieu;
        this.image = image;
        this.photo = photo;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription_lieu() {
        return description_lieu;
    }

    public void setDescription_lieu(String description_lieu) {
        this.description_lieu = description_lieu;
    }

    @Override
    public String toString() {
        return "lieu{" + "id=" + id + ", nom=" + nom + ", description_lieu=" + description_lieu + ", image=" + image + ", photo=" + photo + '}';
    }

   

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
    
    
}
