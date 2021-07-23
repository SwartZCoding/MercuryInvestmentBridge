package fr.mercury.investbridge.utils;

import fr.mercury.investbridge.MercuryInvestBridge;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

public class Utils {

    public final static String LINE = Utils.color("&e&m" + StringUtils.repeat("-", 53));

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)msg);
    }

    public static File getFormatedFile(String fileName) {
        return new File(MercuryInvestBridge.getInstance().getDataFolder(), fileName);
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNotNegative(String value) {
        try {
            Integer.signum(Integer.parseInt(value));
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static int randomInt(int min, int max) {
        Random random = new Random();
        int range = max - min + 1;
        int randomNum = random.nextInt(range) + min;
        return randomNum;
    }

    public static String joinArray(String[] array, String delimiter, int start) {
        if(array.length < start) return "";

        return String.join(color(delimiter), Arrays.copyOfRange(array, start - 1, array.length));
    }

    public static Player getRandomPlayer(Player player) {
        if(Bukkit.getServer().getOnlinePlayers().size() == 1) return player;

        Random random = new Random();
        int index = random.nextInt(Bukkit.getServer().getOnlinePlayers().size());

        Player randomPlayer = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0])[index];

        if(player.getName().equals(randomPlayer.getName())) {
            return getRandomPlayer(player);
        }

        return randomPlayer;
    }

    public static void callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

}
