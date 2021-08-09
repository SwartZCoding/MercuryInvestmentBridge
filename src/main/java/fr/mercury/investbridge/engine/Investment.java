package fr.mercury.investbridge.engine;

import lombok.Data;

/*
 @Author Anto' | SwartZ#0001
 @create 30/07/2021
 */
@Data
public class Investment {

    private int id;
    private String name;
    private long toInvest;
    private long secondsToStay;
    private long rewards;

    public Investment(int id, String name, long secondsToStay, long toInvest, long rewards) {
        this.id = id;
        this.name = name;
        this.toInvest = toInvest;
        this.secondsToStay = secondsToStay;
        this.rewards = rewards;
    }
}
