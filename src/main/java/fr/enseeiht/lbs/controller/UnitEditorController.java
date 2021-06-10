package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.game.object.EntityFactory;
import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Contrôleur qui gère l'affichage et la sauvegarde des unités et leurs statistiques
 * dans le mode creator.
 */
public class UnitEditorController extends JPanel{
    String editedUnit;
    JPanel centerPanel;
    Map<Statistic, JFormattedTextField> fields = new HashMap<>();
    private NumberFormat amountFormat;

    public UnitEditorController() {
        setLayout(new BorderLayout());
        JButton saveButton = new JButton("save");
        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(500, 500));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        add(saveButton, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        saveButton.addActionListener(actionEvent -> {
            Stats newStats = new Stats();
            for (Map.Entry<Statistic, JFormattedTextField> entry : fields.entrySet()) {
                newStats.addStat(entry.getKey(), (Double) entry.getValue().getValue());
            }
            try {
                EntityFactory.setEntityType(editedUnit, EntityFactory.getEntityPrimitiveType(editedUnit), newStats);
            } catch (EntityFactory.UnmodifiableTypeException e) {
                e.printStackTrace();
            }
        });
        setEditedUnit(null);
    }

    public void setEditedUnit(String editedUnit) {
        this.editedUnit = editedUnit;
        centerPanel.removeAll();
        fields.clear();
        if (editedUnit == null || EntityFactory.getEntityTypeStats(editedUnit) == null) {
            centerPanel.add(new JLabel("Select a unit to edit"));
            updateUI();
            return;
        }
        boolean enabled = !EntityFactory.getInitialUnit().contains(editedUnit);
        Stats unitStats = EntityFactory.getEntityTypeStats(editedUnit);
        for (Map.Entry<Statistic, Double> entr : unitStats.getStatistics().entrySet()) {
            centerPanel.add(new JLabel(entr.getKey().name().toLowerCase()));
            JFormattedTextField textField = new JFormattedTextField(amountFormat);
            textField.setValue(entr.getValue());
            textField.setEnabled(enabled);
            centerPanel.add(textField);
            fields.put(entr.getKey(), textField);
        }
        updateUI();
    }
}
