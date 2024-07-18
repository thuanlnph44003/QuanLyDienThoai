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
import model.manhinhmolde;
import model.pinmolde;

/**
 *
 * @author ACER
 */
public class svmanhinh {
    public List<manhinhmolde>fillall(){
        List<manhinhmolde>lst=new ArrayList<>();
         try {
        Connection conn = DBConnect.getConnection();
        Statement stm = conn.createStatement();
        String query = "SELECT IdMH, ManHinh FROM ManHinh";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int Idmh = rs.getInt("IdMH");
            String manhinh = rs.getString("ManHinh");
            // Tạo đối tượng dongspmolde và thêm vào danh sách
            manhinhmolde mh = new manhinhmolde(Idmh, manhinh);
            lst.add(mh);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lst;
        
    }
    public boolean themSanPham(manhinhmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT IdMH,ManHinh FROM ManHinh WHERE IdMH = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, product.getIdMH());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã mh sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO ManHinh (IdMH, ManHinh) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, product.getIdMH());
            pstmt.setString(2, product.getManHinh());
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

        String deleteQuery = "DELETE FROM ManHinh WHERE IdMH= ?";
        PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
        deleteStmt.setInt(1, id);
        deleteStmt.executeUpdate();
        conn.close();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý lỗi khi xóa sản phẩm
        return false;
    }
}
 public boolean suaSanPham(manhinhmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE ManHinh SET ManHinh = ? WHERE IdMH = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, product.getManHinh());
        updateStmt.setInt(2, product.getIdMH());
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

