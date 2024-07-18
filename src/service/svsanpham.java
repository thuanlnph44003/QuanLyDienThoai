/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DBConnect;
import java.util.ArrayList;
import java.util.List;
import model.sanphamodle;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class svsanpham {

    public List<sanphamodle> fiall() {
        List<sanphamodle> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String query = "SELECT MaSanPham,Hang,DongSP,HeDieuHanh,XuatXu "
                    + "FROM SanPham ";

            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                String maSanPham = rs.getString("MaSanPham");
                String Hang = rs.getString("Hang");
                String Dongsp = rs.getString("DongSP");
                String heDieuHanh = rs.getString("HeDieuHanh");
                String xuatXu = rs.getString("XuatXu");
                lst.add(new sanphamodle(maSanPham, Hang, Dongsp, heDieuHanh, xuatXu));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lst;
    }

    // Trong phương thức thêm sản phẩm
    public boolean themSanPham(sanphamodle product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã sản phẩm trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT MaSanPham FROM SanPham WHERE MaSanPham = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, product.getMaSp());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã sản phẩm đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại trong cơ sở dữ liệu");
            return false;
        } else {
            // Mã sản phẩm không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO SanPham (MaSanPham, Hang, DongSP, HeDieuHanh, XuatXu) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, product.getMaSp());
            pstmt.setString(2, product.getHang());
            pstmt.setString(3, product.getDongsp());
            pstmt.setString(4, product.getHeDieuHanh());
            pstmt.setString(5, product.getXuatXu());

            pstmt.executeUpdate();
            return true;
        }
    } catch (SQLException e) {
        // Xử lý lỗi khi thêm vào cơ sở dữ liệu
        e.printStackTrace();
        return false;
    }
}

    public boolean xoaSanPham(String maSanPham) {
        try {
            Connection conn = DBConnect.getConnection();

            String query = "DELETE FROM SanPham WHERE MaSanPham = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, maSanPham);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Xóa thành công ít nhất một bản ghi
                return true;
            } else {
                // Không có bản ghi nào được xóa
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean suaSanPham(sanphamodle product) {
        try {
            Connection conn = DBConnect.getConnection();

            String query = "UPDATE SanPham SET Hang=?,DongSP=?, HeDieuHanh=?,xuatxu=? WHERE MaSanPham=?";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, product.getHang());
            pstmt.setString(2, product.getDongsp());
            pstmt.setString(3, product.getHeDieuHanh());
            pstmt.setString(4, product.getXuatXu());
            pstmt.setString(5, product.getMaSp()); // Điều kiện để xác định sản phẩm cần sửa

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true; // Sửa thành công
            } else {
                return false; // Không có bản ghi nào được sửa
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Xử lý lỗi khi sửa thông tin sản phẩm
        }
    }

}
