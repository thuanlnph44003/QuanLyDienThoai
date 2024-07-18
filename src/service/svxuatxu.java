/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DBConnect;
import java.util.ArrayList;
import java.util.List;
import model.xuatxu;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ACER
 */
public class svxuatxu {
    public List<xuatxu> fillComboBox() {
        List<xuatxu> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String query = "SELECT IdXX,xuatxu FROM XuatXu"; // Sửa lỗi trong câu lệnh SQL
            ResultSet rs = stm.executeQuery(query);
 
            while (rs.next()) {
                String xuatXuValue = rs.getString("xuatxu");
                int IdXX=rs.getInt("IdXX");
                // Tạo đối tượng xuatxu và thêm vào danh sách
                xuatxu xuatXu = new xuatxu(IdXX, xuatXuValue);
                lst.add(xuatXu);
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lst;
    }
        
        
        public boolean themSanPham(xuatxu product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT IdXX FROM XuatXu WHERE IdXX = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, product.getIdXX());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO XuatXu (IdXX, XuatXu) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, product.getIdXX());
            pstmt.setString(2, product.getXuatxu());
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


        public boolean xoaSanPham(int id) {
    try {
        Connection conn = DBConnect.getConnection();

        String query = "DELETE FROM XuatXu WHERE IdXX = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        // Xử lý lỗi khi xóa khỏi cơ sở dữ liệu
        e.printStackTrace();
        return false;
    }
}
        
     public boolean suaSanPham(xuatxu product) {
        try {
            Connection conn = DBConnect.getConnection();

            String query = "UPDATE XuatXu SET XuatXu = ? WHERE IdXX = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, product.getXuatxu());
            pstmt.setInt(2,product.getIdXX());
           

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
