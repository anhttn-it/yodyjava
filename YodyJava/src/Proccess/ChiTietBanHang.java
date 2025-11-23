package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChiTietBanHang {

    private int MaBanHangChiTiet;
    private int MaBanHang;
    private int MaSanPham;
    private int MaBienThe;  // Biến thể sản phẩm
    private int SoLuong;
    private double DonGia;

    private final Connect cn = new Connect();

    public ChiTietBanHang() {}

    public ChiTietBanHang(int MaBanHangChiTiet, int MaBanHang, int MaSanPham, int MaBienThe, int SoLuong, double DonGia) {
        this.MaBanHangChiTiet = MaBanHangChiTiet;
        this.MaBanHang = MaBanHang;
        this.MaSanPham = MaSanPham;
        this.MaBienThe = MaBienThe;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    // ==============================
    // Getter & Setter
    // ==============================
    public int getMaBanHangChiTiet() { return MaBanHangChiTiet; }
    public void setMaBanHangChiTiet(int MaBanHangChiTiet) { this.MaBanHangChiTiet = MaBanHangChiTiet; }

    public int getMaBanHang() { return MaBanHang; }
    public void setMaBanHang(int MaBanHang) { this.MaBanHang = MaBanHang; }

    public int getMaSanPham() { return MaSanPham; }
    public void setMaSanPham(int MaSanPham) { this.MaSanPham = MaSanPham; }

    public int getMaBienThe() { return MaBienThe; }
    public void setMaBienThe(int MaBienThe) { this.MaBienThe = MaBienThe; }

    public int getSoLuong() { return SoLuong; }
    public void setSoLuong(int SoLuong) { this.SoLuong = SoLuong; }

    public double getDonGia() { return DonGia; }
    public void setDonGia(double DonGia) { this.DonGia = DonGia; }

    // ==============================
    // Lấy danh sách chi tiết theo MaBanHang
    // ==============================
    public List<ChiTietBanHang> getListByMaBanHang(int maBanHang) {
        List<ChiTietBanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_BAN_HANG WHERE MaBanHang=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maBanHang);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChiTietBanHang ct = new ChiTietBanHang();
                ct.MaBanHangChiTiet = rs.getInt("MaBanHangChiTiet");
                ct.MaBanHang = rs.getInt("MaBanHang");
                ct.MaSanPham = rs.getInt("MaSanPham");
                ct.MaBienThe = rs.getInt("MaBienThe");
                ct.SoLuong = rs.getInt("SoLuong");
                ct.DonGia = rs.getDouble("DonGia");
                list.add(ct);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc chi tiết bán hàng: " + e.getMessage());
        }
        return list;
    }

    // ==============================
    // Thêm chi tiết bán hàng
    // ==============================
    public boolean addChiTiet() {
        String sql = "INSERT INTO CHI_TIET_BAN_HANG (MaBanHang, MaSanPham, MaBienThe, SoLuong, DonGia) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, MaBanHang);
            ps.setInt(2, MaSanPham);
            ps.setInt(3, MaBienThe);
            ps.setInt(4, SoLuong);
            ps.setDouble(5, DonGia);

            int affected = ps.executeUpdate();
            if (affected > 0) {
                ResultSet key = ps.getGeneratedKeys();
                if (key.next()) {
                    this.MaBanHangChiTiet = key.getInt(1);
                }
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm chi tiết bán hàng: " + e.getMessage());
        }
        return false;
    }

    // ==============================
    // Cập nhật chi tiết bán hàng
    // ==============================
    public boolean updateChiTiet() {
        String sql = "UPDATE CHI_TIET_BAN_HANG SET MaSanPham=?, MaBienThe=?, SoLuong=?, DonGia=? WHERE MaBanHangChiTiet=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaSanPham);
            ps.setInt(2, MaBienThe);
            ps.setInt(3, SoLuong);
            ps.setDouble(4, DonGia);
            ps.setInt(5, MaBanHangChiTiet);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật chi tiết bán hàng: " + e.getMessage());
            return false;
        }
    }

    // ==============================
    // Xóa chi tiết bán hàng
    // ==============================
    public boolean deleteChiTiet() {
        String sql = "DELETE FROM CHI_TIET_BAN_HANG WHERE MaBanHangChiTiet=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaBanHangChiTiet);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết bán hàng: " + e.getMessage());
            return false;
        }
    }

    // ==============================
    // Lấy danh sách Mã sản phẩm
    // ==============================
    public List<Integer> getListMaSanPham() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaSanPham FROM SAN_PHAM";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getInt("MaSanPham"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi load danh sách sản phẩm: " + e.getMessage());
        }
        return list;
    }

    // ==============================
    // Lấy danh sách Biến thể theo SP
    // ==============================
    public List<Integer> getListMaBienThe(int maSanPham) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT ma FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maSanPham);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("ma"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi load danh sách biến thể: " + e.getMessage());
        }
        return list;
    }

    // ==============================
    // Lấy tên màu từ biến thể
    // ==============================
    public String getMauSacByBienThe(int maBienThe) {
        String mau = "";
        String sql = "SELECT MauSac FROM BIEN_THE_SAN_PHAM WHERE ma=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maBienThe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mau = rs.getString("MauSac");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy màu sắc: " + e.getMessage());
        }
        return mau;
    }

    // ==============================
    // Lấy kích cỡ từ biến thể
    // ==============================
    public int getKichCoByBienThe(int maBienThe) {
        int size = 0;
        String sql = "SELECT KichCo FROM BIEN_THE_SAN_PHAM WHERE ma=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maBienThe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                size = rs.getInt("KichCo");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy kích cỡ: " + e.getMessage());
        }
        return size;
    }
    // Lấy danh sách màu của 1 sản phẩm
