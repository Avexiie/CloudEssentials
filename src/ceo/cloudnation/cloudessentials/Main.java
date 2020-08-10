package ceo.cloudnation.cloudessentials;


import org.bukkit.plugin.java.JavaPlugin;   

import ceo.cloudnation.cloudessentials.commands.FeedCMD;
import ceo.cloudnation.cloudessentials.commands.FlyCMD;
import ceo.cloudnation.cloudessentials.commands.HealCMD;
import ceo.cloudnation.cloudessentials.commands.PingCMD;
import ceo.cloudnation.cloudessentials.commands.TpCMD;
import ceo.cloudnation.cloudessentials.commands.KickbanCMD;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		System.out.println("Fly has been enabled!");
		
		getCommand("fly").setExecutor(new FlyCMD());
		getCommand("feed").setExecutor(new FeedCMD());
		getCommand("heal").setExecutor(new HealCMD());
		getCommand("ping").setExecutor(new PingCMD());
		getCommand("tp").setExecutor(new TpCMD());
		getCommand("kick").setExecutor(new KickbanCMD());
		getCommand("ban").setExecutor(new KickbanCMD());
		
	}
	
	public void onDisable() {
		System.out.println("Fly has been disable!");
	}
}