package com.raven.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MainLoading extends javax.swing.JDialog {
    public MainLoading(java.awt.Frame parent, boolean modal) {
    super(parent,modal);
        setUndecorated(true);
        initComponents();

        new Timer(20,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = psload.getValue();
                if(i < 100){
                    psload.setValue(i + 1);
                if(i == 10)
                    lblLoading.setText("Turning On Modules ...");
                if(i == 20)
                    lblLoading.setText("Loading Modules ...");
                if(i == 50)
                    lblLoading.setText("Connecting to Database ...");
                if(i == 70)
                    lblLoading.setText("Connection Successful ...");
                if(i == 80)
                    lblLoading.setText("Launching Application ...");
                }else {
                    MainLoading.this.dispose();

                }
            }
        }).start();
        this.setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblName1 = new javax.swing.JLabel();
        lblinfo = new javax.swing.JLabel();
        lblLoading = new javax.swing.JLabel();
        psload = new progressbar.ProgressBarCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/3454459.jpg"))); // NOI18N

        lblName1.setBackground(new java.awt.Color(255, 255, 255));
        lblName1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName1.setText("VINAFOOD");

        lblinfo.setFont(new java.awt.Font("Genshin", 1, 24)); // NOI18N
        lblinfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblinfo.setText("AN TOÀN - CHẤT LƯỢNG - UY TÍN");

        lblLoading.setBackground(new java.awt.Color(255, 255, 255));
        lblLoading.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLoading.setText("Loading ...");

        psload.setForeground(new java.awt.Color(0, 204, 51));
        psload.setToolTipText("");
        psload.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblinfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLoading)
                .addContainerGap())
            .addComponent(psload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName1)
                .addGap(0, 0, 0)
                .addComponent(lblinfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLoading)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(psload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainLoading dialog = new MainLoading(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLoading;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblinfo;
    private progressbar.ProgressBarCustom psload;
    // End of variables declaration//GEN-END:variables
}
