package main.java.fr.enseeiht.lbs.model.gameObject;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;

public abstract class GameObject {

    public void setReady(){
        Battle.getInstance().addGameObject(this);
    }

    public void removeFromBattle(){
        Battle.getInstance().removeGameObject(this);
    }

    abstract public void start(Battle context);
    abstract public void update(Battle context, long deltaTime);
    abstract public void end(Battle context);
}
