package com.form;

import Class_DAO.KhachHang_DAO;
import Class_DAO.MuaVaoChiTiet_DAO;
import Class_DAO.MuaVao_DAO;
import Class_DAO.SanPham_DAO;
import Class_Model.BanRa_Model;
import Class_Model.KhachHang_Model;
import Class_Model.MuaVaoChiTiet_Model;
import Class_Model.MuaVao_Model;
import Class_Model.SanPham_Model;
import Class_Utils.Auth;
import Class_Utils.MsgBox;
import Class_Utils.XDate;
import static com.model.AutoString.autoID;
import com.model.Excel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormMuaVao extends javax.swing.JFrame {

    MuaVao_DAO dao = new MuaVao_DAO();
    MuaVaoChiTiet_DAO ctdaoAO = new MuaVaoChiTiet_DAO();
    SanPham_DAO spdao = new SanPham_DAO();
    KhachHang_DAO khdao = new KhachHang_DAO();
    DecimalFormat dcf = new DecimalFormat("###,###");

    public JTable getTblHDCT() {
        return tblHDCT;
    }

    public JTextField getTxtTenKH() {
        return txtTenKHang;
    }

    public JTextField getTxtMaHD() {
        return txtMaHD;
    }

    public JTextField getTxtThanhToan() {
        return txtThanhToan;
    }

    public JTextField getTxtTongTien() {
        return txtTongTien;
    }

    public JTextField getTxtMaNV() {
        return txtMaNV;
    }

    public FormMuaVao() {
        initComponents();
        init();
    }

    void init() {
        this.fillTableHD();
        fillTongGiaTri();
        MuaVao_Model kh = new MuaVao_Model();
        String mamv = autoID("HDM", "mamv", "muavao"); // Gọi phương thức autoID để tạo mã KH mới
        kh.setMaMV(mamv);
        txtMaHD.setText(mamv);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowFormatted = formatter.format(now);
        if (txtNgay.getDate() == null) {
            txtNgay.setDate(now);
            kh.setNgayLap(XDate.toDate(nowFormatted, "yyyy-MM-dd"));
        } else {
            kh.setNgayLap(txtNgay.getDate());
        }
        txtNgayTu.setDate(now);
        txtNgayDen.setDate(now);
        txtMaNV.setText((Auth.user.getMANV()));//lấy mã nhân viên từ user
    }
//LOAD DỮ LIỆU LÊN TABLE HOÁ ĐƠN MUA

    void fillTableHD() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTim.getText();
            List<MuaVao_Model> list = dao.selectByKyword(keyword);
            double tongTien = 0; // Tạo biến tổng tiền để tính toán
            for (MuaVao_Model nh : list) {
                Object[] row = {
                    nh.getMaMV(),
                    nh.getTenkh(),
                    nh.getNgayLap(),
                    dcf.format(nh.getTongGiaTri()), //nh.getMaNV()
                };
                model.addRow(row);
                double thanhTienValue = nh.getTongGiaTri(); // Lấy giá trị tổng tiền từ đối tượng MuaVao_Model
                tongTien += thanhTienValue; // Cộng giá trị tổng tiền vào biến tongTien
            }
            txtTongTienHang.setText(dcf.format(tongTien)); // Cập nhật giá trị tổng tiền
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
            e.printStackTrace();
        }
    }
