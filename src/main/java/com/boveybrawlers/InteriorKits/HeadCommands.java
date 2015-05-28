package com.boveybrawlers.InteriorKits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class HeadCommands implements CommandExecutor {
	
	private InteriorKits plugin;
	
	public FileConfiguration heads;
	
	public HeadCommands(InteriorKits plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			final Player player = (Player) sender;
			
			// Defaults must be in creative mode so that the inventory is not replaced
			if(player.getGameMode() == GameMode.CREATIVE || player.isOp() || player.hasPermission("interiorkits.admin")) {
				if(args.length == 1) {
					// Replace the item in player's hand with the player head
					final String owner = args[0];
					ItemStack skull = Head.get(owner);
					player.getInventory().setItemInHand(skull);
					
					if(!player.hasMetadata("interiorkits-" + owner) && !player.hasMetadata("interiorkits-cooldown")) {
						// Add 1 to the player head in the config
						heads = plugin.heads.get();
						
						int amount = 1;
						if(heads.contains(owner)) {
							amount = heads.getInt(args[0]);
						}
						
						heads.set(owner, amount);
					}
					
					// Stop this player adding this to the config for a time
					player.setMetadata("interiorkits-cooldown", new FixedMetadataValue(plugin, true));
					player.setMetadata("interiorkits-" + owner, new FixedMetadataValue(plugin, true));
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							player.removeMetadata("interiorkits-cooldown", plugin);
						}
					}, 200);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							player.removeMetadata("interiorkits-" + owner, plugin);
						}
					}, 2400);
					
				} else {
					player.sendMessage(plugin.prefix + ChatColor.DARK_AQUA + "Usage: /" + command + " <player>");
				}
			} else {
				player.sendMessage(plugin.prefix + ChatColor.RED + "You must be in creative mode to use InteriorKits");
			}
		} else {
			sender.sendMessage(plugin.prefix + ChatColor.RED + "You must be a player to use InteriorKits");
		}
		
		return true;
	}
	
}