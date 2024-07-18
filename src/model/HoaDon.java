/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
/**
 *
 * @author thuan
 */
public class HoaDon {
   private int  mahoadon;
    private String   makhachhang;
    private String   manv;
    private Double thanhtien;
    private int  trangthai;
    private Date ngaytao; 

    public HoaDon() {
    }

    public HoaDon(int mahoadon, String makhachhang, String manv, Double thanhtien, int trangthai, Date ngaytao) {
        this.mahoadon = mahoadon;
        this.makhachhang = makhachhang;
        this.manv = manv;
        this.thanhtien = thanhtien;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public Double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

 
   
}