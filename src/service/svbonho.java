/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bonhomolde;


/**
 *
 * @author ACER
 */
public class svbonho {
    public List<bonhomolde>fillall(){
        List<bonhomolde>lst=new ArrayList<>();
        try {
        Connection conn = DBConnect.getConnection();
        Statement stm = conn.createStatement();
        String query = "SELECT IdBN, BoNho FROM BoNho";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int Idtsp = rs.getInt("IdBN");
            String tenspValue = rs.getString("BoNho");
            // Tạo đối tượng dongspmolde và thêm vào danh sách
            bonhomolde tsp = new bonhomolde(Idtsp, tenspValue);
            lst.add(tsp);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lst;
        
    }
     public boolean themSanPham(bonhomolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT IdBN,BoNho FROM BoNho WHERE IdBN = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, product.getIdBN());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã bonho sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO BoNho (IdBN, BoNho) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, product.getIdBN());
            pstmt.setString(2, product.getBoNho());
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
        String deleteQuery = "DELETE FROM BoNho WHERE IdBN = ?";
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
 public boolean suaSanPham(bonhomolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE BoNho SET BoNho = ? WHERE IdBN = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, product.getBoNho());
        updateStmt.setInt(2, product.getIdBN());
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

