package fr.mercury.investbridge.utils.commands;

public abstract class ICommand {
	
  public ICommand() {}
  public abstract void onCommand(CommandArgs paramCommandArgs);
}
