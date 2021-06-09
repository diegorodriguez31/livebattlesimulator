package main.java.test.it.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.BreakArmorDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PoisonDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.SlowDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.Knight;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static main.java.fr.enseeiht.lbs.model.game_object.EntityFactory.createEntity;
import static main.java.fr.enseeiht.lbs.model.game_object.Statistic.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class KnightBuffBehaviourTest {

    private Knight knight = spy((Knight) createEntity("Knight", new Vector2(0, 0)));
    private static int DELTA_TIME_IN_MILLISECONDS = 1000;
    private static double KNIGHT_RAW_HEALTH = 100.0;
    private static double KNIGHT_RAW_DAMAGE = 30.0;
    private static double KNIGHT_RAW_COOLDOWN = 1.0;
    private static double KNIGHT_RAW_SPEED = 1.0;
    private static double KNIGHT_RAW_RANGE = 1.0;
    private static double KNIGHT_RAW_ACCURACY = 80.0;
    private static double KNIGHT_RAW_AGILITY = 50.0;
    private static double KNIGHT_RAW_ARMOR = 50.0;

    /**
     * Vérifie si les statistiques du chevalier correspondent à celle donner en paramètre. Cela permet surtout
     * de vérifier que seulement la stat concernée a été modifiée
     * @param target cible attendue
     * @param damage dégâts attendus
     * @param cooldown cooldown attendu
     * @param speed vitesse attendue
     * @param range portée attendue
     * @param accuracy précision attendue
     * @param agility agilité attendue
     * @param armor armure attendue
     */
    private static void checkStatsModifications(Knight target, double damage, double cooldown,
                                                double speed, double range, double accuracy, double agility, double armor) {
        assertEquals(damage, target.getStats().getStatisticValue(DAMAGE), 0.1);
        assertEquals(cooldown, target.getStats().getStatisticValue(COOLDOWN), 0.1);
        assertEquals(speed, target.getStats().getStatisticValue(SPEED), 0.1);
        assertEquals(range, target.getStats().getStatisticValue(RANGE), 0.1);
        assertEquals(accuracy, target.getStats().getStatisticValue(ACCURACY), 0.1);
        assertEquals(agility, target.getStats().getStatisticValue(AGILITY), 0.1);
        assertEquals(armor, target.getStats().getStatisticValue(ARMOR), 0.1);
    }

    /**
     * Applique les tics de dégâts en fonction des buffs présents sur la cible
     * @param knight le chevalier
     */
    private static void applyTicDamage(Knight knight) {
        BasicTicVisitor visitor = knight.getTicVisitor(DELTA_TIME_IN_MILLISECONDS);
        for (Buff buff : knight.getBuffs()) {
            buff.accept(visitor);
        }
    }

    /**
     * Mock le chevalier afin de désactiver l'esquive.
     * Le chevalier esquive aléatoirement la classe en ne reçoit pas de dégâts
     */
    @Before
    public void setUp() {
        knight = spy((Knight) createEntity("Knight", new Vector2(0, 0)));
        when(knight.dodge()).thenReturn(false);
    }

    @After
    public void tearDown() {
        knight = null;
    }

    /**
     * Vérifie que l'armure du chevalier le rend insensible au poison
     * Vérifie que l'armure ne protège pas de la modification des statistiques que le poison applique (cooldown et speed)
     */
    @Test
    public void testPoisonDebuffWithArmor() {
        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
        knight.addBuffs(new PoisonDebuff(20, 2.0));
        applyTicDamage(knight);

        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, 2.0, 0.5, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, KNIGHT_RAW_ARMOR);
    }

    /**
     * Vérifie que le chevalier subit les dégâts des tics de feu et de poison s'il n'a pas d'armure
     * Vérifie la bonne modification des statistiques que le poison applique (cooldown et speed) s'il n'a pas d'armure
     */
    @Test
    public void testPoisonDebuffWithoutArmor() {
        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
        knight.addBuffs(new BreakArmorDebuff());
        knight.addBuffs(new PoisonDebuff(20, 2.0));
        applyTicDamage(knight);

        assertEquals(80.0, knight.getHealth(), 0.1);
        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, 2.0, 0.5, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, 0.0);
    }

    /**
     * Vérifie que l'armure du chevalier le rend insensible aux tics de dégâts de feu
     * Vérifie que les statistiques sont non modifiées
     */
    @Test
    public void testFireDebuffWithArmor() {
        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
        applyTicDamage(knight);

        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);

        assertEquals(KNIGHT_RAW_DAMAGE, knight.getStats().getStatisticValue(DAMAGE), 0.1);
    }

    /**
     * Vérifie que le buff Breakarmor appliqué sur le chevalier lui met l'armure à 0
     */
    @Test
    public void testBreakArmorDebuff() {
        assertEquals(KNIGHT_RAW_ARMOR, knight.getStats().getStatisticValue(Statistic.ARMOR), 0.1);
        knight.addBuffs(new BreakArmorDebuff());
        assertEquals(0.0, knight.getStats().getStatisticValue(Statistic.ARMOR), 0.1);
    }


    // slowdebuff
    @Test
    public void testSlowDebuff() {
        assertEquals(1.0, knight.getStats().getStatisticValue(Statistic.SPEED), 0.1);
        knight.addBuffs(new SlowDebuff(0.5));
        assertEquals(0.5, knight.getStats().getStatisticValue(Statistic.SPEED), 0.1);
    }
    // speedbuff

    // stun

    // peasant group buff

}
