/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interface;

import java.sql.*;
import Proccess.BanHang;
import Proccess.ComboItem;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ngocanh
 */
public class BanHangpanel extends javax.swing.JPanel {
    public javax.swing.JTable getTblBanHang() {
    return tblBH;
    }
    private final int userRole;
    private final BanHang bh= new BanHang();
    private boolean cothem = false;  
    private int mabh = -1;
    private final DefaultTableModel tableModel= new DefaultTableModel();
    private void selectComboItem(JComboBox<ComboItem> combo, int value) {
    for (int i = 0; i < combo.getItemCount(); i++) {
        ComboItem item = combo.getItemAt(i);
        if (item.getValue() == value) {
            combo.setSelectedIndex(i);
            return;
        }
    }
}
    public void loadComboBoxMaKH() {
        try {
            cbMaKH.removeAllItems();
            List<ComboItem> list = bh.getAllKH(); 
            for (ComboItem maKH : list) {
                cbMaKH.addItem(maKH);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nạp danh sách NCC: " + e.getMessage());
        }
    }
    public void loadMANv() {
        try {
            cbMaNV.removeAllItems();
            List<ComboItem> list = bh.getAllMNV();
            for (ComboItem nv : list) {
                cbMaNV.addItem(nv);
            }
        } catch (Exception ex) {   // ❗ phải đổi thành Exception
            ex.printStackTrace();
        }
    }
    public void setnull(){
        txtGhiChu.setText("");
        txtMaBH.setText("");
        txtNgayBH.setText("");
        cbMaKH.setSelectedItem(null);
        cbMaNV.setSelectedItem(null);
    }
    public void setbutton(boolean a){
         btnLuuBH.setEnabled(!a);
        btnKLuuBH.setEnabled(!a);
        if (a) {
        if (this.userRole == 2) {
            btnThemBH.setEnabled(true);
            btnSuaBH.setEnabled(false);
            btnXoaBH.setEnabled(false);
        } else if (this.userRole == 0) {
            btnThemBH.setEnabled(false); // Quản lý không được thêm
         
        } else {
            // Trường hợp khác (ví dụ: nhanvienkho)
            btnThemBH.setEnabled(false);
            btnSuaBH.setEnabled(false);
            btnXoaBH.setEnabled(false);
        }
    } else {
        // Chế độ Sửa/Thêm (a=false): chỉ cần đảm bảo nút Lưu/Klưu được bật, các nút chính bị tắt
        btnThemBH.setEnabled(false);
        btnSuaBH.setEnabled(false);
        btnXoaBH.setEnabled(false);
    
}
        
    }
     public void phanquyenbtn() {
    // Luôn khóa các nút chức năng mặc định (vì hàm setbutton(true) không được gọi nữa)
    btnThemBH.setEnabled(false);
    btnSuaBH.setEnabled(false);
    btnXoaBH.setEnabled(false);
    btnLuuBH.setEnabled(false);
    btnKLuuBH.setEnabled(false); // Phân quyền chi tiết
    if (this.userRole == 0) {
        btnSuaBH.setEnabled(true);
        btnXoaBH.setEnabled(true);
    } else if (this.userRole == 2) {
        // Nhân viên tn: Chỉ được Thêm
        btnThemBH.setEnabled(true);
    } 
    
    // Vô hiệu hóa nút Lưu/Klưu cho đến khi nhấn Thêm/Sửa
    btnLuuBH.setEnabled(false);
    btnKLuuBH.setEnabled(false);
}
    public void setKhoa(boolean a){
        txtGhiChu.setEditable(!a);
        cbMaKH.setEnabled(!a);
        cbMaNV.setEnabled(!a);
    }
    public void ShowData() throws SQLException{
        List<BanHang> list = bh.getAll(); 
        for(BanHang phieu:list){
            Object[] row = new Object[6];
            row[0] = phieu.getMaBanHang();
            row[1] = phieu.getNgayBH();
            row[2] = phieu.getTenKhachHang();
            row[3] = phieu.getTenNhanVien();
            row[4]=phieu.getTongTien();
            row[5]=phieu.getGhiChu();     
            tableModel.addRow(row);
        }
    }
    public void ClearData() throws SQLException{
        int n=tableModel.getRowCount()-1;
        for(int i=n;i>=0;i--){
            tableModel.removeRow(i);
        }
    }
    public BanHangpanel(int vaiTro) throws SQLException {
        initComponents();
        this.userRole = vaiTro;
        tableModel.setColumnIdentifiers(new Object[]{"Mã bán hàng", "Ngày bán hàng", "Mã KH", "Mã NV", "Tổng tiền", "Ghi chú"});
        tblBH.setModel(tableModel);
        loadMANv();
        loadComboBoxMaKH();
        ShowData();
        setnull();
        phanquyenbtn();
        setKhoa(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnKLuuBH = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtMaBH = new javax.swing.JTextField();
        btnLM = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNgayBH = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBH = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        cbMaKH = new javax.swing.JComboBox<>();
        btnThemBH = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnSuaBH = new javax.swing.JButton();
        cbMaNV = new javax.swing.JComboBox<>();
        btnXoaBH = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnLuuBH = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(228, 245, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 255, 102)), "Danh sách bán hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(0, 51, 204))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(700, 800));
        jPanel3.setVerifyInputWhenFocusTarget(false);

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        btnKLuuBH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKLuuBH.setText("Klưu");
        btnKLuuBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKLuuBHActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mã bán hàng");

        txtMaBH.setEditable(false);

        btnLM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Restart1.png"))); // NOI18N
        btnLM.setPreferredSize(new java.awt.Dimension(36, 37));
        btnLM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLMActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Ngày đặt hàng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setText("Tìm kiếm:");

        txtNgayBH.setEditable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 450));

