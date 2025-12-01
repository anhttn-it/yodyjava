/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proccess;

import java.sql.*;
import Database.Connect;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ngocanh
 */
public class PhieuNhap {
    private int maMuaHang;
    private int maPhieuNhap;
    private Date ngayNhap;
    private int maNCC;
    private int maNV;
    private double tongTien;
    private String ghiChu;
    private final Connect cn = new Connect();
    public PhieuNhap() {
    }

    public PhieuNhap(int maMuaHang, int maPhieuNhap, Date ngayNhap, int maNCC, int maNV, double tongTien, String ghiChu) {
        this.maMuaHang = maMuaHang;
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public int getMaMuaHang() {
        return maMuaHang;
    }
    

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }
    public Date getNgayNhap() {
        return ngayNhap;
    }
    public int getMaNCC() {
        return maNCC;
    }
    public int getMaNV() {
        return maNV;
    }
    public double getTongTien() {
        return tongTien;
    }
    public String getGhiChu() {
        return ghiChu;
    }

    public void setMaMuaHang(int maMuaHang) {
        this.maMuaHang = maMuaHang;
    }
    
    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }
    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    public List<PhieuNhap> getAll() throws SQLException {
        List<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM PHIEU_NHAP";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.maMuaHang = rs.getInt("MaMuaHang");
                pn.maPhieuNhap = rs.getInt("MaPhieuNhap");
                pn.ngayNhap = rs.getTimestamp("NgayNhap");
                pn.maNCC = rs.getInt("MaNCC");
                pn.maNV = rs.getInt("MaNV");
                pn.tongTien = rs.getDouble("TongTien");
                pn.ghiChu = rs.getString("GhiChu");
                list.add(pn);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Nhập: " + e.getMessage());
        }
        return list;
    }
    public List<Integer> getmamh() throws SQLException{
        List<Integer> list = new ArrayList<>();
        String sql = " select MAMUAHANG FROM MUA_HANG";
        try(Connection con= cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                list.add(rs.getInt("Mamuahang"));
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Nhập: " + e.getMessage());
        }
        return list;
    }
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
    }
    public List<Integer> getAllMNV() throws SQLException{
        List<Integer> list=new ArrayList<>();
        String sql="select Manhanvien from Nhan_vien";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery()){
            while (rs.next()){
                list.add(rs.getInt("manhanvien"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Nhập: " + e.getMessage());
        }
        return list;
    }
    public PhieuNhap getPhieuNhap(int MaPN) throws SQLException{
        String sql = "select * from Phieu_nhap where Maphieunhap=?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql);){
            ps.setInt(1,MaPN);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    PhieuNhap obj = new PhieuNhap();
                    obj.maMuaHang = rs.getInt("MaMuaHang");
                    obj.ghiChu=rs.getString("GhiChu");
                    obj.maNCC=rs.getInt("MaNCC");
                    obj.maNV=rs.getInt("MaNV");
                    obj.maPhieuNhap=rs.getInt("MaPhieuNhap");
                    obj.ngayNhap=rs.getTimestamp("NgayNhap");
                    obj.tongTien=rs.getFloat("TongTien");
                    return obj;
                }
            }
        }
        return null;
    } 
    public boolean deleteData(int maPN) throws SQLException {
        try (Connection con = cn.connectSQL()) {
            con.setAutoCommit(false); // bật transaction
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM CHI_TIET_PHIEU_NHAP WHERE MaPhieuNhap = ?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM PHIEU_NHAP WHERE MaPhieuNhap = ?")) {

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
    public boolean InsertData(PhieuNhap obj) throws SQLException{
        String sql="INSERT INTO PHIEU_NHAP (MaMuaHang, NgayNhap, MaNCC, MaNV, TongTien, GhiChu) VALUES (?,?, ?, ?, ?, ?)";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1, obj.maMuaHang);
            ps.setTimestamp(2, new java.sql.Timestamp(obj.ngayNhap.getTime()));
            ps.setInt(3, obj.maNCC);
            ps.setInt(4, obj.maNV);
            ps.setDouble(5, obj.tongTien);
            ps.setString(6, obj.ghiChu);
            return ps.executeUpdate()>0;
        }
    }
    public boolean EditData(PhieuNhap obj) throws SQLException{
        String sql="update Phieu_nhap set NgayNhap=?, MaNCC=?,MaNV=?,TongTien=?,GhiChu=? where MaPhieunhap=?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setTimestamp(1,new java.sql.Timestamp(obj.ngayNhap.getTime()));
            ps.setInt(2, obj.maNCC);
            ps.setInt(3, obj.maNV);
            ps.setDouble(4, obj.tongTien);
            ps.setString(5, obj.ghiChu);
            ps.setInt(6,obj.maPhieuNhap);
            return ps.executeUpdate()>0;
        }
    }

    
    public List<PhieuNhap> TimKiemPN(String keyword) throws SQLException {
        List<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM PHIEU_Nhap WHERE MaMuaHang LIKE ?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql)){
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            PhieuNhap pn = new PhieuNhap();
            pn.maMuaHang = rs.getInt("MaMuaHang");
            pn.setMaPhieuNhap(rs.getInt("MaPhieuNhap"));
            pn.setNgayNhap(rs.getTimestamp("NgayNhap"));
            pn.setMaNCC(rs.getInt("MaNCC"));
            pn.setMaNV(rs.getInt("MaNV"));
            pn.setTongTien(rs.getFloat("TongTien"));
            pn.setGhiChu(rs.getString("GhiChu"));
            list.add(pn);
        }
        return list;
        }
    }
}
