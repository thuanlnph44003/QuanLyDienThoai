/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.ThongKemodel;
import database.DBConnect;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.JTextField;

/**
 *
 * @author Bui Tuan Hung
 */
public class ThongKeService {

    public List<ThongKemodel> findAll() {
        List<ThongKemodel> lst = new ArrayList<>();

        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM HoaDonChiTiet ";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThongKemodel tk = new ThongKemodel();

                tk.setID_MaHoaDon(rs.getInt("ID_MaHoaDon"));
                tk.setID_MaHoaDonChiTiet(rs.getInt("ID_MaHoaDonChiTiet"));
                tk.setTenSanPham(rs.getString("TenSanPham"));
                tk.setSoLuong(rs.getInt("SoLuong"));
                tk.setGiaBan(rs.getDouble("GiaBan"));

                lst.add(tk);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }
public List<ThongKemodel> findAllByMonth(int month) {
    List<ThongKemodel> lst = new ArrayList<>();
    try {
        Connection conn = DBConnect.getConnection();
        // Sử dụng PreparedStatement để tránh SQL injection và dễ dàng thay đổi tham số
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MONTH(NgayTao) = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, month);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ThongKemodel tk = new ThongKemodel();
            tk.setID_MaHoaDon(rs.getInt("ID_MaHoaDon"));
            tk.setID_MaHoaDonChiTiet(rs.getInt("ID_MaHoaDonChiTiet"));
            tk.setTenSanPham(rs.getString("TenSanPham"));
            tk.setSoLuong(rs.getInt("SoLuong"));
            tk.setGiaBan(rs.getDouble("GiaBan"));
            lst.add(tk);
        }

        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return lst;
}


    public List<ThongKemodel> findTong() {
        List<ThongKemodel> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT SUM(ThanhTien) AS TongDoanhThu FROM HoaDon";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThongKemodel tk = new ThongKemodel();
                tk.setTongDoanhThu(rs.getFloat("TongDoanhThu"));
                lst.add(tk);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public List<ThongKemodel> findDHDT() {
        List<ThongKemodel> lst = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT COUNT(*) AS TongDonHang FROM HoaDon";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThongKemodel tk = new ThongKemodel();
                tk.setTongDonHang(rs.getLong("TongDOnHang"));
                lst.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public void getInvoiceInformation(int selectedMonth, JTextField textFieldTotalAmount, JTextField textFieldTotalInvoices) {
        Connection conn = DBConnect.getConnection();
        if (conn != null) {
            try {

                String countQuery = "SELECT COUNT(ID_MaHoadon) AS SoLuongHoaDon FROM HoaDon WHERE MONTH(NgayTao) = ?";
                try (PreparedStatement countStatement = conn.prepareStatement(countQuery)) {
                    countStatement.setInt(1, selectedMonth);

                    ResultSet countResult = countStatement.executeQuery();
                    if (countResult.next()) {
                        int soLuongHoaDon = countResult.getInt("SoLuongHoaDon");
                        textFieldTotalInvoices.setText(String.valueOf(soLuongHoaDon));
                    }
                }

                String sumQuery = "SELECT SUM(ThanhTien) AS TongDoanhThu FROM HoaDon WHERE MONTH(NgayTao) = ?";
                try (PreparedStatement sumStatement = conn.prepareStatement(sumQuery)) {
                    sumStatement.setInt(1, selectedMonth);

                    ResultSet sumResult = sumStatement.executeQuery();
                    if (sumResult.next()) {
                        double tongDoanhThu = sumResult.getDouble("TongDoanhThu");
                        textFieldTotalAmount.setText(String.valueOf(tongDoanhThu));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getInvoiceInformationNam(int selectedYear, JTextField textFieldTotalAmount, JTextField textFieldTotalInvoices) {
        Connection conn = DBConnect.getConnection();
        if (conn != null) {
            try {

                String countQuery = "SELECT COUNT(ID_MaHoadon) AS SoLuongHoaDon FROM HoaDon WHERE YEAR(NgayTao) = ?";
                try (PreparedStatement countStatement = conn.prepareStatement(countQuery)) {
                    countStatement.setInt(1, selectedYear);

                    ResultSet countResult = countStatement.executeQuery();
                    if (countResult.next()) {
                        int soLuongHoaDon = countResult.getInt("SoLuongHoaDon");
                        textFieldTotalInvoices.setText(String.valueOf(soLuongHoaDon));
                    }
                }

                String sumQuery = "SELECT SUM(ThanhTien) AS TongDoanhThu FROM HoaDon WHERE YEAR(NgayTao) = ?";
                try (PreparedStatement sumStatement = conn.prepareStatement(sumQuery)) {
                    sumStatement.setInt(1, selectedYear);

                    ResultSet sumResult = sumStatement.executeQuery();
                    if (sumResult.next()) {
                        double tongDoanhThu = sumResult.getDouble("TongDoanhThu");
                        textFieldTotalAmount.setText(String.valueOf(tongDoanhThu));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public List<ThongKemodel> findDoanhThuTheoThang(int selectedMonth) {
    List<ThongKemodel> lst = new ArrayList<>();
    try {
        Connection conn = DBConnect.getConnection();
        PreparedStatement stm = conn.prepareStatement(
            "SELECT ID_MaHoaDon, ID_MaHoaDonChiTiet, TenSanPham, SoLuong, GiaBan " +
            "FROM HoaDonChiTiet " +
            "WHERE MONTH(NgayTao) = ?");
        stm.setInt(1, selectedMonth);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ThongKemodel tk = new ThongKemodel();

            tk.setID_MaHoaDon(rs.getInt("ID_MaHoaDon"));
            tk.setID_MaHoaDonChiTiet(rs.getInt("ID_MaHoaDonChiTiet"));
            tk.setTenSanPham(rs.getString("TenSanPham"));
            tk.setSoLuong(rs.getInt("SoLuong"));
            tk.setGiaBan(rs.getDouble("GiaBan"));

            lst.add(tk);
        }
        rs.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lst;
}
}
