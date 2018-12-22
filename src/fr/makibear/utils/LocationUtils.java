package fr.makibear.utils;

import org.bukkit.Location;

public class LocationUtils
{
	public static Location getCenterOfLocation(Location l)
	{
	     Location loc = l.getBlock().getLocation().add(0.5D, 0.0D, 0.5D);
	     return loc;
	}
}
