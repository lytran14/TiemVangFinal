package com.form;

import Class_DAO.NhanVien_DAO;
import Class_Model.NhanVien_Model;
import Class_Utils.Auth;
import Class_Utils.MsgBox;
import static com.model.AutoString.autoEmail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static com.model.AutoString.generateAutoID;

public class FormNhanVien extends javax.swing.JPanel {

    NhanVien_DAO dao = new NhanVien_DAO();

    public FormNhanVien() {
        initComponents();
        init();
    }

    private void init() {
        this.fillToTable();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMaNV.setEnabled(false);
        txtEmail.setEnabled(false);
    }

    private void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien_Model> list = dao.selectAll();
            for (NhanVien_Model nv : list) {
                Object[] row = {
                    nv.getMANV(),
                    nv.getTENNV(),
                    nv.getNGAYSINH(),
                    nv.getDIACHI(),
                    nv.getEMAIL(),
                    nv.getSODT(),
                    nv.getGIOITINH() ? "Nam" : "Nữ",
                    nv.getVITRICONGVIEC() ? "Quản Lý" : "Nhân Viên"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU");
        }
    }

    public void fillToForm(int index) {
        List<NhanVien_Model> list = dao.selectAll();
        NhanVien_Model nv = list.get(index);
        txtTen.setText(nv.getTENNV());
        txtMaNV.setText(nv.getMANV());
        txtDiaChi.setText(nv.getDIACHI());
        txtEmail.setText(nv.getEMAIL());
        txtMatKhau.setText("***********");
        txtMatKhau.setEnabled(false);
        txtNgaySinh.setDate(nv.getNGAYSINH());
        txtSDT.setText(nv.getSODT());
        if (nv.isGIOITINH()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        if (nv.isVITRICONGVIEC()) {
            rdiQLy.setSelected(true);
        } else {
            rdoNVien.setSelected(true);
        }
        tblNhanVien.setRowSelectionInterval(index, index);
    }

    void fillNV() {
        String tenNV = txtTen.getText();
        String maNV = generateAutoID(tenNV);
        txtMaNV.setText(maNV);
        String email = autoEmail(tenNV); // Gọi phương thức autoEmail để tạo địa chỉ email
        txtEmail.setText(email);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioiTinh = new javax.swing.ButtonGroup();
        btgVaiTro = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        txtSDT = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        rdiQLy = new javax.swing.JRadioButton();
        rdoNVien = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(561, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(528, 528, 528))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 80));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel3.setText("Mã Nhân Viên");

        txtMaNV.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel4.setText("Tên Nhân Viên");

        txtTen.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel5.setText("Mật Khẩu");

        jLabel6.setText("Ngày Sinh");

        txtMatKhau.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel7.setText("Địa Chỉ");

        txtDiaChi.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel8.setText("Email");

        jLabel9.setText("Giới Tính");

        jLabel10.setText("Chức Vụ");

        txtEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel12.setText("Số Điện Thoại");

        txtSDT.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        btgGioiTinh.add(rdoNam);
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        btgGioiTinh.add(rdoNu);
        rdoNu.setText("Nữ");

        rdiQLy.setBackground(new java.awt.Color(255, 255, 255));
        btgVaiTro.add(rdiQLy);
        rdiQLy.setText("Quản Lý");

