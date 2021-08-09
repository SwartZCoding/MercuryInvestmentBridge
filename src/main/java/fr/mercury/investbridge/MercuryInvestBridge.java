package fr.mercury.investbridge;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.mercury.investbridge.config.Config;
import fr.mercury.investbridge.config.ConfigManager;
import fr.mercury.investbridge.database.AbstractDatabase;
import fr.mercury.investbridge.database.DatabaseType;
import fr.mercury.investbridge.database.sql.ConfigDatabaseManager;
import fr.mercury.investbridge.engine.InvestmentListener;
import fr.mercury.investbridge.engine.InvestmentManager;
import fr.mercury.investbridge.engine.commands.InvestmentCommand;
import fr.mercury.investbridge.storage.InvestmentStorage;
import fr.mercury.investbridge.storage.StorageUtils;
import fr.mercury.investbridge.utils.ServerChange;
import fr.mercury.investbridge.utils.commands.CommandFramework;
import fr.mercury.investbridge.utils.jsons.JsonPersist;
import fr.mercury.investbridge.utils.jsons.adapters.ItemStackAdapter;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 22/07/2021
 */
@Getter
public class MercuryInvestBridge extends JavaPlugin {

    private @Getter static MercuryInvestBridge instance;
    private CommandFramework framework;
    private @Getter ConfigManager configManager;
    private Config configuration;
    private Gson gson;
    private InvestmentStorage<AbstractDatabase> storage;
    private final List<JsonPersist> persists = Lists.newArrayList();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        configManager = new ConfigManager(this);
        this.configuration = new Config(this, "config");
        ServerChange.init(this);
        this.framework = new CommandFramework(this);
        this.getDataFolder().mkdir();
        this.gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE).registerTypeHierarchyAdapter(ItemStack.class, new ItemStackAdapter(this)).create();

        this.registerPersist(new ConfigDatabaseManager(this));
        this.registerPersist(new InvestmentManager(this));

        for (JsonPersist persist : this.persists) {
            persist.loadData();
        }
        this.storage = StorageUtils.getStorageByName(DatabaseType.valueOf(getConfiguration().getString("DATABASE.TYPE")), ConfigDatabaseManager.getInstance().getDatabase());
        this.registerListener(new InvestmentListener());
        this.framework.registerCommands(new InvestmentCommand());


        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§dMercuryInvestmentBridge §7- [§aON§7]");
        Bukkit.getConsoleSender().sendMessage("");
    }

    @Override
    public void onDisable() {
        this.persists.forEach(p -> {
            try {
                p.saveData(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§dMercuryInvestmentBridge §7- [§cOFF§7]");
        Bukkit.getConsoleSender().sendMessage("");
    }

    public void registerListener(Listener listener) {

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(listener, this);
    }

    public void registerPersist(JsonPersist persist) {
        this.persists.add(persist);
    }
}
