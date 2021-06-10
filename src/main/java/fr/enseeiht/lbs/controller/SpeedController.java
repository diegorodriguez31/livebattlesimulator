package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Contrôleur qui gère le lancement, la mise en pause et la vitesse de déroulement de la bataille.
 */
public class SpeedController extends JPanel{

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");
	private static final float LOW_INCREMENT = 0.10f;
	private static final float HIGH_INCREMENT = 0.75f;
	
	private float storedDeltaTimeMultiplier;

	private Battle model;

	private JButton slowerButton;
	private JButton playButton;
	private JButton fasterButton;

	public SpeedController() {
		this.model = Battle.getInstance();

		slowerButton = new JButton("⏪");
		slowerButton.setEnabled(false);
		playButton = new JButton("▶");
		fasterButton = new JButton("⏩");
		fasterButton.setEnabled(false);

		this.setLayout(new GridLayout(1,  3));
		this.add(slowerButton);
		this.add(playButton);
		this.add(fasterButton);


		slowerButton.addActionListener(actionEvent -> {
			//Change local attribute value
			storedDeltaTimeMultiplier = model.getDeltaTimeMultiplier();
			if (storedDeltaTimeMultiplier <= Battle.DEFAULT_DELTA_TIME_MULTIPLIER){
				if (storedDeltaTimeMultiplier - LOW_INCREMENT >= Battle.MIN_DELTA_TIME_MULTIPLIER )
					storedDeltaTimeMultiplier -= LOW_INCREMENT;
			}else {
				storedDeltaTimeMultiplier -= HIGH_INCREMENT;
			}
			//Update view and model
			model.setDeltaTimeMultiplier(storedDeltaTimeMultiplier);
			updateButtons();
		});

		playButton.addActionListener(actionEvent -> {
			if (model.getDeltaTimeMultiplier() == Battle.STOPPED_DELTA_TIME_MULTIPLIER){
				model.setDeltaTimeMultiplier(storedDeltaTimeMultiplier);
			}else {
				this.storedDeltaTimeMultiplier = model.getDeltaTimeMultiplier();
				model.setDeltaTimeMultiplier(Battle.STOPPED_DELTA_TIME_MULTIPLIER);
			}
			updateButtons();
		});

		fasterButton.addActionListener(actionEvent -> {
			//Change local attribute value
			storedDeltaTimeMultiplier = model.getDeltaTimeMultiplier();
			if (storedDeltaTimeMultiplier < Battle.DEFAULT_DELTA_TIME_MULTIPLIER){
				storedDeltaTimeMultiplier += LOW_INCREMENT;
			}else {
				if (storedDeltaTimeMultiplier + HIGH_INCREMENT <= Battle.MAX_DELTA_TIME_MULTIPLIER )
					storedDeltaTimeMultiplier += HIGH_INCREMENT;
			}
			model.setDeltaTimeMultiplier(storedDeltaTimeMultiplier);
			updateButtons();
		});
		//Update view and model
		this.init();
	}

	private void updateButtons() {
		float localDeltaTimeMultiplier = model.getDeltaTimeMultiplier();

		//If time is stopped
		if ( localDeltaTimeMultiplier == Battle.STOPPED_DELTA_TIME_MULTIPLIER ) {
			//update slower button
			slowerButton.setEnabled(false);
			//update play button
			playButton.setText("▶");
			playButton.setEnabled(true);
			//update faster button
			fasterButton.setEnabled(false);
		}
		//if time is not stopped
		else {
			//update play button
			playButton.setText("⏸ (x"+ DECIMAL_FORMAT.format(storedDeltaTimeMultiplier) + ")");
			//update slower button
			slowerButton.setEnabled(localDeltaTimeMultiplier - LOW_INCREMENT >= Battle.MIN_DELTA_TIME_MULTIPLIER);
			//update faster button
			if ((localDeltaTimeMultiplier + HIGH_INCREMENT) <= Battle.MAX_DELTA_TIME_MULTIPLIER) {
				fasterButton.setEnabled(true);
			}else {
				fasterButton.setEnabled(false);
			}
		}
	}

	public void init(){
		model.setDeltaTimeMultiplier(Battle.STOPPED_DELTA_TIME_MULTIPLIER);
		this.storedDeltaTimeMultiplier = Battle.DEFAULT_DELTA_TIME_MULTIPLIER;
		updateButtons();
	}
}
