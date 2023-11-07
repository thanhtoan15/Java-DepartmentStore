package com.raven.form;

import DAO.LoaiHangDAO;
import DAO.NhaCungCapDAO;
import DAO.SanPhamDAO;
import Entity.LoaiHang;
import Entity.NhaCungCap;
import Entity.SanPham;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Validate.Validate;
import com.raven.main.Main;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javaswingdev.message.MessageDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Form_SanPham extends javax.swing.JPanel {

    SanPham sp = new SanPham();

    public Form_SanPham(int index) {
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
        fillComboBox_LoaiHang();
        fillComboBox_NCC();
        setStatus(true);
        fillComboBox_donvi();
        txtMaSanPham.setEnabled(true);
    }

    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    LoaiHangDAO loaiHangDAO = new LoaiHangDAO();
    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    int index = 0;
    String imageName = null;
    Locale local = new Locale("vi", "VN");
    NumberFormat format = NumberFormat.getCurrencyInstance(local);
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

    void fillComboBox_LoaiHang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiHang.getModel();
        model.removeAllElements();
        try {
            List<LoaiHang> list = loaiHangDAO.select();
            for (LoaiHang loaiHang : list) {
                model.addElement(loaiHang.getMaLoaiHang() + " - " + loaiHang.getTenLoaiHang());
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
        }
    }

    void fillComboBox_NCC() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNhaCungCap.getModel();
        model.removeAllElements();
        try {
            List<NhaCungCap> list = nhaCungCapDAO.select();
            for (NhaCungCap NCC : list) {
                model.addElement(NCC.getMaNhaCungCap() + " - " + NCC.getTenNhaCungCap());
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
        }
    }

    void fillComboBox_donvi() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDonVi.getModel();
        model.removeAllElements();
        String[] header = {"KG", "G", "Hộp"};
        for (String string : header) {
            model.addElement(string);
        }
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = sanPhamDAO.select();
            for (SanPham sanpham : list) {
                Object[] row = {
                    sanpham.getMaLoaiHang(),
                    sanpham.getMaNhaCungCap(),
                    sanpham.getMaSanPham(),
                    sanpham.getTenSanPham(),
                    format.format(sanpham.getGiaBan()),
                    sanpham.getDonVi(),
                    sanpham.getSoLuong()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
        }
    }

    SanPham getModel() {
        SanPham model = new SanPham();
        model.setMaLoaiHang(String.valueOf(cboLoaiHang.getSelectedItem()).substring(0, 5));
        model.setMaNhaCungCap(String.valueOf(cboNhaCungCap.getSelectedItem()).substring(0, 6));
        model.setMaSanPham(txtMaSanPham.getText());
        model.setTenSanPham(txtTenSanPham.getText());

        model.setGiaBan(Integer.valueOf(txtGiaBan.getText()));
        model.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        model.setDonVi(cboDonVi.getSelectedItem() + "");
        model.setHinhSanPham(imageName);
        return model;
    }

    public ImageIcon Resize(ImageIcon icon) {
        Image image = icon.getImage();
        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), image.SCALE_SMOOTH));
        return icon1;
    }

    void setModel(SanPham model) {
        LoaiHang loaiHang = loaiHangDAO.findById(model.getMaLoaiHang());
        NhaCungCap nhaCungCap = nhaCungCapDAO.findById(model.getMaNhaCungCap());

        cboLoaiHang.getModel().setSelectedItem(loaiHang.getMaLoaiHang() + " - " + loaiHang.getTenLoaiHang());
        cboNhaCungCap.getModel().setSelectedItem(nhaCungCap.getMaNhaCungCap() + " - " + nhaCungCap.getTenNhaCungCap());
        txtMaSanPham.setText(model.getMaSanPham());
        txtTenSanPham.setText(model.getTenSanPham());
        txtGiaBan.setText(String.valueOf((int) model.getGiaBan()));
        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
        cboDonVi.setSelectedItem(model.getDonVi());
        if (model.getHinhSanPham() != null) {
            lbAnh.setIcon(Resize(ShareHelper.readLogo(model.getHinhSanPham())));
        }
    }

    void setStatus(boolean insertable) {
        moi.setEnabled(!insertable);
        btnthem.setEnabled(insertable);
        if (index >= tblSanPham.getRowCount()) {
            index = tblSanPham.getRowCount() -1 ;
        }
        String masp = (String) tblSanPham.getValueAt(index, 2);

        System.out.println("" + masp);

        if (sp != null) {
            txtMaSanPham.setEnabled(false);
        } else {
            txtMaSanPham.setEnabled(true);
        }
        if (sanPhamDAO.check_sl(masp).equals("0")) {
            btnxoa.setEnabled(false);
        } else {
            btnxoa.setEnabled(true);
        }
        btnsua.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblSanPham.getRowCount() - 1;
        btnPre.setEnabled(!insertable && first);
        btnFirst.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);

