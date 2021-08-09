package fr.mercury.investbridge.database.sql;

import com.google.common.reflect.TypeToken;
import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.database.AbstractDatabase;
import fr.mercury.investbridge.database.DatabaseType;
import fr.mercury.investbridge.database.MySQLDatabase;
import fr.mercury.investbridge.storage.InvestmentStorage;
import fr.mercury.investbridge.storage.StorageUtils;
import fr.mercury.investbridge.utils.Utils;
import fr.mercury.investbridge.utils.jsons.DiscUtil;
import fr.mercury.investbridge.utils.jsons.Saveable;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/*
 @Author Anto' | SwartZ#0001
 @create 16/07/2021
 */
@Getter
public class ConfigDatabaseManager extends Saveable {

    private static @Getter
    ConfigDatabaseManager instance;
    private ConfigDatabase config;
    private AbstractDatabase database;
    private Database websiteDatabase;
    private FileConfiguration configYml = MercuryInvestBridge.getInstance().getConfiguration();
    private @Getter
    InvestmentStorage storage;

    public ConfigDatabaseManager(MercuryInvestBridge plugin) {
        super(plugin, "Config");
        instance = this;
    }

    public ConfigDatabase getConfig() {
        if (this.config == null) {
            return this.config = new ConfigDatabase();
        }
        return this.config;
    }

    /*
     * Connexion aux base de données
     */
    public void connectDatabases() {
        ConfigDatabase config = this.getConfig();
        this.database = StorageUtils.getDatabaseByName(DatabaseType.valueOf(this.configYml.getString("DATABASE.TYPE")), config.getWebsiteCredentials());
    }

    public void createTable() {
        MySQLDatabase mySqlDatabase = (MySQLDatabase) this.database;
        mySqlDatabase.createTable("investment", new String[]{"username VARCHAR(256)", "investment varchar(255)", "seconds bigint(25)", "money DECIMAL(10,5)", "PRIMARY KEY (username)"});

    }

    @Override
    public File getFile() {
        return Utils.getFormatedFile("databaseCredentials.json");
    }

    @SneakyThrows
    @Override
    public void loadData() {
        String content = DiscUtil.readCatch(this.getFile());
        if (content != null) {
            try {
                this.config = MercuryInvestBridge.getInstance().getGson().fromJson(content, new TypeToken<ConfigDatabase>() {
                }.getType());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        this.connectDatabases();
        this.createTable();
    }

    @Override
    public void saveData(boolean value) {
        DiscUtil.writeCatch(MercuryInvestBridge.getInstance(), this.getFile(), MercuryInvestBridge.getInstance().getGson().toJson(this.getConfig()), value);
    }
}