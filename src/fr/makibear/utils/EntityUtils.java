package fr.makibear.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EntityUtils 
{
	public static List<Entity> getNearbyEntities(Location loc, int range)
	{
	    List<Entity> found = new ArrayList<Entity>();
	    for (Entity entity : loc.getWorld().getEntities()) 
	    	if (isInBorder(loc, entity.getLocation(), range)) 
	    		found.add(entity);
	    return found;
	}
	
	private static boolean isInBorder(Location center, Location notCenter, int range)
	{
	    int x = center.getBlockX();int z = center.getBlockZ();
	    int x1 = notCenter.getBlockX();int z1 = notCenter.getBlockZ();
	    if ((x1 >= x + range) || (z1 >= z + range) || (x1 <= x - range) || (z1 <= z - range)) 
	    	return false;
	    return true;
	}
}
