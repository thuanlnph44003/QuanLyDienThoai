/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.NhanVien;
import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienService {

    public ArrayList<NhanVien> getAllNV() {
        String sql = "SELECT ID_MaNV, TenNV, MatKhau, DiaChi, ChucVu, GioiTinh, NgaySinh, Email, SDT, TrangThai, NgayTao, NgaySua FROM NhanVien";
        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<NhanVien> listNV = new ArrayList<>();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setID_MaNV(rs.getString(1));
                nv.setTenNV(rs.getString(2));
                nv.setMatKhau(rs.getString(3));
                nv.setDiaChi(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                nv.setGioiTinh(rs.getBoolean(6));
                nv.setNgaySinh(rs.getDate(7));
                nv.setEmail(rs.getString(8));
                nv.setSDT(rs.getString(9));
                nv.setTrangThai(rs.getString(10));
                nv.setNgayTao(rs.getDate(11));
                nv.setNgaySua(rs.getDate(12));
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themNhanVien(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (ID_MaNV, TenNV, MatKhau, DiaChi, ChucVu, GioiTinh, NgaySinh, Email, SDT, TrangThai, NgayTao, NgaySua) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), null)";
        int check = 0;

        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            // Thêm giá trị cho các tham số
            ps.setString(1, nv.getID_MaNV());
            ps.setString(2, nv.getTenNV());
            ps.setString(3, nv.getMatKhau());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getChucVu());
            ps.setBoolean(6, nv.isGioiTinh());
            ps.setDate(7, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(8, nv.getEmail());
            ps.setString(9, nv.getSDT());
            ps.setString(10, nv.getTrangThai());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean suaNhanVien(NhanVien nv, String ma) {
        String sql = "UPDATE NhanVien SET TenNV=?, MatKhau=?, DiaChi=?, ChucVu=?, GioiTinh=?, NgaySinh=?, Email=?, SDT=?, TrangThai=?, NgaySua=GETDATE() WHERE ID_MaNV=?";
        int check = 0;

        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getMatKhau());
            ps.setString(3, nv.getDiaChi());
            ps.setString(4, nv.getChucVu());
            ps.setBoolean(5, nv.isGioiTinh());
            ps.setDate(6, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(7, nv.getEmail());
            ps.setString(8, nv.getSDT());
            ps.setString(9, nv.getTrangThai());
            ps.setString(10, ma); // ID_MaNV

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public ArrayList<NhanVien> timKiemTheoTenNV(String tenNV) {
        String sql = "SELECT ID_MaNV, TenNV, MatKhau, DiaChi, ChucVu, GioiTinh, NgaySinh, Email, SDT, TrangThai, NgayTao, NgaySua "
                + "FROM NhanVien "
                + "WHERE lower(TenNV) COLLATE Latin1_General_CI_AI LIKE lower(?) COLLATE Latin1_General_CI_AI";
        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, "%" + tenNV + "%"); // Sử dụng LIKE để tìm kiếm theo tên
            ResultSet rs = ps.executeQuery();
            ArrayList<NhanVien> listNV = new ArrayList<>();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setID_MaNV(rs.getString(1));
                nhanVien.setTenNV(rs.getString(2));
                nhanVien.setMatKhau(rs.getString(3));
                nhanVien.setDiaChi(rs.getString(4));
                nhanVien.setChucVu(rs.getString(5));
                nhanVien.setGioiTinh(rs.getBoolean(6));
                nhanVien.setNgaySinh(rs.getDate(7));
                nhanVien.setEmail(rs.getString(8));
                nhanVien.setSDT(rs.getString(9));
                nhanVien.setTrangThai(rs.getString(10));
                nhanVien.setNgayTao(rs.getDate(11));
                nhanVien.setNgaySua(rs.getDate(12));
                listNV.add(nhanVien);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean xoaNhanVien(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE ID_MaNV = ?";
        int check = 0;

        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }
//----- Tìm kiếm theo ID ------------------------------------------------------------------------------------------------

    public NhanVien selectByID(String id) {
        String sql = "SELECT ID_MaNV, TenNV, MatKhau, DiaChi, ChucVu, GioiTinh, NgaySinh, Email, SDT, TrangThai, NgayTao, NgaySua "
                + "FROM [QuanLyBanDienThoai].[dbo].[NhanVien] WHERE ID_MaNV LIKE ?";
        List<NhanVien> listNV = new ArrayList<>();

        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getString("ID_MaNV"),
                        rs.getString("TenNV"),
                        rs.getString("MatKhau"),
                        rs.getString("DiaChi"),
                        rs.getString("ChucVu"),
                        rs.getBoolean("GioiTinh"),
                        rs.getDate("NgaySinh"),
                        rs.getString("Email"),
                        rs.getString("SDT"),
                        rs.getString("TrangThai"),
                        rs.getDate("NgayTao"),
                        rs.getDate("NgaySua")
                );
                listNV.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNV.isEmpty() ? null : listNV.get(0);
    }
//------- Lấy dữ liệu cho 2 TBL Phụ -------------------------------------------------------------------------------------------

    public ArrayList<NhanVien> getAllNVHD() {
        String sql = "SELECT ID_MaNV, TenNV, MatKhau, DiaChi, ChucVu, GioiTinh, NgaySinh, Email, SDT, TrangThai, NgayTao, NgaySua FROM NhanVien WHERE TrangThai = N'Hoạt động'";

        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            ArrayList<NhanVien> listNV = new ArrayList<>();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setID_MaNV(rs.getString("ID_MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setChucVu(rs.getString("ChucVu"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setSDT(rs.getString("SDT"));
                nv.setTrangThai(rs.getString("TrangThai"));
                nv.setNgayTao(rs.getDate("NgayTao"));
                nv.setNgaySua(rs.getDate("NgaySua"));
                listNV.add(nv);
            }

            return listNV;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<NhanVien> getAllNVNV() {
        String sql = "SELECT ID_MaNV, TenNV, MatKhau, DiaChi, ChucVu, GioiTinh, NgaySinh, Email, SDT, TrangThai, NgayTao, NgaySua FROM NhanVien WHERE TrangThai = N'Nghỉ việc'";

        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            ArrayList<NhanVien> listNV = new ArrayList<>();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setID_MaNV(rs.getString("ID_MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setChucVu(rs.getString("ChucVu"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setSDT(rs.getString("SDT"));
                nv.setTrangThai(rs.getString("TrangThai"));
                nv.setNgayTao(rs.getDate("NgayTao"));
                nv.setNgaySua(rs.getDate("NgaySua"));
                listNV.add(nv);
            }

            return listNV;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
//------ Lấy giới tính ---------------------------------------------------------------------------------------------------

    public ArrayList<String> getGioiTinh() {
        String sql = "SELECT DISTINCT GioiTinh FROM NhanVien";
        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<String> listNganh = new ArrayList<>();
            while (rs.next()) {
                String gioiTinh = rs.getInt(1) == 1 ? "Nam" : "Nữ";
                listNganh.add(gioiTinh);
            }
            return listNganh;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//---- Đổi mật khẩu---------------------------------------------------------------------------------------------------    

    public void updateMK(NhanVien nv) {
        String sql = "UPDATE NhanVien SET MatKhau = ? WHERE ID_MaNV = ?";

        try ( java.sql.Connection conn = DBConnect.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nv.getMatKhau());
            pstmt.setString(2, nv.getID_MaNV());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMKMail(NhanVien nv) {
        String sql = "UPDATE NhanVien SET MatKhau = ? WHERE Email = ?";

        try ( java.sql.Connection conn = DBConnect.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nv.getMatKhau());
            pstmt.setString(2, nv.getEmail());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//---- Kiểm tra email---------------------------------------------------------------------------------------------------

    public NhanVien selectByEmail(String id) {
        String sql = "SELECT ID_MaNV, TenNV, MatKhau, DiaChi, ChucVu, GioiTinh, NgaySinh, Email, SDT, TrangThai, NgayTao, NgaySua "
                + "FROM [QuanLyBanDienThoai].[dbo].[NhanVien] WHERE Email LIKE ?";
        List<NhanVien> listNV = new ArrayList<>();

        try ( java.sql.Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getString("ID_MaNV"),
                        rs.getString("TenNV"),
                        rs.getString("MatKhau"),
                        rs.getString("DiaChi"),
                        rs.getString("ChucVu"),
                        rs.getBoolean("GioiTinh"),
                        rs.getDate("NgaySinh"),
                        rs.getString("Email"),
                        rs.getString("SDT"),
                        rs.getString("TrangThai"),
                        rs.getDate("NgayTao"),
                        rs.getDate("NgaySua")
                );
                listNV.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNV.isEmpty() ? null : listNV.get(0);
    }

//    public String selectEmail(String email) {
//        try {
//            String sql = "SELECT Email FROM nhanvien WHERE Email = ?";
//            try ( PreparedStatement preparedStatement = DBConnect.getConnection().prepareStatement(sql)) {
//                preparedStatement.setString(1, email);
//                ResultSet rs = preparedStatement.executeQuery();
//                if (rs.next()) {
//                    return rs.getString("Email");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
