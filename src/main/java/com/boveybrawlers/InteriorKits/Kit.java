package com.boveybrawlers.InteriorKits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import com.boveybrawlers.InteriorKits.util.ValueComparator;

public class Kit {
	private InteriorKits plugin;
	
	public FileConfiguration heads;
	
	ArrayList<ItemStack> skulls = null;
	
	public Kit(InteriorKits plugin) {
		this.plugin = plugin;
	}
	
	public ArrayList<ItemStack> get() {
		if(skulls == null) {
			this.load();
		}
		
		return this.skulls;
	}
	
	private void load() {
		// Get the top 9 player heads and save it to this.skulls
		heads = plugin.heads.get();
		
		Map<String, Integer> playerHeads = new HashMap<String, Integer>();
		
		for(String key : heads.getKeys(true)) {
			Integer value = heads.getInt(key);
			playerHeads.put(key, value);
		}
		
		if(playerHeads.size() > 0) {
			ValueComparator bvc =  new ValueComparator(playerHeads);
			TreeMap<String, Integer> sortedPlayerHeads = new TreeMap<String, Integer>(bvc);
			
			sortedPlayerHeads.putAll(playerHeads);
			
			ArrayList<ItemStack> skulls = new ArrayList<ItemStack>();
			
			int i = 0;
			for(Map.Entry<String, Integer> entry : sortedPlayerHeads.entrySet()) {
				if(i < 9) {
					String owner = entry.getKey();
					ItemStack skull = Skull.makePlayer(owner);
					skulls.add(skull);
					
					i++;
				}
			}
			
			this.skulls = skulls;
		}
	}
}
