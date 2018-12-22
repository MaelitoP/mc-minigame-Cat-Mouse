package fr.makibear.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.makibear.CatMouse;

public class CommandManager implements CommandExecutor
{
	private CatMouse plugin;
	
	public CommandManager(CatMouse plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(args.length == 0)
		{
			sender.sendMessage(ChatColor.GREEN + "§aCommandes ► §c/cm help: §7pour voir la liste des commandes");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("debug"))
		{
			new CommandDebug(plugin).executeCommand(sender, cmd, args);
			return true;
		}
		if(args[0].equalsIgnoreCase("location"))
		{
			new CommandLocation(plugin).executeCommand(sender, cmd, args);
			return true;
		}
		return true;
	}
}
