package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChiTietMuaHang {
    private int MaSP;
    private String TenSP;
    private String MauSac;
    private int KichCo;
    private int SoLuong;
    private float DonGia;
    private int MaBienThe;
    
    private final Connect cn = new Connect();

    public ChiTietMuaHang() { }

    public ChiTietMuaHang(int maSP, String tenSP, String mauSac, int kichCo, int soLuong, float donGia, int maBienThe) {
        this.MaSP = maSP;
        this.TenSP = tenSP;
        this.MauSac = mauSac;
        this.KichCo = kichCo;
        this.SoLuong = soLuong;
        this.DonGia = donGia;
        this.MaBienThe = maBienThe;
    }

    // Getter & Setter
    public int getMaSP() { return MaSP; }
    public void setMaSP(int maSP) { MaSP = maSP; }

    public String getTenSP() { return TenSP; }
    public void setTenSP(String tenSP) { TenSP = tenSP; }

    public String getMauSac() { return MauSac; }
    public void setMauSac(String mauSac) { MauSac = mauSac; }

    public int getKichCo() { return KichCo; }
    public void setKichCo(int kichCo) { KichCo = kichCo; }

    public int getSoLuong() { return SoLuong; }
    public void setSoLuong(int soLuong) { SoLuong = soLuong; }

    public float getDonGia() { return DonGia; }
    public void setDonGia(float donGia) { DonGia = donGia; }

    public int getMaBienThe() { return MaBienThe; }
    public void setMaBienThe(int maBienThe) { MaBienThe = maBienThe; }

    public Connect getCn() { return cn; }

    // Lấy tất cả MaSP
    public List<Integer> getAllMaSP() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaSanPham FROM SAN_PHAM";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(rs.getInt("MaSanPham"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy danh sách MaSP: " + e.getMessage());
        }
        return list;
    }

    // Lấy tên SP theo MaSP
    public String getTenSPByMa(int maSP) {
        String sql = "SELECT TenSanPham FROM SAN_PHAM WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("TenSanPham");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy tên SP: " + e.getMessage());
        }
        return null;
    }

    // Lấy tất cả màu sắc của SP
    public List<String> getAllMauSac(int maSP) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT MauSac FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(rs.getString("MauSac"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy màu sắc: " + e.getMessage());
        }
        return list;
    }

    // Lấy tất cả kích cỡ của SP
    public List<Integer> getAllKichCo(int maSP) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT KichCo FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(rs.getInt("KichCo"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy kích cỡ: " + e.getMessage());
        }
        return list;
    }

    // Lấy MaBienThe từ MaSP, MauSac, KichCo
    public Integer getMaBienThe(int maSP, String mauSac, int kichCo) {
        String sql = "SELECT Ma FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=? AND MauSac=? AND KichCo=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.setString(2, mauSac);
            ps.setInt(3, kichCo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("Ma");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy MaBienThe: " + e.getMessage());
        }
        return null;
    }
    // Lấy MaMuaHang lớn nhất + 1 để tạo mới
