/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proccess;
import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class SanPham {
    private int maSanPham;
    private String tenSanPham;
    private double donGia;
    private int maNCC;
    private int trangThaiSanPham; // 0 = ngưng bán, 1 = bán
    private double giamGia;

    private final Connect cn = new Connect();

    public SanPham() {}

    public SanPham(int maSanPham, String tenSanPham, double donGia, int maNCC, int trangThaiSanPham, double giamGia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.maNCC = maNCC;
        this.trangThaiSanPham = trangThaiSanPham;
        this.giamGia = giamGia;
    }

    // ===== GETTER & SETTER =====
    public int getMaSanPham() { return maSanPham; }
    public void setMaSanPham(int maSanPham) { this.maSanPham = maSanPham; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public int getMaNCC() { return maNCC; }
    public void setMaNCC(int maNCC) { this.maNCC = maNCC; }

    public int getTrangThaiSanPham() { return trangThaiSanPham; }
    public void setTrangThaiSanPham(int trangThaiSanPham) { this.trangThaiSanPham = trangThaiSanPham; }

    public double getGiamGia() { return giamGia; }
    public void setGiamGia(double giamGia) { this.giamGia = giamGia; }

    // ================== LẤY TẤT CẢ SẢN PHẨM ======================
    public List<SanPham> getAll() throws SQLException {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM SAN_PHAM";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.maSanPham = rs.getInt("MaSanPham");
                sp.tenSanPham = rs.getString("TenSanPham");
                sp.donGia = rs.getDouble("DonGia");
                sp.maNCC = rs.getInt("MaNCC");
                sp.trangThaiSanPham = rs.getInt("TrangThaiSanPham");
                sp.giamGia = rs.getDouble("GiamGia");

                list.add(sp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu SP: " + e.getMessage());
        }
        return list;
    }

    // ======================== THÊM ===================================
    public boolean insert(SanPham sp) throws SQLException {
        String sql = """
            INSERT INTO SAN_PHAM (TenSanPham, DonGia, MaNCC, TrangThaiSanPham, GiamGia)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.tenSanPham);
            ps.setDouble(2, sp.donGia);
            ps.setInt(3, sp.maNCC);
            ps.setInt(4, sp.trangThaiSanPham);
            ps.setDouble(5, sp.giamGia);

            return ps.executeUpdate() > 0;
        }
    }

    // ======================== SỬA ====================================
    public boolean update(SanPham sp) throws SQLException {
        String sql = """
            UPDATE SAN_PHAM
            SET TenSanPham=?, DonGia=?, MaNCC=?, TrangThaiSanPham=?, GiamGia=?
            WHERE MaSanPham=?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.tenSanPham);
            ps.setDouble(2, sp.donGia);
            ps.setInt(3, sp.maNCC);
            ps.setInt(4, sp.trangThaiSanPham);
            ps.setDouble(5, sp.giamGia);
            ps.setInt(6, sp.maSanPham);

            return ps.executeUpdate() > 0;
        }
    }

    // ======================== XÓA ====================================
    public boolean delete(int maSP) throws SQLException {
        String sql = "DELETE FROM SAN_PHAM WHERE MaSanPham=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maSP);
            return ps.executeUpdate() > 0;
        }
    }

    // ====================== TÌM KIẾM ================================
    // ====================== TÌM KIẾM ================================
public List<SanPham> search(String keyword) throws SQLException {
    List<SanPham> list = new ArrayList<>();
    String sql = """
        SELECT * FROM SAN_PHAM
        WHERE TenSanPham LIKE ?
           OR CONVERT(NVARCHAR(20), MaSanPham) LIKE ?
           OR CONVERT(NVARCHAR(20), MaNCC) LIKE ?
    """;

    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {

        String key = "%" + keyword.trim() + "%";
        ps.setString(1, key);
        ps.setString(2, key);
        ps.setString(3, key);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getInt("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setDonGia(rs.getDouble("DonGia"));
                sp.setMaNCC(rs.getInt("MaNCC"));
                sp.setTrangThaiSanPham(rs.getInt("TrangThaiSanPham"));
                sp.setGiamGia(rs.getDouble("GiamGia"));
                list.add(sp);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm SP: " + e.getMessage());
        throw e;
    }

    return list;
}


}
