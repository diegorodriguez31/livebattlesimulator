package main.java.fr.enseeiht.lbs.controller;

import javax.swing.*;

import java.awt.*;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

/**
 * Controleur qui gère la page d'accueil.
 */
public class HomePageController extends JPanel {

    public HomePageController() {
        JLabel title = new JLabel("Live Battle Simulator");
        title.setFont(new Font("Sans Serif", Font.PLAIN, 50));

        JButton battleButton = new JButton("Simuler une bataille");
        battleButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        battleButton.addActionListener(actionEvent -> {
            mainFrame().showBattleArmiesChoice();
        });

        setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraint = new GridBagConstraints();

        layoutConstraint.gridy = 1;
        add(title, layoutConstraint);

        layoutConstraint.gridy = 2;     // element line
        layoutConstraint.ipady = 100;   // element height size
        layoutConstraint.ipadx = 50;    // element width size
        layoutConstraint.insets = new Insets(100,0,0,0);  // padding
        add(battleButton, layoutConstraint);
    }
}
