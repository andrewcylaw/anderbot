package com.anderbot.bot.command;

import com.anderbot.bot.Util.CommandResponseCode;
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

    // TODO - perhaps this should be string args?
    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent msgEvent = (MessageReceivedEvent) event;

        // do something

        return CommandResponseCode.OK;
    }

}
