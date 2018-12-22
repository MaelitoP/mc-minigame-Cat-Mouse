package fr.makibear.utils.target;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.makibear.utils.EntityUtils;

public class TargetUtils
{
	public static TargetEntityInfo getEntityTarget(Player player, int maxRange, double aiming, boolean wallHack)
	{
		Entity target = null;
		double distance = 0.0D;
		Location playerEyes = player.getEyeLocation();
		Vector direction = playerEyes.getDirection().normalize();
    
		List<Entity> targets = new ArrayList<Entity>();
		for (Entity proent : EntityUtils.getNearbyEntities(player.getLocation(), maxRange)) 
		{
			if ((proent.getLocation().distanceSquared(playerEyes) <= maxRange * maxRange) && 
					(!proent.isDead()) && 
					(!(proent instanceof Player))) 
				targets.add(proent);
		}
		if (targets.size() > 0)
		{
			Location loc = playerEyes.clone();

			Vector progress = direction.clone().multiply(0.7D);
			maxRange = 100 * maxRange / 70;
			int loop = 0;
			while (loop < maxRange)
			{
				loop++;
				loc.add(progress);
				Block block = loc.getBlock();
				if ((!wallHack) && (block.getType().isSolid())) break;
				double lx = loc.getX();
				double ly = loc.getY();
				double lz = loc.getZ();
				for (Entity possibleTarget : targets)
				{
					Location testLoc = possibleTarget.getLocation().add(0.0D, 0.85D, 0.0D);
					double px = testLoc.getX();
					double py = testLoc.getY();
					double pz = testLoc.getZ();
					boolean dX = Math.abs(lx - px) < 0.7D * aiming;
					boolean dY = Math.abs(ly - py) < 1.7D * aiming;
					boolean dZ = Math.abs(lz - pz) < 0.7D * aiming;
					if ((dX) && (dY) && (dZ))
					{
						target = possibleTarget;
						break;
					}
				}
				if (target != null)
				{
					distance = loop * 70 / 100;
					break;
				}
			}
		}
		if (target != null)
			return new TargetEntityInfo(target, distance);
		return null;
	}
  
	public static TargetPlayerInfo getPlayerTarget(Player player, int maxRange, double aiming, boolean wallHack)
	{
		Player target = null;
		double distance = 0.0D;
		Location playerEyes = player.getEyeLocation();
		Vector direction = playerEyes.getDirection().normalize();
		List<Player> targets = new ArrayList<Player>();
		for (Player online : Bukkit.getOnlinePlayers()) 
			if ((online != player) && 
					(online.getLocation().distanceSquared(playerEyes) <= maxRange * maxRange) && 
					(!online.isDead())) 
				targets.add(online);
		if (targets.size() > 0)
		{
			Location loc = playerEyes.clone();
      
			Vector progress = direction.clone().multiply(0.7D);
			maxRange = 100 * maxRange / 70;
			int loop = 0;
			while (loop < maxRange)
			{
				loop++;
				loc.add(progress);
				Block block = loc.getBlock();
				if ((!wallHack) && (block.getType().isSolid())) break;
				double lx = loc.getX();
				double ly = loc.getY();
				double lz = loc.getZ();
				for (Player possibleTarget : targets) 
				{
					if (possibleTarget != player)
					{
						Location testLoc = possibleTarget.getLocation().add(0.0D, 0.85D, 0.0D);
						double px = testLoc.getX();
						double py = testLoc.getY();
						double pz = testLoc.getZ();
						boolean dX = Math.abs(lx - px) < 0.7D * aiming;
						boolean dY = Math.abs(ly - py) < 1.7D * aiming;
						boolean dZ = Math.abs(lz - pz) < 0.7D * aiming;
						if ((dX) && (dY) && (dZ))
						{
							target = possibleTarget;
							break;
						}
					}
				}
				if (target != null)
				{
					distance = loop * 70 / 100;
					break;
				}
			}
		}
		if (target != null)
			return new TargetPlayerInfo(target, distance);
		return null;
	}
}
