package fr.makibear.timers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import fr.makibear.CatMouse;
import fr.makibear.utils.Localization;
import net.md_5.bungee.api.ChatColor;

public class FreezeCountdown implements Runnable
{
	public  static 	ArrayList<Player> 	isFreeze 	= new ArrayList<>();
	
	private 		BukkitTask			task;
	private 		int					time 		= 0;
	private			boolean				isRunning 	= false;
	private			Player				p;
	
	public FreezeCountdown(Player p, int time) 
	{
		this.p = p;
		this.time = time;
		isFreeze.add(this.p);
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
			isFreeze.remove(this.p);
		}
	}
	
	@Override
	public void run() 
	{
		this.isRunning = true;
		
		if(this.time == 0)
		{
			endTask();
			return;
		}
		p.sendMessage(Localization.PREFIX + ChatColor.DARK_PURPLE + "Vous êtes actuellement imobilisé pendant encore §f" + this.time + " secondes" + ChatColor.DARK_PURPLE + ".");
		
		this.time--;
	}
}
