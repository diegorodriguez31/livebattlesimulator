package main.java.fr.enseeiht.lbs.model.gameObject;

import java.util.HashMap;
import java.util.Set;

public class Stats {
    private HashMap<Statistic, Double> stats;

    public Stats() {
        stats = new HashMap<>();
    }

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
