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
public class PhieuXuat {
    private int maPhieuXuat;
    private Date ngayXuat;
    private int maNCC;
    private int maNV;
    private double tongTien;
    private String ghiChu;
    private final Connect cn = new Connect();
    public PhieuXuat() {
    }
    public PhieuXuat(int maPhieuXuat, Date ngayXuat, int maNCC, int maNV, double tongTien, String ghiChu) {
        this.maPhieuXuat = maPhieuXuat;
        this.ngayXuat = ngayXuat;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public int getMaPhieuXuat() {
        return maPhieuXuat;
    }
    public Date getNgayXuat() {
        return ngayXuat;
    }
    public int getMaKH() {
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
    public void setMaPhieuXuat(int maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }
    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }
    public void setMaKH(int maNCC) {
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
    public List<PhieuXuat> getAll() throws SQLException {
        List<PhieuXuat> list = new ArrayList<>();
        String sql = "SELECT * FROM PHIEU_XUAT";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhieuXuat pn = new PhieuXuat();
                pn.maPhieuXuat = rs.getInt("MaPhieuXuat");
                pn.ngayXuat = rs.getTimestamp("NgayXuat");
                pn.maNCC = rs.getInt("MaKH");
                pn.maNV = rs.getInt("MaNV");
                pn.tongTien = rs.getDouble("TongTien");
                pn.ghiChu = rs.getString("GhiChu");
                list.add(pn);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Xuất: " + e.getMessage());
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
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Xuất: " + e.getMessage());
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
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Phiếu Xuất: " + e.getMessage());
        }
        return list;
    }
    public PhieuXuat getPhieuXuat(int MaPX) throws SQLException{
        String sql = "select * from Phieu_XUAT where Maphieuxuat=?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql);){
            ps.setInt(1,MaPX);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    PhieuXuat obj = new PhieuXuat();
                    obj.ghiChu=rs.getString("GhiChu");
                    obj.maNCC=rs.getInt("MaKH");
                    obj.maNV=rs.getInt("MaNV");
                    obj.maPhieuXuat=rs.getInt("MaPhieuXuat");
                    obj.ngayXuat=rs.getTimestamp("NgayXuat");
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
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM CHI_TIET_PHIEU_NHAP WHERE MaPhieuXuat = ?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM PHIEU_NHAP WHERE MaPhieuXuat = ?")) {

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
    public boolean InsertData(PhieuXuat obj) throws SQLException{
        String sql="INSERT INTO PHIEU_XUAT (NgayXuat, MaKH, MaNV, TongTien, GhiChu) VALUES (?, ?, ?, ?, ?)";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql)){
            ps.setTimestamp(1, new java.sql.Timestamp(obj.ngayXuat.getTime()));
            ps.setInt(2, obj.maNCC);
            ps.setInt(3, obj.maNV);
            ps.setDouble(4, obj.tongTien);
            ps.setString(5, obj.ghiChu);
            return ps.executeUpdate()>0;
        }
    }
    public boolean EditData(PhieuXuat obj) throws SQLException{
        String sql="update Phieu_Xuat set NgayXuat=?, MaKH=?,MaNV=?,TongTien=?,GhiChu=? where MaPhieuxuat=?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setTimestamp(1,new java.sql.Timestamp(obj.ngayXuat.getTime()));
            ps.setInt(2, obj.maNCC);
            ps.setInt(3, obj.maNV);
            ps.setDouble(4, obj.tongTien);
            ps.setString(5, obj.ghiChu);
            ps.setInt(6,obj.maPhieuXuat);
            return ps.executeUpdate()>0;
        }
    }

}
