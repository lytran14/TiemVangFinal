package com.form;

import Class_DAO.NhanVien_DAO;
import Class_Utils.Auth;
import Class_Utils.MsgBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FormDoiMatKhau extends javax.swing.JDialog {

    NhanVien_DAO dao = new NhanVien_DAO();

    public FormDoiMatKhau(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    private void doiMatKhau() {
        String matKhauMoi = new String(txtMKMoi.getPassword());
        Auth.user.setMATKHAU(matKhauMoi);
        dao.update(Auth.user);
        MsgBox.alert(this, "ĐỔI MẬT KHẨU THÀNH CÔNG!");
        Auth.clear();
        dispose();
        new FormDangNhap((JFrame) SwingUtilities.getWindowAncestor(this), true).setVisible(true);
    }

    boolean validates() {
        if (!txtMKCu.getText().equalsIgnoreCase(Auth.user.getMATKHAU())) {
            MsgBox.alert(this, "SAI MẬT KHẨU ĐĂNG NHẬP!");
            txtMKCu.requestFocus();
            return false;
        }
        if (txtMKMoi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "MẬT KHẨU MỚI KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtMKMoi.requestFocus();
            return false;
        }
        if (txtXacNhanMK.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "XÁC NHẬN MẬT KHẨU KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtXacNhanMK.requestFocus();
            return false;
        }
        if (txtMKCu.getText().equals(txtMKMoi.getText())) {
            MsgBox.alert(this, "MẬT KHẨU CŨ VÀ MẬT KHẨU MỚI KHÔNG ĐƯỢC GIỐNG NHAU!!");
            txtMKCu.requestFocus();
            return false;
        } else if (!txtXacNhanMK.getText().equals(txtMKMoi.getText())) {
            MsgBox.alert(this, "MẬT KHẨU XÁC NHẬN KHÔNG TRÙNG KHỚP!");
            txtXacNhanMK.requestFocus();
            return false;
        }
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-=_+\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";
        if (!txtMKMoi.getText().matches(regex)) {
            String message = "Mật khẩu không hợp lệ.\n\n";
            message += "Yêu cầu:\n";
            message += "- Ít nhất 8 ký tự.\n";
            message += "- Chứa ít nhất một chữ IN HOA.\n";
            message += "- Chứa ít nhất một số.\n";
            message += "- Chứa ít nhất một ký tự đặc biệt.";
            JOptionPane.showMessageDialog(this, message, "Chú ý!", JOptionPane.WARNING_MESSAGE);
            txtMKMoi.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnDongY = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnHuy = new javax.swing.JButton();
        txtMKCu = new javax.swing.JPasswordField();
        txtXacNhanMK = new javax.swing.JPasswordField();
        txtMKMoi = new javax.swing.JPasswordField();

        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("ĐỔI MẬT KHẨU");

        jLabel2.setText("Nhập Mật Khẩu Mới");

        jLabel3.setText("Nhập Mật Khẩu Cũ");

        btnDongY.setText("ĐỒNG Ý");
        btnDongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYActionPerformed(evt);
            }
        });

        jLabel4.setText("Xác Nhận Mật Khẩu Mới");

        btnHuy.setText("HUỶ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtXacNhanMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXacNhanMKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDongY, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtXacNhanMK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMKMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMKCu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMKCu)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtXacNhanMK, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDongY)
                    .addComponent(btnHuy))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYActionPerformed
        if (!Auth.isLogin()) {
            MsgBox.alert(this, "CHƯA ĐĂNG NHẬP!");
            return;
        }
        if (!validates()) {
            return;
        } else {
            doiMatKhau();
        }
    }//GEN-LAST:event_btnDongYActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtXacNhanMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtXacNhanMKActionPerformed

    }//GEN-LAST:event_txtXacNhanMKActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormDoiMatKhau dialog = new FormDoiMatKhau(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnDongY;
    private javax.swing.JButton btnHuy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtMKCu;
    private javax.swing.JPasswordField txtMKMoi;
    private javax.swing.JPasswordField txtXacNhanMK;
    // End of variables declaration//GEN-END:variables
}
