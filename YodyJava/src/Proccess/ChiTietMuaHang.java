package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChiTietMuaHang {
    private int maMH;
    private int maSP;
    private int soLuong;
    private double donGia;

    private final Connect cn = new Connect();

    // Constructors
    public ChiTietMuaHang() {}

    public ChiTietMuaHang(int maMH, int maSP, int soLuong, double donGia) {
        this.maMH = maMH;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getters & Setters
    public int getMaMH() { return maMH; }
    public void setMaMH(int maMH) { this.maMH = maMH; }

    public int getMaSP() { return maSP; }
    public void setMaSP(int maSP) { this.maSP = maSP; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    // ===========================
    // LẤY DỮ LIỆU
    // ===========================

    // Lấy tất cả chi tiết mua hàng
    public List<ChiTietMuaHang> getAll() {
        List<ChiTietMuaHang> list = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_MUA_HANG";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ChiTietMuaHang(
                    rs.getInt("MaMuaHang"),
                    rs.getInt("MaSanPham"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("DonGia")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Chi Tiết Mua Hàng: " + e.getMessage());
        }
        return list;
    }

    // Lấy chi tiết mua hàng theo MaMuaHang
    public List<ChiTietMuaHang> getByMaMuaHang(int maMH) {
        List<ChiTietMuaHang> list = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_MUA_HANG WHERE MaMuaHang = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maMH);
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
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu theo MaMuaHang: " + e.getMessage());
        }
        return list;
    }

    // Lấy chi tiết theo MaMuaHang + MaSanPham
    public ChiTietMuaHang getChiTiet(int maMH, int maSP) {
        String sql = "SELECT * FROM CHI_TIET_MUA_HANG WHERE MaMuaHang = ? AND MaSanPham = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maMH);
            ps.setInt(2, maSP);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ChiTietMuaHang(
                        rs.getInt("MaMuaHang"),
                        rs.getInt("MaSanPham"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("DonGia")
                    );
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc chi tiết: " + e.getMessage());
        }
        return null;
    }

    // Kiểm tra tồn tại chi tiết
    public boolean exists(int maMH, int maSP) {
        String sql = "SELECT 1 FROM CHI_TIET_MUA_HANG WHERE MaMuaHang = ? AND MaSanPham = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maMH);
            ps.setInt(2, maSP);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kiểm tra tồn tại: " + e.getMessage());
        }
        return false;
    }

    // Lấy danh sách tất cả MaMuaHang
    public List<Integer> getAllMaMH() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaMuaHang FROM MUA_HANG ORDER BY MaMuaHang";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(rs.getInt("MaMuaHang"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy MaMuaHang: " + e.getMessage());
        }
        return list;
    }

    // Lấy danh sách tất cả MaSanPham
    public List<Integer> getAllMaSP() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaSanPham FROM SAN_PHAM ORDER BY MaSanPham";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(rs.getInt("MaSanPham"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy MaSanPham: " + e.getMessage());
        }
        return list;
    }

    // ===========================
    // THÊM, SỬA, XÓA
    // ===========================

    // Thêm chi tiết mua hàng
    public boolean insertData(ChiTietMuaHang obj) {
        if (obj == null) return false;
        String sql = "INSERT INTO CHI_TIET_MUA_HANG (MaMuaHang, MaSanPham, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getMaMH());
            ps.setInt(2, obj.getMaSP());
            ps.setInt(3, obj.getSoLuong());
            ps.setDouble(4, obj.getDonGia());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm Chi Tiết Mua Hàng: " + e.getMessage());
        }
        return false;
    }

    // Sửa chi tiết mua hàng
    public boolean editData(ChiTietMuaHang obj) {
        if (obj == null) return false;
        String sql = "UPDATE CHI_TIET_MUA_HANG SET SoLuong = ?, DonGia = ? WHERE MaMuaHang = ? AND MaSanPham = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getSoLuong());
            ps.setDouble(2, obj.getDonGia());
            ps.setInt(3, obj.getMaMH());
            ps.setInt(4, obj.getMaSP());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa Chi Tiết Mua Hàng: " + e.getMessage());
        }
        return false;
    }

    // Xóa chi tiết mua hàng
    public boolean deleteData(int maMH, int maSP) {
        String sql = "DELETE FROM CHI_TIET_MUA_HANG WHERE MaMuaHang = ? AND MaSanPham = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maMH);
            ps.setInt(2, maSP);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa Chi Tiết Mua Hàng: " + e.getMessage());
        }
        return false;
    }
}
