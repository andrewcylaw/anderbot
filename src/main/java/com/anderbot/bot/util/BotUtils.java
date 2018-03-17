package com.anderbot.bot.util;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Bot utilities. Code referenced from Discord4J's basic-bot.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class BotUtils {

    public static IDiscordClient getClient(String token) {
        return new ClientBuilder()
                .withToken(token)
                .build();
    }

    public static void sendMessage(IChannel channel, String message) throws DiscordException {
            RequestBuffer.request(() -> {
                try {
                    channel.sendMessage(message);
                } catch (DiscordException e) {
                    e.printStackTrace();
                    throw e;
                }
            });
    }

    /*
    Note: Unused in DumpCommand because that requires additional checks on the return.
     */
    public static void reactToMessage(IMessage message, ReactionEmoji emoji) throws DiscordException {
        RequestBuffer.request(() -> {
            try {
                message.addReaction(emoji);
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        });
    }

}
