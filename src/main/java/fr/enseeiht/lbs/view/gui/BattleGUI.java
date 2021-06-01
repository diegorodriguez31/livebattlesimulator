package main.java.fr.enseeiht.lbs.view.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.view.content.BattleView;

@SuppressWarnings("serial")
public class BattleGUI extends JFrame {

	public BattleGUI() {
		Battle battle = Battle.getInstance();
		BattleView battleView = new BattleView();
		battle.addObserver(battleView, Battle.PROPERTY_GAME_OBJECTS);

		this.add(battleView);
		
		this.pack();
        this.setSize(new Dimension(500,500));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}
