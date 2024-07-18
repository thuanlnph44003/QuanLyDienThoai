/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class giamgiamolde {
    String ID_MaGiamGia,TenMaGiamGia;
    int Giam;
    Date NgayBatDau,NgaKetThuc;

    public giamgiamolde() {
    }

    public giamgiamolde(String ID_MaGiamGia, String TenMaGiamGia, int Giam, Date NgayBatDau, Date NgaKetThuc) {
        this.ID_MaGiamGia = ID_MaGiamGia;
        this.TenMaGiamGia = TenMaGiamGia;
        this.Giam = Giam;
        this.NgayBatDau = NgayBatDau;
        this.NgaKetThuc = NgaKetThuc;
    }

    public String getID_MaGiamGia() {
        return ID_MaGiamGia;
    }

    public void setID_MaGiamGia(String ID_MaGiamGia) {
        this.ID_MaGiamGia = ID_MaGiamGia;
    }

    public String getTenMaGiamGia() {
        return TenMaGiamGia;
    }

    public void setTenMaGiamGia(String TenMaGiamGia) {
        this.TenMaGiamGia = TenMaGiamGia;
    }

    public int getGiam() {
        return Giam;
    }

    public void setGiam(int Giam) {
        this.Giam = Giam;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgaKetThuc() {
        return NgaKetThuc;
    }

    public void setNgaKetThuc(Date NgaKetThuc) {
        this.NgaKetThuc = NgaKetThuc;
    }
    
    
    
}
