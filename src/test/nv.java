/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package test;

import DAO.NhanVienDAO1;
import Entity.NhanVien;
import Validate.Validate;
import com.raven.main.Main;
import com.raven.notification.Notification;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FPTSHOP
 */
public class nv extends javax.swing.JFrame {

    NhanVienDAO1 dao = new NhanVienDAO1();

    /**
     * Creates new form nv
     */
    public nv() {
        initComponents();
        fillTable();
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblnv.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.select();
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
            System.out.print("lui");
            e.printStackTrace();
        }
    }

    private boolean validateform() {
        StringBuilder str = new StringBuilder();
        List<NhanVien> list = dao.select();
        for (NhanVien nv : list) {
            if (txtma.getText().equalsIgnoreCase(nv.getMaNhanVien())) {
                System.out.print(nv.getMaNhanVien());
                JOptionPane.showMessageDialog(this, "trung ma nhan vien");
                break;
            } else {
                if (Validate.isEmpty(txtma, str, "Mã nhân viên không được để trống")) {
                    JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống");
                    return false;
                }
                if (!Validate.isID(txtma, str, "Mã nhân viên không đúng định dạng")) {
                    JOptionPane.showMessageDialog(this, "Mã nhân viên không đúng định dạng");
                    return false;

                }
                if (Validate.isEmpty(txthoten, str, "Họ tên không được để trống")) {
                    JOptionPane.showMessageDialog(this, "Họ tên không được để trống");
                    return false;
                }
                if (!Validate.isName(txthoten, str, "Họ tên không đúng định dạng")) {
                    JOptionPane.showMessageDialog(this, "Họ tên không đúng định dạng");
                    return false;
                }

                if (Validate.isEmpty(txtEmail, str, "Email không được để trống")) {
                    JOptionPane.showMessageDialog(this, "Email không được để trống");
                    return false;
                }
                if (!Validate.isEmail(txtEmail, str, "Email không đúng định dạng")) {
                    JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
                    return false;
                }
                if (Validate.isEmpty(txtsdt, str, "Số điện thoại không được để trống")) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
                    return false;
                }
                if (!Validate.isSDT(txtsdt, str, "")) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng");
                    return false;
                }
                if (rdoql.isSelected() == false && rdonv.isSelected() == false) {
//            noti_warning("Chưa chọn vài trò");
                    JOptionPane.showMessageDialog(this, "Chưa chọn vài trò");
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNhanVien(txtma.getText());
        model.setHoTenNhanVien(txthoten.getText());
        model.setEmailNhanVien(txtEmail.getText());
        model.setSdtNhanVien(txtsdt.getText());
        model.setVaiTroNhanVien(rdoql.isSelected() ? 0 : 1);

        return model;
    }

    void insert() {
        NhanVien model = getModel();
        try {
            dao.insert(model);
            this.fillTable();
//            noti_sucess("Thêm mới thành công!");
            JOptionPane.showMessageDialog(this, "them thanh cong");
        } catch (HeadlessException e) {
//            noti_warning("Thêm mới thất bại!");
            JOptionPane.showMessageDialog(this, "them  k thanh cong");
        }
    }
    
    void update() {

        NhanVien model = getModel();
        try {
            dao.update(model);
            this.fillTable();
//            noti_sucess("Cập nhật thành công!");
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        } catch (Exception e) {
//            noti_warning("Cập nhật thất bại!");
JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnv = new javax.swing.JTable();
        txtma = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txthoten = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        rdoql = new javax.swing.JRadioButton();
        rdonv = new javax.swing.JRadioButton();
        check = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblnv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "s", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(tblnv);

        buttonGroup1.add(rdoql);
        rdoql.setText("ql");

        buttonGroup1.add(rdonv);
        rdonv.setText("nv");

        check.setText("check");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });

        btnsua.setText("sua");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txthoten, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(txtsdt))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoql, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdonv, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnsua)
                            .addComponent(check)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoql)
                            .addComponent(check))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdonv)
                    .addComponent(btnsua))
                .addGap(78, 78, 78)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        // TODO add your handling code here:
//        if (validateform()) {
//            this.insert();
//        }
        NhanVien s = new NhanVien();
        List<NhanVien> list = dao.select();
//        ArrayList<NhanVien> list = (ArrayList<NhanVien>) new NhanVienDAO1().select();
//        if (list.size() > 0) {
//            JOptionPane.showMessageDialog(this, "Mã ID bị trùng", "vui lòng kiểm tra lại", JOptionPane.ERROR_MESSAGE);
//        } else {
//            this.insert();
//        }
          s.setMaNhanVien(txtma.getText());          
          if(dao.findById(s.getMaNhanVien()) == null ){
              if(validateform()){
             this.insert();}
          }else if(dao.findById(s.getMaNhanVien()) != null){
             JOptionPane.showMessageDialog(this, "Mã ID ton tai ", "vui lòng kiểm tra lại", JOptionPane.ERROR_MESSAGE);
             txtma.setBackground(Color.red);
          }
    }//GEN-LAST:event_checkActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        if(validateform()){
            this.update();
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    /**
         * @param args the command line arguments
         */
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
            java.util.logging.Logger.getLogger(nv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nv().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton check;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdonv;
    private javax.swing.JRadioButton rdoql;
    private javax.swing.JTable tblnv;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
