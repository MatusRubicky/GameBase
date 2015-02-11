package sk.upjs.ics.GameBase;

import java.util.List;
import sk.upjs.ics.GameBase.Other.GameTableModel;
import sk.upjs.ics.GameBase.FactoryAndDAO.GameBaseDAO;
import sk.upjs.ics.GameBase.FactoryAndDAO.DaoFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sk.upjs.ics.GameBase.Entity.Tag;

public class MainForm extends javax.swing.JFrame {

    private final GameBaseDAO gamebaseDao = DaoFactory.INSTANCE.getGameBaseDAO();

    private final GameTableModel gameTableModel = new GameTableModel();

    public MainForm() {
        initComponents();

        tabMainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    updateTags();
                }
            }
        });

        updateTable();
        tabMainTable.grabFocus();
    }

    public final void updateTable() {
        //aby sa nestratili vysledky hladania pri upravovani alebo pridavani hry ak sa v searchu este nieco nachadza
        if (txtSearchTitle.getText().equals("") || !txtSearchTitle.hasFocus()) {
            gameTableModel.refresh();
        } else {
            tabMainTable.clearSelection();
            updateTags();
            gameTableModel.refresh(txtSearchTitle.getText());
        }

    }

    public void updateTags() {
        if ((tabMainTable.getSelectedRow()) != -1) {
            
            txtGamePlay.setText(tagsToString(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(0), getSelectedGameID())));
            txtMultiPlayer.setText(tagsToString(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(1), getSelectedGameID())));
            txtNetwork.setText(tagsToString(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(2), getSelectedGameID())));
            txtPlatform.setText(tagsToString(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(3), getSelectedGameID())));
            txtSetting.setText(tagsToString(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(4), getSelectedGameID())));
        }
        else{
            txtGamePlay.setText("");
            txtMultiPlayer.setText("");
            txtNetwork.setText("");
            txtPlatform.setText("");
            txtSetting.setText("");
        }
    }
    
    private String tagsToString(List<Tag> tags){
        StringBuilder sb=new StringBuilder();
        
        for(Tag tag:tags){
            sb.append(tag.getTagName());
            sb.append(", ");
        }
        if(sb.length()>1)
        sb.delete(sb.length()-2, sb.length()-1);
        
        return sb.toString();
    }

    // Neviem vypnut aby sa stlpce dali posuvat doprava a dolava, a kedze potrebujem vediet ktory stlpec je stlpec z IDckami, musim ho najprv najst
    private int getColumnWithID() {
        for (int i = 0; i < tabMainTable.getColumnCount(); i++) {
            if (tabMainTable.getColumnName(i).equals(gameTableModel.getColumnName(0))) {
                return i;
            }
        }
        return -1;
    }

    private long getSelectedGameID() {
        return (long) tabMainTable.getValueAt(tabMainTable.getSelectedRow(), getColumnWithID());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        lblGamePlay = new javax.swing.JLabel();
        txtGamePlay = new javax.swing.JTextField();
        lblGamePlay1 = new javax.swing.JLabel();
        txtMultiPlayer = new javax.swing.JTextField();
        lblGamePlay2 = new javax.swing.JLabel();
        txtNetwork = new javax.swing.JTextField();
        lblGamePlay3 = new javax.swing.JLabel();
        txtPlatform = new javax.swing.JTextField();
        lblGamePlay4 = new javax.swing.JLabel();
        txtSetting = new javax.swing.JTextField();
        btnAddGame = new javax.swing.JButton();
        btnEditGame = new javax.swing.JButton();
        txtSearchTitle = new javax.swing.JTextField();
        btnSearchTag = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabMainTable = new javax.swing.JTable();
        btnAddStudio = new javax.swing.JButton();
        btnStudios = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("GameBase");

        lblGamePlay.setText("GamePlay: ");

        txtGamePlay.setEditable(false);
        txtGamePlay.setText(" ");

        lblGamePlay1.setText("MultiPlayer:");

        txtMultiPlayer.setEditable(false);
        txtMultiPlayer.setText(" ");

        lblGamePlay2.setText("Network:");

        txtNetwork.setEditable(false);
        txtNetwork.setText(" ");

        lblGamePlay3.setText("Platform:");

        txtPlatform.setEditable(false);
        txtPlatform.setText(" ");

        lblGamePlay4.setText("Setting:");

        txtSetting.setEditable(false);
        txtSetting.setText(" ");

        btnAddGame.setText("Pridať hru...");
        btnAddGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGameActionPerformed(evt);
            }
        });

        btnEditGame.setText("Upraviť...");
        btnEditGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditGameActionPerformed(evt);
            }
        });

        txtSearchTitle.setText("Vyhľadávanie");
        txtSearchTitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchTitleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchTitleFocusLost(evt);
            }
        });
        txtSearchTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTitleKeyReleased(evt);
            }
        });

        btnSearchTag.setText("Hľadať podľa tagov");
        btnSearchTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTagActionPerformed(evt);
            }
        });

        tabMainTable.setAutoCreateRowSorter(true);
        tabMainTable.setModel(gameTableModel);
        tabMainTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMainTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabMainTable);
        tabMainTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnAddStudio.setText("Pridať/upraviť štúdio...");
        btnAddStudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudioActionPerformed(evt);
            }
        });

        btnStudios.setText("Štúdiá");
        btnStudios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGamePlay, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGamePlay))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblGamePlay1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMultiPlayer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGamePlay2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNetwork))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGamePlay3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlatform))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGamePlay4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSetting))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSearchTag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStudios, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddStudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditGame))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchTitle)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGamePlay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGamePlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGamePlay1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMultiPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGamePlay2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGamePlay3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGamePlay4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSetting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddGame)
                    .addComponent(btnEditGame)
                    .addComponent(btnSearchTag)
                    .addComponent(btnAddStudio)
                    .addComponent(btnStudios))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGameActionPerformed
        AddOrEditForm addOrEditForm = new AddOrEditForm(this, true);
        addOrEditForm.setVisible(true);
        updateTable();
    }//GEN-LAST:event_btnAddGameActionPerformed

    private void btnEditGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditGameActionPerformed
        if (tabMainTable.getSelectedRow() == -1) {
            return;
        }

        AddOrEditForm addOrEditForm = new AddOrEditForm(this, true, gamebaseDao.getGame(getSelectedGameID()));
        addOrEditForm.setVisible(true);
        updateTable();
    }//GEN-LAST:event_btnEditGameActionPerformed

    private void btnSearchTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTagActionPerformed
        SearchGamesTaggedForm searchGamesTagged = new SearchGamesTaggedForm(this, true);
        searchGamesTagged.setVisible(true);
        updateTable();
    }//GEN-LAST:event_btnSearchTagActionPerformed

    private void txtSearchTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTitleKeyReleased
        updateTable();
    }//GEN-LAST:event_txtSearchTitleKeyReleased

    private void txtSearchTitleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchTitleFocusGained
        txtSearchTitle.setText("");
    }//GEN-LAST:event_txtSearchTitleFocusGained

    private void txtSearchTitleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchTitleFocusLost
        txtSearchTitle.setText("Vyhľadávanie");
    }//GEN-LAST:event_txtSearchTitleFocusLost

    private void tabMainTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMainTableMouseClicked
        if (evt.getClickCount() == 2 && tabMainTable.getSelectedRow() != -1) {
            GameDetailForm gameDetailForm = new GameDetailForm(this, true, gamebaseDao.getGame(getSelectedGameID()));
            gameDetailForm.setVisible(true);
            updateTable();
        }
    }//GEN-LAST:event_tabMainTableMouseClicked

    private void btnAddStudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudioActionPerformed
        AddOrEditStudio addOrEditStudio = new AddOrEditStudio(this, true);
        addOrEditStudio.setVisible(true);
        addOrEditStudio.setAlwaysOnTop(true);
    }//GEN-LAST:event_btnAddStudioActionPerformed

    private void btnStudiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudiosActionPerformed
        StudioList studioList = new StudioList();
        studioList.setVisible(true);
        studioList.setAlwaysOnTop(true);
    }//GEN-LAST:event_btnStudiosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddGame;
    private javax.swing.JButton btnAddStudio;
    private javax.swing.JButton btnEditGame;
    private javax.swing.JButton btnSearchTag;
    private javax.swing.JButton btnStudios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGamePlay;
    private javax.swing.JLabel lblGamePlay1;
    private javax.swing.JLabel lblGamePlay2;
    private javax.swing.JLabel lblGamePlay3;
    private javax.swing.JLabel lblGamePlay4;
    private javax.swing.JTable tabMainTable;
    private javax.swing.JTextField txtGamePlay;
    private javax.swing.JTextField txtMultiPlayer;
    private javax.swing.JTextField txtNetwork;
    private javax.swing.JTextField txtPlatform;
    private javax.swing.JTextField txtSearchTitle;
    private javax.swing.JTextField txtSetting;
    // End of variables declaration//GEN-END:variables
}
