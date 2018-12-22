package fr.makibear.players;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.makibear.CatMouse;
import fr.makibear.team.Team;
import fr.makibear.team.TeamList;
import fr.makibear.utils.Localization;

public class GamePlayer
{
	public 		static		HashMap<Player, GamePlayer> registered 	= new HashMap<Player, GamePlayer>();
	private					ArrayList<GamePlayer> 		players 	= new ArrayList<GamePlayer>();
	
	protected 				Player 						p;
	protected				Team   						t;
	private 				int	   						dead 		= 0;
	private 				int	   						kill 		= 0;
	private					boolean						isCat;
	private					boolean						isMouse;
	
	public GamePlayer(Player p)
	{
		this.p = p;
		players.add(this);
		registered.put(this.p, this);
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	public Team getTeam()
	{
		return t;
	}
	
	public void add(TeamList t)
	{
		CatMouse.getInstance().getTeamManager().setPlayerTeam(p.getUniqueId(), t);
		this.t = CatMouse.getInstance().getTeamManager().getPlayerTeam(p.getUniqueId());
	}
	
	public void waiting()
	{
		//TODO Given select team iteam and back to hub item.
	}
	
	public boolean isCat()
	{
		return isCat;
	}
	
	public boolean setCat()
	{
		isCat = true;
		return true;
	}
	
	public boolean isMouse()
	{
		return isMouse;
	}
	
	public boolean setMouse()
	{
		isMouse = true;
		return true;
	}
	
	public int getDie()
	{
		return dead;
	}
	
	public void setNewDead()
	{
		dead++;
		p.sendMessage(Localization.PREFIX + "§cATTENTION! §7Vous venez de mourrir de la main d'un enemie.");
	}
	
	public int getKill()
	{
		return kill;
	}
	
	public void setNewKill()
	{
		kill++;
		p.sendMessage(Localization.PREFIX + "§7Vous venez de tué un enemie.");
	}
	
	public void remove()
	{
		players.remove(this);
		registered.remove(this);
		if(getTeam() != null)
			getTeam().removePlayerFromTeam(p.getUniqueId());
	}
}
