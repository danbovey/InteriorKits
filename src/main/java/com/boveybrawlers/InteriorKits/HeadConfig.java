package com.boveybrawlers.InteriorKits;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class HeadConfig {
	
	private InteriorKits plugin;
	
	public FileConfiguration headsConfig = null;
	private File headsConfigFile = null;
	
	public HeadConfig(InteriorKits plugin) {
		this.plugin = plugin;
	}

	public void reload() {
		if(headsConfigFile == null) {
			headsConfigFile = new File(plugin.getDataFolder(), "heads.yml");
		}
		
		headsConfig = YamlConfiguration.loadConfiguration(headsConfigFile);
		
	    Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(plugin.getResource("heads.yml"), "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        headsConfig.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration get() {
	    if (headsConfig == null) {
	        reload();
	    }
	    return headsConfig;
	}
	
	public void save() {
	    if (headsConfig == null || headsConfigFile == null) {
	        return;
	    }
	    try {
	        get().save(headsConfigFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + headsConfigFile, ex);
	    }
	}
	
}
