package Proccess;

import java.sql.*;
import Database.Connect;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class MuaHang {
    private int maMuaHang;
    private Date ngayMua;
    private int maNCC;
    private int maNV;
    private double tongTien;
    private String ghiChu;
    private final Connect cn = new Connect();

    public MuaHang() {}

    public MuaHang(int maMuaHang, Date ngayMua, int maNCC, int maNV, double tongTien, String ghiChu) {
        this.maMuaHang = maMuaHang;
        this.ngayMua = ngayMua;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public int getMaMuaHang() { return maMuaHang; }
    public void setMaMuaHang(int maMuaHang) { this.maMuaHang = maMuaHang; }

    public Date getNgayMua() { return ngayMua; }
    public void setNgayMua(Date ngayMua) { this.ngayMua = ngayMua; }

    public int getMaNCC() { return maNCC; }
    public void setMaNCC(int maNCC) { this.maNCC = maNCC; }

    public int getMaNV() { return maNV; }
    public void setMaNV(int maNV) { this.maNV = maNV; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    public List<MuaHang> getAll() {
    List<MuaHang> list = new ArrayList<>();
    String sql = "SELECT MaMuaHang, NgayMua, MaNCC, MaNV, TongTien, GhiChu FROM MUA_HANG ORDER BY MaMuaHang";
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            MuaHang obj = new MuaHang();
            obj.setMaMuaHang(rs.getInt("MaMuaHang"));
            obj.setNgayMua(rs.getTimestamp("NgayMua"));
            obj.setMaNCC(rs.getInt("MaNCC"));
            obj.setMaNV(rs.getInt("MaNV"));
            obj.setTongTien(rs.getDouble("TongTien"));
            obj.setGhiChu(rs.getString("GhiChu"));
            list.add(obj);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi load MuaHang: " + e.getMessage());
    }
    return list;
}


    public List<Integer> getAllNCC() throws SQLException {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaNhaCungCap FROM Nha_cung_cap";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(rs.getInt("MaNhaCungCap"));
        }
        return list;
    }

    public List<Integer> getAllMNV() throws SQLException {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaNhanVien FROM Nhan_vien";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(rs.getInt("MaNhanVien"));
        }
        return list;
    }

    public MuaHang getMuaHang(int maMH) throws SQLException {
        String sql = "SELECT * FROM MUA_HANG WHERE MaMuaHang = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maMH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MuaHang obj = new MuaHang();
                    obj.maMuaHang = rs.getInt("MaMuaHang");
                    obj.ngayMua = rs.getTimestamp("NgayMua");
                    obj.maNCC = rs.getInt("MaNCC");
                    obj.maNV = rs.getInt("MaNV");
                    obj.tongTien = rs.getDouble("TongTien");
                    obj.ghiChu = rs.getString("GhiChu");
                    return obj;
                }
            }
        }
        return null;
    }

    public boolean deleteData(int maPN) throws SQLException {
        try (Connection con = cn.connectSQL()) {
            con.setAutoCommit(false); // bật transaction
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM CHI_TIET_MUA_Hang WHERE MaMUAHang = ?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM MUA_Hang WHERE MaMUAHang = ?")) {

                ps1.setInt(1, maPN);
                ps1.executeUpdate();

                ps2.setInt(1, maPN);
                int rows = ps2.executeUpdate();

                con.commit();
                return rows > 0;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    public boolean insertData(MuaHang obj) throws SQLException {
        String sql = "INSERT INTO MUA_HANG (NgayMua, MaNCC, MaNV, TongTien, GhiChu) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new java.sql.Timestamp(obj.ngayMua.getTime()));
            ps.setInt(2, obj.maNCC);
            ps.setInt(3, obj.maNV);
            ps.setDouble(4, obj.tongTien);
            ps.setString(5, obj.ghiChu);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean editData(MuaHang obj) throws SQLException {
        String sql = "UPDATE MUA_HANG SET NgayMua=?, MaNCC=?, MaNV=?, TongTien=?, GhiChu=? WHERE MaMuaHang=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new java.sql.Timestamp(obj.ngayMua.getTime()));
            ps.setInt(2, obj.maNCC);
            ps.setInt(3, obj.maNV);
            ps.setDouble(4, obj.tongTien);
            ps.setString(5, obj.ghiChu);
            ps.setInt(6, obj.maMuaHang);
            return ps.executeUpdate() > 0;
        }
    }

    public List<MuaHang> TimKiemMH(String keyword) throws SQLException {
        List<MuaHang> list = new ArrayList<>();
        String sql = "SELECT * FROM MUA_HANG WHERE MaMuaHang LIKE ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MuaHang mh = new MuaHang();
                mh.maMuaHang = rs.getInt("MaMuaHang");
                mh.ngayMua = rs.getTimestamp("NgayMua");
                mh.maNCC = rs.getInt("MaNCC");
                mh.maNV = rs.getInt("MaNV");
                mh.tongTien = rs.getDouble("TongTien");
                mh.ghiChu = rs.getString("GhiChu");
                list.add(mh);
            }
        }
        return list;
    }
    
}
