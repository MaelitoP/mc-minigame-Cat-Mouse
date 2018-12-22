package fr.makibear.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum TeamList 
{
	CAT(ChatColor.AQUA, DyeColor.CYAN, "Chat"),
	MOUSE(ChatColor.YELLOW, DyeColor.YELLOW, "Souris");
	
	/**
	 * Le nom de l'equipe
	 */
	private String name;
	
	/**
	 * La couleur du chat de l'equipe
	 */
	private ChatColor chatColor;
	
	/**
	 * La couleur de la banniere
	 */
	private DyeColor bannerColor;
	
	TeamList(ChatColor chatColor, DyeColor bannerColor, String name) 
	{
		this.chatColor = chatColor;
		this.bannerColor = bannerColor;
		this.name = name;
	}
	
	public String getTeamName() 
	{
		return this.name;
	}
	
	public ChatColor getChatColor() 
	{
		return this.chatColor;
	}
	
	public DyeColor getBannerColor() 
	{
		return this.bannerColor;
	}
}