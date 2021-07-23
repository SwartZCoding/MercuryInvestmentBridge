package fr.mercury.investbridge.storage.mysql;

import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.database.sql.ConfigDatabaseManager;
import fr.mercury.investbridge.database.sql.Database;
import fr.mercury.investbridge.storage.InvestUser;
import fr.mercury.investbridge.storage.InvestmentStorage;
import fr.mercury.investbridge.utils.Utils;
import fr.mercury.investbridge.utils.VaultUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public class MySQLInvestmentStorage implements InvestmentStorage {

    private final Database database = ConfigDatabaseManager.getInstance().getWebsiteDatabase();
    private final FileConfiguration config = MercuryInvestBridge.getInstance().getConfiguration();

    @Override
    public Optional<InvestUser> getUser(String username) {
        ResultSet query = database.query("SELECT * FROM investment WHERE username = '" + username + "';");

        try {
            if (query.next()) {
                int investmentId = query.getInt("investment");
                long timeStayed = query.getLong("seconds");
                BigDecimal money = query.getBigDecimal("money");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public boolean hasAccount(String name) { return database.query("SELECT * FROM investment WHERE username = '" + name + "';") != null; }

    @Override
    public void save(InvestUser user) {

    }

    @Override
    public Optional<BigDecimal> getMoney(String username) {
        ResultSet query = database.query("SELECT money FROM investment WHERE username = '" + username + "';");

        try {
            if (query.next()) {
                BigDecimal money = query.getBigDecimal("money");
                return Optional.of(money);
            }
        } catch (SQLException ignored) {}

        return Optional.empty();
    }

    @Override
    public void addMoney(String username) {

        Optional<BigDecimal> optional = getMoney(username);

        if(!optional.isPresent())
            return;

        BigDecimal bigDecimal = optional.get();

        if(VaultUtils.getBalance(username) < bigDecimal.doubleValue()) {

            final double difference = bigDecimal.doubleValue() - VaultUtils.getBalance(username);
            VaultUtils.depositMoney(username, difference);

            Player player = Bukkit.getPlayer(username);
            player.sendMessage(Utils.color(this.config.getString("MESSAGES.SUCCESSFULY_TAKE_MONEY").replace("%money%", String.valueOf(difference))));
        }
    }
}
