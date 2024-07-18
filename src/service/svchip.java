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
import model.chipmolde;


/**
 *
 * @author ACER
 */
public class svchip {
    public List<chipmolde>fillall(){
        List<chipmolde>lst=new ArrayList<>();
        
        try {
        Connection conn = DBConnect.getConnection();
        Statement stm = conn.createStatement();
        String query = "SELECT IdChip,Chip FROM Chip";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int Idtsp = rs.getInt("IdChip");
            String tenspValue = rs.getString("Chip");
            // Tạo đối tượng dongspmolde và thêm vào danh sách
            chipmolde tsp = new chipmolde(Idtsp, tenspValue);
            lst.add(tsp);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lst;
        
    }
     public boolean themSanPham(chipmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT IdChip,Chip FROM Chip WHERE IdChip = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, product.getIdChip());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã Ten sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO Chip (IdChip, Chip) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, product.getIdChip());
            pstmt.setString(2, product.getChip());
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
        String deleteQuery = "DELETE FROM Chip WHERE IdChip = ?";
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
 public boolean suaSanPham(chipmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE Chip SET Chip = ? WHERE IdChip = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, product.getChip());
        updateStmt.setInt(2, product.getIdChip());
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
