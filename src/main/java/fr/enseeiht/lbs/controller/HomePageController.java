package main.java.fr.enseeiht.lbs.controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

/**
 * Controleur qui gère la page d'accueil.
 */
public class HomePageController extends JPanel {

    public HomePageController() {
        JLabel title = new JLabel("Live Battle Simulator");
        title.setFont(new Font("Sans Serif", Font.BOLD, 75));
        title.setForeground(new Color(255, 134, 0, 255));

        JButton battleButton = new JButton("Simuler une bataille");
        battleButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        battleButton.addActionListener(actionEvent -> {
            mainFrame().showBattleArmiesChoice();
        });

        JButton editorButton = new JButton("Customiser des unités");
        editorButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        editorButton.addActionListener(actionEvent -> {
            mainFrame().showUnitEditor();
        });

        setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraint = new GridBagConstraints();

        layoutConstraint.gridx = 0;
        layoutConstraint.gridy = 0;
        layoutConstraint.gridwidth = 2;
        add(title, layoutConstraint);

        layoutConstraint.gridwidth = 1;
        layoutConstraint.gridy = 1;     // element line
        layoutConstraint.ipady = 100;   // element height size
        layoutConstraint.ipadx = 50;    // element width size
        layoutConstraint.insets = new Insets(100, 0, 0, 0);  // padding
        add(battleButton, layoutConstraint);
        layoutConstraint.gridx = 1;
        add(editorButton, layoutConstraint);
    }

    /**
     * Met le "Wallpaper.png" en fond d'écran de la page
     *
     * @param graphics graphiques de la page
     */
    @Override
    public void paintComponent(Graphics graphics) {
        try {
            Image image = ImageIO.read(Objects.requireNonNull(HomePageController.class.getClassLoader().getResource("Wallpaper.png")));
            graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        } catch (IOException e) {
            e.printStackTrace();
            super.paint(graphics);
        }
    }
}
