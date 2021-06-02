package main.java.fr.enseeiht.lbs.controller.content;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.EntityFactory;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.BattleView;
import main.java.fr.enseeiht.lbs.view.content.BattleWorldView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

public class UnitPlacement extends JPanel {
    private final Battle model;
    private String selectedUnit;
    private final JComboBox<String> armySelect;
    private JScrollPane unitList;

    public UnitPlacement(World world) {// TODO : take world from model
        this.model = Battle.getInstance();
        setLayout(new BorderLayout());

        BattleView battleView = new BattleWorldView(world);
        model.addObserver(battleView, Battle.PROPERTY_GAME_OBJECTS);
        battleView.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (selectedUnit == null) return;
                Entity entity = EntityFactory.createEntity(selectedUnit, battleView.pixelToWorld(mouseEvent.getX(), mouseEvent.getY()));
                entity.setReady();
                model.addGameObject(entity);
                if (entity instanceof Unit && armySelect.getSelectedIndex() != 0) {
                    model.getArmies().get(armySelect.getSelectedIndex() - 1).addUnit((Unit) entity);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

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

        ButtonGroup group = new ButtonGroup();

        JPanel unitTypePanel = new JPanel();
        ActionListener listener = actionEvent -> selectedUnit = actionEvent.getActionCommand();
        for (String u : EntityFactory.getEntityTypes().stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList())) {
            JRadioButton button = new JRadioButton(u);
            button.setActionCommand(u);
            button.addActionListener(listener);
            group.add(button);
            unitTypePanel.add(button);
        }
        this.add(unitTypePanel, BorderLayout.WEST);
        unitTypePanel.setPreferredSize(new Dimension(150, 500));
        unitTypePanel.setLayout(new BoxLayout(unitTypePanel, BoxLayout.Y_AXIS));

        JPanel armyPanel = new JPanel();
        armyPanel.setLayout(new BoxLayout(armyPanel, BoxLayout.Y_AXIS));
        armySelect = new JComboBox<>();
        armySelect.addItem("Neutre");
        for (int i = 0; i < model.getArmies().size(); i++) {
            armySelect.addItem(BattleView.colorsNames.get(BattleWorldView.teamColors.get(i)));
        }
        armyPanel.add(armySelect);
        armyPanel.setPreferredSize(new Dimension(150, 500));
        add(armyPanel, BorderLayout.EAST);
        armySelect.addActionListener(actionEvent -> updateUnitList());

        unitList = new JScrollPane();
        unitList.setLayout(new ScrollPaneLayout());
        unitList.setPreferredSize(new Dimension(150, 480));
        armyPanel.add(unitList);
        model.addObserver(propertyChangeEvent -> updateUnitList(), Battle.PROPERTY_GAME_OBJECTS);
        updateUnitList();
    }

    private void updateUnitList() {
        unitList.removeAll();
        if (armySelect.getSelectedIndex() == 0) {
            model.getObjects().forEach(gameObject -> {
                if (gameObject instanceof Entity) {
                    unitList.add(new JLabel(((Entity) gameObject).getName()));
                }
            });
        } else {
            model.getArmies().get(armySelect.getSelectedIndex() - 1).getUnits().forEach(gameObject -> {
                unitList.add(new JLabel(gameObject.getName()));
            });
        }
        unitList.updateUI();
    }


}
