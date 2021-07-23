package fr.mercury.investbridge.storage;

import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.storage.mongo.MongoInvestmentStorage;
import fr.mercury.investbridge.storage.mysql.MySQLInvestmentStorage;
import org.bukkit.configuration.file.FileConfiguration;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public class StorageUtils {

    private FileConfiguration config = MercuryInvestBridge.getInstance().getConfiguration();

    public static InvestmentStorage getStorageByName(String name) {

        switch (name) {
            case "MYSQL":
                return new MySQLInvestmentStorage();
            case "MONGO":
                return new MongoInvestmentStorage();
        }
        return null;
    }
}
