package com.boveybrawlers.InteriorKits.guis;

import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.boveybrawlers.InteriorKits.InteriorKits;
import com.boveybrawlers.InteriorKits.Skull;

public abstract class GUI {
	
	private InteriorKits plugin;
	
	private String title;
	private String name;
	
	protected Inventory inv;
	
	public GUI(InteriorKits plugin) {
		this.plugin = plugin;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getName() {
		return this.name;
	}
	
	protected void setTitle(String title) {
		this.title = this.plugin.name + ChatColor.WHITE + " - " + title;
		this.name = title;
	}
	
	protected void createItem(String displayName, String texture) {
		ItemStack item = Skull.makeCustom(texture);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + displayName);
		item.setItemMeta(meta);
		
		this.inv.addItem(item);
	}

}
