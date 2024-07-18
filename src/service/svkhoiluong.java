/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DBConnect;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.khoiluongmolde;


/**
 *
 * @author ACER
 */
public class svkhoiluong {
    public List<khoiluongmolde>fillall(){
        List<khoiluongmolde>lst=new ArrayList<>();
        try {
        Connection conn = DBConnect.getConnection();
        Statement stm = conn.createStatement();
        String query = "SELECT IdKL, KhoiLuong FROM KhoiLuong";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int Idtsp = rs.getInt("IdKL");
            String tenspValue = rs.getString("KhoiLuong");
            // Tạo đối tượng dongspmolde và thêm vào danh sách
            khoiluongmolde tsp = new khoiluongmolde(Idtsp, tenspValue);
            lst.add(tsp);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lst;
        
    }
     public boolean themSanPham(khoiluongmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT IdKL,KhoiLuong FROM KhoiLuong WHERE IdKL = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, product.getIdKL());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã kl sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO KhoiLuong (IdKL, KhoiLuong) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, product.getIdKL());
            pstmt.setString(2, product.getKhoiLuong());
            pstmt.executeUpdate();
            return true;
        }
    } catch (SQLException e) {
        // Xử lý lỗi khi thao tác với cơ sở dữ liệu
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
     
    public boolean xoaSanPham(int productId) {
    try {
        Connection conn = DBConnect.getConnection();
        String deleteQuery = "DELETE FROM KhoiLuong WHERE IdKL = ?";
        PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
        pstmt.setInt(1, productId);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
 public boolean suaSanPham(khoiluongmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE KhoiLuong SET KhoiLuong = ? WHERE IdKL = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, product.getKhoiLuong());
        updateStmt.setInt(2, product.getIdKL());
        updateStmt.executeUpdate();

        conn.close();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý lỗi khi sửa sản phẩm
        return false;
    }
}
        
        
    }

