/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.NhanVien;

/**
 *
 * @author ADMIN
 */
public class Login {
    public static NhanVien user = null;
    
   public static void dangXuat(){
        Login.user = null;
    }
   public static boolean daDangNhap(){
      return Login.user != null;
   }
   public static boolean trangThaiTaiKhoan(){
       return "Hoạt động".equals(Login.user.getTrangThai());
   }
    
    public static boolean phanQuyen(){
        return Login.daDangNhap() && Login.user.getChucVu().equals("Quản lý");
    }
}
