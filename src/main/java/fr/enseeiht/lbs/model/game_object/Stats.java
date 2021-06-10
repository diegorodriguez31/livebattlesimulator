package main.java.fr.enseeiht.lbs.model.game_object;

import java.util.HashMap;
import java.util.Set;

/**
 * Map et manipule des statistiques.
 */
public class Stats {
    private HashMap<Statistic, Double> stats;

    public Stats() {
        stats = new HashMap<>();
    }

    /**
     * Constructeur de copie.
     */
    public Stats(Stats stats){
        this.stats = new HashMap<>();
        this.stats.putAll(stats.getStatistics());
    }

    public void addStat(Statistic statistic, double statValue){
        stats.put(statistic, statValue);
    }

    public double getStatisticValue(Statistic stat){
        return (getStatisticsSet().contains(stat) ? stats.get(stat) : 0.0);
    }

    public Set<Statistic> getStatisticsSet() {
        return stats.keySet();
    }

    public boolean containsStat(Statistic stat){
        return stats.containsKey(stat);
    }

    public HashMap<Statistic, Double> getStatistics() {
        return stats;
    }
}
