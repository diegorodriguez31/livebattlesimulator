package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.BtnReloadMapGUI;
import main.java.fr.enseeiht.lbs.controller.CbboxElemGUI;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;

import javax.swing.*;
import java.awt.*;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.createArmies;
import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

public class WorldChoiceView extends JPanel {

    public WorldChoiceView() {

        this.setLocation(100, 200);
        this.setSize(1200, 800);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        GroupLayout layout = new GroupLayout(southPanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        southPanel.setLayout(layout);

        WorldView mapView = new WorldView();
        ChoiceWorldView choices = new ChoiceWorldView();
        northPanel.setPreferredSize(new Dimension(1200, 50));
        choices.setPreferredSize(new Dimension(300, 700));
        southPanel.setPreferredSize(new Dimension(1200, 50));
        mapView.setPreferredSize(new Dimension(700, 700));
        mapView.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        okButton.addActionListener(actionEvent -> {
            mainFrame().showBattleSimulation();

            createArmies();
            for (Army army : Battle.getInstance().getArmies()) {
                for (Unit u :
                        army.getUnits()) {
                    u.setReady();
                }
            }

            new Thread(() -> Battle.getInstance().run()).start();
        });

        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);
        this.add(mapView, BorderLayout.CENTER);
        this.add(choices, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        choices.setLayout((new BorderLayout()));
        choices.add(new CbboxElemGUI(), BorderLayout.CENTER);
        southPanel.add(new BtnReloadMapGUI());
        southPanel.add(okButton);
        this.setVisible(true);
    }
}