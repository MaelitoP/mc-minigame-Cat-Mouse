package fr.makibear.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.makibear.CatMouse;

public abstract class CommandUtil 
{
	protected CatMouse plugin;
	
	public CommandUtil(CatMouse plugin)
	{
		this.plugin = plugin;
	}
	
	public abstract void executeCommand(CommandSender sender, Command cmd, String[] args);
}
