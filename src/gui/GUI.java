package gui;

import sqlStuff.Ereignis;

import javax.swing.*;

/**
 * GUI Klasse für die GUI
 */
public class GUI {
    JList<Ereignis> guiEreignisList = new JList<>();

    public GUI() {
        bestaetigenButton.addActionListener(e -> bestaetigen());
        refresh();
        terminEinplanenCheckBox.addActionListener(e -> einplanenBoxLogic());
        resetButton.addActionListener(e -> {
            Logic logic = new Logic();
            logic.allesLoeschen();
            refresh();
        });
        deleteSelectedButton.addActionListener(e -> delete());
        geplantCheckBox.addActionListener(e -> refresh());
        fertigCheckBox.addActionListener(e -> refresh());
        entfaelltCheckBox.addActionListener(e -> refresh());
    }

    /**
     * erneuert die Anzeige der Ereignisse
     */
    public void refresh() {
        Logic logic = new Logic();
        logic.anzeigeLabel();
        if (geplantCheckBox.isSelected()) {
            logic.filterEreignis(0);
        } else if (fertigCheckBox.isSelected()) {
            logic.filterEreignis(1);
        } else if (entfaelltCheckBox.isSelected()) {
            logic.filterEreignis(2);
        }
        terminAnzeigePanel.removeAll();
        terminAnzeigePanel.setLayout(new BoxLayout(terminAnzeigePanel, BoxLayout.Y_AXIS));
        this.guiEreignisList = logic.ereignisJList;
        terminAnzeigePanel.add(this.guiEreignisList);
        terminAnzeigePanel.revalidate();
        terminAnzeigePanel.repaint();
    }

    /**
     * Methode die das Ereignis in die Datenbank einträgt
     */
    public void bestaetigen() {
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

    /**
     * Methode die, die Felder ein- und ausblendet
     */
    public void einplanenBoxLogic() {
        if (terminEinplanenCheckBox.isSelected()) {
            vonInField.setEnabled(true);
            bisInField.setEnabled(true);

        } else {
            vonInField.setEnabled(false);
            bisInField.setEnabled(false);
        }
    }

    /**
     * Methode die das ausgewählte Ereignis löscht
     */
    public void delete() {
        Logic logic = new Logic();
        logic.loeschen(guiEreignisList.getSelectedValue().getId());
        refresh();
    }


    public JPanel toDoListe;
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
