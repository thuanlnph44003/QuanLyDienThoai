/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class mausacmolde {
    int IdMS;
    String MauSac;

    public mausacmolde() {
    }

    public mausacmolde(int IdMS, String MauSac) {
        this.IdMS = IdMS;
        this.MauSac = MauSac;
    }

    public int getIdMS() {
        return IdMS;
    }

    public void setIdMS(int IdMS) {
        this.IdMS = IdMS;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }
    
}
