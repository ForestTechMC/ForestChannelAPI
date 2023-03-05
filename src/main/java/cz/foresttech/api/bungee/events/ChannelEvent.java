package cz.foresttech.api.bungee.events;

import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;

public class ChannelEvent extends Event{
    private final String channel;
    private final String message;
    private final ProxiedPlayer sender;
    private final Connection senderCon;
    private final Connection receiverCon;

    public ChannelEvent(String channel, String message, ProxiedPlayer sender, Connection senderCon, Connection receiverCon) {
        this.channel = channel;
        this.message = message;
        this.sender = sender;
        this.senderCon = senderCon;
        this.receiverCon = receiverCon;
    }

    public ProxiedPlayer getSender() {
        return sender;
    }

    public Connection getSenderCon() {
        return senderCon;
    }

    public Connection getReceiverCon() {
        return receiverCon;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

}
