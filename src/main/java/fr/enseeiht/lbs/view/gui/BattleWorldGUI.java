package main.java.fr.enseeiht.lbs.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import main.java.fr.enseeiht.lbs.controller.content.SpeedController;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.BattleWorldView;

@SuppressWarnings("serial")
public class BattleWorldGUI extends JFrame {

	public BattleWorldGUI(World world) {
		Battle battle = Battle.getInstance();
		BattleWorldView battleWorldView = new BattleWorldView(world);
		battle.addObserver(battleWorldView, Battle.PROPERTY_GAME_OBJECTS);
		battle.addObserver(battleWorldView, Battle.PROPERTY_RESULTS);

		this.setLayout(new BorderLayout());
		this.add(battleWorldView, BorderLayout.CENTER);
		this.add(new SpeedController(), BorderLayout.SOUTH);
		
		this.pack();
        this.setSize(new Dimension(500,500));
        this.setVisible(true);
	}
	
}
