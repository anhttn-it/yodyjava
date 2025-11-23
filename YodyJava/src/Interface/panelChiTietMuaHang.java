/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interface;

import Proccess.ChiTietMuaHang;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Admin
 */

public class panelChiTietMuaHang extends javax.swing.JPanel {

    private final ChiTietMuaHang ct = new ChiTietMuaHang();
    private boolean cothem = false;
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private int maMHselected = -1; // nếu khác -1 => form mở theo MaMuaHang

    public panelChiTietMuaHang() {
        initComponents();
        initForm();
    }

    public panelChiTietMuaHang(int maMHselected) {
        this.maMHselected = maMHselected;
        initComponents();
        initForm();
    }

    private void initForm() {
        tableModel.setColumnIdentifiers(new Object[]{"Mã MH", "Mã SP", "Màu sắc", "Kích cỡ", "Số lượng", "Đơn giá"});
        tblCTMH.setModel(tableModel);

        loadComboBoxMaMH();
        loadComboBoxMaSP();

        if (maMHselected != -1) {
            String key = String.valueOf(maMHselected);
            for (int i = 0; i < cbMaMH.getItemCount(); i++) {
                if (key.equals(cbMaMH.getItemAt(i))) {
                    cbMaMH.setSelectedItem(key);
                    cbMaMH.setEnabled(false);
                    break;
                }
            }
        }

        setNull();
        setButton(true);
        setKhoa(true);
        showData();
    }

    private void loadComboBoxMaMH() {
        try {
            cbMaMH.removeAllItems();
            List<Integer> list = ct.getAllMaMH();
            for (Integer x : list) cbMaMH.addItem(String.valueOf(x));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nạp MaMH: " + e.getMessage());
        }
    }

    private void loadComboBoxMaSP() {
        try {
            cbMaSP.removeAllItems();
            List<Integer> list = ct.getAllMaSP();
            for (Integer x : list) cbMaSP.addItem(String.valueOf(x));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nạp MaSP: " + e.getMessage());
        }
    }

    private void loadComboBoxMauSac(int maSP) {
        try {
            cbms.removeAllItems();
            List<String> list = ct.getAllMauSac(maSP);
            for (String s : list) cbms.addItem(s);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nạp Màu sắc: " + e.getMessage());
        }
    }

    private void loadComboBoxKichCo(int maSP) {
        try {
            cbkt.removeAllItems();
            List<Integer> list = ct.getAllKichCo(maSP);
            for (Integer x : list) cbkt.addItem(String.valueOf(x));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nạp Kích cỡ: " + e.getMessage());
        }
    }
    public void loadData(int maMH) {
    // clear table trước
    tableModel.setRowCount(0);

    // lấy dữ liệu từ ChiTietMuaHang
    try {
        List<ChiTietMuaHang> list = ct.getByMaMuaHang(maMH);
        for (ChiTietMuaHang c : list) {
            tableModel.addRow(new Object[]{
                c.getMaMH(), c.getMaSP(), c.getMauSac(), c.getKichCo(), c.getSoLuong(), c.getDonGia()
            });
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi load chi tiết: " + e.getMessage());
    }
}
    private void setNull() {
        if (maMHselected == -1) cbMaMH.setSelectedItem(null);
        cbMaSP.setSelectedItem(null);
        cbms.removeAllItems();
        cbkt.removeAllItems();
        txtSoluong.setText("");
        txtDongia.setText("");
    }

    private void setButton(boolean a) {
        btnThemCTMH.setEnabled(a);
        btnSuaCTMH.setEnabled(a);
        btnXoaCTMH.setEnabled(a);
        btnLuuCTMH.setEnabled(!a);
        btnKLuuCTMH.setEnabled(!a);
    }

    private void setKhoa(boolean a) {
        cbMaMH.setEnabled(maMHselected == -1 && !a);
        cbMaSP.setEnabled(!a);
        cbms.setEnabled(!a);
        cbkt.setEnabled(!a);
        txtSoluong.setEditable(!a);
        txtDongia.setEditable(!a);
    }

    private void showData() {
        tableModel.setRowCount(0);
        try {
            List<ChiTietMuaHang> list;
            if (maMHselected != -1) list = ct.getByMaMuaHang(maMHselected);
            else list = ct.getAll();

            for (ChiTietMuaHang c : list) {
                String mausac = c.getMauSac();
                int kichco = c.getKichCo();
                tableModel.addRow(new Object[]{c.getMaMH(), c.getMaSP(), mausac, kichco, c.getSoLuong(), c.getDonGia()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hiển thị dữ liệu: " + e.getMessage());
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnThemCTMH = new javax.swing.JButton();
        btnSuaCTMH = new javax.swing.JButton();
        btnXoaCTMH = new javax.swing.JButton();
        btnLuuCTMH = new javax.swing.JButton();
        btnKLuuCTMH = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbMaMH = new javax.swing.JComboBox<>();
        cbMaSP = new javax.swing.JComboBox<>();
        txtSoluong = new javax.swing.JTextField();
        txtDongia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTMH = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbkt = new javax.swing.JComboBox<>();
        cbms = new javax.swing.JComboBox<>();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHI TIẾT MUA HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 153, 255))); // NOI18N

        btnThemCTMH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemCTMH.setText("Thêm");
        btnThemCTMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTMHActionPerformed(evt);
            }
        });

