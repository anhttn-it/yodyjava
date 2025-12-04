/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proccess;

import Database.Connect;
import java.sql.*;
/**
 *
 * @author ngocanh
 */
public class DangNhap {
    private int taikhoan;
    private String matkhau;
    private int quyen;
    private final Connect cn = new Connect();

    public DangNhap() {
    }

    public DangNhap(int taikhoan, String matkhau, int quyen) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.quyen = quyen;
    }

    public int getTaikhoan() {
        return taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setTaikhoan(int taikhoan) {
        this.taikhoan = taikhoan;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }
    public int getVaiTro(int maNV, String matKhau) {
        String sql = "SELECT VaiTro FROM NHAN_VIEN WHERE MaNhanVien = ? AND MatKhau = ? AND TrangThai = 1";
        
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, maNV);
            ps.setString(2, matKhau);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("VaiTro");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1; 
    }
}

    

