        /*
         * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
         * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
         */
        package Proccess;
        import Database.Connect;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;
        import javax.swing.JOptionPane;
        /**
         *
         * @author Admin
         */
        public class BienTheSanPham {
            private int Ma;
            private int MaSP;
            private String MauSac;
            private String KichCo;
            private int SoLuong;
            private final Connect cn = new Connect();
            public BienTheSanPham() {}

            public BienTheSanPham(int Ma, int MaSP, String MauSac, String KichCo, int SoLuong) {
                this.Ma = Ma;
                this.MaSP = MaSP;
                this.MauSac = MauSac;
                this.KichCo = KichCo;
                this.SoLuong = SoLuong;
            }

            // Getters và Setters
            public int getMa() { return Ma; }
            public void setMa(int Ma) { this.Ma = Ma; }

            public int getMaSP() { return MaSP; }
            public void setMaSP(int MaSP) { this.MaSP = MaSP; }

            public String getMauSac() { return MauSac; }
            public void setMauSac(String MauSac) { this.MauSac = MauSac; }

            public String getKichCo() { return KichCo; }
            public void setKichCo(String KichCo) { this.KichCo = KichCo; }

            public int getSoLuong() { return SoLuong; }
            public void setSoLuong(int SoLuong) { this.SoLuong = SoLuong; }

            // Lấy danh sách biến thể theo MaSP
            public List<BienTheSanPham> getListByMaSP(int maSP) {
                List<BienTheSanPham> list = new ArrayList<>();
                String sql = "SELECT * FROM BIEN_THE_SAN_PHAM WHERE MaSanPham=?";
                try (Connection con = cn.connectSQL();
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setInt(1, maSP);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            BienTheSanPham bt = new BienTheSanPham();
                            bt.Ma = rs.getInt("Ma");
                            bt.MaSP = rs.getInt("MaSanPham");
                            bt.MauSac = rs.getString("MauSac");
                            bt.KichCo = rs.getString("KichCo");
                            bt.SoLuong = rs.getInt("SoLuong");
                            list.add(bt);
                        }
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu Biến Thể SP: " + e.getMessage());
                }
                return list;
            }

            // Thêm biến thể
            public boolean addBienThe() {
                String sql = "INSERT INTO BIEN_THE_SAN_PHAM (MaSanPham, MauSac, KichCo, SoLuong) VALUES (?, ?, ?, ?)";
                try (Connection con = cn.connectSQL();
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setInt(1, MaSP);
                    ps.setString(2, MauSac);
                    ps.setString(3, KichCo);
                    ps.setInt(4, SoLuong);
                    return ps.executeUpdate() > 0;

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi thêm Biến Thể SP: " + e.getMessage());
                    return false;
                }
            }

            // Sửa biến thể
            public boolean updateBienThe() {
                String sql = "UPDATE BIEN_THE_SAN_PHAM SET MauSac=?, KichCo=?, SoLuong=? WHERE Ma=?";
                try (Connection con = cn.connectSQL();
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setString(1, MauSac);
                    ps.setString(2, KichCo);
                    ps.setInt(3, SoLuong);
                    ps.setInt(4, Ma);
                    return ps.executeUpdate() > 0;

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi cập nhật Biến Thể SP: " + e.getMessage());
                    return false;
                }
            }

            // Xóa biến thể
            public boolean deleteBienThe() {
                String sql = "DELETE FROM BIEN_THE_SAN_PHAM WHERE Ma=?";
                try (Connection con = cn.connectSQL();
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setInt(1, Ma);
                    return ps.executeUpdate() > 0;

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi xóa Biến Thể SP: " + e.getMessage());
                    return false;
                }
            }
        }
