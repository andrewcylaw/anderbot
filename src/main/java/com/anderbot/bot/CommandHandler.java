package com.anderbot.bot;

import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.command.Command;
import sx.blah.discord.api.events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Redirects events to the appropriate command handler.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class CommandHandler {

    private Map<String, Command> commandMap;

    private CommandHandler(List<Command> commands) {
        this.commandMap = new HashMap<>();
        commands.forEach(c -> this.commandMap.put(c.getIdentifier(), c));
    }

    public CommandResponseCode handle(String identifier, Event event) {
        Command cmd = commandMap.get(identifier);

        return cmd == null ? CommandResponseCode.FAILED_INVALID_COMMAND : cmd.handle(event);
    }
}
