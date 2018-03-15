package com.anderbot.bot;


import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Experimental file.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class Main {

    public static void main(String[] args) {
        IDiscordClient sampleClient = BotUtil.getClient("dummy token");
        sampleClient.login();

        EventDispatcher dispatcher = sampleClient.getDispatcher();
        IListener<MessageReceivedEvent> listener = new MessageEventListener();

        dispatcher.registerListener(listener);
    }

}
