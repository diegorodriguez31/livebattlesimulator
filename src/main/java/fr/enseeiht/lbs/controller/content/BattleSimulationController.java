package main.java.fr.enseeiht.lbs.controller.content;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Extermination;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.BattleWorldView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.createArmies;
import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

public class BattleSimulationController extends JPanel {

    JButton homePageButton;

    public BattleSimulationController() {
        homePageButton = new JButton("Home Page");

        homePageButton.addActionListener(actionEvent -> {
            mainFrame().showHomePage();
        });

        List<Army> armies = createArmies();
        Battle.getInstance().init(new Extermination(), armies);
        for (Army army : armies) {
            for (Unit u :
                    army.getUnits()) {
                u.setReady();
            }
        }

        World world = new World(20, 20, 35, 10, 5, 50);

        BattleWorldView battleWorldView = new BattleWorldView(world);
        Battle.getInstance().addGameObjectsObserver(battleWorldView);

        setLayout(new BorderLayout());
        add(new SpeedController(Battle.getInstance()), BorderLayout.SOUTH);
        add(homePageButton, BorderLayout.NORTH);
        add(battleWorldView, BorderLayout.CENTER);
    }
}
