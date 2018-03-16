package com.anderbot.bot.command;

import com.anderbot.bot.util.CommandResponseCode;
import sx.blah.discord.api.events.Event;

/**
 * Bot command interface / event handler, implemented by all commands that anderbot expects to receive.
 *
 * Created by andrew.law on 3/15/2018.
 */
public interface Command {

    String getIdentifier();

    CommandResponseCode handle(Event event);

}
