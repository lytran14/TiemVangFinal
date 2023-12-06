package com.form;

import Class_DAO.NhanVien_DAO;
import Class_Model.NhanVien_Model;
import Class_Utils.Auth;
import com.main.Main;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

public class FormDangNhap extends javax.swing.JDialog {

    NhanVien_DAO dao = new NhanVien_DAO();

    public FormDangNhap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        rememberMe();
    }
    Preferences preferences;
    boolean rememberP;

    void rememberMe() {
        preferences = Preferences.userNodeForPackage(this.getClass());
        rememberP = preferences.getBoolean("RememberMe", Boolean.valueOf(""));
        if (rememberP) {
            txtTaiKhoan.setText(preferences.get("User", ""));
            txtPass.setText(preferences.get("Password", ""));
            chkLuuMatKhau.setSelected(rememberP);
        }
    }

    private void LOGIN() {
        String maNV = txtTaiKhoan.getText();
        String matKhau = new String(txtPass.getPassword());
        NhanVien_Model nhanVien = dao.selectById(maNV);
        if (nhanVien == null) {
            JOptionPane.showMessageDialog(this, "TÊN ĐĂNG NHẬP SAI!!!");
        } else if (!matKhau.equals(nhanVien.getMATKHAU())) {
            JOptionPane.showMessageDialog(this, "MẬT KHẨU SAI!!!");
            return;
        } else {
            if (chkLuuMatKhau.isSelected() && !rememberP) {
                preferences.put("User", txtTaiKhoan.getText());
                preferences.put("Password", txtPass.getText());
                preferences.putBoolean("RememberMe", true);
            } else if (!chkLuuMatKhau.isSelected() && rememberP) {
                preferences.put("User", "");
                preferences.put("Password", "");
                preferences.putBoolean("RememberMe", false);
            }
            JOptionPane.showMessageDialog(this, "ĐĂNG NHẬP THÀNH CÔNG!");
            Auth.user = nhanVien;
//            new Main().setVisible(true);
            this.dispose();
        }
    }

    void QuenMK() {
        this.dispose();

        FormQuenMatKhau formQuenMatKhau = new FormQuenMatKhau();
        formQuenMatKhau.setVisible(true);
        //new Main().setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        chkLuuMatKhau = new javax.swing.JCheckBox();
        lbQmk = new javax.swing.JLabel();
        btnHuy = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0,80));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("WELCOME BACK!");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 36, -1, 47));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mật Khẩu");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 194, 129, 31));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tên Tài Khoản");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 135, 129, 31));
        jPanel2.add(txtTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 136, 311, 31));
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 195, 311, 31));

        chkLuuMatKhau.setBackground(new java.awt.Color(0, 0, 0));
        chkLuuMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        chkLuuMatKhau.setText("Lưu Mật Khẩu");
        jPanel2.add(chkLuuMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 244, -1, -1));

        lbQmk.setBackground(new java.awt.Color(0, 0, 0));
        lbQmk.setForeground(new java.awt.Color(255, 255, 255));
        lbQmk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQmk.setText("Quên Mật Khẩu?");
        lbQmk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbQmkMouseClicked(evt);
            }
        });
        jPanel2.add(lbQmk, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 244, 170, -1));

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel2.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 291, 121, 32));

        btnLogin.setText("Đăng Nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 291, 121, 32));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Vui lòng nhập thông tin tài khoản của bạn!!");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 89, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/log.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        LOGIN();
        new Main().setVisible(true);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(this, "BẠN THẬT SỰ MUỐN "
                + "THOÁT ?",
            "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void lbQmkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbQmkMouseClicked
        QuenMK();
    }//GEN-LAST:event_lbQmkMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormDangNhap dialog = new FormDangNhap(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox chkLuuMatKhau;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbQmk;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
