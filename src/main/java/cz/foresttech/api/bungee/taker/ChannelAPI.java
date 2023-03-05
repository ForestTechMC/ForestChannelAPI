package cz.foresttech.api.bungee.taker;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cz.foresttech.api.bungee.events.ChannelHandler;
import cz.foresttech.api.shared.ChannelTaker;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Collection;

public class ChannelAPI implements ChannelTaker {

    private Plugin plugin;

    public ChannelAPI(Plugin plugin) {
        this.plugin = plugin;
        registerEvent(plugin, new ChannelHandler(plugin));
    }

    /**
     *
     * Send method for Bungee -> Spigot
     *
     * @param player sender
     * @param channel channel for
     * @param message content
     */
    @Override
    public void send(Object player, String channel, String message) {
        ProxiedPlayer sender = (ProxiedPlayer) player;

        ByteArrayDataOutput output = ByteStreams.newDataOutput();

        output.writeUTF("Message");
        output.writeUTF(message);
        sender.getServer().getInfo().sendData(getChannel(channel), output.toByteArray());
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
     * This register messenger for spigot
     *
     */
    @Override
    public void register(String channel) {
        channel = getChannel(channel);

        if (plugin.getProxy().getChannels().contains(channel)) {
            return;
        }

        plugin.getProxy().registerChannel(channel);
    }

    /**
     *
     * This unregister messenger for spigot
     *
     */
    @Override
    public void unregister(String channel) {
        channel = getChannel(channel);

        if (!plugin.getProxy().getChannels().contains(channel)) {
            return;
        }

        plugin.getProxy().unregisterChannel(channel);
    }

    /**
     *
     * Method only for register event
     *
     */
    @Override
    public void registerEvent(Object plugin, Object listener) {
        this.plugin.getProxy().getPluginManager().registerListener((Plugin) plugin, (Listener) listener);
    }

    /**
     *
     * This only returns channels from out list
     *
     */
    @Override
    public Collection<String> getChannels() {
        return plugin.getProxy().getChannels();
    }
}