public List<String> getListMauBySanPham(int maSanPham) {
    List<String> list = new ArrayList<>();
    String sql = "SELECT DISTINCT MauSac FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, maSanPham);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(rs.getString("MauSac"));
        }
    } catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Lỗi lấy danh sách màu: " + e.getMessage());
    }
    return list;
}

// Lấy danh sách kích cỡ của 1 sản phẩm
public List<Integer> getListKichCoBySanPham(int maSanPham) {
    List<Integer> list = new ArrayList<>();
    String sql = "SELECT DISTINCT KichCo FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, maSanPham);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(rs.getInt("KichCo"));
        }
    } catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Lỗi lấy danh sách kích cỡ: " + e.getMessage());
    }
    return list;
}

// Lấy MaBienThe dựa vào sản phẩm, màu, kích cỡ
public Integer getMaBienThe(int maSP, String mauSac, int kichCo){
    Integer maBienThe = null;
    String sql = "SELECT ma FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=? AND MauSac=? AND KichCo=?";
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, maSP);
        ps.setString(2, mauSac);
        ps.setInt(3, kichCo);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            maBienThe = rs.getInt("ma");
        }
    } catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Lỗi lấy biến thể: " + e.getMessage());
    }
    return maBienThe;
}
// Lấy 1 chi tiết bán hàng theo MaBanHang và MaBienThe
public ChiTietBanHang getChiTiet(int maBanHang, int maBienThe) {
    ChiTietBanHang ct = null;
    String sql = "SELECT * FROM CHI_TIET_BAN_HANG WHERE MaBanHang=? AND MaBienThe=?";
    
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, maBanHang);
        ps.setInt(2, maBienThe);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            ct = new ChiTietBanHang();
            ct.setMaBanHangChiTiet(rs.getInt("MaBanHangChiTiet"));
            ct.setMaBanHang(rs.getInt("MaBanHang"));
            ct.setMaSanPham(rs.getInt("MaSanPham"));
            ct.setMaBienThe(rs.getInt("MaBienThe"));
            ct.setSoLuong(rs.getInt("SoLuong"));
            ct.setDonGia(rs.getDouble("DonGia"));
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi lấy chi tiết: " + e.getMessage());
    }
    return ct;
}

}
