package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.BattleArmiesChoiceController;
import main.java.fr.enseeiht.lbs.controller.HomePageController;
import main.java.fr.enseeiht.lbs.controller.UnitPlacementControler;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.BattleSimulationView;
import main.java.fr.enseeiht.lbs.view.content.WorldChoiceView;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale de l'application
 * Elle change le contenu mais ne se ferme jamais.
 */
public class LiveBattleSimulatorGUI extends JFrame {

    static LiveBattleSimulatorGUI instance;

    static JPanel cards;

    private BattleSimulationView battleSimulationView;
    private UnitPlacementControler unitPlacementControler;


    /**
     * Identifiants des cards
     */
    static final String HOME_PAGE_CARD = "HOME_PAGE_CARD";
    static final String ARMIES_NB_CHOICES_CARD = "ARMIES_NB_CHOICES_CARD";
    static final String BATTLE_SIMULATION_CARD = "BATTLE_SIMULATION_CARD";
    static final String WORLD_CHOICE_CARD = "WORLD_CHOICE_CARD";
    public static final String UNIT_PLACEMENT_CARD = "UNIT_PLACEMENT_CARD";


    /**
     * Singleton pour n'avoir qu'une seule instance de la fenêtre.
     *
     * @return l'instance de l'objet
     */
    public static LiveBattleSimulatorGUI getInstance() {
        if (instance == null) {
            instance = new LiveBattleSimulatorGUI();
        }
        return instance;
    }

    /**
     * Crée le cardlayout de la fenêtre et affiche la page d'accueil.
     */
    private LiveBattleSimulatorGUI() {
        cards = new JPanel(new CardLayout());
        getContentPane().add(cards);

        cards.add(new HomePageController(), HOME_PAGE_CARD);

        World world = World.getInstance();
        world.generateWorld(10, 20, 5, 25, 40);
        cards.add(new WorldChoiceView(), WORLD_CHOICE_CARD);
        unitPlacementControler = new UnitPlacementControler();

        showHomePage();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static JPanel getCards() {
        return cards;
    }

    /**
     * Affiche la page d'accueil.
     */
    public void showHomePage() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, HOME_PAGE_CARD);
        setChangesReady();
    }

    /**
     * Affiche le menu de selection
     * du nom de bataille
     * du nombre d'armées
     */
    public void showBattleArmiesChoice() {
        cards.add(new BattleArmiesChoiceController(), ARMIES_NB_CHOICES_CARD);
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, ARMIES_NB_CHOICES_CARD);
        setChangesReady();
    }

    /**
     * Afficher le menu de selection du terrain
     */
    public void showWorldSelection() {
        // Crée une nouvelle BattleSimulationView à chaque passage
        // Necessaire pour afficher les éléments swings avant que la bataille ne run (Thread concurrence).
        battleSimulationView = new BattleSimulationView();
        cards.add(battleSimulationView, BATTLE_SIMULATION_CARD);

        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, WORLD_CHOICE_CARD);
        setChangesReady();
    }

    /**
     * Afficher le menu de selection du terrain
     */
    public void showUnitPlacement() {
        // Crée une nouvelle BattleSimulationView à chaque passage
        // Necessaire pour afficher les éléments swings avant que la bataille ne run (Thread concurrence).

        cards.add(unitPlacementControler, UNIT_PLACEMENT_CARD);
        unitPlacementControler.refresh();

        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, UNIT_PLACEMENT_CARD);
        setChangesReady();
    }

    /**
     * Affiche la vue de la bataille.
     */
    public void showBattleSimulation() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, BATTLE_SIMULATION_CARD);
        battleSimulationView.updateWorld();

        setChangesReady();
    }

    /**
     * Rend les panels prêts à l'affichage.
     */
    private void setChangesReady() {
        pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
