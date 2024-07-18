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
public class HoaDonChiTiet {
    int MaHoaDon, MaCTHD;
    String MaChiTietSP,TenSanPham,Imei;
    int soluong;
    double giaban;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int MaHoaDon, int MaCTHD, String MaChiTietSP, String TenSanPham, String Imei, int soluong, double giaban) {
        this.MaHoaDon = MaHoaDon;
        this.MaCTHD = MaCTHD;
        this.MaChiTietSP = MaChiTietSP;
        this.TenSanPham = TenSanPham;
        this.Imei = Imei;
        this.soluong = soluong;
        this.giaban = giaban;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(int MaCTHD) {
        this.MaCTHD = MaCTHD;
    }

    public String getMaChiTietSP() {
        return MaChiTietSP;
    }

    public void setMaChiTietSP(String MaChiTietSP) {
        this.MaChiTietSP = MaChiTietSP;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getImei() {
        return Imei;
    }

    public void setImei(String Imei) {
        this.Imei = Imei;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

         
}