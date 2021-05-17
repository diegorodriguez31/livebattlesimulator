package main.java.fr.enseeiht.lbs.gameObject;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;

public interface GameObject {
    void start(Battle context);
    void update(Battle context, float deltaTime);
    void end(Battle context);
}
