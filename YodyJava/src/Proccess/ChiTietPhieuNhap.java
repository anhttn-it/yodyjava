/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proccess;

import Database.Connect;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author ngocanh
 */
public class ChiTietPhieuNhap {
    private String TenSP;
    private int MaSP;
    private String MauSac;
    private int KichCo;
    private int SoLuong;
    private float DonGia;
    private float GiamGia;
    private final Connect cn=new Connect();

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(String TenSP, int MaSP, String MauSac, int KichCo, int SoLuong, float DonGia, float GiamGia) {
        this.TenSP = TenSP;
        this.MaSP = MaSP;
        this.MauSac = MauSac;
        this.KichCo = KichCo;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.GiamGia = GiamGia;
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

    public float getGiamGia() {
        return GiamGia;
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

    public void setGiamGia(float GiamGia) {
        this.GiamGia = GiamGia;
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
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Chi Tiet Phiếu Nhập: " + e.getMessage());
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
            
    public List<ChiTietPhieuNhap> getall(int mapn) throws SQLException{
        List<ChiTietPhieuNhap> list = new ArrayList<>();
        String sql="SELECT ctpn.SoLuong, ctpn.DonGia, sp.GiamGia, " +
                 "sp.TenSanPham, bt.MauSac, bt.KichCo, ctpn.MaSanPham " +
                 "FROM CHI_TIET_PHIEU_NHAP ctpn " +
                 "JOIN SAN_PHAM sp ON ctpn.MaSanPham = sp.MaSanPham " +
                 "JOIN BIEN_THE_SAN_PHAM bt ON bt.Ma = ctpn.MaBienThe " +
                 "WHERE ctpn.MaPhieuNhap = ?";
        try(Connection con= cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, mapn); // gán giá trị cho parameter
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    ChiTietPhieuNhap ctpn=new ChiTietPhieuNhap();
                    ctpn.DonGia=rs.getFloat("DonGia");
                    ctpn.GiamGia=rs.getFloat("GiamGia");
                    ctpn.KichCo=rs.getInt("KichCo");
                    ctpn.MaSP=rs.getInt("MaSanPham");
                    ctpn.MauSac=rs.getString("MauSac");
                    ctpn.SoLuong=rs.getInt("SoLuong");
                    ctpn.TenSP=rs.getString("TenSanPham");
                    list.add(ctpn);
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Nhập: " + e.getMessage());
        }
        return list;
    }
    public ChiTietPhieuNhap getCTPN(int ma) throws SQLException{
        String sql="SELECT ctpn.SoLuong, ctpn.DonGia, sp.GiamGia, " +
                 "sp.TenSanPham, bt.MauSac, bt.KichCo, ctpn.MaSanPham " +
                 "FROM CHI_TIET_PHIEU_NHAP ctpn " +
                 "JOIN SAN_PHAM sp ON ctpn.MaSanPham = sp.MaSanPham " +
                 "JOIN BIEN_THE_SAN_PHAM bt ON bt.Ma = ctpn.MaBienThe " +
                 "WHERE ctpn.MaSanPham = ?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1, ma);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                   ChiTietPhieuNhap obj=new ChiTietPhieuNhap();
                    obj.DonGia=rs.getFloat("DonGia");
                    obj.GiamGia=rs.getFloat("GiamGia");
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
    
}
//
