package com.anderbot.bot.command;

import com.anderbot.bot.util.CommandResponseCode;
import sx.blah.discord.api.events.Event;

import java.util.List;

/**
 * Bot command interface / event handler, implemented by all commands that anderbot expects to receive.
 *
 * Created by andrew.law on 3/15/2018.
 */
public interface Command {

    List<String> getIdentifiers();

    CommandResponseCode handle(Event event);

}
