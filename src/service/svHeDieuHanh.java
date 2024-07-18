/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.hedieuhanhmolde;
import model.xuatxu;
import java.sql.PreparedStatement;

/**
 *
 * @author ACER
 */
public class svHeDieuHanh {

    public List<hedieuhanhmolde> fillall() {
        List<hedieuhanhmolde> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String query = "SELECT IdHDH, HeDieuHanh FROM HeDieuHanh";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int Idhdh = rs.getInt("IdHDH");
                String hdhString = rs.getString("HeDieuHanh");

                hedieuhanhmolde hdh = new hedieuhanhmolde(Idhdh, hdhString);
                lst.add(hdh);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lst;
    }

    public boolean themSanPham(hedieuhanhmolde product) {
        try {
            Connection conn = DBConnect.getConnection();

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
            String checkQuery = "SELECT IdHDH FROM HeDieuHanh WHERE IdHDH = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, product.getIdHDH());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Mã đã tồn tại, không thể thêm
                JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
                String insertQuery = "INSERT INTO HeDieuHanh (IdHDH, HeDieuHanh) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                pstmt.setInt(1, product.getIdHDH());
                pstmt.setString(2, product.getHeDieuHanh());
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

    public boolean suaSanPham(hedieuhanhmolde product) {
        try {
            Connection conn = DBConnect.getConnection();

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String updateQuery = "UPDATE HeDieuHanh SET HeDieuHanh = ? WHERE IdHDH = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, product.getHeDieuHanh());
            pstmt.setInt(2, product.getIdHDH());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Xử lý lỗi khi thao tác với cơ sở dữ liệu
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi sửa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean xoaSanPham(int  id) {
        try {
            Connection conn = DBConnect.getConnection();

            String query = "DELETE FROM HeDieuHanh WHERE IdHDH = ?";
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

}
