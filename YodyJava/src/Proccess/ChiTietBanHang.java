package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChiTietBanHang {
    private int MaBanHangChiTiet;
    private int MaBanHang;
    private int MaSanPham;
    private int SoLuong;
    private double DonGia;
    private final Connect cn = new Connect();

    public ChiTietBanHang() {}

    public ChiTietBanHang(int MaBanHangChiTiet, int MaBanHang, int MaSanPham, int SoLuong, double DonGia) {
        this.MaBanHangChiTiet = MaBanHangChiTiet;
        this.MaBanHang = MaBanHang;
        this.MaSanPham = MaSanPham;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    // Getters & Setters
    public int getMaBanHangChiTiet() { return MaBanHangChiTiet; }
    public void setMaBanHangChiTiet(int MaBanHangChiTiet) { this.MaBanHangChiTiet = MaBanHangChiTiet; }

    public int getMaBanHang() { return MaBanHang; }
    public void setMaBanHang(int MaBanHang) { this.MaBanHang = MaBanHang; }

    public int getMaSanPham() { return MaSanPham; }
    public void setMaSanPham(int MaSanPham) { this.MaSanPham = MaSanPham; }

    public int getSoLuong() { return SoLuong; }
    public void setSoLuong(int SoLuong) { this.SoLuong = SoLuong; }

    public double getDonGia() { return DonGia; }
    public void setDonGia(double DonGia) { this.DonGia = DonGia; }

    // Lấy danh sách chi tiết theo MaBanHang
    public List<ChiTietBanHang> getListByMaBanHang(int maBanHang) {
        List<ChiTietBanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_BAN_HANG WHERE MaBanHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maBanHang);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietBanHang ct = new ChiTietBanHang();
                    ct.MaBanHangChiTiet = rs.getInt("MaBanHangChiTiet");
                    ct.MaBanHang = rs.getInt("MaBanHang");
                    ct.MaSanPham = rs.getInt("MaSanPham");
                    ct.SoLuong = rs.getInt("SoLuong");
                    ct.DonGia = rs.getDouble("DonGia");
                    list.add(ct);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc chi tiết Bán Hàng: " + e.getMessage());
        }
        return list;
    }

    // Thêm chi tiết bán hàng
    public boolean addChiTiet() {
        String sql = "INSERT INTO CHI_TIET_BAN_HANG (MaBanHang, MaSanPham, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, MaBanHang);
            ps.setInt(2, MaSanPham);
            ps.setInt(3, SoLuong);
            ps.setDouble(4, DonGia);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.MaBanHangChiTiet = generatedKeys.getInt(1);
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm chi tiết Bán Hàng: " + e.getMessage());
        }
        return false;
    }

    // Cập nhật chi tiết bán hàng
    public boolean updateChiTiet() {
        String sql = "UPDATE CHI_TIET_BAN_HANG SET MaSanPham=?, SoLuong=?, DonGia=? WHERE MaBanHangChiTiet=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaSanPham);
            ps.setInt(2, SoLuong);
            ps.setDouble(3, DonGia);
            ps.setInt(4, MaBanHangChiTiet);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật chi tiết Bán Hàng: " + e.getMessage());
            return false;
        }
    }

    // Xóa chi tiết bán hàng
    public boolean deleteChiTiet() {
        String sql = "DELETE FROM CHI_TIET_BAN_HANG WHERE MaBanHangChiTiet=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaBanHangChiTiet);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết Bán Hàng: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách mã sản phẩm (dùng cho ComboBox)
    public List<Integer> getListMaSanPham() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaSanPham FROM SAN_PHAM"; // bảng sản phẩm
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("MaSanPham"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi load sản phẩm: " + e.getMessage());
        }
        return list;
    }
}