public int getNewMaMuaHang() {
    String sql = "SELECT MAX(MaMuaHang) AS maxID FROM CHI_TIET_MUA_HANG";
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return rs.getInt("maxID") + 1;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Lỗi lấy MaMuaHang mới: " + e.getMessage());
    }
    return 1; // Nếu chưa có bản ghi nào
}

    // Lấy tất cả chi tiết mua hàng
    public List<ChiTietMuaHang> getAll() {
        List<ChiTietMuaHang> list = new ArrayList<>();
        String sql = "SELECT sp.MaSanPham, sp.TenSanPham, bt.MauSac, bt.KichCo, cth.SoLuong, cth.DonGia, cth.MaBienThe " +
                     "FROM CHI_TIET_MUA_HANG cth " +
                     "JOIN BIEN_THE_SAN_PHAM bt ON cth.MaBienThe = bt.Ma " +
                     "JOIN SAN_PHAM sp ON cth.MaSanPham = sp.MaSanPham";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChiTietMuaHang obj = new ChiTietMuaHang(
                        rs.getInt("MaSanPham"),
                        rs.getString("TenSanPham"),
                        rs.getString("MauSac"),
                        rs.getInt("KichCo"),
                        rs.getInt("SoLuong"),
                        rs.getFloat("DonGia"),
                        rs.getInt("MaBienThe")
                );
                list.add(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu chi tiết mua hàng: " + e.getMessage());
        }
        return list;
    }

    // Thêm chi tiết
    public boolean addChiTiet(int maSP, String mauSac, int kichCo, int soLuong, float donGia) {
    Integer maBienThe = getMaBienThe(maSP, mauSac, kichCo);
    if (maBienThe == null) {
        JOptionPane.showMessageDialog(null, "Biến thể không tồn tại!");
        return false;
    }
    int maMuaHang = getNewMaMuaHang(); // tạo MaMuaHang mới

    String sql = "INSERT INTO CHI_TIET_MUA_HANG (MaMuaHang, MaSanPham, MaBienThe, SoLuong, DonGia) VALUES (?, ?, ?, ?, ?)";
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, maMuaHang);
        ps.setInt(2, maSP);
        ps.setInt(3, maBienThe);
        ps.setInt(4, soLuong);
        ps.setFloat(5, donGia);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Lỗi thêm chi tiết: " + e.getMessage());
    }
    return false;
}


    // Sửa chi tiết
    public boolean updateChiTiet(int maSP, String mauSac, int kichCo, int soLuong, float donGia) {
        Integer maBienThe = getMaBienThe(maSP, mauSac, kichCo);
        if (maBienThe == null) {
            JOptionPane.showMessageDialog(null, "Biến thể không tồn tại!");
            return false;
        }
        String sql = "UPDATE CHI_TIET_MUA_HANG SET SoLuong=?, DonGia=? WHERE MaBienThe=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setFloat(2, donGia);
            ps.setInt(3, maBienThe);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật chi tiết: " + e.getMessage());
        }
        return false;
    }

    // Xóa chi tiết
    public boolean deleteChiTiet(int maSP, String mauSac, int kichCo) {
        Integer maBienThe = getMaBienThe(maSP, mauSac, kichCo);
        if (maBienThe == null) {
            JOptionPane.showMessageDialog(null, "Biến thể không tồn tại!");
            return false;
        }
        String sql = "DELETE FROM CHI_TIET_MUA_HANG WHERE MaBienThe=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maBienThe);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết: " + e.getMessage());
        }
        return false;
    }
    // Lấy danh sách chi tiết theo MaMuaHang
public List<ChiTietMuaHang> getByMaMH(int maMuaHang) {
    List<ChiTietMuaHang> list = new ArrayList<>();
    String sql = "SELECT cth.MaMuaHang, cth.MaSanPham, sp.TenSanPham, bt.MauSac, bt.KichCo, cth.SoLuong, cth.DonGia, cth.MaBienThe " +
                 "FROM CHI_TIET_MUA_HANG cth " +
                 "JOIN BIEN_THE_SAN_PHAM bt ON cth.MaBienThe = bt.Ma " +
                 "JOIN SAN_PHAM sp ON cth.MaSanPham = sp.MaSanPham " +
                 "WHERE cth.MaMuaHang = ?";
    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, maMuaHang);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChiTietMuaHang obj = new ChiTietMuaHang();
                // map fields (đảm bảo tên getter/setter có trong class)
                obj.setMaSP(rs.getInt("MaSanPham"));
                obj.setTenSP(rs.getString("TenSanPham"));
                obj.setMauSac(rs.getString("MauSac"));
                obj.setKichCo(rs.getInt("KichCo"));
                obj.setSoLuong(rs.getInt("SoLuong"));
                obj.setDonGia(rs.getFloat("DonGia"));
                obj.setMaBienThe(rs.getInt("MaBienThe"));
                // nếu cần lưu MaMuaHang thì bạn có thể thêm field vào class và set ở đây
                list.add(obj);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Lỗi load chi tiết theo MaMuaHang: " + e.getMessage());
    }
    return list;
}
    

}
