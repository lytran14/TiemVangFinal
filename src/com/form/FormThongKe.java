package com.form;

import Class_DAO.BanRa_DAO;
import Class_DAO.ThongKe_DAO;
import com.model.Excel;
import java.io.IOException;
import java.lang.System.Logger;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
            List<Object[]> list = dao.getDThu(nam);
            // Định dạng số
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            for (Object[] row : list) {
                // Định dạng các giá trị số trong mảng row
                for (int i = 0; i < row.length; i++) {
                    if (row[i] instanceof Number) {
                        row[i] = decimalFormat.format(row[i]);
                    }
                }
                model.addRow(row);
            }
        }
        fillDoanhThu();
    }
    void fillTableDoanhThuBanRa() {
        DefaultTableModel model = (DefaultTableModel) tblBanRa.getModel();
        model.setRowCount(0);

        Integer nam = (Integer) cboNam.getSelectedItem();
        if (nam != null) {
            List<Object[]> list = dao.getDThuBanRa(nam);
            // Định dạng số
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            for (Object[] row : list) {
                // Định dạng các giá trị số trong mảng row
                for (int i = 0; i < row.length; i++) {
                    if (row[i] instanceof Number) {
                        row[i] = decimalFormat.format(row[i]);
                    }
                }
                model.addRow(row);
            }
        }
    }

    void fillTableDoanhThuMuaVao() {
        DefaultTableModel model = (DefaultTableModel) tblMuaVao.getModel();
        model.setRowCount(0);

        Integer nam = (Integer) cboNam.getSelectedItem();
        if (nam != null) {
            List<Object[]> list = dao.getDThuMuaVao(nam);

            // Định dạng số
            DecimalFormat decimalFormat = new DecimalFormat("#,###");

            for (Object[] row : list) {
                // Định dạng các giá trị số trong mảng row
                for (int i = 0; i < row.length; i++) {
                    if (row[i] instanceof Number) {
                        row[i] = decimalFormat.format(row[i]);
                    }
                }

                model.addRow(row);
            }
        }
    }

    void fillTableDoanhThuCamDo() {
        DefaultTableModel model = (DefaultTableModel) tblCamDo.getModel();
        model.setRowCount(0);

        Integer nam = (Integer) cboNam.getSelectedItem();
        if (nam != null) {
            List<Object[]> list = dao.getDThuCamDo(nam);

            // Định dạng số
            DecimalFormat decimalFormat = new DecimalFormat("#,###");

            for (Object[] row : list) {
                // Định dạng các giá trị số trong mảng row
                for (int i = 0; i < row.length; i++) {
                    if (row[i] instanceof Number) {
                        row[i] = decimalFormat.format(row[i]);
                    }
                }

                model.addRow(row);
            }
        }
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

    void fillTableDoanhThuDay(Date startDate, Date endDate) {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        // Truy vấn dữ liệu thống kê từ ngày đến ngày
        List<Object[]> list = dao.getDThuFromDateToDate(startDate, endDate);

        for (Object[] row : list) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] instanceof Number) {
                    row[i] = decimalFormat.format(row[i]);
                }
            }
            model.addRow(row);
        }
        //fillDoanhThu();
    }

    void fillTableDoanhThuDayBanRa(Date startDate, Date endDate) {
        DefaultTableModel model = (DefaultTableModel) tblBanRa.getModel();
        model.setRowCount(0);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        List<Object[]> list = dao.getDThuFromDateToDateBanRa(startDate, endDate);
        for (Object[] row : list) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] instanceof Number) {
                    row[i] = decimalFormat.format(row[i]);
                }
            }
            model.addRow(row);
        }
    }

    void fillTableDoanhThuDayMuaVao(Date startDate, Date endDate) {
        DefaultTableModel model = (DefaultTableModel) tblMuaVao.getModel();
        model.setRowCount(0);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        // Truy vấn dữ liệu thống kê từ ngày đến ngày
        List<Object[]> list = dao.getDThuFromDateToDateMuaVao(startDate, endDate);

        for (Object[] row : list) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] instanceof Number) {
                    row[i] = decimalFormat.format(row[i]);
                }
            }
            model.addRow(row);
        }
        //fillDoanhThu();
    }

    void fillTableDoanhThuDayCamDo(Date startDate, Date endDate) {
        DefaultTableModel model = (DefaultTableModel) tblCamDo.getModel();
        model.setRowCount(0);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        // Truy vấn dữ liệu thống kê từ ngày đến ngày
        List<Object[]> list = dao.getDThuFromDateToDateCamDo(startDate, endDate);

        for (Object[] row : list) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] instanceof Number) {
                    row[i] = decimalFormat.format(row[i]);
                }
            }
            model.addRow(row);
        }
        //fillDoanhThu();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBanRa = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtDateTu = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtDateDen = new com.toedter.calendar.JDateChooser();
        btnLocBanRa = new javax.swing.JButton();
        btnXuatBanRa = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMuaVao = new javax.swing.JTable();
        btnLocMuaVao = new javax.swing.JButton();
        txtDateDen1 = new com.toedter.calendar.JDateChooser();
        txtDateTu1 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnXuatMuaVao = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCamDo = new javax.swing.JTable();
        btnLocCamDo = new javax.swing.JButton();
        txtDateDen2 = new com.toedter.calendar.JDateChooser();
        txtDateTu2 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnXuatCamDo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDateTu3 = new com.toedter.calendar.JDateChooser();
        txtDateDen3 = new com.toedter.calendar.JDateChooser();
        btnLocDoanhThu = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        btnDoanhThu = new javax.swing.JButton();

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblBanRa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NGÀY", "TÊN SẢN PHẨM", "SỐ LƯỢNG ", "TỔNG THU"
            }
        ));
        jScrollPane1.setViewportView(tblBanRa);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Từ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Đến");

        btnLocBanRa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLocBanRa.setText("LỌC");
        btnLocBanRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocBanRaActionPerformed(evt);
            }
        });

        btnXuatBanRa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatBanRa.setText("XUẤT FILE");
        btnXuatBanRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBanRaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8)
                                .addGap(212, 212, 212)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDateTu, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(txtDateDen, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnLocBanRa)))
                        .addGap(0, 746, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXuatBanRa, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDateTu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateDen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocBanRa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatBanRa)
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("BÁN RA", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblMuaVao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NGÀY", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "TỔNG  CHI"
            }
        ));
        jScrollPane2.setViewportView(tblMuaVao);

        btnLocMuaVao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLocMuaVao.setText("LỌC");
        btnLocMuaVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocMuaVaoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Đến");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Từ");

        btnXuatMuaVao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatMuaVao.setText("XUẤT FILE");
        btnXuatMuaVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatMuaVaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel12)
                                .addGap(212, 212, 212)
                                .addComponent(jLabel11))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtDateTu1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(txtDateDen1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnLocMuaVao)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 1202, Short.MAX_VALUE)
                .addComponent(btnXuatMuaVao, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDateTu1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateDen1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocMuaVao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatMuaVao)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MUA VÀO", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblCamDo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NGÀY", "TÊN SẢN PHẨM", "SỐ LƯỢNG ", "TỔNG CHI"
            }
        ));
        jScrollPane3.setViewportView(tblCamDo);

        btnLocCamDo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLocCamDo.setText("LỌC");
        btnLocCamDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocCamDoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Đến");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Từ");

        btnXuatCamDo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatCamDo.setText("XUẤT FILE");
        btnXuatCamDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatCamDoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel18)
                                .addGap(212, 212, 212)
                                .addComponent(jLabel15))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtDateTu2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(txtDateDen2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnLocCamDo)))
                        .addGap(0, 746, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXuatCamDo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel15))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDateTu2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateDen2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocCamDo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatCamDo)
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("CẦM ĐỒ", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Từ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Đến");

        btnLocDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLocDoanhThu.setText("LỌC");
        btnLocDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocDoanhThuActionPerformed(evt);
            }
        });

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KHÁCH HÀNG", "BÁN RA", "MUA VÀO", "TỔNG THU", "TỔNG CHI", "DOANH THU "
            }
        ));
        jScrollPane4.setViewportView(tblDoanhThu);

        btnDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDoanhThu.setText("XUẤT FILE");
        btnDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel19)
                                .addGap(212, 212, 212)
                                .addComponent(jLabel20))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtDateTu3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(txtDateDen3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnLocDoanhThu)))
                        .addGap(0, 746, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDateTu3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateDen3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(btnDoanhThu)
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("DOANH THU", jPanel5);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 1310, 440));
    }// </editor-fold>//GEN-END:initComponents

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed

        fillTableDoanhThuBanRa();
        fillTableDoanhThuMuaVao();
        fillTableDoanhThuCamDo();
        fillTableDoanhThu();

    }//GEN-LAST:event_cboNamActionPerformed

    private void btnLocBanRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocBanRaActionPerformed

        Date startDate = txtDateTu.getDate();
        Date endDate = txtDateDen.getDate();

        // Kiểm tra xem ngày bắt đầu và ngày kết thúc đã được chọn
        if (startDate != null && endDate != null) {
            fillTableDoanhThuDayBanRa(startDate, endDate);
        } else {
            // Hiển thị thông báo cho người dùng nếu ngày bắt đầu hoặc ngày kết thúc chưa được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày "
                    + "bắt đầu và ngày kết thúc.");
        }

    }//GEN-LAST:event_btnLocBanRaActionPerformed

    private void btnLocMuaVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocMuaVaoActionPerformed

        Date startDate = txtDateTu1.getDate();
        Date endDate = txtDateDen1.getDate();

        // Kiểm tra xem ngày bắt đầu và ngày kết thúc đã được chọn
        if (startDate != null && endDate != null) {
            fillTableDoanhThuDayMuaVao(startDate, endDate);
        } else {
            // Hiển thị thông báo cho người dùng nếu ngày bắt đầu hoặc ngày kết thúc chưa được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày "
                    + "bắt đầu và ngày kết thúc.");
        }

    }//GEN-LAST:event_btnLocMuaVaoActionPerformed

    private void btnLocCamDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocCamDoActionPerformed

        Date startDate = txtDateTu2.getDate();
        Date endDate = txtDateDen2.getDate();

        // Kiểm tra xem ngày bắt đầu và ngày kết thúc đã được chọn
        if (startDate != null && endDate != null) {
            fillTableDoanhThuDayCamDo(startDate, endDate);
        } else {
            // Hiển thị thông báo cho người dùng nếu ngày bắt đầu hoặc ngày kết thúc chưa được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày "
                    + "bắt đầu và ngày kết thúc.");
        }

    }//GEN-LAST:event_btnLocCamDoActionPerformed

    private void btnLocDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocDoanhThuActionPerformed

        Date startDate = txtDateTu3.getDate();
        Date endDate = txtDateDen3.getDate();

        // Kiểm tra xem ngày bắt đầu và ngày kết thúc đã được chọn
        if (startDate != null && endDate != null) {
            fillTableDoanhThuDay(startDate, endDate);
        } else {
            // Hiển thị thông báo cho người dùng nếu ngày bắt đầu hoặc ngày kết thúc chưa được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày "
                    + "bắt đầu và ngày kết thúc.");
        }

    }//GEN-LAST:event_btnLocDoanhThuActionPerformed

    private void btnDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoanhThuActionPerformed
        
        try {
            excelProducts();
        }catch (IOException ex){
      //      Logger.getLogger(FormThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDoanhThuActionPerformed

    private void btnXuatBanRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBanRaActionPerformed
       
        try {
            excelProductsBanRa();
        }catch (IOException ex){
      //      Logger.getLogger(FormThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnXuatBanRaActionPerformed

    private void btnXuatMuaVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatMuaVaoActionPerformed
       
        try {
            excelProductsMuaVao();
        }catch (IOException ex){
      //      Logger.getLogger(FormThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnXuatMuaVaoActionPerformed

    private void btnXuatCamDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatCamDoActionPerformed
       
        try {
            excelProductsCamDo();
        }catch (IOException ex){
      //      Logger.getLogger(FormThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnXuatCamDoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoanhThu;
    private javax.swing.JButton btnLocBanRa;
    private javax.swing.JButton btnLocCamDo;
    private javax.swing.JButton btnLocDoanhThu;
    private javax.swing.JButton btnLocMuaVao;
    private javax.swing.JButton btnXuatBanRa;
    private javax.swing.JButton btnXuatCamDo;
    private javax.swing.JButton btnXuatMuaVao;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblBanRa;
    private javax.swing.JTable tblCamDo;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblMuaVao;
    private javax.swing.JLabel txtBanRa;
    private com.toedter.calendar.JDateChooser txtDateDen;
    private com.toedter.calendar.JDateChooser txtDateDen1;
    private com.toedter.calendar.JDateChooser txtDateDen2;
    private com.toedter.calendar.JDateChooser txtDateDen3;
    private com.toedter.calendar.JDateChooser txtDateTu;
    private com.toedter.calendar.JDateChooser txtDateTu1;
    private com.toedter.calendar.JDateChooser txtDateTu2;
    private com.toedter.calendar.JDateChooser txtDateTu3;
    private javax.swing.JLabel txtDoanhThu;
    private javax.swing.JLabel txtKhachHang;
    private javax.swing.JLabel txtMuaVao;
    // End of variables declaration//GEN-END:variables


    public void excelProducts() throws IOException {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        Excel.outExcel(model);     
    }
    
    public void excelProductsBanRa() throws IOException {
        DefaultTableModel model = (DefaultTableModel) tblBanRa.getModel();
        Excel.outExcel(model);     
    }
    
    public void excelProductsMuaVao() throws IOException {
        DefaultTableModel model = (DefaultTableModel) tblMuaVao.getModel();
        Excel.outExcel(model);     
    }
    
    public void excelProductsCamDo() throws IOException {
        DefaultTableModel model = (DefaultTableModel) tblCamDo.getModel();
        Excel.outExcel(model);     
    }
    
   
}
