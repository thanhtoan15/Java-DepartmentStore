/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.main;

import DAO.ChiTietHoaDonXuatDAO;
import DAO.HoaDonXuatDAO;
import DAO.KhachHangDAO;
import DAO.NhaCungCapDAO;
import DAO.SanPhamDAO;
import Entity.ChiTietHoaDonXuat;
import Entity.HoaDonXuat;
import Entity.KhachHang;
import Entity.NhaCungCap;
import Entity.SanPham;
import Helper.DialogHelper;
import Helper.Set_icon_frame;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javaswingdev.message.MessageDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Main_Multichoice_CTHDXuat extends javax.swing.JFrame {
    Main a = new Main();

    public Main_Multichoice_CTHDXuat() {
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
        btnOk.setEnabled(true);

    }

    public Main_Multichoice_CTHDXuat(String soPhieu, String MaKhachHang , boolean isEnable) {
        setUndecorated(true);
        initComponents();
        
        jScrollPane2.setVerticalScrollBar(new scrollbar());
        jScrollPane2.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        
        setLocationRelativeTo(null);
        setIconImage(Set_icon_frame.getIconFrame());
        this.soPhieu = soPhieu;
        HoaDonXuat hoaDonXuat = hoaDonXuatDAO.findById(soPhieu);
        float thanhTien = hoaDonXuat.getThanhTien();
        lbThanhTien.setText("" + thanhTien);
        lbSoPhieu.setText(String.valueOf(this.soPhieu));
        KhachHang khachHang = khachHangDAO.findById(MaKhachHang);
        maKhachHang = MaKhachHang;
        lbTenKhachHang.setText(khachHang.getMaKhachHang() + " - " + khachHang.getHoTenKhachHang());
        setTitle("Multiple Choice");
        fillTable();
                System.out.println("helo");

        btnOk.setEnabled(true);
        if(isEnable == false){
            tblChonNhieuSanPham.setEnabled(false);
            noti_infor("Hoá đơn chỉ để xem");
                    System.out.println("helo1111");

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelborder1 = new com.raven.swing.panelborder();
        jLabel1 = new javax.swing.JLabel();
        btnOk = new button.Button();
        button8 = new button.Button();
        panelborder2 = new com.raven.swing.panelborder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChonNhieuSanPham = new com.raven.swing.tablemulti();
        jLabel2 = new javax.swing.JLabel();
        lbSoPhieu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbTenKhachHang = new javax.swing.JLabel();
        lbThanhTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTimKiem = new textfield.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelborder1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Số phiếu");

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        button8.setText("Quay lại");
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(null);
        jScrollPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane2PropertyChange(evt);
            }
        });

        tblChonNhieuSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên NCC", "Tên sản phẩm", "Giá bán", "Đơn vị tính", "Chọn", "Giảm giá", "Số lượng", "Số Lượng Kho"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChonNhieuSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblChonNhieuSanPham);

        javax.swing.GroupLayout panelborder2Layout = new javax.swing.GroupLayout(panelborder2);
        panelborder2.setLayout(panelborder2Layout);
        panelborder2Layout.setHorizontalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelborder2Layout.setVerticalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Vùng thay đổi giá trị");

        lbSoPhieu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSoPhieu.setText("1");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Khách Hàng :");

        lbTenKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTenKhachHang.setText("Tên Khách Hàng");

        lbThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbThanhTien.setText("0 000 000.0");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thành Tiền");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tìm Kiếm");

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
        panelborder1.setLayout(panelborder1Layout);
        panelborder1Layout.setHorizontalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                        .addComponent(panelborder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(185, 185, 185))))
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbSoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbThanhTien)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))))
        );
        panelborder1Layout.setVerticalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbSoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lbTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lbThanhTien)
                            .addComponent(jLabel5)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelborder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
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
    ChiTietHoaDonXuatDAO cthdDao = new ChiTietHoaDonXuatDAO();
    KhachHangDAO khachHangDAO = new KhachHangDAO();
    HoaDonXuatDAO hoaDonXuatDAO = new HoaDonXuatDAO();
    String soPhieu = "";
    String maKhachHang = "";
    int index = 0;

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
    ArrayList<String> listTimKiem = new ArrayList<>();

    void load_timKiem() {
        try {
            listTimKiem.clear();
            List<SanPham> list2 = sanPhamDAO.select_notIn_xuat(soPhieu);
            for (SanPham sanpham : list2) {
                if (sanpham.getSoLuong() > 0) {
                    listTimKiem.add(sanpham.getTenSanPham().trim());
                }
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu loai hang!");
        }
        AutoCompleteDecorator.decorate(txtTimKiem, listTimKiem, false);
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblChonNhieuSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list1 = sanPhamDAO.select_In_Xuat(soPhieu);
            for (SanPham sanpham : list1) {
                NhaCungCap nhaCungCap = nhaCungCapDAO.findById(sanpham.getMaNhaCungCap());
                ChiTietHoaDonXuat chiTietHoaDonXuat = cthdDao.findById(String.valueOf(soPhieu), sanpham.getMaSanPham());
                Object[] row = {
                    nhaCungCap.getMaNhaCungCap() + " - " + nhaCungCap.getTenNhaCungCap(),
                    sanpham.getMaSanPham() + " - " + sanpham.getTenSanPham(),
                    sanpham.getGiaBan(),
                    sanpham.getDonVi(),
                    true,
                    chiTietHoaDonXuat.getGiamGia(),
                    chiTietHoaDonXuat.getSoLuong(),
                    sanpham.getSoLuong()
                };
                model.addRow(row);
            }

            List<SanPham> list2 = sanPhamDAO.select_notIn_xuat(soPhieu);
            for (SanPham sanpham : list2) {
                if (sanpham.getSoLuong() > 0) {
                    NhaCungCap nhaCungCap = nhaCungCapDAO.findById(sanpham.getMaNhaCungCap());
                    Object[] row = {
                        nhaCungCap.getMaNhaCungCap() + " - " + nhaCungCap.getTenNhaCungCap(),
                        sanpham.getMaSanPham() + " - " + sanpham.getTenSanPham(),
                        sanpham.getGiaBan(),
                        sanpham.getDonVi(),
                        false,
                        0,
                        1,
                        sanpham.getSoLuong()
                    };
                    model.addRow(row);
                }

            }

        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu loai hang!");
        }
    }

    void fillTable(String tenSanPham) {
        DefaultTableModel model = (DefaultTableModel) tblChonNhieuSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list2 = sanPhamDAO.select_notIn_xuat(soPhieu);
            for (SanPham sanpham : list2) {
                if (sanpham.getSoLuong() > 0 && sanpham.getTenSanPham().contains(tenSanPham)) {
                    NhaCungCap nhaCungCap = nhaCungCapDAO.findById(sanpham.getMaNhaCungCap());
                    Object[] row = {
                        nhaCungCap.getMaNhaCungCap() + " - " + nhaCungCap.getTenNhaCungCap(),
                        sanpham.getMaSanPham() + " - " + sanpham.getTenSanPham(),
                        sanpham.getGiaBan(),
                        sanpham.getDonVi(),
                        false,
                        0,
                        1,
                        sanpham.getSoLuong()
                    };
                    model.addRow(row);
                }

            }

        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu loai hang!");
        }
    }

    void update() {
        String soPhieu = this.soPhieu;
        for (int i = 0; i < tblChonNhieuSanPham.getRowCount(); i++) {

            Boolean isChoose = (Boolean) tblChonNhieuSanPham.getValueAt(i, 4);
            ChiTietHoaDonXuat chiTietHoaDonXuat
                    = cthdDao.findById(soPhieu, String.valueOf(tblChonNhieuSanPham.getValueAt(i, 1)).substring(0, 5));

            if (isChoose == false && chiTietHoaDonXuat == null) {

            } else {
                SanPham sanPham = sanPhamDAO.findById(String.valueOf(tblChonNhieuSanPham.getValueAt(i, 1)).substring(0, 5));
                String maSanPham = sanPham.getMaSanPham();
                int soLuongNew = (int) tblChonNhieuSanPham.getValueAt(i, 6);
                float giaBan = (float) tblChonNhieuSanPham.getValueAt(i, 2);
                float giamGia = Float.valueOf(tblChonNhieuSanPham.getValueAt(i, 5) + "");

                HoaDonXuat hoaDonXuat = hoaDonXuatDAO.findById(soPhieu);
                float thanhTien = hoaDonXuat.getThanhTien();

                int SoLuongKho = sanPham.getSoLuong();
                int SoluongMoi = 0;
                if (isChoose == true && chiTietHoaDonXuat != null) {
                    //-------------UPDATE-----------------//
                    int SoLuongCurrent = chiTietHoaDonXuat.getSoLuong();
                    if (soLuongNew > SoLuongCurrent) {
                        //---UPDATE SAN PHAM
                        SoluongMoi = SoLuongKho + SoLuongCurrent - soLuongNew;
                        sanPhamDAO.update_soLuong(SoluongMoi, maSanPham);
                        //---UPDATE HDCT XUAT
                        cthdDao.update_soLuong(soLuongNew, giamGia, soPhieu, maSanPham);
                        //---UPDATE HD XUAT
                        thanhTien -= (SoLuongCurrent * giaBan) - giamGia * (SoLuongCurrent * giaBan) / 100;
                        thanhTien += (SoluongMoi * giaBan) - giamGia * (SoluongMoi * giaBan) / 100;
                        lbThanhTien.setText(thanhTien + "");
                        hoaDonXuatDAO.update_ThanhTien(thanhTien, soPhieu);

                    } else if (soLuongNew < SoLuongCurrent) {
                        //---UPDATE SAN PHAM
                        SoluongMoi = SoLuongKho + soLuongNew - SoLuongCurrent;
                        sanPhamDAO.update_soLuong(SoluongMoi, maSanPham);
                        //---UPDATE HDCT XUAT
                        cthdDao.update_soLuong(soLuongNew, giamGia, soPhieu, maSanPham);

                        //---UPDATE HD XUAT
                        thanhTien -= (SoLuongCurrent * giaBan) - giamGia * (SoLuongCurrent * giaBan) / 100;
                        thanhTien += (soLuongNew * giaBan) - giamGia * (soLuongNew * giaBan) / 100;
                        lbThanhTien.setText(thanhTien + "");
                        hoaDonXuatDAO.update_ThanhTien(thanhTien, soPhieu);
                    }
                } else if (isChoose == true && chiTietHoaDonXuat == null) {
//-------------INSERT-----------------//
                    //INSERT CTHD XUAT 
                    ChiTietHoaDonXuat model = new ChiTietHoaDonXuat();
                    model.setSoPhieu(soPhieu);
                    model.setMaSanPham(maSanPham);
                    model.setSoLuong(soLuongNew);
                    model.setGiamGia(giamGia);
                    cthdDao.insert(model);
                    //---UPDATE SAN PHAM
                    SoluongMoi = SoLuongKho - soLuongNew;
                    sanPhamDAO.update_soLuong(SoluongMoi, maSanPham);
                    //---UPDATE HD XUAT
                    thanhTien += (soLuongNew * giaBan) - giamGia * (soLuongNew * giaBan) / 100;
                    lbThanhTien.setText(thanhTien + "");
                    hoaDonXuatDAO.update_ThanhTien(thanhTien, soPhieu);

                } else if (isChoose == false && chiTietHoaDonXuat != null) {
                    //-------------DELETE NOT NULL-----------------//
                    int SoLuongCurrent = chiTietHoaDonXuat.getSoLuong();
                    //---UPDATE SAN PHAM
                    SoluongMoi = SoLuongKho + SoLuongCurrent;
                    sanPhamDAO.update_soLuong(SoluongMoi, maSanPham);
                    //---DELETE CTHD XUAT 
                    cthdDao.delete(soPhieu, maSanPham);
                    //---UPDATE HD XUAT 
                    thanhTien -= (SoLuongCurrent * giaBan) - giamGia * (SoLuongCurrent * giaBan) / 100;
                    lbThanhTien.setText(thanhTien + "");
                    hoaDonXuatDAO.update_ThanhTien(thanhTien, soPhieu);
                }
            }

        }
        noti_sucess("CẬP NHẬT THÀNH CÔNG");
        btnOk.setEnabled(false);
        fillTable();
//        tblChonNhieuSanPham.setRowSelectionInterval(index, index);
    }

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        this.update();
    }//GEN-LAST:event_btnOkActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
        this.dispose();
    }//GEN-LAST:event_button8ActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Enter was pressed. Your code goes here.
            if (txtTimKiem.getText().equals("")) {
                fillTable();
            } else {
                fillTable(txtTimKiem.getText().trim());
            }
        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void jScrollPane2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPane2PropertyChange
        // TODO add your handling code here:
        btnOk.setEnabled(false);
        this.index = tblChonNhieuSanPham.getSelectedRow();
        if (this.index >= 0) {

            SanPham sanPham
                    = sanPhamDAO.findById(String.valueOf(tblChonNhieuSanPham.getValueAt(index, 1)).substring(0, 5));
            ChiTietHoaDonXuat chiTietHoaDonXuat
                    = cthdDao.findById(soPhieu, String.valueOf(tblChonNhieuSanPham.getValueAt(index, 1)).substring(0, 5));

            int SoLuongKho = sanPham.getSoLuong();
            Boolean isChoose = (Boolean) tblChonNhieuSanPham.getValueAt(index, 4);
            int SoLuong = Integer.valueOf(String.valueOf(tblChonNhieuSanPham.getValueAt(index, 6)));
            float GiamGia = Float.valueOf(String.valueOf(tblChonNhieuSanPham.getValueAt(index, 5)));

            if (isChoose && chiTietHoaDonXuat == null) {
                if (GiamGia < 0 && SoLuong <= 0) {
                    noti_warning("GIẢM GIÁ KHÔNG ĐƯỢC ÂM"
                            + "\nSỐ LƯỢNG KHÔNG ĐƯỢC ÂM");
                    tblChonNhieuSanPham.setValueAt("0", index, 5);
                    tblChonNhieuSanPham.setValueAt("1", index, 6);
                } else if (GiamGia < 0) {
                    noti_warning("GIẢM GIÁ KHÔNG ĐƯỢC ÂM");
                    tblChonNhieuSanPham.setValueAt("0", index, 5);
                } else if (SoLuong <= 0) {
                    noti_warning("SỐ LƯỢNG PHẢI LỚN HƠN KHÔNG");
                    tblChonNhieuSanPham.setValueAt("1", index, 6);
                } else if (SoLuong > SoLuongKho) {
                    noti_warning("NHẬP SỐ LƯỢNG < SỐ LƯỢNG KHO 1");
                    tblChonNhieuSanPham.setValueAt("1", index, 6);
                } else {
                    btnOk.setEnabled(true);
                }

            } else if (isChoose && chiTietHoaDonXuat != null) {
                int SoLuongCurrent = chiTietHoaDonXuat.getSoLuong();
                float GiamGiaCurrent = chiTietHoaDonXuat.getGiamGia();

                if (GiamGia < 0 && SoLuong <= 0) {
                    noti_warning("GIẢM GIÁ KHÔNG ĐƯỢC ÂM"
                            + "\nSỐ LƯỢNG PHẢI LỚN HƠN KHÔNG");
                    tblChonNhieuSanPham.setValueAt("" + GiamGiaCurrent, index, 5);
                    tblChonNhieuSanPham.setValueAt("" + SoLuongCurrent, index, 6);
                } else if (GiamGia < 0) {
                    noti_warning("GIẢM GIÁ KHÔNG ĐƯỢC ÂM");
                    tblChonNhieuSanPham.setValueAt("" + GiamGiaCurrent, index, 5);
                } else if (SoLuong <= 0) {
                    noti_warning("SỐ LƯỢNG PHẢI LỚN HƠN KHÔNG");
                    tblChonNhieuSanPham.setValueAt("" + SoLuongCurrent, index, 6);
                } else if (SoLuong > (SoLuongKho + SoLuongCurrent)) {
                    noti_warning("NHẬP SỐ LƯỢNG < SỐ LƯỢNG KHO 2");
                    tblChonNhieuSanPham.setValueAt("" + SoLuongCurrent, index, 6);
                } else {
                    btnOk.setEnabled(true);
                }
            } else if (isChoose == false && chiTietHoaDonXuat != null) {
                btnOk.setEnabled(true);

            }
        }
    }//GEN-LAST:event_jScrollPane2PropertyChange

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Multichoice_CTHDXuat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnOk;
    private button.Button button8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbSoPhieu;
    private javax.swing.JLabel lbTenKhachHang;
    private javax.swing.JLabel lbThanhTien;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.tablemulti tblChonNhieuSanPham;
    private textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
