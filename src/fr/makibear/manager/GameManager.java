package fr.makibear.manager;

import org.bukkit.Bukkit;

import fr.LandersCraft.API.LcAPI;
import fr.LandersCraft.API.Utils.GameSign.GameEtat;
import fr.makibear.CatMouse;
import fr.makibear.entity.type.SpongeEntity;
import fr.makibear.timers.Countdown;

public class GameManager 
{
	public 	Countdown cd = new Countdown();
	
	public void start()
	{
		
	}
	
	public void stop()
	{
		/**
		 * Entity
		 */
		for(SpongeEntity e : EntityManager.list)
			e.remove();
	}
	
	public void notifyPlayerJoin()
	{
		int nbplayer = Bukkit.getOnlinePlayers().size()- LcAPI.getAPI().getJoinManager().countModerators();
		if(nbplayer >= Countdown.MINPLAYER || Countdown.force)
		{
			CatMouse.getInstance().ETAT = GameEtat.COUNTDOWN;
			cd.start();
		}
	}
}
