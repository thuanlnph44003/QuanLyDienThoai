/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class khoiluongmolde {
    int IdKL;
    String KhoiLuong;

    public khoiluongmolde() {
    }

    public khoiluongmolde(int IdKL, String KhoiLuong) {
        this.IdKL = IdKL;
        this.KhoiLuong = KhoiLuong;
    }

    public int getIdKL() {
        return IdKL;
    }

    public void setIdKL(int IdKL) {
        this.IdKL = IdKL;
    }

    public String getKhoiLuong() {
        return KhoiLuong;
    }

    public void setKhoiLuong(String KhoiLuong) {
        this.KhoiLuong = KhoiLuong;
    }
    
}
