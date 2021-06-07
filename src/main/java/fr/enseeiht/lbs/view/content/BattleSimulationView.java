package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.HomePageButtonController;
import main.java.fr.enseeiht.lbs.controller.SpeedController;

import javax.swing.*;
import java.awt.*;

/**
 * Vue qui affiche la bataille et le controleur de vitesse de déroulement de bataille.
 */
public class BattleSimulationView extends JPanel {

    private final BattleWorldView battleWorldView;

    public BattleSimulationView() {
        this.battleWorldView = new BattleWorldView();
        JPanel westpanel = new JPanel();
        JPanel eastpanel = new JPanel();
        this.battleWorldView.setPreferredSize(new Dimension(700, 700));
        westpanel.setPreferredSize(new Dimension(200,700));
        eastpanel.setPreferredSize(new Dimension(200,700));
        setLayout(new BorderLayout());
        add(westpanel,BorderLayout.WEST);
        add(eastpanel,BorderLayout.EAST);
        add(new HomePageButtonController(), BorderLayout.NORTH);
        add(battleWorldView, BorderLayout.CENTER);
        add(new SpeedController(), BorderLayout.SOUTH);
    }

    public void updateWorld() {
        battleWorldView.updateWorld();
    }
}
