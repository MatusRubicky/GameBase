package sk.upjs.ics.GameBase;

import sk.upjs.ics.GameBase.Other.ComboBoxesRowLayout;
import sk.upjs.ics.GameBase.FactoryAndDAO.GameBaseDAO;
import sk.upjs.ics.GameBase.FactoryAndDAO.DaoFactory;
import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Tag;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import sk.upjs.ics.GameBase.Entity.Category;

public class SearchGamesTaggedForm extends javax.swing.JDialog {

    //  GamePlay, MultiPlayer, Network, Platform, Setting;
    private final GameBaseDAO gameBaseDao = DaoFactory.INSTANCE.getGameBaseDAO();
    private final Game game;

    private final List<ComboBoxesRowLayout> comboBoxes;
    private List<List<Tag>> selectedTags;

    public SearchGamesTaggedForm(java.awt.Frame parent, boolean modal) {

        super(parent, modal);

        initComponents();

        setResizable(false);

        comboBoxes = new ArrayList<>();
        
        int y = 180;

        for (Category category : DaoFactory.INSTANCE.getCategories()) {

            comboBoxes.add(new ComboBoxesRowLayout(gameBaseDao.getAllTagsInCategory(category)));

            getContentPane().add(comboBoxes.get(comboBoxes.size() - 1), new org.netbeans.lib.awtextra.AbsoluteConstraints(120, y, -1, 30));
            y += 40;
        }

        this.game = new Game();
        this.game.setID(-1);
        lblStatus.setText("Vyhľadávanie podľa tagov  ");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gameList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("GamePlay:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 110, 32));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("MultiPlayer:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 110, 32));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Network:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 110, 32));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Platform:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 110, 32));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Setting:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 110, 32));

        btnSearch.setText("Hľadaj");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 70, -1));

        btnCancel.setText("Zrušiť");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 70, -1));

        lblStatus.setFont(new java.awt.Font("Sylfaen", 2, 14)); // NOI18N
        lblStatus.setText(" ");
        getContentPane().add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        gameList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gameListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gameList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 470, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        selectedTags = new ArrayList<>();

        for (ComboBoxesRowLayout comboBox : comboBoxes) {
            selectedTags.add(comboBox.getSelectedTags());
        }

        List<Tag> temp = new ArrayList<>();
        for (List<Tag> tg : selectedTags) {
            temp.addAll(tg);
        }

        gameList.setListData(gameBaseDao.listAllGamesTagged(temp).toArray());

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void gameListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameListMouseClicked
        if (evt.getClickCount() == 2 && gameList.getSelectedIndex() != -1) {
            GameDetailForm gameDetailForm = new GameDetailForm((Frame) this.getParent(), true, (Game) gameList.getSelectedValue());
            gameDetailForm.setVisible(true);
        }
    }//GEN-LAST:event_gameListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSearch;
    private javax.swing.JList gameList;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
