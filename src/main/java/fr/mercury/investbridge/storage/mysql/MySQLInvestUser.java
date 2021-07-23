package fr.mercury.investbridge.storage.mysql;

import fr.mercury.investbridge.storage.InvestUser;

import java.math.BigDecimal;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public class MySQLInvestUser implements InvestUser {

    private final String username;
    private BigDecimal money;

    public MySQLInvestUser(String username, BigDecimal money) {
        this.username = username;
        this.money = money;
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
