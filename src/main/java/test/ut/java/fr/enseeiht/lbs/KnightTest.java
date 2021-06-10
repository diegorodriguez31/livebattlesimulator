package main.java.test.ut.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.BreakArmorDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.PoisonDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit.Knight;
import main.java.fr.enseeiht.lbs.utils.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static main.java.fr.enseeiht.lbs.model.game.object.EntityFactory.createEntity;
import static main.java.fr.enseeiht.lbs.model.game.object.Statistic.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class KnightTest {

    private Knight knight;

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
     * Vérifie que les statistiques du chevalier sont bien initialisées à sa création
     */
    @Test
    public void testInitialStatisticsAndHealth() {
        assertEquals(100.0, knight.getHealth(), 0.1);
        assertEquals(30.0, knight.getStats().getStatisticValue(DAMAGE), 0.1);
        assertEquals(1.0, knight.getStats().getStatisticValue(COOLDOWN), 0.1);
        assertEquals(1.0, knight.getStats().getStatisticValue(SPEED), 0.1);
        assertEquals(1.0, knight.getStats().getStatisticValue(RANGE), 0.1);
        assertEquals(80.0, knight.getStats().getStatisticValue(ACCURACY), 0.1);
        assertEquals(50.0, knight.getStats().getStatisticValue(AGILITY), 0.1);
        assertEquals(50.0, knight.getStats().getStatisticValue(ARMOR), 0.1);
    }

    /**
     * Vérifie que le chevalier réduits les dégâts directs grâce à son armure
     */
    @Test
    public void testDirectDamagesWithArmor() {
        knight.receiveDamage(30.0);

        assertEquals(85.0, knight.getHealth(), 0.1);
    }

    /**
     * Vérifie que lorsque son armure est cassée, le chevalier reçoit la totalité des dégâts
     */
    @Test
    public void testDirectDamagesWithoutArmor() {
        knight.addBuffs(new BreakArmorDebuff());
        knight.receiveDamage(30.0);

        assertEquals(70.0, knight.getHealth(), 0.1);
    }

    /**
     * Vérifie que le chevalier a un certain buff déjà appliqué
     */
    @Test
    public void testHasBuff() {
        assertTrue(knight.getBuffs().isEmpty());

        FireDebuff fire = new FireDebuff(10);
        PoisonDebuff poison = new PoisonDebuff(10, 2.0);
        knight.addBuffs(fire);

        assertTrue(knight.hasBuff(fire));
        assertFalse(knight.hasBuff(poison));
    }
}
