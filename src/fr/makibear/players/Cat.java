package fr.makibear.players;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.makibear.CatMouse;
import fr.makibear.utils.ItemsUtils;
import fr.makibear.utils.Localization;

public class Cat extends GamePlayer implements InitPlayer
{
	private Location spawn;
	
	public Cat(Player p)
	{
		super(p);
		this.spawn = CatMouse.getInstance().catS;
		setCat();
	}

	@Override
	public void setup()
	{
		ItemsUtils.clear(p);
		getPlayer().getInventory().setItem(0, CatMouse.getInstance().getItemManager().CHead);
		getPlayer().getInventory().setItem(2, CatMouse.getInstance().getItemManager().CShard);
		getPlayer().getInventory().setItem(3, CatMouse.getInstance().getItemManager().CHeadArrow);
		getPlayer().getInventory().setItem(4, CatMouse.getInstance().getItemManager().CBlockR);
		getPlayer().getInventory().setItem(7, CatMouse.getInstance().getItemManager().CCompass);
		getPlayer().getInventory().setItem(8, CatMouse.getInstance().getItemManager().CHeadSilver);
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 50000, 0));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50000, 0));
	}
	
	public void spawn()
	{
		p.teleport(spawn.add(0, 1, 0));
		p.sendMessage(Localization.PREFIX + "Vous venez d'apparaitre dans votre base.");
	}

	@Override
	public void destroy() 
	{
		remove();
	}
}
