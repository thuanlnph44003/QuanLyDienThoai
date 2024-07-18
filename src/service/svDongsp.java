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
import model.Imeimolde;
import model.dongspmolde;


/**
 *
 * @author ACER
 */
public class svDongsp {
    public List<dongspmolde> filall() {
    List<dongspmolde> lst = new ArrayList<>();
    try {
        Connection conn = DBConnect.getConnection();
        Statement stm = conn.createStatement();
        String query = "SELECT idDSP, DongSP FROM DongSP";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int Iddongsp = rs.getInt("idDSP");
            String dongspValue = rs.getString("DongSP");
            // Tạo đối tượng dongspmolde và thêm vào danh sách
            dongspmolde dongsp = new dongspmolde(Iddongsp, dongspValue);
            lst.add(dongsp);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lst;
} 
    
    public boolean themSanPham(dongspmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT idDSP,DongSP FROM DongSP WHERE idDSP = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, product.getIdDSP());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã Imei sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO DongSP (idDSP, DongSP) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, product.getIdDSP());
            pstmt.setString(2, product.getDongSP());
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
    
    public boolean suaSanPham(dongspmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE DongSP SET DongSP = ? WHERE idDSP = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, product.getDongSP());
        updateStmt.setInt(2, product.getIdDSP());
        updateStmt.executeUpdate();

        conn.close();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý lỗi khi sửa sản phẩm
        return false;
    }
}
public boolean xoaSanPham(int id) {
    try {
        Connection conn = DBConnect.getConnection();

        String deleteQuery = "DELETE FROM DongSP WHERE idDSP = ?";
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

    
    
    }

