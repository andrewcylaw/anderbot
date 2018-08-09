package com.anderbot.bot.util;

import com.anderbot.bot.message.help.EmbeddedHelp;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Utilities for bot operations. Code referenced from Discord4J's basic-bot.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class BotUtils {

    public static IDiscordClient getClient(String token) {
        return new ClientBuilder()
                .withToken(token)
                .build();
    }

    // TODO - clean this up
    public static void sendMessage(IChannel channel, String message) throws DiscordException {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendMessage(IChannel channel, EmbeddedHelp embeddedHelp) {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(embeddedHelp.getEmbeddedHelp());
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        });
    }

    public static void reactToMessage(IMessage message, ReactionEmoji emoji) throws DiscordException {
        RequestBuffer.request(() -> {
            try {
                message.addReaction(emoji);
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        });
    }
    
    // Connects to the first available voice channel if not already connected to a voice channel
    public static void connectToVoiceChannel(IGuild guild) {
        if(guild.getVoiceChannels().stream().anyMatch(IVoiceChannel::isConnected)) {
            return;
        }
        
        for(IVoiceChannel voiceChannel : guild.getVoiceChannels()) {
            try {
                voiceChannel.join();
            } catch (MissingPermissionsException e) {
                // TODO - do something useful like logging
            }
        }
    }

}
