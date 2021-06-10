package main.java.fr.enseeiht.lbs.model.game.object.unit;

import main.java.fr.enseeiht.lbs.LiveBattleSimulator;
import main.java.fr.enseeiht.lbs.model.battle.simulator.Army;
import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.Entity;
import main.java.fr.enseeiht.lbs.model.game.object.Statistic;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.Action;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.AI;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.Buff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.buff.PoisonDebuff;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.utils.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.VERBOSE;
import static main.java.fr.enseeiht.lbs.model.game.object.Statistic.ACCURACY;
import static main.java.fr.enseeiht.lbs.model.game.object.Statistic.AGILITY;

/**
 * Classe très importante de l'application !
 * Factorise le comportement de base des unités qui combattent lors des simulations.
 */
public abstract class Unit extends Entity {
    /**
     * Intelligence artificielle de l'unité, elle lui donne les actions à exécuter
     */
    protected AI ai;

    /**
     * Liste des buffs appliqués à l'unité
     */
    protected List<Buff> buffs = new ArrayList<>();

    /**
     * Temps d'attente entre chaque action de l'unité
     */
    protected double cooldown;

    /**
     * Armée à laquelle l'unité appartient
     */
    private Army team;

    // create basic fighting unit
    public Unit(Vector2 vector, String name, double health, double damage, double cooldown, double speed, double range, double accuracy, double agility) {
        super(name, health, vector);
        stats = new Stats();
        stats.addStat(Statistic.DAMAGE, damage);
        stats.addStat(Statistic.COOLDOWN, cooldown);
        stats.addStat(Statistic.SPEED, speed);
        stats.addStat(Statistic.RANGE, range);
        stats.addStat(ACCURACY, accuracy);
        stats.addStat(Statistic.AGILITY, agility);
        this.cooldown = cooldown;
    }

    public Unit(String name, double health, Vector2 position) {
        super(name, health, position);
    }

    public Unit(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
    }

    /**
     * Au travers d'un visiteur, on copie les statistiques de base de l'unité
     * pour y appliquer les buffs.
     * @return les statisques modifiées par les buffs présents sur l'unité
     */
    @Override
    public Stats getStats() {
        BasicStatModifierBuffVisitor visitor = getStatModifierVisitor();
        for (Buff buff : buffs) {
            buff.accept(visitor);
        }
        return visitor.getStats();
    }

    /**
     * Affiche des informations sur les statistiques de l'unité
     */
    public void status() {
        System.out.println(getName() + " status :");
        System.out.println("\tHealth : " + getHealth());
        System.out.println("\tDamage : " + getStats().getStatisticValue(Statistic.DAMAGE));
        System.out.println("\tCooldown : " + getStats().getStatisticValue(Statistic.COOLDOWN));
        System.out.println("\tSpeed : " + getStats().getStatisticValue(Statistic.SPEED));
        System.out.println("\tRange : " + getStats().getStatisticValue(Statistic.RANGE));
        System.out.println("\tAccuracy : " + getStats().getStatisticValue(ACCURACY));
        System.out.println("\tAgility : " + getStats().getStatisticValue(Statistic.AGILITY));
        System.out.println("\tElement : "+getFieldElement());
    }

    /**
     * Méthode très importante !
     * Comportment de l'unité exécuté à chaque "boucle" lors de la simulation bataille
     *      L'ia de l'unité lui donne les actions à exécuter
     *      On lui applique les tics de dégâts liés à ses buffs si elle en a
     *
     * Méthode inspirée du fonctionnement du moteur de jeu Unity
     *
     * @param context élément (ici la bataille) contenant toutes les informations sur les objets présents lors de la simulation
     * @param deltaTime temps (en millisecondes) mémorisé et partagé par tous les objets de la simulation à chaque "boucle"
     *                  permet aux unités d'agir virtuellement au même moment (et éviter le tour par tour)
     */
    @Override
    public void update(Battle context, long deltaTime) {
        for (Action a :
                ai.getActions(this, context, deltaTime)) {
            a.execute(deltaTime);
        }
        if (VERBOSE >= 2) {
            status();
        }
        // update buffs
        BasicTicVisitor visitor = getTicVisitor(deltaTime);
        for (Iterator<Buff> iterator = buffs.iterator(); iterator.hasNext(); ) {
            Buff buff = iterator.next();
            buff.accept(visitor);
            if(buff.isFinished()) iterator.remove();
        }
    }

    /**
     * Appliquer des dégats à l'unité
     * L'unité peut esquiver l'attaque (dodge()) et ainsi ne recevoir aucun dégat
     * Si les dégats qu'elle reçoit lui sont fatal, elle est retirée de la bataille
     *
     * @param damage montant de dégâts à recevoir
     */
    public void receiveDamage(double damage) {
        if (!dodge()) {
            health -= damage;
            if (LiveBattleSimulator.VERBOSE >= 2) {
                this.status();
            }
            if (isDead()) {
                removeFromBattle();
            }
        }
    }

    /**
     * On ajoute un buff à une unité s'il n'est pas déjà présent
     *
     * @param buff le buff à appliquer à l'unité
     */
    public void addBuffs(Buff buff) {
        if (!hasBuff(buff)) {
            buffs.add(buff);
        }
    }

    /**
     * Récupère les comportements de l'unité avec les buffs qui modifient les statisiques
     *
     * @return BasicStatModifierBuffVisitor le visiteur qui spécifie les comportements de l'unité
     */
    protected BasicStatModifierBuffVisitor getStatModifierVisitor() {
        return new BasicStatModifierBuffVisitor(stats, this);
    }

    /**
     * Récupère les comportements de l'unité avec les buffs qui appliquent des tics de dégâts
     *
     * @return BasicTicVisitor le visiteur qui spécifie les comportements de l'unité
     */
    protected BasicTicVisitor getTicVisitor(long deltaTime) {
        return new BasicTicVisitor(deltaTime, this);
    }

    /**
     * Les unité ont une statistique de précision, leurs attaques n'atteignent pas leur cible à chaque fois
     *
     * @return true si l'attaque est un succès, false sinon
     */
    public boolean attackSuccess() {
        Random random = new Random();
        return (random.nextInt(100) + 1) < getStats().getStatisticValue(ACCURACY);
    }

    /**
     * Les unité ont une statistique de'agilité, elles peuvent esquiver certaines attaques
     *
     * @return true si l'esquive est un succès, false sinon
     */
    public boolean dodge() {
        Random random = new Random();
        return (random.nextInt(100) + 1) < getStats().getStatisticValue(AGILITY);
    }

    public Army getTeam() {
        return team;
    }

    public void setTeam(Army team) {
        this.team = team;
    }

    /**
     * Indique la présence d'un buff déjà appliqué sur l'unité
     *
     * @param buff le buff à appliquer à l'unité
     * @return true si le buff est déjà présent sur l'unité
     */
    public boolean hasBuff(Buff buff) {
        for (Buff currentBuff : buffs) {
            if (currentBuff.getClass() == buff.getClass()) {
                return true;
            }
        }
        return false;
    }

    public List<Buff> getBuffs() {
        return buffs;
    }

    public Stats getRawStats() {
        return stats;
    }
}
