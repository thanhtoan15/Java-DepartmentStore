package com.raven.form;

import DAO.ChiTietHoaDonXuatDAO;
import DAO.HoaDonXuatDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO1;
import Entity.HoaDonXuat;
import Entity.KhachHang;
import Helper.DateHelper;
import Helper.ShareHelper;
import Validate.Validate;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import com.raven.main.Main;
import com.raven.main.Main_Multichoice_CTHDXuat;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javaswingdev.message.MessageDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Form_HoaDonXuat extends javax.swing.JPanel {

    public Form_HoaDonXuat(int index) {
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
        fillComboBox_KhachHang();
        setStatus(true);
        cboMaKhachHang.requestFocus();
        dateChooser1.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {
                System.out.println(date.getDay() + "-" + date.getMonth() + "-" + date.getYear());
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    dateChooser1.hidePopup();
                }
            }
        });
    }

    HoaDonXuatDAO hdxuatDao = new HoaDonXuatDAO();
    ChiTietHoaDonXuatDAO chiTietHoaDonXuatDAO = new ChiTietHoaDonXuatDAO();
    NhanVienDAO1 nhanVienDAO = new NhanVienDAO1();
    KhachHangDAO khachHangDAO = new KhachHangDAO();

    int index = 0;

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

   void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblHDXuat.getModel();
        model.setRowCount(0);
        try {
            List<HoaDonXuat> list = hdxuatDao.select();
            for (HoaDonXuat hdx : list) {
                String TrangThai = "";

                if (KT_ngay(DateHelper.toDate(hdx.getNgayXuat(), "yyyy-MM-dd"))) {
                    TrangThai = "Có thể chỉnh sửa";
                } else {
                    TrangThai = "Không thể chỉnh sửa";
                }
                Object[] row = {
                    hdx.getSoPhieu(),
                    hdx.getMaNhanVien(),
                    hdx.getMaKhachHang(),
                    hdx.getThanhTien(),
                    hdx.getNgayXuat(),
                    hdxuatDao.getTongSoLuong(hdx.getSoPhieu()),
                   TrangThai
                    
                };
                model.addRow(row);
            }
        } catch (Exception e) {
             noti_warning("Lỗi truy vấn dữ liệu loai hang!");
            e.printStackTrace();
        }
    }
 void fillTable_From_To(String dateFrom , String dateTo) {
        DefaultTableModel model = (DefaultTableModel) tblHDXuat.getModel();
        model.setRowCount(0);
        try {
            List<HoaDonXuat> list = hdxuatDao.select_From_To(dateFrom, dateTo);
            for (HoaDonXuat hdx : list) {
                String TrangThai = "";

                if (KT_ngay(DateHelper.toDate(hdx.getNgayXuat(), "yyyy-MM-dd"))) {
                    TrangThai = "Có thể chỉnh sửa";
                } else {
                    TrangThai = "Không thể chỉnh sửa";
                }
                Object[] row = {
                    hdx.getSoPhieu(),
                    hdx.getMaNhanVien(),
                    hdx.getMaKhachHang(),
                    hdx.getThanhTien(),
                    hdx.getNgayXuat(),
                    hdxuatDao.getTongSoLuong(hdx.getSoPhieu()),
                   TrangThai
                    
                };
                model.addRow(row);
            }
        } catch (Exception e) {
             noti_warning("Lỗi truy vấn dữ liệu loai hang!");
            e.printStackTrace();
        }
    }
    void fillComboBox_KhachHang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaKhachHang.getModel();
        model.removeAllElements();
        try {
            List<KhachHang> list = khachHangDAO.select();
            for (KhachHang khachHang : list) {
                model.addElement(khachHang.toString());
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
        }
    }

    HoaDonXuat getModel() {
        HoaDonXuat model = new HoaDonXuat();
        model.setMaNhanVien(ShareHelper.USER.getMaNhanVien());
//        model.setMaNhanVien("NV03");

        model.setMaKhachHang(String.valueOf(cboMaKhachHang.getSelectedItem()).substring(0, 4));
        model.setSoPhieu((String) tblHDXuat.getValueAt(index, 0));
        model.setNgayXuat(DateHelper.toString(txtFromNgayXuat.getSelectedDate().getTime(), "yyyy-MM-dd"));
        return model;
    }

    void setModel(HoaDonXuat model) {
        KhachHang kh = new KhachHang();
        kh = khachHangDAO.findById(model.getMaKhachHang());
        cboMaKhachHang.getModel().setSelectedItem(kh.getMaKhachHang() + " - " + kh.getHoTenKhachHang());

        int Year = Integer.valueOf(model.getNgayXuat().substring(0, 4));
        int Month = Integer.valueOf(model.getNgayXuat().substring(5, 7));
        int Date = Integer.valueOf(model.getNgayXuat().substring(8, 10));
        Calendar calendar = new GregorianCalendar(Year, Month, Date);
        txtFromNgayXuat.setSelectedDate(calendar);
    }

    void setModel() {
        cboMaKhachHang.getModel().setSelectedItem("");

        int Year = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(0, 4));
        int Month = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(5, 7));
        int Date = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(8, 10));
        Calendar calendar = new GregorianCalendar(Year, Month, Date);
        txtFromNgayXuat.setSelectedDate(calendar);
    }

    void setStatus(boolean insertable) {
        btnChiTiet.setEnabled(!insertable);
        btnsua.setEnabled(!insertable);
        btnxoa.setEnabled(!insertable);
        btnChiTiet.setEnabled(!insertable);
        moi.setEnabled(!insertable);
        btnthem.setEnabled(insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblHDXuat.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPre.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);

    }

    void edit() {
        try {
            String makh = (String) tblHDXuat.getValueAt(index, 0);
            HoaDonXuat model = hdxuatDao.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            noti_warning("Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    void clear() {
        this.setModel();
        this.setStatus(true);
        cboMaKhachHang.requestFocus();
    }

    void insert() {
        HoaDonXuat model = getModel();
        try {
            hdxuatDao.insert(model);
            this.fillTable();
            noti_sucess("Thêm mới thành công!");
        } catch (HeadlessException e) {
            noti_warning("Thêm mới thất bại!");
        }
    }

    void update() {

        HoaDonXuat model = getModel();
        try {
            hdxuatDao.update(model);
            this.fillTable();
            noti_sucess("Cập nhật thành công!");

        } catch (Exception e) {
            noti_warning("Cập nhật thất bại!");

        }
    }

    void delete() {
        if (noti_Choice("Bạn thực sự muốn xóa hoá đơn này?", "Không Thể khôi phục ")) {
            HoaDonXuat model = hdxuatDao.findById((String) tblHDXuat.getValueAt(index, 0));
            String soPhieu = model.getSoPhieu();
            try {
                chiTietHoaDonXuatDAO.delete_All(soPhieu);
                hdxuatDao.delete(soPhieu);
                this.fillTable();
                this.clear();
                noti_sucess("Xóa thành công!");
            } catch (Exception e) {
                noti_warning("Xóa thất bại!");
            }
        }
    }


    void KT_ngay(String MaKhachHang) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        // Định nghĩa 2 mốc thời gian ban đầu
        Date date1 = DateHelper.toDate(
                        String.valueOf(tblHDXuat.getValueAt(index, 4)), "yyyy-MM-dd");
        Date date2 = DateHelper.toDate(
                DateHelper.toString(
                        DateHelper.now(), "yyyy-MM-dd"), "yyyy-MM-dd");

        c1.setTime(date1);
        c2.setTime(date2);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

        if (noDay > 3) {
            Main_Multichoice_CTHDXuat form_multipleChoice
                    = new Main_Multichoice_CTHDXuat((String) tblHDXuat.getValueAt(index, 0), MaKhachHang, false);
            form_multipleChoice.setVisible(true);
        } else {
            Main_Multichoice_CTHDXuat form_multipleChoice
                    = new Main_Multichoice_CTHDXuat((String) tblHDXuat.getValueAt(index, 0), MaKhachHang, true);
            form_multipleChoice.setVisible(true);
        }
    }

    boolean KT_ngay(Date ngayKiemTra) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        // Định nghĩa 2 mốc thời gian ban đầu
        Date date1 = DateHelper.toDate(
                DateHelper.toString(
                        ngayKiemTra, "yyyy-MM-dd"), "yyyy-MM-dd");
        Date date2 = DateHelper.toDate(
                DateHelper.toString(
                        DateHelper.now(), "yyyy-MM-dd"), "yyyy-MM-dd");

        c1.setTime(date1);
        c2.setTime(date2);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

        if (noDay > 3) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        panelborder1 = new com.raven.swing.panelborder();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboMaKhachHang = new combo_suggestion.ComboBoxSuggestion();
        txtFromNgayXuat = new datechooser.beans.DateChooserCombo();
        btnLast = new button.Button();
        btnFirst = new button.Button();
        btnPre = new button.Button();
        btnNext = new button.Button();
        btnChiTiet = new button.Button();
        btnthem = new button.Button();
        btnxoa = new button.Button();
        btnsua = new button.Button();
        moi = new button.Button();
        jLabel9 = new javax.swing.JLabel();
        txtToNgayXuat = new datechooser.beans.DateChooserCombo();
        panelborder2 = new com.raven.swing.panelborder();
        jLabel3 = new javax.swing.JLabel();
        panelborder3 = new com.raven.swing.panelborder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDXuat = new com.raven.swing.table();

        panelborder1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel8.setText("Ngày Xuất Từ");

        jLabel2.setText("Mã khách hàng");

        cboMaKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtFromNgayXuat.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    txtFromNgayXuat.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    txtFromNgayXuat.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
    txtFromNgayXuat.setLocale(new java.util.Locale("vi", "VN", ""));
    txtFromNgayXuat.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            txtFromNgayXuatOnSelectionChange(evt);
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

    btnChiTiet.setText("Chi tiết hóa đơn xuất");
    btnChiTiet.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
    btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnChiTietActionPerformed(evt);
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

    jLabel9.setText("Ngày Xuất Đến");

    txtToNgayXuat.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
txtToNgayXuat.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
txtToNgayXuat.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
txtToNgayXuat.setLocale(new java.util.Locale("vi", "VN", ""));
txtToNgayXuat.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
    public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
        txtToNgayXuatOnSelectionChange(evt);
    }
    });

    javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
    panelborder1.setLayout(panelborder1Layout);
    panelborder1Layout.setHorizontalGroup(
        panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder1Layout.createSequentialGroup()
            .addGap(87, 87, 87)
            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(panelborder1Layout.createSequentialGroup()
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(172, 172, 172)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelborder1Layout.createSequentialGroup()
                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(cboMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(txtFromNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addComponent(txtToNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(0, 0, 0)
            .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(74, Short.MAX_VALUE))
    );
    panelborder1Layout.setVerticalGroup(
        panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder1Layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(panelborder1Layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cboMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelborder1Layout.createSequentialGroup()
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtFromNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelborder1Layout.createSequentialGroup()
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtToNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(9, 9, 9)
            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(49, Short.MAX_VALUE))
    );

    panelborder2.setBackground(new java.awt.Color(230, 230, 230));

    jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jLabel3.setText("Danh sách hóa đơn nhập");

    panelborder3.setBackground(new java.awt.Color(255, 255, 255));

    jScrollPane1.setBorder(null);

    tblHDXuat.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null}
        },
        new String [] {
            "Số phiếu", "Mã nhân viên", "Mã khách hàng", "Thành tiền", "Ngày xuất", "Tổng Số Sản Phẩm", "Trạng Thái"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tblHDXuat.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblHDXuatMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblHDXuat);

    javax.swing.GroupLayout panelborder3Layout = new javax.swing.GroupLayout(panelborder3);
    panelborder3.setLayout(panelborder3Layout);
    panelborder3Layout.setHorizontalGroup(
        panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
            .addContainerGap())
    );
    panelborder3Layout.setVerticalGroup(
        panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
            .addContainerGap())
    );

    javax.swing.GroupLayout panelborder2Layout = new javax.swing.GroupLayout(panelborder2);
    panelborder2.setLayout(panelborder2Layout);
    panelborder2Layout.setHorizontalGroup(
        panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder2Layout.createSequentialGroup()
            .addGroup(panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelborder2Layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jLabel3))
                .addGroup(panelborder2Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(33, Short.MAX_VALUE))
    );
    panelborder2Layout.setVerticalGroup(
        panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder2Layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(46, Short.MAX_VALUE))
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

    private void tblHDXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDXuatMouseClicked
        // TODO add your handling code here:
          tblHDXuat.setRowSelectionAllowed(true);
      this.index = tblHDXuat.getSelectedRow();
        if (this.index >= 0) {
            this.edit();
        }
        if (evt.getClickCount() == 2) {
            KT_ngay(String.valueOf(cboMaKhachHang.getSelectedItem()).substring(0, 4));
        }
    }//GEN-LAST:event_tblHDXuatMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblHDXuat.getRowCount() - 1;
        tblHDXuat.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        tblHDXuat.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        this.index--;
        tblHDXuat.setRowSelectionInterval(index, index);
        this.edit();

    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:

        this.index++;
        tblHDXuat.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        KT_ngay(String.valueOf(cboMaKhachHang.getSelectedItem()).substring(0, 4));
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        //        if (validateform()) {
                 HoaDonXuat model = new HoaDonXuat();
        //        model.setMaNhanVien(String.valueOf(cboMaNhanVien.getSelectedItem()).substring(0, 4));
        model.setMaNhanVien("NV03");
        model.setMaKhachHang(String.valueOf(cboMaKhachHang.getSelectedItem()).substring(0, 4));
        model.setSoPhieu("");
        model.setThanhTien(0);
        model.setNgayXuat(DateHelper.toString(txtFromNgayXuat.getSelectedDate().getTime(), "yyyy-MM-dd"));
        try {
            hdxuatDao.insert(model);
            this.fillTable();
            noti_sucess("Thêm moi thanh cong!");
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
       Main_Multichoice_CTHDXuat form_multipleChoice
                = new Main_Multichoice_CTHDXuat(hdxuatDao.getSoPhieu(), model.getMaKhachHang(),true);
        form_multipleChoice.setVisible(true);
            //        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        //        if (validateform()) {
            this.update();
            //        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moiActionPerformed
        this.clear();
          tblHDXuat.setRowSelectionAllowed(false);
          fillTable();
    }//GEN-LAST:event_moiActionPerformed
