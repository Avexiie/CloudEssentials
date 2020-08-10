package ceo.cloudnation.cloudessentials.commands;

import org.bukkit.command.Command;  
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ceo.cloudnation.cloudessentials.utils.Utils;
import ceo.cloudnation.cloudessentials.Main;

public class FeedCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat("&eOnly players may execute this command!"));
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.hasPermission("galactic.feed")) {
			
			int maxFoodLevel = 20;
			
			if(p.getFoodLevel() < maxFoodLevel) {
			   p.setFoodLevel(maxFoodLevel);
			   p.sendMessage(Utils.chat("You have been feed!"));
			   return true;
			}else {
				p.sendMessage(Utils.chat("&cYou are not hungry!"));
			}
		}else {
			p.sendMessage(Utils.chat("&e&lGalacticEssentials &6/ &cYou do not have permission galactic.feed"));
		}
		return false;
	}
}