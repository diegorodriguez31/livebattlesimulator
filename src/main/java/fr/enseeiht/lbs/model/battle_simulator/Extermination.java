package main.java.fr.enseeiht.lbs.model.battle_simulator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Définit l'objectif de bataille "Extermination".
 * Le gagnant est la dernière armée avec des unités encore en vie.
 */
public class Extermination implements Objectif{

    @Override
    public Army getWinner(Battle context) {
        List<Army> survivingArmies = context.getArmies().stream()
                .filter(army -> army.getAliveUnitCount()>0)
                .collect(Collectors.toList());
        return (survivingArmies.size() > 1 ? null : survivingArmies.get(0));
    }
}
