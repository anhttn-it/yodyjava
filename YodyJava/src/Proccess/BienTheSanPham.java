    package Proccess;

    import Database.Connect;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
    import javax.swing.JOptionPane;

    public class BienTheSanPham {
        private int ma;         // identity
        private int maSP;
        private String mauSac;
        private int kichCo;     // INT
        private final Connect cn = new Connect();

        public BienTheSanPham() {}

        public BienTheSanPham(int ma, int maSP, String mauSac, int kichCo) {
            this.ma = ma;
            this.maSP = maSP;
            this.mauSac = mauSac;
            this.kichCo = kichCo;
        }

        // ===== Getters & Setters =====
        public int getMa() { return ma; }
        public void setMa(int ma) { this.ma = ma; }

        public int getMaSP() { return maSP; }
        public void setMaSP(int maSP) { this.maSP = maSP; }

        public String getMauSac() { return mauSac; }
        public void setMauSac(String mauSac) { this.mauSac = mauSac; }

        public int getKichCo() { return kichCo; }
        public void setKichCo(int kichCo) { this.kichCo = kichCo; }

        // ===== Lấy danh sách biến thể theo MaSP =====
        public List<BienTheSanPham> getListByMaSP(int maSP) {
            List<BienTheSanPham> list = new ArrayList<>();
            String sql = "SELECT * FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";
            try (Connection con = cn.connectSQL();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, maSP);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        BienTheSanPham bt = new BienTheSanPham();
                        bt.ma = rs.getInt("ma");
                        bt.maSP = rs.getInt("MaSanPham");
                        bt.mauSac = rs.getString("MauSac");
                        bt.kichCo = rs.getInt("KichCo");
                        list.add(bt);
                    }
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Biến Thể SP: " + e.getMessage());
            }
            return list;
        }
        public BienTheSanPham getBienTheSanPham (int maBienThe) throws SQLException {
                String sql = "SELECT * FROM BIEN_THE_SAN_PHAM WHERE ma=?";
                try (Connection con = cn.connectSQL();
                        PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1,maBienThe);
                    try(ResultSet rs = ps.executeQuery()){
                        if(rs.next()){
                            BienTheSanPham obj = new BienTheSanPham();
                            obj.ma = rs.getInt("ma");
                            obj.maSP = rs.getInt("MaSanPham");
                            obj.mauSac = rs.getString("MauSac");
                            obj.kichCo = rs.getInt("KichCo");
                            return obj;
                        }
                    }
                }
                return null;
            }
        // ===== Thêm biến thể =====
        public boolean addBienThe(BienTheSanPham obj) {
            String sql = "INSERT INTO BIEN_THE_SAN_PHAM (MaSanPham, MauSac, KichCo) VALUES (?, ?, ?)";
            try (Connection con = cn.connectSQL();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, obj.getMaSP());
                ps.setString(2, obj.getMauSac());
                ps.setInt(3, obj.getKichCo());
                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi thêm Biến Thể SP: " + e.getMessage());
                return false;
            }
        }

        // ===== Sửa biến thể =====
        public boolean updateBienThe(BienTheSanPham obj) {
            String sql = "UPDATE BIEN_THE_SAN_PHAM SET MauSac=?, KichCo=? WHERE ma=?";
            try (Connection con = cn.connectSQL();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, obj.getMauSac());
                ps.setInt(2, obj.getKichCo());
                ps.setInt(3, obj.getMa());
                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi cập nhật Biến Thể SP: " + e.getMessage());
                return false;
            }
        }

        // ===== Xóa biến thể =====
        public boolean deleteBienThe() {
            String sql = "DELETE FROM BIEN_THE_SAN_PHAM WHERE ma=?";
            try (Connection con = cn.connectSQL();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, ma);
                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi xóa Biến Thể SP: " + e.getMessage());
                return false;
            }
        }
    }
