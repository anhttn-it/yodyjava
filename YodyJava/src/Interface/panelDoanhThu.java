/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interface;

import java.sql.*;
import Database.Connect;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ngocanh
 */
public class panelDoanhThu extends javax.swing.JPanel {
    private final Connect cn=new Connect();
    private void loadNgayThangNam() {
    cbNgay.removeAllItems();
    cbThang.removeAllItems();
    cbNam.removeAllItems();
    cbNgay.addItem("Tất cả");
    for (int i = 1; i <= 31; i++) {
        cbNgay.addItem(String.valueOf(i));
    }
    for (int i = 1; i <= 12; i++) {
        cbThang.addItem(String.valueOf(i));
    }
    int year = java.time.LocalDate.now().getYear();
    for (int i = 2020; i <= year; i++) {
        cbNam.addItem(String.valueOf(i));
        }
    }
    private void loadDoanhThu(String ngayStr, int thang, int nam) {
        String sql;
        if ("Tất cả".equals(ngayStr)) {
            sql = "SELECT CAST(NgayNhap AS DATE) AS Ngay, TongTien AS Von, 0 AS DoanhThu, 0 - TongTien AS LoiNhuan " +
                  "FROM PHIEU_NHAP " +
                  "WHERE MONTH(NgayNhap)=? AND YEAR(NgayNhap)=? " +
                  "UNION ALL " +
                  "SELECT CAST(NgayXuat AS DATE) AS Ngay, 0 AS Von, TongTien AS DoanhThu, TongTien - 0 AS LoiNhuan " +
                  "FROM PHIEU_XUAT " +
                  "WHERE MONTH(NgayXuat)=? AND YEAR(NgayXuat)=? " +
                  "ORDER BY Ngay";
        } else {
            int ngay = Integer.parseInt(ngayStr);
            sql = "SELECT CAST(NgayNhap AS DATE) AS Ngay, TongTien AS Von, 0 AS DoanhThu, 0 - TongTien AS LoiNhuan " +
                  "FROM PHIEU_NHAP " +
                  "WHERE DAY(NgayNhap)=? AND MONTH(NgayNhap)=? AND YEAR(NgayNhap)=? " +
                  "UNION ALL " +
                  "SELECT CAST(NgayXuat AS DATE) AS Ngay, 0 AS Von, TongTien AS DoanhThu, TongTien - 0 AS LoiNhuan " +
                  "FROM PHIEU_XUAT " +
                  "WHERE DAY(NgayXuat)=? AND MONTH(NgayXuat)=? AND YEAR(NgayXuat)=? " +
                  "ORDER BY Ngay";
        }

        try (Connection conn = cn.connectSQL();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if ("Tất cả".equals(ngayStr)) {
                ps.setInt(1, thang);
                ps.setInt(2, nam);
                ps.setInt(3, thang);
                ps.setInt(4, nam);
            } else {
                int ngay = Integer.parseInt(ngayStr);
                ps.setInt(1, ngay);
                ps.setInt(2, thang);
                ps.setInt(3, nam);
                ps.setInt(4, ngay);
                ps.setInt(5, thang);
                ps.setInt(6, nam);
            }

        ResultSet rs = ps.executeQuery();
         DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
         model.setRowCount(0);

         double tongVon = 0;
         double tongDoanhThu = 0;
         double tongLoiNhuan = 0;

         while (rs.next()) {
             double von = rs.getDouble("Von");
             double doanhThu = rs.getDouble("DoanhThu");
             double loiNhuan = rs.getDouble("LoiNhuan");

             Object[] row = new Object[]{
                 rs.getDate("Ngay"),
                 von,
                 doanhThu,
                 loiNhuan
             };
             model.addRow(row);

             tongVon += von;
             tongDoanhThu += doanhThu;
             tongLoiNhuan += loiNhuan;
         }
            Object[] rowTong = new Object[]{
                "Tổng",
                tongVon,
                tongDoanhThu,
                tongLoiNhuan
            };
            model.addRow(rowTong);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public panelDoanhThu() {
        initComponents();
        loadNgayThangNam();
        LocalDate now = LocalDate.now();
        cbNgay.setSelectedItem(String.valueOf(now.getDayOfMonth()));
        cbThang.setSelectedItem(String.valueOf(now.getMonthValue()));
        cbNam.setSelectedItem(String.valueOf(now.getYear()));
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbNgay = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbThang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbNam = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(228, 245, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doanh Thu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Ngày");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tháng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Năm");

        jButton1.setBackground(new java.awt.Color(255, 204, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Xem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(255, 251, 241));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ngày", "Vốn", "Doanh thu", "Lợi nhuận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(71, 71, 71)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String ngay = cbNgay.getSelectedItem().toString();
        int thang = Integer.parseInt(cbThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cbNam.getSelectedItem().toString());

        loadDoanhThu(ngay, thang, nam);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbNam;
    private javax.swing.JComboBox<String> cbNgay;
    private javax.swing.JComboBox<String> cbThang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
