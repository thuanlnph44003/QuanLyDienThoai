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
import model.pinmolde;



/**
 *
 * @author ACER
 */
public class svpin {
    public  List<pinmolde>fillall(){
        List<pinmolde>lst=new ArrayList<>();
        try {
        Connection conn = DBConnect.getConnection();
        Statement stm = conn.createStatement();
        String query = "SELECT IdPin, Pin FROM Pin";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int IdPin = rs.getInt("IdPin");
            String Pin = rs.getString("Pin");
            // Tạo đối tượng dongspmolde và thêm vào danh sách
            pinmolde tsp = new pinmolde(IdPin, Pin);
            lst.add(tsp);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lst;
        
    }
    
         public boolean themSanPham(pinmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT IdPin,Pin FROM Pin WHERE IdPin = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, product.getIdPin());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã pin sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO Pin (IdPin, Pin) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, product.getIdPin());
            pstmt.setString(2, product.getPin());
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

        String deleteQuery = "DELETE FROM Pin WHERE IdPin= ?";
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
 public boolean suaSanPham(pinmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE Pin SET Pin = ? WHERE IdPin = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, product.getPin());
        updateStmt.setInt(2, product.getIdPin());
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
