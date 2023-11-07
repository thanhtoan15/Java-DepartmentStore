package com.raven.form;

import DAO.ChiTietHoaDonNhapDAO;
import DAO.HoaDonNhapDAO;
import DAO.NhanVienDAO1;
import Entity.HoaDonNhap;
import Entity.NhanVien;
import Helper.DateHelper;
import Helper.DialogHelper;
import Helper.Set_icon_frame;
import Helper.ShareHelper;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import com.raven.main.Main;
import com.raven.main.Main_Multichoice_CTHDNhap;
import com.raven.main.Scan;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javaswingdev.message.MessageDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Form_HDNhap extends javax.swing.JPanel {

    public Form_HDNhap() {
        System.out.println("ko index");
        initComponents();
        setOpaque(false);
         fillTable();
//        jLabel1.setText("Form " + index);
        jScrollPane1.setVerticalScrollBar(new scrollbar());
        jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
       
        setStatus(true);
        dateChooser.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {
                System.out.println(date.getDay() + "-" + date.getMonth() + "-" + date.getYear());
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    dateChooser.hidePopup();
                }
            }
        });
        txtManv.setText(ShareHelper.USER.getMaNhanVien() + " - "+ShareHelper.USER.getHoTenNhanVien());

    }

    public Form_HDNhap(int index) {
        System.out.println("cos index");
        initComponents();
        setOpaque(false);
                fillTable();

//        jLabel1.setText("Form " + index);
        jScrollPane1.setVerticalScrollBar(new scrollbar());
        jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.white);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        setStatus(true);
        dateChooser.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {
                System.out.println(date.getDay() + "-" + date.getMonth() + "-" + date.getYear());
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    dateChooser.hidePopup();
                }
            }
        });
 txtManv.setText(ShareHelper.USER.getMaNhanVien() + " - "+ShareHelper.USER.getHoTenNhanVien());
    }
    HoaDonNhapDAO hdnhapDao = new HoaDonNhapDAO();
    ChiTietHoaDonNhapDAO chiTietHoaDonNhapDAO = new ChiTietHoaDonNhapDAO();
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

   public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblHDNhap.getModel();
        model.setRowCount(0);
        try {
            List<HoaDonNhap> list = hdnhapDao.select();
            for (HoaDonNhap hdn : list) {
                String TrangThai = "";
                
                if (KT_ngay(DateHelper.toDate(hdn.getNgayNhap(), "yyyy-MM-dd"))){
                    TrangThai = "Có thể chỉnh sửa";
                }else {
                    TrangThai = "Không thể chỉnh sửa";
                }
                Object[] row = {
                    hdn.getSoPhieu(),
                    hdn.getMaNhanVien(),
                    hdn.getNgayNhap(),
                    hdnhapDao.getTongSoLuong(hdn.getSoPhieu()),
                    TrangThai
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu loai hang!");
            e.printStackTrace();
        }
    }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    void fillTable_NgayThangNam(String date_checkfrom , String date_checkto) {
        DefaultTableModel model = (DefaultTableModel) tblHDNhap.getModel();
        model.setRowCount(0);
        try {
            List<HoaDonNhap> list1 = hdnhapDao.select_ngayThangNam(date_checkfrom , date_checkto);
            System.out.println(""+hdnhapDao.select_ngayThangNam(date_checkfrom,date_checkto).size());
            for (HoaDonNhap hdn : list1) {
                String TrangThai = "";
                if (KT_ngay(DateHelper.toDate(hdn.getNgayNhap(), "yyyy-MM-dd"))){
                    TrangThai = "Có thể chỉnh sửa";
                }else {
                    TrangThai = "Không thể chỉnh sửa";
                }
                Object[] row = {
                    hdn.getSoPhieu(),
                    hdn.getMaNhanVien(),
                    hdn.getNgayNhap(),
                    hdnhapDao.getTongSoLuong(hdn.getSoPhieu()),
                    TrangThai
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu loai hang!");
            e.printStackTrace();
        }
    }
    HoaDonNhap getModel() {
        HoaDonNhap model = new HoaDonNhap();
        model.setSoPhieu((tblHDNhap.getValueAt(index, 0) + ""));
        model.setMaNhanVien(ShareHelper.USER.getMaNhanVien());
        model.setNgayNhap(DateHelper.toString(txtfromNgayNhap.getSelectedDate().getTime(), "yyyy-MM-dd"));

        return model;
    }
    NhanVienDAO1 nvdao= new NhanVienDAO1();
    void setModel(HoaDonNhap model) {
        NhanVien nv =  nvdao.findById(model.getMaNhanVien());
        int Year = Integer.valueOf(model.getNgayNhap().substring(0, 4));
        int Month = Integer.valueOf(model.getNgayNhap().substring(5, 7)) - 1;
        int Date = Integer.valueOf(model.getNgayNhap().substring(8, 10));
        Calendar calendar = new GregorianCalendar(Year, Month, Date);
        txtfromNgayNhap.setSelectedDate(calendar);
        
        txtManv.setText(model.getMaNhanVien()+" - "+ nv.getHoTenNhanVien());
    }

    void setStatus(boolean insertable) {
        btnthem.setEnabled(insertable);
        moi.setEnabled(!insertable);
        btnCTHD.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblHDNhap.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPre.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);
    }

    void edit() {
        try {
            String soPhieu = (String) tblHDNhap.getValueAt(index, 0);
            HoaDonNhap model = hdnhapDao.findById(soPhieu);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
//            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            noti_infor("Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    void setModel() {
        int Year = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(0, 4));
        int Month = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(5, 7)) ;
        int Date = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(8, 10));
        Calendar calendar = new GregorianCalendar(Year, Month, Date);
        txtfromNgayNhap.setSelectedDate(calendar);
    }

    void clear() {
        this.setModel();
        this.setStatus(true);
    }

    void insert() {
        HoaDonNhap model = getModel();
        try {
            hdnhapDao.insert(model);
            this.fillTable();
            noti_infor("Thêm mới thành công!");
        } catch (HeadlessException e) {
            noti_infor("Thêm mới thất bại!");
        }
    }

    void update() {

        HoaDonNhap model = getModel();
        try {
            hdnhapDao.update(model);
            this.fillTable();
            noti_infor("Cập nhật thành công!");
        } catch (Exception e) {
            noti_infor("Cập nhật thất bại!");
        }
    }

    void delete() {
        if (noti_Choice("", "Bạn thực sự muốn xóa hoá đơn này?")) {
            String makh = String.valueOf(tblHDNhap.getValueAt(index, 0));
            try {
                chiTietHoaDonNhapDAO.delete(makh);
                hdnhapDao.delete(makh);
                this.fillTable();
                this.clear();
                noti_infor("Xóa thành công!");
            } catch (Exception e) {
                noti_infor("Xóa thất bại!");
            }
        }
    }

