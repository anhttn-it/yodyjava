package Proccess;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import Database.Connect;

public class MuaHang {
    private int MaMuaHang;
    private java.util.Date NgayMua;
    private int MaNCC;
    private int MaNV;
    private int TrangThai;
    private final Connect cn = new Connect();

    public MuaHang() {}

    public MuaHang(int MaMuaHang, java.util.Date NgayMua, int MaNCC, int MaNV, int TrangThai) {
        this.MaMuaHang = MaMuaHang;
        this.NgayMua = NgayMua;
        this.MaNCC = MaNCC;
        this.MaNV = MaNV;
        this.TrangThai = TrangThai;
    }

    // getters/setters
    public int getMaMuaHang() { return MaMuaHang; }
    public void setMaMuaHang(int MaMuaHang) { this.MaMuaHang = MaMuaHang; }
    public java.util.Date getNgayMua() { return NgayMua; }
    public void setNgayMua(java.util.Date NgayMua) { this.NgayMua = NgayMua; }
    public int getMaNCC() { return MaNCC; }
    public void setMaNCC(int MaNCC) { this.MaNCC = MaNCC; }
    public int getMaNV() { return MaNV; }
    public void setMaNV(int MaNV) { this.MaNV = MaNV; }
    public int getTrangThai() { return TrangThai; }
    public void setTrangThai(int TrangThai) { this.TrangThai = TrangThai; }

    // --- Lấy danh sách MuaHang + ChiTiet ---
    public List<Object[]> getAllWithChiTiet() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT mh.MaMuaHang, mh.NgayMua, mh.MaNCC, mh.MaNV, " +
                     "ct.MaSP, ct.SoLuong, ct.DonGia " +
                     "FROM MuaHang mh " +
                     "LEFT JOIN ChiTietMuaHang ct ON mh.MaMuaHang = ct.MaMuaHang";

        try (Connection conn = cn.connectSQL();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt("MaMuaHang");
                row[1] = rs.getDate("NgayMua");
                row[2] = rs.getInt("MaNCC");
                row[3] = rs.getInt("MaNV");
                row[4] = rs.getObject("MaSP"); // null nếu không có chi tiết
                row[5] = rs.getObject("SoLuong");
                row[6] = rs.getObject("DonGia");
                list.add(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc MuaHang + ChiTiet: " + e.getMessage());
        }
        return list;
    }

    // --- Lấy 1 MuaHang ---
    public MuaHang getMuaHang(int id) {
        String sql = "SELECT * FROM MUA_HANG WHERE MaMuaHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MuaHang mh = new MuaHang();
                    mh.setMaMuaHang(rs.getInt("MaMuaHang"));
                    Timestamp ts = rs.getTimestamp("NgayMua");
                    mh.setNgayMua(ts != null ? new java.util.Date(ts.getTime()) : null);
                    mh.setMaNCC(rs.getInt("MaNCC"));
                    mh.setMaNV(rs.getInt("MaNV"));
                    mh.setTrangThai(rs.getInt("TrangThai"));
                    return mh;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc MuaHang: " + e.getMessage());
        }
        return null;
    }

    // --- Thêm MuaHang ---
    public boolean insertData(MuaHang obj) {
        String sql = "INSERT INTO MUA_HANG (NgayMua, MaNCC, MaNV, TrangThai) VALUES (?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, new Timestamp(obj.getNgayMua().getTime()));
            ps.setInt(2, obj.getMaNCC());
            ps.setInt(3, obj.getMaNV());
            ps.setInt(4, obj.getTrangThai());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) obj.setMaMuaHang(rs.getInt(1));
                }
            }
            return rows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm MuaHang: " + e.getMessage());
            return false;
        }
    }

    // --- Cập nhật MuaHang ---
    public boolean editData(MuaHang obj) {
        String sql = "UPDATE MUA_HANG SET NgayMua=?, MaNCC=?, MaNV=?, TrangThai=? WHERE MaMuaHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, new Timestamp(obj.getNgayMua().getTime()));
            ps.setInt(2, obj.getMaNCC());
            ps.setInt(3, obj.getMaNV());
            ps.setInt(4, obj.getTrangThai());
            ps.setInt(5, obj.getMaMuaHang());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật MuaHang: " + e.getMessage());
            return false;
        }
    }

    // --- Xóa MuaHang ---
    public boolean deleteData(int id) {
        String sqlChiTiet = "DELETE FROM CHI_TIET_MUA_HANG WHERE MaMuaHang=?";
        String sqlMuaHang = "DELETE FROM MUA_HANG WHERE MaMuaHang=?";
        try (Connection con = cn.connectSQL()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement(sqlChiTiet);
                 PreparedStatement ps2 = con.prepareStatement(sqlMuaHang)) {

                ps1.setInt(1, id);
                ps1.executeUpdate();

                ps2.setInt(1, id);
                int rows = ps2.executeUpdate();

                con.commit();
                return rows > 0;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa MuaHang: " + e.getMessage());
            return false;
        }
    }
}
