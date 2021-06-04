package main.java.fr.enseeiht.lbs.model.battleSimulator;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Army;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;

public interface Objectif{
    Army getWinner(Battle context);
}
