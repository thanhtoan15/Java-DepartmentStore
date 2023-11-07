package com.raven.main;

import DAO.NhanVienDAO1;
import Entity.NhanVien;
import Helper.ShareHelper;
import com.raven.component.Message;
import com.raven.component.PanelCover;
import com.raven.component.PanelLoading;
import com.raven.component.PanelLoginAndRegister;
import com.raven.component.PanelVerifyCode;
import com.raven.form.PanelForgotPass;
import com.raven.model.ModelMessage;
import com.raven.notification.Notification;
import com.raven.service.ServiceMail;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.message.MessageDialog;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main_Login extends javax.swing.JDialog {
    
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoading loading;
    private PanelVerifyCode verifyCode;
    private PanelVerifyCode verifyCode1;
    private PanelLoginAndRegister loginAndRegister;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private String vertifyCodeString = "";
    private String vertifyCodeString1 = "";
    NhanVienDAO1 dao = new NhanVienDAO1();
//        Main a = new Main();

    public Main_Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        
        if (ShareHelper.USER == null) {
            System.out.println("hello");
        }
        initComponents();
        
        init();
        
    }
////// mới 

    public Main_Login(java.awt.Frame parent, boolean modal, String manv, String matKhau) {
        super(parent, modal);
        setUndecorated(true);
        NhanVien nhanVien = dao.findByIDanhMK(manv, matKhau);
        ShareHelper.USER = nhanVien;
        if (ShareHelper.USER == null) {
            new MainLoading(null, true).setVisible(true);
        } else if(ShareHelper.USER != null && !ShareHelper.USER.getTrangThai().equalsIgnoreCase("Đã Nghỉ")){
            System.out.println("" + ShareHelper.USER.getMaNhanVien());
            this.dispose();
            new Main().setVisible(true);
        }else if(ShareHelper.USER != null && ShareHelper.USER.getTrangThai().equalsIgnoreCase("Đã Nghỉ")){
             new MainLoading(null, true).setVisible(true);
        }
        
        initComponents();
        init();
  
    }
