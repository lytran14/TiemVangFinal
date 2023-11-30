package com.form;

import Class_DAO.BanRaChiTiet_DAO;
import Class_DAO.BanRa_DAO;
import Class_DAO.KhachHang_DAO;
import Class_DAO.SanPham_DAO;
import Class_Model.BanRaChiTiet_Model;
import Class_Model.BanRa_Model;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FormBanRa extends javax.swing.JFrame {

    BanRa_DAO dao = new BanRa_DAO();
    BanRaChiTiet_DAO ctdaoAO = new BanRaChiTiet_DAO();
    SanPham_DAO spdao = new SanPham_DAO();
    KhachHang_DAO khdao = new KhachHang_DAO();

    public FormBanRa() {
        initComponents();
        init();
    }
    //in Hoá đơn 

    public JTable getTblBanRa() {
        return tblHoaDonCT;
    }

    public JTextField getTxtMaNV() {
        return txtMaNV;
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

    void init() {
        this.fillTableHoaDon();
        fillTongGiaTri();
        BanRa_Model kh = new BanRa_Model();
        String mabr = autoID("HDB", "mabr", "banra"); // Gọi phương thức autoID để tạo mã KH mới
        kh.setMABR(mabr);
        txtMaHD.setText(mabr);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowFormatted = formatter.format(now);
        if (txtNgayInHD.getDate() == null) {
            txtNgayInHD.setDate(now);
            kh.setNGAYLAP(XDate.toDate(nowFormatted, "yyyy-MM-dd"));
        } else {
            kh.setNGAYLAP(txtNgayInHD.getDate());
        }
        txtMaNV.setText((Auth.user.getMANV()));//lấy mã nhân viên từ user
    }

    void fillTableHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblBanRa.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTim.getText();
            List<BanRa_Model> list = dao.selectByKyword(keyword);
            for (BanRa_Model nh : list) {
                Object[] row = {
                    nh.getMABR(),
                    nh.getTenKH(),
                    nh.getNGAYLAP(),
                    String.format("%.0f", nh.getTONGGIATRI()),
                    nh.getMANV()
                };
                model.addRow(row);
                fillTongGiaTri();
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
            e.printStackTrace();
        }
    }

    void fillTableHoaDonCT(String maBR) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonCT.getModel();
        model.setRowCount(0);
        try {
            List<BanRaChiTiet_Model> list = ctdaoAO.selectsp_br(maBR);
            for (BanRaChiTiet_Model nh : list) {
                Object[] row = {
                    nh.getMASP(),
                    nh.getTENSP(),
                    nh.getLOAISP(),
                    nh.getKHOILUONG(),
                    String.format("%.0f", nh.getDONGIABAN()),
                    String.format("%.0f", nh.getTHANHTIEN()),
                    nh.getMACTBR()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
            System.out.println(e);
        }
    }

    public void fillToFormHoaDon(int index) {
        List<BanRa_Model> list = dao.selectAll();
        if (index >= 0 && index < list.size()) {
            BanRa_Model kh = list.get(index);
            txtKhachHang.setText(kh.getMAKH());
            txtMaHD.setText(kh.getMABR());
            txtMaNV.setText(kh.getMANV());
            txtNgayInHD.setDate(kh.getNGAYLAP());
            txtTongTien.setText(String.format("%.0f", kh.getTONGGIATRI()));
            txtThanhToan.setText(String.format("%.0f", kh.getTONGGIATRI()));

            tblBanRa.setRowSelectionInterval(index, index);
            fillToTenKH();
        } else {
            // Xử lý lỗi khi chỉ số hàng không hợp lệ
            System.err.println("Invalid row index: " + index);
        }
    }

    public class CustomTableModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    void fillTongGiaTri() {
        double tongTien = 0;
        for (int i = 0; i < tblBanRa.getRowCount(); i++) {
            double thanhTienValue = Double.parseDouble(tblBanRa.getValueAt(i, 3).toString());
            tongTien += thanhTienValue;
        }
        txtTongTienHang.setText(String.format("%.0f", tongTien));
    }

    public void fillToFormCT(int selectedRow) {
        List<BanRaChiTiet_Model> list = ctdaoAO.selectsp_br(txtMaHD.getText());
        if (selectedRow >= 0 && selectedRow < list.size()) {
            BanRaChiTiet_Model kh = list.get(selectedRow);
            txtMaSP.setText(kh.getMASP());
            txtTenSP.setText(kh.getTENSP());
            txtTrongLuong.setText(String.valueOf(kh.getKHOILUONG()));
            txtDonGia.setText(String.format("%.0f", kh.getDONGIABAN()));
            txtThanhTien.setText(String.format("%.0f", kh.getTHANHTIEN()));
            // Cộng giá trị thành tiền vào tổng tiền
            double tongTien = 0;
            for (int i = 0; i < tblHoaDonCT.getRowCount(); i++) {
                double thanhTienValue = Double.parseDouble(tblHoaDonCT.getValueAt(i, 5).toString());
                tongTien += thanhTienValue;
            }
            txtTongTien.setText(String.format("%.0f", tongTien));
            txtThanhToan.setText(String.format("%.0f", tongTien));

            txtLoaiSP.setText(kh.getLOAISP());
            tblHoaDonCT.setRowSelectionInterval(selectedRow, selectedRow);
        }
    }

    public void fillToFormSP() {
        String maSP = txtMaSP.getText();
        SanPham_Model sanPham = spdao.selectById(maSP);
        if (sanPham != null) {
            // txtMaSP.setText(sanPham.getMASP());
            txtTenSP.setText(sanPham.getTENSP());
            txtDonGia.setText(String.format("%.0f", sanPham.getDONGIABAN()));
            txtLoaiSP.setText(sanPham.getLOAI());
        }
    }

    public void fillToTenKH() {
        String maKh = txtKhachHang.getText();
        KhachHang_Model khm = khdao.selectBysdt(maKh);
        txtTenKHang.setText(khm.getTenKH());
    }

    void selectDay() {
        LocalDate startDate = txtDateTu.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = txtDateDen.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Để bao gồm cả ngày startDate, bạn cần thay đổi endDate bằng cách thêm 1 ngày.
        //endDate = endDate.plusDays(1);
        Date sqlStartDate = java.sql.Date.valueOf(startDate);
        Date sqlEndDate = java.sql.Date.valueOf(endDate);
        List<BanRa_Model> list = dao.getOrdersByDateRange(sqlStartDate, sqlEndDate);
        DefaultTableModel model = (DefaultTableModel) tblBanRa.getModel();
        model.setRowCount(0);
        try {
            for (BanRa_Model nh : list) {
                Object[] row = {
                    nh.getMABR(),
                    nh.getTenKH(),
                    nh.getNGAYLAP(),
                    String.format("%.0f", nh.getTONGGIATRI()),
                    nh.getMANV()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "LỖI TRUY VẤN DỮ LIỆU!!!");
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBanRa = new javax.swing.JTable();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtDateTu = new com.toedter.calendar.JDateChooser();
        txtDateDen = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        txtTongTienHang = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNgayInHD = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtKhachHang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnThemKH = new javax.swing.JButton();
        txtTenKHang = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
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
        btnReset = new javax.swing.JButton();
        txtLoaiSP = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtThanhToan = new javax.swing.JTextField();
        btnXemHoaDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonCT = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐƠN BÁN RA");

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
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        tblBanRa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ HD", "TÊN KHÁCH HÀNG", "NGÀY LẬP", "TỔNG GIÁ TRỊ", "MÃ NV"
            }
        ));
        tblBanRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanRaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBanRa);

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

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("TỔNG GIÁ TRỊ:");

        txtTongTienHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTongTienHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel16.setText("Từ");

        jLabel17.setText("Đến");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTim)
                                .addGap(46, 46, 46))
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateTu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDateDen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLoc))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTim)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN HOÁ ĐƠN"));

        jLabel4.setText("NGÀY");

        txtNgayInHD.setEnabled(false);

        jLabel5.setText("Mã HD");

        txtMaHD.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaHD.setEnabled(false);

        txtKhachHang.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachHangActionPerformed(evt);
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
                    .addComponent(txtNgayInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenKHang, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHD)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txtNgayInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN SẢN PHẨM"));

        jLabel3.setText("MÃ SP");

        txtMaSP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        txtTenSP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenSP.setEnabled(false);

        jLabel8.setText("TÊN SP");

        jLabel9.setText("LOẠI VÀNG");

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

        jLabel11.setText("ĐƠN GIÁ");

        txtThanhTien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtThanhTien.setEnabled(false);

        jLabel12.setText("THÀNH TIỀN");

        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setText("ĐƠN MỚI");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtLoaiSP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLoaiSP.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSP))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSP))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTrongLuong))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDonGia))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtThanhTien)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoaiSP)))
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnReset))
                .addGap(0, 0, 0))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("THANH TOÁN"));

        jLabel13.setText("TỔNG TIỀN");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(255, 0, 51));
        txtTongTien.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        txtTongTien.setEnabled(false);

        jLabel14.setText("THANH TOÁN");

        txtThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtThanhToan.setForeground(new java.awt.Color(255, 0, 51));
        txtThanhToan.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        txtThanhToan.setEnabled(false);

        btnXemHoaDon.setText("XEM TRƯỚC HD");
        btnXemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemHoaDonActionPerformed(evt);
            }
        });

        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnXemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtTongTien)
                    .addComponent(txtThanhToan))
                .addGap(50, 50, 50))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemHoaDon)
                    .addComponent(btnThanhToan))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblHoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ SP", "TÊN SP", "LOẠI", "TRỌNG LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"
            }
        ));
        tblHoaDonCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonCTMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonCTMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonCT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        new FormKhachHang().setVisible(true);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        fillTableHoaDon();
    }//GEN-LAST:event_btnTimActionPerformed

    private void tblBanRaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanRaMouseClicked
        int selectedRow = tblBanRa.getSelectedRow();
        fillToFormHoaDon(selectedRow);
        reset();
        if (selectedRow >= 0) {
            String maBR = tblBanRa.getValueAt(selectedRow, 0).toString();
            fillTableHoaDonCT(maBR);
            txtKhachHang.setEnabled(false);
            txtNgayInHD.setEnabled(false);
            txtMaSP.setEnabled(false);
            txtTrongLuong.setEnabled(false);
            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            btnThanhToan.setEnabled(false);
            btnXemHoaDon.setEnabled(false);
        }
        tblHoaDonCT.setVisible(true);
    }//GEN-LAST:event_tblBanRaMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateSelectedRow();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        add();
        txtMaSP.setEnabled(true);
        txtTrongLuong.setEnabled(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblHoaDonCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonCTMouseClicked
        int selectedRow = tblHoaDonCT.getSelectedRow();
        if (selectedRow >= 0) {
            fillToFormCT(selectedRow);

        }
    }//GEN-LAST:event_tblHoaDonCTMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetAll((DefaultTableModel) tblHoaDonCT.getModel());
        init();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        fillTableHoaDon();
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        if (check()) {
            fillToFormSP();
            txtMaSP.setEnabled(false);
            txtTrongLuong.setEnabled(true);
            return;
        }

    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (!validates()) {
            return;
        } else if (checkkh()) {
            insert();
            XuatHoaDonBanRa xuatHD = new XuatHoaDonBanRa(this); // Truyền tham chiếu của FormMuaVao
            xuatHD.setVisible(true);
            resetAll((DefaultTableModel) tblHoaDonCT.getModel());
            return;
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrongLuongActionPerformed
        String trongLuongText = txtTrongLuong.getText();

        if (trongLuongText.isEmpty()) {
            MsgBox.alert(this, "TRỌNG LƯỢNG KHÔNG ĐƯỢC TRỐNG!");
            return; // Thoát khỏi phương thức nếu chuỗi rỗng
        }
        try {
            double trongLuong = Double.parseDouble(trongLuongText);
            double donGia = Double.parseDouble(txtDonGia.getText());
            double thanhTien = trongLuong * donGia;
            txtThanhTien.setText(String.format("%.0f", thanhTien));
            if (trongLuong < 0) {
                txtThanhTien.setText("");
                MsgBox.alert(this, "TRỌNG LƯỢNG KHÔNG ĐƯỢC NHỎ HƠN 0!");
                return; // Thoát khỏi phương thức nếu giá trị nhỏ hơn 0
            }

        } catch (NumberFormatException e) {
            MsgBox.alert(this, "LỖI: TRỌNG LƯỢNG KHÔNG HỢP LỆ!");
        }
    }//GEN-LAST:event_txtTrongLuongActionPerformed

    private void txtKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachHangActionPerformed
        if (checkkh()) {
            fillToTenKH();
            return;
        }
    }//GEN-LAST:event_txtKhachHangActionPerformed

    private void tblHoaDonCTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonCTMousePressed
        if (evt.getClickCount() == 2) {
            int selectedRow = tblHoaDonCT.getSelectedRow();
            int tlColumnIndex = tblHoaDonCT.getColumnModel().getColumnIndex("TRỌNG LƯỢNG");

            if (tblHoaDonCT.getSelectedColumn() == tlColumnIndex) {
                tblHoaDonCT.setEnabled(true);
                tblHoaDonCT.setRowSelectionAllowed(true); // Cho phép chọn hàng
                tblHoaDonCT.setColumnSelectionAllowed(true); // Cho phép chọn cột
            } else {
                MsgBox.alert(this, "CHỈ ĐƯỢC PHÉP CẬP NHẬT 'TRỌNG LƯỢNG' CỦA SẢN PHẨM!");

                DefaultTableModel model = (DefaultTableModel) tblHoaDonCT.getModel();
                model.setColumnCount(tblHoaDonCT.getColumnCount()); // Đảm bảo số lượng cột không thay đổi

                tblHoaDonCT.setEnabled(true);
                tblHoaDonCT.setRowSelectionAllowed(true); // Cho phép chọn hàng
                tblHoaDonCT.setColumnSelectionAllowed(true); // Cho phép chọn cột

                tblHoaDonCT.getColumnModel().getColumn(tlColumnIndex).setCellEditor(new DefaultCellEditor(new JTextField()));

                // Kiểm tra chỉ mục hàng có hợp lệ hay không
                if (selectedRow >= 0 && selectedRow < tblHoaDonCT.getRowCount()) {
                    tblHoaDonCT.setRowSelectionInterval(selectedRow, selectedRow); // Chọn lại hàng đã chọn trước đó
                }
            }
        }
    }//GEN-LAST:event_tblHoaDonCTMousePressed

    private void btnXemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemHoaDonActionPerformed
        XuatHoaDonBanRa xuatHD = new XuatHoaDonBanRa(this); // Truyền tham chiếu của FormMuaVao
        xuatHD.setVisible(true);
    }//GEN-LAST:event_btnXemHoaDonActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        selectDay();
        fillTongGiaTri();
    }//GEN-LAST:event_btnLocActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBanRa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXemHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JTable tblBanRa;
    private javax.swing.JTable tblHoaDonCT;
    private com.toedter.calendar.JDateChooser txtDateDen;
    private com.toedter.calendar.JDateChooser txtDateTu;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtLoaiSP;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private com.toedter.calendar.JDateChooser txtNgayInHD;
    private javax.swing.JTextField txtTenKHang;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtThanhToan;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JLabel txtTongTienHang;
    private javax.swing.JTextField txtTrongLuong;
    // End of variables declaration//GEN-END:variables

    void add() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonCT.getModel();
        // Xóa hết các dòng trong bảng
        String maSP = txtMaSP.getText();
        String tenSP = txtTenSP.getText();
        double trongLuong = 0.0;
        double donGia = 0.0;
        try {
            trongLuong = Double.parseDouble(txtTrongLuong.getText());
            donGia = Double.parseDouble(txtDonGia.getText());
        } catch (NumberFormatException e) {
            // Xử lý khi chuỗi không thể chuyển đổi thành số
            JOptionPane.showMessageDialog(this, "TRỌNG LƯỢNG VUI LÒNG NHẬP SỐ!");
            return;
        }
        double thanhTien = trongLuong * donGia;
        txtThanhTien.setText(String.format("%.0f", thanhTien));
        String loaiSP = txtLoaiSP.getText();
        String formattedDonGia = String.format("%.0f", donGia);
        String formattedThanhTien = String.format("%.0f", thanhTien);
        Object[] rowData = {maSP, tenSP, loaiSP, trongLuong, formattedDonGia, formattedThanhTien};
        model.addRow(rowData);
        //tblHoaDonCT.setVisible(true);
        reset();
        // Cộng giá trị thành tiền vào tổng tiền
        double tongTien = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double thanhTienValue = Double.parseDouble(model.getValueAt(i, 5).toString());
            tongTien += thanhTienValue;
        }
        txtTongTien.setText(String.format("%.0f", tongTien));
        txtThanhToan.setText(String.format("%.0f", tongTien));
    }

    private BanRa_Model getFormHoaDon() {
        BanRa_Model kh = new BanRa_Model();
        kh.setMABR(txtMaHD.getText());
        kh.setMAKH(txtKhachHang.getText());
        kh.setNGAYLAP(txtNgayInHD.getDate());
        if (txtMaNV.getText().equals("")) {
            kh.setMANV(Auth.user.getMANV());
        } else {
            kh.setMANV(txtMaNV.getText());
        }
        kh.setTONGGIATRI(Double.parseDouble(txtTongTien.getText()));
        return kh;
    }

    void insert() {
        BanRa_Model kh = getFormHoaDon();
        try {
            dao.insert(kh);
            String maBR = kh.getMABR();
            DefaultTableModel model = (DefaultTableModel) tblHoaDonCT.getModel();
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maSP = model.getValueAt(i, 0).toString();
                double trongLuong = Double.parseDouble(model.getValueAt(i, 3).toString());
                double donGia = Double.parseDouble(model.getValueAt(i, 4).toString());
                double thanhTien = Double.parseDouble(model.getValueAt(i, 5).toString());
                // Tạo đối tượng hoá đơn chi tiết
                BanRaChiTiet_Model ct = new BanRaChiTiet_Model();
                ct.setMABR(maBR);
                ct.setMASP(maSP);
                ct.setKHOILUONG(trongLuong);
                ct.setDONGIABAN(donGia);
                ct.setTHANHTIEN(thanhTien);
                ctdaoAO.insert(ct);
            }
            int dialogResult = JOptionPane.showConfirmDialog(this, "XÁC NHẬN THANH TOÁN! ?",
                    "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                //System.exit(
                //MsgBox.confirm(this, "XÁC NHẬN THANH TOÁN!");
                this.fillTableHoaDon();
                this.reset();
                init();
            }
        } catch (Exception e) {
            MsgBox.alert(this, "THANH TOÁN THẤT BẠI!");
            e.printStackTrace();
        }

    }

    private void updateSelectedRow() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonCT.getModel();
        int selectedRow = tblHoaDonCT.getSelectedRow();
        // Check if the selected column is the "Trọng lượng" column (assuming it's column index 3)
        if (tblHoaDonCT.getSelectedColumn() != 3) {
            JOptionPane.showMessageDialog(this, "CHỈ ĐƯỢC PHÉP CẬP NHẬT 'TRỌNG LƯỢNG' CỦA SẢN PHẨM!");
            return;
        }
        double trongLuong = Double.parseDouble(tblHoaDonCT.getValueAt(selectedRow, 3).toString());
        double donGia = Double.parseDouble(tblHoaDonCT.getValueAt(selectedRow, 4).toString());
        double thanhTien = trongLuong * donGia;
        String formattedThanhTien = String.format("%.0f", thanhTien);
        tblHoaDonCT.setValueAt(trongLuong, selectedRow, 3); // Cập nhật trọng lượng trong bảng
        tblHoaDonCT.setValueAt(formattedThanhTien, selectedRow, 5); // Cập nhật thành tiền trong bảng
        // Cộng giá trị thành tiền vào tổng tiền
        double tongTien = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double thanhTienValue = Double.parseDouble(model.getValueAt(i, 5).toString());
            tongTien += thanhTienValue;
        }
        txtTongTien.setText(String.format("%.0f", tongTien));
        txtThanhToan.setText(String.format("%.0f", tongTien));
        MsgBox.alert(this, "CẬP NHẬT TRỌNG LƯỢNG THÀNH CÔNG!");
    }

    void delete() {
        DefaultTableModel tbModel = (DefaultTableModel) tblHoaDonCT.getModel();
        int vitri = tblHoaDonCT.getSelectedRow();
        if (vitri >= 0) {
            tbModel.removeRow(vitri);
            for (int i = 0; i < tblHoaDonCT.getRowCount(); i++) {
                tblHoaDonCT.setValueAt(1 + 1, i, 1);
            }
        }// Cộng giá trị thành tiền vào tổng tiền
        double tongTien = 0;
        for (int i = 0; i < tbModel.getRowCount(); i++) {
            double thanhTienValue = Double.parseDouble(tbModel.getValueAt(i, 5).toString());
            tongTien += thanhTienValue;
        }
        txtTongTien.setText(String.format("%.0f", tongTien));
        txtThanhToan.setText(String.format("%.0f", tongTien));

    }

    public void reset() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtTrongLuong.setText("");
        txtDonGia.setText("");
        txtLoaiSP.setText("");
        txtThanhTien.setText("");
    }

    public void resetAll(DefaultTableModel model) {
        // Reset các trường dữ liệu
        txtMaHD.setText("");
        txtTenKHang.setText("");
        txtKhachHang.setText("");
        txtNgayInHD.setDate(null);
        txtMaNV.setText("");
        txtLoaiSP.setText("");
        txtTongTien.setText("");
        txtThanhToan.setText("");
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        txtMaSP.setEnabled(true);
        txtTrongLuong.setEnabled(true);
        txtKhachHang.setEnabled(true);
        btnThanhToan.setEnabled(true);
        btnXemHoaDon.setEnabled(true);
        reset();
        // Ẩn bảng tblHoaDonCT
        model.setRowCount(0);
    }
//BẮT LỖI

    boolean validates() {
        if (txtKhachHang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "MÃ KHÁCH HÀNG KHÔNG ĐƯỢC TRỐNG!! ", "CHÚ Ý!!!", 1);
            txtKhachHang.requestFocus();
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
        String khName = txtKhachHang.getText().trim();
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
                txtKhachHang.grabFocus();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ tại đây, ví dụ: hiển thị thông báo lỗi
        }

        return true;
    }
}
