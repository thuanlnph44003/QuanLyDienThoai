/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhachHang {
    private String MaKH;
    private String HoTen;
    private String DiaChi;
    private String SDT;
    private String Email;
    private boolean GioiTinh;
    private boolean TrangThai;
    private Date NgayTao;
    private Date NgaySua;

    public KhachHang() {
    }

    public KhachHang(String MaKH, String HoTen, String DiaChi, String SDT, String Email, boolean GioiTinh, boolean TrangThai, Date NgayTao, Date NgaySua) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public String getEmail() {
        return Email;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }
    
}