package fr.makibear.entity;

public enum EntityType
{
	SPONGE("Fromage");
	
	private String		name;
	
	EntityType(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
