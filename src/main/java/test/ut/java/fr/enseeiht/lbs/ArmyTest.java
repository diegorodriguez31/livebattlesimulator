package main.java.test.ut.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.Knight;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.Ninja;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit.Peasant;
import main.java.fr.enseeiht.lbs.utils.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static main.java.fr.enseeiht.lbs.model.game_object.EntityFactory.createEntity;
import static org.junit.Assert.assertEquals;

/**
 * Programme de test pour vérifier le bon fonctionnement de la classe Army.
 */
public class ArmyTest {

    private Army army;
    private Knight knight;
    private Peasant peasant;
    private Ninja ninja;

    /**
     * Crée une armée et lui ajoute une chevalier et un paysant
     * Crée un ninja
     */
    @Before
    public void setUp() {
        army = new Army(0);
        knight = (Knight) createEntity("Knight", new Vector2(0, 0));
        peasant = (Peasant) createEntity("Peasant", new Vector2(0, 0));

        army.addUnit(knight);
        army.addUnit(peasant);

        ninja = (Ninja) createEntity("Ninja", new Vector2(0, 0));
    }

    @After
    public void tearDown() {
        army = null;
    }

    /**
     * Vérifier que l'ajout d'une unité à l'armée fonctionne
     */
    @Test
    public void testAddUnit() {
        assertEquals(2, army.getUnits().size());
        army.addUnit(ninja);

        assertEquals(3, army.getUnits().size());
        assertEquals(ninja, army.getUnits().get(2));
        assertEquals(army, ninja.getTeam());
    }

    /**
     * Vérifier que l'armée sait déternimer le nombre d'unités encore en vie
     * après qu'une unité soit tuée
     */
    @Test
    public void testGetAliveUnitCountAfterKill() {
        assertEquals(2, army.getAliveUnitCount());
        knight.kill();

        assertEquals(1, army.getAliveUnitCount());
    }

    /**
     * Vérifier que l'armée sait déternimer le nombre d'unités encore en vie
     * après une attaque
     */
    @Test
    public void testGetAliveUnitCountAfterNonLethalAttack() {
        assertEquals(2, army.getAliveUnitCount());
        knight.kill();
        assertEquals(1, army.getAliveUnitCount());

        peasant.receiveDamage(10);
        assertEquals(1, army.getAliveUnitCount());
    }
}
