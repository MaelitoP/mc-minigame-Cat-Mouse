package fr.makibear.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.LandersCraft.API.LcAPI;
import fr.LandersCraft.API.Utils.GameSign.GameAccess;
import fr.LandersCraft.API.Utils.GameSign.GameEtat;
import fr.makibear.CatMouse;
import fr.makibear.listeners.PlayerJoinServer;

public class ServerManager 
{
	public static void init(Plugin plugin)
	{
		CatMouse.getInstance().ETAT = GameEtat.WAITING;
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() 
		{
			@Override
			public void run() 
			{
				updateSign();
			}
		}, 0, 10*20L);
		LcAPI.getAPI().getMenuManager().enableQuickBar(true);
		
		//API
		LcAPI.getAPI().getJoinManager().registerHandler(new PlayerJoinServer(), 10);
		
		//Team
		/*CatMouse.getInstance().getTeamManager().enableTeamType(TeamList.CAT);
		CatMouse.getInstance().getTeamManager().enableTeamType(TeamList.MOUSE);*/
	}
	
	public static void updateSign() 
	{
		if(CatMouse.getInstance().sign == null) 
		{
			CatMouse.getInstance().sign = LcAPI.getAPI().getGameSign();
			
			CatMouse.getInstance().sign.setMaxPlayer(21);
			
			CatMouse.getInstance().sign.setMapName(CatMouse.getInstance().mapConfig.getString("Configuration.Map.Name"));
		}
		
		int onlinePlayer = Bukkit.getOnlinePlayers().size() + LcAPI.getAPI().getJoinManager().countExpectedPlayers() - LcAPI.getAPI().getJoinManager().countModerators();
		
		CatMouse.getInstance().sign.setEtat(CatMouse.getInstance().ETAT);
		CatMouse.getInstance().sign.setAccess((CatMouse.getInstance().ETAT == GameEtat.WAITING || CatMouse.getInstance().ETAT == GameEtat.COUNTDOWN) ? GameAccess.ALLOW : GameAccess.DISALLOW);
		CatMouse.getInstance().sign.setOnlinePlayer(onlinePlayer);
		CatMouse.getInstance().sign.send();
	}
	
	public static void shutdown()
	{
		Bukkit.getScheduler().runTaskLater(CatMouse.getInstance(), new Runnable() 
		{
			@Override
			public void run() 
			{
				for(Player player : Bukkit.getOnlinePlayers()) 
					if(player != null)
					{
						player.kickPlayer("lobby");
					}
			}
		}, 20 * 6L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(CatMouse.getInstance(), new Runnable() 
		{
			@Override
			public void run() 
			{
				Bukkit.shutdown();
			}
		}, 20 * 10);
	}
}