        rdoNVien.setBackground(new java.awt.Color(255, 255, 255));
        btgVaiTro.add(rdoNVien);
        rdoNVien.setText("Nhân Viên");

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("XOÁ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("LÀM MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 84, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNam)
                            .addComponent(rdiQLy))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoNVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoNu)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLamMoi)
                                .addGap(240, 240, 240))
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNu)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNam)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNVien)
                            .addComponent(rdiQLy)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 1250, -1));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "Ngày Sinh", "Địa Chỉ", "Email", "Số ĐT", "Giới Tính", "Chức Vụ"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 457, 1250, 290));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setOpaque(true);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1340, 710));
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!validates()) {
            return;
        } else if (check()) {
            insert();
            return;
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        reset();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThem.setEnabled(true);
        //txtMaNV.setEnabled(true);
        txtMatKhau.setEnabled(true);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int index = tblNhanVien.getSelectedRow();
        fillToForm(index);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        txtMaNV.setEnabled(false);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (!validates()) {
            return;
        } else {
            update();
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        fillNV();
    }//GEN-LAST:event_txtTenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinh;
    private javax.swing.ButtonGroup btgVaiTro;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdiQLy;
    private javax.swing.JRadioButton rdoNVien;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    private NhanVien_Model getForm() {
        NhanVien_Model model = new NhanVien_Model();
//        String maNV = generateAutoID(txtTen.getText());
        model.setMANV(txtMaNV.getText());
        model.setTENNV(txtTen.getText());
        model.setDIACHI(txtDiaChi.getText());
        //       String email = autoEmail(txtTen.getText()); // Gọi phương thức autoEmail để tạo địa chỉ email
        model.setEMAIL(txtEmail.getText());
        model.setMATKHAU(new String(txtMatKhau.getPassword()));
        model.setNGAYSINH(txtNgaySinh.getDate());
        model.setSODT(txtSDT.getText());
        model.setVITRICONGVIEC(rdiQLy.isSelected());
        model.setGIOITINH(rdoNam.isSelected());
        return model;
    }

    public void reset() {
        txtMaNV.setText("");
        txtTen.setText("");
        txtNgaySinh.setDate(null);
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtMatKhau.setText("");
        btgGioiTinh.clearSelection();
        btgVaiTro.clearSelection();
        btnThem.setEnabled(true);
    }

    private void insert() {
        NhanVien_Model model = getForm();
        String confirm = new String(txtMatKhau.getPassword());
        if (confirm.equals(model.getMATKHAU())) {
            try {
                dao.insert(model);
                this.fillToTable();
                this.reset();
                MsgBox.alert(this, "THÊM THÀNH CÔNG!");
            } catch (Exception e) {
                MsgBox.alert(this, "THÊM THẤT BẠI!");
                System.out.println(e);
            }
        } else {
            MsgBox.alert(this, "XÁC NHẬN MẬT KHẨU SAI!!!");
        }
    }

    private void update() {
        NhanVien_Model model = getForm();
        try {
            dao.update(model);
            this.fillToTable();
            this.reset();
            MsgBox.alert(this, "CẬP NHẬT THÀNH CÔNG!");
        } catch (Exception e) {
            MsgBox.alert(this, "CẬP NHẬT THẤT BẠI");
        }
    }

    private void delete() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "BẠN KHÔNG CÓ QUYỀN XOÁ NHÂN VIÊN");
        } else {
            if (MsgBox.confirm(this, "BẠN MUỐN XOÁ NHÂN VIÊN NÀY?")) {
                String maNV = txtMaNV.getText();
                try {
                    dao.delete(maNV);
                    this.fillToTable();
                    this.reset();
                    MsgBox.alert(this, "XOÁ THÀNH CÔNG");
                } catch (Exception e) {
                    MsgBox.alert(this, "XOÁ THẤT BẠI.");
                }
            }
        }
    }
//BẮT LỖI

    boolean validates() {

        if (txtTen.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "TÊN NHÂN VIÊN KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtTen.requestFocus();
            return false;
        }
        if (txtMatKhau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "MẬT KHẨU KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtMatKhau.requestFocus();
            return false;
        }// Kiểm tra mật khẩu theo biểu thức chính quy
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-=_+\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";
        if (!txtMatKhau.getText().matches(regex)) {
            String message = "Mật khẩu không hợp lệ.\n\n";
            message += "Yêu cầu:\n";
            message += "- Ít nhất 8 ký tự.\n";
            message += "- Chứa ít nhất một chữ IN HOA.\n";
            message += "- Chứa ít nhất một số.\n";
            message += "- Chứa ít nhất một ký tự đặc biệt.";
            JOptionPane.showMessageDialog(this, message, "Chú ý!", JOptionPane.WARNING_MESSAGE);
            txtMatKhau.requestFocus();
            return false;
        }
        if (txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "NGÀY SINH KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtNgaySinh.requestFocus();
            return false;
        }
        if (txtDiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "ĐỊA CHỈ KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtMatKhau.requestFocus();
            return false;
        }
        if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SỐ ĐIỆN THOẠI KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtMatKhau.requestFocus();
            return false;
        } else if (!txtSDT.getText().matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "SỐ ĐIỆN THOẠI PHẢI LÀ SỐ! ", "CHÚ Ý!!!", 1);
            txtSDT.requestFocus();
            return false;
        } else if (txtSDT.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "SỐ ĐIỆN THOẠI PHẢI ĐỦ 10 SỐ!", "CHÚ Ý!!!", JOptionPane.WARNING_MESSAGE);
            txtSDT.requestFocus();
            return false;
        }
        boolean isRoleSelectedgt = false;
        boolean isRoleSelectedVt = false;
        if (rdoNam.isSelected()) {
            isRoleSelectedgt = true;
        } else if (rdoNu.isSelected()) {
            isRoleSelectedgt = true;
        }
        if (!isRoleSelectedgt) {
            JOptionPane.showMessageDialog(this, "GIỚI TÍNH KHÔNG ĐƯỢC TRỐNG!", "CHÚ Ý!!!", 1);
            rdoNam.requestFocus(); // hoặc rdoTruongPhong.requestFocus();
            return false;
        }
        if (rdoNVien.isSelected()) {
            isRoleSelectedVt = true;
        } else if (rdiQLy.isSelected()) {
            isRoleSelectedVt = true;
        }
        if (!isRoleSelectedVt) {
            JOptionPane.showMessageDialog(this, "VAI TRÒ KHÔNG ĐƯỢC TRỐNG!", "CHÚ Ý!!!", 1);
            rdoNVien.requestFocus(); // hoặc rdoTruongPhong.requestFocus();
            return false;
        }

        return true;
    }

    boolean check() {
        String pName = txtMaNV.getText().trim();
// Thực hiện truy vấn để kiểm tra mã khách hàng
        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE MANV = ?";
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pName);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(this, "MÃ NHÂN VIÊN ĐÃ TỒN TẠI!");
                txtMaNV.grabFocus();
                return false;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
