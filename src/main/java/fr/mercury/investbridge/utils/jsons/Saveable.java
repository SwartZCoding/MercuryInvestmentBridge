package fr.mercury.investbridge.utils.jsons;

import fr.mercury.investbridge.MercuryInvestBridge;
import fr.mercury.investbridge.utils.commands.CommandFramework;
import fr.mercury.investbridge.utils.commands.ICommand;
import org.bukkit.event.Listener;

import java.io.File;

public abstract class Saveable implements JsonPersist {
	
	public boolean needDir, needFirstSave;

	public Saveable(MercuryInvestBridge plugin, String name)
	{
		this(plugin, name, false, false);
	}

	public Saveable(MercuryInvestBridge plugin, String name, boolean needDir, boolean needFirstSave)
	{
		this.needDir = needDir;
		this.needFirstSave = needFirstSave;
		if (this.needDir) {
			if (this.needFirstSave)
			{
				saveData(false);
			}
			else
			{
				File directory = getFile();
				if (!directory.exists()) {
					try
					{
						directory.mkdir();
					}
					catch (Exception exception)
					{
						exception.printStackTrace();
					}
				}
			}
		}
}

	public void registerCommand(ICommand command) {
		MercuryInvestBridge plugin = MercuryInvestBridge.getInstance();
		CommandFramework framework = plugin.getFramework();
		framework.registerCommands(command);
	}

	public void registerListener(Listener listener) {
		MercuryInvestBridge plugin = MercuryInvestBridge.getInstance();
		plugin.registerListener(listener);
	}
}
