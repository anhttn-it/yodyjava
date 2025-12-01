/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interface;

import Proccess.ChiTietPhieuXuat;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ngocanh
 */
public class panelChiTietPhieuXuat extends javax.swing.JPanel {
    public javax.swing.JTable getTblCTPX() {
    return tblCTPX;
    }
    public void loadData(int mapx) throws SQLException {
    this.maPX = mapx;
    ShowData();
    }
    private final ChiTietPhieuXuat ct = new ChiTietPhieuXuat();
    private final DefaultTableModel tableModel = new DefaultTableModel(
    new String[] {"Mã SP", "Tên SP", "Màu sắc", "Kích cỡ", "Số lượng", "Đơn giá"}, 0
    );
    private int maPX;
    private boolean cothem=true;
    public void ShowData() throws SQLException{
        List<ChiTietPhieuXuat> list = ct.getall(maPX);
        tableModel.setRowCount(0);
        for(ChiTietPhieuXuat ctpx:list){
            Object[] row = new Object[7];
            row[0]= ctpx.getMaSP();
            row[1]=ctpx.getTenSP();
            row[2]=ctpx.getMauSac();
            row[3]=ctpx.getKichCo();
            row[4]=ctpx.getSoLuong();
            row[5]=ctpx.getDonGia();

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
        btnThemCTPX.setEnabled(a);
        btnSuaCTPX.setEnabled(a);
        btnXoaCTPX.setEnabled(a);
        btnLuuCTPX.setEnabled(!a);
        btnKLuuCTPX.setEnabled(!a);
        
    }
    public void setKhoa(boolean a){
        txtDonGia.setEditable(!a);
        txtSoLuong.setEditable(!a);
        cbKichCo.setEnabled(!a);
        cbMaSP.setEnabled(!a); 
        cbMauSac.setEnabled(!a); 
    }
    public panelChiTietPhieuXuat() {
        initComponents();
        tblCTPX.setModel(tableModel); 
        try {
        loadMaSp();
        setnull();
        setbutton(true);
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

        jLabel9 = new javax.swing.JLabel();
        cbKichCo = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTPX = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        cbMaSP = new javax.swing.JComboBox<>();
        btnSuaCTPX = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnXoaCTPX = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnLuuCTPX = new javax.swing.JButton();
        cbMauSac = new javax.swing.JComboBox<>();
        btnKLuuCTPX = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnThemCTPX = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 800));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(java.awt.SystemColor.textHighlight);
        jLabel9.setText("Chi tiết phiếu xuất");

        txtTenSP.setEditable(false);

        tblCTPX.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCTPX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPXMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTPX);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Số lượng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Đơn giá");

        btnSuaCTPX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaCTPX.setText("Sửa");
        btnSuaCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTPXActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên SP");

        btnXoaCTPX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaCTPX.setText("Xóa");
        btnXoaCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTPXActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Màu sắc");

        btnLuuCTPX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuuCTPX.setText("Lưu");
        btnLuuCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuCTPXActionPerformed(evt);
            }
        });

        btnKLuuCTPX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKLuuCTPX.setText("KLưu");
        btnKLuuCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKLuuCTPXActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã SP");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Kích cỡ");

        btnThemCTPX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemCTPX.setText("Thêm");
        btnThemCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTPXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemCTPX)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSuaCTPX)
                            .addGap(47, 47, 47)
                            .addComponent(btnXoaCTPX))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(52, 52, 52)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnLuuCTPX)
                                            .addGap(41, 41, 41)
                                            .addComponent(btnKLuuCTPX))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(40, 40, 40)))
                            .addGap(149, 149, 149)))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(cbKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemCTPX)
                            .addComponent(btnSuaCTPX)
                            .addComponent(btnXoaCTPX)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLuuCTPX)
                            .addComponent(btnKLuuCTPX))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCTPXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPXMouseClicked
        try {
            // TODO add your handling code here:
            int row =tblCTPX.getSelectedRow();
            if(row < 0) return;
            String ma = tblCTPX.getValueAt(row,0).toString();
            int masp=Integer.parseInt(ma);
            String mausac = tblCTPX.getValueAt(row, 2).toString();
            String kt=tblCTPX.getValueAt(row,3).toString();
            int kichco=Integer.parseInt(kt);
            int mabt=ct.getMaBienThe(masp, mausac, kichco);
            ChiTietPhieuXuat obj=ct.getCTPX(maPX,mabt);
            if(obj!=null){
                cbMaSP.setSelectedItem(obj.getMaSP());
                txtDonGia.setText(String.valueOf(obj.getDonGia()));
                txtSoLuong.setText(String.valueOf(obj.getSoLuong()));
                txtTenSP.setText(obj.getTenSP());
                cbKichCo.setSelectedItem(obj.getKichCo());

                cbMauSac.setSelectedItem(obj.getMauSac());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tblCTPXMouseClicked

    private void btnSuaCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTPXActionPerformed
        // TODO add your handling code here:
        int row = tblCTPX.getSelectedRow();
        if (row >= 0) {
            setbutton(false);
            setKhoa(false);
            cothem = false;
            tblCTPX.setEnabled(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
        }
    }//GEN-LAST:event_btnSuaCTPXActionPerformed

    private void btnXoaCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTPXActionPerformed
        // TODO add your handling code here:
        int row = tblCTPX.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa!");
            return;
        }
        try {
            int maSP = (int) tblCTPX.getValueAt(row, 0);
            String mausac = (String) tblCTPX.getValueAt(row, 2);
            int kichco = (int) tblCTPX.getValueAt(row, 3);
            Integer maBienThe = ct.getMaBienThe(maSP, mausac, kichco);
            if(maBienThe == null){
                JOptionPane.showMessageDialog(this, "Biến thể sản phẩm không tồn tại!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa chi tiết sản phẩm này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if(confirm == JOptionPane.YES_OPTION){
                boolean kq = ct.deleteCTPX(maPX, maBienThe);
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
    }//GEN-LAST:event_btnXoaCTPXActionPerformed

    private void btnLuuCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuCTPXActionPerformed
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
                kq = ct.addCTPX(maPX, maSP, mausac, kichco, soluong, dongia);
                if (kq) {
                    tableModel.setRowCount(0);
                    ShowData();
                    setnull();
                    setbutton(true);
                    setKhoa(true);
                    tblCTPX.setEnabled(true);
                }
            } else {
                kq = ct.updateCTPX(maPX, maSP, mausac, kichco, soluong, dongia);
                if (kq) {
                    tableModel.setRowCount(0);
                    ShowData();
                    setnull();
                    setbutton(true);
                    setKhoa(true);
                    tblCTPX.setEnabled(true);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho Số lượng, Đơn giá");
        }
    }//GEN-LAST:event_btnLuuCTPXActionPerformed

    private void btnKLuuCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKLuuCTPXActionPerformed
        // TODO add your handling code here:
        setnull();
        setbutton(true);
        setKhoa(true);
        tblCTPX.setEnabled(true);
    }//GEN-LAST:event_btnKLuuCTPXActionPerformed

    private void btnThemCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTPXActionPerformed
        // TODO add your handling code here:
        setnull();
        setbutton(false);
        setKhoa(false);
        cothem=true;
        tblCTPX.setEnabled(false);
    }//GEN-LAST:event_btnThemCTPXActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKLuuCTPX;
    private javax.swing.JButton btnLuuCTPX;
    private javax.swing.JButton btnSuaCTPX;
    private javax.swing.JButton btnThemCTPX;
    private javax.swing.JButton btnXoaCTPX;
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
    private javax.swing.JTable tblCTPX;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