// void noti_infor(String noti) {
//        new Notification(, Notification.Type.INFO, Notification.Location.TOP_CENTER, noti).showNotification();
//
//    }
//
//    void noti_sucess(String noti) {
//        new Notification(null, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, noti).showNotification();
//    }
//
//    void noti_warning(String noti) {
//        new Notification(a, Notification.Type.WARNING, Notification.Location.TOP_CENTER, noti).showNotification();
//    }
//
//    Boolean noti_Choice(String tittle, String detail) {
//        MessageDialog obj = new MessageDialog(a);
//        obj.showMessage(tittle, detail);
//        if (obj.getMessageType() == MessageDialog.MessageType.OK) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    //sinh mã code

    public String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        System.out.println("Random mã thành công");
        return code;
    }
    
    private void init() {
        
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loading = new PanelLoading();
        verifyCode = new PanelVerifyCode();
        verifyCode1 = new PanelVerifyCode();

        // sự kiện button register
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                NhanVien user = loginAndRegister.getUser();
                try {
                    NhanVien nv = dao.findById(user.getMaNhanVien());
//                    System.out.println(""+user.getMaNhanVien()+" - "+nv.getMaNhanVien());
                    if (nv == null) {
                        System.out.println("" + loginAndRegister.mk2() + "- " + user.getMatKhauNhanVien());
                        if (user.getMaNhanVien().equals("")) {
                            showMessage(Message.MessageType.ERROR, "mã nhân viên không được để trống");
                        } else if (user.getEmailNhanVien().equals("")) {
                            showMessage(Message.MessageType.ERROR, "email không được để trống");
                        } else if (dao.findByEmail(user.getEmailNhanVien()) != null) {
                            showMessage(Message.MessageType.ERROR, "Email đã được sử dụng");
                        } else if (!user.getMatKhauNhanVien().equalsIgnoreCase(loginAndRegister.mk2())) {
                            showMessage(Message.MessageType.ERROR, "Mật khẩu không khớp");
                        }
                        else {
                            if(user.getMatKhauNhanVien().equals("")){
                                user.setMatKhauNhanVien("1");
                                vertifyCodeString = generateVerifyCode();
                            sendMain(user.getEmailNhanVien(), vertifyCodeString);
                            System.out.println(vertifyCodeString);
                            }else{
                                vertifyCodeString = generateVerifyCode();
                            sendMain(user.getEmailNhanVien(), vertifyCodeString);
                            System.out.println(vertifyCodeString);
                            }
                            
                        }
                    } else {
                        showMessage(Message.MessageType.ERROR, "Mã nhân viên đã được sửu dụng");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        //đăng nhập
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                login();
            }
        };
        
        ActionListener eventqr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginAndRegister.checkqr() == 1 && ShareHelper.USER == null) {
                    Main_Login.this.dispose();
                    new Scan().setVisible(true);
                }
            }
        };
        //  quên mk

        ActionListener evtqmk = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVien user = dao.findById(loginAndRegister.getDataLogin().getMaNhanVien());
                if (loginAndRegister.checkmk() == 2 && ShareHelper.USER == null) {
                    if (user != null) {
                        try {
                            System.out.println("" + user.getEmailNhanVien());
                            
                            vertifyCodeString1 = generateVerifyCode();
                            
                            sendMain1(user.getEmailNhanVien(), vertifyCodeString1);
                            verifyCode1.addEventButtonOK(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (verifyCode1.getInputCode().trim().equals(vertifyCodeString1.trim())) {
                                        showMessage(Message.MessageType.SUCCESS, "Nhập mã thành công");
                                        verifyCode1.setVisible(false);
                                        ShareHelper.USER = user;
                                        new PanelForgotPass(null, true).setVisible(true);
                                        
                                    } else {
                                        showMessage(Message.MessageType.ERROR, "Sai mã rồi 1, vui lòng nhập lại");
                                    }
                                }
                            });
                        } catch (SQLException ex) {
                            Logger.getLogger(Main_Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    } else {
                        showMessage(Message.MessageType.ERROR, "không tìm thấy mã nhân viên");
                    }
                }
            }
        };
        
        loginAndRegister = new PanelLoginAndRegister(eventRegister, eventLogin, eventqr, evtqmk);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }
            
            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);  //  for smooth animation
        bg.setLayout(layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode1, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(verifyCode1, "pos 0 0 100% 100%");
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%"); //  1al as 100%
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });

        // form gửi code gửi 
        verifyCode.addEventButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("---" + verifyCode.getInputCode().trim() + vertifyCodeString.trim() + "---");
                
                if (verifyCode.getInputCode().trim().equals(vertifyCodeString.trim())) {
                    NhanVien user = loginAndRegister.getUser();
                    register();
//                    showMessage(Message.MessageType.SUCCESS, "Đăng kí thành công ");
                    verifyCode.setVisible(false);
                    loginAndRegister.clear();
                    
                } else {
                    showMessage(Message.MessageType.ERROR, "Sai mã rồi 2 , vui lòng nhập lại");
                }
                
            }
        });
    }
    
    private boolean register() {
        NhanVien user = loginAndRegister.getUser();
        if(user.getMatKhauNhanVien().equals("1")){
            user.setMatKhauNhanVien("123");
                    dao.insert(user);
                    showMessage(Message.MessageType.SUCCESS, "Đăng kí thành công "+"\n Mật khẩu mặc định của bạn là :"+user.getMatKhauNhanVien());
                    return true;
        }else{
             dao.insert(user);
             showMessage(Message.MessageType.SUCCESS, "Đăng kí thành công ");
             return true;
        }
            
//        return true;
    }
    
    private boolean login() {
        NhanVien nv = dao.findByIDanhMK(loginAndRegister.getDataLogin().getMaNhanVien(), loginAndRegister.getDataLogin().getMatKhauNhanVien());
//        int i = 0;
        try {
            if (nv != null && !nv.getTrangThai().equalsIgnoreCase("Đã Nghỉ")) {
                 ShareHelper.USER = nv;
                System.out.println("đăng nhập thành công");
//                System.out.println(""+nv.getTrangThai());
                showMessage(Message.MessageType.SUCCESS, "Đăng nhập thành công");
//                
                Main_Login.this.dispose();
                int i = 0;
                new Main(i).setVisible(true);
            } 
            else if(nv != null && nv.getTrangThai().equalsIgnoreCase("Đã Nghỉ")  ){
                showMessage(Message.MessageType.ERROR, "Nhân viên đã nghỉ việc");
                return false;
                }
            else {
                showMessage(Message.MessageType.ERROR, "Kiểm tra tài khoản và mật khẩu");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(Message.MessageType.ERROR, "Lỗi đăng nhập");
        }
        return true;
    }
    
    private void sendMain(String mail, String VertifyCode) {
        new Thread(new Runnable() {
            @Override
            
            public void run() {
//                NhanVien user = loginAndRegister.getUser();
//                if(user.getMaNhanVien().equals(dao.findById(user.getMaNhanVien()))){
//                    showMessage(Message.MessageType.ERROR, "Tài khoản đã được sử dụng");
//                }else{
                loading.setVisible(true);
                ModelMessage ms = new ServiceMail().sendMain(mail, VertifyCode);
                System.out.println(mail + " " + VertifyCode);
                if (ms.isSuccess()) {
                    loading.setVisible(false);
                    verifyCode.setVisible(true);
                } else {
                    loading.setVisible(false);
                    showMessage(Message.MessageType.ERROR, ms.getMessage());
                }
//            }
            }
        }).start();
    }
    
    private void sendMain1(String mail, String VertifyCode) {
        new Thread(new Runnable() {
            @Override
            
            public void run() {
                
                loading.setVisible(true);
                ModelMessage ms = new ServiceMail().sendMain(mail, VertifyCode);
                System.out.println(mail + " " + VertifyCode);
                if (ms.isSuccess()) {
                    loading.setVisible(false);
                    verifyCode1.setVisible(true);
                } else {
                    loading.setVisible(false);
                    showMessage(Message.MessageType.ERROR, ms.getMessage());
                }
                
            }
        }).start();
    }
    
    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }
            
            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }
            
            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main_Login dialog = new Main_Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                        
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
