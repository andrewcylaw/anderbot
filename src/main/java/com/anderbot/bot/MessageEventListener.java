package com.anderbot.bot;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Experimental event listener.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class MessageEventListener implements IListener<MessageReceivedEvent> {

    public MessageEventListener() { }

    @Override
    public void handle(MessageReceivedEvent event) {
        String[] msg = event.getMessage().getContent().split(" ");
        System.out.println("MessageReceiveEvent was triggered: " + msg);
    }

}
