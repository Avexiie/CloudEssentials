package ceo.cloudnation.cloudessentials.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KickbanEvent extends Event {
	
	Player p;
	Type t;
	
	public KickbanEvent(Player p, Type t) {
		this.p = p;
		this.t = t;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public Type getType() {
		return t;
	}
	
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
