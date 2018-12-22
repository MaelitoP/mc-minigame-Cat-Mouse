package fr.makibear.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;

import fr.makibear.entity.type.SpongeEntity;

public class EntityManager 
{
	public static HashMap<Location, SpongeEntity> entity = new HashMap<Location, SpongeEntity>();
	public static List<SpongeEntity> list = new ArrayList<SpongeEntity>();
	
	public static SpongeEntity getEntity(Location l)
	{
		if(entity.containsKey(l))
			return entity.get(l);
		return null;
	}
}
