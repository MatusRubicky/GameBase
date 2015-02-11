package sk.upjs.ics.GameBase;

import sk.upjs.ics.GameBase.Other.YouTubeVideo;
import sk.upjs.ics.GameBase.FactoryAndDAO.GameBaseDAO;
import sk.upjs.ics.GameBase.FactoryAndDAO.DaoFactory;
import sk.upjs.ics.GameBase.Entity.Game;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class GameDetailForm extends javax.swing.JDialog {

    private final GameBaseDAO gamebaseDao = DaoFactory.INSTANCE.getGameBaseDAO();
    private final YouTubeVideo youTubeVideo;

    public GameDetailForm(java.awt.Frame parent, boolean modal, Game game) {
        super(parent, modal);
        initComponents();

        //youtube video
        NativeSwing.initialize();
        NativeInterface.open();
        youTubeVideo = new YouTubeVideo();
        add(youTubeVideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 420, 220));

        reload(game);

    }

    private void reload(Game game) {
        lblTitle.setText(game.getTitle());
        txtDescription.setText(game.getGameDesc());
        txtStudio.setText(game.getStudio());
        txtYearOfRelease.setText(game.getReleaseDate());

        txtGamePlay.setText(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(0), game.getID()).toString());
        txtMultiPlayer.setText(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(1), game.getID()).toString());
        txtNetwork.setText(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(2), game.getID()).toString());
        txtPlatform.setText(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(3), game.getID()).toString());
        txtSetting.setText(gamebaseDao.getTagsForGameInCategory(DaoFactory.INSTANCE.getCategories().get(4), game.getID()).toString());

        //youtube video
        youTubeVideo.changeVideo(game);

        //list podobnych hier
        listSimilarGames.removeAll();
        listSimilarGames.setListData(gamebaseDao.listMostSimilarGames(game.getID()).toArray());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblGamePlay = new javax.swing.JLabel();
        txtGamePlay = new javax.swing.JTextField();
        lblMultiPlayer = new javax.swing.JLabel();
        txtMultiPlayer = new javax.swing.JTextField();
        lblNetwork = new javax.swing.JLabel();
        txtNetwork = new javax.swing.JTextField();
        lblPlatform = new javax.swing.JLabel();
        txtPlatform = new javax.swing.JTextField();
        lblSetting = new javax.swing.JLabel();
        txtSetting = new javax.swing.JTextField();
        lblSimilarGames = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listSimilarGames = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblStudio = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtStudio = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtYearOfRelease = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setText("Afhgipag G/OASS");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 290, 50));

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        txtDescription.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescription);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, 290, 167));

        lblGamePlay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGamePlay.setText("GamePlay: ");
        getContentPane().add(lblGamePlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 64, 22));

        txtGamePlay.setEditable(false);
        txtGamePlay.setText(" ");
        getContentPane().add(txtGamePlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 241, 663, -1));

        lblMultiPlayer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMultiPlayer.setText("MultiPlayer:");
        getContentPane().add(lblMultiPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 268, 64, 22));

        txtMultiPlayer.setEditable(false);
        txtMultiPlayer.setText(" ");
        getContentPane().add(txtMultiPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 269, 663, -1));

        lblNetwork.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNetwork.setText("Network:");
        getContentPane().add(lblNetwork, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 296, 64, 22));

        txtNetwork.setEditable(false);
        txtNetwork.setText(" ");
        getContentPane().add(txtNetwork, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 297, 663, -1));

        lblPlatform.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPlatform.setText("Platform:");
        getContentPane().add(lblPlatform, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 324, 64, 22));

        txtPlatform.setEditable(false);
        txtPlatform.setText(" ");
        txtPlatform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlatformActionPerformed(evt);
            }
        });
        getContentPane().add(txtPlatform, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 325, 663, -1));

        lblSetting.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSetting.setText("Setting:");
        getContentPane().add(lblSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 352, 64, 22));

        txtSetting.setEditable(false);
        txtSetting.setText(" ");
        getContentPane().add(txtSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 353, 663, -1));

        lblSimilarGames.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSimilarGames.setText("Najpodobnejšie hry vzostupne");
        getContentPane().add(lblSimilarGames, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 380, 440, 24));

        listSimilarGames.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listSimilarGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSimilarGamesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listSimilarGames);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 437, 144));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ďaľšie informácie");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 280, 30));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 10, 190));

        lblStudio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblStudio.setText("Štúdio:");
        getContentPane().add(lblStudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 90, 20));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Dátum vydania:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 450, 90, 20));

        txtStudio.setEditable(false);
        jScrollPane3.setViewportView(txtStudio);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 190, -1));

        txtYearOfRelease.setEditable(false);
        jScrollPane4.setViewportView(txtYearOfRelease);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 190, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPlatformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlatformActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlatformActionPerformed

    private void listSimilarGamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSimilarGamesMouseClicked
        if (evt.getClickCount() == 2 && listSimilarGames.getSelectedIndex() != -1) {
            reload(((Game) listSimilarGames.getSelectedValue()));
        }
    }//GEN-LAST:event_listSimilarGamesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGamePlay;
    private javax.swing.JLabel lblMultiPlayer;
    private javax.swing.JLabel lblNetwork;
    private javax.swing.JLabel lblPlatform;
    private javax.swing.JLabel lblSetting;
    private javax.swing.JLabel lblSimilarGames;
    private javax.swing.JLabel lblStudio;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList listSimilarGames;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtGamePlay;
    private javax.swing.JTextField txtMultiPlayer;
    private javax.swing.JTextField txtNetwork;
    private javax.swing.JTextField txtPlatform;
    private javax.swing.JTextField txtSetting;
    private javax.swing.JTextPane txtStudio;
    private javax.swing.JTextPane txtYearOfRelease;
    // End of variables declaration//GEN-END:variables

}