//LOAD DỮ LIỆU LÊN TABLE ĐƠN MUA CHI TIẾT

    void fillTableHoaDonCT(String maBR) {
        DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
        model.setRowCount(0);
        try {
            List<MuaVaoChiTiet_Model> list = ctdaoAO.selectsp_br(maBR);
            for (MuaVaoChiTiet_Model nh : list) {
                Object[] row = {
                    nh.getMASP(),
                    nh.getLOAISP(),
                    nh.getKHOILUONG(),
                    dcf.format(nh.getDONGIAMUA()),
                    dcf.format(nh.getTHANHTIEN()),
                    nh.getMACTMV()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
            e.printStackTrace();
        }
    }
//LOAD DỮ LIỆU TỪ TABLE HOÁ ĐƠN LÊN CÁC TXT FORM

    public void fillToFormHoaDon(int index) {
        List<MuaVao_Model> list = dao.selectAll();
        if (index >= 0 && index < list.size()) {
            MuaVao_Model hoaDon = list.get(index);
            txtKhach.setText(hoaDon.getMaKH());
            txtMaHD.setText(hoaDon.getMaMV());
            txtMaNV.setText(hoaDon.getMaNV());
            txtNgay.setDate(hoaDon.getNgayLap());
            tblHoaDon.setRowSelectionInterval(index, index);
            fillToTenKH();
            double tongGiaTri = hoaDon.getTongGiaTri();
            txtTongTien.setText(dcf.format(tongGiaTri));
            txtThanhToan.setText(dcf.format(tongGiaTri));
        } else {
            // Xử lý lỗi khi chỉ số hàng không hợp lệ
            System.err.println("Invalid row index: " + index);
        }
    }
//LOAD DỮ LIỆU TỪ TABLE ĐƠN CHI TIẾT LÊN CÁC TXT FORM 

    public void fillToFormCT(int selectedRow) {
        List<MuaVaoChiTiet_Model> list = ctdaoAO.selectsp_br(txtMaHD.getText());
        if (selectedRow >= 0 && selectedRow < list.size()) {
            MuaVaoChiTiet_Model kh = list.get(selectedRow);
            txtMaSP.setText(kh.getMASP());
            txtTrongLuong.setText(String.valueOf(kh.getKHOILUONG()));

            txtDonGia.setText(dcf.format(kh.getDONGIAMUA()));
            txtThanhTien.setText(dcf.format(kh.getTHANHTIEN()));

            // Cộng giá trị thành tiền vào tổng tiền
            double tongTien = 0;
            for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                String thanhTienStr = tblHDCT.getValueAt(i, 4).toString();
                if (isNumber(thanhTienStr)) {
                    double thanhTienValue = Double.parseDouble(thanhTienStr.replace(",", ""));
                    tongTien += thanhTienValue;
                }
            }
//            txtTongTien.setText(dcf.format(tongTien));
//            txtThanhToan.setText(dcf.format(tongTien));
            txtLoaiSP.setText(kh.getLOAISP());
            tblHDCT.setRowSelectionInterval(selectedRow, selectedRow);
        }
    }
// LOAD THÔNG TIN SẢN PHẦM ĐƯỢC CHỌN

    public void fillToFormSP() {
        String maSP = txtMaSP.getText();
        SanPham_Model sanPham = spdao.selectById(maSP);
        if (sanPham != null) {
            // txtMaSP.setText(sanPham.getMASP());
            txtDonGia.setText(String.format("%.0f", sanPham.getDONGIABAN()));
            txtLoaiSP.setText(sanPham.getLOAI());
        }
    }
// LOAD THÔNG TIN KHÁCH HÀNG ĐƯỢC CHỌN

    public void fillToTenKH() {
        String maKh = txtKhach.getText();
        KhachHang_Model khm = khdao.selectBysdt(maKh);
        txtTenKHang.setText(khm.getTenKH());
    }

    void selectDay() {
        LocalDate startDate = txtNgayTu.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = txtNgayDen.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Để bao gồm cả ngày startDate, bạn cần thay đổi endDate bằng cách thêm 1 ngày.
        //endDate = endDate.plusDays(1);
        Date sqlStartDate = java.sql.Date.valueOf(startDate);
        Date sqlEndDate = java.sql.Date.valueOf(endDate);
        List<MuaVao_Model> list = dao.getOrdersByDateRange(sqlStartDate, sqlEndDate);
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            for (MuaVao_Model nh : list) {
                Object[] row = {
                    nh.getMaMV(),
                    nh.getTenkh(),
                    nh.getNgayLap(),
                    dcf.format(nh.getTongGiaTri()), //nh.getMaNV()
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
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            Object value = tblHoaDon.getValueAt(i, 3);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTongTienHang = new javax.swing.JLabel();
        txtNgayTu = new com.toedter.calendar.JDateChooser();
        txtNgayDen = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        btnXuatMuaVao = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNgay = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtKhach = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnThemKH = new javax.swing.JButton();
        txtTenKHang = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTrongLuong = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtLoaiSP = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        btnResset = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        txtThanhToan = new javax.swing.JTextField();
        btnXemTruoc = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐƠN MUA VÀO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ HD", "TÊN KHÁCH HÀNG", "NGÀY LẬP", "TỔNG GIÁ TRỊ"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        btnTim.setText("TÌM");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel2.setText("TÌM KIẾM");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("TỔNG GIÁ TRỊ:");

        txtTongTienHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTongTienHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel3.setText("Từ");

        jLabel8.setText("Đến");

        btnLoc.setText("LỌC");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnXuatMuaVao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatMuaVao.setText("XUẤT FILE");
        btnXuatMuaVao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatMuaVaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXuatMuaVao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayTu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLoc))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTim))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTim))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLoc, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(txtNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnXuatMuaVao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN HOÁ ĐƠN"));

        jLabel4.setText("NGÀY");

        txtNgay.setBackground(new java.awt.Color(255, 255, 255));
        txtNgay.setEnabled(false);

        jLabel5.setText("Mã HD");

        txtMaHD.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaHD.setEnabled(false);
        txtMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDActionPerformed(evt);
            }
        });

        txtKhach.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtKhach.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachActionPerformed(evt);
            }
        });

        jLabel6.setText("KHÁCH HÀNG");

        txtMaNV.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaNV.setEnabled(false);

        jLabel7.setText("Mã NV");

        btnThemKH.setText("+");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        txtTenKHang.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenKHang.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenKHang, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV))
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN SẢN PHẨM"));

        jLabel9.setText("MÃ SẢN PHẨM");

        txtTrongLuong.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTrongLuong.setEnabled(false);
        txtTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrongLuongActionPerformed(evt);
            }
        });

        jLabel10.setText("TRỌNG LƯỢNG");

        txtDonGia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDonGia.setEnabled(false);

        jLabel11.setText("ĐƠN GIÁ/CHỈ");

        txtThanhTien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtThanhTien.setEnabled(false);

        jLabel12.setText("THÀNH TIỀN");

        btnAdd.setText("THÊM");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("SỬA");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("XÓA");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel15.setText("LOẠI SẢN PHẨM");

        txtLoaiSP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLoaiSP.setEnabled(false);

        txtMaSP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        btnResset.setText("TẠO HOÁ ĐƠN MỚI");
        btnResset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRessetActionPerformed(evt);
            }
        });

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoaiSP))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDonGia)
                            .addComponent(txtTrongLuong)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnResset))
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnResset))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("THANH TOÁN"));

        jLabel13.setText("TỔNG TIỀN");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongTien.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        txtTongTien.setEnabled(false);

        jLabel14.setText("THANH TOÁN");

        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtThanhToan.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        txtThanhToan.setEnabled(false);
        txtThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhToanActionPerformed(evt);
            }
        });

        btnXemTruoc.setText("XEM TRƯỚC HOÁ ĐƠN");
        btnXemTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemTruocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnXemTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtThanhToan))))
                .addGap(40, 40, 40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnXemTruoc))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ SẢN PHẨM", "LOẠI VÀNG", "TRỌNG LƯỢNG", "ĐƠN GIÁ", "THÀNH TIÊN"
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
        jScrollPane2.setViewportView(tblHDCT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int selectedRow = tblHoaDon.getSelectedRow();
        fillToFormHoaDon(selectedRow);
        reset();
        if (selectedRow >= 0) {
            String maBR = tblHoaDon.getValueAt(selectedRow, 0).toString();
            fillTableHoaDonCT(maBR);
            txtKhach.setEnabled(false);
            txtNgay.setEnabled(false);
            txtMaSP.setEnabled(false);
            txtTrongLuong.setEnabled(false);
            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            btnThanhToan.setEnabled(false);
        }
        tblHDCT.setVisible(true);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        fillTableHD();
    }//GEN-LAST:event_txtTimActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        fillTableHD();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        selectDay();
        fillTongGiaTri();
    }//GEN-LAST:event_btnLocActionPerformed

    private void txtMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDActionPerformed

    }//GEN-LAST:event_txtMaHDActionPerformed

    private void txtKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachActionPerformed
        if (checkkh()) {
            fillToTenKH();
            return;
        }
    }//GEN-LAST:event_txtKhachActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        new FormKhachHang().setVisible(true);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void txtTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrongLuongActionPerformed
        String trongLuongText = txtTrongLuong.getText();
        if (trongLuongText.isEmpty()) {
            MsgBox.alert(this, "TRỌNG LƯỢNG KHÔNG ĐƯỢC TRỐNG!");
            return; // Thoát khỏi phương thức nếu chuỗi rỗng
        }
        try {
            double trongLuong = Double.parseDouble(trongLuongText);
            double donGia = Double.parseDouble(txtDonGia.getText());
            double thanhTien = (trongLuong * donGia) / 3.75;
            txtThanhTien.setText(dcf.format(thanhTien));
            if (trongLuong < 0) {

                MsgBox.alert(this, "TRỌNG LƯỢNG KHÔNG ĐƯỢC NHỎ HƠN 0!");
                txtTrongLuong.setText("");
                txtThanhTien.setText("");
                return; // Thoát khỏi phương thức nếu giá trị nhỏ hơn 0
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "LỖI: TRỌNG LƯỢNG KHÔNG HỢP LỆ!");
            txtTrongLuong.setText("");
        }
    }//GEN-LAST:event_txtTrongLuongActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!validatesSP()) {
            return;
        } else if (check()) {
            add();
            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
        }
        txtMaSP.setEnabled(true);
        txtTrongLuong.setEnabled(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateSelectedRow();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        if (check()) {
            fillToFormSP();
            txtMaSP.setEnabled(false);
            txtTrongLuong.setEnabled(true);
            return;
        }
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnRessetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRessetActionPerformed
        resetAll((DefaultTableModel) tblHDCT.getModel());
        init();
    }//GEN-LAST:event_btnRessetActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (!validates()) {
            return;
        } else if (checkkh()) {
            insert();
            resetAll((DefaultTableModel) tblHDCT.getModel());
            return;
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhToanActionPerformed

    private void btnXemTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemTruocActionPerformed
        XuatHoaDonMua xuatHD = new XuatHoaDonMua(this); // Truyền tham chiếu của FormMuaVao
        xuatHD.setVisible(true);
    }//GEN-LAST:event_btnXemTruocActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        int selectedRow = tblHDCT.getSelectedRow();
        if (selectedRow >= 0) {
            fillToFormCT(selectedRow);
        }

    }//GEN-LAST:event_tblHDCTMouseClicked

    private void tblHDCTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMousePressed
        if (evt.getClickCount() == 2) {

            int selectedRow = tblHDCT.getSelectedRow();
            int tlColumnIndex = tblHDCT.getColumnModel().getColumnIndex("TRỌNG LƯỢNG");

            if (tblHDCT.getSelectedColumn() == tlColumnIndex) {
                tblHDCT.setEnabled(true);
                tblHDCT.setRowSelectionAllowed(true); // Cho phép chọn hàng
                tblHDCT.setColumnSelectionAllowed(true); // Cho phép chọn cột
            } else {
                MsgBox.alert(this, "CHỈ ĐƯỢC PHÉP CẬP NHẬT 'TRỌNG LƯỢNG' CỦA SẢN PHẨM!");

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new FormSanPham().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void excelProducts() throws IOException {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        Excel.outExcel(model);
    }

    private void btnXuatMuaVaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatMuaVaoActionPerformed

        try {
            excelProducts();
        } catch (IOException ex) {
            //      Logger.getLogger(FormThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnXuatMuaVaoActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMuaVao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnResset;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXemTruoc;
    private javax.swing.JButton btnXuatMuaVao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtKhach;
    private javax.swing.JTextField txtLoaiSP;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private com.toedter.calendar.JDateChooser txtNgay;
    private com.toedter.calendar.JDateChooser txtNgayDen;
    private com.toedter.calendar.JDateChooser txtNgayTu;
    private javax.swing.JTextField txtTenKHang;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtThanhToan;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JLabel txtTongTienHang;
    private javax.swing.JTextField txtTrongLuong;
    // End of variables declaration//GEN-END:variables
//THÊM CÁC SẢN PHẨM
    void add() {
        DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
        // Xóa hết các dòng trong bảng
        String maSP = txtMaSP.getText();
        double trongLuong = 0.0;
        double donGia = 0.0;
        try {
            trongLuong = Double.parseDouble(txtTrongLuong.getText().replace(",", ""));
            donGia = Double.parseDouble(txtDonGia.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            // Xử lý khi chuỗi không thể chuyển đổi thành số
            MsgBox.alert(this, "THIẾU TRỌNG LƯỢNG!! ENTER SẢN PHẨM ĐỂ NHẬP TRỌNG LƯỢNG!!");
            return;
        }
        double thanhTien = (trongLuong * donGia) / 3.75;
        txtThanhTien.setText(dcf.format(thanhTien));
        String loaiSP = txtLoaiSP.getText();
        String formattedDonGia = dcf.format(donGia);
        String formattedThanhTien = dcf.format(thanhTien);
        Object[] rowData = {maSP, loaiSP, trongLuong, formattedDonGia, formattedThanhTien};
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
        txtTongTien.setText(dcf.format(tongTien));
        txtThanhToan.setText(dcf.format(tongTien));

    }
//CẬP NHẬT TRỌNG LƯỢNG CÁC SẢN PHẨM

    private void updateSelectedRow() {

        DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
        int selectedRow = tblHDCT.getSelectedRow();
        // Check if the selected column is the "Trọng lượng" column (assuming it's column index 3)
        if (tblHDCT.getSelectedColumn() != 2) {
            JOptionPane.showMessageDialog(this, "CHỈ ĐƯỢC PHÉP CẬP NHẬT 'TRỌNG LƯỢNG' CỦA SẢN PHẨM!");
            return;
        }
        String trongLuongStr = tblHDCT.getValueAt(selectedRow, 2).toString().replace(",", "");
        // Kiểm tra nếu trongLuongStr không phải là số và không nhỏ hơn 0
        if (!trongLuongStr.matches("\\d+(\\.\\d+)?") || Double.parseDouble(trongLuongStr) < 0) {
            JOptionPane.showMessageDialog(this, "TRỌNG LƯỢNG PHẢI LÀ SỐ VÀ KHÔNG ĐƯỢC NHỎ HƠN 0!");
            return;
        }
        double trongLuong = Double.parseDouble(trongLuongStr);
        double donGia = Double.parseDouble(tblHDCT.getValueAt(selectedRow, 3).toString().replace(",", ""));
        double thanhTien = (trongLuong * donGia) / 3.75;
        String formattedThanhTien = dcf.format(thanhTien);
        tblHDCT.setValueAt(trongLuong, selectedRow, 2); // Cập nhật trọng lượng trong bảng
        tblHDCT.setValueAt(formattedThanhTien, selectedRow, 4); // Cập nhật thành tiền trong bảng
        // Cộng giá trị thành tiền vào tổng tiền
        double tongTien = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String thanhTienStr = model.getValueAt(i, 4).toString().replace(",", "");
            double thanhTienValue = Double.parseDouble(thanhTienStr);
            tongTien += thanhTienValue;
        }
        txtTongTien.setText(dcf.format(tongTien));
        txtThanhToan.setText(dcf.format(tongTien));
        MsgBox.alert(this, "CẬP NHẬT TRỌNG LƯỢNG THÀNH CÔNG!");
    }
//XOÁ CÁC SÀN PHẨM ĐƯỢC CHỌN

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
            double thanhTienValue = Double.parseDouble(tbModel.getValueAt(i, 4).toString());
            tongTien += thanhTienValue;
        }
        txtTongTien.setText(dcf.format(tongTien));
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

    //GETFORM CHO HOÁ ĐƠN
    private MuaVao_Model getFormHoaDon() {
        MuaVao_Model kh = new MuaVao_Model();
        kh.setMaMV(txtMaHD.getText());
        String tenKhachHang = txtTenKHang.getText();
        String maKhachHang = findMaKhachHangByTen(tenKhachHang); // Hàm findMaKhachHangByTen() là hàm tìm mã khách hàng từ tên khách hàng 
        kh.setMaKH(maKhachHang);
        kh.setNgayLap(txtNgay.getDate());
        if (txtMaNV.getText().equals("")) {
            kh.setMaNV(Auth.user.getMANV());
        } else {
            kh.setMaNV(txtMaNV.getText());
        }
        String tongTienStr = txtTongTien.getText().replace(",", "");
        double tongTien = 0.0;
        tongTien = Double.parseDouble(tongTienStr);
        kh.setTongGiaTri(tongTien);
        return kh;
    }

    //LƯU VÀO HOÁ ĐƠN VÀ HOÁ ĐƠN CT
    void insert() {
        MuaVao_Model kh = getFormHoaDon();
        try {
            dao.insert(kh);
            String maMV = kh.getMaMV();
            DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maSP = model.getValueAt(i, 0).toString();
                double trongLuong = Double.parseDouble(model.getValueAt(i, 2).toString().replace(",", ""));
                double donGia = Double.parseDouble(model.getValueAt(i, 3).toString().replace(",", ""));
                double thanhTien = Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", ""));
                // Tạo đối tượng hoá đơn chi tiết
                MuaVaoChiTiet_Model ct = new MuaVaoChiTiet_Model();
                ct.setMAMV(maMV);
                ct.setMASP(maSP);
                ct.setKHOILUONG(trongLuong);
                ct.setDONGIAMUA(donGia);
                ct.setTHANHTIEN(thanhTien);
                ctdaoAO.insert(ct);
            }
            MsgBox.confirm(this, "XÁC NHẬN THANH TOÁN!");
            this.fillTableHD();
            XuatHoaDonMua xuatHD = new XuatHoaDonMua(this); // Truyền tham chiếu của FormMuaVao
            xuatHD.setVisible(true);
            this.reset();
        } catch (Exception e) {
            MsgBox.alert(this, "THANH TOÁN THẤT BẠI!");
            e.printStackTrace();
        }
    }
//RESET

    public void reset() {
        txtMaSP.setText("");
        txtTrongLuong.setText("");
        txtDonGia.setText("");
        txtLoaiSP.setText("");
        txtThanhTien.setText("");
    }

    public void resetAll(DefaultTableModel model) {
        // Reset các trường dữ liệu
        txtMaHD.setText("");
        txtTenKHang.setText("");
        txtKhach.setText("");
        txtNgay.setDate(null);
        txtMaNV.setText("");
        txtLoaiSP.setText("");
        txtThanhToan.setText("");
        txtTongTien.setText("");
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        txtMaSP.setEnabled(true);
        btnThanhToan.setEnabled(true);
        btnXemTruoc.setEnabled(true);
        txtKhach.setEnabled(true);
        reset();
        // Ẩn bảng tblHoaDonCT
        model.setRowCount(0);
    }

    //BẮT LỖI
    boolean validates() {
        if (txtKhach.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "MÃ KHÁCH HÀNG KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtKhach.requestFocus();
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
        return true;
    }

    boolean check() {
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
        String khName = txtKhach.getText().trim();
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
                txtKhach.grabFocus();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ tại đây, ví dụ: hiển thị thông báo lỗi
        }

        return true;
    }
}
