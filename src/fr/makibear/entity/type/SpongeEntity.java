package fr.makibear.entity.type;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.makibear.entity.Entity;
import fr.makibear.entity.EntityType;
import fr.makibear.manager.EntityManager;
import fr.makibear.utils.ItemsUtils;
import fr.makibear.utils.Localization;

public class SpongeEntity implements Entity
{
	Location 	l;
	Block 		b;
	Player 		p;
	boolean 	isTake = false;
	boolean		move   = false;
	
	@Override
	public Location getLocation() 
	{
		return l;
	}

	@Override
	public boolean teleport(Location l) 
	{
		if(this.l != l)
		{
			remove();
			this.l = l;
			this.move = true;
			spawn(this.l);
			return true;
		}
		return false;
	}

	@Override
	public void remove() 
	{
		if(isTake)
			if(p.getInventory().contains(Material.SPONGE))
				ItemsUtils.removeInventoryItems(p.getInventory(), Material.SPONGE, 1);
		else
			b.setType(Material.AIR);
		EntityManager.entity.remove(this.l);
		EntityManager.list.remove(this);
	}

	@Override
	public boolean isValid()
	{
		if(l != null)
			return true;
		return false;
	}

	@Override
	public EntityType getType() 
	{
		return EntityType.SPONGE;
	}

	@Override
	public String getName() 
	{
		return getType().getName();
	}
	

	@Override
	public void spawn(Location l)
	{
		if(move)
		{
			this.b = l.getBlock();
			this.b.setType(Material.SPONGE);
			
			Location loc = new Location(l.getWorld(), b.getX(), b.getY(), b.getZ());
			this.l = loc;
			EntityManager.entity.put(this.l, this);
			EntityManager.list.add(this);
			this.move = false;
			
			return;
		}
		this.l = l;
		this.b = l.getBlock();
		this.b.setType(Material.SPONGE);
		
		Location loc = new Location(l.getWorld(), b.getX(), b.getY(), b.getZ());
		this.l = loc;
		EntityManager.entity.put(this.l, this);
		EntityManager.list.add(this);
	}

	@Override
	public void take(Player p) 
	{
		this.p = p;
		b.setType(Material.AIR);
		p.getInventory().addItem(new ItemStack(Material.SPONGE));
		p.sendMessage(Localization.PREFIX + "§eTu viens d'attraper un formage.");
		isTake = true;
	}
}