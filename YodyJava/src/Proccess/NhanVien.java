package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class NhanVien {

    private String ho;
    private String ten;
    private int gioiTinh;   // sẽ chứa "0" hoặc "1"
    private Date ngaySinh;
    private int maNV;
    private String matKhau;
    private String email;
    private String diaChi;
    private int vaiTro;     // số (0 = nhân viên, 1 = admin)
    private int trangThai;
    private Date ngayTao;

    private final Connect cn = new Connect();

    public NhanVien() {}

    public NhanVien(String ho, String ten, int gioiTinh, Date ngaySinh,
                     int maNV, String matKhau, String email,
                     String diaChi, int vaiTro, int trangThai, Date ngayTao) {

        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.email = email;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    // ===== GETTER & SETTER =====
    public String getHo() { return ho; }
    public void setHo(String ho) { this.ho = ho; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public int getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(int gioiTinh) { this.gioiTinh = gioiTinh; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public int getMaNhanVien() { return maNV; }
    public void setMaNhanVien(int maNV) { this.maNV = maNV; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public int getVaiTro() { return vaiTro; }
    public void setVaiTro(int vaiTro) { this.vaiTro = vaiTro; }

    public int getTrangThai() { return trangThai; }
    public void setTrangThai(int trangThai) { this.trangThai = trangThai; }

    public Date getNgayTao() { return ngayTao; }
    public void setNgayTao(Date ngayTao) { this.ngayTao = ngayTao; }

    // ================== LẤY TẤT CẢ NHÂN VIÊN ======================
    public List<NhanVien> getAll() throws SQLException {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NHAN_VIEN";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.ho = rs.getString("Ho");
                nv.ten = rs.getString("Ten");
                nv.gioiTinh = rs.getInt("GioiTinh");
                nv.ngaySinh = rs.getTimestamp("NgaySinh");
                nv.maNV = rs.getInt("MaNhanVien");
                nv.matKhau = rs.getString("MatKhau");
                nv.email = rs.getString("Email");
                nv.diaChi = rs.getString("DiaChi");
                nv.vaiTro = rs.getInt("VaiTro");
                nv.trangThai = rs.getInt("TrangThai");
                nv.ngayTao = rs.getTimestamp("NgayTao");

                list.add(nv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu NV: " + e.getMessage());
        }

        return list;
    }
public NhanVien getNhanVien (int MaNV) throws SQLException {
        String sql = "SELECT * FROM NHAN_VIEN WHERE MaNhanVien=?";
        try (Connection con = cn.connectSQL();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1,MaNV);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    NhanVien obj = new NhanVien();
                    obj.maNV = rs.getInt("MaNhanVien");     
                    obj.ho = rs.getString("Ho");
                    obj.ten = rs.getString("Ten");
                    obj.email = rs.getString("Email");
                    obj.ngaySinh = rs.getTimestamp("NgaySinh");
                    obj.diaChi = rs.getString("DiaChi");
                    obj.gioiTinh = rs.getInt("GioiTinh");
                    obj.matKhau = rs.getString("MatKhau");
                    obj.vaiTro = rs.getInt("VaiTro");
                    obj.trangThai = rs.getInt("TrangThai");
                    obj.ngayTao = rs.getTimestamp("NgayTao");
                    return obj;
                }
            }
        }
        return null;
    }
    // ======================== THÊM ===================================
    public boolean insert(NhanVien obj) throws SQLException {
        String sql = """
            INSERT INTO NHAN_VIEN
            (Ho, Ten, Email, NgaySinh, DiaChi, GioiTinh, MatKhau, VaiTro, TrangThai, NgayTao)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.ho);
            ps.setString(2, obj.ten);
            ps.setString(3, obj.email);
            ps.setTimestamp(4, new java.sql.Timestamp(obj.ngaySinh.getTime()));
            ps.setString(5, obj.diaChi);
            ps.setInt(6, obj.gioiTinh);
            ps.setString(7, obj.matKhau);
            ps.setInt(8, obj.vaiTro);
            ps.setInt(9, obj.trangThai);
            ps.setTimestamp(10, new java.sql.Timestamp(obj.ngayTao.getTime()));

            return ps.executeUpdate() > 0;
        }
        
    }

    // ======================== SỬA ====================================
    public boolean update(NhanVien obj) throws SQLException {
        String sql = """
            UPDATE NHAN_VIEN
            SET Ho=?, Ten=?, Email=?, NgaySinh=?, DiaChi=?, GioiTinh=?, MatKhau=?, VaiTro=?, TrangThai=?
            WHERE MaNhanVien=?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.ho);
            ps.setString(2, obj.ten);
            ps.setString(3, obj.email);
            ps.setTimestamp(4,new java.sql.Timestamp(obj.ngaySinh.getTime()));
            ps.setString(5, obj.diaChi);
            ps.setInt(6, obj.gioiTinh);
            ps.setString(7, obj.matKhau);
            ps.setInt(8, obj.vaiTro);
            ps.setInt(9, obj.trangThai);
            ps.setInt(10, obj.maNV);

            return ps.executeUpdate() > 0;
        }
        
    }

    // ======================== XÓA THẬT ================================
    public boolean delete(int maNV) throws SQLException  {
        try (Connection con = cn.connectSQL()) {
            con.setAutoCommit(false); // bật transaction
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM NHAN_VIEN WHERE MaNhanVien = ?"))
                    {
                ps.setInt(1, maNV);
                int rows = ps.executeUpdate();

                con.commit();
                return rows > 0;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    // ====================== TÌM KIẾM ================================
    public List<NhanVien> search(String keyword) throws SQLException {
        List<NhanVien> list = new ArrayList<>();
        String sql = """
            SELECT * FROM NHAN_VIEN
            WHERE Ho LIKE ? OR Ten LIKE ? OR MaNhanVien LIKE ?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();

                nv.setHo(rs.getString("Ho"));
                nv.setTen(rs.getString("Ten"));
                nv.setGioiTinh(rs.getInt("GioiTinh"));
                nv.setNgaySinh(rs.getTimestamp("NgaySinh"));
                nv.setMaNhanVien(rs.getInt("MaNhanVien"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setEmail (rs.getString("Email"));
                nv.setDiaChi (rs.getString("DiaChi"));
                nv.setVaiTro (rs.getInt("VaiTro"));
                nv.setTrangThai (rs.getInt("TrangThai"));
                nv.setNgayTao(rs.getTimestamp("NgayTao"));

                list.add(nv);
            }
        }
    }

    return list;
}
}
