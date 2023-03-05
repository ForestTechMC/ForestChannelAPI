package cz.foresttech.api.spigot.taker;

import cz.foresttech.api.shared.ChannelTaker;
import cz.foresttech.api.spigot.events.ChannelHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

public class ChannelAPI implements ChannelTaker {

    private JavaPlugin plugin;

    public ChannelAPI(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     *
     * Send method for Spigot -> Bungee
     *
     * @param player sender
     * @param channel channel for
     * @param message content
     */
    @Override
    public void send(Object player, String channel, String message) {
        Player sender = (Player) player;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(stream);

        try {
            output.writeUTF("Message");
            output.writeUTF(sender.getName());
            output.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sender.sendPluginMessage(plugin, getChannel(channel), stream.toByteArray());
    }

    /**
     *
     * This returns channel with prefix of plugin
     * This is important part
     *
     */
    @Override
    public String getChannel(String channelName) {
        return (plugin.getDescription().getName() + ":" + channelName).toLowerCase();
    }

    /**
     *
     * This remove prefix of plugin
     *
     */
    @Override
    public String removeTag(String channelWithTag) {
        channelWithTag = channelWithTag.replace(plugin.getDescription().getName().toLowerCase() + ":", "");
        return channelWithTag;
    }

    /**
     *
     * Method only for register event
     *
     */
    @Override
    public void registerEvent(Object plugin, Object listener) {
        Bukkit.getServer().getPluginManager().registerEvents((Listener) listener, (JavaPlugin) plugin);
    }

    /**
     *
     * This register messenger for spigot
     *
     */
    @Override
    public void register(String channelName) {
        String channel = getChannel(channelName);

        if (Bukkit.getMessenger().getOutgoingChannels().contains(channel)) {
            return;
        }

        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, channel);
        Bukkit.getMessenger().registerIncomingPluginChannel(plugin, channel, new ChannelHandler());
    }

    /**
     *
     * This unregister messenger for spigot
     *
     */
    @Override
    public void unregister(String channelName) {
        String channel = getChannel(channelName);

        if (Bukkit.getMessenger().getOutgoingChannels().contains(channel)) {
            return;
        }

        Bukkit.getMessenger().unregisterOutgoingPluginChannel(plugin, channel);
        Bukkit.getMessenger().unregisterIncomingPluginChannel(plugin, channel, new ChannelHandler());
    }

    /**
     *
     * This only returns channels from out list
     *
     */
    @Override
    public Collection<String> getChannels() {
        return Bukkit.getMessenger().getOutgoingChannels();
    }
}
