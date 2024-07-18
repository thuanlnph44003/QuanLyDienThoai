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
public class KhachHangLSGD {
    private String maKH;
    private String hoVaTen;
    private String sdt;
    private Date ngayMua;
    private String tenSanPham;
    private int soLuong;
    private float giaBan;
    private float thanhTien;
    private int trangThaiHoaDon;

    public KhachHangLSGD() {
    }

    public KhachHangLSGD(String maKH, String hoVaTen, String sdt, Date ngayMua, String tenSanPham, int soLuong, float giaBan, float thanhTien, int trangThaiHoaDon) {
        this.maKH = maKH;
        this.hoVaTen = hoVaTen;
        this.sdt = sdt;
        this.ngayMua = ngayMua;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.thanhTien = thanhTien;
        this.trangThaiHoaDon = trangThaiHoaDon;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public String getSdt() {
        return sdt;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public int getTrangThaiHoaDon() {
        return trangThaiHoaDon;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setTrangThaiHoaDon(int trangThaiHoaDon) {
        this.trangThaiHoaDon = trangThaiHoaDon;
    }
    
    
}
