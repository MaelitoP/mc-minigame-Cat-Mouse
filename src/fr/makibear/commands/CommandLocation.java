package fr.makibear.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.makibear.CatMouse;
import fr.makibear.commands.utils.CommandUtil;
import fr.makibear.utils.Localization;
import fr.makibear.utils.PlayerUtils;

public class CommandLocation extends CommandUtil
{
	public CommandLocation(CatMouse plugin)
	{
		super(plugin);
	}

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args)
	{
		Player p = (Player)sender;

		if(!sender.hasPermission("catmouse.admin") || !sender.isOp())
		{
			p.sendMessage(Localization.PREFIX + ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande.");
		}
		else
		{
			if(args.length == 2)
			{
				if(args[0].equalsIgnoreCase("location"))
				{
					switch (args[1]) 
					{
						case "fridge":
							CatMouse.saveLocation("Configuration.Location.Fridge", p.getLocation());
							p.sendMessage(Localization.PREFIX + "Le frigo a bien était placé.");
							PlayerUtils.playAcceptSound(p);
							break;
					}
				}
			}
		}
	}
}