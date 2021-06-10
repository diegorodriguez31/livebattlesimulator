package main.java.test.it.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.*;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit.Knight;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static main.java.fr.enseeiht.lbs.model.game.object.EntityFactory.createEntity;
import static main.java.fr.enseeiht.lbs.model.game.object.Statistic.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Teste les comportements du chevalier (Knight) pour chaque buff qu'il reçoit
 */
public class KnightBuffBehaviourTest {

    /**
     * Cible des buffs dont on vérifie les bons comportements
     */
    private Knight knight;

    /**
     * Temps entre chaque tic de dégâts (en millisecondes)
     */
    private static final int DELTA_TIME_IN_MILLISECONDS = 1000;

    /**
     * Statistiques brutes du chevalier
     */
    private final double KNIGHT_RAW_HEALTH = 100.0;
    private final double KNIGHT_RAW_DAMAGE = 30.0;
    private final double KNIGHT_RAW_COOLDOWN = 1.0;
    private final double KNIGHT_RAW_SPEED = 1.0;
    private final double KNIGHT_RAW_RANGE = 1.0;
    private final double KNIGHT_RAW_ACCURACY = 80.0;
    private final double KNIGHT_RAW_AGILITY = 50.0;
    private final double KNIGHT_RAW_ARMOR = 50.0;

    /**
     * Vérifie si les statistiques du chevalier correspondent à celles données en paramètre. Cela permet surtout
     * de vérifier que seulement la ou les statistiques concernées ont été modifié
     * @param target cible des buffs
     * @param damage dégâts que la cible peut provoquer attendus
     * @param cooldown temps entre chaque attaque attendu
     * @param speed vitesse attendue
     * @param range portée attendue
     * @param accuracy précision attendue
     * @param agility agilité attendue
     * @param armor armure attendue
     */
    private void checkStatsModifications(Knight target, double damage, double cooldown,
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
     */
    private void applyTicDamage() {
        BasicTicVisitor visitor = knight.getTicVisitor(DELTA_TIME_IN_MILLISECONDS);
        for (Buff buff : knight.getBuffs()) {
            buff.accept(visitor);
        }
    }

    /**
     * Mock le chevalier afin de désactiver l'esquive
     * Le chevalier esquive aléatoirement les dégâts pour ne pas en recevoir
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
        knight.addBuffs(new PoisonDebuff(20, 2.0, 1000));
        applyTicDamage();

        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, 2.0, 0.5, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, KNIGHT_RAW_ARMOR);
    }

    /**
     * Vérifie que le chevalier subit les dégâts des tics de poison s'il n'a pas d'armure
     * Vérifie la bonne modification des statistiques que le poison applique (cooldown et speed) s'il n'a pas d'armure
     */
    @Test
    public void testPoisonDebuffWithoutArmor() {
        knight.addBuffs(new BreakArmorDebuff());
        knight.addBuffs(new PoisonDebuff(20, 2.0, 1000));
        applyTicDamage();

        assertEquals(80.0, knight.getHealth(), 0.1);
        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, 2.0, 0.5, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, 0.0);
    }

