package Proccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Database.Connect;

public class KhachHang {
    private int MaKH;
    private String TenKH;
    private String DiaChi;
    private String SoDienThoai;
    private int GioiTinh; // 0 = Nữ, 1 = Nam
    private int LoaiKH;   // 0 = Thường, 1 = VIP

    private final Connect cn = new Connect();

    public KhachHang() {}

    public KhachHang(int MaKH, String TenKH, String DiaChi, String SoDienThoai, int GioiTinh, int LoaiKH) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.GioiTinh = GioiTinh;
        this.LoaiKH = LoaiKH;
    }

    // Getter và Setter
    public int getMaKH() { return MaKH; }
    public void setMaKH(int MaKH) { this.MaKH = MaKH; }

    public String getTenKH() { return TenKH; }
    public void setTenKH(String TenKH) { this.TenKH = TenKH; }

    public String getDiaChi() { return DiaChi; }
    public void setDiaChi(String DiaChi) { this.DiaChi = DiaChi; }

    public String getSoDienThoai() { return SoDienThoai; }
    public void setSoDienThoai(String SoDienThoai) { this.SoDienThoai = SoDienThoai; }

    public int getGioiTinh() { return GioiTinh; }
    public void setGioiTinh(int GioiTinh) { this.GioiTinh = GioiTinh; }

    public int getLoaiKH() { return LoaiKH; }
    public void setLoaiKH(int LoaiKH) { this.LoaiKH = LoaiKH; }

    // Lấy tất cả khách hàng
    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KHACH_HANG";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.MaKH = rs.getInt("MaKhachHang");
                kh.TenKH = rs.getString("TenKhachHang");
                kh.DiaChi = rs.getString("DiaChi");
                kh.SoDienThoai = rs.getString("SoDienThoai");
                kh.GioiTinh = rs.getInt("GioiTinh");
                kh.LoaiKH = rs.getInt("LoaiKhachHang");
                list.add(kh);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Khách Hàng: " + e.getMessage());
        }
        return list;
    }

    // Lấy 1 khách hàng theo MaKH
    public KhachHang getKhachHang(int MaKH) {
        String sql = "SELECT * FROM KHACH_HANG WHERE MaKhachHang = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, MaKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.MaKH = rs.getInt("MaKhachHang");
                    kh.TenKH = rs.getString("TenKhachHang");
                    kh.DiaChi = rs.getString("DiaChi");
                    kh.SoDienThoai = rs.getString("SoDienThoai");
                    kh.GioiTinh = rs.getInt("GioiTinh");
                    kh.LoaiKH = rs.getInt("LoaiKhachHang");
                    return kh;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy Khách Hàng: " + e.getMessage());
        }
        return null;
    }

    // Xóa khách hàng
    public boolean deleteData(int MaKH) {
        String sql = "DELETE FROM KHACH_HANG WHERE MaKhachHang = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, MaKH);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xóa thất bại: " + e.getMessage());
        }
        return false;
    }

    // Thêm khách hàng
    public boolean insertData(KhachHang obj) {
        String sql = "INSERT INTO KHACH_HANG (TenKhachHang, DiaChi, SoDienThoai, GioiTinh, LoaiKhachHang) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.TenKH);
            ps.setString(2, obj.DiaChi);
            ps.setString(3, obj.SoDienThoai);
            ps.setInt(4, obj.GioiTinh);
            ps.setInt(5, obj.LoaiKH);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại: " + e.getMessage());
        }
        return false;
    }

    // Sửa khách hàng
    public boolean editData(KhachHang obj) {
        String sql = "UPDATE KHACH_HANG SET TenKhachHang = ?, DiaChi = ?, SoDienThoai = ?, GioiTinh = ?, LoaiKhachHang = ? WHERE MaKhachHang = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.TenKH);
            ps.setString(2, obj.DiaChi);
            ps.setString(3, obj.SoDienThoai);
            ps.setInt(4, obj.GioiTinh);
            ps.setInt(5, obj.LoaiKH);
            ps.setInt(6, obj.MaKH);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại: " + e.getMessage());
        }
        return false;
    }
}
