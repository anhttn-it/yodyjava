/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interface;

import java.sql.*;
import Proccess.ChiTietPhieuNhap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ngocanh
 */
public class panelChiTietPhieuNhap extends javax.swing.JPanel {
    public javax.swing.JTable getTblCTPN() {
    return tblCTPN;
    }
    public void loadData(int mapn) throws SQLException {
    this.maPN = mapn;
    ShowData();
    }
    private final ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
    //private final DefaultTableModel tableModel= new DefaultTableModel();
    private final DefaultTableModel tableModel = new DefaultTableModel(
    new String[] {"Mã SP", "Tên SP", "Màu sắc", "Kích cỡ", "Số lượng", "Đơn giá"}, 0
    );
    private final int userRole;
    private int maPN;
    private boolean cothem=true;
    public void ShowData() throws SQLException{
        List<ChiTietPhieuNhap> list = ct.getall(maPN);
        tableModel.setRowCount(0);
        for(ChiTietPhieuNhap ctpn:list){
            Object[] row = new Object[7];
            row[0]= ctpn.getMaSP();
            row[1]=ctpn.getTenSP();
            row[2]=ctpn.getMauSac();
            row[3]=ctpn.getKichCo();
            row[4]=ctpn.getSoLuong();
            row[5]=ctpn.getDonGia();

            tableModel.addRow(row);
        }
    }
    public void loadMaSp() throws SQLException{
        cbMaSP.removeAllItems();
        List<Integer> list = ct.getallMasp();
        for (Integer masp:list){
            cbMaSP.addItem(masp);
        }
        cbMaSP.setSelectedItem(null);
    }
    public void loadtensp() throws SQLException{
        if (cbMaSP.getSelectedItem() == null) return;
        int masp = Integer.parseInt(cbMaSP.getSelectedItem().toString());
        txtTenSP.setText(ct.setTensp(masp));
    }
    public void loadmausac() throws SQLException{
        if (cbMaSP.getSelectedItem() == null) return;
        cbMauSac.removeAllItems();
        int masp = Integer.parseInt(cbMaSP.getSelectedItem().toString());
        List<String> mausac = ct.getallmausac(masp);
        for(String a:mausac){
            cbMauSac.addItem(a);
        }
    }
    public void loadkichco() throws SQLException{
        if (cbMaSP.getSelectedItem() == null) return;
        cbKichCo.removeAllItems();
        int masp = Integer.parseInt(cbMaSP.getSelectedItem().toString());
        List<Integer> kichco = ct.getallkichco(masp);
        for(int a:kichco){
            cbKichCo.addItem(a);
        }
    }
    public void setnull(){
        txtDonGia.setText("");
        txtSoLuong.setText("");
        txtTenSP.setText("");
        cbKichCo.setSelectedItem(null);
        cbMaSP.setSelectedItem(null);
        cbMauSac.setSelectedItem(null);
    }
    public void setbutton(boolean a){
        btnLuuCTPN.setEnabled(!a);
        btnKLuuCTPN.setEnabled(!a);
        if (a) {
        if (this.userRole == 1) {
            btnThemCTPN.setEnabled(true);
            btnSuaCTPN.setEnabled(false);
            btnXoaCTPN.setEnabled(false);
        } else if (this.userRole == 0) {
            btnThemCTPN.setEnabled(false); // Quản lý không được thêm
         
        } else {
            // Trường hợp khác (ví dụ: nhanvienthungan)
            btnThemCTPN.setEnabled(false);
            btnSuaCTPN.setEnabled(false);
            btnXoaCTPN.setEnabled(false);
        }
    } else {
        // Chế độ Sửa/Thêm (a=false): chỉ cần đảm bảo nút Lưu/Klưu được bật, các nút chính bị tắt
        btnThemCTPN.setEnabled(false);
        btnSuaCTPN.setEnabled(false);
        btnXoaCTPN.setEnabled(false);
    
}
        }
    public void phanquyenbtn() {
    // Luôn khóa các nút chức năng mặc định (vì hàm setbutton(true) không được gọi nữa)
    btnThemCTPN.setEnabled(false);
    btnSuaCTPN.setEnabled(false);
    btnXoaCTPN.setEnabled(false);
    btnLuuCTPN.setEnabled(false);
    btnKLuuCTPN.setEnabled(false); // Phân quyền chi tiết
    if (this.userRole == 0) {
        btnSuaCTPN.setEnabled(true);
        btnXoaCTPN.setEnabled(true);
    } else if (this.userRole == 1) {
        // Nhân viên kho: Chỉ được Thêm
        btnThemCTPN.setEnabled(true);
    } 
    
    // Vô hiệu hóa nút Lưu/Klưu cho đến khi nhấn Thêm/Sửa
    btnLuuCTPN.setEnabled(false);
    btnKLuuCTPN.setEnabled(false);
}
    public void setKhoa(boolean a){
        txtDonGia.setEditable(!a);
        txtSoLuong.setEditable(!a);
        cbKichCo.setEnabled(!a);
        cbMaSP.setEnabled(!a); 
        cbMauSac.setEnabled(!a); 
    }
    public panelChiTietPhieuNhap(int vaiTro) {
        initComponents();
        this.userRole = vaiTro;
        tblCTPN.setModel(tableModel); 
        try {
        loadMaSp();
        setnull();
        phanquyenbtn();
        setKhoa(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cbMaSP.addActionListener(e -> {
        try {
            loadmausac();
            loadkichco();
            loadtensp();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbMaSP = new javax.swing.JComboBox<>();
        btnSuaCTPN = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnXoaCTPN = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnLuuCTPN = new javax.swing.JButton();
        cbMauSac = new javax.swing.JComboBox<>();
        btnKLuuCTPN = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbKichCo = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTPN = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnThemCTPN = new javax.swing.JButton();

        setBackground(new java.awt.Color(228, 245, 255));
        setPreferredSize(new java.awt.Dimension(700, 750));

        btnSuaCTPN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaCTPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit.png"))); // NOI18N
        btnSuaCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTPNActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên SP");

        btnXoaCTPN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaCTPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close.png"))); // NOI18N
        btnXoaCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTPNActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Màu sắc");

        btnLuuCTPN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuuCTPN.setText("Lưu");
        btnLuuCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuCTPNActionPerformed(evt);
            }
        });

        btnKLuuCTPN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKLuuCTPN.setText("KLưu");
        btnKLuuCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKLuuCTPNActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Kích cỡ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 204));
        jLabel9.setText("Chi tiết phiếu nhập");

        txtTenSP.setEditable(false);

        tblCTPN.setBackground(new java.awt.Color(255, 251, 241));
        tblCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Màu sắc", "Kích cỡ", "Số lượng", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCTPN.setPreferredSize(new java.awt.Dimension(700, 500));
        tblCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPNMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTPN);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Số lượng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Đơn giá");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã SP");

        btnThemCTPN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemCTPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Plus_1.png"))); // NOI18N
        btnThemCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTPNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThemCTPN)
                        .addGap(19, 19, 19)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnSuaCTPN)
                        .addGap(70, 70, 70)
                        .addComponent(btnXoaCTPN)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 103, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLuuCTPN)
                        .addGap(41, 41, 41)
                        .addComponent(btnKLuuCTPN)
                        .addGap(114, 114, 114))))
            .addGroup(layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuCTPN)
                    .addComponent(btnKLuuCTPN))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaCTPN)
                    .addComponent(btnThemCTPN)
                    .addComponent(btnXoaCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTPNActionPerformed
        // TODO add your handling code here:
        int row = tblCTPN.getSelectedRow();
        if (row >= 0) {
            setbutton(false);
            setKhoa(false);
            cothem = false;
            tblCTPN.setEnabled(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
        }
    }//GEN-LAST:event_btnSuaCTPNActionPerformed

    private void btnXoaCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTPNActionPerformed
        // TODO add your handling code here:
        int row = tblCTPN.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa!");
            return;
        }
        try {
            int maSP = (int) tblCTPN.getValueAt(row, 0);
            String mausac = (String) tblCTPN.getValueAt(row, 2);
            int kichco = (int) tblCTPN.getValueAt(row, 3);
            Integer maBienThe = ct.getMaBienThe(maSP, mausac, kichco);
            if(maBienThe == null){
                JOptionPane.showMessageDialog(this, "Biến thể sản phẩm không tồn tại!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa chi tiết sản phẩm này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if(confirm == JOptionPane.YES_OPTION){
                boolean kq = ct.deleteCTPN(maPN, maBienThe);
                if(kq){
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    ShowData();
                    setnull();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
        } catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnXoaCTPNActionPerformed

    private void btnLuuCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuCTPNActionPerformed
        // TODO add your handling code here:
        try {
            int maSP = (int) cbMaSP.getSelectedItem();
            String mausac = (String) cbMauSac.getSelectedItem();
            int kichco = (int) cbKichCo.getSelectedItem();
            int soluong = Integer.parseInt(txtSoLuong.getText());
            float dongia = Float.parseFloat(txtDonGia.getText());
            if (cbMaSP.getSelectedItem() == null || cbMauSac.getSelectedItem() == null || cbKichCo.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ Mã SP, Màu sắc và Kích cỡ!");
                return;
            }
            boolean kq;
            if (cothem) {
                kq = ct.addCTPN(maPN, maSP, mausac, kichco, soluong, dongia);
                if (kq) {
                    tableModel.setRowCount(0);
                    ShowData();
                    setnull();
                    setbutton(true);
                    setKhoa(true);
                    tblCTPN.setEnabled(true);
                }
            } else {
                kq = ct.updateCTPN(maPN, maSP, mausac, kichco, soluong, dongia);
                if (kq) {
                    tableModel.setRowCount(0);
                    ShowData();
                    setnull();
                    phanquyenbtn();
                    setKhoa(true);
                    tblCTPN.setEnabled(true);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho Số lượng, Đơn giá");
        }
    }//GEN-LAST:event_btnLuuCTPNActionPerformed

    private void btnKLuuCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKLuuCTPNActionPerformed
        // TODO add your handling code here:
        setnull();
        phanquyenbtn();
        setKhoa(true);
        tblCTPN.setEnabled(true);
    }//GEN-LAST:event_btnKLuuCTPNActionPerformed

    private void tblCTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPNMouseClicked
        try {
            // TODO add your handling code here:
            int row =tblCTPN.getSelectedRow();
            if(row < 0) return;
            String ma = tblCTPN.getValueAt(row,0).toString();
            int masp=Integer.parseInt(ma);
            String mausac = tblCTPN.getValueAt(row, 2).toString();
            String kt=tblCTPN.getValueAt(row,3).toString();
            int kichco=Integer.parseInt(kt);
            int mabt=ct.getMaBienThe(masp, mausac, kichco);
            ChiTietPhieuNhap obj=ct.getCTPN(maPN,mabt);
            if(obj!=null){
                cbMaSP.setSelectedItem(obj.getMaSP());
                txtDonGia.setText(String.valueOf(obj.getDonGia()));
                txtSoLuong.setText(String.valueOf(obj.getSoLuong()));
                txtTenSP.setText(obj.getTenSP());
                cbKichCo.setSelectedItem(obj.getKichCo());

                cbMauSac.setSelectedItem(obj.getMauSac());
            }
            if (this.userRole == 0) {
            btnSuaCTPN.setEnabled(true);
            btnXoaCTPN.setEnabled(true);
        } else {
            btnSuaCTPN.setEnabled(false);
            btnXoaCTPN.setEnabled(false);
        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tblCTPNMouseClicked

    private void btnThemCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTPNActionPerformed
        // TODO add your handling code here:
        setnull();
        setbutton(false);
        setKhoa(false);
        cothem=true;
        tblCTPN.setEnabled(false);
    }//GEN-LAST:event_btnThemCTPNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKLuuCTPN;
    private javax.swing.JButton btnLuuCTPN;
    private javax.swing.JButton btnSuaCTPN;
    private javax.swing.JButton btnThemCTPN;
    private javax.swing.JButton btnXoaCTPN;
    private javax.swing.JComboBox<Integer> cbKichCo;
    private javax.swing.JComboBox<Integer> cbMaSP;
    private javax.swing.JComboBox<String> cbMauSac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCTPN;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
