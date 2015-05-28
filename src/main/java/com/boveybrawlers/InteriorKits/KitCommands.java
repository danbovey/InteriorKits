package com.boveybrawlers.InteriorKits;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitCommands implements CommandExecutor {
	
	private InteriorKits plugin;
	
	public KitCommands(InteriorKits plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			// Defaults must be in creative mode so that the inventory is not replaced
			if(player.hasPermission("interiorkits.use") || player.isOp() || player.hasPermission("interiorkits.admin")) {
				if(player.getGameMode() == GameMode.CREATIVE || player.isOp() || player.hasPermission("interiorkits.admin")) {
					if(args.length == 0) {
						// Load the InteriorKits top kit and replace the player's hotbar with it
						ArrayList<ItemStack> skulls = plugin.topKit.get();
						
						if(skulls.size() > 0) {
							int i = 0;
							for(ItemStack skull : skulls) {
								player.getInventory().setItem(i, skull);
								i++;
							}
						} else {
							player.sendMessage(plugin.prefix + ChatColor.RED + "There are no items in the kit to give you :(");
						}
					} else {
						player.sendMessage(plugin.prefix + ChatColor.DARK_AQUA + "Usage: /" + command + " <player>");
					}
				} else {
					player.sendMessage(plugin.prefix + ChatColor.RED + "You must be in creative mode to use InteriorKits");
				}
			}
		} else {
			sender.sendMessage(plugin.prefix + ChatColor.RED + "You must be a player to use InteriorKits");
		}
		
		return true;
	}

}
