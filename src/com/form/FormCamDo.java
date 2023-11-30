
package com.form;

import Class_DAO.CamDo_DAO;
import Class_DAO.ChiTietPhieuCam_DAO;
import Class_DAO.KhachHang_DAO;
import Class_DAO.SanPham_DAO;
import Class_Model.CamDo_Model;
import Class_Model.ChiTietPhieuCam_Model;
import Class_Model.KhachHang_Model;
import Class_Model.SanPham_Model;
import Class_Utils.Auth;
import Class_Utils.MsgBox;
import Class_Utils.XDate;
import static com.model.AutoString.autoID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FormCamDo extends javax.swing.JFrame {
//@didi
    CamDo_DAO dao = new CamDo_DAO();
    KhachHang_DAO khdao = new KhachHang_DAO();
    SanPham_DAO spdao = new SanPham_DAO();
    ChiTietPhieuCam_DAO ctpcdao = new ChiTietPhieuCam_DAO();


    public FormCamDo() {
        initComponents();
        init();
    }
    
    void init() {
        CamDo_Model cd = new CamDo_Model();
        //this.setLocationRelativeTo(null);
        this.fillTable();
        txtNgayLap.setEnabled(false);
        txtMaPhieu.setEditable(false);
        txtMaNV.setEditable(false);
        String maCAM = autoID("MC", "MACAM", "CAMDO"); 
        cd.setMaCam(maCAM);
        txtMaPhieu.setText(maCAM);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowFormatted = formatter.format(now);
        if (txtNgayLap.getDate() == null) {
            txtNgayLap.setDate(now);
            cd.setNgayLap(XDate.toDate(nowFormatted, "yyyy-MM-dd"));
        } else {
            cd.setNgayLap(txtNgayLap.getDate());
        }     
    }
    

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHangCD.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKH.getText();
            List<CamDo_Model> list = dao.selectByKyword(keyword);
            for (CamDo_Model cd : list) {
                Object[] row = {
                    cd.getMaCam(),
                    cd.getNgayLap(),
                    String.format("%.0f",cd.getSoTienCam()),
                    cd.getNgayHetHan(),
                    cd.getSoNgay(),
                    cd.isTrangThai()? "Còn hạn" : "Hết hạn",
                    cd.getMaKH()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
        }
    }
    
    
    public void fillToFormCD(int index) {
        List<CamDo_Model> list = dao.selectAll();
        if (index >= 0 && index < list.size()) {
            CamDo_Model cd = list.get(index);
            txtMaPhieu.setText(cd.getMaCam());
            txtNgayLap.setDate(cd.getNgayLap());
            txtSoTienCam.setText(String.format("%.0f", cd.getSoTienCam()));
            txtMaKH.setText(cd.getMaKH());
            txtMaNV.setText(cd.getMaNV());
            txtSoNgayCam.setText(String.valueOf(cd.getSoNgay()));
            txtNgayBatDau.setDate(cd.getNgayCam());
            txtNgayHetHan.setDate(cd.getNgayHetHan());
            
//            String trongLuongText = txtTrongLuong.getText();
//            double trongLuong = Double.parseDouble(trongLuongText);
//            double donGia = Double.parseDouble(txtDonGia.getText());
//            double GiaTri = trongLuong * donGia;
//            txtGiaTri.setText(String.format("%.0f", GiaTri));
            
            tblKhachHangCD.setRowSelectionInterval(index, index);
           
        } else {
            // Xử lý lỗi khi chỉ số hàng không hợp lệ
            System.err.println("Invalid row index: " + index);
        }
    }
    
    public void fillToFormCTCD(int index1) {
        
        List<ChiTietPhieuCam_Model> list = ctpcdao.selectAll();
  //      if (index1 >= 0 && index1 < list.size()) {
            ChiTietPhieuCam_Model ctpc = list.get(index1);
            txtMaSP.setText(ctpc.getMaSP());
            txtDonGia.setText(String.format("%.0f", ctpc.getDonGia()));
            txtTrongLuong.setText(String.valueOf(ctpc.getKhoiLuong()));
            txtLaiXuat.setText(String.format("%.0f", ctpc.getLaiXuat()));
            
            tblKhachHangCD.setRowSelectionInterval(index1, index1);
    //    }
            
    }
    
    
//    public void fillToFormKH(int index2) {
//
//          List<KhachHang_Model> list = khdao.selectAll();
//       if (index2 >= 0 && index2 < list.size()) {
//            KhachHang_Model kh = list.get(index2);
//            txtTenKH.setText(kh.getTenKH());
//            txtCCCD.setText(kh.getSoCCCD());
//            txtSoDT.setText(kh.getSoDTKH());
//            txtDiaChi.setText(kh.getDiaChi());
//            
//            tblKhachHangCD.setRowSelectionInterval(index2, index2);
//       }
//    }
    
     public void fillToFormSP(int index3) {

        List<SanPham_Model> list = spdao.selectAll();
        if (index3 >= 0 && index3 < list.size()) {
            SanPham_Model sp = list.get(index3);
            txtMaSP.setText(sp.getMASP());
            txtTenSP.setText(sp.getTENSP());
            
            tblKhachHangCD.setRowSelectionInterval(index3, index3);
        }
    }

    public void fillToFormKH() {
        String maKH = txtMaKH.getText();
        KhachHang_Model kh = khdao.selectById(maKH);
        if (kh != null) {
            txtMaKH.setText(kh.getMaKH());
            txtTenKH.setText(kh.getTenKH());
            txtCCCD.setText(kh.getSoCCCD());
            txtSoDT.setText(kh.getSoDTKH());
            txtDiaChi.setText(kh.getDiaChi());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grTrangThai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNgayLap = new com.toedter.calendar.JDateChooser();
        txtMaPhieu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnFormKH = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        txtMaKH = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtMaSP = new javax.swing.JTextField();
        txtSoNgayCam = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtLaiXuat = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtTrongLuong = new javax.swing.JTextField();
        txtDonViTinh = new javax.swing.JTextField();
        txtSoTienCam = new javax.swing.JTextField();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayHetHan = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHangCD = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTimKH = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("HỢP ĐỒNG CẦM ĐỒ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 1216, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(32, 32, 32)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("NGÀY");

        jLabel7.setText("MÃ PHIẾU");

        jLabel8.setText("MÃ NV");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN KHÁCH HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("MÃ kHÁCH HÀNG");

        btnFormKH.setText("+");
        btnFormKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormKHActionPerformed(evt);
            }
        });

        jLabel3.setText("CMND/CCCD");

        jLabel5.setText("SỐ ĐT");

        jLabel13.setText("ĐỊA CHỈ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane2.setViewportView(txtDiaChi);

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel20.setText("TÊN KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(txtCCCD))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFormKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnFormKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN HÀNG CẦM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        txtTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrongLuongActionPerformed(evt);
            }
        });

        jLabel1.setText("MÃ SẢN PHẨM");

        jLabel6.setText("NGÀY HẾT HẠN");

        jLabel9.setText("TÊN SẢN PHẨM");

        jLabel10.setText("NGÀY BẮT ĐẦU");

        jLabel11.setText("ĐƠN GIÁ");

        jLabel12.setText("KHỐI LƯỢNG");

        jLabel14.setText("SÓ TIỀN CẦM");

        jLabel15.setText("ĐƠN VỊ TÍNH");

        jLabel16.setText("SỐ NGÀY CẦM");

        jLabel17.setText("LÃI XUẤT/THÁNG");

        btnSanPham.setText("+");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDonViTinh, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTrongLuong, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(txtSoNgayCam))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(12, 12, 12)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoTienCam)
                    .addComponent(txtLaiXuat)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDonGia))
                .addGap(41, 41, 41))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayHetHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSoNgayCam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoTienCam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLaiXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

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

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("LAM MỚI");
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addGap(189, 189, 189))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH KHÁCH HÀNG CẦM ĐỒ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblKhachHangCD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ PHIẾU", "NGÀY LẬP", "SỐ TIỀN", "HẠN TRẢ", "SỐ NGÀY", "TRẠNG THÁI", "MÃ KH"
            }
        ));
        tblKhachHangCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangCDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHangCD);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("TÌM KHÁCH HÀNG");

        txtTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFormKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormKHActionPerformed
       
        new FormKhachHang().setVisible(true);
        
    }//GEN-LAST:event_btnFormKHActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        
        if (check()) {
            fillToFormKH();
            txtTenKH.setEditable(false);
            txtCCCD.setEditable(false);
            txtSoDT.setEditable(false);
            txtDiaChi.setEditable(false);
            return;
        }
        
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        
        insert();
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
       
        reset();
        init();
        
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKHActionPerformed
        
        fillTable();
        
    }//GEN-LAST:event_txtTimKHActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        
        delete();
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblKhachHangCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangCDMouseClicked
        
          int selectedRow = tblKhachHangCD.getSelectedRow();
        if (selectedRow >= 0) {
            fillToFormCD(selectedRow);
            fillToFormCTCD(selectedRow);
//            fillToFormKH(selectedRow);
            fillToFormSP(selectedRow);
        }
        
    }//GEN-LAST:event_tblKhachHangCDMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        
        Update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        
        
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        
        
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void txtTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrongLuongActionPerformed
        
        
    }//GEN-LAST:event_txtTrongLuongActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCamDo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFormKH;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup grTrangThai;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblKhachHangCD;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonViTinh;
    private javax.swing.JTextField txtLaiXuat;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtMaSP;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayHetHan;
    private com.toedter.calendar.JDateChooser txtNgayLap;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtSoNgayCam;
    private javax.swing.JTextField txtSoTienCam;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKH;
    private javax.swing.JTextField txtTrongLuong;
    // End of variables declaration//GEN-END:variables

    private ChiTietPhieuCam_Model getFormCTPC() {
        ChiTietPhieuCam_Model model = new ChiTietPhieuCam_Model();
        model.setMaCam(txtMaPhieu.getText());
        model.setSoTienCam(Float.parseFloat(txtSoTienCam.getText()));
        model.setKhoiLuong(Float.parseFloat(txtTrongLuong.getText()));
        model.setDonGia(Float.parseFloat(txtDonGia.getText()));
        model.setMaSP(txtMaSP.getText());
        model.setLaiXuat(Float.parseFloat(txtLaiXuat.getText()));
       
        return model;
    }
    
    private CamDo_Model getFormCam() {
        CamDo_Model cd = new CamDo_Model();
        cd.setMaCam(txtMaPhieu.getText());
        cd.setNgayLap(txtNgayLap.getDate());
        cd.setSoTienCam(Float.parseFloat(txtSoTienCam.getText()));
        cd.setMaKH(txtMaKH.getText());

        // Kiểm tra nếu mã nhân viên không được nhập, sử dụng mã nhân viên hiện tại
        if (txtMaNV.getText().equals("")) {
            cd.setMaNV(Auth.user.getMANV());
        } else {
            cd.setMaNV(txtMaNV.getText());
        }

        cd.setSoNgay(Float.parseFloat(txtSoNgayCam.getText()));
        cd.setNgayCam(txtNgayBatDau.getDate());
        cd.setNgayHetHan(txtNgayHetHan.getDate());

        // Xác định trạng thái của cam đồ
//        LocalDate ngayCam = txtNgayBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate ngayHetHan = txtNgayHetHan.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate ngayHienTai = LocalDate.now();
//        if (ngayHienTai.isBefore(ngayCam) || ngayHienTai.isAfter(ngayHetHan)) {
//            cd.setTrangThai("Hết hạn");
//        } else {
//            cd.setTrangThai("Còn hạn");
//        }

        return cd;
    }
     
     
      void insert() {
        CamDo_Model cd = getFormCam();
        ChiTietPhieuCam_Model ctpc = getFormCTPC();
        try {
            dao.insert(cd);
            ctpcdao.insert(ctpc);
            this.fillTable();
            this.reset();
            MsgBox.alert(this, "THÊM THÀNH CÔNG!");
        } catch (Exception e) {
            MsgBox.alert(this, "THÊM THẤT BẠI!");
            System.out.println(e);
        }
    }
     
    void Update() {
        CamDo_Model cd = getFormCam();
        ChiTietPhieuCam_Model ctpc = getFormCTPC();
        try {
            dao.update(cd);
            ctpcdao.update(ctpc);
            this.fillTable();
            this.reset();
            MsgBox.alert(this, "THÊM THÀNH CÔNG!");
        } catch (Exception e) {
            MsgBox.alert(this, "THÊM THẤT BẠI!");
            System.out.println(e);
        }
    }
      
    
    void delete() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "BẠN KHÔNG CÓ QUYỀN XOÁ KHÁCH HÀNG!");
        } else {
            String macd = txtMaPhieu.getText();
            if (MsgBox.confirm(this, "BẠN MUỐN XOÁ KHÁCH HÀNG NÀY?")) {
                try {
                    dao.delete(macd);
//                    ctpcdao.delete(macd);
                    this.fillTable();
                    this.reset();
                    MsgBox.alert(this, "XOÁ THÀNH CÔNG!");
                } catch (Exception e) {
                    MsgBox.alert(this, "XOÁ THẤT BẠI!");
                }
            }
      }
    }
    
    
     public void reset() {
        // Reset các trường dữ liệu
        txtMaPhieu.setText("");
        txtMaNV.setText("");
        txtNgayLap.setDate(null);
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtMaSP.setText("");
        txtCCCD.setText("");
        txtSoDT.setText("");
        txtDiaChi.setText("");
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayHetHan.setDate(null);
        txtDonGia.setText("");
        txtTrongLuong.setText("");
        txtDonViTinh.setText("");
        txtSoTienCam.setText("");
        txtSoNgayCam.setText("");
        txtLaiXuat.setText("");
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
       
        // Ẩn bảng tblHoaDonCT
    }
    
    boolean check() {
        String pName = txtMaKH.getText().trim();

        // Kiểm tra mã sản phẩm
        String sqlSP = "SELECT COUNT(*) FROM KHACHHANG WHERE MAKH = ?";
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            PreparedStatement stmtSP = con.prepareStatement(sqlSP);
            stmtSP.setString(1, pName);
            ResultSet resultSetSP = stmtSP.executeQuery();
            resultSetSP.next();
            int countSP = resultSetSP.getInt(1);
            if (countSP <= 0) {
                JOptionPane.showMessageDialog(this, "MÃ KHÁCH HÀNG KHÔNG TỒN TẠI!");
                txtMaKH.grabFocus();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ tại đây, ví dụ: hiển thị thông báo lỗi
        }
        return true;
    }

    
    boolean checkMaSP() {
        String pName = txtMaSP.getText().trim();

        // Kiểm tra mã sản phẩm
        String sqlSP = "SELECT COUNT(*) FROM SANPHAM WHERE MASP = ?";
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            PreparedStatement stmtSP = con.prepareStatement(sqlSP);
            stmtSP.setString(1, pName);
            ResultSet resultSetSP = stmtSP.executeQuery();
            resultSetSP.next();
            int countSP = resultSetSP.getInt(1);
            if (countSP <= 0) {
                JOptionPane.showMessageDialog(this, "MÃ SẢN PHẨM KHÔNG TỒN TẠI!");
                txtMaSP.grabFocus();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ tại đây, ví dụ: hiển thị thông báo lỗi
        }
        return true;
    }

}
