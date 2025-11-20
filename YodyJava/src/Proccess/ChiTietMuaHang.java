package Proccess;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import Database.Connect;

public class ChiTietMuaHang {

    private int maMuaHang;
    private int maSanPham;
    private int soLuong;
    private double donGia;
    private final Connect cn = new Connect();

    public ChiTietMuaHang() {}

    public ChiTietMuaHang(int maMuaHang, int maSanPham, int soLuong, double donGia) {
        this.maMuaHang = maMuaHang;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getters / Setters
    public int getMaMuaHang() { return maMuaHang; }
    public void setMaMuaHang(int maMuaHang) { this.maMuaHang = maMuaHang; }

    public int getMaSanPham() { return maSanPham; }
    public void setMaSanPham(int maSanPham) { this.maSanPham = maSanPham; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    // Lấy tất cả chi tiết theo MaMuaHang
    public List<ChiTietMuaHang> getAll(int maMuaHang) {
        List<ChiTietMuaHang> list = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_MUA_HANG WHERE MaMuaHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maMuaHang);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ChiTietMuaHang(
                        rs.getInt("MaMuaHang"),
                        rs.getInt("MaSanPham"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("DonGia")
                    ));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc chi tiết MuaHang: " + e.getMessage());
        }
        return list;
    }

    // Thêm chi tiết
    public boolean insertData() {
        String sql = "INSERT INTO CHI_TIET_MUA_HANG (MaMuaHang, MaSanPham, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maMuaHang);
            ps.setInt(2, maSanPham);
            ps.setInt(3, soLuong);
            ps.setDouble(4, donGia);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm chi tiết: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật chi tiết
    public boolean updateData() {
        String sql = "UPDATE CHI_TIET_MUA_HANG SET SoLuong=?, DonGia=? WHERE MaMuaHang=? AND MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, soLuong);
            ps.setDouble(2, donGia);
            ps.setInt(3, maMuaHang);
            ps.setInt(4, maSanPham);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật chi tiết: " + e.getMessage());
            return false;
        }
    }

    // Xóa chi tiết
    public boolean deleteData() {
        String sql = "DELETE FROM CHI_TIET_MUA_HANG WHERE MaMuaHang=? AND MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maMuaHang);
            ps.setInt(2, maSanPham);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết: " + e.getMessage());
            return false;
        }
    }
}
