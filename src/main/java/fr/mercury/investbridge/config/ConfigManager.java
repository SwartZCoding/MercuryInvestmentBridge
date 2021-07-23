package fr.mercury.investbridge.config;

import fr.mercury.investbridge.MercuryInvestBridge;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private final MercuryInvestBridge mercuryInvestBridge;
    private final FileConfiguration config;

    public ConfigManager(MercuryInvestBridge mercuryInvestBridge) {
        this.mercuryInvestBridge = mercuryInvestBridge;
        this.config = this.mercuryInvestBridge.getConfig();
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', this.config.getString(path));
    }

    public List<String> getStringList(String path) {

        List<String> stringList = this.config.getStringList(path);
        ArrayList<String> toReturn = new ArrayList<>();

        stringList.forEach(line -> toReturn.add(ChatColor.translateAlternateColorCodes('&', line)));

        return toReturn;
    }

    public void setDouble(String path, double value) { this.config.set(path, value); }

    public int getInt(String path) {
        return this.config.getInt(path);
    }

    public boolean getBoolean(String path) {
        return this.config.getBoolean(path);
    }

    private double getDouble(String path) {
        return this.config.getDouble(path);
    }

    public double getFloat(String path) {
        return this.config.getDouble(path);
    }
    public long getLong(String path){
        return this.config.getLong(path);
    }

    public FileConfiguration getConfig() { return this.config; }

    public void updateConfig() {
        this.mercuryInvestBridge.saveConfig();
        this.mercuryInvestBridge.reloadConfig();
    }
}
