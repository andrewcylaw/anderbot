package com.anderbot.bot;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Bot utilities. Code referenced from Discord4J's basic-bot.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class BotUtil {

    static String BOT_PREFIX = "/";

    public static IDiscordClient getClient(String token) {
        return new ClientBuilder()
                .withToken(token)
                .build();
    }

    public static void sendMessage(IChannel channel, String message) {
            RequestBuffer.request(() -> {
                try {
                    channel.sendMessage(message);
                } catch (DiscordException e) {
                    e.printStackTrace();
                }
            });
    }

}
