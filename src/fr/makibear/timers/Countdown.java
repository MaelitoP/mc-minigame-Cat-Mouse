package fr.makibear.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import fr.LandersCraft.API.LcAPI;
import fr.LandersCraft.API.Utils.GameSign.GameEtat;
import fr.makibear.CatMouse;
import fr.makibear.utils.Localization;

public class Countdown implements Runnable
{
					BukkitTask	task;
					int			timeleft 	= 0;
					boolean		isRunning 	= false;
	
	private 		int 		resetCount 	= 60;
	
	public static 	boolean 	force 		= false;
	
	public static 	int 		MAXPLAYER 	= 21;
	public static 	int 		MINPLAYER 	= 5;
	
	public Countdown() 
	{
		this.timeleft = resetCount;
	}
	
	public void start()
	{
		if(!this.isRunning) 
		{
			this.isRunning = true;
			this.task = Bukkit.getScheduler().runTaskTimer(CatMouse.getInstance(), this, 0, 20);
			timeleft = resetCount;
			CatMouse.getInstance().ETAT = GameEtat.COUNTDOWN;
		}	
	}
	
	public void endTask()
	{
		if(this.isRunning) 
		{
			this.task.cancel();
			this.isRunning = false;
			this.timeleft = resetCount;
		}
	}
	
	@Override
	public void run() 
	{
		this.isRunning = true;
		
		int nbplayer = Bukkit.getOnlinePlayers().size()- LcAPI.getAPI().getJoinManager().countModerators();
		
		if(nbplayer < MINPLAYER && !force)
		{
			endTask();
			CatMouse.getInstance().ETAT = GameEtat.WAITING;
			return;
		}
		
		if(this.timeleft == 0)
		{
			endTask();
			
			for (Player p : Bukkit.getWorld("world").getPlayers()) 
			{
				p.setLevel(0);
				p.setExp(0);
			}
			return;
		}
		if((nbplayer >= MAXPLAYER-1 || force) && this.timeleft > 10)
		{
			this.timeleft = 10;
		}
		
		if(this.timeleft <=10) 
		{
			if(this.timeleft <= 5)
				LcAPI.getAPI().getNMS().getTitle().setFadeIn(0).setStay(21).setFadeOut(0).setTitle(ChatColor.RED+""+ChatColor.BOLD+this.timeleft).setSubTitle(ChatColor.AQUA+"Préparez-vous au combat !").sendAll();
			else
			{
				LcAPI.getAPI().getNMS().getTitle()
				.setFadeIn(0)
				.setStay(21)
				.setFadeOut(0)
				.setTitle( ChatColor.GREEN + "Cat & Mouse" )
				.setSubTitle(ChatColor.GRAY + "Map " + ChatColor.AQUA + CatMouse.getInstance().mapConfig.getString("Configuration.Map.Name") + ChatColor.GRAY + " par " + ChatColor.GOLD + CatMouse.getInstance().mapConfig.getString("Configuration.Map.Builder"))
				.sendAll();
			}
			for (Player p : Bukkit.getWorld("world").getPlayers()) 
			{
				p.setLevel(this.timeleft);
				float xp = (float)this.timeleft / 10;
				p.setExp(xp);
				p.playNote(p.getLocation(), Instrument.PIANO, Note.natural((timeleft > 7)?0:1, Tone.values()[(timeleft > 7)?(14-timeleft):(7-timeleft)]));
			}
		} 
		else 
		{
			if((this.timeleft % 5 == 0 && this.timeleft <30) || (this.timeleft % 20 == 0 && this.timeleft > 30)) 
			{
				Bukkit.getServer().broadcastMessage(Localization.PREFIX + ChatColor.GRAY + "Debut de la partie dans " + ChatColor.GREEN + this.timeleft + ChatColor.GRAY + " secondes.");
			}
			for (Player p : Bukkit.getWorld("world").getPlayers())
			{
				p.setLevel(this.timeleft);
			}
		}
		this.timeleft--;
	}
}
