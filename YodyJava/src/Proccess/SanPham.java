package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SanPham {
    private int maSanPham;
    private String tenSanPham;
    private int maNCC;
    private String trangThaiSanPham; // "Còn hàng", "Hết hàng"
    private double giamGia;

    private final Connect cn = new Connect();

    public SanPham() {}

    public SanPham(int maSanPham, String tenSanPham, int maNCC, String trangThaiSanPham, double giamGia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maNCC = maNCC;
        this.trangThaiSanPham = trangThaiSanPham;
        this.giamGia = giamGia;
    }

    // GETTER & SETTER
    public int getMaSanPham() { return maSanPham; }
    public void setMaSanPham(int maSanPham) { this.maSanPham = maSanPham; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public int getMaNCC() { return maNCC; }
    public void setMaNCC(int maNCC) { this.maNCC = maNCC; }

    public String getTrangThaiSanPham() { return trangThaiSanPham; }
    public void setTrangThaiSanPham(String trangThaiSanPham) { this.trangThaiSanPham = trangThaiSanPham; }

    public double getGiamGia() { return giamGia; }
    public void setGiamGia(double giamGia) { this.giamGia = giamGia; }

    // Lấy tất cả sản phẩm
    public List<SanPham> getAll() throws SQLException {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM SAN_PHAM";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getInt("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setMaNCC(rs.getInt("MaNCC"));
                sp.setTrangThaiSanPham(rs.getString("TrangThaiSanPham"));
                sp.setGiamGia(rs.getDouble("GiamGia"));
                list.add(sp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu SP: " + e.getMessage());
        }
        return list;
    }

    // Thêm sản phẩm
    public boolean insert(SanPham sp) throws SQLException {
        String sql = "INSERT INTO SAN_PHAM (TenSanPham, MaNCC, TrangThaiSanPham, GiamGia) VALUES (?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getMaNCC());
            ps.setString(3, sp.getTrangThaiSanPham());
            ps.setDouble(4, sp.getGiamGia());

            return ps.executeUpdate() > 0;
        }
    }

    // Sửa sản phẩm
    public boolean update(SanPham sp) throws SQLException {
        String sql = "UPDATE SAN_PHAM SET TenSanPham=?, MaNCC=?, TrangThaiSanPham=?, GiamGia=? WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getMaNCC());
            ps.setString(3, sp.getTrangThaiSanPham());
            ps.setDouble(4, sp.getGiamGia());
            ps.setInt(5, sp.getMaSanPham());

            return ps.executeUpdate() > 0;
        }
    }

    // Xóa sản phẩm
    public boolean delete(int maSP) throws SQLException {
        String sql = "DELETE FROM SAN_PHAM WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maSP);
            return ps.executeUpdate() > 0;
        }
    }

    // Tìm kiếm sản phẩm
    public List<SanPham> search(String keyword) throws SQLException {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM SAN_PHAM WHERE TenSanPham LIKE ? OR CONVERT(NVARCHAR(20), MaSanPham) LIKE ? OR CONVERT(NVARCHAR(20), MaNCC) LIKE ?";
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
                    sp.setMaNCC(rs.getInt("MaNCC"));
                    sp.setTrangThaiSanPham(rs.getString("TrangThaiSanPham"));
                    sp.setGiamGia(rs.getDouble("GiamGia"));
                    list.add(sp);
                }
            }
        }
        return list;
    }

    // ----------------- PHẦN MỚI: LẤY DANH SÁCH NCC -----------------
    public List<Integer> getAllNCC() {
    List<Integer> list = new ArrayList<>();
    String sql = "SELECT MaNhaCungCap FROM NHA_CUNG_CAP";

    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int maNCC = rs.getInt("MaNhaCungCap");
            list.add(maNCC); // đúng kiểu Integer
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi load nhà cung cấp: " + e.getMessage());
    }
    return list;
}
}
