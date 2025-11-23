package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChiTietMuaHang {
    private int maMH;
    private int maSP;
    private String mauSac;
    private int kichCo;
    private int soLuong;
    private double donGia;
    private int maBienThe;

    private final Connect cn = new Connect();

    // Constructors
    public ChiTietMuaHang() {}

    public ChiTietMuaHang(int maMH, int maSP, String mauSac, int kichCo, int soLuong, double donGia) {
        this.maMH = maMH;
        this.maSP = maSP;
        this.mauSac = mauSac;
        this.kichCo = kichCo;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getters & Setters
    public int getMaMH() { return maMH; }
    public void setMaMH(int maMH) { this.maMH = maMH; }
    public int getMaSP() { return maSP; }
    public void setMaSP(int maSP) { this.maSP = maSP; }
    public String getMauSac() { return mauSac; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }
    public int getKichCo() { return kichCo; }
    public void setKichCo(int kichCo) { this.kichCo = kichCo; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public int getMaBienThe() { return maBienThe; }
    public void setMaBienThe(int maBienThe) { this.maBienThe = maBienThe; }

    // ===========================
    // LẤY DỮ LIỆU
    // ===========================

    public List<ChiTietMuaHang> getAll() {
        List<ChiTietMuaHang> list = new ArrayList<>();
        String sql = "SELECT ctmh.MaMuaHang, ctmh.MaSanPham, bt.MauSac, bt.KichCo, ctmh.SoLuong, ctmh.DonGia, ctmh.MaBienThe " +
                     "FROM CHI_TIET_MUA_HANG ctmh " +
                     "JOIN BIEN_THE_SAN_PHAM bt ON ctmh.MaBienThe = bt.Ma";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChiTietMuaHang obj = new ChiTietMuaHang();
                obj.setMaMH(rs.getInt("MaMuaHang"));
                obj.setMaSP(rs.getInt("MaSanPham"));
                obj.setMauSac(rs.getString("MauSac"));
                obj.setKichCo(rs.getInt("KichCo"));
                obj.setSoLuong(rs.getInt("SoLuong"));
                obj.setDonGia(rs.getDouble("DonGia"));
                obj.setMaBienThe(rs.getInt("MaBienThe"));
                list.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Chi Tiết Mua Hàng: " + e.getMessage());
        }
        return list;
    }

    public List<ChiTietMuaHang> getByMaMuaHang(int maMH) {
        List<ChiTietMuaHang> list = new ArrayList<>();
        String sql = "SELECT ctmh.MaMuaHang, ctmh.MaSanPham, bt.MauSac, bt.KichCo, ctmh.SoLuong, ctmh.DonGia, ctmh.MaBienThe " +
                     "FROM CHI_TIET_MUA_HANG ctmh " +
                     "JOIN BIEN_THE_SAN_PHAM bt ON ctmh.MaBienThe = bt.Ma " +
                     "WHERE MaMuaHang = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maMH);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietMuaHang obj = new ChiTietMuaHang();
                    obj.setMaMH(rs.getInt("MaMuaHang"));
                    obj.setMaSP(rs.getInt("MaSanPham"));
                    obj.setMauSac(rs.getString("MauSac"));
                    obj.setKichCo(rs.getInt("KichCo"));
                    obj.setSoLuong(rs.getInt("SoLuong"));
                    obj.setDonGia(rs.getDouble("DonGia"));
                    obj.setMaBienThe(rs.getInt("MaBienThe"));
                    list.add(obj);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu theo MaMuaHang: " + e.getMessage());
        }
        return list;
    }

    public Integer getMaBienThe(int maSP, String mauSac, int kichCo) {
        String sql = "SELECT Ma FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=? AND MauSac=? AND KichCo=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.setString(2, mauSac);
            ps.setInt(3, kichCo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Ma");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy MaBienThe: " + e.getMessage());
        }
        return null;
    }

    // ===========================
    // THÊM, SỬA, XÓA
    // ===========================

    // Thêm chi tiết mua hàng
    public boolean addChiTietMH(int maMH, int maSP, String mauSac, int kichCo, int soLuong, double donGia) {
        Integer maBienThe = getMaBienThe(maSP, mauSac, kichCo);
        if (maBienThe == null) {
            JOptionPane.showMessageDialog(null, "Biến thể không tồn tại!");
            return false;
        }
        String sql = "INSERT INTO CHI_TIET_MUA_HANG (MaMuaHang, MaSanPham, MaBienThe, SoLuong, DonGia) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maMH);
            ps.setInt(2, maSP);
            ps.setInt(3, maBienThe);
            ps.setInt(4, soLuong);
            ps.setDouble(5, donGia);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm Chi Tiết Mua Hàng: " + e.getMessage());
        }
        return false;
    }

    // Sửa chi tiết mua hàng
    public boolean updateChiTietMH(int maMH, int maSP, String mauSac, int kichCo, int soLuong, double donGia) {
        Integer maBienThe = getMaBienThe(maSP, mauSac, kichCo);
        if (maBienThe == null) {
            JOptionPane.showMessageDialog(null, "Biến thể không tồn tại!");
            return false;
        }
        String sql = "UPDATE CHI_TIET_MUA_HANG SET SoLuong=?, DonGia=? WHERE MaMuaHang=? AND MaBienThe=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setDouble(2, donGia);
            ps.setInt(3, maMH);
            ps.setInt(4, maBienThe);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa Chi Tiết Mua Hàng: " + e.getMessage());
        }
        return false;
    }

    // Xóa chi tiết mua hàng
    public boolean deleteData(int maMH, int maBienThe) {
        String sql = "DELETE FROM CHI_TIET_MUA_HANG WHERE MaMuaHang=? AND MaBienThe=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maMH);
            ps.setInt(2, maBienThe);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa Chi Tiết Mua Hàng: " + e.getMessage());
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

    // Lấy danh sách Màu sắc của 1 MaSP
    public List<String> getAllMauSac(int maSP) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT MauSac FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(rs.getString("MauSac"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy Màu sắc: " + e.getMessage());
        }
        return list;
    }

    // Lấy danh sách Kích cỡ của 1 MaSP
    public List<Integer> getAllKichCo(int maSP) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT KichCo FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(rs.getInt("KichCo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy Kích cỡ: " + e.getMessage());
        }
        return list;
    }
}
