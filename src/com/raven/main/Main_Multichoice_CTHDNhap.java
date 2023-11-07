package com.raven.main;

import DAO.ChiTietHoaDonNhapDAO;
import DAO.NhaCungCapDAO;
import DAO.SanPhamDAO;
import Entity.ChiTietHoaDonNhap;
import Entity.NhaCungCap;
import Entity.SanPham;
import Helper.DialogHelper;
import com.raven.form.Form_HDNhap;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javaswingdev.message.MessageDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Main_Multichoice_CTHDNhap extends javax.swing.JFrame {

    Main a = new Main();

    public Main_Multichoice_CTHDNhap() {
        setUndecorated(true);
        initComponents();

        jScrollPane2.setVerticalScrollBar(new scrollbar());
        jScrollPane2.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);

        setLocationRelativeTo(null);
        setTitle("Multiple Choice");
        fillTable();
    }
//Form_HDNhap ab = new Form_HDNhap();

    public Main_Multichoice_CTHDNhap(String soPHieu) {
        System.out.println("helo1");
        setUndecorated(true);
        initComponents();

        jScrollPane2.setVerticalScrollBar(new scrollbar());
        jScrollPane2.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);


        setLocationRelativeTo(null);
        this.soPhieu = soPHieu;
        lbSoPhieu.setText(String.valueOf(this.soPhieu));
        setTitle("Multiple Choice");
        fillTable();
        btnok.setEnabled(false);
