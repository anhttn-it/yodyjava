/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ngocanh
 */
public class frmHome extends javax.swing.JFrame {
        private final int userRole;
        private void resetButtonColors() {
        btnMua.setBackground(null);
        btnBan.setBackground(null);
        btnNhap.setBackground(null);
        btnXuat.setBackground(null);
        btnSanPham.setBackground(null);
        btnNhanVien.setBackground(null);
        btnncc.setBackground(null);
        btnkh.setBackground(null);
        btnBaoCao.setBackground(null);
    }
        private void highlightButton(javax.swing.JButton selected) {
        resetButtonColors();
        selected.setBackground(java.awt.Color.GRAY);
    }
    private void setPanel(JPanel mainPanel, JPanel detailPanel) {
        pane.removeAll();
        pane.setLayout(new java.awt.BorderLayout());
        pane.add(mainPanel);
        pane.revalidate();
        pane.repaint();
        panelct.removeAll();
        panelct.setLayout(new java.awt.BorderLayout());
        if (detailPanel != null) {
            panelct.add(detailPanel);
        }
        panelct.revalidate();
        panelct.repaint();
    }
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmHome.class.getName());

    public frmHome() {
        initComponents();
        this.userRole = -1;
    }
    public frmHome(int vaiTro) {
        initComponents();
        this.userRole = vaiTro;
        // Gọi hàm phân quyền sau khi các components đã được khởi tạo
        phanQuyenChucNang(vaiTro); 
        this.setExtendedState(MAXIMIZED_BOTH); // Mở form toàn màn hình
        this.setTitle("Hệ thống Quản lý Bán hàng - Vai trò: " + getRoleName(vaiTro));
    }
    private void phanQuyenChucNang(int vaiTro) {
        
        // Mặc định, ẩn tất cả nút (ngoại trừ nút Đăng Xuất)
        btnMua.setVisible(false);
        btnBan.setVisible(false);
        btnNhap.setVisible(false);
        btnXuat.setVisible(false);
        btnSanPham.setVisible(false);
        btnNhanVien.setVisible(false);
        btnncc.setVisible(false);
        btnkh.setVisible(false);
        btnBaoCao.setVisible(false);
        
        switch (vaiTro) {
            case 0: // QUẢN LÝ (Full Access)
                // Quản lý có thể thấy TẤT CẢ
                btnMua.setVisible(true);        // Mua hàng
                btnBan.setVisible(true);        // Bán hàng
                btnNhap.setVisible(true);       // Phiếu nhập
                btnXuat.setVisible(true);       // Phiếu xuất
                btnSanPham.setVisible(true);    // Sản phẩm
                btnNhanVien.setVisible(true);   // Nhân viên
                btnncc.setVisible(true);        // Nhà cung cấp
                btnkh.setVisible(true);         // Khách hàng
                btnBaoCao.setVisible(true);     // Báo Cáo/Thống kê
                break;

            case 1: // NHÂN VIÊN KHO (Warehouse Staff)
                // Tập trung vào Mua hàng và Quản lý Kho
                btnMua.setVisible(true);        // Mua hàng (để tạo đơn mua)
                btnNhap.setVisible(true);       // Phiếu nhập (nhận hàng vào kho)
                btnXuat.setVisible(true);       // Phiếu xuất (xuất hàng khỏi kho)
                btnSanPham.setVisible(true);    // Quản lý Sản phẩm (thông tin)
                btnncc.setVisible(true);        // Nhà cung cấp
                break;

            case 2: // NHÂN VIÊN THU NGÂN (Sales/Cashier Staff)
                // Tập trung vào Bán hàng và thông tin khách hàng
                btnBan.setVisible(true);        // Bán hàng
                btnkh.setVisible(true);         // Khách hàng
                btnSanPham.setVisible(true);    
                break;
                
            default: // Vai trò khác hoặc -1
                JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập chức năng nào. Vui lòng liên hệ Quản trị viên.", "Lỗi Phân Quyền", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
    // Hàm phụ trợ để hiển thị tên vai trò (Optional)
    private String getRoleName(int vaiTro) {
        switch (vaiTro) {
            case 0: return "Quản lý";
            case 1: return "Nhân viên Kho";
            case 2: return "Nhân viên Thu Ngân";
            default: return "Không xác định";
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnMua = new javax.swing.JButton();
        btnNhap = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnBan = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnncc = new javax.swing.JButton();
        btnkh = new javax.swing.JButton();
        btnBaoCao = new javax.swing.JButton();
        pane = new javax.swing.JPanel();
        panelct = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoyody.png"))); // NOI18N

        btnMua.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnMua.setText("Mua hàng");
        btnMua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuaActionPerformed(evt);
            }
        });

        btnNhap.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnNhap.setText("Phiếu nhập");
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapActionPerformed(evt);
            }
        });

        btnXuat.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnXuat.setText("Phiếu xuất");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnSanPham.setText("Sản phẩm");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(255, 204, 153));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        btnBan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnBan.setText("Bán hàng");
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });

        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnncc.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnncc.setText("Nhà cung cấp");
        btnncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnccActionPerformed(evt);
            }
        });

        btnkh.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnkh.setText("Khách hàng");
        btnkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkhActionPerformed(evt);
            }
        });

        btnBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnBaoCao.setText("Báo Cáo");
        btnBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoCaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnncc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnNhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnkh, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnMua)
                .addGap(18, 18, 18)
                .addComponent(btnBan)
                .addGap(18, 18, 18)
                .addComponent(btnNhap)
                .addGap(18, 18, 18)
                .addComponent(btnXuat)
                .addGap(18, 18, 18)
                .addComponent(btnSanPham)
                .addGap(18, 18, 18)
                .addComponent(btnNhanVien)
                .addGap(18, 18, 18)
                .addComponent(btnncc)
                .addGap(18, 18, 18)
                .addComponent(btnkh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBaoCao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pane.setBackground(new java.awt.Color(255, 255, 255));
        pane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pane.setPreferredSize(new java.awt.Dimension(700, 800));

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelct.setBackground(new java.awt.Color(255, 255, 255));
        panelct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelct.setPreferredSize(new java.awt.Dimension(700, 800));

        javax.swing.GroupLayout panelctLayout = new javax.swing.GroupLayout(panelct);
        panelct.setLayout(panelctLayout);
        panelctLayout.setHorizontalGroup(
            panelctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        panelctLayout.setVerticalGroup(
            panelctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addComponent(panelct, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapActionPerformed

        highlightButton(btnNhap);
        try {
            panelPhieuNhap pn = new panelPhieuNhap();
            pane.setPreferredSize(new Dimension(700, 800));
            setPanel(pn, null);
            pn.getTblPhieuNhap().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int row = pn.getTblPhieuNhap().getSelectedRow();
                    if (row >= 0) {
                        int mapn = (int) pn.getTblPhieuNhap().getValueAt(row, 1);
                        try {
                            panelChiTietPhieuNhap detail = new panelChiTietPhieuNhap();
                            detail.loadData(mapn);
                            panelct.removeAll();
                            panelct.add(detail);
                            panelct.revalidate();
                            panelct.repaint();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

        } catch (SQLException ex) {
            System.getLogger(frmHome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_btnNhapActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed

        highlightButton(btnXuat);
        try {
            panelPhieuXuat px = new panelPhieuXuat();
            pane.setPreferredSize(new Dimension(700, 800));
            setPanel(px, null);
            px.getTblPhieuXuat().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int row = px.getTblPhieuXuat().getSelectedRow();
                    if (row >= 0) {
                        int mapx = (int) px.getTblPhieuXuat().getValueAt(row, 1);
                        try {
                            panelChiTietPhieuXuat detail = new panelChiTietPhieuXuat();
                            detail.loadData(mapx);
                            panelct.removeAll();
                            panelct.setLayout(new java.awt.BorderLayout());
                            panelct.add(detail);
                            panelct.revalidate();
                            panelct.repaint();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

        } catch (SQLException ex) {
            System.getLogger(frmHome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed

        highlightButton(btnSanPham);
        try {
            panelSanPham sp = new panelSanPham();
            pane.setPreferredSize(new Dimension(700, 800));
            setPanel(sp, null);
            sp.gettbSanPham().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int row = sp.gettbSanPham().getSelectedRow();
                    if (row >= 0) {
                        int masp = (int) sp.gettbSanPham().getValueAt(row, 1);
                        try {
                            panelBienTheSanPham detail = new panelBienTheSanPham();
                            detail.loadData(masp);
                            panelct.removeAll();
                            panelct.setLayout(new java.awt.BorderLayout());
                            panelct.add(detail);
                            panelct.revalidate();
                            panelct.repaint();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        } catch (SQLException ex) {
            System.getLogger(frmHome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        highlightButton(btnBan);
        try {
            BanHangpanel px = new BanHangpanel();
            pane.setPreferredSize(new Dimension(700, 800));
            setPanel(px, null);
            px.getTblBanHang().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int row = px.getTblBanHang().getSelectedRow();
                    if (row >= 0) {
                        int mapx = (int) px.getTblBanHang().getValueAt(row, 0);
                        try {
                            ChiTietBanHangpanel detail = new ChiTietBanHangpanel();
                            detail.loadData(mapx);
                            panelct.removeAll();
                            panelct.setLayout(new java.awt.BorderLayout());
                            panelct.add(detail);
                            panelct.revalidate();
                            panelct.repaint();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        } catch (SQLException ex) {
            System.getLogger(frmHome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        highlightButton(btnNhanVien);
        try {
            panelNhanVien nv = new panelNhanVien();
            pane.setPreferredSize(new Dimension(1400, 800));
            setPanel(nv, null);
        } catch (SQLException ex) {
            System.getLogger(frmHome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnccActionPerformed
        // TODO add your handling code here:
        highlightButton(btnncc);
        try {
            panelNhaCungCap ncc = new panelNhaCungCap();
            pane.setPreferredSize(new Dimension(1400, 800));
            setPanel(ncc, null);
        } catch (SQLException ex) {
            System.getLogger(frmHome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_btnnccActionPerformed

    private void btnkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkhActionPerformed
        // TODO add your handling code here:
        highlightButton(btnkh);
        try {
            panelKhachHang kh = new panelKhachHang();
            pane.setPreferredSize(new Dimension(1400, 800));
            setPanel(kh, null);
        } catch (SQLException ex) {
            System.getLogger(frmHome.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_btnkhActionPerformed

    private void btnMuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuaActionPerformed
      // TODO add your handling code here:
        highlightButton(btnMua);
    try {
        panelMuaHang mh = new panelMuaHang();
        pane.setPreferredSize(new Dimension(700, 800));
        setPanel(mh, null);
        mh.gettblMuaHang().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = mh.gettblMuaHang().getSelectedRow();
                if (row >= 0) {
                    int maMH = (int) mh.gettblMuaHang().getValueAt(row, 0);
                    try {
                        panelChiTietMuaHang detail = new panelChiTietMuaHang();
                        detail.loadDataByMaMH(maMH);
                        panelct.removeAll();
                        panelct.setLayout(new java.awt.BorderLayout());
                        panelct.add(detail);
                        panelct.revalidate();
                        panelct.repaint();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi load chi tiết: " + ex.getMessage());
                    }
                }
            }
        });

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khởi tạo panel Mua Hàng: " + ex.getMessage());
    }
 
    }//GEN-LAST:event_btnMuaActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        highlightButton(btnDangXuat);
        frmDangNhap dn = new frmDangNhap();
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoCaoActionPerformed
        // TODO add your handling code here:
            highlightButton(btnBaoCao);
            pane.setPreferredSize(new Dimension(700, 800));
            panelDoanhThu dt = new panelDoanhThu();
            panelThongKe tk = new panelThongKe();
            setPanel(dt, tk);
    }//GEN-LAST:event_btnBaoCaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmHome().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnBaoCao;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnMua;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnNhap;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnXuat;
    private javax.swing.JButton btnkh;
    private javax.swing.JButton btnncc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pane;
    private javax.swing.JPanel panelct;
    // End of variables declaration//GEN-END:variables
}
