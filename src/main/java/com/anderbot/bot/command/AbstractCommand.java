package com.anderbot.bot.command;

import com.anderbot.bot.event.CommandEvent;
import com.anderbot.bot.event.EventDispatcher;
import com.anderbot.bot.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Arrays;
import java.util.List;

/**
 * A generic command class with some useful defaults.
 */
public abstract class AbstractCommand implements Command {

    private EventDispatcher eventDispatcher;
    private List<String> identifiers;
    
    private MessageReceivedEvent messageReceivedEvent;
    private IMessage message;
    private IGuild guild;
    private IChannel channel;
    private List<String> args;

    public AbstractCommand(String... identifiers) {
        this.identifiers = Arrays.asList(identifiers);
    }
    
    public void parseMessageReceivedEvent(Event event) {
        messageReceivedEvent = (MessageReceivedEvent) event;
        message = messageReceivedEvent.getMessage();
        guild = message.getGuild();
        channel = messageReceivedEvent.getChannel();
        args = MessageUtils.getArgs(message);
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }
    
    public CommandEvent getCommandEvent(String command) {
        return this.eventDispatcher.getCommandEvent(this.identifiers.get(0) + command);
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public MessageReceivedEvent getMessageReceivedEvent() {
        return messageReceivedEvent;
    }

    public IMessage getMessage() {
        return message;
    }

    public IGuild getGuild() {
        return guild;
    }

    public IChannel getChannel() {
        return channel;
    }

    public List<String> getArgs() {
        return args;
    }

    @Autowired
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }
}
