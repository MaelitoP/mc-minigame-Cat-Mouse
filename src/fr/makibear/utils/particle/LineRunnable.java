package fr.makibear.utils.particle;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.LandersCraft.API.Tools.ParticleEffect;

public class LineRunnable
{
	public static void displayParticles(Player player, Location max)
	{
	    Location eyeLoc = player.getEyeLocation();
	    
	    double px = eyeLoc.getX();
	    double py = eyeLoc.getY();
	    double pz = eyeLoc.getZ();
	    double yaw = Math.toRadians(eyeLoc.getYaw() + 90.0F);
	    double pitch = Math.toRadians(eyeLoc.getPitch() + 90.0F);
	    
	    double x = Math.sin(pitch) * Math.cos(yaw);
	    double y = Math.sin(pitch) * Math.sin(yaw);
	    double z = Math.cos(pitch);
	    
	    for (int i = 1; i <= Math.round(player.getLocation().distance(max)); i++)
	    {
	    	Location loc = new Location(player.getWorld(), px + i * x, py + i * z, pz + i * y);
	    	if((loc.getX() > max.getX() && loc.getY() > max.getY() && loc.getZ() > max.getZ()) || loc == max) break;
	    	ParticleEffect.SPELL_MOB.display(0.1F, 0.1F, 0.1F, 10F, 50, loc, 200D);
	    }
	}
}
