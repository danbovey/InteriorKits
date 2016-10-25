package com.boveybrawlers.InteriorKits.guis;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.boveybrawlers.InteriorKits.InteriorKits;
import com.boveybrawlers.InteriorKits.util.ConfigLoader;

public class PagedGUI extends GUI {
	
	private InteriorKits plugin;
	String group;
	int page;

	public PagedGUI(InteriorKits plugin, String group, int page) {
		super(plugin);
		this.plugin = plugin;
		this.group = group;
		this.page = page;
	}
	
	private int invSize = 45;
	private int itemSize = 36;
	private int prevArrowSlot = 47;
	private int nextArrowSlot = 49;
	
	public void open(Player player) {
		this.inv = Bukkit.createInventory(player, this.invSize, this.getTitle());
		
		FileConfiguration config = new ConfigLoader(this.plugin, this.group + ".yml").get();
		List<Map<?, ?>> items = config.getMapList("items");
		
		int i;
		for(i = this.page * this.itemSize - this.itemSize; i < this.page * this.itemSize; i++) {
			Map<?, ?> map = items.get(i);
			this.createItem((String) map.get("name"), (String) map.get("texture"));
		}
		
		if(items.get(i) != null) {
			this.createNextPaginator();
		}
		if(page > 1) {
			this.createPrevPaginator();
		}
		
		player.openInventory(this.inv);
	}
	
	protected void createPrevPaginator() {
		ItemStack item = new ItemStack(Material.ARROW, this.page - 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "Previous page");
		item.setItemMeta(meta);
		
		this.inv.setItem(this.prevArrowSlot, item);
	}
	
	protected void createNextPaginator() {
		ItemStack item = new ItemStack(Material.ARROW, this.page + 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "Next page");
		item.setItemMeta(meta);
		
		this.inv.setItem(this.nextArrowSlot, item);
	}

}
