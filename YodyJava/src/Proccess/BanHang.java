package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BanHang {
/*
    private int MaBanHang;
    private Timestamp NgayDatHang;
    private int MaKhachHang;
    private int MaNV;
    private double TongTien;
    private String GhiChu;

    private final Connect cn = new Connect();

    public BanHang() {}

    public BanHang(int MaBanHang, Timestamp NgayDatHang, int MaKhachHang, int MaNV, double TongTien, String GhiChu) {
        this.MaBanHang = MaBanHang;
        this.NgayDatHang = NgayDatHang;
        this.MaKhachHang = MaKhachHang;
        this.MaNV = MaNV;
        this.TongTien = TongTien;
        this.GhiChu = GhiChu;
    }

    // ===================== GET - SET =====================
    public int getMaBanHang() { return MaBanHang; }
    public void setMaBanHang(int MaBanHang) { this.MaBanHang = MaBanHang; }

    public Timestamp getNgayDatHang() { return NgayDatHang; }
    public void setNgayDatHang(Timestamp NgayDatHang) { this.NgayDatHang = NgayDatHang; }

    public int getMaKhachHang() { return MaKhachHang; }
    public void setMaKhachHang(int MaKhachHang) { this.MaKhachHang = MaKhachHang; }

    public int getMaNV() { return MaNV; }
    public void setMaNV(int MaNV) { this.MaNV = MaNV; }

    public double getTongTien() { return TongTien; }
    public void setTongTien(double TongTien) { this.TongTien = TongTien; }

    public String getGhiChu() { return GhiChu; }
    public void setGhiChu(String GhiChu) { this.GhiChu = GhiChu; }

    // ===================== LẤY TẤT CẢ ĐƠN BÁN =====================
    public List<BanHang> getAllBanHang() {
        List<BanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM BAN_HANG ORDER BY MaBanHang DESC";

        try (Connection con = cn.connectSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                BanHang bh = new BanHang();
                bh.MaBanHang = rs.getInt("MaBanHang");
                bh.NgayDatHang = rs.getTimestamp("NgayDatHang");
                bh.MaKhachHang = rs.getInt("MaKhachHang");
                bh.MaNV = rs.getInt("MaNV");
                bh.TongTien = rs.getDouble("TongTien");
                bh.GhiChu = rs.getString("GhiChu");

                list.add(bh);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc danh sách Bán hàng: " + e.getMessage());
        }

        return list;
    }

    // ===================== THÊM =====================
    public boolean addBanHang() {
        String sql = """
            INSERT INTO BAN_HANG (NgayDatHang, MaKhachHang, MaNV, TongTien, GhiChu)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, NgayDatHang);
            ps.setInt(2, MaKhachHang);
            ps.setInt(3, MaNV);
            ps.setDouble(4, TongTien);
            ps.setString(5, GhiChu);

            int kq = ps.executeUpdate();
            if (kq > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.MaBanHang = rs.getInt(1);
                }
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm Bán hàng: " + e.getMessage());
        }

        return false;
    }

    // ===================== SỬA =====================
    public boolean updateBanHang() {
        String sql = """
            UPDATE BAN_HANG
            SET NgayDatHang=?, MaKhachHang=?, MaNV=?, TongTien=?, GhiChu=?
            WHERE MaBanHang=?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, NgayDatHang);
            ps.setInt(2, MaKhachHang);
            ps.setInt(3, MaNV);
            ps.setDouble(4, TongTien);
            ps.setString(5, GhiChu);
            ps.setInt(6, MaBanHang);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật Bán hàng: " + e.getMessage());
        }
        return false;
    }

    // ===================== XÓA =====================
    public boolean deleteBanHang() {
        String sql = "DELETE FROM BAN_HANG WHERE MaBanHang=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaBanHang);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa Bán hàng: " + e.getMessage());
        }

        return false;
    }

    // ===================== LẤY CHI TIẾT ĐƠN BÁN =====================
    public List<ChiTietBanHang> getChiTietBanHang() {
        List<ChiTietBanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM CHI_TIET_BAN_HANG WHERE MaBanHang=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, MaBanHang);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietBanHang ct = new ChiTietBanHang();
                ct.setMaBanHangChiTiet(rs.getInt("MaBanHangChiTiet"));
                ct.setMaBanHang(rs.getInt("MaBanHang"));
                ct.setMaSanPham(rs.getInt("MaSanPham"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setDonGia(rs.getDouble("DonGia"));
                ct.setMaBienThe(rs.getInt("MaBienThe"));

                list.add(ct);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc chi tiết Bán hàng: " + e.getMessage());
        }

        return list;
    }

    // ===================== LẤY DANH SÁCH MÃ KHÁCH HÀNG =====================
    public List<Integer> getAllKH() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaKhachHang FROM KHACH_HANG";

        try (Connection con = cn.connectSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(rs.getInt(1));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy danh sách khách hàng: " + e.getMessage());
        }

        return list;
    }

    // ===================== LẤY DANH SÁCH MÃ NHÂN VIÊN =====================
    public List<Integer> getAllMNV() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaNhanVien FROM NHAN_VIEN";

        try (Connection con = cn.connectSQL();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(rs.getInt(1));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy danh sách nhân viên: " + e.getMessage());
        }

        return list;
    }
    // ===================== LẤY 1 ĐƠN BÁN THEO MÃ =====================
public BanHang getBanHang(int maBanHang) {
    BanHang bh = null;
    String sql = "SELECT * FROM BAN_HANG WHERE MaBanHang = ?";

    try (Connection con = cn.connectSQL();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, maBanHang);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            bh = new BanHang();
            bh.setMaBanHang(rs.getInt("MaBanHang"));
            bh.setNgayDatHang(rs.getTimestamp("NgayDatHang"));
            bh.setMaKhachHang(rs.getInt("MaKhachHang"));
            bh.setMaNV(rs.getInt("MaNV"));
            bh.setTongTien(rs.getDouble("TongTien"));
            bh.setGhiChu(rs.getString("GhiChu"));
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi đọc đơn bán hàng: " + e.getMessage());
    }

    return bh;
}*/
    private int maBanHang;
    private java.util.Date ngayBH;
    private int maNCC;
    private int maNV;
    private double tongTien;
    private String ghiChu;
    private final Connect cn = new Connect();
    public BanHang() {
    }
    public BanHang(int maBanHang, java.util.Date ngayBH, int maNCC, int maNV, double tongTien, String ghiChu) {
        this.maBanHang = maBanHang;
        this.ngayBH = ngayBH;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public int getMaBanHang() {
        return maBanHang;
    }
    public java.util.Date getNgayBH() {
        return ngayBH;
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
    public void setMaBanHang(int maBanHang) {
        this.maBanHang = maBanHang;
    }
    public void setNgayBH(java.util.Date ngayXuat) {
        this.ngayBH = ngayBH;
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
                pn.maNCC = rs.getInt("MaKhachHang");
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
                    obj.maNCC=rs.getInt("MaKhachHang");
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
            ps.setInt(2, obj.maNCC);
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
            ps.setInt(2, obj.maNCC);
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
