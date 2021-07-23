package fr.mercury.investbridge.engine;

import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.storage.InvestmentStorage;
import fr.mercury.investbridge.utils.Scheduler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/*
 @Author Anto' | SwartZ#0001
 @create 23/07/2021
 */
public class InvestmentListener implements Listener {

    private InvestmentStorage storage = MercuryInvestBridge.getInstance().getStorage();

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        if (storage.hasAccount(player.getName())) {

            Scheduler.runAsync(() -> {

                storage.addMoney(player.getName());

            });
        }
    }

}
