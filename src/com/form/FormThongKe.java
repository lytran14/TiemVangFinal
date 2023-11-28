package com.form;

import Class_DAO.BanRa_DAO;
import Class_DAO.ThongKe_DAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class FormThongKe extends javax.swing.JPanel {

    ThongKe_DAO dao = new ThongKe_DAO();
    BanRa_DAO brdao = new BanRa_DAO();

    public FormThongKe() {
        initComponents();
        fillComboBoxNam();
    }

    void fillComboBoxNam() {
        DefaultComboBoxModel modol = (DefaultComboBoxModel) cboNam.getModel();
        modol.removeAllElements();
        List<Integer> list = brdao.selectYear();
        for (Integer year : list) {
            modol.addElement(year);
        }
    }

    void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        Integer nam = (Integer) cboNam.getSelectedItem();
        if (nam != null) {
            List<Object[]> list = dao.getDThu(nam); // Sử dụng giá trị của 'nam' thay vì giá trị cứng 2023
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
        fillDoanhThu();
    }

    void fillDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        int rowCount = model.getRowCount();
        if (rowCount > 0) {
            // Giả sử chỉ lấy giá trị từ hàng đầu tiên trong bảng
            Object soKH = model.getValueAt(0, 0);
            Object soBR = model.getValueAt(0, 1);
            Object soMV = model.getValueAt(0, 2);
            Object doanhThu = model.getValueAt(0, 5);
            // Gán giá trị cho các label tương ứng
            txtKhachHang.setText(soKH.toString());
            txtBanRa.setText(soBR.toString());
            txtMuaVao.setText(soMV.toString());
            txtDoanhThu.setText(doanhThu.toString());
        } else {
            // Xử lý khi không có dữ liệu trong bảng
            txtKhachHang.setText("");
            txtBanRa.setText("");
            txtMuaVao.setText("");
            txtDoanhThu.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDateTu = new com.toedter.calendar.JDateChooser();
        txtDateDen = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        btnLoc = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cboNam = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtBanRa = new javax.swing.JLabel();
        txtMuaVao = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDoanhThu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("THỐNG KÊ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(587, 587, 587)
                .addComponent(jLabel1)
                .addContainerGap(627, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 80));
        add(txtDateTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 202, 30));
        add(txtDateDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 202, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Từ");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Đến");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KHÁCH HÀNG", "BÁN RA", "MUA VÀO", "TỔNG THU", "TỔNG CHI", "DOANH THU"
            }
        ));
        jScrollPane1.setViewportView(tblDoanhThu);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 1270, 330));

        btnLoc.setBackground(new java.awt.Color(204, 204, 204));
        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.setText("LỌC");
        add(btnLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, -1, 30));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));
        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, -1, -1));

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023" }));
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });
        add(cboNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 250, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Năm");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 50, 30));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("KHÁCH HÀNG");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 280, 50));

        txtKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtKhachHang.setText("0");
        add(txtKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 280, 50));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("BÁN RA");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 280, 50));

        txtBanRa.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtBanRa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBanRa.setText("0");
        add(txtBanRa, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 280, 50));

        txtMuaVao.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtMuaVao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMuaVao.setText("0");
        add(txtMuaVao, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 280, 50));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("MUA VÀO");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 280, 50));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("DOANH THU");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 230, 280, 50));

        txtDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThu.setText("0");
        add(txtDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 190, 280, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/2.png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 280, 160));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/5.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 150, 280, 160));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/1.png"))); // NOI18N
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 280, 160));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/4.png"))); // NOI18N
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 280, 160));
    }// </editor-fold>//GEN-END:initComponents

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        fillTableDoanhThu();
    }//GEN-LAST:event_cboNamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JLabel txtBanRa;
    private com.toedter.calendar.JDateChooser txtDateDen;
    private com.toedter.calendar.JDateChooser txtDateTu;
    private javax.swing.JLabel txtDoanhThu;
    private javax.swing.JLabel txtKhachHang;
    private javax.swing.JLabel txtMuaVao;
    // End of variables declaration//GEN-END:variables
}
