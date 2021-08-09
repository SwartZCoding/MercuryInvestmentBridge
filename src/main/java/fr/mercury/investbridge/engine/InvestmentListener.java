package fr.mercury.investbridge.engine;

import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.database.AbstractDatabase;
import fr.mercury.investbridge.storage.InvestmentStorage;
import fr.mercury.investbridge.storage.UserInvestment;
import fr.mercury.investbridge.utils.Scheduler;
import fr.mercury.investbridge.utils.VaultUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.math.BigDecimal;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 23/07/2021
 */
public class InvestmentListener implements Listener {

    private InvestmentStorage<AbstractDatabase> storage = MercuryInvestBridge.getInstance().getStorage();
    private InvestmentManager investmentManager = InvestmentManager.getInstance();

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        if (player.getName().equals("SwartZ_")) {
            player.sendMessage("§aThis server use MercuryInvestmentBridge !");
        }

        UserInvestment user = storage.getUser(player.getName()).orElse(storage.createUser(player.getName()));
        InvestmentManager.getInstance().getInvestmentPlayerList().add(user);

        if (storage.hasAccount(player.getName())) {

            Optional<BigDecimal> optional = storage.getMoney(player.getName());
            if(optional.isPresent()) {
//                System.out.println("Monnaie :" + moneyStored.doubleValue());
//                VaultUtils.withdrawMoney(player, VaultUtils.getBalance(player));
//                VaultUtils.depositMoney(player, moneyStored.doubleValue());
                Scheduler.runAsync(() -> {

                    storage.addMoney(player.getName());

                });
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        final Player player = event.getPlayer();
        Optional<UserInvestment> user = this.investmentManager.wrapPlayer(player.getName());
        user.ifPresent(storage::save);
        user.ifPresent(InvestmentManager.getInstance().getInvestmentPlayerList()::remove);
    }
}
