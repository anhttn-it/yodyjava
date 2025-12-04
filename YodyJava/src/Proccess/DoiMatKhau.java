/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proccess;
import Database.Connect;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class DoiMatKhau {
    private int passmoi;
    private String passcu;
    private final Connect cn = new Connect();
     public DoiMatKhau() {
    }
     public boolean thucHienDoiMatKhau(int maNV, String mkCu, String mkMoi) {
        String checkSql = "SELECT COUNT(*) FROM NHAN_VIEN WHERE MaNhanVien = ? AND MatKhau = ? AND TrangThai = 1";
        String updateSql = "UPDATE NHAN_VIEN SET MatKhau = ? WHERE MaNhanVien = ?";
        
        try (Connection con = cn.connectSQL()) {
            int count = 0;
            try (PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                checkPs.setInt(1, maNV);
                checkPs.setString(2, mkCu); 
                try (ResultSet rs = checkPs.executeQuery()) {
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }
                }
            }

            if (count == 0) {
                return false; 
            }
            try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
                updatePs.setString(1, mkMoi); 
                updatePs.setInt(2, maNV);     
                
                int rowsAffected = updatePs.executeUpdate();
                return rowsAffected > 0;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi đổi mật khẩu cho NV " + maNV + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
   