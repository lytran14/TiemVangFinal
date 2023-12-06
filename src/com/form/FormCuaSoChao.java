package com.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class FormCuaSoChao extends javax.swing.JDialog {

    public FormCuaSoChao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
       
    }

    private void init() {
        this.setLocationRelativeTo(null);
        new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = pgb.getValue();
                if (value < pgb.getMaximum()) {
                    pgb.setValue(value + 1);
                } else {
                    dispose();
                }
            }
        }).start();    
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        pgb = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setUndecorated(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pgb.setStringPainted(true);
        jPanel2.add(pgb, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 470, 980, 22));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/log.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 979, 522));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormCuaSoChao dialog = new FormCuaSoChao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar pgb;
    // End of variables declaration//GEN-END:variables
}
