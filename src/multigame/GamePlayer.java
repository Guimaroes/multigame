/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multigame;

import java.awt.event.KeyEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Gabriel
 */
public class GamePlayer extends javax.swing.JFrame {

    private DatagramSocket ds;
    
    public GamePlayer() throws Exception {
        initComponents();
        initGamePlayer();
    }

    private void initGamePlayer() throws Exception {
        ds = new DatagramSocket();
    }
    
    private void movePlayer( String direction ) throws Exception {
        
        String data = playerSelector.getSelectedItem().toString() + "|" + direction;

        byte[] b = data.getBytes();
        InetAddress ia = InetAddress.getByName("177.44.248.11");
        DatagramPacket dp = new DatagramPacket(b,b.length,ia,9990);

        byte[] b1 = new byte[1024];
        DatagramPacket dp1 = new DatagramPacket(b1,b1.length);

        ds.send(dp);
//        ds.receive(dp1);
//
//        String error = new String(dp1.getData());
//
//        if ( !error.trim().isEmpty() ) {
//            System.out.println(error);
//        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        playerSelector = new javax.swing.JComboBox<>();
        textField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jLabel1.setText("Choose your player:");

        playerSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "a", "b", "c", "d" }));
        playerSelector.setSelectedIndex(-1);

        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(playerSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 231, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(playerSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        
    }//GEN-LAST:event_formKeyTyped

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldActionPerformed

    private void textFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyTyped
        
    }//GEN-LAST:event_textFieldKeyTyped

    private void textFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyPressed
        try {
            textField.setText("");
            
            switch ( evt.getKeyCode() ) {
                case KeyEvent.VK_W:
                    movePlayer("W");
                    break;
                case KeyEvent.VK_A:
                    movePlayer("A");
                    break;
                case KeyEvent.VK_S:
                    movePlayer("S");
                    break;
                case KeyEvent.VK_D:
                    movePlayer("D");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_textFieldKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> playerSelector;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
