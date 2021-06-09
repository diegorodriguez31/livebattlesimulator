package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.battle_simulator.InvalidBattleStateException;
import main.java.fr.enseeiht.lbs.model.game_object.Entity;
import main.java.fr.enseeiht.lbs.model.game_object.EntityFactory;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.view.content.BattleView;
import main.java.fr.enseeiht.lbs.view.content.BattleWorldView;
import main.java.fr.enseeiht.lbs.view.gui.GuiComponent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.stream.Collectors;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

public class UnitPlacementController extends JPanel implements GuiComponent {

    private final Battle model;
    private String selectedUnit;
    private final JComboBox<String> armySelect;
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
        battleWorldView.setPreferredSize(new Dimension(950,620));
        JPanel mainpanel = new JPanel();
        //mainpanel.setPreferredSize(new Dimension(900,700));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainpanel.add(battleWorldView);
        battleWorldView.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (selectedUnit == null) return;
                Entity entity = EntityFactory.createEntity(selectedUnit, battleWorldView.pixelCoordinatesToWorld(mouseEvent.getX(), mouseEvent.getY()));
                if (entity instanceof Unit && armySelect.getSelectedIndex() != 0) {
                    model.getArmies().get(armySelect.getSelectedIndex() - 1).addUnit((Unit) entity);
                }
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
        JScrollPane scrll = new JScrollPane(this.unitListView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrll.setPreferredSize(new Dimension(10, 480));

        //Setting up the army selector
        this.armySelect = new JComboBox<>();
        this.armySelect.addActionListener(actionEvent -> unitListView.updateUnitList());

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));
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
        armyPanel.setLayout(new BoxLayout(armyPanel, BoxLayout.Y_AXIS));
        armyPanel.add(this.armySelect);
        armyPanel.add(scrll);
        armyPanel.add(okButton);
        armyPanel.add(cancelButton);

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
            button.addActionListener(actionEvent -> this.selectedUnit = actionEvent.getActionCommand());
            group.add(button);
            unitTypePanel.add(button);
        }
    }

    private void updateArmies() {
        armySelect.removeAllItems();
        armySelect.addItem("Neutre");
        for (int i = 0; i < model.getArmies().size(); i++) {
            armySelect.addItem(BattleView.COLORS_NAME.get(BattleWorldView.TEAM_COLORS.get(i)));
        }
        armySelect.setSelectedIndex(0);
    }

    @Override
    public void reset() {
        battleWorldView.stopObserving();
        unitListView.stopObserving();
    }

    @Override
    public void init() {
        armySelect.setSelectedIndex(-1);
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
            if (armySelect.getSelectedIndex() == -1) return;
            if (armySelect.getSelectedIndex() == 0) {
                model.getObjects().forEach(gameObject -> {
                    if (gameObject instanceof Entity) {
                        this.add(new JLabel("- " + ((Entity) gameObject).getName()));
                    }
                });
            } else {
                model.getArmies().get(armySelect.getSelectedIndex() - 1).getUnits().forEach(gameObject -> this.add(new JLabel("- " + gameObject.getName())));
            }
            this.updateUI();
        }
    }

    public BattleWorldView getBattleWorldView() {
        return battleWorldView;
    }
}
