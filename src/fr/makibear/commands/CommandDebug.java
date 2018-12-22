package fr.makibear.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.makibear.CatMouse;
import fr.makibear.commands.utils.CommandUtil;
import fr.makibear.players.Cat;
import fr.makibear.utils.Localization;

public class CommandDebug extends CommandUtil
{
	public CommandDebug(CatMouse plugin) 
	{
		super(plugin);
	}

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args)
	{
		Player p = (Player)sender;

		if(!sender.hasPermission("catmouse.debug") || !sender.isOp())
		{
			p.sendMessage(Localization.PREFIX + ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande.");
		}
		else
		{
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("debug"))
				{
					//GamePlayer gp = PlayerUtils.getGamePlayer(p);
					new Cat(p).setup();
				}
			}
		}
	}
}
