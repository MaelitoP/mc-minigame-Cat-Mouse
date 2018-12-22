package fr.makibear.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.makibear.players.GamePlayer;

public class PlayerUtils 
{
	public static GamePlayer getGamePlayer(Player p)
	{
		GamePlayer gm;
		if(!GamePlayer.registered.containsKey(p)) gm = null;
		else
			gm = GamePlayer.registered.get(p);
		return gm;
	}
	
	public static void playCancelledSound(Player p)
	{
		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 5F, 5F);
	}
	
	public static void playAcceptSound(Player p)
	{
		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 5F, 5F);
	}
	
	public static void kill(Player player)
	{
		player.setHealth(0.0D);
	}
	  
	public static void damage(Player player, double damage)
	{
	    player.damage(damage);
	}
	  
	public static void heal(Player player)
	{
	    player.setHealth(20.0D);
    }
	  
    public static void heal(Player player, double value)
	{
	    double d = player.getHealth();
	    d += value;
	    player.setHealth(d);
	}
	  
	public static void feed(Player player)
	{
	    player.setFoodLevel(20);
	}
	  
	public static void feed(Player player, int value)
	{
	    int d = player.getFoodLevel();
	    d += value;
	    player.setFoodLevel(d);
	}
	
	public static void addPotionEffect(Player player, PotionEffectType potioneffect, int time, int level)
	{
	    player.addPotionEffect(new PotionEffect(potioneffect, time * 20, level));
	}
	  
	public static void removePotionEffect(Player player, PotionEffectType potioneffect)
	{
	    player.removePotionEffect(potioneffect);
	}
	
	public static void removeAllPotionEffect(Player player)
	{
	    for (PotionEffect effets : player.getActivePotionEffects()) 
	    	player.removePotionEffect(effets.getType());
	}
}
