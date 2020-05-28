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
public class sugest {
    private int id;
    private user idu;
    private saison ids;
    private String Msg;

    public sugest() {
    }

    public sugest(int id, user idu, saison ids, String Msg) {
        this.id = id;
        this.idu = idu;
        this.ids = ids;
        this.Msg = Msg;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public user getIdu() {
        return idu;
    }

    public void setIdu(user idu) {
        this.idu = idu;
    }

    public saison getIds() {
        return ids;
    }

    public void setIds(saison ids) {
        this.ids = ids;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    @Override
    public String toString() {
        return "sugest{" + "id=" + id + ", idu=" + idu + ", ids=" + ids + ", Msg=" + Msg + '}';
    }
    
    
}
