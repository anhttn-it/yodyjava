package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class NhaCungCap {
    private int maNCC;
    private String tenNCC;
    private String email;

    private final Connect cn = new Connect();

    public NhaCungCap() {}

    public NhaCungCap(int maNCC, String tenNCC, String email) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.email = email;
    }

    public int getMaNCC() { return maNCC; }
    public void setMaNCC(int maNCC) { this.maNCC = maNCC; }

    public String getTenNCC() { return tenNCC; }
    public void setTenNCC(String tenNCC) { this.tenNCC = tenNCC; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM NHA_CUNG_CAP";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.maNCC = rs.getInt("MaNhaCungCap");
                ncc.tenNCC = rs.getString("Ten");
                ncc.email = rs.getString("Email");
                list.add(ncc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Nha Cung Cap: " + e.getMessage());
        }
        return list;
    }

    public NhaCungCap getNCC(int maNCC) {
        String sql = "SELECT * FROM NHA_CUNG_CAP WHERE MaNhaCungCap = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maNCC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NhaCungCap ncc = new NhaCungCap();
                    ncc.maNCC = rs.getInt("MaNhaCungCap");
                    ncc.tenNCC = rs.getString("Ten");
                    ncc.email = rs.getString("Email");
                    return ncc;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy NCC: " + e.getMessage());
        }
        return null;
    }

    public boolean insertData(NhaCungCap obj) {
        String sql = "INSERT INTO NHA_CUNG_CAP (Ten, Email) VALUES (?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.tenNCC);
            ps.setString(2, obj.email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm NCC: " + e.getMessage());
        }
        return false;
    }

    public boolean editData(NhaCungCap obj) {
        String sql = "UPDATE NHA_CUNG_CAP SET Ten = ?, Email = ? WHERE MaNhaCungCap = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.tenNCC);
            ps.setString(2, obj.email);
            ps.setInt(3, obj.maNCC);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa NCC: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteData(int maNCC) {
        String sql = "DELETE FROM NHA_CUNG_CAP WHERE MaNhaCungCap = ?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maNCC);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa NCC: " + e.getMessage());
        }
        return false;
    }
    public List<NhaCungCap> search(String keyword) {
    List<NhaCungCap> list = new ArrayList<>();
    String sql = """
            SELECT * NHA_CUNG_CAP
            WHERE MaNhaCungCap LIKE ?
               OR Ten LIKE ?
               OR Email LIKE ?
        """;
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {
        String key = "%" + keyword + "%";
        ps.setString(1, key);
        ps.setString(2, key);
        ps.setString(3, key);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getInt("MaNhaCungCap"));
                ncc.setTenNCC(rs.getString("Ten"));
                ncc.setEmail(rs.getString("Email"));
                list.add(ncc);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm NCC: " + e.getMessage());
    }
    return list;
    }
}
