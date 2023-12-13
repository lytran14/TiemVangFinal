package com.form;

import com.toedter.calendar.JDateChooser;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class XuatHopDongCamDo extends javax.swing.JFrame {

    private FormCamDo formCamDo;

    public XuatHopDongCamDo(FormCamDo form) {
        initComponents();
        setLocationRelativeTo(this);
        formCamDo = form; // Lưu trữ tham chiếu của FormMuaVao
        init(formCamDo);
    }

    void init(FormCamDo form1) {
        JTable tblHDCT = form1.getTblHoaDonCT();
        JTextField txtTenKH = form1.getTxtTenKH();
        JTextField txtCCCD = form1.getSoCCCD();
        JTextField txtMaNV = form1.getTxtMaNV();
        JTextField txtSoDT = form1.getSoDT();
        JTextField txtMaHD = form1.getTxtMaHD();
        JTextField txtlaiXuat = form1.getlaiXuat();
        JTextField txtTienChuoc = form1.getTxtTienPhaiTra();
        JTextField txtThanhToan = form1.getTxtThanhToan();
        JDateChooser txtNgayBatDau = form1.getNgayBatDau();
        JDateChooser txtNgayKetThuc = form1.getNgayKetThuc();

        String html = "<html>";
        html += "<div style='margin-left: 15px;'>";
        html += "<h2 style='text-align: center;'>CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM<br></h2>";
        html += "<p style='font-weight: normal;text-align: center;'>Độc Lập - Tự Do - Hạnh Phúc<br></p>";
        html += "<h1 style='text-align: center;'>HỢP ĐỒNG CẦM ĐỒ<br></h1>";
        html += "<h2 style='text-align: center;'>TIỆM VÀNG<br></h2>";
        html += "<p style='font-weight: normal;text-align: center;'>ĐC: Toà nhà FPT Polytechnic, Đ. Số 22, Thường Thạnh,<br></p>";
        html += "<p style='font-weight: normal;text-align: center;'> Q.Cái Răng, TP.Cần Thơ<br></p>";
        html += "<p style='font-weight: normal;text-align: center;'>SĐT: 0981 725 836<br></p>";
        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
        //thông tin đơn
        html += "<table style='font-weight: normal;'>";
        html += "<tr>";
        html += "<th style='font-weight: normal;'>Mã Hoá Đơn:</th>";
        html += "<td>" + txtMaHD.getText() + "</td>";
        html += "<th style='margin-left: 50px; font-weight: normal;'>Khách Hàng:</th>";
        html += "<td>" + txtTenKH.getText() + "</td>";
        html += "</tr>";
        html += "<tr>";
        html += "<th style='font-weight: normal;'>Số Căn Cước:</th>";
        html += "<td>" + txtCCCD.getText() + "</td>";
        html += "<th style='margin-left: 50px; font-weight: normal;'>Số Điện Thoại:</th>";
        html += "<td>" + txtSoDT.getText() + "</td>";
        html += "</tr>";
        html += "<p style='font-weight: normal;'>Nhân Viên: " + txtMaNV.getText() + "</p>";
        html += "</table>";
//ADD DATE TIME
        Date dd = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss");
        String Date = dateFormat.format(dd);
        String time = dateTime.format(dd);
        html += "<table style='font-weight: normal;'>";
        html += "<tr>";
        html += "<th style='font-weight: normal;'>Date:</th>";
        html += "<td>" + Date + "</td>";
        html += "<th style='margin-left: 130px; font-weight: normal;'>Time:</th>";
        html += "<td>" + time + "</td>";
        html += "</tr>";
        html += "</table>";
        //chi tiết đơn
        html += "<br>";
        html += "<div style='text-align: center;'>THÔNG TIN HOÁ ĐƠN</div><br/>";
        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
        html += "<div style='text-align: center;'>";
        html += "<table style='max-width: 50%;'>";
        html += "<tr>"
                + "<th style='font-weight: normal;'>Tên Sản Phẩm</th>"
                + "<th style='font-weight: normal;'>Khối Lượng</th>"
                + "<th style='font-weight: normal;'>Đơn Giá</th>"
                + "<th style='font-weight: normal;'>Số Tiền Cầm</th>"
                + "<th style='font-weight: normal;'>Lãi Xuất/Ngày</th>"
                + "</tr>";
//thêm sp vào hoá đơn
        DefaultTableModel dt = (DefaultTableModel) tblHDCT.getModel();
        for (int i = 0; i < tblHDCT.getRowCount(); i++) {
            String tenSP = dt.getValueAt(i, 1).toString();
            String khoiluong = dt.getValueAt(i, 2).toString();
            String dongia = dt.getValueAt(i, 3).toString();
            String soTien = dt.getValueAt(i, 4).toString();
            String laixuat = dt.getValueAt(i, 5).toString();
            html += "<tr>";
            html += "<td style='text-align:center;font-weight: normal;'>" + tenSP + "</td>";
            html += "<td style='text-align:center;font-weight: normal;'>" + khoiluong + "</td>";
            html += "<td style='text-align:center;font-weight: normal;'>" + dongia + "</td>";
            html += "<td style='text-align:center;font-weight: normal;'>" + soTien + "</td>";
            html += "<td style='text-align:center;font-weight: normal;'>" + laixuat + "</td>";
            html += "</tr>";
        }
        html += "</tr>";
        html += "</table>";
        html += "</div>";
        ////add tong bill
//        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
//        html += "<p style =text-align: left;font-weight: normal;'>Ngày Bắt Đầu : " + txtNgayBatDau.getDate() + "<br></p>";
//        html += "<p style =text-align: left;font-weight: normal;'>Ngày Kết Thúc : " + txtNgayKetThuc.getDate() + "<br></p>";
        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
        html += "<p style =text-align: right;'><b> Tiền Chuộc : " + txtTienChuoc.getText() + "<br></b></p>";
        html += "<p style =text-align: right;'><b> Tổng Tiền Nhận: " + txtThanhToan.getText() + "<br></b></p>";
        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
        html += "<table style='font-weight: normal;'>";
        html += "<table style='max-width: 50%;'>";
        html += "<tr>"
                + "<th style='font-weight: normal;'>Người Nhận Tiền</th>"
                + "<th style='font-weight: normal;margin-left: 200px;'>Người Phụ Trách</th>";
        html += "</tr>";
        html += "<br>";
        html += "<br>";
        html += "<br>";
        html += "<tr>";
        html += "<td style='text-align:center;font-weight: normal;'>" + txtTenKH.getText() + "</td>";
        html += "<td style='text-align:center;font-weight: normal;margin-left: 200px;'>" + txtMaNV.getText() + "</td>";
        html += "</tr>";
        html += "</table>";
        html += "<br>";
        html += "<br>";
        html += "<br>";
        html += "</div>";
        html += "</html> ";
        blb.setContentType("text/html");
        blb.setText(html);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHuy = new javax.swing.JButton();
        btnInHD = new javax.swing.JButton();
        lblhoadon = new javax.swing.JScrollPane();
        blb = new javax.swing.JEditorPane();
        txtForm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHuy.setText("HUỶ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        getContentPane().add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, 149, -1));

        btnInHD.setText("IN HOÁ ĐƠN");
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });
        getContentPane().add(btnInHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 149, -1));

        lblhoadon.setViewportView(blb);

        getContentPane().add(lblhoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 560));

        txtForm.setBackground(new java.awt.Color(255, 255, 255));
        txtForm.setOpaque(true);
        getContentPane().add(txtForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed
        FilePrintClicked(blb);
    }//GEN-LAST:event_btnInHDActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormCamDo form1 = new FormCamDo(); // Tạo instance của FormMuaVao
                XuatHopDongCamDo xuatCDo = new XuatHopDongCamDo(form1); // Truyền tham chiếu của FormMuaVao
                xuatCDo.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane blb;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnInHD;
    private javax.swing.JScrollPane lblhoadon;
    private javax.swing.JLabel txtForm;
    // End of variables declaration//GEN-END:variables

    public void FilePrintClicked(JEditorPane label) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat format, int pagenum) throws PrinterException {
                if (pagenum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(format.getImageableX(), format.getImageableY());
                float pageWidth = (float) format.getImageableWidth();
                float pageHeight = (float) format.getImageableHeight();
                float imageHeight = (float) label.getHeight();
                float imageWidth = (float) label.getWidth();
                float scaleFactor = Math.min((float) pageWidth / (float) imageWidth, (float) pageHeight / (float) imageHeight);
                int scaledWidth = (int) (((float) imageWidth) * scaleFactor);
                int scaledHeight = (int) (((float) imageHeight) * scaleFactor);
                // Tạo BufferedImage với chế độ màu TYPE_INT_ARGB
                BufferedImage image = new BufferedImage(label.getWidth(), label.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D gg = image.createGraphics();
                // Vẽ JLabel lên BufferedImage
                label.paint(gg);
                // Vẽ BufferedImage lên trang in
                g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean returningResult = job.printDialog();
        if (returningResult) {
            try {
                job.print();
                JOptionPane.showMessageDialog(this, "In Thành Công!");
                dispose();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, "Không Thể In!");
            }
        }
    }
}
