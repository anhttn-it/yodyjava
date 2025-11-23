package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class KhachHang {
    private int maKH;
    private String tenKH;
    private String diaChi;
    private String soDienThoai;
    private String gioiTinh; // SQL là nvarchar(10)
    private int loaiKH;

    private final Connect cn = new Connect();

    public KhachHang() {}

    public KhachHang(int maKH, String tenKH, String diaChi, String soDienThoai, String gioiTinh, int loaiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.loaiKH = loaiKH;
    }

    // ===== GETTER & SETTER =====
    public int getMaKH() { return maKH; }
    public void setMaKH(int maKH) { this.maKH = maKH; }

    public String getTenKH() { return tenKH; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public int getLoaiKH() { return loaiKH; }
    public void setLoaiKH(int loaiKH) { this.loaiKH = loaiKH; }

    // ================== LẤY TẤT CẢ KHÁCH HÀNG ======================
    public List<KhachHang> getAll() throws SQLException {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KHACH_HANG";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MaKhachHang"));
                kh.setTenKH(rs.getString("TenKhachHang"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setLoaiKH(rs.getInt("LoaiKhachHang"));
                list.add(kh);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu KH: " + e.getMessage());
        }
        return list;
    }

    // ======================== THÊM ===================================
    public boolean insertData(KhachHang kh) throws SQLException {
        String sql = """
            INSERT INTO KHACH_HANG (TenKhachHang, DiaChi, SoDienThoai, GioiTinh, LoaiKhachHang)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, kh.getTenKH());
            ps.setString(2, kh.getDiaChi());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getGioiTinh());
            ps.setInt(5, kh.getLoaiKH());

            return ps.executeUpdate() > 0;
        }
    }

    // ======================== SỬA ====================================
    public boolean editData(KhachHang kh) throws SQLException {
        String sql = """
            UPDATE KHACH_HANG
            SET TenKhachHang=?, DiaChi=?, SoDienThoai=?, GioiTinh=?, LoaiKhachHang=?
            WHERE MaKhachHang=?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, kh.getTenKH());
            ps.setString(2, kh.getDiaChi());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getGioiTinh());
            ps.setInt(5, kh.getLoaiKH());
            ps.setInt(6, kh.getMaKH());

            return ps.executeUpdate() > 0;
        }
    }

    // ======================== XÓA ====================================
    public boolean deleteData(int maKH) throws SQLException {
        String sql = "DELETE FROM KHACH_HANG WHERE MaKhachHang=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maKH);
            return ps.executeUpdate() > 0;
        }
    }

    // ====================== TÌM KIẾM ================================
    public List<KhachHang> search(String keyword) throws SQLException {
        List<KhachHang> list = new ArrayList<>();
        String sql = """
            SELECT * FROM KHACH_HANG
            WHERE TenKhachHang LIKE ?
               OR MaKhachHang LIKE ?
               OR SoDienThoai LIKE ?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String key = "%" + keyword.trim() + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getInt("MaKhachHang"));
                    kh.setTenKH(rs.getString("TenKhachHang"));
                    kh.setDiaChi(rs.getString("DiaChi"));
                    kh.setSoDienThoai(rs.getString("SoDienThoai"));
                    kh.setGioiTinh(rs.getString("GioiTinh"));
                    kh.setLoaiKH(rs.getInt("LoaiKhachHang"));
                    list.add(kh);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm KH: " + e.getMessage());
            throw e;
        }

        return list;
    }

    // ================= LẤY 1 KH =====================
    public KhachHang getKhachHang(int maKH) throws SQLException {
        String sql = "SELECT * FROM KHACH_HANG WHERE MaKhachHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(rs.getInt("MaKhachHang"));
                    kh.setTenKH(rs.getString("TenKhachHang"));
                    kh.setDiaChi(rs.getString("DiaChi"));
                    kh.setSoDienThoai(rs.getString("SoDienThoai"));
                    kh.setGioiTinh(rs.getString("GioiTinh"));
                    kh.setLoaiKH(rs.getInt("LoaiKhachHang"));
                    return kh;
                }
            }
        }
        return null;
    }
}
