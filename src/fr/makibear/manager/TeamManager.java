package fr.makibear.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import fr.makibear.team.Team;
import fr.makibear.team.TeamList;


public class TeamManager
{
	/**
	 * la liste des joueurs et leurs equipes associe
	 */
	private ConcurrentHashMap<UUID, TeamList> playersTeam = new ConcurrentHashMap<UUID, TeamList>();
	
	/**
	 * La liste des equipes en service
	 */
	private ConcurrentHashMap<TeamList, Team> teams = new ConcurrentHashMap<TeamList, Team>();
	
	/**
	 * Activer une nouvelle team
	 * @param teamType Le type de la team
	 */
	public void enableTeamType(TeamList teamType) 
	{
		if(this.teams.containsKey(teamType)) return;
		this.teams.put(teamType, new Team(teamType));
	}
	
	/**
	 * Desactiver une equipe
	 * @param teamType Le type de l'equipe
	 */
	public void disableTeamType(TeamList teamType) 
	{
		if(!this.teams.containsKey(teamType)) return;
		for(UUID player : this.teams.get(teamType).getPlayers())
			this.playersTeam.remove(player);
	}
	
	/**
	 * Definir l'equipe d'un joueur
	 * @param player
	 * @param team
	 */
	public void setPlayerTeam(UUID player, TeamList teamType)
	{
		if(!this.teams.containsKey(teamType)) return;
		
		for(Team team : this.teams.values())
			team.removePlayerFromTeam(player);
		this.playersTeam.put(player, teamType);
		this.teams.get(teamType).addPlayer(player);
	}
	
	/**
	 * Retirer un joueur de son equipe
	 * @param player Le joueur
	 */
	public void removePlayerFromHisTeam(UUID player) 
	{
		this.playersTeam.remove(player);
		for(Team team : this.teams.values()) 
			team.removePlayerFromTeam(player);
	}
	
	/**
	 * Savoir si deux joueurs sont dans la meme equipe
	 * @param a Le premier joueur
	 * @param b Le second joueur
	 * @return
	 */
	public boolean arePlayerInSameTeam(UUID a, UUID b)
	{
		if(!this.playersTeam.containsKey(a) || !this.playersTeam.containsKey(b)) return false;
		return (this.playersTeam.get(a) == this.playersTeam.get(b));
	}
	
	/**
	 * Obtenir le type de la team d'un joueur
	 * @param player Le joueur
	 * @return Le type de la team ou null
	 */
	public TeamList getPlayerTeamList(UUID player) 
	{
		if(this.playersTeam.containsKey(player))
			return this.playersTeam.get(player);
		return null;
	}
	
	/**
	 * Obtenir la team d'un joueur
	 * @param player Le joueur
	 * @return La team du joueur ou null
	 */
	public Team getPlayerTeam(UUID player)
	{
		if(this.playersTeam.containsKey(player) && this.teams.containsKey(this.playersTeam.get(player))) 
			return  this.teams.get(this.playersTeam.get(player));
		return null;
	}
	
	/**
	 * Obtenir la liste des teams activees
	 * @return
	 */
	public List<Team> getTeamsEnable()
	{
		return new ArrayList<Team>(this.teams.values());
	}
}
