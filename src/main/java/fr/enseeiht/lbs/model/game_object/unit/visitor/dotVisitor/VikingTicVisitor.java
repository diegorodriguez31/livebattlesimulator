package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;

public class VikingTicVisitor extends BasicTicVisitor{

    private static final int WEAKNESS_MULTIPLIER = 2;

    public VikingTicVisitor(long deltaTime, Unit unit) {
        super(deltaTime * WEAKNESS_MULTIPLIER, unit);
    }
}
