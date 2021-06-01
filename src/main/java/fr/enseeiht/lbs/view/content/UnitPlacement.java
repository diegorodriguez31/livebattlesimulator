package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.EntityFactory;
import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

public class UnitPlacement extends JPanel {
    private final Battle model;
    private String selectedUnit;
    private String selectedArmy;

    public UnitPlacement(World world) {// TODO : take world from model
        this.model = Battle.getInstance();
        setLayout(new BorderLayout());

        BattleView battleView = new BattleWorldView(world);
        model.addGameObjectsObserver(battleView);
        battleView.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (selectedUnit == null) return;
                Entity entity = EntityFactory.createEntity(selectedUnit, battleView.pixelToWorld(mouseEvent.getX(), mouseEvent.getY()));
                entity.setReady();
                model.addGameObject(entity);
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
        JComboBox<String> armyselect = new JComboBox<>();
        for (int i = 0; i < model.getArmies().size(); i++) {
            armyselect.addItem("army " + i);
        }
        armyPanel.add(armyselect);
        armyPanel.setPreferredSize(new Dimension(150, 500));
        add(armyPanel, BorderLayout.EAST);
    }


}
