package com.ghstsch.dangerousforest.players;

import java.util.Vector;

/**
 * Created by Aaa on 24.06.2015.
 */
public class PlayerStats {
    private int statsCount;
    private float statValue[];
    private float statUpCost[];
    private Vector<String> statName;

    public PlayerStats() {
        statValue = new float[20];
        statUpCost = new float[20];
        statName = new Vector<String>();
    }

    public String getStatName(int id) {
        if(id < statsCount) return statName.get(id);
        else return "null";
    }

    public int getStatsCount() {
        return statsCount;
    }

    public float getStatValue(int id) {
        if(id < statsCount) return statValue[id];
        else return 0.0f;
    }

    public float getStatCost(int id) {
        if(id < statsCount) return statUpCost[id];
        else return 0.0f;
    }

    public void addStat(String name) {
        statsCount++;
        statName.add(name);
    }

    public void removeStats() {
        statsCount = 0;
        statName.clear();
    }

    public void addToStat(int id, float value) {
        if(id < statsCount) statValue[id] += value;
    }

    public void setStat(int id, float value) {
        if(id < statsCount) statValue[id] = value;
    }


}
