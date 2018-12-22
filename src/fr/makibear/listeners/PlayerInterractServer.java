package fr.makibear.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fr.makibear.entity.type.SpongeEntity;
import fr.makibear.manager.EntityManager;
import fr.makibear.players.GamePlayer;
import fr.makibear.timers.ItemCountdown;
import fr.makibear.utils.ItemsUtils;
import fr.makibear.utils.Localization;
import fr.makibear.utils.PlayerUtils;
import fr.makibear.utils.particle.LineRunnable;
import fr.makibear.utils.target.TargetPlayerInfo;
import fr.makibear.utils.target.TargetUtils;

public class PlayerInterractServer implements Listener
{
	@EventHandler
	public void useItemEvent(PlayerInteractEvent e)
	{
		Action a = e.getAction();
		ItemStack i = e.getItem();
		Player p = e.getPlayer();
		
		GamePlayer gm = PlayerUtils.getGamePlayer(p);
		if(gm == null) return;
		
		if(i != null)
		{
			if(i.getItemMeta() != null)
			{
				if(!(a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK)) return;
				if (i.getItemMeta().getDisplayName() == null) return;
				
				if(i.getItemMeta().getDisplayName().equals(ItemsUtils.RELOAD))
				{
					e.setCancelled(true);
					p.sendMessage(Localization.PREFIX + ChatColor.RED + "Attendez le rechargement de cette item !");
					PlayerUtils.playCancelledSound(p);
					return;
				}
				else if(i.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Saut"))
				{
					e.setCancelled(true);
					p.setVelocity(p.getLocation().getDirection().add(new Vector(0.0, 1.2, 0.0)));
					new ItemCountdown(p, p.getInventory().getItem(3), 20, 3).start();
					return;
				}
				else if(i.getItemMeta().getDisplayName().equals(ChatColor.RED + "Coup de langue"))
				{
					e.setCancelled(true);
					TargetPlayerInfo target = TargetUtils.getPlayerTarget(p, 5, 1.40, false);
					if(target != null)
					{
						LineRunnable.displayParticles(p, target.getPlayer().getLocation());
						target.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 8));
						PlayerUtils.playAcceptSound(p);
						new ItemCountdown(p, p.getInventory().getItem(4), 20, 4).start();
					}
					else
					{
						p.sendMessage(Localization.PREFIX + "Aucun chat à l'horizon.");
						PlayerUtils.playCancelledSound(p);
					}
					return;
				}
			}
		}
		
		/**
		 * Blocks
		 */
		Block b = e.getClickedBlock();
		if(b == null) return;
		if(b.getType() == Material.SPONGE)
		{
			if(EntityManager.getEntity(b.getLocation()) == null) return;
			
			if(gm.isMouse())
			{
				SpongeEntity entity = EntityManager.getEntity(b.getLocation());
				entity.take(p);
			}
			else
				p.sendMessage(Localization.PREFIX + ChatColor.RED + "Protège le frigo au lieu de le manger !");
			return;
		}
	}
}
