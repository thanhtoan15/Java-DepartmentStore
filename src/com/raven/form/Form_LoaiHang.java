package com.raven.form;

import DAO.LoaiHangDAO;
import Entity.LoaiHang;
import Helper.DialogHelper;
import Helper.Set_icon_frame;
import Validate.Validate;
import com.raven.main.Main;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javaswingdev.message.MessageDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Form_LoaiHang extends javax.swing.JPanel {

    LoaiHang lh = new LoaiHang();

    public Form_LoaiHang(int index) {
        initComponents();
        setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new scrollbar());
        jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        fillTable();
        setStatus(true);
        txtMaLoai.setEnabled(true);
    }

    LoaiHangDAO loaiHangDAO = new LoaiHangDAO();
    int index = 0;

    Main a = new Main();

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

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblLoaiHang.getModel();
        model.setRowCount(0);
        try {
            List<LoaiHang> list = loaiHangDAO.select();
//            System.out.println(""+list.size());

            for (LoaiHang loaiHang : list) {
                Object[] row = {
                    loaiHang.getMaLoaiHang(),
                    loaiHang.getTenLoaiHang()
                };
                model.addRow(row);
            }

        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu !");
            e.printStackTrace();
        }
    }

    LoaiHang getModel() {
        LoaiHang model = new LoaiHang();
        model.setMaLoaiHang(txtMaLoai.getText());
        model.setTenLoaiHang(txtTenLoai.getText());
        return model;
    }

    void setModel(LoaiHang model) {
        txtMaLoai.setText(model.getMaLoaiHang());
        txtTenLoai.setText(model.getTenLoaiHang());
    }

    void setStatus(boolean insertable) {
        btnsua.setEnabled(!insertable);
        moi.setEnabled(!insertable);
        btnthem.setEnabled(insertable);
        if (loaiHangDAO.check_lh(txtMaLoai.getText()).equals("0")) {
            btnxoa.setEnabled(false);
        } else {
            btnxoa.setEnabled(true);
        }
        if (lh != null) {
            txtMaLoai.setEnabled(false);
        } else {
            txtMaLoai.setEnabled(true);
        }
        boolean first = this.index > 0;
        boolean last = this.index < tblLoaiHang.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPre.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);
    }

    void edit() {
        try {
            String makh = (String) tblLoaiHang.getValueAt(index, 0);
            LoaiHang model = loaiHangDAO.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
        }
    }

    void clear() {
        this.setModel(new LoaiHang());
        this.setStatus(true);
        txtMaLoai.requestFocus();
        txtMaLoai.setBackground(Color.white);
        txtTenLoai.setBackground(Color.white);
    }

    void insert() {
        LoaiHang model = getModel();
        try {
            loaiHangDAO.insert(model);
            this.fillTable();
            noti_sucess("Thêm mới thành công!");
        } catch (HeadlessException e) {
            noti_warning("Thêm mới thất bại!");
        }
    }

    void update() {

        LoaiHang model = getModel();
        try {
            loaiHangDAO.update(model);
            this.fillTable();
            noti_sucess("Cập nhật thành công!");
        } catch (Exception e) {
            noti_warning("Cập nhật thất bại!");
        }
    }

    void delete() {
        if (noti_Choice("Bạn thực sự muốn xóa loại hàng này?", "Không thể khôi phục sau khi xoá")) {
            String makh = txtMaLoai.getText();
            try {
                loaiHangDAO.delete(makh);
                this.fillTable();
                this.clear();
                noti_sucess("Xóa thành công!");
            } catch (Exception e) {
                noti_warning("Xóa thất bại!");
            }
        }
    }

    private boolean validateform() {
        StringBuilder str = new StringBuilder();
        if (Validate.isEmpty(txtMaLoai, str, "Mã loại hàng không được để trống")) {
            noti_warning("Mã nhân viên không được để trống");
            return false;
        }
        if (!Validate.isMH(txtMaLoai, str, "Mã loại hàng không được để trống")) {
            noti_warning("Mã loại hàng không đúng định dạng");
            return false;

        }
        if (Validate.isEmpty(txtTenLoai, str, "Tên hàng không được để trống")) {
            noti_warning("Tên hàng không được để trống");
            return false;
        }
        if (!Validate.isTH(txtTenLoai, str, "Tên hàng không được để trống")) {
            noti_warning("Tên hàng không đúng định dạng");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelborder1 = new com.raven.swing.panelborder();
        jLabel2 = new javax.swing.JLabel();
        txtMaLoai = new textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenLoai = new textfield.TextField();
        btnLast = new button.Button();
        btnFirst = new button.Button();
        btnPre = new button.Button();
        btnNext = new button.Button();
        btnthem = new button.Button();
        btnxoa = new button.Button();
        btnsua = new button.Button();
        moi = new button.Button();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        panelborder2 = new com.raven.swing.panelborder();
        jLabel3 = new javax.swing.JLabel();
        panelborder3 = new com.raven.swing.panelborder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoaiHang = new com.raven.swing.table();

        panelborder1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Mã Loại");

        txtMaLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Tên Loại");

        txtTenLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/forward-button.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/first-button.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/prev-button.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/play-button.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnthem.setText("Thêm");
        btnthem.setToolTipText("");
        btnthem.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnxoa.setText("Xóa");
        btnxoa.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        moi.setText("Làm mới");
        moi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moiActionPerformed(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
        panelborder1.setLayout(panelborder1Layout);
        panelborder1Layout.setHorizontalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(169, 169, 169))))
        );
        panelborder1Layout.setVerticalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel17))
                        .addGap(0, 0, 0)
                        .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel18))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))))
        );

        panelborder2.setBackground(new java.awt.Color(230, 230, 230));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Danh sách loại hàng");

        panelborder3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        tblLoaiHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã loại", "Tên loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLoaiHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblLoaiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLoaiHang);

        javax.swing.GroupLayout panelborder3Layout = new javax.swing.GroupLayout(panelborder3);
        panelborder3.setLayout(panelborder3Layout);
        panelborder3Layout.setHorizontalGroup(
            panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelborder3Layout.setVerticalGroup(
            panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelborder2Layout = new javax.swing.GroupLayout(panelborder2);
        panelborder2.setLayout(panelborder2Layout);
        panelborder2Layout.setHorizontalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGroup(panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel3))
                    .addGroup(panelborder2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelborder2Layout.setVerticalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelborder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelborder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelborder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(panelborder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblLoaiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiHangMouseClicked
        // TODO add your handling code here:
        tblLoaiHang.setRowSelectionAllowed(true);
        if (evt.getClickCount() == 1) {
            this.index = tblLoaiHang.getSelectedRow();
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblLoaiHangMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblLoaiHang.getRowCount() - 1;
        tblLoaiHang.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        tblLoaiHang.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        this.index--;
        tblLoaiHang.setRowSelectionInterval(index, index);
        this.edit();

    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:

        this.index++;
        tblLoaiHang.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        lh.setMaLoaiHang(txtMaLoai.getText());
        List<LoaiHang> list = loaiHangDAO.select();
        if (loaiHangDAO.findById(txtMaLoai.getText()) == null) {
            if (validateform()) {
                this.insert();
                clear();
            }
        } else {
            noti_warning("Mã loại hàng đã tồn tại");
            txtMaLoai.setBackground(new Color(250, 250, 210));
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        if (validateform()) {
            this.update();
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moiActionPerformed
        this.clear();
        txtMaLoai.setEnabled(true);
        tblLoaiHang.setRowSelectionAllowed(false);
    }//GEN-LAST:event_moiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnFirst;
    private button.Button btnLast;
    private button.Button btnNext;
    private button.Button btnPre;
    private button.Button btnsua;
    private button.Button btnthem;
    private button.Button btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private button.Button moi;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.panelborder panelborder3;
    private com.raven.swing.table tblLoaiHang;
    private textfield.TextField txtMaLoai;
    private textfield.TextField txtTenLoai;
    // End of variables declaration//GEN-END:variables
}
