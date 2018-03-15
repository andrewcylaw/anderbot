package com.anderbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Experimental event listener.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class MessageEventListener implements IListener<MessageReceivedEvent> {

    private CommandHandler commandHandler;

    public MessageEventListener() { }

    @Override
    public void handle(MessageReceivedEvent event) {
        String[] msg = event.getMessage().getContent().split(" ");
        System.out.println("MessageReceiveEvent was triggered: " + msg);

        // Handle the command
        commandHandler.handle(msg[0], event);
    }

    @Autowired
    public void setCommandHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

}
