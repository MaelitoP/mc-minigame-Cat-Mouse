package fr.makibear.items;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.makibear.utils.ItemsUtils;

public class ItemManager 
{
	public ItemStack 
	CHead, CShard, CHeadArrow, CBlockR, CCompass,   CHeadSilver, //Cat
	MHead, MInfo,  MHeadLeft,  MMilk,   MHeadRight, MCompass,    //Mouse
	TeamSelect, Back;											 //Waiting
	
	public ItemManager()
	{
		/**
		 * Cat Items
		 */
		cat();
		
	    /**
		 * Souris Items
		 */
	    mouse();
	    
	    /**
		 * Waiting Items
		 */
	    waiting();
	}
	
	public void cat()
	{
		CHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	    SkullMeta CHeadMeta = (SkullMeta)CHead.getItemMeta();
	    CHeadMeta.setDisplayName(ChatColor.YELLOW + "Chat");
	    CHeadMeta.setOwner("MHF_Ocelot");
	    CHeadMeta.setLore(Arrays.asList(new String[]
	    		{ 
	    			"§7Vous êtes un chat.",
	    			"§7Tuez toutes les Souris pour",
	    			"§7remporter la partie."
	    		}));
	    CHead.setItemMeta(CHeadMeta);
	    
	    CShard = new ItemStack(Material.PRISMARINE_SHARD);
	    ItemsUtils.enchantItem(CShard, Enchantment.DAMAGE_ALL, 1);
	    ItemMeta CShardMeta = CShard.getItemMeta();
	    CShardMeta.setDisplayName(ChatColor.RED + "Coup de griffe");
	    CShardMeta.setLore(Arrays.asList(new String[]
	    		{
	    			"§7Permet d'attaquer une souris",
	    			"§7en la griffant."
	    		}));
	    CShard.setItemMeta(CShardMeta);
	    
	    CHeadArrow = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	    SkullMeta CHeadArrowMeta = (SkullMeta)CHeadArrow.getItemMeta();
	    CHeadArrowMeta.setDisplayName(ChatColor.GREEN + "Saut");
	    CHeadArrowMeta.setOwner("MHF_ArrowUp");
	    CHeadArrowMeta.setLore(Arrays.asList(new String[]
	    		{ 
	    			"§7Vous permet de sauter de",
	    			"§7quelques blocs pour éviter des",
	    			"§7obstacles.",
	    			"§e",
	    			"§7§cDurée de régénération:",
	    			"    §f20 secondes"
	    		}));
	    CHeadArrow.setItemMeta(CHeadArrowMeta);
	    
	    CBlockR = new ItemStack(Material.REDSTONE_BLOCK);
	    ItemMeta CBlockRMeta = CBlockR.getItemMeta();
	    CBlockRMeta.setDisplayName(ChatColor.RED + "Coup de langue");
	    CBlockRMeta.setLore(Arrays.asList(new String[]
	    		{
	    			"§7Permet de soigner un allié en",
	    			"§7le léchant. Vous immobilise",
	    			"§7pendant quelques instants.",
	    			"§e",
	    			"§7§cDurée de régénération:",
	    			"    §f50 secondes"
	    		}));
	    CBlockR.setItemMeta(CBlockRMeta);
	    
	    CCompass = new ItemStack(Material.COMPASS);
	    ItemMeta CCompassMeta = CCompass.getItemMeta();
	    CCompassMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Frigo à §f{d} mètres");
	    CCompassMeta.setLore(Arrays.asList(new String[]
	    		{
	    			"§7Le frigo est par là. ",
	    			"§7Protégez le !"
	    		}));
	    CCompass.setItemMeta(CCompassMeta);
	    
	    CHeadSilver = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	    SkullMeta CHeadSilverMeta = (SkullMeta)CHeadSilver.getItemMeta();
	    CHeadSilverMeta.setDisplayName(ChatColor.GOLD + "Souris tuées: " + ChatColor.WHITE + "0");
	    CHeadSilverMeta.setOwner(/* TODO silverfish head */null);
	    CHeadSilverMeta.setLore(Arrays.asList(new String[]
	    		{ 
	    			"§7Tuez les toutes !"
	    		}));
	    CHeadSilver.setItemMeta(CHeadSilverMeta);
	}
	
	public void mouse()
	{
		
	}
	
	public void waiting()
	{
		
	}
}
