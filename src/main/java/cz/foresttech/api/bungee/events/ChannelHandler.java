package cz.foresttech.api.bungee.events;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collection;


public class ChannelHandler implements Listener {

    private Plugin plugin;

    public ChannelHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     *
     * This is our caller for custom event
     * We did this for better manipulation with content
     *
     * @param event Official plugin message event
     */
    @EventHandler
    public void onPluginMessageEvent(PluginMessageEvent event) {
        if (event.getTag().startsWith("minecraft")) {
            return;
        }

        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(event.getData()));

        String message;
        String playerName;
        try {
            stream.readUTF();
            playerName = stream.readUTF();
            message = stream.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(playerName);
        if (player == null && !player.isConnected()) {
            Collection<ProxiedPlayer> players = ProxyServer.getInstance().getPlayers();
            player = players.stream().findFirst().get();
        }
        plugin.getProxy().getPluginManager().callEvent(new ChannelEvent(event.getTag(), message, player, event.getSender(), event.getReceiver()));
    }

}
