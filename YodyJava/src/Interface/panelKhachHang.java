/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interface;

import Proccess.KhachHang;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Admin
 */
public class panelKhachHang extends javax.swing.JPanel {

    private final KhachHang kh = new KhachHang();
    private boolean cothem = false;
    private int makh = -1;
    private final DefaultTableModel tableModel = new DefaultTableModel();

    public panelKhachHang() throws SQLException {
        initComponents();

        tableModel.setColumnIdentifiers(new Object[]{"Mã KH", "Tên KH", "Địa chỉ", "SĐT", "Giới tính", "Loại KH"});
        tblKhachHang.setModel(tableModel);

        loadComboBoxGioiTinh();
        loadComboBoxLoaiKH();
        showData();
        setNull();
        setButton(true);
        setKhoa(true);
    }

    private void loadComboBoxGioiTinh() {
        cbGioiTinh.removeAllItems();
        cbGioiTinh.addItem("Nam");
        cbGioiTinh.addItem("Nữ");
    }

    private void loadComboBoxLoaiKH() {
        cbLoaiKH.removeAllItems();
        cbLoaiKH.addItem("0 - Thường");
        cbLoaiKH.addItem("1 - VIP");
    }

    private void setNull() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtSoDienThoai.setText("");
        cbGioiTinh.setSelectedItem(null);
        cbLoaiKH.setSelectedItem(null);
    }

    private void setButton(boolean a) {
        btnThemKH.setEnabled(a);
        btnSuaKH.setEnabled(a);
        btnXoaKH.setEnabled(a);
        btnLuuKH.setEnabled(!a);
        btnKLuuKH.setEnabled(!a);
    }

    private void setKhoa(boolean a) {
        txtTenKH.setEditable(!a);
        txtDiaChi.setEditable(!a);
        txtSoDienThoai.setEditable(!a);
        cbGioiTinh.setEnabled(!a);
        cbLoaiKH.setEnabled(!a);
    }

    private void clearData() {
        int n = tableModel.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    private void showData() throws SQLException {
        clearData();
        List<KhachHang> list = kh.getAll();
        for (KhachHang k : list) {
            Object[] row = new Object[]{
                k.getMaKH(),
                k.getTenKH(),
                k.getDiaChi(),
                k.getSoDienThoai(),
                k.getGioiTinh(),
                k.getLoaiKH()
            };
            tableModel.addRow(row);
        }
    }
    private void loadTable(List<KhachHang> list) {
    clearData(); // xóa hết dữ liệu cũ
    for (KhachHang k : list) {
        Object[] row = new Object[]{
            k.getMaKH(),
            k.getTenKH(),
            k.getDiaChi(),
            k.getSoDienThoai(),
        };
        tableModel.addRow(row);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbGioiTinh = new javax.swing.JComboBox<>();
        btnThemKH = new javax.swing.JButton();
        cbLoaiKH = new javax.swing.JComboBox<>();
        btnSuaKH = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnXoaKH = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtTimKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnKLuuKH = new javax.swing.JButton();
        btnLuuKH = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH KHÁCH HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 102, 255))); // NOI18N

        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Plus_1.png"))); // NOI18N
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnSuaKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit.png"))); // NOI18N
        btnSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKHActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Giới tính:");

        btnXoaKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close.png"))); // NOI18N
        btnXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Loại khách hàng:");

        jLabel7.setText("Tìm khách hàng:");

        txtTimKH.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtTimKH.setForeground(new java.awt.Color(153, 153, 153));
        txtTimKH.setText("Mã khách hàng, Tên khách hàng, Số điện thoại");
        txtTimKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKHKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã khách hàng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên khách hàng:");

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Giới tính", "Loại khách hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
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
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Địa chỉ:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(txtTenKH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnThemKH)
                                .addGap(18, 18, 18)))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(200, 200, 200))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(416, 416, 416)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSuaKH)
                        .addGap(267, 267, 267)
                        .addComponent(btnXoaKH)
                        .addGap(402, 402, 402))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaKH)
                        .addGap(63, 63, 63))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThemKH)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(53, 53, 53)
                                .addComponent(btnSuaKH)))
                        .addGap(0, 63, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        btnKLuuKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKLuuKH.setText("K.Lưu");
        btnKLuuKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKLuuKHActionPerformed(evt);
            }
        });

        btnLuuKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuuKH.setText("Lưu");
        btnLuuKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1336, Short.MAX_VALUE)
                .addComponent(btnLuuKH)
                .addGap(34, 34, 34)
                .addComponent(btnKLuuKH)
                .addGap(78, 78, 78))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuKH)
                    .addComponent(btnKLuuKH))
                .addContainerGap(773, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        setNull();
        setButton(false);
        setKhoa(false);
        txtMaKH.setEditable(false); // khóa ô mã
        cothem = true;
        tblKhachHang.setEnabled(false);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed
        // TODO add your handling code here:
        if (txtMaKH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy chọn khách hàng cần sửa!");
        } else {
            setButton(false);
            setKhoa(false);
            txtMaKH.setEditable(false);
            cothem = false;
            tblKhachHang.setEnabled(false);
        }
    }//GEN-LAST:event_btnSuaKHActionPerformed

    private void btnXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKHActionPerformed
        // TODO add your handling code here:
        String ma = txtMaKH.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy chọn khách hàng cần xóa!");
            return;
        }
        try {
            int id = Integer.parseInt(ma);
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?") == JOptionPane.YES_OPTION) {
                kh.deleteData(id);
                clearData();
                showData();
                setNull();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }//GEN-LAST:event_btnXoaKHActionPerformed

    private void btnLuuKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuKHActionPerformed
        // TODO add your handling code here:
        try {
            String ten = txtTenKH.getText().trim();
            String sdt = txtSoDienThoai.getText().trim();

            if (ten.isEmpty() || txtDiaChi.getText().isEmpty() || sdt.isEmpty()
                || cbGioiTinh.getSelectedItem() == null || cbLoaiKH.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                return;
            }

            // Kiểm tra tên KH chỉ có chữ và khoảng trắng
            if (!ten.matches("[a-zA-ZÀ-ỹ ]+")) {
                JOptionPane.showMessageDialog(this, "Tên khách hàng chỉ được nhập chữ!");
                txtTenKH.requestFocus();
                return;
            }

            // Kiểm tra số điện thoại chỉ có số và đúng 10 chữ số
            if (!sdt.matches("\\d+") || sdt.length() != 10) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải là 10 chữ số!");
                txtSoDienThoai.requestFocus();
                return;
            }

            KhachHang obj = new KhachHang();
            obj.setTenKH(ten);
            obj.setDiaChi(txtDiaChi.getText());
            obj.setSoDienThoai(sdt);

            // Lấy trực tiếp giá trị "Nam"/"Nữ" từ ComboBox
            obj.setGioiTinh(cbGioiTinh.getSelectedItem().toString());

            obj.setLoaiKH(Integer.parseInt(cbLoaiKH.getSelectedItem().toString().substring(0, 1)));

            if (cothem) {
                if (kh.insertData(obj)) {
                    showData();
                    JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
                }
            } else {
                int id = Integer.parseInt(txtMaKH.getText());
                obj.setMaKH(id);
                if (kh.editData(obj)) {
                    showData();
                    JOptionPane.showMessageDialog(this, "Sửa khách hàng thành công!");
                }
            }

            setNull();
            setButton(true);
            setKhoa(true);
            cothem = false;
            tblKhachHang.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại! " + ex.getMessage());
        }
    }//GEN-LAST:event_btnLuuKHActionPerformed

    private void btnKLuuKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKLuuKHActionPerformed
        // TODO add your handling code here:
        setNull();
        setKhoa(true);
        setButton(true);
        tblKhachHang.setEnabled(true);
    }//GEN-LAST:event_btnKLuuKHActionPerformed

    private void txtTimKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKHKeyReleased
        // TODO add your handling code here:
        try {
            List<KhachHang> list = kh.search(txtTimKH.getText()); // sửa dao → kh
            loadTable(list);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm: " + e.getMessage());
        }
    }//GEN-LAST:event_txtTimKHKeyReleased

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        try {
            int row = tblKhachHang.getSelectedRow();
            if (row >= 0) {
                makh = (int) tblKhachHang.getValueAt(row, 0);
                KhachHang obj = kh.getKhachHang(makh);
                if (obj != null) {
                    txtMaKH.setText(String.valueOf(obj.getMaKH()));
                    txtTenKH.setText(obj.getTenKH());
                    txtDiaChi.setText(obj.getDiaChi());
                    txtSoDienThoai.setText(obj.getSoDienThoai());

                    // Sửa phần GioiTinh
                    cbGioiTinh.setSelectedItem(obj.getGioiTinh()); // obj.getGioiTinh() = "Nam" hoặc "Nữ"

                    cbLoaiKH.setSelectedItem(obj.getLoaiKH() + " - " + (obj.getLoaiKH() == 0 ? "Thường" : "VIP"));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi đọc dữ liệu: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKLuuKH;
    private javax.swing.JButton btnLuuKH;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private javax.swing.JComboBox<String> cbLoaiKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKH;
    // End of variables declaration//GEN-END:variables
}
