package com.boveybrawlers.InteriorKits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Head {
	
	public static ItemStack get(String owner) {
		ItemStack skullStack = new ItemStack(Material.SKULL_ITEM, 1);
		skullStack.setDurability((short) 3);
		SkullMeta meta = (SkullMeta) skullStack.getItemMeta();
		meta.setOwner(owner);
		skullStack.setItemMeta(meta);
		
		return skullStack;
	}
	
}