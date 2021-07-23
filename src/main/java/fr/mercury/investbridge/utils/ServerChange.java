package fr.mercury.investbridge.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ServerChange {

    private static Plugin plugin = null;

    public static void init(final Plugin pl) {

        plugin = pl;
        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public static void changeServer(final Player player, final String server) {

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    public static void joinQueue(final Player player) {

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("JoinQueue");
        out.writeUTF(player.getName());
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
