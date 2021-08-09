package fr.mercury.investbridge.storage;

import fr.mercury.investbridge.database.AbstractDatabase;
import fr.mercury.investbridge.utils.VaultUtils;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */

public abstract class InvestmentStorage<T extends AbstractDatabase> {

    protected T database;

    public InvestmentStorage(T database) {
        this.database = database;
    }

    public UserInvestment createUser(String username) {
        return new UserInvestment(username, BigDecimal.valueOf(VaultUtils.getBalance(username)), -1, 0);
    }

    public boolean hasAccount(String name) {
        return this.getUser(name).isPresent();
    }

    public abstract Optional<UserInvestment> getUser(String username);

    public abstract void save(UserInvestment user);

    public abstract Optional<BigDecimal> getMoney(String username);

    public abstract void addMoney(String username);
}
