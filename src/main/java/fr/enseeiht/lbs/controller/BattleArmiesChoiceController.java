package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Extermination;
import main.java.fr.enseeiht.lbs.view.gui.GuiComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

/**
 * Controleur qui gère le choix nom de la bataille et le nombre d'armées.
 */
public class BattleArmiesChoiceController extends JPanel implements GuiComponent {

    private final JTextField battleName;
    private final JSpinner nbArmiesSpinner;

    private static BattleArmiesChoiceController instance;

    public static BattleArmiesChoiceController getInstance(){
        if (instance == null){
            instance = new BattleArmiesChoiceController();
        }
        return instance;
    }
    public BattleArmiesChoiceController() {
        JLabel title = new JLabel("Préparez la bataille");
        title.setFont(new Font("Sans Serif", Font.PLAIN, 50));

        battleName = new JTextField("Nom de bataille");
        battleName.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        battleName.setHorizontalAlignment(JTextField.CENTER);

        JLabel questionNbArmies = new JLabel("Combien d'armées vont d'affronter ?");
        questionNbArmies.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        nbArmiesSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 6, 1));
        nbArmiesSpinner.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        nbArmiesSpinner.setEditor(new JSpinner.DefaultEditor(nbArmiesSpinner));

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        okButton.addActionListener(actionEvent -> {
            saveValues();
            mainFrame().showWorldSelection();
        });

        // set the layout
        setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraint = new GridBagConstraints();

        int oldIpadx = layoutConstraint.ipadx;
        int oldIpadY = layoutConstraint.ipady;

        layoutConstraint.gridy = 1;
        layoutConstraint.anchor = GridBagConstraints.PAGE_START;
        layoutConstraint.insets = new Insets(10, 0, 0, 0);
        add(title, layoutConstraint);

        layoutConstraint.gridy = 2;
        layoutConstraint.ipady = 40;   // element height size
        layoutConstraint.ipadx = 200;    // element width size
        layoutConstraint.insets = new Insets(100, 0, 0, 0);
        add(battleName, layoutConstraint);

        layoutConstraint.gridy = 3;
        layoutConstraint.ipady = oldIpadY;
        layoutConstraint.ipadx = oldIpadx;
        layoutConstraint.insets = new Insets(50, 0, 0, 0);
        add(questionNbArmies, layoutConstraint);

        layoutConstraint.gridy = 4;
        layoutConstraint.ipady = 40;
        layoutConstraint.ipadx = 32;
        layoutConstraint.insets = new Insets(50, 0, 0, 0);
        add(nbArmiesSpinner, layoutConstraint);

        layoutConstraint.gridy = 6;
        layoutConstraint.ipady = oldIpadY;
        layoutConstraint.ipadx = oldIpadx;
        add(okButton, layoutConstraint);

        layoutConstraint.gridy = 7;
        add(new HomePageButtonController(), layoutConstraint);

        this.reset();
    }

    /**
     * Sauve dans le modèle le nom de la bataille et le nombre d'armées.
     */
    private void saveValues() {
        if (!battleName.getText().equals("Nom de bataille")) {
            Battle.getInstance().setName(battleName.getText());
        } else {
            Battle.getInstance().setName("Bataille");
        }

        Battle.getInstance().init(new Extermination(), (Integer) nbArmiesSpinner.getValue());
    }

    @Override
    public void reset() {
        //do nothing
    }

    @Override
    public void init(){
        battleName.setText("Nom de bataille");
        nbArmiesSpinner.setValue(2);
    }

    /**
     * Met le "Wallpaper.png" en fond d'écran de la page
     * @param graphics graphiques de la page
     */
    @Override
    public void paintComponent(Graphics graphics) {
        try {
            Image image = ImageIO.read(Objects.requireNonNull(HomePageController.class.getClassLoader().getResource("Wallpaper.png")));
            graphics.drawImage(image, 0,0, this.getWidth(), this.getHeight(), null);
        } catch (IOException e) {
            e.printStackTrace();
            super.paint(graphics);
        }
    }
}
