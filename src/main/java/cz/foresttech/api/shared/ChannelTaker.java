package cz.foresttech.api.shared;

import java.util.Collection;

/**
 *
 * Typical interface
 *
 */
public interface ChannelTaker {

    void send(Object player, String channel, String message);

    void register(String name);

    void unregister(String name);

    void registerEvent(Object plugin, Object listener);

    String getChannel(String channelName);

    String removeTag(String channelWithTag);

    Collection<String> getChannels();



}
