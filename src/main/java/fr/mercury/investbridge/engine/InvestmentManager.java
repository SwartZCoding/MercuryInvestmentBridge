package fr.mercury.investbridge.engine;

import com.google.common.collect.Lists;
import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.storage.UserInvestment;
import fr.mercury.investbridge.utils.jsons.Saveable;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.Optional;

/*
 @Author Anto' | SwartZ#0001
 @create 30/07/2021
 */
public class InvestmentManager extends Saveable {

    private static @Getter InvestmentManager instance;
    private @Getter List<UserInvestment> investmentPlayerList;

    public InvestmentManager(MercuryInvestBridge plugin) {
        super(plugin, "Investment");
        instance = this;
        this.investmentPlayerList = Lists.newArrayList();
    }

    public Optional<UserInvestment> wrapPlayer(String name) {
        return this.getInvestmentPlayerList().stream().filter(i -> i.getUsername().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public File getFile() {return null;}

    @Override
    public void loadData() {}

    @Override
    public void saveData(boolean sync) {}
}
