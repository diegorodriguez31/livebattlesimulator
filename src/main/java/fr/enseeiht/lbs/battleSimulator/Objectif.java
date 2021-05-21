package main.java.fr.enseeiht.lbs.battleSimulator;

import main.java.fr.enseeiht.lbs.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.battleSimulator.Battle;

public interface Objectif {
    Army getWinner(Battle context);
}
