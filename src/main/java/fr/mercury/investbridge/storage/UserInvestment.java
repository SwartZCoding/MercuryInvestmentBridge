package fr.mercury.investbridge.storage;

import fr.mercury.investbridge.engine.Investment;

import java.math.BigDecimal;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */

public class UserInvestment {

    private String username;
    private BigDecimal money;
    private int investment;
    private long seconds;

    public UserInvestment() {
    }

    public UserInvestment(String username, BigDecimal money, int investment, long seconds) {
        this.username = username;
        this.money = money;
        this.investment = investment;
        this.seconds = seconds;
    }

    public String getUsername() {
        return this.username;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getInvestment() {
        return investment;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }
}