        btnSuaCTMH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaCTMH.setText("Sửa");
        btnSuaCTMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTMHActionPerformed(evt);
            }
        });

        btnXoaCTMH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaCTMH.setText("Xóa");
        btnXoaCTMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTMHActionPerformed(evt);
            }
        });

        btnLuuCTMH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLuuCTMH.setText("Lưu");
        btnLuuCTMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuCTMHActionPerformed(evt);
            }
        });

        btnKLuuCTMH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKLuuCTMH.setText("K.Lưu");
        btnKLuuCTMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKLuuCTMHActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã mua hàng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã sản phẩm:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Số lượng:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Đơn giá:");

        cbMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMaSPActionPerformed(evt);
            }
        });

        tblCTMH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã mua hàng", "Mã sản phẩm", "Màu sắc", "Kích cỡ", "Số lượng", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTMH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTMHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTMH);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Kích cỡ:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Màu sắc:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbMaSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbms, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThemCTMH)
                        .addGap(28, 28, 28)
                        .addComponent(btnSuaCTMH)
                        .addGap(28, 28, 28)
                        .addComponent(btnXoaCTMH)
                        .addGap(30, 30, 30)
                        .addComponent(btnLuuCTMH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnKLuuCTMH)
                                .addGap(37, 37, 37)
                                .addComponent(btnThoat))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbkt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTMH)
                    .addComponent(btnSuaCTMH)
                    .addComponent(btnXoaCTMH)
                    .addComponent(btnLuuCTMH)
                    .addComponent(btnKLuuCTMH)
                    .addComponent(btnThoat))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(cbMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbkt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(cbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemCTMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTMHActionPerformed
        // TODO add your handling code here:
        setNull();
        setButton(false);
        setKhoa(false);
        cothem = true;
    }//GEN-LAST:event_btnThemCTMHActionPerformed

    private void btnSuaCTMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTMHActionPerformed
        // TODO add your handling code here:
        if (cbMaMH.getSelectedItem() == null || cbMaSP.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Hãy chọn chi tiết cần sửa!");
            return;
        }
        setButton(false);
        setKhoa(false);
        cothem = false;
    }//GEN-LAST:event_btnSuaCTMHActionPerformed

    private void btnXoaCTMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTMHActionPerformed
        // TODO add your handling code here:
        if (cbMaMH.getSelectedItem() == null || cbMaSP.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Hãy chọn chi tiết cần xóa!");
            return;
        }
        try {
            int maMH = Integer.parseInt(cbMaMH.getSelectedItem().toString());
            int maSP = Integer.parseInt(cbMaSP.getSelectedItem().toString());
            String mausac = cbms.getSelectedItem().toString();
            int kichco = Integer.parseInt(cbkt.getSelectedItem().toString());
            int maBienThe = ct.getMaBienThe(maSP, mausac, kichco);

            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (ct.deleteData(maMH, maBienThe)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    showData();
                    setNull();
                } else JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi xóa: " + e.getMessage());
        }
    }//GEN-LAST:event_btnXoaCTMHActionPerformed

    private void btnLuuCTMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuCTMHActionPerformed
        // TODO add your handling code here:
        try {
            if (cbMaMH.getSelectedItem() == null || cbMaSP.getSelectedItem() == null || cbms.getSelectedItem() == null || cbkt.getSelectedItem() == null || txtSoluong.getText().isEmpty() || txtDongia.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                return;
            }

            int maMH = Integer.parseInt(cbMaMH.getSelectedItem().toString());
            int maSP = Integer.parseInt(cbMaSP.getSelectedItem().toString());
            String mausac = cbms.getSelectedItem().toString();
            int kichco = Integer.parseInt(cbkt.getSelectedItem().toString());
            int sl = Integer.parseInt(txtSoluong.getText().trim());
            float dg = Float.parseFloat(txtDongia.getText().trim());

            boolean ok = cothem ? ct.addChiTietMH(maMH, maSP, mausac, kichco, sl, dg) : ct.updateChiTietMH(maMH, maSP, mausac, kichco, sl, dg);

            if (ok) {
                JOptionPane.showMessageDialog(this, (cothem ? "Thêm" : "Sửa") + " thành công!");
                showData();
                setNull();
                setButton(true);
                setKhoa(true);
            } else {
                JOptionPane.showMessageDialog(this, (cothem ? "Thêm" : "Sửa") + " thất bại!");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Số lượng/Đơn giá phải là số!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi lưu dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btnLuuCTMHActionPerformed

    private void btnKLuuCTMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKLuuCTMHActionPerformed
        // TODO add your handling code here:
        setNull();
        setButton(true);
        setKhoa(true);
        tblCTMH.clearSelection();
    }//GEN-LAST:event_btnKLuuCTMHActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void cbMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMaSPActionPerformed
        // TODO add your handling code here:
        if (cbMaSP.getSelectedItem() != null) {
            try {
                int maSP = Integer.parseInt(cbMaSP.getSelectedItem().toString());
                loadComboBoxMauSac(maSP);
                loadComboBoxKichCo(maSP);

                // nếu ko có giá trị, disable cbms và cbkt
                cbms.setEnabled(cbms.getItemCount() > 0);
                cbkt.setEnabled(cbkt.getItemCount() > 0);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ");
            }
        }
    }//GEN-LAST:event_cbMaSPActionPerformed

    private void tblCTMHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTMHMouseClicked
        // TODO add your handling code here:
        try {
            int row = tblCTMH.getSelectedRow();
            if (row >= 0) {
                cbMaMH.setSelectedItem(tblCTMH.getValueAt(row, 0).toString());
                cbMaSP.setSelectedItem(tblCTMH.getValueAt(row, 1).toString());

                // Nạp lại comboBox Màu sắc và Kích cỡ theo Mã SP
                int maSP = Integer.parseInt(cbMaSP.getSelectedItem().toString());
                loadComboBoxMauSac(maSP);
                loadComboBoxKichCo(maSP);

                cbms.setSelectedItem(tblCTMH.getValueAt(row, 2).toString());
                cbkt.setSelectedItem(tblCTMH.getValueAt(row, 3).toString());

                txtSoluong.setText(tblCTMH.getValueAt(row, 4).toString());
                txtDongia.setText(tblCTMH.getValueAt(row, 5).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi lấy dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_tblCTMHMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKLuuCTMH;
    private javax.swing.JButton btnLuuCTMH;
    private javax.swing.JButton btnSuaCTMH;
    private javax.swing.JButton btnThemCTMH;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoaCTMH;
    private javax.swing.JComboBox<String> cbMaMH;
    private javax.swing.JComboBox<String> cbMaSP;
    private javax.swing.JComboBox<String> cbkt;
    private javax.swing.JComboBox<String> cbms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCTMH;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtSoluong;
    // End of variables declaration//GEN-END:variables

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
