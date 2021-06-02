package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.HomePageButtonController;
import main.java.fr.enseeiht.lbs.controller.SpeedController;
import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import java.awt.*;

public class BattleSimulationView extends JPanel {

    public BattleSimulationView(World world) {
        BattleWorldView battleWorldView = new BattleWorldView(world);

        setLayout(new BorderLayout());
        add(new HomePageButtonController(), BorderLayout.NORTH);
        add(battleWorldView, BorderLayout.CENTER);
        add(new SpeedController(), BorderLayout.SOUTH);
    }
}
