package com.boveybrawlers.InteriorKits;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.boveybrawlers.InteriorKits.listeners.InventoryClick;
import com.boveybrawlers.InteriorKits.util.ConfigLoader;

public class InteriorKits extends JavaPlugin {
	
	public InteriorKits plugin;
	public String name = ChatColor.DARK_PURPLE + "InteriorKits" + ChatColor.RESET;
	public String prefix = ChatColor.DARK_GRAY + "[" + name + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
	
	public String[] groups = {"food", "devices", "misc", "alphabet", "interior", "color", "blocks", "mobs", "games", "characters", "pokemon"};
	
	public ConfigLoader heads;
	public Kit topKit = null;
	
	@Override
	public void onEnable() {
		this.plugin = this;
		
		this.heads = new ConfigLoader(plugin, "heads.yml");
		this.topKit = new Kit(plugin);
		
		this.getCommand("head").setExecutor(new HeadCommands(plugin));
		this.getCommand("skull").setExecutor(new HeadCommands(plugin));
		this.getCommand("ikit").setExecutor(new KitCommands(plugin));
		this.getCommand("interior").setExecutor(new KitCommands(plugin));
		
		this.getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
	}
	
	@Override
	public void onDisable() {
		heads.save();
	}
	
}