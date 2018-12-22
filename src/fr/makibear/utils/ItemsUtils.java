package fr.makibear.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import net.md_5.bungee.api.ChatColor;

public class ItemsUtils 
{
	public static String RELOAD = ChatColor.RED + "Rechargement en cours...";
	
	public static void clear(Player p)
	{
		p.getInventory().clear();
		p.getInventory().setHelmet(getNothing());
	    p.getInventory().setBoots(getNothing());
	    p.getInventory().setLeggings(getNothing());
	    p.getInventory().setChestplate(getNothing());
		for(PotionEffect e : p.getActivePotionEffects())
			p.removePotionEffect(e.getType());
		p.updateInventory();
	}
	
	public static ItemStack getItem(Material m, int amount)
	{
	    return new ItemStack(m, amount);
	}
	
	public static ItemStack getNothing()
	{
	    return getItem(Material.AIR, 1);
	}
	
	public static void removeInventoryItems(Inventory inv, Material type, int amount) 
	{
        ItemStack[] items = inv.getContents();
        for (int i = 0; i < items.length; i++) 
        {
            ItemStack is = items[i];
            if (is != null && is.getType() == type) 
            {
                int newamount = is.getAmount() - amount;
                if (newamount > 0) 
                {
                    is.setAmount(newamount);
                    break;
                } 
                else 
                {
                    items[i] = new ItemStack(Material.AIR);
                    amount = -newamount;
                    if (amount == 0) break;
                }
            }
        }
        inv.setContents(items);
    }
	
	public static void enchantItem(ItemStack item, Enchantment enchantment, int level)
	{
	    item.addEnchantment(enchantment, level);
	}
}
