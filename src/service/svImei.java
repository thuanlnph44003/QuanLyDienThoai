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
import model.Imeimolde;
import model.xuatxu;
import java.sql.*;
import javax.swing.JOptionPane;
import model.sanphamchitietmolde;
import model.sanphamchitietmolde2;

/**
 *
 * @author ACER
 */
public class svImei {

    public List<Imeimolde> fiall() {
        List<Imeimolde> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            String query = "SELECT ID_IMEI, MaChiTietSP FROM ImeiSanPham";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int ID_IMEI = rs.getInt("ID_IMEI");
                String MaChiTietSP = rs.getString("MaChiTietSP");
                Imeimolde imei = new Imeimolde(ID_IMEI, MaChiTietSP);
                lst.add(imei);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lst;
    }

   public boolean themSanPham(Imeimolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã sản phẩm trước khi thêm vào cơ sở dữ liệu
        String checkMaSPQuery = "SELECT MaChiTietSP FROM ChiTietSanPham WHERE MaChiTietSP = ?";
        PreparedStatement checkMaSPStmt = conn.prepareStatement(checkMaSPQuery);
        checkMaSPStmt.setString(1, product.getMaChiTietSP());
        ResultSet maSPResultSet = checkMaSPStmt.executeQuery();

        if (!maSPResultSet.next()) {
            // Mã Chi tiết Sản phẩm chưa tồn tại, thông báo lỗi và không thực hiện thêm mới
            JOptionPane.showMessageDialog(null, "Mã Chi tiết Sản phẩm chưa tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã Chi tiết Sản phẩm tồn tại, tiếp tục kiểm tra trùng ID_IMEI
            String checkQuery = "SELECT ID_IMEI FROM ImeiSanPham WHERE ID_IMEI = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, product.getIdImei());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // ID_IMEI đã tồn tại, không thể thêm
                JOptionPane.showMessageDialog(null, "ID_IMEI sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                // ID_IMEI không tồn tại, thực hiện thêm vào cơ sở dữ liệu
                String insertQuery = "INSERT INTO ImeiSanPham (ID_IMEI, MaChiTietSP) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                pstmt.setInt(1, product.getIdImei());
                pstmt.setString(2, product.getMaChiTietSP());
                pstmt.executeUpdate();
                return true;
            }
        }
    } catch (SQLException e) {
        // Xử lý lỗi khi thao tác với cơ sở dữ liệu
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

public boolean themimeitutable(int maIMEI, String maSPCT) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT ID_IMEI,MaChiTietSP FROM ImeiSanPham WHERE ID_IMEI = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, maIMEI);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã Imei sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO ImeiSanPham (ID_IMEI, MaChiTietSP) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, maIMEI);
            pstmt.setString(2, maSPCT);
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

    public boolean suaSanPham(Imeimolde product) {
        try {
            Connection conn = DBConnect.getConnection();

            // Sửa thông tin sản phẩm dựa trên ID_IMEI
            String updateQuery = "UPDATE ImeiSanPham SET MaChiTietSP = ? WHERE ID_IMEI = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setString(1, product.getMaChiTietSP());
            updateStmt.setInt(2, product.getIdImei());
            updateStmt.executeUpdate();

            // Đóng kết nối và trả về true nếu sửa thành công
            conn.close();
            return true;
        } catch (SQLException e) {
            // Xử lý lỗi khi thao tác với cơ sở dữ liệu
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi sửa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean xoaSanPham(int idImei) {
        try {
            Connection conn = DBConnect.getConnection();

            // Xóa sản phẩm dựa trên ID_IMEI
            String deleteQuery = "DELETE FROM ImeiSanPham WHERE ID_IMEI = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, idImei);
            deleteStmt.executeUpdate();

            // Đóng kết nối và trả về true nếu xóa thành công
            conn.close();
            return true;
        } catch (SQLException e) {
            // Xử lý lỗi khi thao tác với cơ sở dữ liệu
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<String> getIMEIsByProduct(String maChiTietSP) {
        List<String> imeiList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getConnection();
            String query = "SELECT ID_IMEI FROM ImeiSanPham WHERE MaChiTietSP = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, maChiTietSP);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String ID_IMEI = rs.getString("ID_IMEI");
                imeiList.add(ID_IMEI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tất cả các kết nối và tài nguyên
        }

        return imeiList;
    }

    public sanphamchitietmolde2 getProductByIMEI(String imei) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        sanphamchitietmolde2 product = null;

        try {
            connection = DBConnect.getConnection();

            String query = "SELECT CSP.MaChiTietSP, CSP.MaSanPham, CSP.TenSanPham, CSP.Pin, CSP.ManHinh, CSP.CamMera, "
                    + "CSP.KhoiLuong, CSP.Chip, CSP.MauSac, CSP.HinhAnh, CSP.BoNho, CSP.GiaNhap, CSP.GiaBan, ISP.ID_IMEI "
                    + "FROM ChiTietSanPham CSP "
                    + "INNER JOIN ImeiSanPham ISP ON CSP.MaChiTietSP = ISP.MaChiTietSP "
                    + "WHERE ISP.ID_IMEI = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, imei);
            rs = statement.executeQuery();

            if (rs.next()) {
                String MaChiTietSP = rs.getString("MaChiTietSP");
                String masanpham = rs.getString("MaSanPham");
                String TenSanPham = rs.getString("TenSanPham");
                String Pin = rs.getString("Pin");
                String ManHinh = rs.getString("ManHinh");
                String Camera = rs.getString("CamMera");
                String KhoiLuong = rs.getString("KhoiLuong");
                String Chip = rs.getString("Chip");
                String HinhAnh = rs.getString("HinhAnh");
                String MauSac = rs.getString("MauSac");
                String BoNho = rs.getString("BoNho");
                String   ID_IMEI = rs.getString("ID_IMEI");
                double GiaNhap = rs.getDouble("GiaNhap");
                double GiaBan = rs.getDouble("GiaBan");

                product = new sanphamchitietmolde2(MaChiTietSP, masanpham, TenSanPham, Pin, ManHinh, Camera, KhoiLuong, Chip, HinhAnh, MauSac, BoNho, GiaNhap, GiaBan, ID_IMEI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        return product;
    }

    public boolean deleteIMEIFromDatabase(String selectedIMEI) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean deleted = false;

        try {
            conn = DBConnect.getConnection();
            String query = "DELETE FROM ImeiSanPham WHERE ID_IMEI = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, selectedIMEI);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tất cả các kết nối và tài nguyên
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deleted;
    }
}
