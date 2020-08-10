package ceo.cloudnation.cloudessentials.commands;

import org.bukkit.command.Command;   
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;


public class FlyCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] arghs) {
		// TODO Auto-generated method stub
		if(label.equalsIgnoreCase("fly")) {
			Player p = (Player) sender;
			if(p.isFlying() == true) {
				p.setAllowFlight(false);
				p.setFlying(false);
				p.sendMessage("§e§lGEssentials §6/ §cFly has been disabled!");
			}else {
				p.setAllowFlight(true);
				p.setFlying(true);
				p.sendMessage("§e§lGEssentials §6/ §aFly has been enabled!");
			}
		}
		return true;
	}
	
}