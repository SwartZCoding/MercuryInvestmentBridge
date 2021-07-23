package fr.mercury.investbridge.engine.commands;

import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.utils.ServerChange;
import fr.mercury.investbridge.utils.Utils;
import fr.mercury.investbridge.utils.commands.Command;
import fr.mercury.investbridge.utils.commands.CommandArgs;
import fr.mercury.investbridge.utils.commands.ICommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

/*
 @Author Anto' | SwartZ#0001
 @create 23/07/2021
 */
public class InvestmentCommand extends ICommand {

    private FileConfiguration config = MercuryInvestBridge.getInstance().getConfiguration();
    private int timer = config.getInt("INVEST_COMMAND.TIMER");
    BukkitTask task;

    @Override
    @Command(name = {"invest", "investment", "investissement", "investissements"})
    public void onCommand(CommandArgs args) {

        Player player = args.getPlayer();

        this.task = Bukkit.getScheduler().runTaskTimer(MercuryInvestBridge.getInstance(), () -> {
            player.sendMessage(Utils.color(config.getString("MESSAGES.TELEPORT_TO_INVEST")).replace("%time%", String.valueOf(timer)));

            if (timer == 0) {
                Bukkit.getScheduler().cancelTask(task.getTaskId());
                ServerChange.changeServer(player, config.getString("INVEST_COMMAND.SERVER_NAME"));
            }

            timer--;
        }, 20, 20);


    }
}
