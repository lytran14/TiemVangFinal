package com.form;

import Class_DAO.DonViTinh_DAO;
import Class_DAO.LoaiSanPham_DAO;
import Class_DAO.SanPham_DAO;
import Class_Model.DonViTinh_Model;
import Class_Model.LoaiSanPham_Model;
import Class_Model.SanPham_Model;
import Class_Utils.MsgBox;
import Class_Utils.XImage;
import static com.model.AutoString.autoID;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormSanPham extends javax.swing.JFrame {

    JFileChooser filechooser = new JFileChooser();
    SanPham_DAO dao = new SanPham_DAO();
    LoaiSanPham_DAO loaidao = new LoaiSanPham_DAO();
    DonViTinh_DAO dvtdao = new DonViTinh_DAO();
    private String hinhAnh = null;

    public FormSanPham() {
        initComponents();
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        this.fillToTable();
        this.fillComboBoxLoai();
        this.fillComboBoxDVT();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtMaSP.setEnabled(false);
        String maSP = autoID("SP", "maSP", "SANPHAM"); // Gọi phương thức autoID để tạo mã mới
        txtMaSP.setText(maSP);
    }

    private void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham_Model> list = dao.selectAll();
            for (SanPham_Model sp : list) {
                Object[] row = {
                    sp.getMASP(),
                    sp.getTENSP(),
                    sp.getLOAI(),
                    sp.getDONGIANHAP(),
                    sp.getDONGIABAN(),
                    sp.getDONVITINH()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU");
        }
    }

    public void fillToForm(int index) {
        List<SanPham_Model> list = dao.selectAll();
        if (index >= 0 && index < list.size()) {
            SanPham_Model sp = list.get(index);
            txtMaSP.setText(sp.getMASP());
            txtTenSP.setText(sp.getTENSP());
            // Tìm kiếm phần tử tương ứng và chọn trong combo box cboLoaiVang
            for (int i = 0; i < cboLoaiVang.getItemCount(); i++) {
                Object item = cboLoaiVang.getItemAt(i);
                if (item instanceof LoaiSanPham_Model) {
                    LoaiSanPham_Model loai = (LoaiSanPham_Model) item;
                    if (loai.getTenLSP().equals(sp.getLOAI())) {
                        cboLoaiVang.setSelectedItem(item);
                        break;
                    }
                }
            }
            // Tìm kiếm phần tử tương ứng và chọn trong combo box cboDVT
            for (int i = 0; i < cboDVT.getItemCount(); i++) {
                Object item = cboDVT.getItemAt(i);
                if (item instanceof DonViTinh_Model) {
                    DonViTinh_Model dv = (DonViTinh_Model) item;
                    if (dv.getTenDVT().equals(sp.getDONVITINH())) {
                        cboDVT.setSelectedItem(item);
                        break;
                    }
                }
            }
            String Anh = sp.getHINH();
            if (Anh == null || Anh.equalsIgnoreCase("NULL")) {
                lblHinh.setText("NO AVATAR");
                lblHinh.setIcon(null);
            } else {
                lblHinh.setText("");
                lblHinh.setToolTipText(Anh);
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img_pduc/" + Anh));
                Image image = imageIcon.getImage();
                lblHinh.setIcon(new ImageIcon(image.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), 0)));
            }
            txtGiaNhap.setText(String.valueOf(sp.getDONGIANHAP()));
            txtGiaBan.setText(String.valueOf(sp.getDONGIABAN()));
            tblSanPham.setRowSelectionInterval(index, index);
        }
    }

    void fillComboBoxLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiVang.getModel();
        model.removeAllElements();
        try {
            List<LoaiSanPham_Model> list = loaidao.selectAll();
            for (LoaiSanPham_Model loaisp : list) {
                model.addElement(loaisp);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void fillComboBoxDVT() {
        DefaultComboBoxModel modell = (DefaultComboBoxModel) cboDVT.getModel();
        modell.removeAllElements();
        try {
            List<DonViTinh_Model> list = dvtdao.selectAll();
            for (DonViTinh_Model dvt : list) {
                modell.addElement(dvt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboLoaiVang = new javax.swing.JComboBox<>();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMota = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        cboDVT = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("SẢN PHẨM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
        );

        jLabel3.setText("MÃ SP");

        jLabel8.setText("TÊN SP");

        jLabel9.setText("LOẠI VÀNG");

        cboLoaiVang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("GIÁ NHẬP");

        jLabel11.setText("GIÁ BÁN");

        jLabel12.setText("ĐƠN VỊ");

        jLabel13.setText("MÔ TẢ");

        txtMota.setColumns(20);
        txtMota.setRows(5);
        jScrollPane2.setViewportView(txtMota);

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

        cboDVT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboLoaiVang, 0, 689, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaSP))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTenSP))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGiaNhap))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGiaBan))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboDVT, 0, 689, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addGap(50, 50, 50))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLoaiVang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cboDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnLamMoi))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ SP", "TÊN SP", "LOẠI VÀNG", "ĐƠN GIÁ NHẬP", "ĐƠN GIÁ BÁN", "ĐƠN VỊ TÍNH"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();
        fillToForm(index);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        txtMaSP.setEnabled(false);
    }//GEN-LAST:event_tblSanPhamMouseClicked

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
        init();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThem.setEnabled(true);
        //txtMaSP.setEnabled(true);
    }//GEN-LAST:event_btnLamMoiActionPerformed

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

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        lblHinh.setText("");
        chonAnh();
    }//GEN-LAST:event_lblHinhMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboDVT;
    private javax.swing.JComboBox<String> cboLoaiVang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMota;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables

    private SanPham_Model getForm() {
        SanPham_Model model = new SanPham_Model();
        model.setMASP(txtMaSP.getText());
        model.setTENSP(txtTenSP.getText()); // Sửa thành txtTenSP.getText()
        LoaiSanPham_Model selectedLoai = (LoaiSanPham_Model) cboLoaiVang.getSelectedItem();
        model.setMALOAISP(selectedLoai.getMaLSP());
        model.setLOAI(selectedLoai.getTenLSP());
        model.setDONGIANHAP(Double.parseDouble(txtGiaNhap.getText()));
        model.setDONGIABAN(Double.parseDouble(txtGiaBan.getText()));
        DonViTinh_Model selectedDV = (DonViTinh_Model) cboDVT.getSelectedItem();
        model.setMADVTINH(selectedDV.getMaDVT()); // Sửa thành selectedDV.getMaDVT()
        model.setDONVITINH(selectedDV.getTenDVT());
        model.setMOTA(txtMota.getText());
        model.setHINH(lblHinh.getToolTipText());
        if (model.getHINH() == null) {
            model.setHINH("NULL");
        }
        return model;
    }

    public void reset() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtMota.setText("");
        btnThem.setEnabled(true);
        lblHinh.setIcon(null); // Đặt lại biểu tượng hình ảnh thành null
        lblHinh.setText("");
    }

    private void insert() {
        SanPham_Model model = getForm();
        try {
            dao.insert(model);
            this.fillToTable();
            this.reset();
            MsgBox.alert(this, "THÊM THÀNH CÔNG!");
        } catch (Exception e) {
            MsgBox.alert(this, "THÊM THẤT BẠI!");
            e.printStackTrace();
        }
    }

    private void update() {
        SanPham_Model model = getForm();
        try {
            dao.update(model);
            this.fillToTable();
            this.reset();
            MsgBox.alert(this, "CẬP NHẬT THÀNH CÔNG!");
        } catch (Exception e) {
            MsgBox.alert(this, "CẬP NHẬT THẤT BẠI");
            e.printStackTrace();
        }
    }

    private void delete() {
        if (MsgBox.confirm(this, "BẠN MUỐN XOÁ SẢN PHẨM NÀY?")) {
            String maNV = txtMaSP.getText();
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

    private void chonAnh() {
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            XImage.save(file); // lưu hình và thư mục logos
            ImageIcon icon = XImage.read(file.getName());
            Image image = icon.getImage(); //trả về hình cho icon
            Image newImg = image.getScaledInstance(190, 152, java.awt.Image.SCALE_SMOOTH); //TẠO MỘT PHIÊN BẢN THU NHỎ, CÓ THỂ ĐIỀU CHỈNH KÍCH THƯỚC
            icon = new ImageIcon(newImg);
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName());
        }
    }

    boolean validates() {
        if (txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "TÊN SẢN PHẨM KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtTenSP.requestFocus();
            return false;
        }
        String giaNhapText = txtGiaNhap.getText();
        if (giaNhapText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "GIÁ NHẬP KHÔNG ĐƯỢC TRỐNG!", "CHÚ Ý!!!", JOptionPane.WARNING_MESSAGE);
            txtGiaNhap.requestFocus();
            return false;
        }

        try {
            double giaNhap = Double.parseDouble(giaNhapText);
            if (giaNhap < 0) {
                JOptionPane.showMessageDialog(this, "GIÁ NHẬP PHẢI LỚN HƠN HOẶC BẰNG 0!", "CHÚ Ý!!!", JOptionPane.WARNING_MESSAGE);
                txtGiaNhap.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "GIÁ NHẬP PHẢI LÀ SỐ!", "CHÚ Ý!!!", JOptionPane.WARNING_MESSAGE);
            txtGiaNhap.requestFocus();
            return false;
        }

        String giaBanText = txtGiaBan.getText();
        if (giaBanText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "GIÁ BÁN KHÔNG ĐƯỢC TRỐNG!", "CHÚ Ý!!!", JOptionPane.WARNING_MESSAGE);
            txtGiaBan.requestFocus();
            return false;
        }

        try {
            double giaBan = Double.parseDouble(giaBanText);
            if (giaBan < 0) {
                JOptionPane.showMessageDialog(this, "GIÁ BÁN PHẢI LỚN HƠN HOẶC BẰNG 0!", "CHÚ Ý!!!", JOptionPane.WARNING_MESSAGE);
                txtGiaBan.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "GIÁ BÁN PHẢI LÀ SỐ!", "CHÚ Ý!!!", JOptionPane.WARNING_MESSAGE);
            txtGiaBan.requestFocus();
            return false;
        }
        Icon hinhAnh = lblHinh.getIcon();
        if (hinhAnh == null) {
            JOptionPane.showMessageDialog(this, "HÌNH ẢNH KHÔNG ĐƯỢC TRỐNG!", "CHÚ Ý!!!", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    boolean check() {
        String pName = txtTenSP.getText().trim();
// Thực hiện truy vấn để kiểm tra
        String sql = "SELECT COUNT(*) FROM SANPHAM WHERE TENSP = ?";
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pName);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(this, "TÊN SẢN PHẨM ĐÃ TỒN TẠI!");
                txtTenSP.grabFocus();
                return false;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
