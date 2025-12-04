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
    public SanPham getSanPham (int MaSP) throws SQLException {
        String sql = "SELECT * FROM SAN_PHAM WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1,MaSP);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    SanPham obj = new SanPham();
                    obj.maSanPham = rs.getInt("MaSanPham");
                    obj.tenSanPham = rs.getString("TenSanPham");
                    obj.maNCC = rs.getInt("MaNCC");
                    obj.trangThaiSanPham = rs.getString("TrangThaiSanPham");
                    obj.giamGia = rs.getDouble("GiamGia");
                    return obj;
                }
            }
        }
        return null;
    }
    // Thêm sản phẩm
    public boolean insert(SanPham obj) throws SQLException {
        String sql = "INSERT INTO SAN_PHAM (TenSanPham, MaNCC, TrangThaiSanPham, GiamGia) VALUES (?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.tenSanPham);
            ps.setInt(2, obj.maNCC);
            ps.setString(3, obj.trangThaiSanPham);
            ps.setDouble(4, obj.giamGia);

            return ps.executeUpdate() > 0;
        }
    }

    // Sửa sản phẩm
    public boolean update(SanPham obj) throws SQLException {
        String sql = "UPDATE SAN_PHAM SET TenSanPham=?, MaNCC=?, TrangThaiSanPham=?, GiamGia=? WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.tenSanPham);
            ps.setInt(2, obj.maNCC);
            ps.setString(3, obj.trangThaiSanPham);
            ps.setDouble(4, obj.giamGia);
            ps.setInt(5, obj.maSanPham);

            return ps.executeUpdate() > 0;
        }
    }

    // Xóa sản phẩm
    public boolean delete(int maSP) throws SQLException {
        try (Connection con = cn.connectSQL()) {
            con.setAutoCommit(false); // bật transaction
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM BIEN_THE_SAN_PHAM WHERE maSanPham = ?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM SAN_PHAM WHERE maSanPham = ?")) {

         ps1.setInt(1, maSP);
                ps1.executeUpdate();

                ps2.setInt(1, maSP);
                int rows = ps2.executeUpdate();

                con.commit();
                return rows > 0;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    // Tìm kiếm sản phẩm
    public List<SanPham> search(String keyword) throws SQLException {
        List<SanPham> list = new ArrayList<>();
        String sql = """
            SELECT * FROM SAN_PHAM
            WHERE MaSanPham LIKE ?
               OR TenSanPham LIKE ?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String key = "%" + keyword.trim() + "%";
            ps.setString(1, key);
            ps.setString(2, key);

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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm KH: " + e.getMessage());
            throw e;
        }

        return list;
    }

    // ----------------- PHẦN MỚI: LẤY DANH SÁCH NCC -----------------
    public List<Integer> getAllNCC() throws SQLException{
        List<Integer> list = new ArrayList<>();
        String sql="select MaNhaCungCap from Nha_cung_cap";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();){
            while (rs.next()){
                list.add(rs.getInt("Manhacungcap"));
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Nhập: " + e.getMessage());
        }
        return list;
    }}