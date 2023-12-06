package com.form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class FormXacNhanMK extends javax.swing.JFrame {

    PreparedStatement pst = null;
    public String email;

    public FormXacNhanMK() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public FormXacNhanMK(String email) {
        this.email = email;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPass2 = new javax.swing.JTextField();
        txtPass1 = new javax.swing.JTextField();
        btnDongY = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("XÁC NHẬN MẬT KHẨU MỚI");

        jLabel2.setText("Nhập Lại Mật Khẩu Mới");

        jLabel3.setText("Nhập Mật Khẩu Mới");

        btnDongY.setText("ĐỒNG Ý");
        btnDongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYActionPerformed(evt);
            }
        });

        jButton3.setText("HUỶ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDongY, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDongY)
                    .addComponent(jButton3))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYActionPerformed
        if (!validates()) {
            return;
        } else {
            xacNhanMkMoi();
        }
    }//GEN-LAST:event_btnDongYActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormXacNhanMK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDongY;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtPass1;
    private javax.swing.JTextField txtPass2;
    // End of variables declaration//GEN-END:variables

    void xacNhanMkMoi() {
        if (txtPass1.getText().equals(txtPass2.getText())) {
            try {
                String updateQuery = "UPDATE NHANVIEN SET MATKHAU = ? WHERE email=? ";
                Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
                pst = con.prepareStatement(updateQuery);
                pst.setString(1, txtPass2.getText());
                pst.setString(2, email);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "THAY ĐỔI MẬT KHẨU THÀNH CÔNG");
                dispose();
                new FormDangNhap(this, true).setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
//                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "MẬT KHẨU XÁC NHẬN KHÔNG TRÙNG KHỚP");
        }
    }

    boolean validates() {

        if (txtPass1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "MẬT KHẨU MỚI KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtPass1.requestFocus();
            return false;
        }
        if (txtPass2.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "XÁC NHẬN MẬT KHẨU KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtPass2.requestFocus();
            return false;
        }
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-=_+\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";
        if (!txtPass1.getText().matches(regex)) {
            String message = "Mật khẩu không hợp lệ.\n\n";
            message += "Yêu cầu:\n";
            message += "- Ít nhất 8 ký tự.\n";
            message += "- Chứa ít nhất một chữ IN HOA.\n";
            message += "- Chứa ít nhất một số.\n";
            message += "- Chứa ít nhất một ký tự đặc biệt.";
            JOptionPane.showMessageDialog(this, message, "Chú ý!", JOptionPane.WARNING_MESSAGE);
            txtPass1.requestFocus();
            return false;
        }
        return true;
    }
}
