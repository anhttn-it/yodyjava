package Proccess;

import Database.Connect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class NhanVien {

    private String ho;
    private String ten;
    private String gioiTinh;   // sẽ chứa "0" hoặc "1"
    private String ngaySinh;
    private String maNV;
    private String matKhau;
    private String email;
    private String diaChi;
    private String vaiTro;     // số (0 = nhân viên, 1 = admin)
    private String trangThai;
    private String ngayTao;

    private final Connect cn = new Connect();

    public NhanVien() {}

    public NhanVien(String ho, String ten, String gioiTinh, String ngaySinh,
                     String maNV, String matKhau, String email,
                     String diaChi, String vaiTro, String trangThai, String ngayTao) {

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

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getMaNhanVien() { return maNV; }
    public void setMaNhanVien(String maNV) { this.maNV = maNV; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) { this.vaiTro = vaiTro; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getNgayTao() { return ngayTao; }
    public void setNgayTao(String ngayTao) { this.ngayTao = ngayTao; }

    // ================== LẤY TẤT CẢ NHÂN VIÊN ======================
    public List<NhanVien> getAll() throws SQLException {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NHAN_VIEN WHERE XoaTaiKhoan = 0";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.ho = rs.getString("Ho");
                nv.ten = rs.getString("Ten");
                nv.gioiTinh = rs.getString("GioiTinh");
                nv.ngaySinh = rs.getString("NgaySinh");
                nv.maNV = rs.getString("MaNhanVien");
                nv.matKhau = rs.getString("MatKhau");
                nv.email = rs.getString("Email");
                nv.diaChi = rs.getString("DiaChi");
                nv.vaiTro = rs.getString("VaiTro");
                nv.trangThai = rs.getString("TrangThai");
                nv.ngayTao = rs.getString("NgayTao");

                list.add(nv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu NV: " + e.getMessage());
        }

        return list;
    }

    // ======================== THÊM ===================================
    public boolean insert(NhanVien nv) throws SQLException {
        String sql = """
            INSERT INTO NHAN_VIEN
            (Ho, Ten, Email, NgaySinh, DiaChi, GioiTinh, MatKhau, VaiTro)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nv.ho);
            ps.setString(2, nv.ten);
            ps.setString(3, nv.email);
            ps.setString(4, nv.ngaySinh);
            ps.setString(5, nv.diaChi);
            ps.setInt(6, Integer.parseInt(nv.gioiTinh)); 
            ps.setString(7, nv.matKhau);
            ps.setInt(8, Integer.parseInt(nv.vaiTro));

            return ps.executeUpdate() > 0;
        }
    }

    // ======================== SỬA ====================================
    public boolean update(NhanVien nv) throws SQLException {
        String sql = """
            UPDATE NHAN_VIEN
            SET Ho=?, Ten=?, Email=?, NgaySinh=?, DiaChi=?, GioiTinh=?, MatKhau=?, VaiTro=?, TrangThai=?
            WHERE MaNhanVien=?
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nv.ho);
            ps.setString(2, nv.ten);
            ps.setString(3, nv.email);
            ps.setString(4, nv.ngaySinh);
            ps.setString(5, nv.diaChi);
            ps.setInt(6, Integer.parseInt(nv.gioiTinh));
            ps.setString(7, nv.matKhau);
            ps.setInt(8, Integer.parseInt(nv.vaiTro));
            ps.setString(9, nv.trangThai);
            ps.setString(10, nv.maNV);

            return ps.executeUpdate() > 0;
        }
    }

    // ======================== XÓA (MỀM) ================================
    public boolean delete(String maNV) throws SQLException {
        String sql = "UPDATE NHAN_VIEN SET XoaTaiKhoan = 1 WHERE MaNhanVien=?";

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        }
    }

    // ====================== TÌM KIẾM ================================
    public List<NhanVien> search(String keyword) throws SQLException {
        List<NhanVien> list = new ArrayList<>();
        String sql = """
            SELECT * FROM NHAN_VIEN
            WHERE (Ho LIKE ? OR Ten LIKE ? OR MaNhanVien LIKE ?)
            AND XoaTaiKhoan = 0
        """;

        try (Connection con = cn.connectSQL();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();

                    nv.ho = rs.getString("Ho");
                    nv.ten = rs.getString("Ten");
                    nv.gioiTinh = rs.getString("GioiTinh");
                    nv.ngaySinh = rs.getString("NgaySinh");
                    nv.maNV = rs.getString("MaNhanVien");
                    nv.matKhau = rs.getString("MatKhau");
                    nv.email = rs.getString("Email");
                    nv.diaChi = rs.getString("DiaChi");
                    nv.vaiTro = rs.getString("VaiTro");
                    nv.trangThai = rs.getString("TrangThai");
                    nv.ngayTao = rs.getString("NgayTao");

                    list.add(nv);
                }
            }
        }

        return list;
    }
}
