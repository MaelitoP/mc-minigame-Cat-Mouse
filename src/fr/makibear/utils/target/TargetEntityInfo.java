package fr.makibear.utils.target;

import org.bukkit.entity.Entity;

public class TargetEntityInfo
{
	private Entity entity;
	private double distance;
  
	public TargetEntityInfo(Entity entity, double distance)
	{
		setEntity(entity);
		setDistance(distance);
	}
  
	public Entity getEntity()
	{
		return this.entity;
	}
  
	public void setEntity(Entity entity)
	{
		this.entity = entity;
	}
  
	public double getDistance()
	{
		return this.distance;
	}
  
	public void setDistance(double distance)
	{
		this.distance = distance;
	}
}
