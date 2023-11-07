package com.raven.main;

import Helper.ShareHelper;
import com.raven.component.PaneChangePass;
import com.raven.event.EventMenu;
import com.raven.form.Form_HDNhap;
import com.raven.form.Form_HoaDonXuat;
import com.raven.form.Form_KhachHang;
import com.raven.form.Form_LoaiHang;
import com.raven.form.Form_NhaCungCap;
import com.raven.form.Form_NhanVien;
import com.raven.form.Form_SanPham;
import com.raven.form.Form_TrangChu;
import com.raven.form.Form_ThongKe;
import com.raven.notification.Notification;
import java.awt.Color;
import java.awt.Component;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        Main_Login a = new Main_Login(this, true);//.setVisible(true);

        if (ShareHelper.USER == null) {
            System.exit(0);
        } else {
            EventMenu event = new EventMenu() {
                @Override
                public void selected(int index) {
                    if (index == 0) {
                        showForm(new Form_TrangChu());
                    } else if (index == 1) {
                        showForm(new Form_NhanVien(1));
                    } else if (index == 2) {
                        showForm(new Form_KhachHang(2));
                    } else if (index == 3) {
                        showForm(new Form_SanPham(3));
                    } else if (index == 4) {
                        showForm(new Form_LoaiHang(4));
                    } else if (index == 5) {
                        showForm(new Form_NhaCungCap(5));
                    } else if (index == 6) {
                        Form_HDNhap a = new Form_HDNhap(6);
                        showForm(a);
                    } else if (index == 7) {
                        showForm(new Form_HoaDonXuat(7));
                    } else if (index == 8) {
                        showForm(new Form_ThongKe());
                    } else if (index == 9) {
                        dispose();
                        ShareHelper.logoff();;
                        a.setVisible(true);
                        System.out.println("" + ShareHelper.USER);
                        if (ShareHelper.USER != null) {
                            menu1.setname(ShareHelper.USER.getHoTenNhanVien(), ShareHelper.USER.getVaiTroNhanVien());
                        }
                    } else if (index == 10) {
                        System.out.println("đổi mk");
//                        showForm(new PanelQueryPassword(10));
                        new PaneChangePass(null, true).setVisible(true);
                    }
                }
            };
            menu1.initMenu(event);
            setBackground(new Color(0, 0, 0, 0));
            showForm(new Form_TrangChu());
            menu1.setname(ShareHelper.USER.getHoTenNhanVien(), ShareHelper.USER.getVaiTroNhanVien());
        }
    }

    public Main(int i) {
        initComponents();
        Main_Login a = new Main_Login(this, true);//.setVisible(true);
        if (i == 0) {
            new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Đăng nhập thành công").showNotification();
            i += 1;
        }
        if (ShareHelper.USER == null) {
            System.exit(0);
        } else {
            EventMenu event = new EventMenu() {
                @Override
                public void selected(int index) {
                    if (index == 0) {
                        showForm(new Form_TrangChu());
                    } else if (index == 1) {
                        showForm(new Form_NhanVien(1));
                    } else if (index == 2) {
                        showForm(new Form_KhachHang(2));
                    } else if (index == 3) {
                        showForm(new Form_SanPham(3));
                    } else if (index == 4) {
                        showForm(new Form_LoaiHang(4));
                    } else if (index == 5) {
                        showForm(new Form_NhaCungCap(5));
                    } else if (index == 6) {
                        Form_HDNhap a = new Form_HDNhap(6);
                        showForm(a);
                    } else if (index == 7) {
                        showForm(new Form_HoaDonXuat(7));
                    } else if (index == 8) {
                        showForm(new Form_ThongKe());
                    } else if (index == 9) {
                        dispose();
                        ShareHelper.logoff();;
//                        new Main().setVisible(true);
//                        new Main_Login(null, true).setVisible(true);
                        a.setVisible(true);
                        System.out.println("" + ShareHelper.USER);
                        if (ShareHelper.USER != null) {
                            menu1.setname(ShareHelper.USER.getHoTenNhanVien(), ShareHelper.USER.getVaiTroNhanVien());
                        }
                    } else if (index == 10) {
                        System.out.println("đổi mk");
//                        showForm(new PanelQueryPassword(10));
                        new PaneChangePass(null, true).setVisible(true);
                    }
                }
            };
            menu1.initMenu(event);
            setBackground(new Color(0, 0, 0, 0));
            showForm(new Form_TrangChu());
            menu1.setname(ShareHelper.USER.getHoTenNhanVien(), ShareHelper.USER.getVaiTroNhanVien());
        }
    }
    public Main(String a) {
        initComponents();
        a = "6";
        if (ShareHelper.USER == null) {
            System.exit(0);
        } else {
            EventMenu event = new EventMenu() {
                @Override
                public void selected(int index) {
                    if (index == 0) {
                        showForm(new Form_TrangChu());
                    } else if (index == 1) {
                        showForm(new Form_NhanVien(1));
                    } else if (index == 2) {
                        showForm(new Form_KhachHang(2));
                    } else if (index == 3) {
                        showForm(new Form_SanPham(3));
                    } else if (index == 4) {
                        showForm(new Form_LoaiHang(4));
                    } else if (index == 5) {
                        showForm(new Form_NhaCungCap(5));
                    } else if (index == 6) {
                        Form_HDNhap a = new Form_HDNhap(6);
                        showForm(a);
                    } else if (index == 7) {
                        showForm(new Form_HoaDonXuat(7));
                    } else if (index == 8) {
                        showForm(new Form_ThongKe());
                    } else if (index == 9) {
                        dispose();
                        ShareHelper.logoff();;
//                        new Main().setVisible(true);
//                        new Main_Login(null, true).setVisible(true);
                        
                        System.out.println("" + ShareHelper.USER);
                        if (ShareHelper.USER != null) {
                            menu1.setname(ShareHelper.USER.getHoTenNhanVien(), ShareHelper.USER.getVaiTroNhanVien());
                        }
                    } else if (index == 10) {
                        System.out.println("đổi mk");
//                        showForm(new PanelQueryPassword(10));
                        new PaneChangePass(null, true).setVisible(true);
                    }
                }
            };
            menu1.initMenu(event);
            setBackground(new Color(0, 0, 0, 0));
            showForm(new Form_TrangChu());
            menu1.setname(ShareHelper.USER.getHoTenNhanVien(), ShareHelper.USER.getVaiTroNhanVien());
        }
    }
    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.raven.swing.RoundPanel();
        header2 = new com.raven.component.Header();
        menu1 = new com.raven.component.Menu();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        header2.setBackground(new java.awt.Color(0, 204, 51));

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        body.setOpaque(false);
        body.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bodyMouseClicked(evt);
            }
        });
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 1115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bodyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bodyMouseClicked

    }//GEN-LAST:event_bodyMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private com.raven.component.Header header2;
    private com.raven.component.Menu menu1;
    private com.raven.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
