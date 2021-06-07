package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.HomePageButtonController;
import main.java.fr.enseeiht.lbs.controller.SpeedController;
import main.java.fr.enseeiht.lbs.view.gui.GuiComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Vue qui affiche la bataille et le controleur de vitesse de déroulement de bataille.
 */
public class BattleSimulationView extends JPanel implements GuiComponent {

    private final BattleWorldView battleWorldView;
    private final SpeedController speedController;

    private static BattleSimulationView instance;

    public static BattleSimulationView getInstance(){
        if (instance == null){
            instance = new BattleSimulationView();
        }
        return instance;
    }

    private BattleSimulationView() {
        this.battleWorldView = new BattleWorldView();
        this.speedController = new SpeedController();

        setLayout(new BorderLayout());
        add(new HomePageButtonController(), BorderLayout.NORTH);
        add(battleWorldView, BorderLayout.CENTER);
        add(speedController, BorderLayout.SOUTH);

        this.reset();
    }

    @Override
    public void reset() {
        battleWorldView.stopObserving();
    }

    @Override
    public void init() {
        battleWorldView.startObserving();
        this.speedController.init();
    }
}
