/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.huntersclub.info.entities;


/**
 *
 * @author Aymen
 */
public class saison {
    private int id;
    private String Date_debut;
    private String Date_fin;
    private annimal idA;
    private lieu  idL;
    private int rating = 0;

    public saison() {
    }

    public saison(int id, String Date_debut, String Date_fin, annimal idA, lieu idL, int rating) {
        this.id = id;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.idA = idA;
        this.idL = idL;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(String Date_debut) {
        this.Date_debut = Date_debut;
    }

    public String getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(String Date_fin) {
        this.Date_fin = Date_fin;
    }

    public annimal getIdA() {
        return idA;
    }

    public void setIdA(annimal idA) {
        this.idA = idA;
    }

    public lieu getIdL() {
        return idL;
    }

    public void setIdL(lieu idL) {
        this.idL = idL;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "saison{" + "id=" + id + ", Date_debut=" + Date_debut + ", Date_fin=" + Date_fin + ", idA=" + idA + ", idL=" + idL + ", rating=" + rating + '}';
    }
    
    
}
