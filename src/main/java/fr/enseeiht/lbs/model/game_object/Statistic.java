package main.java.fr.enseeiht.lbs.model.game_object;

public enum Statistic {
    HEALTH("health"), DAMAGE("damage"), COOLDOWN("cooldown"), SPEED("speed"), RANGE("range"), ACCURACY("accuracy"), AGILITY("agility"), ARMOR("armor"), AREA_OF_EFFECT("area_of_effect"), SHIELD("shield");

    private String string;

    Statistic(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}