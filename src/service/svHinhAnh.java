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
import model.Hinhanh;
import model.xuatxu;

/**
 *
 * @author ACER
 */
public class svHinhAnh {
    public List<Hinhanh>selectall(){
        List<Hinhanh>lst=new ArrayList<>();
        try {
            Connection conn = DBConnect.getConnection();
            Statement stm = conn.createStatement();
            String query = "SELECT HinhAnh FROM HinhAnh"; // Sửa lỗi trong câu lệnh SQL

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                String ha = rs.getString("HinhAnh");

                // Tạo đối tượng xuatxu và thêm vào danh sách
                Hinhanh hinhanh= new Hinhanh(ha); // Chỉnh sửa phương thức khởi tạo tương ứng trong lớp xuatxu của bạn
                lst.add(hinhanh);
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lst;
    }
       
    }

