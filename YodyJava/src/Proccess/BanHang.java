package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BanHang {

    private int MaBanHang;
    private Timestamp NgayDatHang;
    private int MaKhachHang;
    private int MaNV;
    private double TongTien;
    private String GhiChu;

    private final Connect cn = new Connect();

    public BanHang() {}

    public BanHang(int MaBanHang, Timestamp NgayDatHang, int MaKhachHang, int MaNV, double TongTien, String GhiChu) {
        this.MaBanHang = MaBanHang;
        this.NgayDatHang = NgayDatHang;
        this.MaKhachHang = MaKhachHang;
        this.MaNV = MaNV;
        this.TongTien = TongTien;
        this.GhiChu = GhiChu;
    }

    // ===================== GET - SET =====================
    public int getMaBanHang() { return MaBanHang; }
    public void setMaBanHang(int MaBanHang) { this.MaBanHang = MaBanHang; }

    public Timestamp getNgayDatHang() { return NgayDatHang; }
    public void setNgayDatHang(Timestamp NgayDatHang) { this.NgayDatHang = NgayDatHang; }

    public int getMaKhachHang() { return MaKhachHang; }
    public void setMaKhachHang(int MaKhachHang) { this.MaKhachHang = MaKhachHang; }

    public int getMaNV() { return MaNV; }
    public void setMaNV(int MaNV) { this.MaNV = MaNV; }

    public double getTongTien() { return TongTien; }
    public void setTongTien(double TongTien) { this.TongTien = TongTien; }

    public String getGhiChu() { return GhiChu; }
    public void setGhiChu(String GhiChu) { this.GhiChu = GhiChu; }

    // ===================== LẤY TẤT CẢ ĐƠN BÁN =====================
    public List<BanHang> getAllBanHang() {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM BAN_HANG ORDER BY MaBanHang DESC";

        try (Connection con = cn.connectSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                BanHang bh = new BanHang();
                bh.MaBanHang = rs.getInt("MaBanHang");
                bh.NgayDatHang = rs.getTimestamp("NgayDatHang");
                bh.MaKhachHang = rs.getInt("MaKhachHang");
                bh.MaNV = rs.getInt("MaNV");
                bh.TongTien = rs.getDouble("TongTien");
                bh.GhiChu = rs.getString("GhiChu");

                list.add(bh);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách Bán hàng: " + e.getMessage());
        }

        return list;
    }

    // ===================== THÊM =====================
    public boolean addBanHang() {
        String sql = """
            INSERT INTO BAN_HANG (NgayDatHang, MaKhachHang, MaNV, TongTien, GhiChu)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, NgayDatHang);
            ps.setInt(2, MaKhachHang);
            ps.setInt(3, MaNV);
            ps.setDouble(4, TongTien);
            ps.setString(5, GhiChu);

            int kq = ps.executeUpdate();
            if (kq > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.MaBanHang = rs.getInt(1);
                }
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm Bán hàng: " + e.getMessage());
        }

        return false;
    }

    // ===================== SỬA =====================
    public boolean updateBanHang() {
        String sql = """
            UPDATE BAN_HANG
            SET NgayDatHang=?, MaKhachHang=?, MaNV=?, TongTien=?, GhiChu=?
            WHERE MaBanHang=?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, NgayDatHang);
            ps.setInt(2, MaKhachHang);
            ps.setInt(3, MaNV);
            ps.setDouble(4, TongTien);
            ps.setString(5, GhiChu);
            ps.setInt(6, MaBanHang);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật Bán hàng: " + e.getMessage());
        }
        return false;
    }

    // ===================== XÓA =====================
    public boolean deleteBanHang() {
        String sql = "DELETE FROM BAN_HANG WHERE MaBanHang=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaBanHang);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa Bán hàng: " + e.getMessage());
        }

        return false;
    }

    // ===================== LẤY CHI TIẾT ĐƠN BÁN =====================
    public List<ChiTietBanHang> getChiTietBanHang() {
        List<ChiTietBanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_BAN_HANG WHERE MaBanHang=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaBanHang);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietBanHang ct = new ChiTietBanHang();
                ct.setMaBanHangChiTiet(rs.getInt("MaBanHangChiTiet"));
                ct.setMaBanHang(rs.getInt("MaBanHang"));
                ct.setMaSanPham(rs.getInt("MaSanPham"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setDonGia(rs.getDouble("DonGia"));
                ct.setMaBienThe(rs.getInt("MaBienThe"));

                list.add(ct);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc chi tiết Bán hàng: " + e.getMessage());
        }

        return list;
    }

    // ===================== LẤY DANH SÁCH MÃ KHÁCH HÀNG =====================
    public List<Integer> getAllKH() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaKhachHang FROM KHACH_HANG";

        try (Connection con = cn.connectSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(rs.getInt(1));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy danh sách khách hàng: " + e.getMessage());
        }

        return list;
    }

    // ===================== LẤY DANH SÁCH MÃ NHÂN VIÊN =====================
    public List<Integer> getAllMNV() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaNhanVien FROM NHAN_VIEN";

        try (Connection con = cn.connectSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(rs.getInt(1));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy danh sách nhân viên: " + e.getMessage());
        }

        return list;
    }
    // ===================== LẤY 1 ĐƠN BÁN THEO MÃ =====================
public BanHang getBanHang(int maBanHang) {
    BanHang bh = null;
    String sql = "SELECT * FROM BAN_HANG WHERE MaBanHang = ?";

    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, maBanHang);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            bh = new BanHang();
            bh.setMaBanHang(rs.getInt("MaBanHang"));
            bh.setNgayDatHang(rs.getTimestamp("NgayDatHang"));
            bh.setMaKhachHang(rs.getInt("MaKhachHang"));
            bh.setMaNV(rs.getInt("MaNV"));
            bh.setTongTien(rs.getDouble("TongTien"));
            bh.setGhiChu(rs.getString("GhiChu"));
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi đọc đơn bán hàng: " + e.getMessage());
    }

    return bh;
}

}
