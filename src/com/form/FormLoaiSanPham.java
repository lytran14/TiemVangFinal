/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.form;

import Class_DAO.LoaiSanPham_DAO;
import Class_Model.LoaiSanPham_Model;
import Class_Utils.Auth;
import Class_Utils.MsgBox;
import static com.model.AutoString.autoID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class FormLoaiSanPham extends javax.swing.JFrame {

    LoaiSanPham_DAO dao = new LoaiSanPham_DAO();
    
    public FormLoaiSanPham() {
        initComponents();
        init();
        txtMaLSP.setEnabled(false);
        
    }

    private void init() {
        this.setLocationRelativeTo(null);
        this.fillToTable();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    private void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblLoaiSP.getModel();
        model.setRowCount(0);
        try {
            List<LoaiSanPham_Model> list = dao.selectAll();
            for (LoaiSanPham_Model lsp : list) {
                Object[] row = {
                    lsp.getMaLSP(),
                    lsp.getTenLSP(),
                    lsp.getMoTa()};
                
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU");
        }
    }

    public void fillToForm(int index) {
        List<LoaiSanPham_Model> list = dao.selectAll();
        LoaiSanPham_Model lsp = list.get(index);
        
        txtMaLSP.setText(lsp.getMaLSP());
        txtTenLSP.setText(lsp.getTenLSP());
        txtMoTa.setText(lsp.getMoTa());
        
        tblLoaiSP.setRowSelectionInterval(index, index);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTenLSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaLSP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoaiSP = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("LOẠI SẢN PHẨM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(556, 556, 556)
                .addComponent(jLabel1)
                .addContainerGap(575, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1325, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN LOẠI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("MÃ LOẠI");

        jLabel8.setText("TÊN LOẠI");

        jLabel13.setText("MÔ TẢ");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("SỬA ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("XÓA");
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
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnLamMoi))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenLSP)
                                    .addComponent(txtMaLSP))))
                        .addGap(744, 744, 744))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 110, 1220, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblLoaiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ LOẠI", "TÊN LOẠI", "MÔ TẢ"
            }
        ));
        tblLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLoaiSP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 420, 1220, 320));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1330, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        
        delete();
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
       
        if (!validates()) {
            return;
        } else if (check()) {
            insert();
            return;
        }
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        
        if (!validates()) {
            return;
        } else {
            update();
            return;
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        
        reset();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThem.setEnabled(true);
        
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSPMouseClicked
        
        int index = tblLoaiSP.getSelectedRow();
        fillToForm(index);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
//        txtMaLSP.setEditable(false);
        
    }//GEN-LAST:event_tblLoaiSPMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormLoaiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLoaiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLoaiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLoaiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLoaiSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblLoaiSP;
    private javax.swing.JTextField txtMaLSP;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenLSP;
    // End of variables declaration//GEN-END:variables

    
    private LoaiSanPham_Model getForm() {
        LoaiSanPham_Model model = new LoaiSanPham_Model();
        String maLSP = autoID("ML", "MALOAISP", "LOAISANPHAM"); // Gọi phương thức autoID để tạo mã KH mới
        model.setMaLSP(maLSP);
//        model.setMaLSP(txtMaLSP.getText());
        model.setTenLSP(txtTenLSP.getText());
        model.setMoTa(txtMoTa.getText());
       
        return model;
    }
    
    private LoaiSanPham_Model getFormUpate() {
        LoaiSanPham_Model model = new LoaiSanPham_Model();
        model.setMaLSP(txtMaLSP.getText());
        model.setTenLSP(txtTenLSP.getText());
        model.setMoTa(txtMoTa.getText());
       
        return model;
    }

    public void reset() {
        txtMaLSP.setText("");
        txtTenLSP.setText("");
        txtMoTa.setText("");
        
    }

    void insert() {
        LoaiSanPham_Model lsp = getForm();
        try {
            dao.insert(lsp);
            this.fillToTable();
            this.reset();
            MsgBox.alert(this, "THÊM THÀNH CÔNG!");
        } catch (Exception e) {
            MsgBox.alert(this, "THÊM THẤT BẠI!");
            System.out.println(e);
        }
    }

    private void update() {
        LoaiSanPham_Model model = getFormUpate();
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
            MsgBox.alert(this, "BẠN KHÔNG CÓ QUYỀN XOÁ LOẠI SẢN PHẢM");
        } else {
            if (MsgBox.confirm(this, "BẠN MUỐN XOÁ LOẠI SẢN PHẨM NÀY?")) {
                String maNV = txtMaLSP.getText();
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
        
        if (txtTenLSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "TÊN LOẠI SẢN PHẨM KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtTenLSP.requestFocus();
            return false;
        }
//        if (txtMoTa.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "MÔ TẢ KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
//            txtMoTa.requestFocus();
//            return false;
//        }
        
        return true;
    }

    boolean check() {
        String pName = txtMaLSP.getText().trim();
// Thực hiện truy vấn để kiểm tra mã khách hàng
        String sql = "SELECT COUNT(*) FROM LOAISANPHAM WHERE MALOAISP = ?";
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pName);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(this, "MÃ LOẠI SẢN PHẨM ĐÃ TỒN TẠI!");
                txtMaLSP.grabFocus();
                return false;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