//        btncancle.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//              
//            }
//        });
    }

    /// mới 1.1
    public Main_Multichoice_CTHDNhap(String soPhieu, boolean isEnable) {
        System.out.println("helo2");
        setUndecorated(true);
        initComponents();

        jScrollPane2.setVerticalScrollBar(new scrollbar());
        jScrollPane2.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);

        setLocationRelativeTo(null);
        this.soPhieu = soPhieu;
        lbSoPhieu.setText(String.valueOf(this.soPhieu));
        setTitle("Multiple Choice");
        fillTable();
        btnok.setEnabled(false);
        btncancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Form_HDNhap().fillTable();
            }
        });
        if (isEnable == false) {
            tblChonNhieuSanPham.setEnabled(false);
            noti_infor("Chi tiết hoá đơn này chỉ thể xem");
            System.out.println("helo");

        }

    }

    void noti_infor(String noti) {
        new Notification(a, Notification.Type.INFO, Notification.Location.TOP_CENTER, noti).showNotification();
    }

    void noti_sucess(String noti) {
        new Notification(a, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, noti).showNotification();
    }

    void noti_warning(String noti) {
        new Notification(a, Notification.Type.WARNING, Notification.Location.TOP_CENTER, noti).showNotification();
    }

    Boolean noti_Choice(String tittle, String detail) {
        MessageDialog obj = new MessageDialog(a);
        obj.showMessage(tittle, detail);
        if (obj.getMessageType() == MessageDialog.MessageType.OK) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelborder1 = new com.raven.swing.panelborder();
        jLabel1 = new javax.swing.JLabel();
        btnok = new button.Button();
        btncancle = new button.Button();
        panelborder2 = new com.raven.swing.panelborder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChonNhieuSanPham = new com.raven.swing.tablemulti();
        jLabel2 = new javax.swing.JLabel();
        lbSoPhieu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelborder1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Số phiếu");

        btnok.setText("OK");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        btncancle.setText("Quay lại");
        btncancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancleActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(null);

        tblChonNhieuSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên NCC", "Tên sản phẩm", "Đơn Vị", "Chọn", "Giá Nhập", "Giá Bán", "Số Lượng", "Số Lượng Kho"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChonNhieuSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblChonNhieuSanPham.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblChonNhieuSanPhamPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tblChonNhieuSanPham);

        javax.swing.GroupLayout panelborder2Layout = new javax.swing.GroupLayout(panelborder2);
        panelborder2.setLayout(panelborder2Layout);
        panelborder2Layout.setHorizontalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelborder2Layout.setVerticalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Vùng thay đổi giá trị");

        lbSoPhieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSoPhieu.setText("0");

        javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
        panelborder1.setLayout(panelborder1Layout);
        panelborder1Layout.setHorizontalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel1)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(btnok, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncancle, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbSoPhieu)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                        .addComponent(panelborder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(171, 171, 171))))
        );
        panelborder1Layout.setVerticalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbSoPhieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelborder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelborder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelborder1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    ChiTietHoaDonNhapDAO cthdDao = new ChiTietHoaDonNhapDAO();
    String soPhieu = "0";
    int index = 0;

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblChonNhieuSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list1 = sanPhamDAO.select_In_Nhap(soPhieu);
            for (SanPham sanpham : list1) {
                NhaCungCap nhaCungCap = nhaCungCapDAO.findById(sanpham.getMaNhaCungCap());
                ChiTietHoaDonNhap chiTietHoaDonNhap = cthdDao.findById(String.valueOf(soPhieu), sanpham.getMaSanPham());
                Object[] row = {
                    nhaCungCap.getMaNhaCungCap() + " - " + nhaCungCap.getTenNhaCungCap(),
                    sanpham.getMaSanPham() + " - " + sanpham.getTenSanPham(),
                    sanpham.getDonVi(),
                    true,
                    chiTietHoaDonNhap.getGiaNhap(),
                    sanpham.getGiaBan(),
                    chiTietHoaDonNhap.getSoLuong(),
                    sanpham.getSoLuong()
                };
                model.addRow(row);
            }

            List<SanPham> list2 = sanPhamDAO.select_notIn_nhap(soPhieu);
            for (SanPham sanpham : list2) {
                NhaCungCap nhaCungCap = nhaCungCapDAO.findById(sanpham.getMaNhaCungCap());
                Object[] row = {
                    nhaCungCap.getMaNhaCungCap() + " - " + nhaCungCap.getTenNhaCungCap(),
                    sanpham.getMaSanPham() + " - " + sanpham.getTenSanPham(),
                    sanpham.getDonVi(),
                    false,
                    1,
                    sanpham.getGiaBan(),
                    1,
                    sanpham.getSoLuong()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu !");
        }
    }

    void update() {
        String soPhieu = this.soPhieu;

        for (int i = 0; i < tblChonNhieuSanPham.getRowCount(); i++) {

            Boolean isChoose = (Boolean) tblChonNhieuSanPham.getValueAt(i, 3);
            ChiTietHoaDonNhap chiTietHoaDonNhap
                    = cthdDao.findById(soPhieu, String.valueOf(tblChonNhieuSanPham.getValueAt(i, 1)).substring(0, 5));

            if (isChoose == false && chiTietHoaDonNhap == null) {

            } else {
                int soLuongNew = (int) tblChonNhieuSanPham.getValueAt(i, 6);
                int giaNhap = (int) tblChonNhieuSanPham.getValueAt(i, 4);

                SanPham sanPham = sanPhamDAO.findById(String.valueOf(tblChonNhieuSanPham.getValueAt(i, 1)).substring(0, 6));
                String maSanPham = sanPham.getMaSanPham();
                int SoLuongKho = sanPham.getSoLuong();
                int SoluongMoi = 0;

                if (isChoose == true && chiTietHoaDonNhap != null) {
                    //-------------UPDATE-----------------//
                    int SoLuongCurrent = chiTietHoaDonNhap.getSoLuong();

                    if (soLuongNew > SoLuongCurrent) {
                        SoluongMoi = soLuongNew - SoLuongCurrent + SoLuongKho;
                        sanPhamDAO.update_soLuong(SoluongMoi, maSanPham);
                        cthdDao.update_soLuong(soLuongNew, giaNhap, soPhieu, maSanPham);

                    } else if (soLuongNew < SoLuongCurrent) {

                        SoluongMoi = SoLuongKho - SoLuongCurrent + soLuongNew;
                        sanPhamDAO.update_soLuong(SoluongMoi, maSanPham);
                        cthdDao.update_soLuong(soLuongNew, giaNhap, soPhieu, maSanPham);

                    }
                } else if (isChoose == true && chiTietHoaDonNhap == null) {
                    //-------------INSERT-----------------//
                    ChiTietHoaDonNhap model = new ChiTietHoaDonNhap();
                    model.setSoPhieu(soPhieu);
                    model.setMaSanPham(maSanPham);
                    model.setSoLuong(soLuongNew);
                    model.setGiaNhap(giaNhap);
                    cthdDao.insert(model);
                    SoluongMoi = SoLuongKho + soLuongNew;
                    sanPhamDAO.update_soLuong(SoluongMoi, maSanPham);
                } else if (isChoose == false && chiTietHoaDonNhap != null) {
                    //-------------DELETE NOT NULL-----------------//
                    SoluongMoi = SoLuongKho - chiTietHoaDonNhap.getSoLuong();
                    sanPhamDAO.update_soLuong(SoluongMoi, maSanPham);
                    cthdDao.delete(soPhieu, maSanPham);
                }
            }
        }
        noti_sucess("CẬP NHẬT THÀNH CÔNG");
        btnok.setEnabled(false);
        fillTable();
    }

    public void addeventbuttonCancle(ActionListener evnet) {
        btncancle.addActionListener(evnet);
    }
    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        this.update();
    }//GEN-LAST:event_btnokActionPerformed

    private void btncancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancleActionPerformed
