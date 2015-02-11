package sk.upjs.ics.GameBase.Other;

import sk.upjs.ics.GameBase.Entity.Tag;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteLayout;


//Tato trieda vytvara a obsluhuje comboboxi tak aby ich mohol byt lubovolni 
//pocet a aby sa jeden tag nedal vybrat vo viacerych comboboxoch naraz
public class ComboBoxesRowLayout extends JPanel {

    private final List<Tag> allTags;
    private final List<Tag> availableTags;
    private final List<JComboBox<Tag>> comboBoxes;

    private int firstShownComboBoxIndex = 0;
    private final int maxShown = 3;
    private final int spacing = 100;

    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton leftButton;
    private final JButton rightButton;
    private boolean canRefresh = true;

    private final Tag prototypeWidthTag;

    //Konstruktor zo ziadnymi uz existujucimi tagmi
    public ComboBoxesRowLayout(List<Tag> allTags) {
        this(allTags, null);
    }

    //Konstruktor pre uz existujuce tagy
    public ComboBoxesRowLayout(List<Tag> allTags, List<Tag> alreadySelected) {
        this.setLayout(new AbsoluteLayout());
        comboBoxes = new ArrayList<>();

        this.setBackground(Color.red);

        this.allTags = new ArrayList<>();
        this.allTags.addAll(allTags);
        availableTags = new ArrayList<>();
        availableTags.addAll(allTags);

        addButton = new JButton("+");
        deleteButton = new JButton("-");
        leftButton = new JButton("<");
        rightButton = new JButton(">");

        prototypeWidthTag = new Tag();
        prototypeWidthTag.setTagName("DefaulWidth");

        prepareButtons();

        if (alreadySelected != null) {
            for (Tag tag : alreadySelected) {
                createComboBox();
                comboBoxes.get(comboBoxes.size() - 1).setSelectedItem(tag);
                shiftComboBoxes();
            }
        }
    }

    //Vytvori tlacidla
    private void prepareButtons() {
        add(leftButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));
        leftButton.setLocation(0, 0);
        leftButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstShownComboBoxIndex--;
                shiftComboBoxes();
            }
        });
        leftButton.setVisible(false);

        add(rightButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 0, -1, 30));
        rightButton.setLocation(35, 0);
        rightButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstShownComboBoxIndex++;
                shiftComboBoxes();
            }
        });
        rightButton.setVisible(false);

        add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));
        deleteButton.setLocation(70, 0);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroyComboBox();
                if (comboBoxes.size() - 1 >= maxShown) {
                    firstShownComboBoxIndex = comboBoxes.size() - maxShown;
                } else {
                    firstShownComboBoxIndex = 0;
                }
                shiftComboBoxes();
            }
        });
        deleteButton.setVisible(false);

        add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 0, -1, 30));
        addButton.setLocation(105, 0);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createComboBox();
                if (comboBoxes.size() - 1 >= maxShown) {
                    firstShownComboBoxIndex = comboBoxes.size() - maxShown;
                } else {
                    firstShownComboBoxIndex = 0;
                }
                shiftComboBoxes();
            }
        });
    }

    //Znici posledny combobox v rade
    private void destroyComboBox() {
        availableTags.add((Tag) comboBoxes.get(comboBoxes.size() - 1).getSelectedItem());
        remove(comboBoxes.get(comboBoxes.size() - 1));
        comboBoxes.remove(comboBoxes.get(comboBoxes.size() - 1));
        revalidate();
        repaint();

        refreshComboBoxes();
        addButton.setVisible(true);
    }

    //Vytvori combobox na konci
    private void createComboBox() {
        comboBoxes.add(new JComboBox(new javax.swing.DefaultComboBoxModel(availableTags.toArray())));
        comboBoxes.get(comboBoxes.size() - 1).setPrototypeDisplayValue(prototypeWidthTag);
        add(comboBoxes.get(comboBoxes.size() - 1), new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0));//je jedno ake suradnice tam dame, metoda shift comboboxes to posunie
        comboBoxes.get(comboBoxes.size() - 1).addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshComboBoxes();
            }
        });

        refreshComboBoxes();
        deleteButton.setVisible(true);
    }

    //Jeden combobox obsahuje tagy len svoj + vsetky nevybrate. Tato metoda 
    //vymaze obsah vsetkych comboboxov az na tag ktory je momentalne vybraty v 
    //kazdom z nich, a znova naplni tagmi z listu availableTags ktory drzi 
    //nevybrate tagy.
    public void refreshComboBoxes() {
        if (!canRefresh || comboBoxes.isEmpty()) {
            return;
        }
        canRefresh = false;

        availableTags.clear();
        availableTags.addAll(allTags);

        for (JComboBox<Tag> comboBox : comboBoxes) {
            availableTags.remove((Tag) comboBox.getSelectedItem());
        }

        Tag selectedTag;
        for (JComboBox<Tag> comboBox : comboBoxes) {
            selectedTag = (Tag) comboBox.getSelectedItem();
            comboBox.removeAllItems();
            comboBox.addItem(selectedTag);
            for (Tag tag : availableTags) {
                comboBox.addItem(tag);
            }
        }
        canRefresh = true;
    }

    //Pocet comboboxov v rade je obmedzeny len poctom dostupnych tagov, ale na
    //obrazovke sa ukaze len tolko kolko je v premennej maxShown, tato metoda 
    //posuva a zneviditelnuje comboboxi tak aby ked sa napriklad maxShown=3, zobrazili 3 za 
    //sebou iduce comboboxi od indexu ktory je v premennej firstShownComboBoxIndex
    private void shiftComboBoxes() {
        if (availableTags.isEmpty()) {
            addButton.setVisible(false);
        }
        if (comboBoxes.isEmpty()) {
            deleteButton.setVisible(false);
        }

        int pom = 0;
        for (int i = 0; i < comboBoxes.size(); i++) {
            if (i < firstShownComboBoxIndex || i > firstShownComboBoxIndex - 1 + maxShown) {
                comboBoxes.get(i).setVisible(false);
            } else {
                comboBoxes.get(i).setVisible(true);
                add(comboBoxes.get(i), new org.netbeans.lib.awtextra.AbsoluteConstraints(70 + (pom * spacing), 0));
                pom++;
            }
        }

        if (comboBoxes.size() <= maxShown) {
            leftButton.setVisible(false);
            rightButton.setVisible(false);

            add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70 + (comboBoxes.size() * spacing), 0));
            add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70 + (comboBoxes.size() * spacing) + 35, 0));

        } else {
            if (firstShownComboBoxIndex == 0) {
                leftButton.setVisible(false);
                rightButton.setVisible(true);
            } else if (firstShownComboBoxIndex == comboBoxes.size() - maxShown) {
                leftButton.setVisible(true);
                rightButton.setVisible(false);
            } else {
                leftButton.setVisible(true);
                rightButton.setVisible(true);
            }

        }
    }

    //Zo vsetkych comboboxov vytiahne vybrate a vlozi do listu
    public List<Tag> getSelectedTags() {
        List<Tag> selectedTags = new ArrayList<>();

        for (JComboBox<Tag> comboBox : comboBoxes) {
            selectedTags.add((Tag) comboBox.getSelectedItem());
        }

        return selectedTags;
    }
}
