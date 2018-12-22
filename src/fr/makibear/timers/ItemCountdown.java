package fr.makibear.timers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import fr.makibear.CatMouse;
import fr.makibear.utils.ItemsUtils;

public class ItemCountdown implements Runnable
{
	private BukkitTask	task;
	private int			time 		= 0;
	private	boolean		isRunning 	= false;
	private ItemStack 	i;
	private	int			slot;
	private	Player		p;
	private String		s;
	
	public ItemCountdown(Player p, ItemStack i, int time, int slot) 
	{
		this.p = p;
		this.i = i;
		this.time = time;
		this.slot = slot;
		this.s = i.getItemMeta().getDisplayName();
	}
	
	public ItemStack getItemStack()
	{
		return i;
	}
	
	public int getTimeLeft()
	{
		return time;
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	public void start()
	{
		if(!this.isRunning) 
		{
			this.isRunning = true;
			this.task = Bukkit.getScheduler().runTaskTimer(CatMouse.getInstance(), this, 0, 20);
		}	
	}
	
	public void endTask()
	{
		if(this.isRunning) 
		{
			this.task.cancel();
			this.isRunning = false;
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(s);
			i.setItemMeta(im);
			i.setAmount(1);
			
			p.getInventory().setItem(this.slot, this.i);
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5F, 5F);
			p.updateInventory();
		}
	}
	
	@Override
	public void run() 
	{
		this.isRunning = true;
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ItemsUtils.RELOAD);
		i.setItemMeta(im);
		
		if(this.time == 0)
		{
			endTask();
			return;
		}
		
		i.setAmount(-time);
		p.getInventory().setItem(this.slot, this.i);
		this.time--;
	}
}
