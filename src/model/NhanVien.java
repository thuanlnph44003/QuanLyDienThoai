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
public class NhanVien {

    private String ID_MaNV;
    private String TenNV;
    private String MatKhau;
    private String DiaChi;
    private String ChucVu;
    private boolean GioiTinh;
    private Date NgaySinh;
    private String Email;
    private String SDT;
    private String TrangThai;
    private Date NgayTao;
    private Date NgaySua;

    public NhanVien() {
    }

    public NhanVien(String ID_MaNV, String TenNV, String MatKhau, String DiaChi, String ChucVu, boolean GioiTinh, Date NgaySinh, String Email, String SDT, String TrangThai, Date NgayTao, Date NgaySua) {
        this.ID_MaNV = ID_MaNV;
        this.TenNV = TenNV;
        this.MatKhau = MatKhau;
        this.DiaChi = DiaChi;
        this.ChucVu = ChucVu;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.Email = Email;
        this.SDT = SDT;
        this.TrangThai = TrangThai;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
    }

    public NhanVien(String string, String string0, String string1, String string2, String string3, String string4, boolean aBoolean, java.sql.Date date, String string5, String string6, String string7, java.sql.Date date0, java.sql.Date date1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getID_MaNV() {
        return ID_MaNV;
    }

    public void setID_MaNV(String ID_MaNV) {
        this.ID_MaNV = ID_MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "ID_MaNV='" + ID_MaNV + ", TenNV='" + TenNV + ", MatKhau='" + MatKhau + ", DiaChi='" + DiaChi + ", ChucVu='" + ChucVu + ", NgaySinh=" + NgaySinh + ", Email='" + Email + ", SDT='" + SDT + ", TrangThai='" + TrangThai + ", NgayTao=" + NgayTao + ", NgaySua=" + NgaySua + '}';
    }
}
