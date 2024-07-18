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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.HoaDonChiTiet;
import model.sanphamchitietmolde;

/**
 *
 * @author ACER
 */
public class svhoadonchitiet {

    public List<HoaDonChiTiet> fiall() {
        List<HoaDonChiTiet> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String query = "SELECT ID_MaHoaDonChiTiet, ID_MaHoadon, MaChiTietSP, TenSanPham, GiaBan, SoLuong,ID_IMEI "
                    + "FROM dbo.HoaDonChiTiet";

            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int mahoadonchitiet = rs.getInt("ID_MaHoaDonChiTiet");
                int hoadon = rs.getInt("ID_MaHoadon");
                String machitietsanpham = rs.getString("MaChiTietSP");
                String tensp = rs.getString("TenSanPham");
                double giaban = rs.getDouble("GiaBan");
                int soluong = rs.getInt("SoLuong");
                String imei = rs.getString("ID_IMEI");
                lst.add(new HoaDonChiTiet(hoadon, mahoadonchitiet, machitietsanpham, tensp, imei, soluong, giaban));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public boolean insertDataToDatabase(String mahoadon, String maSPCT, String tenSP, int soLuong, double GiaBan, String imei) {
    // Check if any of the input strings are empty
    if (mahoadon.isEmpty()) {
        // Handle the error, for example:
        return false;
    }

    try {
        Connection conn = DBConnect.getConnection();

        String insertQuery = "INSERT INTO HoaDonChiTiet (ID_MaHoadon, MaChiTietSP, TenSanPham, SoLuong, GiaBan, ID_IMEI) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertQuery);
        pstmt.setString(1, mahoadon);
        pstmt.setString(2, maSPCT);
        pstmt.setString(3, tenSP);
        pstmt.setInt(4, soLuong);
        pstmt.setDouble(5, GiaBan);
        pstmt.setString(6, imei);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean xoahdct(int ID_MaHoaDonChiTiet) {
        try {
            Connection conn = DBConnect.getConnection();

            String deleteQuery = "DELETE FROM HoaDonChiTiet WHERE ID_MaHoaDonChiTiet = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, ID_MaHoaDonChiTiet);
            deleteStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            // Xử lý lỗi khi thao tác với cơ sở dữ liệu
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa IMEI: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<HoaDonChiTiet> searchByInvoiceID(String maHoaDon) {
        List<HoaDonChiTiet> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = DBConnect.getConnection();
            String query = "SELECT MaChiTietSP, SoLuong, TenSanPham, GiaBan, ID_IMEI "
                    + "FROM HoaDonChiTiet "
                    + "WHERE ID_MaHoadon = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, maHoaDon);
            rs = statement.executeQuery();

            while (rs.next()) {
                String maChiTietSP = rs.getString("MaChiTietSP");
                int soLuong = rs.getInt("SoLuong");
                String tenSanPham = rs.getString("TenSanPham");
                double giaBan = rs.getDouble("GiaBan");
                String idImei = rs.getString("ID_IMEI");

                HoaDonChiTiet hdct = new HoaDonChiTiet(soLuong, soLuong, maChiTietSP, tenSanPham, idImei, soLuong, giaBan);
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

    public List<Object[]> getDataByNgayTao(Date ngayTao) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Object[]> resultList = new ArrayList<>();

        try {
            connection = DBConnect.getConnection();
            String query = "SELECT HoaDon.NgayTao, HoaDonChiTiet.MaChiTietSP, HoaDonChiTiet.SoLuong, HoaDonChiTiet.TenSanPham, HoaDonChiTiet.GiaBan, HoaDonChiTiet.ID_IMEI "
                    + "FROM HoaDon INNER JOIN HoaDonChiTiet ON HoaDon.ID_MaHoadon = HoaDonChiTiet.ID_MaHoadon "
                    + "WHERE HoaDon.NgayTao = ?";
            statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(ngayTao.getTime()));

            rs = statement.executeQuery();
            while (rs.next()) {

                String maChiTietSP = rs.getString("MaChiTietSP");
                int soLuong = rs.getInt("SoLuong");
                String tenSanPham = rs.getString("TenSanPham");
                double giaBan = rs.getDouble("GiaBan");
                String ID_IMEI = rs.getString("ID_IMEI");
                // Đưa dữ liệu vào một mảng Object để sử dụng trong bảng
                Object[] rowData = {maChiTietSP, tenSanPham, giaBan, soLuong, ID_IMEI};
                resultList.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và các tài nguyên liên quan
            // ...
        }

        return resultList;
    }

}
