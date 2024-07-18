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
import model.cammeramolde;

/**
 *
 * @author ACER
 */
public class svcammera {
    public List<cammeramolde>fillall(){
        List<cammeramolde>lst=new ArrayList<>();
        
         try {
        Connection conn = DBConnect.getConnection();
        Statement stm = conn.createStatement();
        String query = "SELECT Id_Cmr, CamMera FROM CamMera";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            int Idcmr = rs.getInt("Id_Cmr");
            String cammera = rs.getString("CamMera");
            // Tạo đối tượng dongspmolde và thêm vào danh sách
            cammeramolde cmr=new cammeramolde(Idcmr, cammera);
            lst.add(cmr);
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lst;
        
    }
        
        public boolean themSanPham(cammeramolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT Id_Cmr,CamMera FROM CamMera WHERE Id_Cmr = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, product.getId_Cmr());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã cmr sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO CamMera (Id_Cmr, CamMera) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, product.getId_Cmr());
            pstmt.setString(2, product.getCamMera());
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

        String deleteQuery = "DELETE FROM CamMera WHERE Id_Cmr= ?";
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
 public boolean suaSanPham(cammeramolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE CamMera SET CamMera = ? WHERE Id_Cmr = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setString(1, product.getCamMera());
        updateStmt.setInt(2, product.getId_Cmr());
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

