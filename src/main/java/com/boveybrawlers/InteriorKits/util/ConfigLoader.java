package com.boveybrawlers.InteriorKits.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.boveybrawlers.InteriorKits.InteriorKits;

public class ConfigLoader {
	
	private InteriorKits plugin;
	private String file;
	
	public FileConfiguration config = null;
	private File configFile = null;
	
	public ConfigLoader(InteriorKits plugin, String file) {
		this.plugin = plugin;
		this.file = file;
	}

	public void reload() {
		if(configFile == null) {
			configFile = new File(plugin.getDataFolder(), this.file);
		}
		
		config = YamlConfiguration.loadConfiguration(configFile);
		
	    Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(plugin.getResource(this.file), "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        config.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration get() {
	    if (config == null) {
	        reload();
	    }
	    return config;
	}
	
	public void save() {
	    if (config == null || configFile == null) {
	        return;
	    }
	    try {
	        get().save(configFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
	    }
	}
	
}