        tblBH.setBackground(new java.awt.Color(255, 251, 241));
        tblBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã bán hàng", "Ngày bán hàng", "Mã KH", "Mã NV", "Tổng tiền", "Ghi chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblBH.setPreferredSize(new java.awt.Dimension(700, 500));
        tblBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBH);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Khách hàng");

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnThemBH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Plus_1.png"))); // NOI18N
        btnThemBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBHActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã NV");

        btnSuaBH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit.png"))); // NOI18N
        btnSuaBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaBHActionPerformed(evt);
            }
        });

        btnXoaBH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close.png"))); // NOI18N
        btnXoaBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaBHActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Ghi chú");

        btnLuuBH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuuBH.setText("Lưu");
        btnLuuBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuBHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnThemBH)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtMaBH, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnLM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTim)
                                .addGap(0, 41, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnLuuBH)
                                .addGap(31, 31, 31)
                                .addComponent(btnKLuuBH))
                            .addComponent(cbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBH, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(55, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(btnSuaBH)
                                .addGap(90, 90, 90)
                                .addComponent(btnXoaBH))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnKLuuBH)
                            .addComponent(btnLuuBH)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLM, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTim)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(txtNgayBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addComponent(btnThemBH))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaBH)
                            .addComponent(btnXoaBH))))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnKLuuBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKLuuBHActionPerformed
        // TODO add your handling code here:
        setnull();
        setKhoa(true);
        phanquyenbtn();
        tblBH.setEnabled(true);
    }//GEN-LAST:event_btnKLuuBHActionPerformed

    private void btnLMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLMActionPerformed
        // TODO add your handling code here:
        try {
            ClearData();
            ShowData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnLMActionPerformed

    private void tblBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBHMouseClicked
       try {
            int row = tblBH.getSelectedRow();
            String ma = tblBH.getValueAt(row, 0).toString();
            mabh = Integer.parseInt(ma);
            BanHang obj = bh.getBanHang (mabh);
            if (obj != null) {
                txtGhiChu.setText(obj.getGhiChu());
                txtMaBH.setText(String.valueOf(obj.getMaBanHang ()));
                txtNgayBH.setText(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj.getNgayBH()));
                selectComboItem(cbMaKH, obj.getMaKH());
                selectComboItem(cbMaNV, obj.getMaNV());
            }
            if (this.userRole == 0) {
            btnSuaBH.setEnabled(true);
            btnXoaBH.setEnabled(true);
        } else {
            btnSuaBH.setEnabled(false);
            btnXoaBH.setEnabled(false);
        }
            ChiTietBanHangpanel chiTietPanel = new ChiTietBanHangpanel(userRole);
            chiTietPanel.loadData(mabh);
            chiTietPanel.setVisible(true);

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Lỗi đọc dữ liệu: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblBHMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String keyword = txtTimKiem.getText().trim();
        if(keyword.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu xuất để tìm!");
            return;
        }
        try {
            ClearData();
            List<BanHang> list = bh.TimKiemPX(keyword);
            if(list.isEmpty()){
                JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu xuất nào!");
            } else {
                for(BanHang phieu : list){
                    Object[] row = new Object[6];
                    row[0] = phieu.getMaBanHang();
                    row[1] = phieu.getNgayBH();
                    row[2] = phieu.getMaKH();
                    row[3] = phieu.getMaNV();
                    row[4] = phieu.getTongTien();
                    row[5] = phieu.getGhiChu();
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnThemBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBHActionPerformed
        // TODO add your handling code here:
        setnull();
        setbutton(false);
        setKhoa(false);
        cothem=true;
        tblBH.setEnabled(false);
    }//GEN-LAST:event_btnThemBHActionPerformed

    private void btnSuaBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaBHActionPerformed
        // TODO add your handling code here:
        String mapn=txtMaBH.getText();
        if(mapn.isEmpty()){
            JOptionPane.showMessageDialog(this,"Hãy chọn sản phẩm cần sửa!");
        }
        else{
            setKhoa(false);
            tblBH.setEnabled(false);
            setbutton(false);
            cothem=false;
        }
    }//GEN-LAST:event_btnSuaBHActionPerformed

    private void btnXoaBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaBHActionPerformed
        // TODO add your handling code here:
        String ma=txtMaBH.getText();
        try{
            if(ma.isEmpty()){
                JOptionPane.showMessageDialog(this,"Hãy chọn sản phẩm cần xóa!");
                return;
            }
            int mpn=Integer.parseInt(ma);
            if(JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa không!")==JOptionPane.YES_OPTION){
                bh.deleteData(mpn);
                ClearData();
                ShowData();
                setnull();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoaBHActionPerformed

    private void btnLuuBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuBHActionPerformed
        // TODO add your handling code here:
        Object makhObj = cbMaKH.getSelectedItem();
        Object manvObj = cbMaNV.getSelectedItem();
        String ghichu = txtGhiChu.getText().trim();
        String maphieu=txtMaBH.getText();

        if(makhObj == null || manvObj == null){
            JOptionPane.showMessageDialog(this,"Vui lòng điền đầy đủ thông tin!");
        }

        else{
            try {
                ComboItem nvItem = (ComboItem) manvObj;
                ComboItem khItem = (ComboItem) makhObj;
                int makh = khItem.getValue();
                int manv = nvItem.getValue();
                BanHang obj = new BanHang();
                java.util.Date now = new java.util.Date();
                obj.setNgayBH(now);   // tự động lấy thời gian hiện tại
                //            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //            txtNgayNhap.setText(sdf.format(now));
                obj.setMaKH(makh);
                obj.setMaNV(manv);
                obj.setTongTien(0);
                obj.setGhiChu(ghichu);
                if(cothem==true){
                    bh.InsertData(obj);
                }
                else{
                    int mabanhang = Integer.parseInt(maphieu);
                    obj.setMaBanHang(mabanhang);
                    bh.EditData(obj);
                }
                ClearData();
                ShowData();
                setnull();
                phanquyenbtn();
                setKhoa(true);
                cothem=false;
                tblBH.setEnabled(true);
            } catch(Exception ex){
                 ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,"Cập nhật thất bại\n" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnLuuBHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKLuuBH;
    private javax.swing.JButton btnLM;
    private javax.swing.JButton btnLuuBH;
    private javax.swing.JButton btnSuaBH;
    private javax.swing.JButton btnThemBH;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaBH;
    private javax.swing.JComboBox<ComboItem> cbMaKH;
    private javax.swing.JComboBox<ComboItem> cbMaNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBH;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaBH;
    private javax.swing.JTextField txtNgayBH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
