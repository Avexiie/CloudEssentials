package ceo.cloudnation.cloudessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command; 
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class PingCMD extends JavaPlugin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You are not a player!");
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("ping")) {
			int ping = ((CraftPlayer) p).getHandle().ping;
			p.sendMessage(ChatColor.GREEN + "Your ping: " + ping);
			return true;
		}
		return false;
	}
}
