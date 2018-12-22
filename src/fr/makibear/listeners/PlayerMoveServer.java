package fr.makibear.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.LandersCraft.API.Utils.GameSign.GameEtat;
import fr.makibear.CatMouse;
import fr.makibear.players.GamePlayer;
import fr.makibear.timers.FreezeCountdown;
import fr.makibear.utils.PlayerUtils;

public class PlayerMoveServer implements Listener
{
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		if(CatMouse.getInstance().ETAT == GameEtat.INGAME)
		{
			if(FreezeCountdown.isFreeze.contains(p))
				p.teleport(p);
			
			GamePlayer gp = null;
			if(PlayerUtils.getGamePlayer(p) != null)	
				gp = PlayerUtils.getGamePlayer(p);
			if(gp.isCat())
			{
				/**
				 * Compass system for search fridge
				 */
				ItemStack i = null;
				if(p.getInventory().getItem(7) != null)
				{
					p.setCompassTarget(CatMouse.getInstance().fridge);
					i = p.getInventory().getItem(8);
					ItemMeta im = i.getItemMeta();
					
					double d = p.getLocation().distance(CatMouse.getInstance().fridge);
					int y = (int)Math.round(d);
					String s = null;
					if(im.getDisplayName().contains("{d}"))
						s = im.getDisplayName().replace("{d}", Integer.toString(y));
					else
						s = ChatColor.LIGHT_PURPLE + "Frigo à " + ChatColor.WHITE + y + " mètres";
					im.setDisplayName(s);
					i.setItemMeta(im);
					p.getInventory().setItem(7, i);
				}
			}
		}
	}
}
