/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class tensanphamolde {
    int Idtensp;
    String tensanpham;

    public tensanphamolde() {
    }

    public tensanphamolde(int Idtensp, String tensanpham) {
        this.Idtensp = Idtensp;
        this.tensanpham = tensanpham;
    }

    public int getIdtensp() {
        return Idtensp;
    }

    public void setIdtensp(int Idtensp) {
        this.Idtensp = Idtensp;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }
    
}
