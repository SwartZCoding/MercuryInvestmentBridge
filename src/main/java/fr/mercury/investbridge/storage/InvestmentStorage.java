package fr.mercury.investbridge.storage;

import java.math.BigDecimal;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */

public interface InvestmentStorage {

    Optional<InvestUser> getUser(String username);

    boolean hasAccount(String name);

    void save(InvestUser user);

    Optional<BigDecimal> getMoney(String username);

    void addMoney(String username);
}
