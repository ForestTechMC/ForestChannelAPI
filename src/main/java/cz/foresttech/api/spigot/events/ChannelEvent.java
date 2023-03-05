package cz.foresttech.api.spigot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChannelEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final String channel;
    private final String message;

    public ChannelEvent(Player player, String channel, String message) {
        this.player = player;
        this.channel = channel;
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