    /**
     * Vérifie que l'armure du chevalier le rend insensible aux tics de dégâts de feu
     * Vérifie que les statistiques sont non modifiées (le feu ne modifie pas les statistiques)
     */
    @Test
    public void testFireDebuffWithArmor() {
        knight.addBuffs(new FireDebuff(10, 1000));
        applyTicDamage();

        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, KNIGHT_RAW_COOLDOWN, KNIGHT_RAW_SPEED, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, KNIGHT_RAW_ARMOR);
    }

    /**
     * Vérifie que le chevalier reçoit les tics de dégâts de feu s'il n'a pas d'armure
     * Vérifie que les statistiques sont non modifiées (le feu ne modifie pas les statistiques) s'il na pas d'armure
     */
    @Test
    public void testFireDebuffWithoutArmor() {
        knight.addBuffs(new BreakArmorDebuff());
        knight.addBuffs(new FireDebuff(10, 1000));
        applyTicDamage();

        assertEquals(90, knight.getHealth(), 0.1);
        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, KNIGHT_RAW_COOLDOWN, KNIGHT_RAW_SPEED, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, 0.0);
    }

    /**
     * Vérifie que le buff BreakArmor appliqué sur le chevalier lui détruit l'armure
     * Vérifie qu'il n'y a pas de tics de dégâts
     * Vérifie que la santé et les statistiques non concernées ne sont pas affectées par le buff
     */
    @Test
    public void testBreakArmorDebuff() {
        knight.addBuffs(new BreakArmorDebuff());
        applyTicDamage();

        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, KNIGHT_RAW_COOLDOWN, KNIGHT_RAW_SPEED, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, 0.0);
    }

    /**
     * Vérifie que l'armure n'est pas encore réduite à nouveau si elle est déjà détruite
     */
    @Test
    public void testBreakArmorDebuffWithoutArmor() {
        knight.addBuffs(new BreakArmorDebuff());
        testBreakArmorDebuff();
    }

    /**
     * Vérifie que le buff SlowDebuff appliqué sur le chevalier réduit sa vitesse de déplacement
     * Vérifie qu'il n'y a pas de tics de dégâts
     * Vérifie que la santé et les statistiques non concernées ne sont pas affectées par le buff
     */
    @Test
    public void testSlowDebuffWithArmor() {
        knight.addBuffs(new SlowDebuff(0.5, 1000));
        applyTicDamage();

        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, KNIGHT_RAW_COOLDOWN, 0.5, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, KNIGHT_RAW_ARMOR);
    }

    /**
     * Vérifie que sans armure, c'est le même comportement que testSlowDebuffWithArmor
     */
    @Test
    public void testSlowDebuffWithoutArmor() {
       knight.addBuffs(new BreakArmorDebuff());
       knight.addBuffs(new SlowDebuff(0.5, 1000));
       applyTicDamage();

       assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
       checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, KNIGHT_RAW_COOLDOWN, 0.5, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, 0.0);
    }

    /**
     * Vérifie que le buff Stun appliqué sur le chevalier augmente le temps entre chacune de ses attaques (cooldown)
     * Vérifie qu'il n'y a pas de tics de dégâts
     * Vérifie que la santé et les statistiques non concernées ne sont pas affectées par le buff
     */
    @Test
    public void testStunDebuffWithArmor() {
        knight.addBuffs(new StunDebuff(2.5));
        applyTicDamage();

        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, 3.5, KNIGHT_RAW_SPEED, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, KNIGHT_RAW_ARMOR);
        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
    }

    /**
     * Vérifie que sans armure, c'est le même comportement que testStunDebuffWithArmor
     */
    @Test
    public void testStunDebuffWithoutArmor() {
        knight.addBuffs(new StunDebuff(2.5));
        knight.addBuffs(new BreakArmorDebuff());
        applyTicDamage();

        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, 3.5, KNIGHT_RAW_SPEED, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, 0.0);
        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
    }

    /**
     * Vérifie que le buff PeasantGroup appliqué sur le chevalier est ignoré car ce n'est pas un paysant
     * On vérifie ici le comportement d'un cas d'erreur car où un buff lié aux paysants ne doit pas être appliqué à un chevalier
     */
    @Test
    public void testPeasantGroupBuffWithArmor() {
        knight.addBuffs(new PeasantGroupBuff(4.0,2.0));
        applyTicDamage();

        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, KNIGHT_RAW_COOLDOWN, KNIGHT_RAW_SPEED, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, KNIGHT_RAW_ARMOR);
        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
    }

    /**
     * Vérifie que sans armure, c'est le même comportement que testPeasantGroupBuffWithArmor
     */
    @Test
    public void testPeasantGroupBuffWithoutArmor() {
        knight.addBuffs(new BreakArmorDebuff());
        knight.addBuffs(new PeasantGroupBuff(4.0,2.0));
        applyTicDamage();

        checkStatsModifications(knight, KNIGHT_RAW_DAMAGE, KNIGHT_RAW_COOLDOWN, KNIGHT_RAW_SPEED, KNIGHT_RAW_RANGE, KNIGHT_RAW_ACCURACY, KNIGHT_RAW_AGILITY, 0.0);
        assertEquals(KNIGHT_RAW_HEALTH, knight.getHealth(), 0.1);
    }
}
