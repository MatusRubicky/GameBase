package sk.upjs.ics.GameBase;

import sk.upjs.ics.GameBase.Other.ComboBoxesRowLayout;
import sk.upjs.ics.GameBase.FactoryAndDAO.GameBaseDAO;
import sk.upjs.ics.GameBase.FactoryAndDAO.DaoFactory;
import sk.upjs.ics.GameBase.Entity.Game;
import sk.upjs.ics.GameBase.Entity.Tag;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sk.upjs.ics.GameBase.Entity.Category;
import sk.upjs.ics.GameBase.Entity.Studio;
import sk.upjs.ics.GameBase.FactoryAndDAO.StudioDAO;

public class AddOrEditForm extends javax.swing.JDialog {

    //        GamePlay, MultiPlayer, Network, Platform, Setting;
    private final GameBaseDAO gameBaseDao = DaoFactory.INSTANCE.getGameBaseDAO();
    private final StudioDAO studioDAO = DaoFactory.INSTANCE.getStudioDAO();
    private final Game game;

    private final List<ComboBoxesRowLayout> comboBoxes;
    private List<Tag> selectedTags;

    //Konstruktor pre vytvaranie novej hry
    public AddOrEditForm(java.awt.Frame parent, boolean modal) {
        this(parent, modal, null);
    }

    //Konstruktor pre editovanie uz existujucej hry
    public AddOrEditForm(java.awt.Frame parent, boolean modal, Game game) {
        super(parent, modal);
        initComponents();

        setResizable(false);

        comboBoxes = new ArrayList<>();

        int y = 180;

        for (Category category : DaoFactory.INSTANCE.getCategories()) {
            if (game == null) {
                comboBoxes.add(new ComboBoxesRowLayout(gameBaseDao.getAllTagsInCategory(category)));
            } else {//ak game != null , teda editujeme , tak posleme aj jej tagy, nech su uz vytvorene pri otvoreni editovacieho okna
                comboBoxes.add(new ComboBoxesRowLayout(gameBaseDao.getAllTagsInCategory(category), gameBaseDao.getTagsForGameInCategory(category, game.getID())));
            }
            getContentPane().add(comboBoxes.get(comboBoxes.size() - 1), new org.netbeans.lib.awtextra.AbsoluteConstraints(120, y, -1, 30));
            y += 40;
        }

        if (game == null) {
            this.game = new Game();
            this.game.setID(-1);
            lblStatus.setText("Pridávanie  ");
        } else {
            this.game = game;
            txtTitle.setText(game.getTitle());
            txtDescription.setText(game.getGameDesc());
            txtStudio.setText(game.getStudio());
            txtYearOfRelease.setText(game.getReleaseDate());
            lblStatus.setText("Upravovanie  ");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        btnAddTag = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtStudio = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtYearOfRelease = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Názov:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 89, 32));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Popis:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 49, 89, 32));
        getContentPane().add(txtTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 11, 540, 32));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("GamePlay:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 110, 32));

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 49, 540, 125));

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

        btnSave.setText("Uložiť");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, 70, -1));

        btnCancel.setText("Zrušiť");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 70, -1));

        lblStatus.setFont(new java.awt.Font("Sylfaen", 2, 14)); // NOI18N
        lblStatus.setText(" ");
        getContentPane().add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 170, -1));

        btnAddTag.setText("Pridať tag");
        btnAddTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTagActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddTag, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 500, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Štúdio:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 90, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Dátum:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 90, 30));

        jScrollPane2.setViewportView(txtStudio);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 530, 30));

        jScrollPane3.setViewportView(txtYearOfRelease);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 530, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Ukladanie
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (txtTitle.getText().equals("") || txtDescription.getText().equals("")) {
            lblStatus.setForeground(Color.red);
            lblStatus.setText("Vyplňte Názov a Popis hry!  ");
            return;
        }

        selectedTags = new ArrayList<>();

        for (ComboBoxesRowLayout comboBox : comboBoxes) {
            selectedTags.addAll(comboBox.getSelectedTags());
        }

        game.setTitle(txtTitle.getText());
        game.setGameDesc(txtDescription.getText());
        game.setStudio(txtStudio.getText());

        game.setReleaseDate(txtYearOfRelease.getText());

        gameBaseDao.saveGame(game);

        if (game.getID() == -1) {
            saveTagsForNewGame();
        } else {
            saveTagsForExistingGame();
        }

         if (studioDAO.getNumberOfStudios(txtStudio.getText()) == 0) {
            addStudio(txtStudio.getText()); //pridana metoda JPMR            
        }
        
        lblStatus.setForeground(Color.green);
        lblStatus.setText("Uložené.  ");
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void saveTagsForNewGame() {
        gameBaseDao.saveAllTags(selectedTags, gameBaseDao.getLastIDFromGames());
    }

    //Nehlada zmeny, jednoducho vsetky existujuce tagy konkretnej hry v databaze vymaze, a ulozi nove
    private void saveTagsForExistingGame() {
        gameBaseDao.deleteTags(game.getID());
        gameBaseDao.saveAllTags(selectedTags, game.getID());
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTagActionPerformed
        AddTagForm addTagForm = new AddTagForm((java.awt.Frame) getParent().getParent(), true);
        addTagForm.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAddTagActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTag;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextPane txtStudio;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextPane txtYearOfRelease;
    // End of variables declaration//GEN-END:variables

    private void addStudio(String name) { //pridana metoda JPMR
        int option = JOptionPane.showConfirmDialog(this, "Štúdio " + name + " sa nenachádza v databáze. Chcete ho pridať?", "Pridať štúdio?", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            Studio studio = new Studio();
            studio.setName(name);
            
            studioDAO.addStudio(studio);
        }
    }
}
