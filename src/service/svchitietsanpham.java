/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Imeimolde;
import model.sanphamchitietmolde;
import model.sanphamodle;

/**
 *
 * @author ACER
 */
public class svchitietsanpham {

    public List<sanphamchitietmolde> selectall() {
        List<sanphamchitietmolde> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
  String sql = "SELECT SP.MaSanPham, SP.DongSP, CTSP.MaChiTietSP, CTSP.TenSanPham, "
        + "CTSP.Pin, CTSP.ManHinh, CTSP.CamMera AS Camera, CTSP.KhoiLuong, CTSP.Chip, "
        + "CTSP.HinhAnh, CTSP.MauSac, CTSP.BoNho, CTSP.GiaNhap, CTSP.GiaBan, MAX(ISP.ID_IMEI) AS ID_IMEI, "
        + "COALESCE(COUNT(ISP.ID_IMEI), 0) AS SoLuongIMEI "
        + "FROM dbo.SanPham SP "
        + "INNER JOIN dbo.ChiTietSanPham CTSP ON SP.MaSanPham = CTSP.MaSanPham "
        + "LEFT JOIN dbo.ImeiSanPham ISP ON CTSP.MaChiTietSP = ISP.MaChiTietSP "
        + "GROUP BY SP.MaSanPham, SP.DongSP, CTSP.MaChiTietSP, CTSP.TenSanPham, "
        + "CTSP.Pin, CTSP.ManHinh, CTSP.CamMera, CTSP.KhoiLuong, CTSP.Chip, "
        + "CTSP.HinhAnh, CTSP.MauSac, CTSP.BoNho, CTSP.GiaNhap, CTSP.GiaBan";


            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String masanpham = rs.getString("MaSanPham");
                String DongSp = rs.getString("DongSP");
                String MaChiTietSP = rs.getString("MaChiTietSP");
                String TenSanPham = rs.getString("TenSanPham");
                String Pin = rs.getString("Pin");
                String ManHinh = rs.getString("ManHinh");
                String Camera = rs.getString("Camera");
                String KhoiLuong = rs.getString("KhoiLuong");
                String Chip = rs.getString("Chip");
                String HinhAnh = rs.getString("HinhAnh");
                String MauSac = rs.getString("MauSac");
                String BoNho = rs.getString("BoNho");
                double GiaNhap = rs.getDouble("GiaNhap");
                double GiaBan = rs.getDouble("GiaBan");
                int Imei = rs.getInt("ID_IMEI");
                int soLuongIMEI = rs.getInt("SoLuongIMEI") + 0;
    lst.add(new sanphamchitietmolde(masanpham, DongSp, MaChiTietSP, TenSanPham, Pin, ManHinh, Camera, KhoiLuong, Chip, HinhAnh, MauSac, BoNho, GiaNhap, GiaBan, Imei, soLuongIMEI));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

  public boolean themSanPham(sanphamchitietmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        // Kiểm tra trùng mã trước khi thêm vào cơ sở dữ liệu
        String checkQuery = "SELECT MaChiTietSP FROM ChiTietSanPham WHERE MaChiTietSP = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, product.getMaChiTietSanPham());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Mã đã tồn tại, không thể thêm
            JOptionPane.showMessageDialog(null, "Mã chi tiết sản phẩm đã tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // Mã không tồn tại, tiến hành thêm vào cơ sở dữ liệu
            String insertQuery = "INSERT INTO ChiTietSanPham(MaChiTietSP, MaSanPham, TenSanPham, Pin, ManHinh, CamMera, KhoiLuong, Chip, HinhAnh, MauSac, BoNho, GiaNhap, GiaBan) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, product.getMaChiTietSanPham());
            pstmt.setString(2, product.getMaSanPham());
            pstmt.setString(3, product.getTenSanPham());
            pstmt.setString(4, product.getPin());
            pstmt.setString(5, product.getManHinh());
            pstmt.setString(6, product.getCammera());
            pstmt.setString(7, product.getKhoiLuong());
            pstmt.setString(8, product.getChip());
            pstmt.setString(9, product.getHinhAnh());
            pstmt.setString(10, product.getMauSac());
            pstmt.setString(11, product.getBoNho());
            pstmt.setDouble(12, product.getGiaNhap());
            pstmt.setDouble(13, product.getGiaBan());
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
public boolean suaSanPham(sanphamchitietmolde product) {
    try {
        Connection conn = DBConnect.getConnection();

        String updateQuery = "UPDATE ChiTietSanPham SET MaSanPham=?, TenSanPham=?, Pin=?, ManHinh=?, CamMera=?, KhoiLuong=?, Chip=?, HinhAnh=?, MauSac=?, BoNho=?, GiaNhap=?, GiaBan=? WHERE MaChiTietSP=?";
        PreparedStatement pstmt = conn.prepareStatement(updateQuery);
        pstmt.setString(1, product.getMaSanPham());
        pstmt.setString(2, product.getTenSanPham());
        pstmt.setString(3, product.getPin());
        pstmt.setString(4, product.getManHinh());
        pstmt.setString(5, product.getCammera());
        pstmt.setString(6, product.getKhoiLuong());
        pstmt.setString(7, product.getChip());
        pstmt.setString(8, product.getHinhAnh());
        pstmt.setString(9, product.getMauSac());
        pstmt.setString(10, product.getBoNho());
        pstmt.setDouble(11, product.getGiaNhap());
        pstmt.setDouble(12, product.getGiaBan());
        pstmt.setString(13, product.getMaChiTietSanPham());

        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi sửa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
public boolean xoaSanPham(String maChiTietSP) {
    try {
        Connection conn = DBConnect.getConnection();

        // Bước 1: Xóa dữ liệu từ bảng ImeiSanPham
        String deleteImeiQuery = "DELETE FROM ImeiSanPham WHERE MaChiTietSP=?";
        PreparedStatement deleteImeiStmt = conn.prepareStatement(deleteImeiQuery);
        deleteImeiStmt.setString(1, maChiTietSP);
        int rowsDeletedImei = deleteImeiStmt.executeUpdate();

        // Bước 2: Xóa dữ liệu từ bảng ChiTietSanPham
        String deleteChiTietQuery = "DELETE FROM ChiTietSanPham WHERE MaChiTietSP=?";
        PreparedStatement deleteChiTietStmt = conn.prepareStatement(deleteChiTietQuery);
        deleteChiTietStmt.setString(1, maChiTietSP);
        int rowsDeletedChiTiet = deleteChiTietStmt.executeUpdate();

        // Kiểm tra xem đã xóa dữ liệu thành công từ cả hai bảng hay không
        boolean deleteSuccess = rowsDeletedImei > 0 && rowsDeletedChiTiet > 0;

        if (deleteSuccess) {
            // Nếu xóa thành công từ cả hai bảng, trả về true
            return true;
        } else {
            // Nếu xóa không thành công từ ít nhất một trong hai bảng, rollback và trả về false
            conn.rollback(); // Quay lại trạng thái trước khi thực hiện xóa
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xóa dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}


}


