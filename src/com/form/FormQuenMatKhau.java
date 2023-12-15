package com.form;

import java.util.Properties;
import java.*;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import java.security.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.ssl.SSLContext;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FormQuenMatKhau extends javax.swing.JFrame {

    public FormQuenMatKhau() {
        initComponents();
        setLocationRelativeTo(null);
        txtOTP.setEnabled(false);
    }
    int randomCode;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOTP = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnGuiMa = new javax.swing.JButton();
        btnTiepTuc = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("LẤY LẠI MẬT KHẨU");

        jLabel2.setText("Mã Xác Nhận");

        jLabel3.setText("Email");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnGuiMa.setText("GỬI MÃ");
        btnGuiMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMaActionPerformed(evt);
            }
        });

        btnTiepTuc.setText("TIẾP TỤC");
        btnTiepTuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiepTucActionPerformed(evt);
            }
        });

        btnHuy.setText("HUỶ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
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
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuiMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTiepTuc, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuiMa))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTiepTuc)
                    .addComponent(btnHuy))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTiepTucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiepTucActionPerformed
        if (Integer.valueOf(txtOTP.getText()) == randomCode) {
            FormXacNhanMK xn = new FormXacNhanMK(txtEmail.getText());
            xn.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "NHẬP SAI OTP!!");
            return;
        }
        dispose();
        //new FormXacNhanMK().setVisible(true);

    }//GEN-LAST:event_btnTiepTucActionPerformed

    private void btnGuiMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMaActionPerformed
        if (!validates()) {
            return;
        } else if (checkkh()) {
            guima();
            txtOTP.setEnabled(true);
            return;
        }
    }//GEN-LAST:event_btnGuiMaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        new FormDangNhap((JFrame) SwingUtilities.getWindowAncestor(this), true).setVisible(true);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        btnGuiMa.setEnabled(true);
    }//GEN-LAST:event_txtEmailActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuiMa;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTiepTuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtOTP;
    // End of variables declaration//GEN-END:variables
void guima() {
        try {
            Random rand = new Random();
            randomCode = rand.nextInt(999999);
            String host = "smtp.gmail.com";
            String user = "accfarm0004@gmail.com";
            String pass = "srtn muhd yaiv pbjs";
            String to = txtEmail.getText();
            String subject = "Reseting Code";
            String message = "Mã Xác Nhận Là: " + randomCode + "\nMã Chỉ Có Hiệu Lực Trong 1 Phút";
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
//  pros.put("SMTP","ssl://smtp.gmail.com");
            pros.put("smtp_port", "587");
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
            Security.addProvider(SSLContext.getDefault().getProvider());
            //SSLContext sslContext = SSLContext.getDefault();
//    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            JOptionPane.showMessageDialog(null, "VUI LÒNG CHECK EMAIL ĐỂ LẤY MÃ OTP!!");
            btnGuiMa.setEnabled(false);
            // Hủy bỏ mã OTP sau 60 giây
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    randomCode = rand.nextInt(999999);// đặt mã về dãy random khác
                    btnGuiMa.setEnabled(true);
                }
            }, 60000); // 60 giây
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
    }
//BẮT LỖI

    boolean validates() {

        if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "EMAIL KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtEmail.requestFocus();
            return false;
        }
        return true;
    }

    boolean checkkh() {
        // Kiểm tra mã khách hàng
        String khName = txtEmail.getText().trim();
        String sqlKH = "SELECT COUNT(*) FROM NHANVIEN WHERE EMAIL = ?";
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            PreparedStatement stmtKH = con.prepareStatement(sqlKH);
            stmtKH.setString(1, khName);
            ResultSet resultSetKH = stmtKH.executeQuery();
            resultSetKH.next();
            int countKH = resultSetKH.getInt(1);
            if (countKH <= 0) {
                JOptionPane.showMessageDialog(this, "EMAIL KHÔNG TỒN TẠI!");
                txtEmail.grabFocus();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ tại đây, ví dụ: hiển thị thông báo lỗi
        }
        return true;
    }

}
