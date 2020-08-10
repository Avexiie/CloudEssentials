package ceo.cloudnation.cloudessentials.commands;

import net.md_5.bungee.api.ChatColor;     
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ceo.cloudnation.cloudessentials.Main;

public class HealCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(label.equalsIgnoreCase("heal")) {
			if(args.length == 0) {
				player.sendMessage("§7You have been healed!");
				player.setHealth(20.0);
				player.setFoodLevel(20);
				player.setFireTicks(0);
			}
		}
		return false;
	}
}