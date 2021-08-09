package fr.mercury.investbridge.storage;

import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.database.AbstractDatabase;
import fr.mercury.investbridge.database.DatabaseType;
import fr.mercury.investbridge.database.MongoDBDatabase;
import fr.mercury.investbridge.database.MySQLDatabase;
import fr.mercury.investbridge.database.sql.DatabaseCredentials;
import fr.mercury.investbridge.storage.mongo.MongoInvestmentStorage;
import fr.mercury.investbridge.storage.mysql.MySQLInvestmentStorage;
import org.bukkit.configuration.file.FileConfiguration;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
public class StorageUtils {

    private FileConfiguration config = MercuryInvestBridge.getInstance().getConfiguration();

    public static InvestmentStorage getStorageByName(DatabaseType databaseType, AbstractDatabase database) {

        switch (databaseType) {
            case MYSQL:
                return new MySQLInvestmentStorage((MySQLDatabase) database);
            case MONGODB:
                return new MongoInvestmentStorage((MongoDBDatabase) database);
        }
        return null;
    }

    public static AbstractDatabase getDatabaseByName(DatabaseType databaseType, DatabaseCredentials credentials) {

        switch (databaseType) {
            case MYSQL:
                return new MySQLDatabase(credentials);
            case MONGODB:
                return new MongoDBDatabase(credentials);
        }

        return null;
    }
}
