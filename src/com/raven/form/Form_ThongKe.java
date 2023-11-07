package com.raven.form;

import DAO.ThongKeDao;
import Helper.DateHelper;
import com.raven.chart.ModelChart;
import com.raven.main.Main;
import com.raven.notification.Notification;
import com.raven.swing.scrollbar;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javaswingdev.message.MessageDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Form_ThongKe extends javax.swing.JPanel {

    public Form_ThongKe() {
        initComponents();
        setOpaque(false);
        init();
        int Year = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(0, 4));
        int Month = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(5, 7));
        int Date = Integer.valueOf(DateHelper.toString(DateHelper.now(), "yyyy-MM-dd").substring(8, 10));
        Calendar calendar = new GregorianCalendar(Year, Month, Date);
        txtdate.setSelectedDate(calendar);

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

    ThongKeDao dao = new ThongKeDao();

    void sl_DonNhap(int month) {
        String a = dao.get_SLDonNhap_Thang(month);
        lblhdnhap.setText(a + "");

    }

//
    void sl_Xuat(int month) {
        String a = dao.get_SLDonXuat_Thang(month);
        lblhdxuat.setText(a + "");
    }
//
    private void fillTablespdatmuanhieu(int month) {
        try {
            DefaultTableModel model = (DefaultTableModel) tblspmax_month.getModel();
            model.setColumnIdentifiers(new String[]{"Mã SP", "Mã Loại Hàng", "Tên Sản Phẩm", "Giá Bán", "số lượng"});
            model.setRowCount(0);
//            int a = Integer.valueOf(cboMonth_SP.getSelectedItem() + "");
            if (month != 0) {
                List<Object[]> list = dao.getTop3SP_muaMax(month);

                for (Object[] row : list) {
                    Object[] ob = {row[0], row[1], row[2], row[3], row[4]};
                    model.addRow(ob);
                }
                model.fireTableDataChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//
    private void fillTablencc(int month) {
        try {
            DefaultTableModel model = (DefaultTableModel) tblnccmax_month.getModel();
            model.setRowCount(0);
//            int a = Integer.valueOf(cboMonth_NCC.getSelectedItem() + "");
            if (month != 0) {
                List<Object[]> list = dao.getTop3NCC(month);

                for (Object[] row : list) {
                    Object[] ob = {row[0], row[1], row[2]};
                    model.addRow(ob);
                }
                model.fireTableDataChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//

    void doanhThu_Ngay(int day) {
        String a = dao.get_DoanhThu_Ngay(day);
        if (dao.get_DoanhThu_Ngay(day) == null) {
            a = "0";
        } else {
            lbldoanhthuNgay.setText(a + "");
        }
    }
//

    String doanhThu_Thang(int month, int year) {
        String b = "0";
        return b = dao.get_DoanhThu_Thang(month, year);
//        if (Double.valueOf(dao.get_DoanhThu_Thang(month, year)) == null) {
//            return b = "0";
//        } else {
//            return b = dao.get_DoanhThu_Thang(month, year);
//        }
    }
//    void doanhThu_tung_Thang(int month , int year){
//        for (int i = 1; i <= 12; i++) {
//            if(doanhThu_Thang(month, year) != null){
//                
//            }
//        }
//    }
//

    void doanhThu_Nam(int year) {
        String a = dao.get_DoanhThu_Nam(year);
        if (dao.get_DoanhThu_Nam(year) == null) {
            a = "0";
        } else {
            lbldoanhthuNam.setText(a + "");
        }
    }
    int year1 = 2022;
//    double a1 = 0.0;

    private void init() {
//        chart.addLegend("Income", new Color(12, 84, 175), new Color(0, 108, 247));
//        chart.addLegend("Expense", new Color(54, 4, 143), new Color(104, 49, 200));
//        chart.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
//        chart.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));

        lineChart.addData(new ModelChart("January", new double[]{Double.valueOf(doanhThu_Thang(1, year1))}));
        lineChart.addData(new ModelChart("February", new double[]{Double.valueOf(doanhThu_Thang(2 , year1))}));
        lineChart.addData(new ModelChart("March", new double[]{Double.valueOf(doanhThu_Thang(3 , year1))}));
        lineChart.addData(new ModelChart("April", new double[]{Double.valueOf(doanhThu_Thang(4 , year1))}));
        lineChart.addData(new ModelChart("May", new double[]{Double.valueOf(doanhThu_Thang(5 , year1))}));
        lineChart.addData(new ModelChart("June", new double[]{Double.valueOf(doanhThu_Thang(6 , year1))}));
        lineChart.addData(new ModelChart("July", new double[]{Double.valueOf(doanhThu_Thang(7 , year1))}));
        lineChart.addData(new ModelChart("Aughts", new double[]{Double.valueOf(doanhThu_Thang(8 , year1))}));
        lineChart.addData(new ModelChart("September", new double[]{Double.valueOf(doanhThu_Thang(9 , year1))}));
        lineChart.addData(new ModelChart("Octorber", new double[]{Double.valueOf(doanhThu_Thang(10 , year1))}));
        lineChart.addData(new ModelChart("November", new double[]{Double.valueOf(doanhThu_Thang(11 , year1))}));
        lineChart.addData(new ModelChart("December", new double[]{Double.valueOf(doanhThu_Thang(12 , year1))}));
        lineChart.start();
        lineChart.addLegend("Income", new Color(12, 84, 175), new Color(0, 108, 247));
//        lineChart.addLegend("Expense", new Color(54, 4, 143), new Color(104, 49, 200));
//        lineChart.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
//        lineChart.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
//        lineChart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
//        lineChart.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
//        lineChart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
//        lineChart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
//        lineChart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
//        lineChart.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
        lineChart.start();
//        progress1.start();
//        progress2.start();
//        progress3.start();
        jScrollPane1.setVerticalScrollBar(new scrollbar());
        jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.white);
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        jScrollPane2.setVerticalScrollBar(new scrollbar());
        jScrollPane2.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.white);
        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.raven.swing.RoundPanel();
        txtdate = new datechooser.beans.DateChooserCombo();
        roundPanel2 = new com.raven.swing.RoundPanel();
        lineChart = new com.raven.chart.Chart();
        jLabel11 = new javax.swing.JLabel();
        roundPanel3 = new com.raven.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        lblhdnhap = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblhdxuat = new javax.swing.JLabel();
        roundPanel4 = new com.raven.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        panelborder1 = new com.raven.swing.panelborder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblspmax_month = new com.raven.swing.table();
        roundPanel5 = new com.raven.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        panelborder2 = new com.raven.swing.panelborder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblnccmax_month = new com.raven.swing.table();
        roundPanel6 = new com.raven.swing.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        lbldoanhthuNgay = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbldoanhthuNam = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(230, 230, 230));

        txtdate.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
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
    txtdate.setLocale(new java.util.Locale("vi", "", ""));
    txtdate.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            txtdateOnSelectionChange(evt);
        }
    });

    javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
    roundPanel1.setLayout(roundPanel1Layout);
    roundPanel1Layout.setHorizontalGroup(
        roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtdate, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
            .addContainerGap())
    );
    roundPanel1Layout.setVerticalGroup(
        roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    roundPanel2.setBackground(new java.awt.Color(230, 230, 230));
    roundPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    roundPanel2.add(lineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1087, 310));

    jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    jLabel11.setText("Doanh Thu Theo Tháng");
    roundPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

    roundPanel3.setBackground(new java.awt.Color(230, 230, 230));

    jLabel3.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
    jLabel3.setText("Hóa đơn nhập :");
    jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

    lblhdnhap.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
    lblhdnhap.setText("100");

    jLabel4.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
    jLabel4.setText("Hóa đơn xuất :");
    jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

    lblhdxuat.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
    lblhdxuat.setText("100");

    javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
    roundPanel3.setLayout(roundPanel3Layout);
    roundPanel3Layout.setHorizontalGroup(
        roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblhdnhap)
            .addGap(41, 41, 41)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblhdxuat)
            .addContainerGap(48, Short.MAX_VALUE))
    );
    roundPanel3Layout.setVerticalGroup(
        roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(lblhdnhap)
                .addComponent(jLabel4)
                .addComponent(lblhdxuat))
            .addContainerGap(20, Short.MAX_VALUE))
    );

    roundPanel4.setBackground(new java.awt.Color(230, 230, 230));

    jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
    jLabel1.setText("Sản phẩm bán nhiều nhất theo tháng");

    panelborder1.setBackground(new java.awt.Color(255, 255, 255));

    jScrollPane1.setBorder(null);

    tblspmax_month.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
        new String [] {
            "Mã SP", "Mã loại hàng", "Tên SP", "Giá bán", "Số lượng"
        }
    ));
    jScrollPane1.setViewportView(tblspmax_month);

    javax.swing.GroupLayout panelborder1Layout = new javax.swing.GroupLayout(panelborder1);
    panelborder1.setLayout(panelborder1Layout);
    panelborder1Layout.setHorizontalGroup(
        panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panelborder1Layout.setVerticalGroup(
        panelborder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
    roundPanel4.setLayout(roundPanel4Layout);
    roundPanel4Layout.setHorizontalGroup(
        roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel4Layout.createSequentialGroup()
            .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelborder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(roundPanel4Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    roundPanel4Layout.setVerticalGroup(
        roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addGap(10, 10, 10)
            .addComponent(panelborder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    roundPanel5.setBackground(new java.awt.Color(230, 230, 230));

    jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
    jLabel2.setText("Nhà cung cấp có số lượng đơn nhiều nhất theo tháng");

    panelborder2.setBackground(new java.awt.Color(255, 255, 255));

    jScrollPane2.setBorder(null);

    tblnccmax_month.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null}
        },
        new String [] {
            "Mã NCC", "Tên NCC", "Số lượng"
        }
    ));
    jScrollPane2.setViewportView(tblnccmax_month);

    javax.swing.GroupLayout panelborder2Layout = new javax.swing.GroupLayout(panelborder2);
    panelborder2.setLayout(panelborder2Layout);
    panelborder2Layout.setHorizontalGroup(
        panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
            .addContainerGap())
    );
    panelborder2Layout.setVerticalGroup(
        panelborder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelborder2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
    roundPanel5.setLayout(roundPanel5Layout);
    roundPanel5Layout.setHorizontalGroup(
        roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel5Layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addComponent(jLabel2)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(roundPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(panelborder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    roundPanel5Layout.setVerticalGroup(
        roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(panelborder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    roundPanel6.setBackground(new java.awt.Color(230, 230, 230));

    jLabel5.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
    jLabel5.setText("Doanh Thu theo ngày:");
    jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

    lbldoanhthuNgay.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
    lbldoanhthuNgay.setText("100");

    jLabel9.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
    jLabel9.setText("Doanh Thu theo năm:");
    jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

    lbldoanhthuNam.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
    lbldoanhthuNam.setText("100");

    javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
    roundPanel6.setLayout(roundPanel6Layout);
    roundPanel6Layout.setHorizontalGroup(
        roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lbldoanhthuNgay)
            .addGap(38, 38, 38)
            .addComponent(jLabel9)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lbldoanhthuNam)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    roundPanel6Layout.setVerticalGroup(
        roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(roundPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(lbldoanhthuNgay)
                .addComponent(jLabel9)
                .addComponent(lbldoanhthuNam))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(10, 10, 10)
            .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0))
    );
    }// </editor-fold>//GEN-END:initComponents

    private void txtdateOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_txtdateOnSelectionChange
        // TODO add your handling code here:
        sl_DonNhap(Integer.valueOf(DateHelper.toString(txtdate.getSelectedDate().getTime(), "MM")));
        sl_Xuat(Integer.valueOf(DateHelper.toString(txtdate.getSelectedDate().getTime(), "MM")));
        doanhThu_Ngay(Integer.valueOf(DateHelper.toString(txtdate.getSelectedDate().getTime(), "dd")));
        doanhThu_Nam(Integer.valueOf(DateHelper.toString(txtdate.getSelectedDate().getTime(), "YYYY")));
        
        fillTablespdatmuanhieu(Integer.valueOf(DateHelper.toString(txtdate.getSelectedDate().getTime(), "MM")));
        fillTablencc(Integer.valueOf(DateHelper.toString(txtdate.getSelectedDate().getTime(), "MM")));
        
        year1 = Integer.valueOf(DateHelper.toString(txtdate.getSelectedDate().getTime(), "YYYY"));
//        double a = 0;
//        a = doanhThu_Thang(1, year1);
//System.out.println(""+doanhThu_Thang(1, year1));


    }//GEN-LAST:event_txtdateOnSelectionChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbldoanhthuNam;
    private javax.swing.JLabel lbldoanhthuNgay;
    private javax.swing.JLabel lblhdnhap;
    private javax.swing.JLabel lblhdxuat;
    private com.raven.chart.Chart lineChart;
    private com.raven.swing.panelborder panelborder1;
    private com.raven.swing.panelborder panelborder2;
    private com.raven.swing.RoundPanel roundPanel1;
    private com.raven.swing.RoundPanel roundPanel2;
    private com.raven.swing.RoundPanel roundPanel3;
    private com.raven.swing.RoundPanel roundPanel4;
    private com.raven.swing.RoundPanel roundPanel5;
    private com.raven.swing.RoundPanel roundPanel6;
    private com.raven.swing.table tblnccmax_month;
    private com.raven.swing.table tblspmax_month;
    private datechooser.beans.DateChooserCombo txtdate;
    // End of variables declaration//GEN-END:variables
}
