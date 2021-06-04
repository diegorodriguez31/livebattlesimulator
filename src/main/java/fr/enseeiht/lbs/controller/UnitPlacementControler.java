package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.view.content.BattleView;
import main.java.fr.enseeiht.lbs.view.content.BattleWorldView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

public class UnitPlacementControler extends JPanel {
    private final Battle model;
    private final BattleWorldView battleView;
    private String selectedUnit;
    private final JComboBox<String> armySelect;
    private final JPanel unitList, unitTypePanel;
    private final ButtonGroup group;
    private final ActionListener listener;

    public UnitPlacementControler() {
        this.model = Battle.getInstance();
        setLayout(new BorderLayout());

        // Creates the view of the game
        this.battleView = new BattleWorldView();
        model.addObserver(battleView, Battle.PROPERTY_GAME_OBJECTS);
        battleView.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (selectedUnit == null) return;
                Entity entity = EntityFactory.createEntity(selectedUnit, battleView.pixelToWorld(mouseEvent.getX(), mouseEvent.getY()));
                if (entity instanceof Unit && armySelect.getSelectedIndex() != 0) {
                    model.getArmies().get(armySelect.getSelectedIndex() - 1).addUnit((Unit) entity);
                }
                entity.setReady();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {

            }
        });
        this.add(battleView, BorderLayout.CENTER);

        // Creates the list of entities
        group = new ButtonGroup();
        unitTypePanel = new JPanel();
        listener = actionEvent -> selectedUnit = actionEvent.getActionCommand();
        updateEntityTypes();
        this.add(unitTypePanel, BorderLayout.WEST);
        unitTypePanel.setPreferredSize(new Dimension(150, 500));
        unitTypePanel.setLayout(new BoxLayout(unitTypePanel, BoxLayout.Y_AXIS));

        //Setting up the army selector
        JPanel armyPanel = new JPanel();
        armyPanel.setLayout(new BoxLayout(armyPanel, BoxLayout.Y_AXIS));
        armySelect = new JComboBox<>();
        updateArmies();
        for (int i = 0; i < model.getArmies().size(); i++) {
            armySelect.addItem(BattleView.COLORS_NAME.get(BattleWorldView.TEAM_COLORS.get(i)));
        }
        armyPanel.add(armySelect);
        armyPanel.setPreferredSize(new Dimension(150, 500));
        add(armyPanel, BorderLayout.EAST);
        armySelect.addActionListener(actionEvent -> updateUnitList());

        //Setting up the army list
        unitList = new JPanel();
        unitList.setLayout(new BoxLayout(unitList, BoxLayout.Y_AXIS));
        JScrollPane scrll = new JScrollPane(unitList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrll.setPreferredSize(new Dimension(150, 480));
        armyPanel.add(scrll);
        model.addObserver(propertyChangeEvent -> updateUnitList(), Battle.PROPERTY_GAME_OBJECTS);
        updateUnitList();

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        okButton.addActionListener(actionEvent -> {
            mainFrame().showBattleSimulation();
            new Thread(() -> Battle.getInstance().run()).start();
        });

        armyPanel.add(okButton);

        JButton cancelButton = new JButton("Annuler");
        cancelButton.setFont(new Font("Sans Serif", Font.PLAIN, 12));

        cancelButton.addActionListener(actionEvent -> {
            mainFrame().showHomePage();
        });

        armyPanel.add(cancelButton);

    }

    private void updateEntityTypes() {
        unitTypePanel.removeAll();
        for (String buttonText : EntityFactory.getEntityTypes().stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList())) {
            JRadioButton button = new JRadioButton(buttonText);
            button.setActionCommand(buttonText);
            button.addActionListener(listener);
            group.add(button);
            unitTypePanel.add(button);
        }
    }


    /**
     * Update the list of units
     */
    private void updateUnitList() {
        unitList.removeAll();
        if (armySelect.getSelectedIndex() == -1) return;
        if (armySelect.getSelectedIndex() == 0) {
            model.getObjects().forEach(gameObject -> {
                if (gameObject instanceof Entity) {
                    unitList.add(new JLabel("- " + ((Entity) gameObject).getName()));
                }
            });
        } else {
            model.getArmies().get(armySelect.getSelectedIndex() - 1).getUnits().forEach(gameObject -> {
                unitList.add(new JLabel("- " + gameObject.getName()));
            });
        }
        unitList.updateUI();
    }

    private void updateArmies() {
        armySelect.removeAllItems();
        armySelect.addItem("Neutre");
        for (int i = 0; i < model.getArmies().size(); i++) {
            armySelect.addItem(BattleView.COLORS_NAME.get(BattleWorldView.TEAM_COLORS.get(i)));
        }
        armySelect.setSelectedIndex(0);
    }

    public void refresh() {
        updateEntityTypes();
        updateArmies();
        updateUnitList();
        battleView.updateWorld();
    }


}
