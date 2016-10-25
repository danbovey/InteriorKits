package com.boveybrawlers.InteriorKits.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.boveybrawlers.InteriorKits.InteriorKits;
import com.boveybrawlers.InteriorKits.guis.GUI;
import com.boveybrawlers.InteriorKits.guis.MainMenu;
import com.boveybrawlers.InteriorKits.guis.PagedGUI;

public class InventoryClick implements Listener {
	
	InteriorKits plugin;
	
	public InventoryClick(InteriorKits plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		
		// If it's our inventory
		if(inv.getTitle().contains("InteriorKits")) {
			GUI mainMenu = new MainMenu(this.plugin);
			if(inv.getTitle().equals(mainMenu.getTitle())) {
				// Cancel all events within our menu
				event.setCancelled(true);
				
				Player player = (Player) event.getWhoClicked();
				ItemStack item = event.getCurrentItem();
				if(item == null) {
		            return;
		        }
				
				String group = ChatColor.stripColor(item.getItemMeta().getDisplayName()).toLowerCase();
				this.plugin.getLogger().log(Level.INFO, group);
				
				// Search the group list for the item name
				List<String> groupList = Arrays.asList(this.plugin.groups);
				if(groupList.indexOf(group) > -1) {
					// Open the requested GUI on Page 1
					PagedGUI gui = new PagedGUI(this.plugin, group, 1);
					gui.open(player);
				}
			} else {
				for(String group : this.plugin.groups) {
					if(inv.getTitle().toLowerCase().contains(group)) {
						// Cancel all events within our inventory
						event.setCancelled(true);
						
						Player player = (Player) event.getWhoClicked();
						ItemStack item = event.getCurrentItem();
						if(item == null) {
				            return;
				        }
						
						if(item.getType() == Material.SKULL_ITEM) {
							// Add the skull to the players inventory
							player.getInventory().addItem(item);
						} else if(item.getType() == Material.ARROW) {
							// Go to the next/prev page
							// The amount of arrows represent page numbers
							PagedGUI gui = new PagedGUI(this.plugin, group, item.getAmount());
							gui.open(player);
						}
					}
				}
			}
		}
	}

}
