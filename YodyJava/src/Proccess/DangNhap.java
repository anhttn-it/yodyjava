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
    public boolean gettkmk(int tk, String mk) throws SQLException {
        String sql = "SELECT * FROM nhan_vien WHERE manhanvien = ? AND matkhau = ?";
        try (Connection con = cn.connectSQL();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tk);
            ps.setString(2, mk);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
}
