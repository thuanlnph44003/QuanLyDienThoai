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
import model.giamgiamolde;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import model.HoaDon;

/**
 *
 * @author ACER
 */
public class svgiamgia {

    public List<giamgiamolde> fiall() {
        List<giamgiamolde> lst = new ArrayList<>();

        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String query = "SELECT ID_MaGiamGia, TenMaGiamGia, Giam, NgayBatDau, NgaKetThuc "
                    + "FROM GiamGia";

            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String ID_MaGiamGia = rs.getString("ID_MaGiamGia");
                String TenMaGiamGia = rs.getString("TenMaGiamGia");
                int Giam = rs.getInt("Giam");
                Date NgayBatDau = rs.getDate("NgayBatDau");
                Date NgaKetThuc = rs.getDate("NgaKetThuc");
                lst.add(new giamgiamolde(ID_MaGiamGia, TenMaGiamGia, Giam, NgayBatDau, NgaKetThuc));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public boolean themSanPham(giamgiamolde product) {
        try {
            Connection conn = DBConnect.getConnection();

            // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
            String checkQuery = "SELECT ID_MaGiamGia FROM GiamGIa WHERE ID_MaGiamGia = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, product.getID_MaGiamGia());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Mã đã tồn tại, không thể thêm
                JOptionPane.showMessageDialog(null, "Mã gg đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
                String sql = "INSERT INTO GiamGia (ID_MaGiamGia, TenMaGiamGia, Giam, NgayBatDau, NgaKetThuc) "
                        + "VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, product.getID_MaGiamGia());
                pstmt.setString(2, product.getTenMaGiamGia());
                pstmt.setInt(3, product.getGiam());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String ngayBD = sdf.format(product.getNgayBatDau());
                pstmt.setString(4, ngayBD);
                String ngaykt = sdf.format(product.getNgaKetThuc());
                pstmt.setString(5, ngaykt);

                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            // Xử lý lỗi khi thao tác với cơ sở dữ liệu
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm ma gg " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean xoaSanPham(String maGiamGia) {
        try {
            Connection conn = DBConnect.getConnection();

            // Xóa sản phẩm có mã giảm giá chỉ định
            String deleteQuery = "DELETE FROM GiamGia WHERE ID_MaGiamGia = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setString(1, maGiamGia);

            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected > 0) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa mã gg: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

   public boolean capNhatSanPham(giamgiamolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE GiamGia SET TenMaGiamGia = ?, Giam = ?, NgayBatDau = ?, NgaKetThuc = ? WHERE ID_MaGiamGia = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);

        updateStmt.setString(1, product.getTenMaGiamGia());
        updateStmt.setInt(2, product.getGiam());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayBD = sdf.format(product.getNgayBatDau());
        updateStmt.setString(3, ngayBD);
        String ngaykt = sdf.format(product.getNgaKetThuc());
        updateStmt.setString(4, ngaykt);
        updateStmt.setString(5, product.getID_MaGiamGia());

        int rowsAffected = updateStmt.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin gg thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy mã gg để cập nhật.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật thông tin gg: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

}
