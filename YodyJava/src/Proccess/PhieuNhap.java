/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proccess;

import java.sql.*;
import Database.Connect;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ngocanh
 */
public class PhieuNhap {
    private int maPhieuNhap;
    private Date ngayNhap;
    private int maNCC;
    private int maNV;
    private double tongTien;
    private String ghiChu;
    private final Connect cn = new Connect();
    public PhieuNhap() {
    }
    public PhieuNhap(int maPhieuNhap, Date ngayNhap, int maNCC, int maNV, double tongTien, String ghiChu) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }
    public Date getNgayNhap() {
        return ngayNhap;
    }
    public int getMaNCC() {
        return maNCC;
    }
    public int getMaNV() {
        return maNV;
    }
    public double getTongTien() {
        return tongTien;
    }
    public String getGhiChu() {
        return ghiChu;
    }
    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }
    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    public List<PhieuNhap> getAll() throws SQLException {
        List<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM PHIEU_NHAP";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.maPhieuNhap = rs.getInt("MaPhieuNhap");
                pn.ngayNhap = rs.getDate("NgayNhap");
                pn.maNCC = rs.getInt("MaNCC");
                pn.maNV = rs.getInt("MaNV");
                pn.tongTien = rs.getDouble("TongTien");
                pn.ghiChu = rs.getString("GhiChu");
                list.add(pn);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Nhập: " + e.getMessage());
        }
        return list;
    }
    
    
    
    
}
