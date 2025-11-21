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
public class BanHang {
    private int MaBanHang;
    private int MaKhachHang;
    private int MaNV;
    private Timestamp NgayDatHang;
    private int TrangThai; // 0: chờ, 1: xác nhận, 2: đã giao
    private final Connect cn = new Connect();

    public BanHang() {}

    public BanHang(int MaBanHang, int MaKhachHang, int MaNV, Timestamp NgayDatHang, int TrangThai) {
        this.MaBanHang = MaBanHang;
        this.MaKhachHang = MaKhachHang;
        this.MaNV = MaNV;
        this.NgayDatHang = NgayDatHang;
        this.TrangThai = TrangThai;
    }

    // Getters và Setters
    public int getMaBanHang() { return MaBanHang; }
    public void setMaBanHang(int MaBanHang) { this.MaBanHang = MaBanHang; }

    public int getMaKhachHang() { return MaKhachHang; }
    public void setMaKhachHang(int MaKhachHang) { this.MaKhachHang = MaKhachHang; }

    public int getMaNV() { return MaNV; }
    public void setMaNV(int MaNV) { this.MaNV = MaNV; }

    public Timestamp getNgayDatHang() { return NgayDatHang; }
    public void setNgayDatHang(Timestamp NgayDatHang) { this.NgayDatHang = NgayDatHang; }

    public int getTrangThai() { return TrangThai; }
    public void setTrangThai(int TrangThai) { this.TrangThai = TrangThai; }

    // Lấy danh sách tất cả đơn bán
    public List<BanHang> getAllBanHang() {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM BAN_HANG";
        try (Connection con = cn.connectSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                BanHang bh = new BanHang();
                bh.MaBanHang = rs.getInt("MaBanHang");
                bh.MaKhachHang = rs.getInt("MaKhachHang");
                bh.MaNV = rs.getInt("MaNV");
                bh.NgayDatHang = rs.getTimestamp("NgayDatHang");
                bh.TrangThai = rs.getInt("TrangThai");
                list.add(bh);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Bán Hàng: " + e.getMessage());
        }
        return list;
    }

    // Thêm đơn bán
    public boolean addBanHang() {
        String sql = "INSERT INTO BAN_HANG (MaKhachHang, MaNV, NgayDatHang, TrangThai) VALUES (?, ?, ?, ?)";
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        // Gán giá trị
        ps.setInt(1, MaKhachHang);
        ps.setInt(2, MaNV);
        ps.setTimestamp(3, NgayDatHang);
        ps.setInt(4, TrangThai);

        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            // Lấy MaBanHang tự tăng
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.MaBanHang = generatedKeys.getInt(1);
                }
            }
            return true; // Thêm thành công
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi thêm Bán Hàng: " + e.getMessage());
    }
    return false; // Thêm thất bại
    }

    // Sửa đơn bán
    public boolean updateBanHang() {
        String sql = "UPDATE BAN_HANG SET MaKhachHang=?, MaNV=?, NgayDatHang=?, TrangThai=? WHERE MaBanHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaKhachHang);
            ps.setInt(2, MaNV);
            ps.setTimestamp(3, NgayDatHang);
            ps.setInt(4, TrangThai);
            ps.setInt(5, MaBanHang);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật Bán Hàng: " + e.getMessage());
            return false;
        }
    }

    // Xóa đơn bán
    public boolean deleteBanHang() {
        String sql = "DELETE FROM BAN_HANG WHERE MaBanHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaBanHang);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa Bán Hàng: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách chi tiết của đơn này
    public List<ChiTietBanHang> getChiTietBanHang() {
        List<ChiTietBanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_BAN_HANG WHERE MaBanHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaBanHang);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietBanHang ct = new ChiTietBanHang();
                    ct.setMaBanHangChiTiet(rs.getInt("MaBanHangChiTiet"));
                    ct.setMaBanHang(rs.getInt("MaBanHang"));
                    ct.setMaSanPham(rs.getInt("MaSanPham"));
                    ct.setSoLuong(rs.getInt("SoLuong"));
                    ct.setDonGia(rs.getDouble("DonGia"));
                    list.add(ct);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc chi tiết Bán Hàng: " + e.getMessage());
        }
        return list;
    }
}
