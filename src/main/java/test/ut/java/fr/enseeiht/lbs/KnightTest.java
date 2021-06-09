package main.java.test.ut.java.fr.enseeiht.lbs;

import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.BreakArmorDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.PoisonDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.Knight;
import main.java.fr.enseeiht.lbs.utils.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static main.java.fr.enseeiht.lbs.model.game_object.EntityFactory.createEntity;
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

    /**
     * Vérifie que le chevalier réduits les dégâts directs grâce à son armure
     */
    @Test
    public void testDirectDamagesWithArmor() {
        assertEquals(100.0, knight.getHealth(), 0.1);
        knight.receiveDamage(30.0);

        assertEquals(85.0, knight.getHealth(), 0.1);
    }

    /**
     * Vérifie que lorsque son armure est cassée, le chevalier reçoit la totalité des dégâts
     */
    @Test
    public void testDirectDamagesWithoutArmor() {
        assertEquals(100.0, knight.getHealth(),0.1);
        knight.addBuffs(new BreakArmorDebuff());
        knight.receiveDamage(30.0);

        assertEquals(70.0, knight.getHealth(), 0.1);
    }
}