//        this.dispose();
//        new Form_HDNhap().fillTable();
//        new Form_HDNhap().
        dispose();
        new Form_HDNhap().fillTable();
    }//GEN-LAST:event_btncancleActionPerformed

    private void tblChonNhieuSanPhamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblChonNhieuSanPhamPropertyChange
        // TODO add your handling code here:\
        btnok.setEnabled(false);
        this.index = tblChonNhieuSanPham.getSelectedRow();
        if (this.index >= 0) {

            SanPham sanPham = sanPhamDAO.findById(String.valueOf(tblChonNhieuSanPham.getValueAt(index, 1)).substring(0, 5));
            String maSanPham = sanPham.getMaSanPham();
            ChiTietHoaDonNhap chiTietHoaDonNhap = cthdDao.findById(soPhieu, maSanPham);

            Boolean isChoose = (Boolean) tblChonNhieuSanPham.getValueAt(index, 3);
            int GiaNhap = Integer.valueOf(String.valueOf(tblChonNhieuSanPham.getValueAt(index, 4)));
            int SoLuong = Integer.valueOf(String.valueOf(tblChonNhieuSanPham.getValueAt(index, 6)));
            int GiaHienTai = (int) sanPham.getGiaBan();

            if (isChoose && chiTietHoaDonNhap == null) {
                if (GiaNhap <= 0 && SoLuong <= 0) {
                    noti_warning("GIÁ NHẬP PHẢI LỚN HƠN 0"
                            + "\nSỐ LƯỢNG PHẢI LỚN HƠN 0");
                    tblChonNhieuSanPham.setValueAt("1", index, 4);
                    tblChonNhieuSanPham.setValueAt("1", index, 6);
                } else if (GiaNhap <= 0) {
                    noti_warning("GIÁ NHẬP PHẢI LỚN HƠN 0");
                    tblChonNhieuSanPham.setValueAt("1", index, 4);
                } else if (GiaNhap > GiaHienTai) {
                    noti_warning("GIÁ NHẬP PHẢI <= GIÁ BÁN");
                    tblChonNhieuSanPham.setValueAt("1", index, 4);
                } else if (SoLuong <= 0) {
                    noti_warning("SỐ LƯỢNG PHẢI LỚN HƠN 0");
                    tblChonNhieuSanPham.setValueAt("1", index, 6);
                } else if (GiaNhap == 1) {
                    noti_warning("VUI LÒNG ĐIỀN GIÁ NHẬP");
                } else {
                    btnok.setEnabled(true);
                }

            } else if (isChoose && chiTietHoaDonNhap != null) {
                int soLuongCurrtent = chiTietHoaDonNhap.getSoLuong();
                int giaNhapCurrent = chiTietHoaDonNhap.getGiaNhap();

                if (GiaNhap <= 0 && SoLuong <= 0) {
                    noti_warning("GIÁ NHẬP PHẢI LỚN HƠN 0"
                            + "\nSỐ LƯỢNG PHẢI LỚN HƠN 0");
                    tblChonNhieuSanPham.setValueAt("" + giaNhapCurrent, index, 4);
                    tblChonNhieuSanPham.setValueAt("" + soLuongCurrtent, index, 6);
                } else if (GiaNhap <= 0) {
                    noti_warning("GIÁ NHẬP PHẢI LỚN HƠN 0");
                    tblChonNhieuSanPham.setValueAt("" + giaNhapCurrent, index, 4);
                } else if (GiaNhap > GiaHienTai) {
                    noti_warning("GIÁ NHẬP PHẢI <= GIÁ BÁN");
                    tblChonNhieuSanPham.setValueAt("" + giaNhapCurrent, index, 4);
                } else if (SoLuong <= 0) {
                    noti_warning("SỐ LƯỢNG PHẢI LỚN HƠN 0");
                    tblChonNhieuSanPham.setValueAt("" + soLuongCurrtent, index, 6);
                } else if (GiaNhap == 1) {
                    noti_warning("VUI LÒNG ĐIỀN GIÁ NHẬP");
                } else {
                    btnok.setEnabled(true);
                }
            } else if (isChoose == false && chiTietHoaDonNhap != null) {
                btnok.setEnabled(true);
            }

        }
    }//GEN-LAST:event_tblChonNhieuSanPhamPropertyChange

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Multichoice_CTHDNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btncancle;
    private button.Button btnok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbSoPhieu;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.tablemulti tblChonNhieuSanPham;
    // End of variables declaration//GEN-END:variables
}
