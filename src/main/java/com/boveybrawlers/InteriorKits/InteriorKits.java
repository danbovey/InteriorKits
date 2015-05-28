package com.boveybrawlers.InteriorKits;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class InteriorKits extends JavaPlugin {
	
	public InteriorKits plugin;
	public String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_PURPLE + "InteriorKits" + ChatColor.DARK_GRAY + "]";
	public HeadConfig heads;
	
	public Kit topKit = null;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		heads = new HeadConfig(plugin);
		topKit = new Kit(plugin);
		
		this.getCommand("head").setExecutor(new HeadCommands(plugin));
		this.getCommand("skull").setExecutor(new HeadCommands(plugin));
		this.getCommand("ikit").setExecutor(new KitCommands(plugin));
		this.getCommand("interior").setExecutor(new KitCommands(plugin));
		this.getCommand("int").setExecutor(new KitCommands(plugin));
	}
	
	@Override
	public void onDisable() {
		heads.save();
	}
	
}