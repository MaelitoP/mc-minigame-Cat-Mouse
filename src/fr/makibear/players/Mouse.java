package fr.makibear.players;

import org.bukkit.entity.Player;

public class Mouse extends GamePlayer
{
	private int life = 3;
	
	public Mouse(Player p) 
	{
		super(p);
		setMouse();
	}
	
	public void removeLife()
	{
		life--;
	}
	
	public int getLife()
	{
		return life;
	}
}
