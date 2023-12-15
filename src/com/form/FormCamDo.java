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
import com.model.Excel;
import com.toedter.calendar.JDateChooser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormCamDo extends javax.swing.JFrame {

    CamDo_DAO dao = new CamDo_DAO();
    KhachHang_DAO khdao = new KhachHang_DAO();
    SanPham_DAO spdao = new SanPham_DAO();
    ChiTietPhieuCam_DAO ctpcdao = new ChiTietPhieuCam_DAO();
    DecimalFormat dcf = new DecimalFormat("###,###");

    public FormCamDo() {
        initComponents();
        init();
    }
    //in Hoá đơn 

    public JTable getTblHoaDonCT() {
        return tblHDCT;
    }

    public JTextField getTxtMaNV() {
        return txtMaNV;
    }

    public JTextField getTxtTenKH() {
        return txtTenKH;
    }

    public JTextField getSoCCCD() {
        return txtCCCD;
    }

    public JTextField getSoDT() {
        return txtSoDT;
    }

    public JTextField getTxtMaHD() {
        return txtMaPhieu;
    }

    public JTextField getTxtThanhToan() {
        return txtThanhToan;
    }

    public JTextField getTxtTienPhaiTra() {
        return txtTienChuoc;
    }

    public JDateChooser getNgayBatDau() {
        return txtNgayBatDau;
    }

    public JDateChooser getNgayKetThuc() {
        return txtNgayHetHan;
    }

    public JDateChooser getNgay() {
        return txtNgayLap;
    }

    public JTextField getlaiXuat() {
        return txtLaiXuat;
    }

    public JTextField getTienLaiThang() {
        return txtTienLaiThang;
    }

    void init() {
        CamDo_Model cd = new CamDo_Model();
        this.fillTableHDCD();
        fillTongGiaTri();
        txtNgayLap.setEnabled(false);
        txtNgayBatDau.setEnabled(false);
        txtMaPhieu.setEditable(false);
        txtMaNV.setEditable(false);
        txtLaiXuat.setEditable(false);
        txtLaiPD.setEditable(false);
        txtSoTienCam.setEditable(false);
        txtTongTienCam.setEditable(false);
        txtThanhToan.setEditable(false);
        txtTienChuoc.setEditable(false);
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

        if (txtNgayBatDau.getDate() == null) {
            txtNgayBatDau.setDate(now);
            cd.setNgayCam(XDate.toDate(nowFormatted, "yyyy-MM-dd"));
        } else {
            cd.setNgayCam(txtNgayBatDau.getDate());
        }
        txtTienLaiThang.setEnabled(false);
        txtNgayTu.setDate(now);
        txtNgayDen.setDate(now);
        txtMaNV.setText((Auth.user.getMANV()));
        btnThanhLy.setEnabled(false);
    }

    void fillTableHDCD() {
        DefaultTableModel model = (DefaultTableModel) tblHDCD.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKH.getText();
            List<CamDo_Model> list = dao.selectByKyword(keyword);
            for (CamDo_Model cd : list) {
                Object[] row = {
                    cd.getMaCam(),
                    cd.getTenKH(),
                    dcf.format(cd.getSoTienCam()),
                    cd.getNgayLap(),
                    cd.getNgayHetHan(),
                    cd.getSoNgay(),
                    cd.isTrangThai() ? "Còn hạn" : "Hết hạn"
                };
                model.addRow(row);
                fillTongGiaTri();
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
        }
    }

    void fillTableHoaDonCT(String maCAM) {
        DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
        model.setRowCount(0);
        try {
            List<ChiTietPhieuCam_Model> list = ctpcdao.selectsp_br(maCAM);
            for (ChiTietPhieuCam_Model nh : list) {
                Object[] row = {
                    nh.getMaSP(),
                    nh.getTenSP(),
                    nh.getKhoiLuong(),
                    dcf.format(nh.getDonGia()),
                    dcf.format(nh.getSoTienCam()),
                    nh.getLaiXuat(),
                    //dcf.format( nh.getLaiXuat()),
                    dcf.format(nh.getLaiXuatPT()),
                    nh.getMaPhieu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
            e.printStackTrace();
        }
    }

    public void fillToFormHDCD(int index) {
        List<CamDo_Model> list = dao.selectAll();
        if (index >= 0 && index < list.size()) {
            CamDo_Model cd = list.get(index);
            txtMaPhieu.setText(cd.getMaCam());
            txtMaKH.setText(cd.getMaKH());
            txtTenKH.setText(cd.getTenKH());
            txtCCCD.setText(cd.getCccd());
            txtSoDT.setText(cd.getSdtKH());
            txtMaNV.setText(cd.getMaNV());
            txtNgayLap.setDate(cd.getNgayLap());
            txtNgayBatDau.setDate(cd.getNgayCam());
            txtNgayHetHan.setDate(cd.getNgayHetHan());
            txtSoNgayCam.setText(dcf.format(cd.getSoNgay()));
            tblHDCD.setRowSelectionInterval(index, index);
            fillToTenKH();
            double tSotienCam = cd.getSoTienCam();
            txtTongTienCam.setText(dcf.format(tSotienCam));
            txtThanhToan.setText(dcf.format(tSotienCam));
            // Cộng giá trị thành tiền vào tổng tiền
        }
    }

    public void fillToFormHDCT(int selectedRow) {
        List<ChiTietPhieuCam_Model> list = ctpcdao.selectsp_br(txtMaPhieu.getText());
        if (selectedRow >= 0 && selectedRow < list.size()) {
            ChiTietPhieuCam_Model kh = list.get(selectedRow);
            txtMaSP.setText(kh.getMaSP());
            txtTenSP.setText(kh.getTenSP());
            txtTrongLuong.setText(String.valueOf(kh.getKhoiLuong()));
            txtDonGia.setText(dcf.format(kh.getDonGia()));
            txtSoTienCam.setText(dcf.format(kh.getSoTienCam()));
            txtLaiXuat.setText(kh.getLaiXuat() + "");

            String laiXuatText = txtLaiXuat.getText();
            try {
                double laiXuat = Double.parseDouble(laiXuatText);
                if (laiXuat <= 0) {
                    MsgBox.alert(this, "LÃI XUẤT PHẢI LỚN HƠN 0!");
                    return; // Thoát khỏi phương thức nếu lãi xuất không hợp lệ
                }
                double trongLuong = Double.parseDouble(txtTrongLuong.getText());
                double donGia = Double.parseDouble(txtDonGia.getText().replace(",", ""));
                double thanhTien = ((trongLuong * donGia) / 3.75 / 100) * 80;
                double lai = thanhTien * (laiXuat / 100);
                txtLaiPD.setText(dcf.format(lai));
                txtLaiPD.setEditable(false);
            } catch (NumberFormatException e) {
                MsgBox.alert(this, "LỖI: LÃI XUẤT KHÔNG HỢP LỆ!");
                e.printStackTrace();
            }

            // Cộng giá trị thành tiền vào tổng tiền
            double tongTien = 0;
            for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                String thanhTienStr = tblHDCT.getValueAt(i, 4).toString();
                if (isNumber(thanhTienStr)) {
                    double thanhTienValue = Double.parseDouble(thanhTienStr.replace(",", ""));
                    tongTien += thanhTienValue;
                }
            }
            txtTongTienCam.setText(dcf.format(tongTien));
            txtThanhToan.setText(dcf.format(tongTien));
            // Cộng giá trị tiền chuộc
            double tienchuoc = 0;
            for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                double laiXuatt = Double.parseDouble(tblHDCT.getValueAt(i, 5).toString().replace(",", ""));
                double thanhTienn = Double.parseDouble(tblHDCT.getValueAt(i, 4).toString().replace(",", ""));
                double tien = (laiXuatt / 100) * thanhTienn;
                double chuoc = tien + thanhTienn;
                tienchuoc += chuoc;
            }
            txtTienChuoc.setText(dcf.format(tienchuoc));
        }
        tblHDCT.setRowSelectionInterval(selectedRow, selectedRow);
    }

    public void fillToTenKH() {
        String maKh = txtMaKH.getText();
        KhachHang_Model khm = khdao.selectBysdt(maKh);
        txtTenKH.setText(khm.getTenKH());
        txtCCCD.setText(khm.getSoCCCD());
        txtSoDT.setText(khm.getSoDTKH());
    }

    public void fillToFormSP() {
        String maSP = txtMaSP.getText();
        SanPham_Model sanPham = spdao.selectById(maSP);
        if (sanPham != null) {
            // txtMaSP.setText(sanPham.getMASP());
            txtDonGia.setText(dcf.format(sanPham.getDONGIABAN()));
            txtTenSP.setText(sanPham.getTENSP());
        }
    }

    void selectDay() {
        LocalDate startDate = txtNgayTu.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = txtNgayDen.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Để bao gồm cả ngày startDate, bạn cần thay đổi endDate bằng cách thêm 1 ngày.
        endDate = endDate.plusDays(1);
        Date sqlStartDate = java.sql.Date.valueOf(startDate);
        Date sqlEndDate = java.sql.Date.valueOf(endDate);
        List<CamDo_Model> list = dao.getOrdersByDateRange(sqlStartDate, sqlEndDate);
        DefaultTableModel model = (DefaultTableModel) tblHDCD.getModel();
        model.setRowCount(0);
        try {
            for (CamDo_Model cd : list) {
                Object[] row = {
                    cd.getMaCam(),
                    cd.getTenKH(),
                    dcf.format(cd.getSoTienCam()),
                    cd.getNgayLap(),
                    cd.getNgayHetHan(),
                    cd.getSoNgay(),
                    cd.isTrangThai() ? "Còn hạn" : "Hết hạn"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
            System.out.println(e);
        }
    }

    void fillTongGiaTri() {
        double tongTien = 0;
        for (int i = 0; i < tblHDCD.getRowCount(); i++) {
            Object value = tblHDCD.getValueAt(i, 2);
            if (value instanceof Number) {
                double thanhTienValue = ((Number) value).doubleValue();
                tongTien += thanhTienValue;
            } else if (value instanceof String) {
                String thanhTienStr = (String) value;
                if (isNumber(thanhTienStr)) {
                    double thanhTienValue = Double.parseDouble(thanhTienStr.replace(",", ""));
                    tongTien += thanhTienValue;
                }
            }
        }
        txtTongTienHang.setText(dcf.format(tongTien));
    }

    boolean isNumber(String str) {
        try {
            Double.parseDouble(str.replace(",", ""));
            return true;
        } catch (NumberFormatException e) {
            return false;
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
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtLaiPD = new javax.swing.JTextField();
        btnThanhLy = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtThanhToan = new javax.swing.JTextField();
        txtTongTienCam = new javax.swing.JTextField();
        btnXemHD = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtTienChuoc = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtTienLaiThang = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTimKH = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtNgayTu = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        txtNgayDen = new com.toedter.calendar.JDateChooser();
        btnLoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDCD = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        txtTongTienHang = new javax.swing.JLabel();
        btnXuatCamDo = new javax.swing.JButton();

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
            .addGap(0, 0, Short.MAX_VALUE)
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

        jLabel2.setText("MÃ KHÁCH HÀNG");

        btnFormKH.setText("+");
        btnFormKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormKHActionPerformed(evt);
            }
        });

        jLabel3.setText("CMND/CCCD");

        txtCCCD.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCCCD.setEnabled(false);

        jLabel5.setText("SỐ ĐT");

        txtSoDT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSoDT.setEnabled(false);

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel20.setText("TÊN KHÁCH HÀNG");

        txtTenKH.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenKH.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFormKH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(txtTenKH))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFormKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN HÀNG CẦM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        txtSoNgayCam.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSoNgayCam.setEnabled(false);

        txtTenSP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenSP.setEnabled(false);

        txtLaiXuat.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLaiXuat.setEnabled(false);

        txtDonGia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDonGia.setEnabled(false);

        txtTrongLuong.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTrongLuong.setEnabled(false);
        txtTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrongLuongActionPerformed(evt);
            }
        });

        txtSoTienCam.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSoTienCam.setEnabled(false);

        jLabel1.setText("MÃ SẢN PHẨM");

        jLabel6.setText("NGÀY HẾT HẠN");

        jLabel9.setText("TÊN SẢN PHẨM");

        jLabel10.setText("NGÀY BẮT ĐẦU");

        jLabel11.setText("ĐƠN GIÁ/CHỈ");

        jLabel12.setText("KHỐI LƯỢNG");

        jLabel14.setText("SỐ TIỀN CẦM");

        jLabel16.setText("SỐ NGÀY CẦM");

        jLabel17.setText("LÃI XUẤT/THÁNG");

        btnSanPham.setText("+");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnLamMoi.setText("TẠO HOÁ ĐƠN MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("CẬP NHẬT");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel21.setText("TIỀN/ THÁNG");

        txtLaiPD.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLaiPD.setEnabled(false);

        btnThanhLy.setText("THANH LÝ HỢP ĐỒNG");
        btnThanhLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhLyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSoTienCam, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTrongLuong, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(txtSoNgayCam)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLaiXuat)
                            .addComponent(txtTenSP)
                            .addComponent(txtNgayHetHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDonGia)
                            .addComponent(txtLaiPD))
                        .addGap(41, 41, 41))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnLamMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThanhLy)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                            .addComponent(txtTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSoNgayCam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17))
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoTienCam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLaiXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLaiPD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnXoa))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLamMoi)
                        .addComponent(btnThanhLy)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "KHỐI LƯỢNG ", "ĐƠN GIÁ", "SỐ TIỀN CẦM", "LÃI XUẤT/NGÀY"
            }
        ));
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHDCTMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblHDCT);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("THANH TOÁN"));

        jLabel13.setText("TỔNG TIỀN CẦM");

        jLabel15.setText("THANH TOÁN");

        txtThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtThanhToan.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtTongTienCam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongTienCam.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        btnXemHD.setText("XEM PHIẾU CẦM");
        btnXemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemHDActionPerformed(evt);
            }
        });

        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel25.setText("SỐ TIỀN CHUỘC");

        txtTienChuoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienChuoc.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel26.setText("TIỀN LÃI/THÁNG");

        txtTienLaiThang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienLaiThang.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnXemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtThanhToan, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTongTienCam, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(txtTienChuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTienLaiThang, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienLaiThang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienChuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTienCam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnXemHD))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(44, 44, 44))
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("TÌM KHÁCH HÀNG");

        txtTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKHActionPerformed(evt);
            }
        });

        jLabel23.setText("Từ");

        jLabel24.setText("Đến");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH KHÁCH HÀNG CẦM ĐỒ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHDCD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ PHIẾU", "TÊN KH", "SỐ TIỀN", "NGÀY LẬP", "HẠN TRẢ", "SỐ NGÀY", "TRẠNG THÁI"
            }
        ));
        tblHDCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCDMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHDCDMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHDCD);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("TỔNG GIÁ TRỊ:");

        txtTongTienHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTongTienHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnXuatCamDo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatCamDo.setText("XUẤT FILE");
        btnXuatCamDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatCamDoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatCamDo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnXuatCamDo)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayTu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLoc))
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtTimKH)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayTu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(txtNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnLoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFormKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormKHActionPerformed
        new FormKhachHang().setVisible(true);
    }//GEN-LAST:event_btnFormKHActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        if (check()) {
            fillToTenKH();
            txtTenKH.setEditable(false);
            txtCCCD.setEditable(false);
            txtSoDT.setEditable(false);

            return;
        }
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!validatesSP()) {
            return;
        } else if (check()) {
            add();
            btnXoa.setEnabled(true);
            btnSua.setEnabled(true);
        }
        txtMaSP.setEnabled(true);
        txtTrongLuong.setEnabled(false);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        resetAll((DefaultTableModel) tblHDCT.getModel());
        init();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKHActionPerformed
        fillTableHDCD();
    }//GEN-LAST:event_txtTimKHActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblHDCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCDMouseClicked
        int selectedRow = tblHDCD.getSelectedRow();
        fillToFormHDCD(selectedRow);
        reset();
        if (selectedRow >= 0) {
            String maCam = tblHDCD.getValueAt(selectedRow, 0).toString();
            fillTableHoaDonCT(maCam);
            txtMaKH.setEnabled(false);
            txtNgayLap.setEnabled(false);
            txtMaSP.setEnabled(false);
            txtTrongLuong.setEnabled(false);
            btnThem.setEnabled(false);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnThanhToan.setEnabled(false);
            btnXemHD.setEnabled(true);
            btnThanhLy.setEnabled(true);
            txtNgayHetHan.setEnabled(false);
        }
        double tienchuoc = 0;
        for (int i = 0; i < tblHDCT.getRowCount(); i++) {
            double laiXuatt = Double.parseDouble(tblHDCT.getValueAt(i, 5).toString().replace(",", ""));
            double thanhTienn = Double.parseDouble(tblHDCT.getValueAt(i, 4).toString().replace(",", ""));
            double tien = (laiXuatt / 100) * thanhTienn;
            double chuoc = tien + thanhTienn;
            tienchuoc += chuoc;
            txtTienChuoc.setText(dcf.format(tienchuoc));
        }
        double Tonglaithang = 0;
        for (int i = 0; i < tblHDCT.getRowCount(); i++) {
            double laiXuatt = Double.parseDouble(tblHDCT.getValueAt(i, 5).toString().replace(",", ""));
            double thanhTienn = Double.parseDouble(tblHDCT.getValueAt(i, 4).toString().replace(",", ""));
            //double tien = (laiXuatt / 100) * thanhTienn;
            double lai = thanhTienn * (laiXuatt / 100);
            Tonglaithang += lai;
            txtTienLaiThang.setText(dcf.format(Tonglaithang));
        }

        tblHDCT.setVisible(true);
    }//GEN-LAST:event_tblHDCDMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        updateSelectedRow();

    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed

        if (checkMaSP()) {
            fillToFormSP();
            txtTrongLuong.setEnabled(true);
            return;
        }

    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed

        new FormSanPham().setVisible(true);

    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void txtTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrongLuongActionPerformed

        String trongLuongText = txtTrongLuong.getText();
        if (trongLuongText.isEmpty()) {
            MsgBox.alert(this, "TRỌNG LƯỢNG KHÔNG ĐƯỢC TRỐNG!");
            return; // Thoát khỏi phương thức nếu chuỗi rỗng
        }
        try {
            double trongLuong = Double.parseDouble(trongLuongText);
            if (trongLuong <= 0) {
                MsgBox.alert(this, " TRỌNG LƯỢNG PHẢI LỚN HƠN 0!");
                return; // Thoát khỏi phương thức nếu trọng lượng không hợp lệ
            }
            double donGia = Double.parseDouble(txtDonGia.getText().replace(",", ""));
            double thanhTien = (trongLuong * donGia) / 3.75;
            double soTienCam = (thanhTien / 100) * 80;
            txtSoTienCam.setText(dcf.format(soTienCam));
            txtSoTienCam.setEditable(false);
            // Lấy ngày bắt đầu và ngày kết thúc từ các trường ngày
            LocalDate ngayBatDau = txtNgayBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate ngayHetHan = txtNgayHetHan.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // Kiểm tra nếu ngày hết hạn không phải sau ngày bắt đầu
            if (ngayBatDau.isAfter(ngayHetHan)) {
                MsgBox.alert(this, "NGÀY HẾT HẠN PHẢI SAU NGÀY BẮT ĐẦU!");
                return; // Thoát khỏi phương thức nếu có lỗi
            }
            // Tính số ngày giữa ngày bắt đầu và ngày kết thúc
            long soNgay = ChronoUnit.DAYS.between(ngayBatDau, ngayHetHan);
            // Đảo dấu số ngày để có kết quả dương
            soNgay = Math.abs(soNgay);
            // Hiển thị số ngày
            txtSoNgayCam.setText(String.valueOf(soNgay));
            txtSoNgayCam.setEditable(false);
            if (soNgay < 30) {
                MsgBox.alert(this, "NGÀY CẦM KHÔNG ĐƯỢC DƯỚI 30 NGÀY!!");
                txtSoNgayCam.setText("");
            } else if (soNgay >= 30 && soNgay <= 90) {
                double laiXuat = 0.02;
                txtLaiXuat.setText("2%");
                double laidong = (soTienCam * laiXuat);
                txtLaiPD.setText(dcf.format(laidong));
            } else if (soNgay > 90 && soNgay <= 365) {
                double laiXuat = 0.025;
                txtLaiXuat.setText("2.5%");
                double laidong = (soTienCam * laiXuat);
                txtLaiPD.setText(dcf.format(laidong));
            } else {
                double laiXuat = 0.04;
                txtLaiXuat.setText("4%");
                double laidong = (soTienCam * laiXuat);
                txtLaiPD.setText(dcf.format(laidong));
            }

        } catch (NumberFormatException e) {
            MsgBox.alert(this, "LỖI: TRỌNG LƯỢNG KHÔNG HỢP LỆ!");
            txtTrongLuong.setText("");
            e.printStackTrace();
        }

    }//GEN-LAST:event_txtTrongLuongActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked

        int selectedRow = tblHDCT.getSelectedRow();
        if (selectedRow >= 0) {
            fillToFormHDCT(selectedRow);
        }

    }//GEN-LAST:event_tblHDCTMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        if (!validates()) {
            return;
        } else if (checkkh()) {
            insert();
            resetAll((DefaultTableModel) tblHDCT.getModel());
            return;
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblHDCTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMousePressed
        if (evt.getClickCount() == 2) {

            int selectedRow = tblHDCT.getSelectedRow();
            int tlColumnIndex = tblHDCT.getColumnModel().getColumnIndex("KHỐI LƯỢNG ");
            if (tblHDCT.getSelectedColumn() == tlColumnIndex) {
                tblHDCT.setEnabled(true);
                tblHDCT.setRowSelectionAllowed(true); // Cho phép chọn hàng
                tblHDCT.setColumnSelectionAllowed(true); // Cho phép chọn cột
            } else {
                MsgBox.alert(this, "CHỈ ĐƯỢC PHÉP CẬP NHẬT 'KHỐI LƯỢNG' CỦA SẢN PHẨM!");
                DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
                model.setColumnCount(tblHDCT.getColumnCount()); // Đảm bảo số lượng cột không thay đổi
                tblHDCT.setEnabled(true);
                tblHDCT.setRowSelectionAllowed(true); // Cho phép chọn hàng
                tblHDCT.setColumnSelectionAllowed(true); // Cho phép chọn cột
                tblHDCT.getColumnModel().getColumn(tlColumnIndex).setCellEditor(new DefaultCellEditor(new JTextField()));
                // Kiểm tra chỉ mục hàng có hợp lệ hay không
                if (selectedRow >= 0 && selectedRow < tblHDCT.getRowCount()) {
                    tblHDCT.setRowSelectionInterval(selectedRow, selectedRow); // Chọn lại hàng đã chọn trước đó
                }
            }
        }
    }//GEN-LAST:event_tblHDCTMousePressed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        selectDay();
        fillTongGiaTri();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnXemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemHDActionPerformed
        XuatHopDongCamDo xuatHD = new XuatHopDongCamDo(this); // Truyền tham chiếu của FormMuaVao
        xuatHD.setVisible(true);
    }//GEN-LAST:event_btnXemHDActionPerformed

    public void excelProducts() throws IOException {
        DefaultTableModel model = (DefaultTableModel) tblHDCD.getModel();
        Excel.outExcel(model);
    }

    private void btnXuatCamDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatCamDoActionPerformed

        try {
            excelProducts();
        } catch (IOException ex) {
            //      Logger.getLogger(FormThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnXuatCamDoActionPerformed

    private void tblHDCDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCDMousePressed

    }//GEN-LAST:event_tblHDCDMousePressed

    private void btnThanhLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhLyActionPerformed
        thanhLyHopDong();
    }//GEN-LAST:event_btnThanhLyActionPerformed

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
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThanhLy;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXemHD;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatCamDo;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblHDCD;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtLaiPD;
    private javax.swing.JTextField txtLaiXuat;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtMaSP;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayDen;
    private com.toedter.calendar.JDateChooser txtNgayHetHan;
    private com.toedter.calendar.JDateChooser txtNgayLap;
    private com.toedter.calendar.JDateChooser txtNgayTu;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtSoNgayCam;
    private javax.swing.JTextField txtSoTienCam;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThanhToan;
    private javax.swing.JTextField txtTienChuoc;
    private javax.swing.JTextField txtTienLaiThang;
    private javax.swing.JTextField txtTimKH;
    private javax.swing.JTextField txtTongTienCam;
    private javax.swing.JLabel txtTongTienHang;
    private javax.swing.JTextField txtTrongLuong;
    // End of variables declaration//GEN-END:variables

    void add() {
        DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
        // Xóa hết các dòng trong bảng
        String maSP = txtMaSP.getText();
        double trongLuong = 0.0;
        double donGia = 0.0;
        double laiXuat = 0.0;
        try {
            trongLuong = Double.parseDouble(txtTrongLuong.getText().replace(",", ""));
            donGia = Double.parseDouble(txtDonGia.getText().replace(",", ""));
            laiXuat = Double.parseDouble(txtLaiXuat.getText().replace("%", ""));
        } catch (NumberFormatException e) {
            // Xử lý khi chuỗi không thể chuyển đổi thành số
            MsgBox.alert(this, "ENTER SẢN PHẨM ĐỂ NHẬP TRỌNG LƯỢNG!!");
            e.printStackTrace();
            return;
        }
        double thanhTien = ((trongLuong * donGia) / 3.75 / 100) * 80;
        txtSoTienCam.setText(dcf.format(thanhTien));
        String tenSP = txtTenSP.getText();
        String formattedDonGia = dcf.format(donGia);
        String formattedThanhTien = dcf.format(thanhTien);

        Object[] rowData = {maSP, tenSP, trongLuong, formattedDonGia,
            formattedThanhTien, laiXuat};
        model.addRow(rowData);
        //tblHoaDonCT.setVisible(true);
        reset();
        // Cộng giá trị thành tiền vào tổng tiền
        double tongTien = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String thanhTienStr = model.getValueAt(i, 4).toString();
            if (isNumber(thanhTienStr)) {
                double thanhTienValue = Double.parseDouble(thanhTienStr.replace(",", ""));
                tongTien += thanhTienValue;
            }
        }
        double tienchuoc = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double laiXuatt = Double.parseDouble(model.getValueAt(i, 5).toString().replace(",", ""));
            double thanhTienn = Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", ""));
            double tien = (laiXuatt / 100) * thanhTienn;
            double chuoc = tien + thanhTienn;
            tienchuoc += chuoc;
        }

        double Tonglaithang = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double laiXuatt = Double.parseDouble(model.getValueAt(i, 5).toString().replace(",", ""));
            double thanhTienn = Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", ""));
            //double tien = (laiXuatt / 100) * thanhTienn;
            double lai = thanhTienn * (laiXuatt / 100);
            Tonglaithang += lai;
        }
        txtTienLaiThang.setText(dcf.format(Tonglaithang));
        txtTienChuoc.setText(dcf.format(tienchuoc));
        txtTongTienCam.setText(dcf.format(tongTien));
        txtThanhToan.setText(dcf.format(tongTien));

    }

    private void updateSelectedRow() {
        DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
        int selectedRow = tblHDCT.getSelectedRow();
        double trongLuong = Double.parseDouble(tblHDCT.getValueAt(selectedRow, 2).toString().replace(",", ""));
        double donGia = Double.parseDouble(tblHDCT.getValueAt(selectedRow, 3).toString().replace(",", ""));
        double laiXuat = Double.parseDouble(tblHDCT.getValueAt(selectedRow, 5).toString().replace("%", ""));
        double thanhTien = ((trongLuong * donGia) / 3.75 / 100) * 80;
        String formattedThanhTien = dcf.format(thanhTien);
        tblHDCT.setValueAt(trongLuong, selectedRow, 2); // Cập nhật trọng lượng trong bảng
        tblHDCT.setValueAt(formattedThanhTien, selectedRow, 4); // Cập nhật thành tiền trong bảng
        // Cộng giá trị thành tiền vào tổng tiền
        double tongTien = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double thanhTienValue = Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", ""));
            tongTien += thanhTienValue;
        }
        double tienchuoc = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double laiXuatt = Double.parseDouble(model.getValueAt(i, 5).toString().replace(",", ""));
            double thanhTienn = Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", ""));
            double tien = (laiXuatt / 100) * thanhTienn;
            double chuoc = tien + thanhTienn;
            tienchuoc += chuoc;
        }

        double Tonglaithang = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double laiXuatt = Double.parseDouble(model.getValueAt(i, 5).toString().replace(",", ""));
            double thanhTienn = Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", ""));
            //double tien = (laiXuatt / 100) * thanhTienn;
            double lai = thanhTienn * (laiXuatt / 100);
            Tonglaithang += lai;
        }
        txtTienLaiThang.setText(dcf.format(Tonglaithang));
        txtTienChuoc.setText(dcf.format(tienchuoc));
        txtTongTienCam.setText(dcf.format(tongTien));
        txtThanhToan.setText(dcf.format(tongTien));

        MsgBox.alert(this, "CẬP NHẬT KHỐI LƯỢNG THÀNH CÔNG!");
    }

    void delete() {
        DefaultTableModel tbModel = (DefaultTableModel) tblHDCT.getModel();
        int vitri = tblHDCT.getSelectedRow();
        if (vitri >= 0) {
            // Lưu trữ giá trị ban đầu của cột thứ hai
            Object originalValue = tblHDCT.getValueAt(vitri, 1);
            tbModel.removeRow(vitri);
            // Gán lại giá trị ban đầu cho cột thứ hai trong vòng lặp
            for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                tblHDCT.setValueAt(originalValue, i, 1);
            }
        }
        // Cộng giá trị thành tiền vào tổng tiền
        double tongTien = 0;
        for (int i = 0; i < tbModel.getRowCount(); i++) {
            double thanhTienValue = Double.parseDouble(tbModel.getValueAt(i, 4).toString().replace(",", ""));
            tongTien += thanhTienValue;
        }
        double tienchuoc = 0;
        for (int i = 0; i < tbModel.getRowCount(); i++) {
            double laiXuat = Double.parseDouble(tbModel.getValueAt(i, 5).toString().replace(",", ""));
            double thanhTien = Double.parseDouble(tbModel.getValueAt(i, 4).toString().replace(",", ""));
            double tien = (laiXuat / 100) * thanhTien;
            double chuoc = tien + thanhTien;
            tienchuoc += chuoc;
        }

        double Tonglaithang = 0;
        for (int i = 0; i < tbModel.getRowCount(); i++) {
            double laiXuatt = Double.parseDouble(tbModel.getValueAt(i, 5).toString().replace(",", ""));
            double thanhTienn = Double.parseDouble(tbModel.getValueAt(i, 4).toString().replace(",", ""));
            //double tien = (laiXuatt / 100) * thanhTienn;
            double lai = thanhTienn * (laiXuatt / 100);
            Tonglaithang += lai;
        }
        txtTienLaiThang.setText(dcf.format(Tonglaithang));
        txtTienChuoc.setText(dcf.format(tienchuoc));
        txtTongTienCam.setText(dcf.format(tongTien));
        txtThanhToan.setText(dcf.format(tongTien));
    }

    String findMaKhachHangByTen(String tenKhachHang) {
        List<KhachHang_Model> danhSachKhachHang = khdao.selectAll(); // Hàm getDanhSachKhachHang() là hàm lấy danh sách khách hàng

        for (KhachHang_Model khachHang : danhSachKhachHang) {
            if (khachHang.getTenKH().equals(tenKhachHang)) {
                return khachHang.getMaKH();
            }
        }
        return null; // Trả về null nếu không tìm thấy mã khách hàng
    }

    private CamDo_Model getFormHoaDon() {
        CamDo_Model kh = new CamDo_Model();
        kh.setMaCam(txtMaPhieu.getText());
        String tenKhachHang = txtTenKH.getText();
        String maKhachHang = findMaKhachHangByTen(tenKhachHang); // Hàm findMaKhachHangByTen() là hàm tìm mã khách hàng từ tên khách hàng 
        kh.setMaKH(maKhachHang);
        kh.setNgayLap(txtNgayLap.getDate());
        kh.setNgayCam(txtNgayBatDau.getDate());
        kh.setNgayHetHan(txtNgayHetHan.getDate());

        if (txtMaNV.getText().equals("")) {
            kh.setMaNV(Auth.user.getMANV());
        } else {
            kh.setMaNV(txtMaNV.getText());
        }

        kh.setSoNgay(Float.parseFloat(txtSoNgayCam.getText()));
        kh.setSoTienCam(Float.parseFloat(txtTongTienCam.getText().replace(",", "")));

        return kh;
    }

    //LƯU VÀO HOÁ ĐƠN VÀ HOÁ ĐƠN CT
    void insert() {
        CamDo_Model cd = getFormHoaDon();
        try {
            dao.insert(cd);
            String maCam = cd.getMaCam();
            DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maSP = model.getValueAt(i, 0).toString();
                float trongLuong = Float.parseFloat(model.getValueAt(i, 2).toString().replace(",", ""));
                float donGia = Float.parseFloat(model.getValueAt(i, 3).toString().replace(",", ""));
                float thanhTien = Float.parseFloat(model.getValueAt(i, 4).toString().replace(",", ""));
                float laiXuat = Float.parseFloat(model.getValueAt(i, 5).toString().replace(",", ""));
                // Tạo đối tượng hoá đơn chi tiết
                ChiTietPhieuCam_Model ct = new ChiTietPhieuCam_Model();
                ct.setMaCam(maCam);
                ct.setKhoiLuong(trongLuong);
                ct.setDonGia(donGia);
                ct.setSoTienCam(thanhTien);
                ct.setMaSP(maSP);
                ct.setLaiXuat(laiXuat);
                ctpcdao.insert(ct);
            }

            MsgBox.confirm(this, "XÁC NHẬN THANH TOÁN!");
            this.fillTableHDCD();
            XuatHopDongCamDo xuatHD = new XuatHopDongCamDo(this); // Truyền tham chiếu của FormMuaVao
            xuatHD.setVisible(true);
            this.reset();
        } catch (Exception e) {
            MsgBox.alert(this, "THANH TOÁN THẤT BẠI!");
            e.printStackTrace();
        }
    }

    public String getMaPhieu(String maCam) {
        String maPhieu = null;
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            String sql = "SELECT MAPHIEU FROM PHIEUCAMDO WHERE MACAM = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maCam);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                maPhieu = resultSet.getString("MAPHIEU");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maPhieu;
    }

    void thanhLyHopDong() {
        String maHDC = txtMaPhieu.getText();
        String maPhieu = getMaPhieu(maHDC);
        if (MsgBox.confirm(this, "XÁC NHẬN THANH LÝ HỢP ĐỒNG?")) {
            try {
                ctpcdao.delete(maPhieu);
                dao.delete(maHDC);
                MsgBox.alert(this, "THANH LÝ THÀNH CÔNG!");
                this.fillTableHDCD();
                XuatHopDongCamDo xuatHD = new XuatHopDongCamDo(this); // Truyền tham chiếu của FormMuaVao
                xuatHD.setVisible(true);
                this.reset();
            } catch (Exception e) {
                MsgBox.alert(this, "THANH LÝ THẤT BẠI!");
                e.printStackTrace();
            }
        }
    }

    public void reset() {
        txtMaSP.setText("");
        txtTrongLuong.setText("");
        txtDonGia.setText("");
        txtTenSP.setText("");
        txtSoTienCam.setText("");
        //txtLaiXuat.setText("");
        txtLaiPD.setText("");
    }

    public void resetAll(DefaultTableModel model) {
        // Reset các trường dữ liệu
        txtMaPhieu.setText("");
        txtMaNV.setText("");
        txtNgayLap.setDate(null);
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtCCCD.setText("");
        txtSoDT.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayHetHan.setDate(null);
        txtSoNgayCam.setText("");
        txtTongTienCam.setText("");
        txtThanhToan.setText("");
        txtLaiXuat.setText("");
        txtTienChuoc.setText("");
        btnThem.setEnabled(true);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        txtMaSP.setEnabled(true);
        btnThanhToan.setEnabled(true);
        btnXemHD.setEnabled(true);
        txtMaKH.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        txtTienLaiThang.setText("");
        txtNgayHetHan.setEnabled(true);
        reset();
        // Ẩn bảng tblHoaDonCT
        model.setRowCount(0);
    }

    boolean validates() {
        if (txtMaKH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "MÃ KHÁCH HÀNG KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtMaKH.requestFocus();
            return false;
        }
        return true;
    }

    boolean validatesSP() {
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "MÃ SẢN PHẨM KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtMaSP.requestFocus();
            return false;
        }
        if (txtNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "NGÀY BẮT ĐẦU KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtNgayBatDau.requestFocus();
            return false;
        } else if (txtNgayHetHan.getDate() == null) {
            JOptionPane.showMessageDialog(this, "NGÀY KẾT THÚC KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtNgayHetHan.requestFocus();
            return false;
        }
        if (txtTrongLuong.getText().equals("")) {
            MsgBox.alert(this, "TRỌNG LƯỢNG KHÔNG ĐƯỢC TRỐNG!");
            return false;
        }
        if (txtNgayBatDau.getDate().after(txtNgayHetHan.getDate())) {
            MsgBox.alert(this, "NGÀY HẾT HẠN PHẢI SAU NGÀY BẮT ĐẦU!");
            return false;
        }

        return true;
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

    boolean checkkh() {
        // Kiểm tra mã khách hàng
        String khName = txtMaKH.getText().trim();
        String sqlKH = "SELECT COUNT(*) FROM KHACHHANG WHERE MAKH = ? OR SODTKH = ?";
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            PreparedStatement stmtKH = con.prepareStatement(sqlKH);
            stmtKH.setString(1, khName);
            stmtKH.setString(2, khName);
            ResultSet resultSetKH = stmtKH.executeQuery();
            resultSetKH.next();
            int countKH = resultSetKH.getInt(1);
            if (countKH <= 0) {
                JOptionPane.showMessageDialog(this, "MÃ KHÁCH HÀNG HOẶC SỐ ĐIỆN THOẠI KHÁCH HÀNG KHÔNG TỒN TẠI!");
                txtMaKH.grabFocus();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ tại đây, ví dụ: hiển thị thông báo lỗi
        }

        return true;
    }
}
