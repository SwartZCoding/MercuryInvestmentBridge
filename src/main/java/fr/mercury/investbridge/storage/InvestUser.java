package fr.mercury.investbridge.storage;

import java.math.BigDecimal;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public interface InvestUser {
    String getUsername();

    BigDecimal getMoney();

    void setMoney(BigDecimal money);

}
