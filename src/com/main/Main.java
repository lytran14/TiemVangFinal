package com.main;

import Class_Utils.Auth;
import Class_Utils.MsgBox;
import com.event.EventMenuSelected;
import com.form.FormBanRa;
import com.form.FormCamDo;
import com.form.FormCuaSoChao;
import com.form.FormDangNhap;
import com.form.FormDoiMatKhau;
import com.form.FormGiaVang;
import com.form.FormKhachHang;
import com.form.FormLoaiSanPham;
import com.form.FormMuaVao;
import com.form.FormNhanVien;
import com.form.FormSanPham;
import com.form.FormThongKe;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends javax.swing.JFrame {

    private FormThongKe tkKe;
    private FormSanPham sp;
    private FormLoaiSanPham lsp;
    private FormBanRa br;
    private FormMuaVao mv;
    private FormCamDo cd;
    private FormGiaVang gv;
    private FormKhachHang kh;
    private FormNhanVien nv;

    public Main() {
        initComponents();
        tkKe = new FormThongKe();
        sp = new FormSanPham();
        lsp = new FormLoaiSanPham();
        br = new FormBanRa();
        mv = new FormMuaVao();
        cd = new FormCamDo();
        gv = new FormGiaVang();
        kh = new FormKhachHang();
        nv = new FormNhanVien();
        new FormCuaSoChao(this, true).setVisible(true);
        new FormDangNhap(this, true).setVisible(true);
        menu.initMoving(Main.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                //System.out.println(".selected(): " + index);
                if (index == 7) {
                    if (Auth.isLogin()) {
                        setForm(gv);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 2) {
                    if (Auth.isLogin()) {
                        setForm(sp);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 3) {
                    if (Auth.isLogin()) {
                        setForm(lsp);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 4) {
                    if (Auth.isLogin()) {
                        setForm(br);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 5) {
                    if (Auth.isLogin()) {
                        setForm(mv);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 6) {
                    if (Auth.isLogin()) {
                        setForm(cd);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 9) {
                    if (Auth.isLogin()) {
                        setForm(kh);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 11) {
                    if (Auth.isLogin()) {
                        setForm(nv);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 12) {
                    if (Auth.isLogin()) {
                        setForm(tkKe);
                    } else {
                        MsgBox.alert(null, "Vui lòng đăng nhập.");
                    }
                } else if (index == 14) {
                    Auth.clear();
                    new FormDangNhap(Main.this, true).setVisible(true);
                } else if (index == 15) {
                    new FormDoiMatKhau(Main.this, true).setVisible(true);
                } else if (index == 16) {
                    if (MsgBox.confirm(null, "BẠN CHẮC CHẮN MUỐN THOÁT?")) {
                        System.exit(0);
                    }
                }
            }
        });
        setForm(new FormThongKe());
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void setForm(JFrame frame) {
        mainPanel.removeAll();
        mainPanel.add(frame.getContentPane());
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder2 = new com.swing.PanelBorder();
        menu = new com.component.Menu();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1257, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private com.component.Menu menu;
    private com.swing.PanelBorder panelBorder2;
    // End of variables declaration//GEN-END:variables
}
