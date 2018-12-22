package fr.makibear.listeners;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.LandersCraft.API.LcAPI;
import fr.LandersCraft.API.GamesNetwork.JoinHandler;
import fr.LandersCraft.API.GamesNetwork.JoinResponse;
import fr.LandersCraft.API.GamesNetwork.ResponseType;
import fr.LandersCraft.API.Utils.GameSign.GameEtat;
import fr.makibear.CatMouse;
import fr.makibear.manager.ServerManager;
import fr.makibear.players.GamePlayer;
import fr.makibear.timers.Countdown;
import fr.makibear.utils.PlayerUtils;

public class PlayerJoinServer implements JoinHandler, Listener
{
	@EventHandler
	public void join(PlayerJoinEvent e)
	{
		e.setJoinMessage("");
	}
	
	@Override
	public void finishJoin(Player p)
	{
		if(CatMouse.getInstance().ETAT == GameEtat.WAITING)
		{
			GamePlayer player = new GamePlayer(p);
			p.setPlayerListName(ChatColor.WHITE + p.getName());
			player.waiting();
			
			System.out.println(PlayerUtils.getGamePlayer(p));
			
			Bukkit.broadcastMessage("§7§l[§8+§7] " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " vient de rejoindre la partie.");
			
			CatMouse.getInstance().getGameManager().notifyPlayerJoin();
		}
		
		ServerManager.updateSign();
	}

	@Override
	public void onLogin(UUID uuid)
	{
		
	}

	@Override
	public void onLogout(Player p)
	{
		if(CatMouse.getInstance().ETAT.equals(GameEtat.INGAME) && Bukkit.getOnlinePlayers().size() == 0)
			ServerManager.shutdown();
		
		/**
		 * Le joueur quitte volontairement la game
		 */
		if(CatMouse.getInstance().ETAT == GameEtat.INGAME)
		{
			//TODO
		}
		else if(CatMouse.getInstance().ETAT == GameEtat.WAITING) 
		{
			if(PlayerUtils.getGamePlayer(p) != null)
				PlayerUtils.getGamePlayer(p).remove();
			Bukkit.broadcastMessage("§7§l[§8+§7] " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " vient de quitter la partie.");
		}
		
		ServerManager.updateSign();
	}

	@Override
	public JoinResponse requestJoin(UUID uuid, JoinResponse response)
	{
		int onlinePlayer = Bukkit.getOnlinePlayers().size() + LcAPI.getAPI().getJoinManager().countExpectedPlayers() - LcAPI.getAPI().getJoinManager().countModerators();
		
		if(CatMouse.getInstance().ETAT == GameEtat.INGAME) 
		{
			response.disallow(ResponseType.DENY_IN_GAME);
		}
		else if(CatMouse.getInstance().ETAT == GameEtat.WAITING || CatMouse.getInstance().ETAT == GameEtat.COUNTDOWN) 
		{
			if(onlinePlayer < Countdown.MAXPLAYER) 
			{
				response.allow();
			}
			else
				response.disallow(ResponseType.DENY_FULL);
		}
		else
			response.disallow(ResponseType.DENY_NOT_READY);
		
		return response;
	}

	@Override
	public JoinResponse requestPartyJoin(UUID partyLeader, Set<UUID> partyMembers,
			JoinResponse response)
	{
		int onlinePlayer = Bukkit.getOnlinePlayers().size() + LcAPI.getAPI().getJoinManager().countExpectedPlayers() - LcAPI.getAPI().getJoinManager().countModerators();
		
		if(CatMouse.getInstance().ETAT == GameEtat.INGAME) 
		{
			response.disallow(ResponseType.DENY_IN_GAME);
		}
		else if(CatMouse.getInstance().ETAT == GameEtat.WAITING || CatMouse.getInstance().ETAT == GameEtat.COUNTDOWN) 
		{
			if(onlinePlayer >= Countdown.MAXPLAYER) 
			{
				response.disallow(ResponseType.DENY_FULL);
			} 
			else if(partyMembers.size() > (Countdown.MAXPLAYER - onlinePlayer))
			{
				response.disallow(ResponseType.DENY_FULL);
			}
			else
				response.allow();
		} 
		else 
			response.disallow(ResponseType.DENY_NOT_READY);
				
		return response;
	}

	@Override
	public JoinResponse requestPartyJoinLater(UUID player, UUID party,
			JoinResponse response)
	{
		int onlinePlayer = Bukkit.getOnlinePlayers().size() + LcAPI.getAPI().getJoinManager().countExpectedPlayers() - LcAPI.getAPI().getJoinManager().countModerators();
		
		if(CatMouse.getInstance().ETAT == GameEtat.INGAME) 
		{
			response.disallow(ResponseType.DENY_IN_GAME);
		} 
		else if(CatMouse.getInstance().ETAT == GameEtat.WAITING || CatMouse.getInstance().ETAT == GameEtat.COUNTDOWN)
		{
			if(onlinePlayer < Countdown.MAXPLAYER) 
			{
				response.allow();
			}
			else 
				response.disallow(ResponseType.DENY_FULL);
		}
		else
			response.disallow(ResponseType.DENY_NOT_READY);
		
		return response;
	}

	@Override
	public JoinResponse requestReconnect(UUID player, JoinResponse response)
	{
		response.disallow(ResponseType.DENY_IN_GAME);
		return null;
	}

	@Override
	public void reconnect(Player player) { }
}
