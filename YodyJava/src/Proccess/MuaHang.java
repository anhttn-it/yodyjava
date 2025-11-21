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
    private final Connect cn = new Connect();

    public MuaHang() {}

    public MuaHang(int maMuaHang, Date ngayMua, int maNCC, int maNV) {
        this.maMuaHang = maMuaHang;
        this.ngayMua = ngayMua;
        this.maNCC = maNCC;
        this.maNV = maNV;
    }

    public int getMaMuaHang() { return maMuaHang; }
    public void setMaMuaHang(int maMuaHang) { this.maMuaHang = maMuaHang; }

    public Date getNgayMua() { return ngayMua; }
    public void setNgayMua(Date ngayMua) { this.ngayMua = ngayMua; }

    public int getMaNCC() { return maNCC; }
    public void setMaNCC(int maNCC) { this.maNCC = maNCC; }

    public int getMaNV() { return maNV; }
    public void setMaNV(int maNV) { this.maNV = maNV; }

    public List<MuaHang> getAll() {
        List<MuaHang> list = new ArrayList<>();
        String sql = "SELECT * FROM MUA_HANG";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MuaHang mh = new MuaHang();
                mh.maMuaHang = rs.getInt("MaMuaHang");
                mh.ngayMua = rs.getTimestamp("NgayMua");
                mh.maNCC = rs.getInt("MaNCC");
                mh.maNV = rs.getInt("MaNV");
                list.add(mh);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Mua Hàng: " + e.getMessage());
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
                    return obj;
                }
            }
        }
        return null;
    }

    public boolean deleteData(int maMH) throws SQLException {
        String sql = "DELETE FROM MUA_HANG WHERE MaMuaHang = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maMH);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean insertData(MuaHang obj) throws SQLException {
        String sql = "INSERT INTO MUA_HANG (NgayMua, MaNCC, MaNV) VALUES (?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new java.sql.Timestamp(obj.ngayMua.getTime()));
            ps.setInt(2, obj.maNCC);
            ps.setInt(3, obj.maNV);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean editData(MuaHang obj) throws SQLException {
        String sql = "UPDATE MUA_HANG SET NgayMua = ?, MaNCC = ?, MaNV = ? WHERE MaMuaHang = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new java.sql.Timestamp(obj.ngayMua.getTime()));
            ps.setInt(2, obj.maNCC);
            ps.setInt(3, obj.maNV);
            ps.setInt(4, obj.maMuaHang);
            return ps.executeUpdate() > 0;
        }
    }
}
