package fr.mercury.investbridge.utils;

import fr.mercury.investbridge.database.sql.ConfigDatabaseManager;
import fr.mercury.investbridge.database.sql.Database;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public class DatabaseUtils {

    private static ConfigDatabaseManager configManager = ConfigDatabaseManager.getInstance();
    private static Database database = configManager.getWebsiteDatabase();

    public static boolean has(String name, double money) {
        return getBalance(name) >= (int) money;
    }

    public static boolean hasAccount(Player player) {

        ResultSet resultSet = ConfigDatabaseManager.getInstance().getWebsiteDatabase().query("SELECT * FROM investment WHERE username = '" + player.getName() + "';");

        return resultSet != null;
    }

    public static int getBalance(String name) {
        int balance = 0;
        if (database != null) {
            if (database.isConnected()) {
                String request = "SELECT money FROM users WHERE name = '" + name + "'";
                ResultSet set = database.query(request);
                if (set != null) {
                    try {
                        while (set.next()) {
                            int money = set.getInt("money");
                            balance = money;
                        }
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
        return balance;
    }
}
