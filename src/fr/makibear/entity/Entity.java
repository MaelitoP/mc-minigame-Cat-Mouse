package fr.makibear.entity;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Entity 
{
	public Location getLocation();
	
	public boolean teleport(Location l);
	
	public void remove();
	
	public boolean isValid();
	
	public EntityType getType();
	
	public String getName();
	
	public void spawn(Location l);
	
	public void take(Player p);
}