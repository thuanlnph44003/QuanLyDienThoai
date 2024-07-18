/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DBConnect;
import java.util.List;
import model.sanphamodle;
import java.util.ArrayList;
import java.util.List;
import model.sanphamodle;
import java.sql.*;
import model.sanphamchitietmolde;

/**
 *
 * @author ACER
 */
public class ProductService {

    public List<sanphamchitietmolde> ProductSearch(String keyword) {
        List<sanphamchitietmolde> foundProducts = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            String query = "SELECT * FROM ChiTietSanPham WHERE TenSanPham LIKE ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // Đọc dữ liệu từ ResultSet và tạo đối tượng sanphamodle
                sanphamchitietmolde product = new sanphamchitietmolde();
                product.setMaSanPham(rs.getString("MaSanPham"));
                product.setMaChiTietSanPham(rs.getString("MaChiTietSP"));
                product.setTenSanPham(rs.getString("TenSanPham"));
                product.setPin(rs.getString("Pin"));
                product.setManHinh(rs.getString("ManHinh"));
                product.setCammera(rs.getString("CamMera"));
                product.setKhoiLuong(rs.getString("KhoiLuong"));
                product.setChip(rs.getString("Chip"));
                product.setBoNho(rs.getString("BoNho"));
                product.setHinhAnh(rs.getString("HinhAnh"));
                product.setMauSac(rs.getString("MauSac"));
                product.setGiaNhap(rs.getDouble("GiaNhap"));
                product.setGiaBan(rs.getDouble("GiaBan"));
                // Thêm sản phẩm vào danh sách kết quả
                foundProducts.add(product);
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundProducts;
    }
}
