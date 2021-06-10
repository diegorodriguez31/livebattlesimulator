package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.battle_simulator.InvalidBattleStateException;
import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.view.content.BattleWorldView;
import main.java.fr.enseeiht.lbs.view.gui.GuiComponent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.stream.Collectors;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

public class UnitPlacementController extends JPanel implements GuiComponent {

    private final Battle model;
    private String selectedUnit;
    private int selectedArmy;
    private final ButtonGroup armyGroup;
    private final JPanel armySelectPanel;
    private final JPanel unitTypePanel;
    private final UnitListView unitListView;
    private final ButtonGroup group;
    private final BattleWorldView battleWorldView;

    private static UnitPlacementController instance;

    public static UnitPlacementController getInstance() {
        if (instance == null) {
            instance = new UnitPlacementController();
        }
        return instance;
    }

    public UnitPlacementController() {
        model = Battle.getInstance();

        // Creates the view of the game
        battleWorldView = new BattleWorldView();
        battleWorldView.setPreferredSize(new Dimension(950, 620));
        JPanel mainpanel = new JPanel();
        mainpanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainpanel.add(battleWorldView);
        battleWorldView.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (selectedUnit == null) return;
                Entity entity = EntityFactory.createEntity(selectedUnit, battleWorldView.pixelCoordinatesToWorld(mouseEvent.getX(), mouseEvent.getY()));
                model.getArmies().get(selectedArmy).addUnit((Unit) entity);
                entity.setReady();
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
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

        // Creates the list of entities
        this.group = new ButtonGroup();

        this.unitTypePanel = new JPanel();
        this.unitTypePanel.setPreferredSize(new Dimension(100, 700));
        this.unitTypePanel.setLayout(new BoxLayout(unitTypePanel, BoxLayout.Y_AXIS));

        //Setting up the army list
        this.unitListView = new UnitListView();
        JScrollPane scroll = new JScrollPane(this.unitListView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(10, 480));

        //Setting up the army select
        this.armySelectPanel = new JPanel();
        this.armySelectPanel.setLayout(new BoxLayout(armySelectPanel, BoxLayout.Y_AXIS));
        this.armyGroup = new ButtonGroup();
        this.selectedArmy = 0;
        Collections.list(group.getElements()).forEach((button) -> {
            button.addActionListener(actionEvent -> unitListView.updateUnitList());
        });

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        okButton.addActionListener(actionEvent -> {
            try {
                Battle.getInstance().runAsync();
                mainFrame().showBattleSimulation();
            } catch (InvalidBattleStateException e) {
                System.err.println(e.getMessage());
            }
        });

        JButton cancelButton = new HomePageButtonController();
        cancelButton.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        JPanel armyPanel = new JPanel();
        armyPanel.setPreferredSize(new Dimension(200, 500));
        armyPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        armyPanel.add(this.armySelectPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        armyPanel.add(scroll, gridBagConstraints);
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        armyPanel.add(okButton, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        armyPanel.add(cancelButton, gridBagConstraints);

        this.setLayout(new BorderLayout());
        this.add(mainpanel, BorderLayout.CENTER);
        this.add(this.unitTypePanel, BorderLayout.WEST);
        this.add(armyPanel, BorderLayout.EAST);

        this.reset();
    }

    private void updateEntityTypes() {
        this.unitTypePanel.removeAll();
        for (String buttonText : EntityFactory.getEntityTypes().stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList())) {
            JRadioButton button = new JRadioButton(buttonText);
            button.setActionCommand(buttonText);
            button.addActionListener(actionEvent -> {
                this.selectedUnit = actionEvent.getActionCommand();
            });
            group.add(button);
            unitTypePanel.add(button);
        }
    }

    private void updateArmies() {
        // Remove all the buttons from the groupButton
        armySelectPanel.removeAll();
        Collections.list(armyGroup.getElements()).forEach(armyGroup::remove);
        for (int i = 0; i < model.getArmies().size(); i++) {
            int tmpI = i;
            JRadioButton armyButton = new JRadioButton(BattleWorldView.COLORS_NAME.get(BattleWorldView.TEAM_COLORS.get(i)));
            armyButton.addActionListener(e -> {
                selectedArmy = tmpI;
                unitListView.updateUnitList();
            });
            if (i == 0) armyButton.setSelected(true);
            armyGroup.add(armyButton);
            armySelectPanel.add(armyButton);
            if(i == selectedArmy){
                armyButton.setSelected(true);
            }
        }
    }

    @Override
    public void reset() {
        battleWorldView.stopObserving();
        unitListView.stopObserving();
    }

    @Override
    public void init() {
        updateEntityTypes();
        updateArmies();
        battleWorldView.startObserving();
        unitListView.startObserving();

    }

    private class UnitListView extends JPanel implements PropertyChangeListener {
        public UnitListView() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        @Override
        public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
            if (propertyChangeEvent.getPropertyName().equals(Battle.PROPERTY_GAME_OBJECTS)) {
                updateUnitList();
            }
        }

        public void startObserving() {
            updateUnitList();
            Battle.addObserver(this, Battle.PROPERTY_GAME_OBJECTS);
        }

        public void stopObserving() {
            Battle.removeObserver(this, Battle.PROPERTY_GAME_OBJECTS);
        }

        /**
         * Update the list of units
         */
        private void updateUnitList() {
            this.removeAll();
            model.getArmies().get(selectedArmy).getUnits().forEach(gameObject -> this.add(new JLabel("- " + gameObject.getName())));
            this.updateUI();
        }
    }

    public BattleWorldView getBattleWorldView() {
        return battleWorldView;
    }
}
