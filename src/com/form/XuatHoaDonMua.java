package com.form;

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

public class XuatHoaDonMua extends javax.swing.JFrame {

    private FormMuaVao formMuaVao;

    public XuatHoaDonMua(FormMuaVao form) {
        initComponents();
        setLocationRelativeTo(this);
        formMuaVao = form; // Lưu trữ tham chiếu của FormMuaVao
        init(formMuaVao);
    }

    void init(FormMuaVao form1) {
        JTable tblHDCT = form1.getTblHDCT();
        JTextField txtTenKH = form1.getTxtTenKH();
        JTextField txtMaHD = form1.getTxtMaHD();
        JTextField txtTenNV = form1.getTxtMaNV();
        JTextField txtThanhToan = form1.getTxtThanhToan();
        JTextField txtTongTien = form1.getTxtTongTien();
        String html = "<html>";
        html += "<div style='margin-left: 15px;'>";
        html += "<h2 style='text-align: center;'>TIỆM VÀNG CHƯA CÓ TÊN</h2>";
        html += "<p style='font-weight: normal;text-align: center;'>ĐC: Toà nhà FPT Polytechnic, Đ. Số 22, Thường Thạnh,<br></p>";
        html += "<p style='font-weight: normal;text-align: center;'> Q.Cái Răng, TP.Cần Thơ<br></p>";
        html += "<p style='font-weight: normal;text-align: center;'>SĐT: 0981 725 836<br></p>";
        //thông tin đơn
        html += "<table style='font-weight: normal;'>";
        html += "<tr>";
        html += "<th style='font-weight: normal;'>Mã Hoá Đơn:</th>";
        html += "<td>" + txtMaHD.getText() + "</td>";
        html += "<th style='margin-left: 50px; font-weight: normal;'>Khách Hàng:</th>";
        html += "<td>" + txtTenKH.getText() + "</td>";
        html += "</tr>";
        html += "<p style='font-weight: normal;'>Nhân Viên: " + txtTenNV.getText() + "</p>";
        html += "</table>";
        //chi tiết đơn
        html += "<br>";
        html += "<div style='text-align: center;'>THÔNG TIN HOÁ ĐƠN</div><br/>";
        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
        html += "<div style='text-align: center;'>";
        html += "<table style='max-width: 50%;'>";
        html += "<tr>"
                + "<th style='font-weight: normal;'>Loại vàng</th>"
                + "<th style='font-weight: normal;'>Trọng Lượng</th>"
                + "<th style='font-weight: normal;'>Đơn Giá</th>"
                + "<th style='font-weight: normal;'>Thành Tiền</th>"
                + "</tr>";
//thêm sp vào hoá đơn
        DefaultTableModel dt = (DefaultTableModel) tblHDCT.getModel();
        for (int i = 0; i < tblHDCT.getRowCount(); i++) {
            String loaiv = dt.getValueAt(i, 1).toString();
            String khoiluong = dt.getValueAt(i, 2).toString();
            String dongia = dt.getValueAt(i, 3).toString();
            String thanhtien = dt.getValueAt(i, 4).toString();
            html += "<tr>";
            html += "<td style='text-align:center;font-weight: normal;'>" + loaiv + "</td>";
            html += "<td style='text-align:center;font-weight: normal;'>" + khoiluong + "</td>";
            html += "<td style='text-align:center;font-weight: normal;'>" + dongia + "</td>";
            html += "<td style='text-align:center;font-weight: normal;'>" + thanhtien + "</td>";
            html += "</tr>";
        }
        html += "</tr>";
        html += "</table>";
        html += "</div>";
        ////add tong bill
        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
        html += "<p style =text-align: right;'><b> Tổng Tiền : " + txtTongTien.getText() + "<br></b></p>";
        html += "<p style =text-align: right;'><b> Thanh Toán: " + txtThanhToan.getText() + "<br></b></p>";
        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
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
        html += "<div style='text-align: center;font-weight: normal;'>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </div><br/>";
        html += "<h6 style='text-align: center;'>CẢM ƠN QUÝ KHÁCH ĐÃ TIN TƯỞNG VÀ ỦNG HỘ!!<br></h6> ";
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

        setBackground(new java.awt.Color(255, 255, 255));
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

        getContentPane().add(lblhoadon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 480, 560));

        txtForm.setBackground(new java.awt.Color(255, 255, 255));
        txtForm.setOpaque(true);
        getContentPane().add(txtForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 640));

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
                FormMuaVao form1 = new FormMuaVao(); // Tạo instance của FormMuaVao
                XuatHoaDonMua xuatHD = new XuatHoaDonMua(form1); // Truyền tham chiếu của FormMuaVao
                xuatHD.setVisible(true);
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
