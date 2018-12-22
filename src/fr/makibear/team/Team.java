package fr.makibear.team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.scoreboard.Scoreboard;

public class Team 
{
	/**
	 * Le type de la team
	 */
	private TeamList teamlist;
	
	/**
	 * Le nom de l'equipe
	 */
	private String name;
	
	/**
	 * La couleur du chat de l'equipe
	 */
	private ChatColor chatColor;
	
	/**
	 * La liste des joueurs dans l'equipe
	 */
	private List<UUID> players = new ArrayList<UUID>();
	
	/**
	 * La couleur de la banniere
	 */
	private DyeColor bannerColor;
	
	public Team(TeamList teamlist)
	{
		this.teamlist = teamlist;
		this.name = teamlist.getTeamName();
		this.chatColor = teamlist.getChatColor();
		this.bannerColor = teamlist.getBannerColor();
		
		Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
		if(!sb.getTeams().contains("T" + this.name)) 
		{
			sb.registerNewTeam("T" + this.name);
			
			sb.getTeam("T" + this.name).setPrefix(this.chatColor + "");
			sb.getTeam("T" + this.name).setCanSeeFriendlyInvisibles(false);
			sb.getTeam("T" + this.name).setAllowFriendlyFire(false);
			/*Objective healthObjective = sb.registerNewObjective("Health", Criterias.HEALTH);
			healthObjective.setDisplayName("Health");
			healthObjective.setDisplaySlot(DisplaySlot.PLAYER_LIST);*/
		}
	}
	
	/**
	 * Obtenir la banniere de l'equipe
	 * @return
	 */
	public ItemStack getBanner() 
	{
		ItemStack item = new ItemStack(Material.BANNER);
		BannerMeta meta = (BannerMeta)item.getData();
		
		meta.setBaseColor(this.bannerColor);
		meta.setDisplayName(this.chatColor + getTeamName());
		/*List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY+"Rejoindre l'equipe "+this.chatColor+"rouge");
		meta.setLore(lore);*/
		meta.addPattern(new Pattern(this.bannerColor, PatternType.FLOWER));
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * Obtenir la couleur de l'equipe
	 * @return
	 */
	public ChatColor getChatColor() 
	{
		return this.chatColor;
	}
	
	/**
	 * Le nom de l'equipe
	 * @return
	 */
	public String getTeamName()
	{
		return this.name;
	}
	
	/**
	 * Ajouter un joueur a l'equipe
	 * @param player Le joueur
	 */
	public void addPlayer(UUID player) 
	{
		if(!this.players.contains(player))
			this.players.add(player);
		//TODO check \/
		//Bukkit.getScoreboardManager().getMainScoreboard().getTeam("T"+this.name).addPlayer(player);
		//player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
	}
	
	/**
	 * Retirer un joueur de l'equipe
	 * @param player Le joueur
	 */
	public void removePlayerFromTeam(UUID player) 
	{
		this.players.remove(player);
	}
	
	/**
	 * Obtenir le type de team
	 * @return
	 */
	public TeamList getTeam()
	{
		return this.teamlist;
	}
	
	/**
	 * Obtenir la liste des joueurs dans l'equipe
	 * @return
	 */
	public List<UUID> getPlayers()
	{
		return this.players;
	}
	
}
