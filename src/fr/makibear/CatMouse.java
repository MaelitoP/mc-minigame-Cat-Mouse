package fr.makibear;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.LandersCraft.API.Utils.GameSign;
import fr.LandersCraft.API.Utils.GameSign.GameEtat;
import fr.makibear.commands.CommandManager;
import fr.makibear.items.ItemManager;
import fr.makibear.listeners.PlayerInterractServer;
import fr.makibear.listeners.PlayerJoinServer;
import fr.makibear.listeners.PlayerMoveServer;
import fr.makibear.manager.GameManager;
import fr.makibear.manager.ServerManager;
import fr.makibear.manager.TeamManager;
import fr.makibear.team.Team;
import fr.makibear.utils.LocationUtils;

public class CatMouse extends JavaPlugin
{
	public static 	CatMouse 			instance;
	private 		Logger 				logger 							= Logger.getLogger("Minecraft");
	public  		GameSign 			sign;
	
	public 			GameEtat 			ETAT 							= GameEtat.REBOOT;
	public          File    			map;
	public  		FileConfiguration 	mapConfig;
	public static 	Team				CAT, MOUSE;
	
	public			ItemManager 		im;
	public			TeamManager 		tm;
	public			GameManager			gm;
	
	public			Location			fridge, waiting, catS, mouseS;
	
	public void onLoad()
		{ instance = this; }
	
	public void onEnable()
	{
		logger.info("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		logger.info("               Cat & Mouse             ");
		logger.info("                                       ");
		logger.info("              Developed by:            ");
		logger.info("                -MakiBearDY            ");
		logger.info("                                       ");
		logger.info("             Version dev0.1            ");
		logger.info("             Status: Enable            ");
		logger.info("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		
		/**
		 * Setup
		 */
		gm = new GameManager();
		tm = new TeamManager();
		im = new ItemManager();
		
		loadConfig();
		loadLocationConfig();
		ServerManager.init(this);
		gm = new GameManager();
		//tm = new TeamManager();
		registerEvents();
		this.getCommand("cm").setExecutor(new CommandManager(this));
		
		//Team
		//CAT = new Team(TeamList.CAT);
		//MOUSE = new Team(TeamList.MOUSE);
	}
	
	public void onDisable()
	{
		logger.info("[Cat & Mouse] Disable");
		
		try 
	    {
			mapConfig.save(map);
		}
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	}
	
	public static CatMouse getInstance()
	{
		return instance;
	}
	
	public ItemManager getItemManager()
	{
		return im;
	}
	
	public TeamManager getTeamManager()
	{
		return tm;
	}
	
	public GameManager getGameManager()
	{
		return gm;
	}
	
	public void registerEvents()
	{
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerMoveServer(), this);
		pm.registerEvents(new PlayerInterractServer(), this);
		pm.registerEvents(new PlayerJoinServer(), this);
	}
	
	public static void saveLocation(String path, Location l)
	{
	    Location loc = LocationUtils.getCenterOfLocation(l);
	    getInstance().mapConfig.set(path + ".x", Double.valueOf(loc.getX()));
	    getInstance().mapConfig.set(path + ".y", Double.valueOf(loc.getY()));
	    getInstance().mapConfig.set(path + ".z", Double.valueOf(loc.getZ()));
	    getInstance().mapConfig.set(path + ".w", loc.getWorld().getName());
	    try 
	    {
	    	getInstance().mapConfig.save(getInstance().map);
		}
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	}
	
	public static Location loadLocation(String path)
	{
	    double x = getInstance().mapConfig.getDouble(path + ".x");
	    double y = getInstance().mapConfig.getDouble(path + ".y");
	    double z = getInstance().mapConfig.getDouble(path + ".z");
	    World w = Bukkit.getWorld(getInstance().mapConfig.getString(path + ".w"));
	    return new Location(w, x, y, z);
	}
	
	public void loadLocationConfig()
	{
		fridge 		= loadLocation("Configuration.Location.Fridge");
		/*waiting 	= loadLocation("Configuration.Location.Spawn");
		catS 		= loadLocation("Configuration.Location.Cat");
		mouseS 		= loadLocation("Configuration.Location.Mouse");*/
	}
	
	public void loadConfig()
	{
		map = new File("./world/map.yml");
		
		if (map.exists())
		{
			mapConfig = YamlConfiguration.loadConfiguration(map);
			System.out.println("** Fichier map chargé avec succée **");
		}
		else
			System.out.println("** La fichier config n'existe pas **");
	}
}
