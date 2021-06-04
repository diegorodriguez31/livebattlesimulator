package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.BattleArmiesChoiceController;
import main.java.fr.enseeiht.lbs.controller.HomePageController;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.BattleSimulationView;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale de l'application
 * Elle change le contenu mais ne se ferme jamais.
 */
public class LiveBattleSimulatorGUI extends JFrame {

    static LiveBattleSimulatorGUI instance;

    static JPanel cards;

    /**
     * Identifiants des cards
     */
    static String HOME_PAGE_CARD = "HOME_PAGE_CARD";
    static String ARMIES_NB_CHOICES_CARD = "ARMIES_NB_CHOICES_CARD";
    static String BATTLE_SIMULATION_CARD = "BATTLE_SIMULATION_CARD";

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
        // TODO le world est créé ici car nous n'y avons pas accès actuelement.
        // Ce constructeur doit être supprimé et remplacé par un World.getInstance lors de l'ajout de cette fonctionnalité.
        World world = new World(20, 20, 35, 10, 5, 50);

        // Crée une nouvelle BattleSimulationView à chaque passage : Necessaire pour avoir un affichage cohérent.
        //TODO Deplacer ce code lorsque la fenetre de creation de World existera
        cards.add(new BattleSimulationView(world), BATTLE_SIMULATION_CARD);

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
     * Affiche la vue de la bataille.
     */
    public void showBattleSimulation() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, BATTLE_SIMULATION_CARD);
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
