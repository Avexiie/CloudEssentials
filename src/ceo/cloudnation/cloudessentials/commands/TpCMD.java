package ceo.cloudnation.cloudessentials.commands;

import org.bukkit.Bukkit; 
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("tp")) {
			if (sender instanceof Player) {
				
				Player p = (Player) sender;
				
				if (args.length == 1) {
					Player target = (Player) Bukkit.getPlayer(args[0]);
					if (target != null) {
						if (p != target) {
							p.teleport(target);
							p.sendMessage("You has been teleported " + target.getDisplayName() + " teleportiert.");
							target.sendMessage("Tes " + p.getDisplayName() + " bacottest teleportiert.");
							return true;
						}else {
							p.sendMessage("Sudah ku bilang.");
							return true;
						}
					}else {
						p.sendMessage("Kintil " + args[0] + " He is not online!.");
						return true;
					}
				}else {
					p.sendMessage("Command: /tp [Name])");
					return true;
				}
			}else {
				sender.sendMessage("Lah dodol.");
				return true;
			}
			
		}
		return false;
	}
}
