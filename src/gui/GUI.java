package gui;

import sqlStuff.Ereignis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    JList<Ereignis> guiEreignisList = new JList<Ereignis>();

    public GUI() {
        bestaetigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ereignis = ereignisNameField.getText();
                byte status = 0;
                if (geplantRadioButton.isSelected()) {
                    status = 0;
                } else if (fertigRadioButton.isSelected()) {
                    status = 1;
                } else if (entfaelltRadioButton.isSelected()) {
                    status = 2;
                }
                String von = "";
                String bis = "";
                if (terminEinplanenCheckBox.isSelected()) {
                    von = vonInField.getText();
                    bis = bisInField.getText();
                }
                Logic.addContent(ereignis, status, von, bis);
                refresh();
                ereignisNameField.setText("");
            }
        });
        refresh();
        terminEinplanenCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (terminEinplanenCheckBox.isSelected()) {
                    vonInField.setEnabled(true);
                    bisInField.setEnabled(true);

                } else {
                    vonInField.setEnabled(false);
                    bisInField.setEnabled(false);
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic logic = new Logic();
                logic.allesLoeschen();
                refresh();
            }
        });
        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }

    public void refresh() {
        Logic logic = new Logic();
        logic.anzeigeLabel();
        terminAnzeigePanel.removeAll();
        terminAnzeigePanel.setLayout(new BoxLayout(terminAnzeigePanel, BoxLayout.Y_AXIS));
        this.guiEreignisList = logic.ereignisJList;
        terminAnzeigePanel.add(this.guiEreignisList);
        terminAnzeigePanel.revalidate();
        terminAnzeigePanel.repaint();
    }

    public void delete() {
        Logic logic = new Logic();
        logic.loeschen(guiEreignisList.getSelectedValue().getId());
        refresh();
    }


    public JPanel toDoListe;
    private JCheckBox mitBemerkungCheckBox;
    private JCheckBox mitZusInfoCheckBox;
    private JCheckBox geplantCheckBox;
    private JCheckBox fertigCheckBox;
    private JCheckBox entfaelltCheckBox;
    private JRadioButton geplantRadioButton;
    private JRadioButton entfaelltRadioButton;
    private JRadioButton fertigRadioButton;
    private JCheckBox terminEinplanenCheckBox;
    private JButton bestaetigenButton;
    private JPanel terminAnzeigePanel;
    private JTextField ereignisNameField;
    private JFormattedTextField vonInField;
    private JFormattedTextField bisInField;
    private JPanel aussenPanel;
    private JPanel inputHolder;
    private JPanel filterPanel;
    private JButton resetButton;
    private JButton deleteSelectedButton;
}
