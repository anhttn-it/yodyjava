package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ChiTietBanHang {

    /*private int MaBanHangChiTiet;
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
}*/
    private String TenSP;
    private int MaSP;
    private String MauSac;
    private int KichCo;
    private int SoLuong;
    private float DonGia;
    private float GiamGia;
    private int MaBienThe;
    private final Connect cn=new Connect();

    public ChiTietBanHang() {
    }

    public ChiTietBanHang(String TenSP, int MaSP, String MauSac, int KichCo, int SoLuong, float DonGia) {
        this.TenSP = TenSP;
        this.MaSP = MaSP;
        this.MauSac = MauSac;
        this.KichCo = KichCo;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }


    public String getTenSP() {
        return TenSP;
    }

    public int getMaSP() {
        return MaSP;
    }

    public String getMauSac() {
        return MauSac;
    }

    public int getKichCo() {
        return KichCo;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }


    public Connect getCn() {
        return cn;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    public void setKichCo(int KichCo) {
        this.KichCo = KichCo;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public List<Integer> getallMasp() throws SQLException{
        List<Integer> listMasp = new ArrayList<>();
        String sql = "select MaSanPham from san_pham";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery()){
            while(rs.next()){
                listMasp.add(rs.getInt("MaSanPham"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Chi Tiet Phiếu Xuất: " + e.getMessage());
        }
        return listMasp;
    }
    public String setTensp(Integer masp) throws SQLException{
        String sql = "select TENSANPHAm from San_pham where masanpham =?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,masp);
            try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("tensanpham");
                }
            }
        }
        return null;
    }
    public List<String> getallmausac(Integer masp) throws SQLException{
        List<String> listmausac=new ArrayList<>();
        String sql="select mausac from bien_the_san_pham where masanpham =?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,masp);
            try(ResultSet rs=ps.executeQuery()){
                while (rs.next()){
                    listmausac.add(rs.getString("mausac"));  
                }
            }
        }
        return listmausac;
    }
     public List<Integer> getallkichco(Integer masp) throws SQLException{
        List<Integer> listkichco=new ArrayList<>();
        String sql="select kichco from bien_the_san_pham where masanpham =?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,masp);
            try(ResultSet rs=ps.executeQuery()){
                while (rs.next()){
                    listkichco.add(rs.getInt("kichco"));  
                }
            }
        }
        return listkichco;
    }
     
    public List<ChiTietBanHang> getall(int mapn) throws SQLException{
        List<ChiTietBanHang> list = new ArrayList<>();
        String sql="SELECT ctpx.SoLuong, ctpx.DonGia, " +
                   "sp.TenSanPham, bt.MauSac, bt.KichCo, ctpx.MaSanPham " +
                   "FROM CHI_TIET_Ban_Hang ctpx " +
                   "JOIN SAN_PHAM sp ON ctpx.MaSanPham = sp.MaSanPham " +
                   "JOIN BIEN_THE_SAN_PHAM bt ON bt.Ma = ctpx.MaBienThe " +
                   "WHERE ctpx.MaBanHang = ?";
        try(Connection con= cn.connectSQL();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, mapn);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    ChiTietBanHang ctpn=new ChiTietBanHang();
                    ctpn.DonGia = rs.getFloat("DonGia");
                    ctpn.KichCo = rs.getInt("KichCo");
                    ctpn.MaSP = rs.getInt("MaSanPham");
                    ctpn.MauSac = rs.getString("MauSac");
                    ctpn.SoLuong = rs.getInt("SoLuong");
                    ctpn.TenSP = rs.getString("TenSanPham");
                    list.add(ctpn);
                }
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Nhập: " + e.getMessage());
        }
        return list;
    }

    public ChiTietBanHang getCTPN(int mapn,int mabt) throws SQLException{
        String sql="SELECT ctpx.SoLuong, ctpx.DonGia, " +
                 "sp.TenSanPham, bt.MauSac, bt.KichCo, ctpx.MaSanPham " +
                 "FROM CHI_TIET_Ban_Hang ctpx " +
                 "JOIN SAN_PHAM sp ON ctpx.MaSanPham = sp.MaSanPham " +
                 "JOIN BIEN_THE_SAN_PHAM bt ON bt.Ma = ctpx.MaBienThe " +
                 "WHERE ctpx.MaBanHang = ? AND ctpx.Mabienthe = ?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1, mapn);
            ps.setInt(2, mabt);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                   ChiTietBanHang obj=new ChiTietBanHang();
                    obj.DonGia=rs.getFloat("DonGia");
                    obj.KichCo=rs.getInt("KichCo");
                    obj.MaSP=rs.getInt("MaSanPham");
                    obj.MauSac=rs.getString("MauSac");
                    obj.SoLuong=rs.getInt("SoLuong");
                    obj.TenSP=rs.getString("TenSanPham");
                    return obj; 
                }
            }
        }
        return null;
    }
    

    public Integer getMaBienThe(int maSP, String mausac, int kichco) throws SQLException {
        String sql = "SELECT Ma FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=? AND MauSac=? AND KichCo=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.setString(2, mausac);
            ps.setInt(3, kichco);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    return rs.getInt("ma");
                }
            }
        }
        return null;
    }

    public boolean addCTPN(int maPX, int maSP, String mausac, int kichco, int soluong, float dongia) throws SQLException {
        Integer maBienThe = getMaBienThe(maSP, mausac, kichco);
        if (maBienThe == null) {
            JOptionPane.showMessageDialog(null, "Biến thể không tồn tại!");
            return false;
        }
        String sql = "INSERT INTO CHI_TIET_Ban_Hang (MaBanHang, MaSanPham, MaBienThe, SoLuong, DonGia) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            ps.setInt(2, maSP);
            ps.setInt(3, maBienThe);
            ps.setInt(4, soluong);
            ps.setFloat(5, dongia);
            return ps.executeUpdate() > 0;
        }
    }


    public boolean updateCTPN(int maPX, int maSP,String mausac, int kichco, int soluong, float dongia) throws SQLException {
        Integer maBienThe = getMaBienThe(maSP, mausac, kichco);
        String sql = "UPDATE CHI_TIET_Ban_Hang SET SoLuong=?, DonGia=? WHERE MaBanHang=? AND MaBienThe=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soluong);       
            ps.setFloat(2, dongia);  
            ps.setInt(3, maPX);        
            ps.setInt(4, maBienThe);    
            return ps.executeUpdate() > 0;
        }
    }


    public boolean deleteCTPN(int maPX, int maBienThe) throws SQLException {
        String sql = "DELETE FROM CHI_TIET_Ban_Hang WHERE MaBanHang=? AND MaBienThe=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            ps.setInt(2, maBienThe);
            return ps.executeUpdate() > 0;
        }
    }

    public ChiTietBanHang getCTBH(int maPX, int mabt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
