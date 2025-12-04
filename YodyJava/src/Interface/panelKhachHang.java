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
        btnKLuuKH = new javax.swing.JButton();
        btnLuuKH = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTimKH = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbGioiTinh = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbLoaiKH = new javax.swing.JComboBox<>();
        btnThemKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btlamoi = new javax.swing.JButton();
        bttim = new javax.swing.JButton();

        setBackground(new java.awt.Color(228, 245, 255));

        jPanel2.setBackground(new java.awt.Color(228, 245, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(0, 51, 204))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1400, 800));

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("Tìm kiếm:");

        txtTimKH.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtTimKH.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã khách hàng:");

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên khách hàng:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Địa chỉ:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Giới tính:");

        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Loại khách hàng:");

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

        btnXoaKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close.png"))); // NOI18N
        btnXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHActionPerformed(evt);
            }
        });

        tblKhachHang.setBackground(new java.awt.Color(255, 251, 241));
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

        btlamoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Restart1.png"))); // NOI18N
        btlamoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlamoiActionPerformed(evt);
            }
        });

        bttim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        bttim.setToolTipText("");
        bttim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(btnThemKH)
                .addGap(80, 80, 80)
                .addComponent(btnSuaKH)
                .addGap(84, 84, 84)
                .addComponent(btnXoaKH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btlamoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(bttim)
                        .addGap(146, 146, 146)
                        .addComponent(btnKLuuKH)
                        .addGap(68, 68, 68)
                        .addComponent(btnLuuKH))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(txtMaKH))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)
                                .addComponent(jLabel5)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbGioiTinh, 0, 180, Short.MAX_VALUE)
                            .addComponent(cbLoaiKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bttim, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btlamoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLuuKH)
                            .addComponent(btnKLuuKH))))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemKH)
                    .addComponent(btnSuaKH)
                    .addComponent(btnXoaKH))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        setNull();
        setButton(false);
        setKhoa(false);
        txtMaKH.setEditable(false); // khóa ô mã
        cothem = true;
        tblKhachHang.setEnabled(false);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void bttimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttimActionPerformed
        // TODO add your handling code here:
        String keyword = txtTimKH.getText().trim();
        if(keyword.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin để tìm!");
            return;
        }
        try{
            clearData();
            List <KhachHang> list = kh.search(keyword);
            if(list.isEmpty()){
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm nào!");
                
            }
            else{
                for(KhachHang kha: list){
                    Object[] row = new Object[6];
                    row [0] = kha.getMaKH();
                    row [1] = kha.getTenKH();
                    row [2] = kha.getDiaChi();
                    row [3] = kha.getSoDienThoai();
                    row [4] = kha.getGioiTinh();
                    row [5] = kha.getLoaiKH();
                    tableModel.addRow(row);
            }
        } 
        }
        catch (SQLException e) {
        // Thông báo lỗi SQL chi tiết
        JOptionPane.showMessageDialog(this, "Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());}
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm!" +e.getMessage());
        }
    }//GEN-LAST:event_bttimActionPerformed

    private void btlamoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlamoiActionPerformed
        // TODO add your handling code here:
        try {
            clearData();
            showData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi tải lại dữ liệu: " + ex.getMessage());
        }
    }//GEN-LAST:event_btlamoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btlamoi;
    private javax.swing.JButton btnKLuuKH;
    private javax.swing.JButton btnLuuKH;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton bttim;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKH;
    // End of variables declaration//GEN-END:variables
}
