package com.raven.form;

import DAO.NhaCungCapDAO;
import Entity.NhaCungCap;
import Helper.DialogHelper;
import Validate.Validate;
import com.raven.main.Main;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.List;
import javaswingdev.message.MessageDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Form_NhaCungCap extends javax.swing.JPanel {

    NhaCungCap ncc = new NhaCungCap();

    public Form_NhaCungCap(int index) {
        initComponents();
        setOpaque(false);
//        jLabel1.setText("Form " + index);
        jScrollPane1.setVerticalScrollBar(new scrollbar());
        jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        fillTable();
        setStatus(true);
        txtMaNCC.setEnabled(true);
    }
    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
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
        DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
        model.setRowCount(0);
        try {
            List<NhaCungCap> list = nhaCungCapDAO.select();
            for (NhaCungCap NCC : list) {
                Object[] row = {
                    NCC.getMaNhaCungCap(),
                    NCC.getTenNhaCungCap(),
                    NCC.getSdtNhaCungCap(),
                    NCC.getEmailNhaCungCap(),
                    NCC.getDiaChiNhaCungCap()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu loai hang!");
        }
    }

    NhaCungCap getModel() {
        NhaCungCap model = new NhaCungCap();
        model.setMaNhaCungCap(txtMaNCC.getText());
        model.setTenNhaCungCap(txtTenNCC.getText());
        model.setSdtNhaCungCap(txtSDT.getText());
        model.setEmailNhaCungCap(txtEmail.getText());
        model.setDiaChiNhaCungCap(txtDiaChi.getText());
        return model;
    }

    void setModel(NhaCungCap model) {
        txtMaNCC.setText(model.getMaNhaCungCap());
        txtTenNCC.setText(model.getTenNhaCungCap());
        txtSDT.setText(model.getSdtNhaCungCap());
        txtEmail.setText(model.getEmailNhaCungCap());
        txtDiaChi.setText(model.getDiaChiNhaCungCap());
    }

    void setStatus(boolean insertable) {
        btnthem.setEnabled(insertable);
        btnsua.setEnabled(!insertable);

        tblNCC.setRowSelectionAllowed(!insertable);
        if (nhaCungCapDAO.check_lh(txtMaNCC.getText()).equals("0")) {
            btnxoa.setEnabled(false);
        } else {
            btnxoa.setEnabled(true);
        }
        if (ncc != null) {
            txtMaNCC.setEnabled(false);
        } else {
            txtMaNCC.setEnabled(true);
        }

        boolean first = this.index > 0;
        boolean last = this.index < tblNCC.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPre.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);
    }

    void edit() {
        try {
            String makh = (String) tblNCC.getValueAt(index, 0);
            NhaCungCap model = nhaCungCapDAO.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
        }
    }

    void clear() {
        this.setModel(new NhaCungCap());
        this.setStatus(true);
        txtMaNCC.requestFocus();
        txtDiaChi.setBackground(Color.white);
        txtEmail.setBackground(Color.white);
        txtSDT.setBackground(Color.white);
        txtTenNCC.setBackground(Color.white);
        txtMaNCC.setBackground(Color.white);
    }

    void insert() {
        NhaCungCap model = getModel();
        try {
            nhaCungCapDAO.insert(model);
            this.fillTable();
            noti_sucess("Thêm mới thành công!");
        } catch (HeadlessException e) {
            noti_warning("Thêm mới thất bại!");
        }
    }

    void update() {

        NhaCungCap model = getModel();
        try {
            nhaCungCapDAO.update(model);
            this.fillTable();
            noti_sucess("Cập nhật thành công!");
        } catch (Exception e) {
            noti_warning("Cập nhật thất bại!");
        }
    }

    void delete() {
        if (noti_Choice("Bạn thực sự muốn xóa nhà cung cấp này?", "Không thể khôi phục sau khi xoá")) {
            String makh = txtMaNCC.getText();
            try {
                nhaCungCapDAO.delete(makh);
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
        if (Validate.isEmpty(txtMaNCC, str, "Mã khách hàng không được để trống")) {
            noti_warning("Mã nhà cung cấp không được để trống");
            return false;
        }
        if (!Validate.isNCC(txtMaNCC, str, "Mã nhân viên không đúng định dạng")) {
            noti_warning("Mã nhà cung cấp không đúng định dạng");
            return false;

        }
        if (Validate.isEmpty(txtTenNCC, str, "Họ tên không được để trống")) {
            noti_warning("Tên nhà cung cấp không được để trống");
            return false;
        }
        if (!Validate.isName(txtTenNCC, str, "Họ tên không đúng định dạng")) {
            noti_warning("Tên nhà cung cấp không đúng định dạng");
            return false;
        }

        if (Validate.isEmpty(txtEmail, str, "Email không được để trống")) {
            noti_warning("Email không được để trống");
            return false;
        }
        if (!Validate.isEmail(txtEmail, str, "Email không đúng định dạng")) {
            noti_warning("Email không đúng định dạng");
            return false;
        }
        if (Validate.isEmpty(txtSDT, str, "Số điện thoại không được để trống")) {
            noti_warning("Số điện thoại không được để trống");
            return false;
        }
        if (!Validate.isSDT(txtSDT, str, "")) {
            noti_warning("Số điện thoại chưa đúng định dạng");
            return false;
        }
        if (Validate.isEmpty(txtDiaChi, str, "Mã khách hàng không được để trống")) {
            noti_warning("Địa chỉ không được để trống");
            return false;
        }
//        if(!Validate.isDiaChi(txtDiaChi, str, "Mã nhân viên không đúng định dạng")){
//            noti_warning("Địa chỉ không đúng định dạng");
//            return false;
//            
//        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelborder1 = new com.raven.swing.panelborder();
        jLabel1 = new javax.swing.JLabel();
        txtMaNCC = new textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenNCC = new textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new textfield.TextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new textfield.TextField();
        txtDiaChi = new textfield.TextField();
        jLabel9 = new javax.swing.JLabel();
        btnLast = new button.Button();
        btnFirst = new button.Button();
        btnPre = new button.Button();
        btnNext = new button.Button();
        btnthem = new button.Button();
        btnxoa = new button.Button();
        btnsua = new button.Button();
        moi = new button.Button();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        panelborder2 = new com.raven.swing.panelborder();
        jLabel3 = new javax.swing.JLabel();
        panelborder3 = new com.raven.swing.panelborder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNCC = new com.raven.swing.table();

        panelborder1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhà cung cấp");

        txtMaNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Tên nhà cung cấp");

        txtTenNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setText("Số điện thoại");

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel8.setText("Email");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ");

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

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("*");

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("*");

        javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
        panelborder1.setLayout(panelborder1Layout);
        panelborder1Layout.setHorizontalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        panelborder1Layout.setVerticalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(0, 0, 0)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel20))
                                .addGap(0, 0, 0)
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        panelborder2.setBackground(new java.awt.Color(230, 230, 230));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Danh sách nhà cung cấp");

        panelborder3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NCC", "Tên NCC", "SDT", "Email", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNCC.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNCC);

        javax.swing.GroupLayout panelborder3Layout = new javax.swing.GroupLayout(panelborder3);
        panelborder3.setLayout(panelborder3Layout);
        panelborder3Layout.setHorizontalGroup(
            panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelborder3Layout.setVerticalGroup(
            panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelborder2Layout = new javax.swing.GroupLayout(panelborder2);
        panelborder2.setLayout(panelborder2Layout);
        panelborder2Layout.setHorizontalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelborder2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelborder2Layout.setVerticalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
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
                .addGap(10, 10, 10)
                .addComponent(panelborder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNCCMouseClicked
        // TODO add your handling code here:
        tblNCC.setRowSelectionAllowed(true);
        if (evt.getClickCount() == 1) {
            this.index = tblNCC.getSelectedRow();
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblNCCMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblNCC.getRowCount() - 1;
        tblNCC.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        tblNCC.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        this.index--;
        tblNCC.setRowSelectionInterval(index, index);
        this.edit();

    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:

        this.index++;
        tblNCC.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed

        ncc.setMaNhaCungCap(txtMaNCC.getText());
        List<NhaCungCap> list = nhaCungCapDAO.select();
        if (nhaCungCapDAO.findById(txtMaNCC.getText()) == null) {
            if (validateform()) {
                this.insert();
                clear();
            }
        } else {
            noti_warning("Mã nhà cung cấp đã tồn tại");
            txtMaNCC.setBackground(new Color(250, 250, 210));
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
        txtMaNCC.setEnabled(true);
        tblNCC.setRowSelectionAllowed(false);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private button.Button moi;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.panelborder panelborder3;
    private com.raven.swing.table tblNCC;
    private textfield.TextField txtDiaChi;
    private textfield.TextField txtEmail;
    private textfield.TextField txtMaNCC;
    private textfield.TextField txtSDT;
    private textfield.TextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