//        SanPham sp = sanPhamDAO.findById(masp);
    }

    void edit() {
        try {
            String makh = (String) tblSanPham.getValueAt(index, 2);
            SanPham model = sanPhamDAO.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
        }
    }

    void clear() {
//        this.setModel(new SanPham());
        txtGiaBan.setText("");
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtSoLuong.setText("");

        this.setStatus(true);

        cboLoaiHang.requestFocus();
        txtGiaBan.setBackground(Color.white);
        txtMaSanPham.setBackground(Color.white);
        txtTenSanPham.setBackground(Color.white);
        txtSoLuong.setBackground(Color.white);
    }

    void insert() {
        SanPham model = getModel();
        try {
            sanPhamDAO.insert(model);
            this.fillTable();
            noti_sucess("Thêm mới thành công!");
        } catch (HeadlessException e) {
            noti_warning("Thêm mới thất bại!");
        }

    }

    void update() {

        SanPham model = getModel();
        try {
            sanPhamDAO.update(model);
            this.fillTable();
            noti_sucess("Cập nhật thành công!");
        } catch (Exception e) {
            noti_warning("Cập nhật thất bại!");
        }
    }

    void delete() {
        if (noti_Choice("Bạn thực sự muốn xóa sản phẩm này?", "Không thể khôi phục sau khi xoá")) {
            String makh = txtMaSanPham.getText();
            try {
                sanPhamDAO.delete(makh);
                this.fillTable();
                this.clear();
                noti_sucess("Xóa thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                noti_warning("Xóa thất bại!");
            }
        }
    }

    private boolean validateform() {
        StringBuilder str = new StringBuilder();
        if (Validate.isEmpty(txtMaSanPham, str, "")) {
            noti_warning("Mã sản phẩm không được để trống");
            return false;
        }
        if (!Validate.isID(txtMaSanPham, str, "")) {
            noti_warning("Mã sản phẩm không đúng định dạng");
            return false;

        }
        if (Validate.isEmpty(txtTenSanPham, str, "")) {
            noti_warning("Tên sản phẩm không được để trống");
            return false;
        }
        if (!Validate.isName(txtTenSanPham, str, "Họ tên không đúng định dạng")) {
            noti_warning("Tên sản phẩm không đúng định dạng");
            return false;
        }

        if (Validate.isEmpty(txtGiaBan, str, "")) {
            noti_warning("Giá bán không được để trống");
            return false;
        }
        if (!Validate.isSDT(txtGiaBan, str, "")) {

            noti_warning("Giá bán chưa đúng định dạng");
            return false;
        }
        if (Integer.parseInt(txtGiaBan.getText()) < 0) {
            noti_warning("Giá bán phải lớn hơn 0");
            return false;
        }
        if (Validate.isEmpty(txtSoLuong, str, "")) {
            noti_warning("Số lượng không được để trống");
            return false;
        }
        if (!Validate.isSDT(txtSoLuong, str, "")) {
            noti_warning("Số lượng chưa đúng định dạng");
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenSanPham = new textfield.TextField();
        jLabel8 = new javax.swing.JLabel();
        txtMaSanPham = new textfield.TextField();
        cboLoaiHang = new combo_suggestion.ComboBoxSuggestion();
        cboNhaCungCap = new combo_suggestion.ComboBoxSuggestion();
        jLabel9 = new javax.swing.JLabel();
        txtGiaBan = new textfield.TextField();
        jLabel10 = new javax.swing.JLabel();
        txtSoLuong = new textfield.TextField();
        jLabel11 = new javax.swing.JLabel();
        lbAnh = new javax.swing.JLabel();
        cboDonVi = new combo_suggestion.ComboBoxSuggestion();
        btnFirst = new button.Button();
        btnPre = new button.Button();
        btnNext = new button.Button();
        btnLast = new button.Button();
        btnthem = new button.Button();
        btnxoa = new button.Button();
        btnsua = new button.Button();
        moi = new button.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelborder2 = new com.raven.swing.panelborder();
        jLabel3 = new javax.swing.JLabel();
        panelborder3 = new com.raven.swing.panelborder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new com.raven.swing.tablesp();

        panelborder1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Mã loại hàng");

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Mã nhà cung cấp");

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setText("Tên sản phẩm");

        txtTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel8.setText("Mã sản phẩm");

        txtMaSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboLoaiHang.setForeground(new java.awt.Color(80, 80, 80));
        cboLoaiHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboNhaCungCap.setForeground(new java.awt.Color(80, 80, 80));
        cboNhaCungCap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setText("Giá bán ");

        txtGiaBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setText("Số lượng");

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setText("Đơn vị");

        lbAnh.setBackground(new java.awt.Color(255, 255, 255));
        lbAnh.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        lbAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnh.setText("Ảnh");
        lbAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lbAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhMouseClicked(evt);
            }
        });

        cboDonVi.setForeground(new java.awt.Color(80, 80, 80));
        cboDonVi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/forward-button.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
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

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*");

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");

        javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
        panelborder1.setLayout(panelborder1Layout);
        panelborder1Layout.setHorizontalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(118, 118, 118)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(12, 12, 12)
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(cboDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(95, 95, 95)
                .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        panelborder1Layout.setVerticalGroup(
            panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelborder1Layout.createSequentialGroup()
                                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel14))
                                    .addGap(0, 0, 0)
                                    .addComponent(cboLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel13)))
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel15))
                                .addGroup(panelborder1Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelborder1Layout.createSequentialGroup()
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12))
                                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelborder1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelborder2.setBackground(new java.awt.Color(230, 230, 230));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Danh sách sản phẩm");

        panelborder3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setToolTipText("");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã loại hàng", "Mã NCC", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Đơn vị tính", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout panelborder3Layout = new javax.swing.GroupLayout(panelborder3);
        panelborder3.setLayout(panelborder3Layout);
        panelborder3Layout.setHorizontalGroup(
            panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelborder3Layout.setVerticalGroup(
            panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelborder2Layout = new javax.swing.GroupLayout(panelborder2);
        panelborder2.setLayout(panelborder2Layout);
        panelborder2Layout.setHorizontalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelborder2Layout.setVerticalGroup(
            panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelborder2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelborder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        tblSanPham.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        this.index--;
        tblSanPham.setRowSelectionInterval(index, index);
        this.edit();

    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:

        this.index++;
        tblSanPham.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblSanPham.getRowCount() - 1;
        tblSanPham.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        tblSanPham.setRowSelectionAllowed(true);
        this.index = tblSanPham.getSelectedRow();
        if (this.index >= 0) {
            this.edit();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked
    public void ResizeImage(String imageName) {
        ImageIcon icon = new ImageIcon("src\\Image\\" + imageName);
        Image image = icon.getImage();
        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), image.SCALE_SMOOTH));
        lbAnh.setIcon(icon1);
    }
    private void lbAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser file = new JFileChooser("src\\Image\\");
            int kq = file.showOpenDialog(file);
            if (kq == JFileChooser.APPROVE_OPTION) {
                imageName = file.getSelectedFile().getName();
                System.out.println(imageName);
                lbAnh.setText("");
                ResizeImage(imageName);
            } else {
                noti_warning("bạn chưa chọn ảnh");
            }

        } catch (Exception a) {
            a.printStackTrace();
        }
    }//GEN-LAST:event_lbAnhMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed

        sp.setMaSanPham(txtMaSanPham.getText());
        List<SanPham> list = sanPhamDAO.select();
        if (sanPhamDAO.findById(txtMaSanPham.getText()) == null) {
            if (validateform()) {
                this.insert();
                clear();
            }
        } else {
            noti_warning("Mã sản phẩm đã tồn tại");
            txtMaSanPham.setBackground(new Color(250, 250, 210));
            txtMaSanPham.requestFocus();
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
        txtMaSanPham.enable(true);
        tblSanPham.setRowSelectionAllowed(false);
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
    private combo_suggestion.ComboBoxSuggestion cboDonVi;
    private combo_suggestion.ComboBoxSuggestion cboLoaiHang;
    private combo_suggestion.ComboBoxSuggestion cboNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAnh;
    private button.Button moi;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.panelborder panelborder3;
    private com.raven.swing.tablesp tblSanPham;
    private textfield.TextField txtGiaBan;
    private textfield.TextField txtMaSanPham;
    private textfield.TextField txtSoLuong;
    private textfield.TextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
