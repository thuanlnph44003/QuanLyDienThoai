/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.KhachHang;
import model.KhachHangLSGD;

/**
 *
 * @author Admin
 */
public class KhachHangService {

    public ArrayList<KhachHang> getAllSV() {
        String sql = "SELECT ID_MaKH, HoVaTen, DiaChi, SDT, Email, GioiTinh, TrangThai, NgayTao, NgaySua FROM KhachHang";
        try ( Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<KhachHang> listSV = new ArrayList<>();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getString(1));
                khachHang.setHoTen(rs.getString(2));
                khachHang.setDiaChi(rs.getString(3));
                khachHang.setSDT(rs.getString(4));
                khachHang.setEmail(rs.getString(5));
                khachHang.setGioiTinh(rs.getBoolean(6));
                khachHang.setTrangThai(rs.getBoolean(7));
                khachHang.setNgayTao(rs.getDate(8));
                khachHang.setNgaySua(rs.getDate(9));
                listSV.add(khachHang);
            }
            return listSV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themKhachHang(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (ID_MaKH, HoVaTen, DiaChi, SDT, Email, GioiTinh, TrangThai, NgayTao, NgaySua) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), null)";

        int check = 0;

        try ( Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            // Thêm giá trị cho các tham số
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getSDT());
            ps.setString(5, kh.getEmail());
            ps.setBoolean(6, kh.isGioiTinh());
            ps.setBoolean(7, kh.isTrangThai());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean suaKhachHang(KhachHang kh, String ma) {
        String sql = "UPDATE KhachHang SET HoVaTen=?, DiaChi=?, SDT=?, Email=?, GioiTinh=?, TrangThai=?, NgaySua=GETDATE() WHERE ID_MaKH=?";
        int check = 0;

        try ( Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getDiaChi());
            ps.setString(3, kh.getSDT());
            ps.setString(4, kh.getEmail());
            ps.setBoolean(5, kh.isGioiTinh());
            ps.setBoolean(6, kh.isTrangThai());
            ps.setString(7, ma); // ID_MaKH

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public ArrayList<KhachHang> timKiemKHTen(String hoVaTen) {
        String sql = "SELECT ID_MaKH, HoVaTen, DiaChi, SDT, Email, GioiTinh, TrangThai, NgayTao, NgaySua "
                + "FROM KhachHang "
                + "WHERE lower(HoVaTen) COLLATE Latin1_General_CI_AI LIKE lower(?) COLLATE Latin1_General_CI_AI";
        try ( Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, "%" + hoVaTen + "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<KhachHang> listKH = new ArrayList<>();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getString(1));
                khachHang.setHoTen(rs.getString(2));
                khachHang.setDiaChi(rs.getString(3));
                khachHang.setSDT(rs.getString(4));
                khachHang.setEmail(rs.getString(5));
                khachHang.setGioiTinh(rs.getBoolean(6));
                khachHang.setTrangThai(rs.getBoolean(7));
                khachHang.setNgayTao(rs.getDate(8));
                khachHang.setNgaySua(rs.getDate(9));
                listKH.add(khachHang);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<KhachHangLSGD> getLichSuGDKH() {
    String sql = "SELECT " +
                 "KhachHang.ID_MaKH, " +
                 "KhachHang.HoVaTen, " +
                 "KhachHang.SDT, " +
                 "HoaDonChiTiet.NgayTao AS NgayMua, " +
                 "HoaDonChiTiet.TenSanPham, " +
                 "HoaDonChiTiet.SoLuong, " +
                 "HoaDonChiTiet.GiaBan, " +
                 "HoaDon.ThanhTien, " +
                 "HoaDon.TrangThai AS TrangThaiHoaDon " +
                 "FROM " +
                 "KhachHang " +
                 "JOIN " +
                 "HoaDon ON KhachHang.ID_MaKH = HoaDon.ID_MaKH " +
                 "JOIN " +
                 "HoaDonChiTiet ON HoaDon.ID_MaHoadon = HoaDonChiTiet.ID_MaHoadon";

    try (Connection cn = DBConnect.getConnection(); 
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        ArrayList<KhachHangLSGD> listKhachHangHoaDon = new ArrayList<>();

        while (rs.next()) {
            KhachHangLSGD khachHangHoaDonDTO = new KhachHangLSGD();
            khachHangHoaDonDTO.setMaKH(rs.getString("ID_MaKH"));
            khachHangHoaDonDTO.setHoVaTen(rs.getString("HoVaTen"));
            khachHangHoaDonDTO.setSdt(rs.getString("SDT"));
            khachHangHoaDonDTO.setNgayMua(rs.getDate("NgayMua"));
            khachHangHoaDonDTO.setTenSanPham(rs.getString("TenSanPham"));
            khachHangHoaDonDTO.setSoLuong(rs.getInt("SoLuong"));
            khachHangHoaDonDTO.setGiaBan(rs.getFloat("GiaBan"));
            khachHangHoaDonDTO.setThanhTien(rs.getFloat("ThanhTien"));
            khachHangHoaDonDTO.setTrangThaiHoaDon(rs.getInt("TrangThaiHoaDon"));
            listKhachHangHoaDon.add(khachHangHoaDonDTO);
        }

        return listKhachHangHoaDon;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


}
