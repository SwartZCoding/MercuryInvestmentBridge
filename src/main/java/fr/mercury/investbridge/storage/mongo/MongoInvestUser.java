package fr.mercury.investbridge.storage.mongo;

import fr.mercury.investbridge.storage.InvestUser;

import java.math.BigDecimal;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public class MongoInvestUser implements InvestUser {

    private final String username;

    private BigDecimal money;

    private long stayedTime;

    public MongoInvestUser(String username, BigDecimal money, long stayedTime) {
        this.username = username;
        this.money = money;
        this.stayedTime = stayedTime;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public BigDecimal getMoney() {
        return this.money;
    }

    @Override
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
