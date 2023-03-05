package cz.foresttech.api.spigot.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ChannelHandler implements PluginMessageListener {

    /**
     *
     * Universal Plugin listener for messages
     *
     * @param channel from
     * @param player sender
     * @param bytes message
     */
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));
        String message;
        try {
            stream.readUTF();
            message = stream.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ChannelEvent channelEvent = new ChannelEvent(player, channel, message);
        Bukkit.getPluginManager().callEvent(channelEvent);

    }

}
