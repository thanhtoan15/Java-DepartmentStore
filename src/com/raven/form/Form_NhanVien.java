package com.raven.form;

import DAO.NhanVienDAO1;
import Entity.NhanVien;
import Helper.ShareHelper;
import Validate.Validate;
import com.raven.main.Main;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.List;
import javaswingdev.message.MessageDialog;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Form_NhanVien extends javax.swing.JPanel {

    NhanVienDAO1 NVDao = new NhanVienDAO1();
    NhanVien nv = new NhanVien();
    int index = 0;

    public Form_NhanVien(int index) {
        initComponents();
        setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new scrollbar());
        jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        setStatus(true);
        fillTable();
        rdoQuanly.setSelected(true);
    }
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
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);

        try {
            List<NhanVien> list = NVDao.select();
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNhanVien(),
                    nv.getHoTenNhanVien(),
                    nv.getEmailNhanVien(),
                    nv.getSdtNhanVien(),
                    nv.getVaiTroNhanVien() == 1 ? "Nhân viên" : "Quản Lý ",
                    nv.getTrangThai()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu Nhân viên!");
            e.printStackTrace();
        }
    }

    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNhanVien(txtManv.getText());
        model.setHoTenNhanVien(txthoten.getText());
        model.setEmailNhanVien(txtEmail.getText());
        model.setSdtNhanVien(txtSDT.getText());
        model.setVaiTroNhanVien(rdoQuanly.isSelected() ? 0 : 1);
        nv = model;
        return model;
    }

    void setModel(NhanVien model) {
        txtManv.setText(model.getMaNhanVien());
        txthoten.setText(model.getHoTenNhanVien());
        txtEmail.setText(model.getEmailNhanVien());
        txtSDT.setText(model.getSdtNhanVien());
        int a = model.getVaiTroNhanVien();
        if (a == 0) {
            rdoQuanly.setSelected(true);
        } else {
            rdoNhanvien.setSelected(true);
        }
    }

    void setStatus(boolean insertable) {
        nv = NVDao.findById(getModel().getMaNhanVien());
        moi.setEnabled(!insertable);
        btnthem.setEnabled(insertable);
        if (nv != null) {
            txtManv.setEnabled(false);
            if (!nv.getTrangThai().equalsIgnoreCase("Đi Làm")) {
                btnKhoiPhuc.setEnabled(true);
            } else {
                btnKhoiPhuc.setEnabled(false);
            }
        } else {
            txtManv.setEnabled(true);
        }
        btnsua.setEnabled(!insertable);
        btnxoa.setEnabled(!insertable);
        if (nv != null) {
            if (NVDao.check_nv(nv.getMaNhanVien()).equals("0") && nv.getTrangThai().equalsIgnoreCase("Đã Nghỉ")) {
                btnxoa.setEnabled(false);
            } else {
                btnxoa.setEnabled(true);
            }
        }

        boolean first = this.index > 0;
        boolean last = this.index < tblNhanVien.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPre.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);

    }

        void edit() {
        try {
            String makh = (String) tblNhanVien.getValueAt(index, 0);
            NhanVien model = NVDao.findById(makh);
//            nv = model;
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
                                    System.out.println(""+nv);

            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
        }
    }

    void clear() {
        this.setStatus(true);
        txtManv.setText("");
        txtManv.setBackground(Color.white);
        txthoten.setText("");
        txthoten.setBackground(Color.white);
        txtEmail.setText("");
        txtEmail.setBackground(Color.white);
        txtSDT.setText("");
        txtSDT.setBackground(Color.white);
//        buttonGroup1.clearSelection();
        txtManv.requestFocus();

    }

    void insert() {
        NhanVien model = getModel();
        try {
            NVDao.insert(model);
            this.fillTable();
            noti_sucess("Thêm mới thành công!");
        } catch (HeadlessException e) {
            noti_warning("Thêm mới thất bại!");
        }
    }

    void update() {

        NhanVien model = getModel();
        try {
            NVDao.update(model);
            this.fillTable();
            noti_sucess("Cập nhật thành công!");
        } catch (Exception e) {
            noti_warning("Cập nhật thất bại!");
        }
    }

    void delete() {
        if (noti_Choice("Bạn thực sự muốn xóa nhân viên này?", null)) {
            String maNV = txtManv.getText();
            try {

                if (!NVDao.check_nv(maNV).equals("0")) {
                    NVDao.delete(maNV);
                } else {
                    NVDao.update(maNV);
                }
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
        if (Validate.isEmpty(txtManv, str, "Mã nhân viên không được để trống")) {
            noti_warning("Mã nhân viên không được để trống");
            return false;
        }

        if (!Validate.isID(txtManv, str, "Mã nhân viên không đúng định dạng")) {
            noti_warning("Mã nhân viên không đúng định dạng");
            return false;

        }
//        if (Validate.isEmpty(txthoten, str, "Họ tên không được để trống")) {
//            noti_warning("Họ tên không được để trống");
//            return false;
//        }
        if (!Validate.isName(txthoten, str, "Họ tên không đúng định dạng")) {
            noti_warning("Họ tên không đúng định dạng");
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
       if(nv!= null){
           
       }else{
                       if (NVDao.findByEmail(txtEmail.getText()) != null) {

            if (NVDao.findByEmail(txtEmail.getText()).getEmailNhanVien().equalsIgnoreCase(txtEmail.getText())) {
                noti_warning("Email đã được sử dụng");
                return false;
            }
        }
       }
//        if (Validate.isEmailDuplucation(txtEmail, str, "Email đã tồn tại:")) {
//            noti_warning("Email đã tồn tại");
//            return false;
//        }
//        if (Validate.isEmpty(txtSDT, str, "Số điện thoại không được để trống")) {
//            noti_warning("Số điện thoại không được để trống");
//            return false;
//        }
        if (!Validate.isSDT(txtSDT, str, "")) {
            noti_warning("Số điện thoại chưa đúng định dạng");
            return false;
        }
        if (rdoQuanly.isSelected() == false && rdoNhanvien.isSelected() == false) {
            noti_warning("Chưa chọn vài trò");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelborder1 = new com.raven.swing.panelborder();
        jLabel1 = new javax.swing.JLabel();
        txtManv = new textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        txthoten = new textfield.TextField();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new textfield.TextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        rdoQuanly = new radio_button.RadioButtonCustom();
        rdoNhanvien = new radio_button.RadioButtonCustom();
        btnthem = new button.Button();
        btnxoa = new button.Button();
        btnsua = new button.Button();
        moi = new button.Button();
        btnLast = new button.Button();
        btnFirst = new button.Button();
        btnPre = new button.Button();
        btnNext = new button.Button();
        btnKhoiPhuc = new button.Button();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        panelborder2 = new com.raven.swing.panelborder();
        jLabel3 = new javax.swing.JLabel();
        panelborder3 = new com.raven.swing.panelborder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new com.raven.swing.table();

        panelborder1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhân viên");

        txtManv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Họ và tên");

        txthoten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setText("Số điện thoại");

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel8.setText("Email");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Vai trò");

        rdoQuanly.setBackground(new java.awt.Color(230, 230, 230));
        rdoQuanly.setBorder(null);
        buttonGroup1.add(rdoQuanly);
        rdoQuanly.setText("Quản Lý");
        rdoQuanly.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        rdoQuanly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoQuanlyActionPerformed(evt);
            }
        });

        rdoNhanvien.setBackground(new java.awt.Color(230, 230, 230));
        rdoNhanvien.setBorder(null);
        buttonGroup1.add(rdoNhanvien);
        rdoNhanvien.setText("Nhân viên");
        rdoNhanvien.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        rdoNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNhanvienActionPerformed(evt);
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

        btnKhoiPhuc.setText("Khôi phục trạng thái");
        btnKhoiPhuc.setToolTipText("");
        btnKhoiPhuc.setEnabled(false);
        btnKhoiPhuc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("*");

        javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
        panelborder1.setLayout(panelborder1Layout);
        panelborder1Layout.setHorizontalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
                            .addComponent(rdoNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoQuanly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        panelborder1Layout.setVerticalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(0, 0, 0)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, 0)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoQuanly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelborder1Layout.createSequentialGroup()
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
                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelborder2.setBackground(new java.awt.Color(230, 230, 230));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Danh sách nhân viên");

        panelborder3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Email", "SDT", "Vai Trò", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout panelborder3Layout = new javax.swing.GroupLayout(panelborder3);
        panelborder3.setLayout(panelborder3Layout);
        panelborder3Layout.setHorizontalGroup(
            panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelborder3Layout.setVerticalGroup(
            panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelborder2Layout = new javax.swing.GroupLayout(panelborder2);
        panelborder2.setLayout(panelborder2Layout);
        panelborder2Layout.setHorizontalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGroup(panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3))
                    .addGroup(panelborder2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelborder2Layout.setVerticalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(881, Short.MAX_VALUE))
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
        tblNhanVien.setRowSelectionAllowed(false);
        txtManv.setEnabled(true);
                nv = null;
        System.out.println(""+nv);
    }//GEN-LAST:event_moiActionPerformed

    private void mouse(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouse
        // TODO add your handling code here:
        tblNhanVien.setRowSelectionAllowed(true);
        if (evt.getClickCount() == 1) {
            this.index = tblNhanVien.getSelectedRow();
            if (this.index >= 0) {
                this.edit();
            }
        
        }
    }//GEN-LAST:event_mouse

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblNhanVien.getRowCount() - 1;
        tblNhanVien.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        tblNhanVien.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        this.index--;
        tblNhanVien.setRowSelectionInterval(index, index);
        this.edit();

    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:

        this.index++;
        tblNhanVien.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed

        List<NhanVien> list = NVDao.select();
        if (NVDao.findById(txtManv.getText()) == null) {
            if (validateform()) {
                this.insert();
                clear();
            }
        } else {
            noti_warning("Mã nhân viên đã tồn tại");
            txtManv.setBackground(new Color(250, 250, 210));
        }

    }//GEN-LAST:event_btnthemActionPerformed

    private void rdoQuanlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoQuanlyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoQuanlyActionPerformed

    private void rdoNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNhanvienActionPerformed

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        // TODO add your handling code here:
        if (noti_Choice("Bạn thực sự muốn khôi phuc nhân viên này?", null)) {
            String maNV = txtManv.getText();
            try {
                NVDao.update_khoiPhuc(maNV);
                this.fillTable();
                this.clear();
                noti_sucess("Khôi phục thành công");
            } catch (Exception e) {
                noti_warning("Khôi phuc thất bại!");
            }
        }
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnFirst;
    private button.Button btnKhoiPhuc;
    private button.Button btnLast;
    private button.Button btnNext;
    private button.Button btnPre;
    private button.Button btnsua;
    private button.Button btnthem;
    private button.Button btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private button.Button moi;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.panelborder panelborder3;
    private radio_button.RadioButtonCustom rdoNhanvien;
    private radio_button.RadioButtonCustom rdoQuanly;
    private com.raven.swing.table tblNhanVien;
    private textfield.TextField txtEmail;
    private textfield.TextField txtManv;
    private textfield.TextField txtSDT;
    private textfield.TextField txthoten;
    // End of variables declaration//GEN-END:variables
}