String dateFrom  = "";
String dateTo = "";
    private void txtFromNgayXuatOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_txtFromNgayXuatOnSelectionChange
        // TODO add your handling code here:
        dateFrom = DateHelper.toString(txtFromNgayXuat.getSelectedDate().getTime(), "yyyy-MM-dd");
    }//GEN-LAST:event_txtFromNgayXuatOnSelectionChange

    private void txtToNgayXuatOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_txtToNgayXuatOnSelectionChange
        // TODO add your handling code here:
        dateTo = DateHelper.toString(txtToNgayXuat.getSelectedDate().getTime(), "yyyy-MM-dd");
        fillTable_From_To(dateFrom, dateTo);
    }//GEN-LAST:event_txtToNgayXuatOnSelectionChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnChiTiet;
    private button.Button btnFirst;
    private button.Button btnLast;
    private button.Button btnNext;
    private button.Button btnPre;
    private button.Button btnsua;
    private button.Button btnthem;
    private button.Button btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private combo_suggestion.ComboBoxSuggestion cboMaKhachHang;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private button.Button moi;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.panelborder panelborder3;
    private com.raven.swing.table tblHDXuat;
    private datechooser.beans.DateChooserCombo txtFromNgayXuat;
    private datechooser.beans.DateChooserCombo txtToNgayXuat;
    // End of variables declaration//GEN-END:variables
}
