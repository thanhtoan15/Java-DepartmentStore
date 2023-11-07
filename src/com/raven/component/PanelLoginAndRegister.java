package com.raven.component;

import DAO.NhanVienDAO1;
import Entity.NhanVien;
import com.raven.main.Main_Login;
import com.raven.main.Scan;
import com.raven.swing.Button;
import com.raven.swing.MyPasswordField;
import com.raven.swing.MyTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import radio_button.RadioButtonCustom;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public NhanVien getDataLogin() {
        return dataLogin;
    }

    public NhanVien getUser() {
        return user;
    }
    private NhanVienDAO1 dao;
    private NhanVien user;
    private NhanVien dataLogin;
    String mk2;

    public String mk2() {
        return mk2;
    }
    int qr = 0;

    public int checkqr() {
        return qr;
    }
    int qmk = 0;

    public int checkmk() {
        return qmk;
    }

    public PanelLoginAndRegister(ActionListener eventRegister, ActionListener eventLogin, ActionListener eventqr, ActionListener evtqmk) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin, eventqr, evtqmk);
        login.setVisible(true);
        register.setVisible(false);

//        Forgotpass.setVisible(false);
    }
    MyTextField txtUser = new MyTextField();
    MyTextField txtName = new MyTextField();
    MyTextField txtEmail = new MyTextField();
    MyTextField txtSDT = new MyTextField();
    MyPasswordField txtPass = new MyPasswordField();
    radio_button.RadioButtonCustom rdonv = new RadioButtonCustom();
    MyPasswordField txtPass2 = new MyPasswordField();
    radio_button.RadioButtonCustom rdoquanly = new RadioButtonCustom();

    public void reques(Component p) {
        p.requestFocus();
    }

    private void initRegister(ActionListener eventRegister) {

        register.setLayout(new MigLayout("wrap", "push[left]push", "push[]25[]10[]10[]10[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("TẠO TÀI KHOẢN");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);

        // user

        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/staff.png")));
        txtUser.setHint("MaNV (*)");
        txtUser.setFont(new Font("sansserif", 1, 10));
        register.add(txtUser, "w 60%"); 

        // name
        txtName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
        txtName.setHint("Họ Tên");
        txtName.setFont(new Font("sansserif", 1, 10));
        register.add(txtName, "w 60%");

        // email 
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/mail.png")));
        txtEmail.setHint("Email (*)");
        txtEmail.setFont(new Font("sansserif", 1, 10));
        register.add(txtEmail, "w 60%");
        // sdt 
        txtSDT.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/phone.png")));
        txtSDT.setHint("Số Điện Thoại ");
        txtSDT.setFont(new Font("sansserif", 1, 10));
        register.add(txtSDT, "w 60%");
        // pass
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtPass.setHint("Mật Khẩu");
        txtPass.setFont(new Font("sansserif", 1, 10));
        register.add(txtPass, "w 60%");
        //pass2
        txtPass2.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtPass2.setHint("Xác nhận Mật Khẩu");
        txtPass2.setFont(new Font("sansserif", 1, 10));
        register.add(txtPass2, "w 60%");

        // vai trò 
        rdonv.setText("Nhân Viên         ");
        rdonv.setFont(new Font("Segoe UI", 1, 12));
        
        rdonv.setBackground(new Color(7, 164, 121));
        register.add(rdonv, "cell 0 7"); // "cell column row width height"

        rdoquanly.setText("Quản Lý");
        rdoquanly.setFont(new Font("Segoe UI", 1, 12));
        rdoquanly.setBackground(new Color(7, 164, 121));
        rdoquanly.setSelected(true);
        register.add(rdoquanly, "cell 0 7");  // "cell column row width height"

        ButtonGroup gr1 = new ButtonGroup();
        gr1.add(rdonv);
        gr1.add(rdoquanly);

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventRegister);
        cmd.setText("ĐĂNG KÍ");
        register.add(cmd, "w 40%, h 40");

        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user = new NhanVien(txtUser.getText(), txtName.getText(), txtSDT.getText(), txtEmail.getText(), rdonv.isSelected() ? 1 : 0, String.valueOf(txtPass.getText()), "Đang làm");
                mk2 = txtPass2.getText();
            }
        });
    }

    private void initLogin(ActionListener eventLogin, ActionListener eventqr, ActionListener evtqmk) {

        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("ĐĂNG NHẬP");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);

        MyTextField txtManv = new MyTextField();
        txtManv.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
        txtManv.setHint("MaNV");
        txtManv.setText("NV01");
        login.add(txtManv, "w 60%");

        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtPass.setHint("Mật khẩu");
        txtPass.setText("123");
        login.add(txtPass, "w 60%");

        // Button QR code
        JButton btnQR = new JButton();
        
        btnQR.setForeground(new Color(100, 100, 100));
        btnQR.setFont(new Font("sansserif", 1, 12));
        btnQR.setContentAreaFilled(false);
        btnQR.addActionListener(eventqr);
        btnQR.setIcon(new ImageIcon("src\\Icon\\barcode.png"));
        btnQR.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(btnQR);
        btnQR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qr = 1;
            }
        });
        // button quên mk
        JButton cmdForget = new JButton("Quên mật khẩu ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.addActionListener(evtqmk);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);

        cmdForget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qmk = 2;
                String manv = txtManv.getText();
//                String password = String.valueOf(txtPass.getPassword());
                dataLogin = new NhanVien(manv, null, null, null, 0, null, null);
            }
        });

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventLogin);
        cmd.setText("ĐĂNG NHẬP");
        login.add(cmd, "w 40%, h 40");

        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String manv = txtManv.getText();
                String password = String.valueOf(txtPass.getPassword());
                dataLogin = new NhanVien(manv, null, null, null, 0, password, null);
                System.out.println(manv + " " + password);
            }
        });


    }

    public void clear() {
        txtName.setText("");
        txtEmail.setText("");
        txtPass.setText("");
        txtPass2.setText("");
        txtUser.setText("");
        txtSDT.setText("");
    }

    public void showRegister(boolean show) {
        if (show) {

            register.setVisible(false);
            login.setVisible(true);
        } else {

            register.setVisible(true);
            login.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
