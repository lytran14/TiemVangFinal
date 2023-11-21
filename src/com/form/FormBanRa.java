package com.form;

import Class_DAO.BanRaChiTiet_DAO;
import Class_DAO.BanRa_DAO;
import Class_DAO.SanPham_DAO;
import Class_Model.BanRaChiTiet_Model;
import Class_Model.BanRa_Model;
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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormBanRa extends javax.swing.JFrame {

    BanRa_DAO dao = new BanRa_DAO();
    BanRaChiTiet_DAO ctdaoAO = new BanRaChiTiet_DAO();
    SanPham_DAO spdao = new SanPham_DAO();

    public FormBanRa() {
        initComponents();
        init();
    }

    void init() {
        this.fillTableHoaDon();

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
                    nh.getMAKH(),
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
        } else {
            // Xử lý lỗi khi chỉ số hàng không hợp lệ
            System.err.println("Invalid row index: " + index);
        }
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
            txtMaSP.setText(sanPham.getMASP());
            txtTenSP.setText(sanPham.getTENSP());
            txtDonGia.setText(String.format("%.0f", sanPham.getDONGIABAN()));
            txtLoaiSP.setText(sanPham.getLOAI());
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
                "MÃ HD", "MÃ KH", "NGÀY LẬP", "TỔNG GIÁ TRỊ", "MÃ NV"
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTim))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(txtTim))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(448, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("THÔNG TIN HOÁ ĐƠN"));

        jLabel4.setText("NGÀY");

        jLabel5.setText("Mã HD");

        txtMaHD.setEnabled(false);

        txtKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachHangActionPerformed(evt);
            }
        });

        jLabel6.setText("KHÁCH");

        txtMaNV.setEnabled(false);

        jLabel7.setText("Mã NV");

        btnThemKH.setText("+");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgayInHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHD)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        txtTenSP.setEnabled(false);

        jLabel8.setText("TÊN SP");

        jLabel9.setText("LOẠI VÀNG");

        txtTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrongLuongActionPerformed(evt);
            }
        });

        jLabel10.setText("TRỌNG LƯỢNG");

        txtDonGia.setEnabled(false);

        jLabel11.setText("ĐƠN GIÁ");

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

        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

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

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(255, 0, 51));
        txtTongTien.setEnabled(false);

        jLabel14.setText("THANH TOÁN");

        txtThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtThanhToan.setForeground(new java.awt.Color(255, 0, 51));
        txtThanhToan.setEnabled(false);

        btnXemHoaDon.setText("XEM TRƯỚC HD");

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
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemHoaDon)
                    .addComponent(btnThanhToan))
                .addGap(0, 0, 0))
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
        });
        jScrollPane2.setViewportView(tblHoaDonCT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
        }
        tblHoaDonCT.setVisible(true);
    }//GEN-LAST:event_tblBanRaMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateSelectedRow();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        add();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblHoaDonCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonCTMouseClicked
        int selectedRow = tblHoaDonCT.getSelectedRow();
        if (selectedRow >= 0) {
            fillToFormCT(selectedRow);

        }
        //fillToFormCT(selectedRow);
    }//GEN-LAST:event_tblHoaDonCTMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetAll((DefaultTableModel) tblHoaDonCT.getModel());
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        fillTableHoaDon();
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        if (check()) {
            fillToFormSP();
            return;
        }

    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (!validates()) {
            return;
        } else if (checkkh()) {
            insert();
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
            txtTongTien.setText(String.format("%.0f", thanhTien));
            txtThanhToan.setText(String.format("%.0f", thanhTien));
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "LỖI: TRỌNG LƯỢNG KHÔNG HỢP LỆ!");
        }
    }//GEN-LAST:event_txtTrongLuongActionPerformed

    private void txtKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachHangActionPerformed

    }//GEN-LAST:event_txtKhachHangActionPerformed

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
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtLoaiSP;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private com.toedter.calendar.JDateChooser txtNgayInHD;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtThanhToan;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongTien;
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
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!");
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
        String mabr = autoID("HDB", "mabr", "banra"); // Gọi phương thức autoID để tạo mã KH mới
        kh.setMABR(mabr);
        kh.setMAKH(txtKhachHang.getText());

        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowFormatted = formatter.format(now);
        if (txtNgayInHD.getDate() == null) {
            kh.setNGAYLAP(XDate.toDate(nowFormatted, "yyyy-MM-dd"));
        } else {
            kh.setNGAYLAP(txtNgayInHD.getDate());
        }
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
            MsgBox.confirm(this, "XÁC NHẬN THANH TOÁN THÀNH CÔNG!");
            this.fillTableHoaDon();
            this.reset();
        } catch (Exception e) {
            MsgBox.alert(this, "THANH TOÁN THẤT BẠI!");
            e.printStackTrace();
        }
    }

    private void updateSelectedRow() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonCT.getModel();
        int selectedRow = tblHoaDonCT.getSelectedRow();
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
        txtTongTien.setText("");
        txtThanhToan.setText("");
    }

    public void resetAll(DefaultTableModel model) {
        // Reset các trường dữ liệu
        txtMaHD.setText("");
        txtKhachHang.setText("");
        txtNgayInHD.setDate(null);
        txtMaNV.setText("");
        txtLoaiSP.setText("");
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtTrongLuong.setText("");
        txtDonGia.setText("");
        txtThanhTien.setText("");
        txtTongTien.setText("");
        txtThanhToan.setText("");
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        txtMaSP.setEnabled(true);
        txtTrongLuong.setEnabled(true);
        txtKhachHang.setEnabled(true);
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
        String sqlKH = "SELECT COUNT(*) FROM KHACHHANG WHERE MAKH = ?";
        try {
            Connection con = Class_DBHelder.DBHelder_SQL.getDbConnection();
            PreparedStatement stmtKH = con.prepareStatement(sqlKH);
            stmtKH.setString(1, khName);
            ResultSet resultSetKH = stmtKH.executeQuery();
            resultSetKH.next();
            int countKH = resultSetKH.getInt(1);
            if (countKH <= 0) {
                JOptionPane.showMessageDialog(this, "MÃ KHÁCH HÀNG KHÔNG TỒN TẠI!");
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