void KT_ngay() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        // Định nghĩa 2 mốc thời gian ban đầu
        Date date1 = DateHelper.toDate(
                DateHelper.toString(
                        txtfromNgayNhap.getSelectedDate().getTime(), "yyyy-MM-dd"), "yyyy-MM-dd");
        Date date2 = DateHelper.toDate(
                DateHelper.toString(
                        DateHelper.now(), "yyyy-MM-dd"), "yyyy-MM-dd");

        c1.setTime(date1);
        c2.setTime(date2);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

        if (noDay > 3) {
            Main_Multichoice_CTHDNhap form_multipleChoice
                    = new Main_Multichoice_CTHDNhap((String) tblHDNhap.getValueAt(index, 0), false);
            form_multipleChoice.setVisible(true);
        } else {
            Main_Multichoice_CTHDNhap form_multipleChoice
                    = new Main_Multichoice_CTHDNhap((String) tblHDNhap.getValueAt(index, 0), true);
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
        dateChooser = new com.raven.datechooser.DateChooser();
        panelborder1 = new com.raven.swing.panelborder();
        jLabel8 = new javax.swing.JLabel();
        btnCTHD = new button.Button();
        txtfromNgayNhap = new datechooser.beans.DateChooserCombo();
        btnLast = new button.Button();
        btnNext = new button.Button();
        btnPre = new button.Button();
        btnFirst = new button.Button();
        btnthem = new button.Button();
        btnxoa = new button.Button();
        jButton1 = new javax.swing.JButton();
        moi = new button.Button();
        jLabel9 = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtToNgayNhap = new datechooser.beans.DateChooserCombo();
        panelborder2 = new com.raven.swing.panelborder();
        jLabel3 = new javax.swing.JLabel();
        panelborder3 = new com.raven.swing.panelborder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDNhap = new com.raven.swing.table();

        panelborder1.setBackground(new java.awt.Color(230, 230, 230));

        jLabel8.setText("Ngày nhập Từ :");

        btnCTHD.setText("Chi tiết hóa đơn");
        btnCTHD.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btnCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTHDActionPerformed(evt);
            }
        });

        txtfromNgayNhap.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
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
    txtfromNgayNhap.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    txtfromNgayNhap.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
    txtfromNgayNhap.setLocale(new java.util.Locale("vi", "VN", ""));
    txtfromNgayNhap.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            txtfromNgayNhapOnSelectionChange(evt);
        }
    });

    btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/forward-button.png"))); // NOI18N
    btnLast.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnLastActionPerformed(evt);
        }
    });

    btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/play-button.png"))); // NOI18N
    btnNext.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnNextActionPerformed(evt);
        }
    });

    btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/prev-button.png"))); // NOI18N
    btnPre.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnPreActionPerformed(evt);
        }
    });

    btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/first-button.png"))); // NOI18N
    btnFirst.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnFirstActionPerformed(evt);
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

    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/barcode.png"))); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    moi.setText("Làm mới");
    moi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
    moi.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            moiActionPerformed(evt);
        }
    });

    jLabel9.setText("Nhân Viên");

    txtManv.setEditable(false);
    txtManv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

    jLabel10.setText("Ngày nhập Đến : ");

    txtToNgayNhap.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
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
txtToNgayNhap.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
txtToNgayNhap.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
txtToNgayNhap.setLocale(new java.util.Locale("vi", "VN", ""));
txtToNgayNhap.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
    public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
        txtToNgayNhapOnSelectionChange(evt);
    }
    });

    javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
    panelborder1.setLayout(panelborder1Layout);
    panelborder1Layout.setHorizontalGroup(
        panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder1Layout.createSequentialGroup()
            .addGap(75, 75, 75)
            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(panelborder1Layout.createSequentialGroup()
                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelborder1Layout.createSequentialGroup()
                            .addComponent(btnCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(166, 166, 166)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelborder1Layout.createSequentialGroup()
                    .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(txtfromNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(txtToNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(21, 21, 21)))
            .addContainerGap(176, Short.MAX_VALUE))
    );
    panelborder1Layout.setVerticalGroup(
        panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder1Layout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8)
                .addComponent(jLabel9)
                .addComponent(jLabel10))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtToNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtfromNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelborder1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(txtManv))
            .addGap(42, 42, 42)
            .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(33, 33, 33))
    );

    panelborder2.setBackground(new java.awt.Color(230, 230, 230));

    jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jLabel3.setText("Danh sách hóa đơn nhập");

    panelborder3.setBackground(new java.awt.Color(255, 255, 255));

    jScrollPane1.setBorder(null);

    tblHDNhap.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
        new String [] {
            "Số phiếu", "Mã nhân viên", "Ngày nhập", "Tổng Số Sản Phẩm", "Trạng Thái"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tblHDNhap.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblHDNhapMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblHDNhap);

    javax.swing.GroupLayout panelborder3Layout = new javax.swing.GroupLayout(panelborder3);
    panelborder3.setLayout(panelborder3Layout);
    panelborder3Layout.setHorizontalGroup(
        panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
            .addContainerGap())
    );
    panelborder3Layout.setVerticalGroup(
        panelborder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
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
                    .addGap(28, 28, 28)
                    .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(43, Short.MAX_VALUE))
    );
    panelborder2Layout.setVerticalGroup(
        panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder2Layout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(panelborder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(10, Short.MAX_VALUE))
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

    private void btnCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTHDActionPerformed
        // TODO add your handling code here:

        String sophieu = (String) tblHDNhap.getValueAt(index, 0);
        
        Main_Multichoice_CTHDNhap a = new Main_Multichoice_CTHDNhap(sophieu);
        a.setVisible(true);
        
        
        KT_ngay();
        fillTable();
//        new com.raven.main.Main(sophieu).setVisible(true);

//        a.setVisible(true);

    }//GEN-LAST:event_btnCTHDActionPerformed
  ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               fillTable();
            }
        };
    private void tblHDNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDNhapMouseClicked
        // TODO add your handling code here:
        tblHDNhap.setRowSelectionAllowed(true);
        this.index = tblHDNhap.getSelectedRow();
        if (this.index >= 0) {
            this.edit();
        }
        if (evt.getClickCount() == 2) {
//            this.dispose();
            KT_ngay();
        }
    }//GEN-LAST:event_tblHDNhapMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblHDNhap.getRowCount() - 1;
        tblHDNhap.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:

        this.index++;
        tblHDNhap.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        this.index--;
        tblHDNhap.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        tblHDNhap.setRowSelectionInterval(index, index);
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
//        if (validateform()) {
        HoaDonNhap model = new HoaDonNhap();
        model.setSoPhieu("");
        model.setNgayNhap(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd"));
        model.setMaNhanVien(ShareHelper.USER.getMaNhanVien());
//        model.setMaKhachHang(String.valueOf(cboMaKhachHang.getSelectedItem()).substring(0, 4));
//        model.setThanhTien(0);
        try {
            hdnhapDao.insert(model);
            this.fillTable();
            noti_infor("Thêm moi thanh cong!");
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
        Main_Multichoice_CTHDNhap form_multipleChoice
                = new Main_Multichoice_CTHDNhap(hdnhapDao.getSoPhieu(), true);
//        this.dispose();
        form_multipleChoice.setVisible(true);
//        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Scan().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moiActionPerformed
        this.clear();
        
        tblHDNhap.setRowSelectionAllowed(false);
        fillTable();
        txtManv.setText(ShareHelper.USER.getMaNhanVien()+" - "+ShareHelper.USER.getHoTenNhanVien());
    }//GEN-LAST:event_moiActionPerformed
String date_checkfrom ="";
String date_checkto = "";
    private void txtfromNgayNhapOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_txtfromNgayNhapOnSelectionChange
        // TODO add your handling code here:
        date_checkfrom = DateHelper.toString(txtfromNgayNhap.getSelectedDate().getTime(), "yyyy-MM-dd");
    }//GEN-LAST:event_txtfromNgayNhapOnSelectionChange

    private void txtToNgayNhapOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_txtToNgayNhapOnSelectionChange
        // TODO add your handling code here:
        date_checkto = DateHelper.toString(txtToNgayNhap.getSelectedDate().getTime(), "yyyy-MM-dd");
        fillTable_NgayThangNam(date_checkfrom, date_checkto);
    }//GEN-LAST:event_txtToNgayNhapOnSelectionChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnCTHD;
    private button.Button btnFirst;
    private button.Button btnLast;
    private button.Button btnNext;
    private button.Button btnPre;
    private button.Button btnthem;
    private button.Button btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.raven.datechooser.DateChooser dateChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private button.Button moi;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.panelborder panelborder3;
    private com.raven.swing.table tblHDNhap;
    private javax.swing.JTextField txtManv;
    private datechooser.beans.DateChooserCombo txtToNgayNhap;
    private datechooser.beans.DateChooserCombo txtfromNgayNhap;
    // End of variables declaration//GEN-END:variables
}
