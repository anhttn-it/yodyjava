/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interface;
import Proccess.BanHang;
import Proccess.ChiTietBanHang;
import Database.Connect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.List;
/**
 *
 * @author Admin
 */
public class panelBanHang extends javax.swing.JPanel {
private DefaultTableModel model;
    private boolean editMode = false; // true khi đang thêm/sửa
    private Connect cn = new Connect();
//private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmBanHang.class.getName());
    /**
     * Creates new form panelBanHang
     */
    public panelBanHang() {
        initComponents();
        model = (DefaultTableModel) tbbanhang.getModel();
        setButton(true);
        setEnableFields(false);
        loadComboBoxes();
        loadData();

        tbbanhang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tbbanhang.getSelectedRow() >= 0) {
                    fillToForm();
                }
            }
        });
    }

     private void loadComboBoxes() {
        try (Connection con = cn.connectSQL()) {
            // Nhân viên
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT MaNhanVien FROM NHAN_VIEN");
            cbmnv.removeAllItems();
            while (rs1.next()) {
                cbmnv.addItem(rs1.getString("MaNhanVien"));
            }

            // Khách hàng
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT MaKhachHang FROM KHACH_HANG");
            cbmkh.removeAllItems();
            while (rs2.next()) {
                cbmkh.addItem(rs2.getString("MaKhachHang"));
            }

            // Trạng thái
            cbtrangthai.removeAllItems();
            cbtrangthai.addItem("Chờ xác nhận"); // 0
            cbtrangthai.addItem("Xác nhận");     // 1
            cbtrangthai.addItem("Đã giao");      // 2

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi load combobox: " + e.getMessage());
        }
    }

    // ================= Load dữ liệu lên table =================
    private void loadData() {
        List<BanHang> list = new BanHang().getAllBanHang();
        model.setRowCount(0);
        for (BanHang bh : list) {
            model.addRow(new Object[]{
                    bh.getMaBanHang(),
                    bh.getMaKhachHang(),
                    bh.getMaNV(),
                    bh.getNgayDatHang(),
                    bh.getTrangThai()
            });
        }
    }

    // ================= Clear form =================
    private void clearFields() {
        txtmbh.setText("");
        cbmnv.setSelectedIndex(0);
        cbmkh.setSelectedIndex(0);
        txtngaydat.setText("");
        cbtrangthai.setSelectedIndex(0);
    }

    // ================= Enable/Disable fields =================
    private void setEnableFields(boolean enable) {
        txtmbh.setEnabled(enable);
        cbmnv.setEnabled(enable);
        cbmkh.setEnabled(enable);
        txtngaydat.setEnabled(enable);
        cbtrangthai.setEnabled(enable);
    }

    // ================= Enable/Disable button =================
    private void setButton(boolean enable) {
        btthem.setEnabled(enable);
        btsua.setEnabled(enable);
        btxoa.setEnabled(enable);
        btluu.setEnabled(!enable);
        btkluu.setEnabled(!enable);
    }

    // ================= Fill form từ table =================
    private void fillToForm() {
        int row = tbbanhang.getSelectedRow();
        if (row < 0) return;

        txtmbh.setText(tbbanhang.getValueAt(row, 0).toString());
        cbmkh.setSelectedItem(tbbanhang.getValueAt(row, 1).toString());
        cbmnv.setSelectedItem(tbbanhang.getValueAt(row, 2).toString());
        Timestamp ts = (Timestamp) tbbanhang.getValueAt(row, 3);
        txtngaydat.setText(ts.toString().split(" ")[0]); // yyyy-MM-dd
        cbtrangthai.setSelectedIndex(Integer.parseInt(tbbanhang.getValueAt(row, 4).toString()));
    }

    // ================= Validate input =================
    private boolean validateInput() {
        if (cbmnv.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!");
            cbmnv.requestFocus();
            return false;
        }
        if (cbmkh.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng không được để trống!");
            cbmkh.requestFocus();
            return false;
        }
        if (txtngaydat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày đặt hàng không được để trống!");
            txtngaydat.requestFocus();
            return false;
        }
        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbtrangthai = new javax.swing.JComboBox<>();
        btthem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btsua = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btxoa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btluu = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btkluu = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btthoat = new javax.swing.JButton();
        txtmbh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbmnv = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbmkh = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbanhang = new javax.swing.JTable();
        txtngaydat = new javax.swing.JTextField();

        cbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ xác nhận", "Xác nhận", "Đã giao" }));

        btthem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btthem.setText("Thêm");
        btthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã bán hàng:");

        btsua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btsua.setText("Sửa");
        btsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsuaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã khách hàng:");

        btxoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btxoa.setText("Xóa");
        btxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Mã nhân viên:");

        btluu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btluu.setText("Lưu");
        btluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluuActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ngày đặt hàng:");

        btkluu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btkluu.setText("K.Lưu");
        btkluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btkluuActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Trạng thái:");

        btthoat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btthoat.setText("Thoát");
        btthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthoatActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("Chi tiết bán hàng");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 121, 227));
        jLabel1.setText("Danh sách bán hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        tbbanhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã bán hàng", "Mã khách hàng", "Mã nhân viên", "Ngày đặt hàng", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbbanhang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtmbh)
                                        .addComponent(cbmnv, 0, 118, Short.MAX_VALUE)
                                        .addComponent(cbtrangthai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(99, 99, 99)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbmkh, 0, 119, Short.MAX_VALUE)
                                        .addComponent(txtngaydat)))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btthem)
                            .addGap(18, 18, 18)
                            .addComponent(btsua)
                            .addGap(18, 18, 18)
                            .addComponent(btxoa)
                            .addGap(18, 18, 18)
                            .addComponent(btluu)
                            .addGap(18, 18, 18)
                            .addComponent(btkluu)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btthoat))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtmbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(cbmnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtngaydat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btthem)
                    .addComponent(btsua)
                    .addComponent(btxoa)
                    .addComponent(btluu)
                    .addComponent(btkluu)
                    .addComponent(btthoat))
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // TODO add your handling code here:
        clearFields();
        setEnableFields(true);
        setButton(false);
        editMode = true;
    }//GEN-LAST:event_btthemActionPerformed

    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed
        // TODO add your handling code here:
        if (tbbanhang.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 đơn hàng để sửa!");
            return;
        }
        setEnableFields(true);
        setButton(false);
        editMode = false; // Sửa -> không phải thêm
    }//GEN-LAST:event_btsuaActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
        // TODO add your handling code here:
        if (tbbanhang.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 đơn hàng để xóa!");
            return;
        }
        int id = Integer.parseInt(txtmbh.getText());
        BanHang bh = new BanHang();
        bh.setMaBanHang(id);
        if (bh.deleteBanHang()) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            loadData();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }//GEN-LAST:event_btxoaActionPerformed

    private void btluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluuActionPerformed
        // TODO add your handling code here:
        if (!validateInput()) return;

        BanHang bh = new BanHang();
        if (!txtmbh.getText().trim().isEmpty())
        bh.setMaBanHang(Integer.parseInt(txtmbh.getText()));
        bh.setMaNV(Integer.parseInt(cbmnv.getSelectedItem().toString()));
        bh.setMaKhachHang(Integer.parseInt(cbmkh.getSelectedItem().toString()));
        bh.setNgayDatHang(Timestamp.valueOf(txtngaydat.getText() + " 00:00:00"));
        bh.setTrangThai(cbtrangthai.getSelectedIndex());

        boolean success;
        if (editMode) success = bh.addBanHang();
        else success = bh.updateBanHang();

        if (success) {
            JOptionPane.showMessageDialog(this, "Lưu thành công!");
            loadData();
            clearFields();
            setButton(true);
            setEnableFields(false);
            editMode = false;
        } else {
            JOptionPane.showMessageDialog(this, "Lưu thất bại!");
        }
    }//GEN-LAST:event_btluuActionPerformed

    private void btkluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btkluuActionPerformed
        // TODO add your handling code here:
        clearFields();
        setButton(true);
        setEnableFields(false);
        editMode = false;
    }//GEN-LAST:event_btkluuActionPerformed

    private void btthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthoatActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btthoatActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        int row = tbbanhang.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 đơn hàng để xem chi tiết!");
            return;
        }
        int maBH = Integer.parseInt(tbbanhang.getValueAt(row, 0).toString());
        frmChiTietBanHang frm = new frmChiTietBanHang(maBH);
        frm.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btkluu;
    private javax.swing.JButton btluu;
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton btthoat;
    private javax.swing.JButton btxoa;
    private javax.swing.JComboBox<String> cbmkh;
    private javax.swing.JComboBox<String> cbmnv;
    private javax.swing.JComboBox<String> cbtrangthai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbbanhang;
    private javax.swing.JTextField txtmbh;
    private javax.swing.JTextField txtngaydat;
    // End of variables declaration//GEN-END:variables
}
