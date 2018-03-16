package com.anderbot.bot.command;

import com.anderbot.bot.util.BotUtil;
import com.anderbot.bot.util.CommandResponseCode;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Sample anderbot command.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class SampleCommand implements Command {

    // "identifier" is the bot command on Discord
    private String identifier;

    public SampleCommand(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent msgEvent = (MessageReceivedEvent) event;

        // At this point, the command and prefix have been verified correctly
        BotUtil.sendMessage(((MessageReceivedEvent) event).getChannel(), "The message received from SampleCommand was " + msgEvent.getMessage());

        return CommandResponseCode.OK;
    }

}
