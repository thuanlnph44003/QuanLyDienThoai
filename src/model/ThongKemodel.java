/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bui Tuan Hung
 */
public class ThongKemodel {
    private Integer ID_MaHoaDon;
    private Integer ID_MaHoaDonChiTiet;
    private String TenSanPham;
    private Integer SoLuong;
    private Double GiaBan;
    private float tongDoanhThu;
    private long tongDonHang;

    public ThongKemodel() {
    }

    public ThongKemodel(Integer ID_MaHoaDon, Integer ID_MaHoaDonChiTiet, String TenSanPham, Integer SoLuong, Double GiaBan, float tongDoanhThu, long tongDonHang) {
        this.ID_MaHoaDon = ID_MaHoaDon;
        this.ID_MaHoaDonChiTiet = ID_MaHoaDonChiTiet;
        this.TenSanPham = TenSanPham;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
        this.tongDoanhThu = tongDoanhThu;
        this.tongDonHang = tongDonHang;
    }

    public Integer getID_MaHoaDon() {
        return ID_MaHoaDon;
    }

    public void setID_MaHoaDon(Integer ID_MaHoaDon) {
        this.ID_MaHoaDon = ID_MaHoaDon;
    }

    public Integer getID_MaHoaDonChiTiet() {
        return ID_MaHoaDonChiTiet;
    }

    public void setID_MaHoaDonChiTiet(Integer ID_MaHoaDonChiTiet) {
        this.ID_MaHoaDonChiTiet = ID_MaHoaDonChiTiet;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public float getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(float tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public long getTongDonHang() {
        return tongDonHang;
    }

    public void setTongDonHang(long tongDonHang) {
        this.tongDonHang = tongDonHang;
    }
    
}