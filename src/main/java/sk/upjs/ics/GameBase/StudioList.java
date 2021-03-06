/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.GameBase;

import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Studio;
import sk.upjs.ics.GameBase.FactoryAndDAO.DaoFactory;
import sk.upjs.ics.GameBase.FactoryAndDAO.GameBaseDAO;
import sk.upjs.ics.GameBase.FactoryAndDAO.StudioDAO;

/**
 *
 * @author Uživateľ
 */
public class StudioList extends javax.swing.JFrame {

    private final GameBaseDAO gameBaseDAO = DaoFactory.INSTANCE.getGameBaseDAO();
    private final StudioDAO studioDAO = DaoFactory.INSTANCE.getStudioDAO();

    /**
     * Creates new form StudioList
     */
    public StudioList() {
        initComponents();

        lstStudios.setModel(new javax.swing.DefaultComboBoxModel<>(studioDAO.getAllStudios().toArray()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstStudios = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGames = new javax.swing.JList();
        btnOK = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstStudios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstStudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstStudiosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstStudios);

        lstGames.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstGamesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstGames);

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitle.setText("Štúdiá                Hry");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOK))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOK)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void lstStudiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstStudiosMouseClicked
        if (evt.getClickCount() == 1) {
            Studio studio = (Studio) lstStudios.getSelectedValue();
            lstGames.setModel(new javax.swing.DefaultComboBoxModel<>(studioDAO.getListOfGames(studio.getID()).toArray()));
        }
    }//GEN-LAST:event_lstStudiosMouseClicked

    private void lstGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstGamesMouseClicked
        if (evt.getClickCount() == 2) {
            Game game = (Game) lstGames.getSelectedValue();
            GameDetailForm gameDetailForm = new GameDetailForm(this, true, game);
            gameDetailForm.setVisible(true);
            gameDetailForm.setAlwaysOnTop(true);
        }
    }//GEN-LAST:event_lstGamesMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList lstGames;
    private javax.swing.JList lstStudios;
    // End of variables declaration//GEN-END:variables
}
