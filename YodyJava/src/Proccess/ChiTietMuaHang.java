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
        String sql="select DISTINCT mausac from bien_the_san_pham where masanpham =?";
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
        String sql="select DISTINCT kichco from bien_the_san_pham where masanpham =?";
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
     
    public List<ChiTietMuaHang> getall(int mapn) throws SQLException{
        List<ChiTietMuaHang> list = new ArrayList<>();
        String sql="SELECT ctpx.SoLuong, ctpx.DonGia, " +
                   "sp.TenSanPham, bt.MauSac, bt.KichCo, ctpx.MaSanPham " +
                   "FROM CHI_TIET_mua_Hang ctpx " +
                   "JOIN SAN_PHAM sp ON ctpx.MaSanPham = sp.MaSanPham " +
                   "JOIN BIEN_THE_SAN_PHAM bt ON bt.Ma = ctpx.MaBienThe " +
                   "WHERE ctpx.MamuaHang = ?";
        try(Connection con= cn.connectSQL();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, mapn);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    ChiTietMuaHang ctpn=new ChiTietMuaHang();
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

    public ChiTietMuaHang getCTMH(int mapn,int mabt) throws SQLException{
        String sql="SELECT ctpx.SoLuong, ctpx.DonGia, " +
                 "sp.TenSanPham, bt.MauSac, bt.KichCo, ctpx.MaSanPham " +
                 "FROM CHI_TIET_MUA_Hang ctpx " +
                 "JOIN SAN_PHAM sp ON ctpx.MaSanPham = sp.MaSanPham " +
                 "JOIN BIEN_THE_SAN_PHAM bt ON bt.Ma = ctpx.MaBienThe " +
                 "WHERE ctpx.MaMUAHang = ? AND ctpx.Mabienthe = ?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1, mapn);
            ps.setInt(2, mabt);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                   ChiTietMuaHang obj=new ChiTietMuaHang();
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
        String sql = "INSERT INTO CHI_TIET_MUA_Hang (MaMuaHang, MaSanPham, MaBienThe, SoLuong, DonGia) VALUES (?, ?, ?, ?, ?)";
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
        String sql = "UPDATE CHI_TIET_MUA_Hang SET SoLuong=?, DonGia=? WHERE MaMuaHang=? AND MaBienThe=?";
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
        String sql = "DELETE FROM CHI_TIET_MUA_Hang WHERE MaMuaHang=? AND MaBienThe=?";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            ps.setInt(2, maBienThe);
            return ps.executeUpdate() > 0;
        }
    }


}
