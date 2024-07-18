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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import model.HoaDon;

/**
 *
 * @author ACER
 */
public class svhoadon {

    public List<HoaDon> fillall() {
        List<HoaDon> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT ID_MaHoadon, ID_MaKH, ID_MaNV, ThanhTien, TrangThai, NgayTao "
                    + "FROM dbo.HoaDon";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int mahoadon = rs.getInt("ID_MaHoadon");
                String makh = rs.getString("ID_MaKH");
                String manv = rs.getString("ID_MaNV");
                double thanhtien = rs.getDouble("ThanhTien");
                int trangthai = rs.getInt("TrangThai");
                Date ngaytao = rs.getDate("NgayTao");
                lst.add(new HoaDon(mahoadon, makh, manv, thanhtien, trangthai, ngaytao));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;

    }

    public boolean themSanPham(HoaDon product) {
        try {
            Connection conn = DBConnect.getConnection();

            // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
            String checkQuery = "SELECT ID_MaHoadon FROM HoaDon WHERE ID_MaHoadon = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, product.getMahoadon());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Mã đã tồn tại, không thể thêm
                JOptionPane.showMessageDialog(null, "Mã hoa don đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
                String sql = "INSERT INTO HoaDon (ID_MaHoadon, ID_MaKH, ID_MaNV, ThanhTien, TrangThai, NgayTao) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, product.getMahoadon());
                pstmt.setString(2, product.getMakhachhang());
                pstmt.setString(3, product.getManv());
                pstmt.setDouble(4, product.getThanhtien());
                pstmt.setInt(5, 0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(product.getNgaytao());
                pstmt.setString(6, date);
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
    public boolean updateInvoicePriceAndStatus(int maHoaDon, double giaLay) {
    boolean success = false;

    try {
        Connection conn = DBConnect.getConnection();
        String query = "UPDATE HoaDon SET ThanhTien = ?, TrangThai = 1 WHERE ID_MaHoadon = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setDouble(1, giaLay);
        statement.setInt(2, maHoaDon);

        int rowsUpdated = statement.executeUpdate();
        success = rowsUpdated > 0;

        statement.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return success;
}
  public List<HoaDon> checktrunghoadon(String maHoaDon) {
        List<HoaDon> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = DBConnect.getConnection();
            String query = "SELECT TrangThai "
                    + "FROM HoaDon "
                    + "WHERE ID_MaHoadon = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, maHoaDon);
            rs = statement.executeQuery();

            while (rs.next()) {
    
                int trangthai = rs.getInt("TrangThai");
                HoaDon hdct = new HoaDon(trangthai, maHoaDon, null, null, trangthai, null);
                resultList.add(hdct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi khi truy vấn cơ sở dữ liệu
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }
}
