package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Date;

public class BanHang {
    private int maBanHang;
    private Date ngayBH;
    private int maKH;
    private int maNV;
    private double tongTien;
    private String ghiChu;
    private final Connect cn = new Connect();
    public BanHang() {
    }
    public BanHang(int maBanHang,Date ngayBH, int maKH, int maNV, double tongTien, String ghiChu) {
        this.maBanHang = maBanHang;
        this.ngayBH = ngayBH;
        this.maKH = maKH;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public int getMaBanHang() {
        return maBanHang;
    }
    public Date getNgayBH() {
        return ngayBH;
    }
    public int getMaKH() {
        return maKH;
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
    public void setMaBanHang(int maBanHang) {
        this.maBanHang = maBanHang;
    }
    public void setNgayBH(Date ngayBH) {
        this.ngayBH = ngayBH;
    }
    public void setMaKH(int maKH) {
        this.maKH = maKH;
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
    public List<BanHang> getAll() throws SQLException {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM Ban_Hang";
        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BanHang pn = new BanHang();
                pn.maBanHang = rs.getInt("MaBanHang");
                pn.ngayBH = rs.getTimestamp("NgayDatHang");
                pn.maKH = rs.getInt("MaKhachHang");
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
    public List<Integer> getAllKH() throws SQLException{
        List<Integer> list = new ArrayList<>();
        String sql="select MaKhachHang from Khach_hang";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();){
            while (rs.next()){
                list.add(rs.getInt("MaKhachHang"));
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu BH: " + e.getMessage());
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
    public BanHang getBanHang(int MaPX) throws SQLException{
        String sql = "select * from Ban_Hang where MaBanHang=?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql);){
            ps.setInt(1,MaPX);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    BanHang obj = new BanHang();
                    obj.ghiChu=rs.getString("GhiChu");
                    obj.maKH=rs.getInt("MaKhachHang");
                    obj.maNV=rs.getInt("MaNV");
                    obj.maBanHang=rs.getInt("MaBanHang");
                    obj.ngayBH=rs.getTimestamp("NgayDatHang");
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
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM CHI_TIET_Ban_Hang WHERE MaBanHang = ?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM Ban_Hang WHERE MaBanHang = ?")) {

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
    public boolean InsertData(BanHang obj) throws SQLException{
        String sql="INSERT INTO Ban_Hang (NgayDatHang, MaKhachHang, MaNV, TongTien, GhiChu) VALUES (?, ?, ?, ?, ?)";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps=con.prepareStatement(sql)){
            ps.setTimestamp(1, new java.sql.Timestamp(obj.ngayBH.getTime()));
            ps.setInt(2, obj.maKH);
            ps.setInt(3, obj.maNV);
            ps.setDouble(4, obj.tongTien);
            ps.setString(5, obj.ghiChu);
            return ps.executeUpdate()>0;
        }
    }
    public boolean EditData(BanHang obj) throws SQLException{
        String sql="update Ban_Hang set NgayDatHang=?, MaKhachHang=?,MaNV=?,TongTien=?,GhiChu=? where MaBanHang=?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setTimestamp(1,new java.sql.Timestamp(obj.ngayBH.getTime()));
            ps.setInt(2, obj.maKH);
            ps.setInt(3, obj.maNV);
            ps.setDouble(4, obj.tongTien);
            ps.setString(5, obj.ghiChu);
            ps.setInt(6,obj.maBanHang);
            return ps.executeUpdate()>0;
        }
    }
    
    public List<BanHang> TimKiemPX(String keyword) throws SQLException {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM Ban_Hang WHERE maBanHang LIKE ?";
        try(Connection con=cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql)){
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            BanHang px = new BanHang();
            px.setMaBanHang(rs.getInt("MaBanHang"));
            px.setNgayBH(rs.getTimestamp("NgayDatHang"));
            px.setMaKH(rs.getInt("MaKhachHang"));
            px.setMaNV(rs.getInt("MaNV"));
            px.setTongTien(rs.getFloat("TongTien"));
            px.setGhiChu(rs.getString("GhiChu"));
            list.add(px);
        }
        return list;
        }
    }


}
