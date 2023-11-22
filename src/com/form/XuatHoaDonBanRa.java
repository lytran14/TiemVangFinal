package com.form;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class XuatHoaDonBanRa extends javax.swing.JFrame {

    private FormBanRa formBanRa;

    public XuatHoaDonBanRa(FormBanRa form) {
        initComponents();
        setLocationRelativeTo(this);
        formBanRa = form; // Lưu trữ tham chiếu của FormMuaVao
        init(formBanRa);
    }

    void init(FormBanRa form1) {
        JTable tblBan = form1.getTblBanRa();
        JTextField txtTenKH = form1.getTxtTenKH();
        JTextField txtMaHD = form1.getTxtMaHD();
        JTextField txtThanhToan = form1.getTxtThanhToan();
        JTextField txtTongTien = form1.getTxtTongTien();

        txtHoaDOn.setText("                         ************************************************************\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "                                     ------------------TIỆM VÀNG--------------\n");
        txtHoaDOn.setText(txtHoaDOn.getText() + "ĐC: Toà nhà FPT Polytechnic, Đ. Số 22, Thường Thạnh, Cái Răng, Cần Thơ\n");
        txtHoaDOn.setText(txtHoaDOn.getText() + "SĐT: 0981 725 836\n");
        txtHoaDOn.setText(txtHoaDOn.getText() + "Mã Hoá Đơn: " + txtMaHD.getText() + "\t\tKhách Hàng: " + txtTenKH.getText() + "\n");
        txtHoaDOn.setText(txtHoaDOn.getText() + "-------------------------------------------------------------------------------------------------------------\n");
        txtHoaDOn.setText(txtHoaDOn.getText() + "Tên Sản Phẩm\t Loại vàng\t Trọng Lượng\t Đơn Giá\t Thành Tiền\n");
        txtHoaDOn.setText(txtHoaDOn.getText() + "-------------------------------------------------------------------------------------------------------------\n");

        DefaultTableModel dt = (DefaultTableModel) tblBan.getModel();
        for (int i = 0; i < tblBan.getRowCount(); i++) {
            String tenSP = dt.getValueAt(i, 1).toString();
            String loaiv = dt.getValueAt(i, 2).toString();
            String khoiluong = dt.getValueAt(i, 3).toString();
            String dongia = dt.getValueAt(i, 4).toString();
            String thanhtien = dt.getValueAt(i, 5).toString();
            txtHoaDOn.setText(txtHoaDOn.getText() + tenSP + "\t" + loaiv + "\t" + khoiluong + "\t" + dongia + "\t" + thanhtien + "\n");
        }
//add tong bill
        txtHoaDOn.setText(txtHoaDOn.getText() + "-------------------------------------------------------------------------------------------------------------\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "Tổng Tiền : " + txtTongTien.getText() + " VNĐ" + "\n");
        txtHoaDOn.setText(txtHoaDOn.getText() + "Thanh Toán: " + txtThanhToan.getText() + " VNĐ" + "\n");
        //DATE TIME
        Date dd = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss");
        String Date = dateFormat.format(dd);
        String time = dateTime.format(dd);
        txtHoaDOn.setText(txtHoaDOn.getText() + "-------------------------------------------------------------------------------------------------------------\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "Date: " + Date + "\t\tTime: " + time + "\n");
        txtHoaDOn.setText(txtHoaDOn.getText() + "-------------------------------------------------------------------------------------------------------------\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "*******************************************************************************************\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "\tCẢM ƠN QUÝ KHÁCH ĐÃ TIN TƯỞNG VÀ ỦNG HỘ!!\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "\t\t               ******\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "\n ");
        txtHoaDOn.setText(txtHoaDOn.getText() + "\t\tCode by Nhom9\n ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHoaDOn = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jButton1.setText("HUỶ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("IN HOÁ ĐƠN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 102, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN HOÁ ĐƠN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        txtHoaDOn.setColumns(20);
        txtHoaDOn.setRows(5);
        jScrollPane1.setViewportView(txtHoaDOn);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormBanRa form1 = new FormBanRa(); // Tạo instance của FormMuaVao
                XuatHoaDonBanRa xuatHD = new XuatHoaDonBanRa(form1); // Truyền tham chiếu của FormMuaVao
                xuatHD.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtHoaDOn;
    // End of variables declaration//GEN-END:variables
}
