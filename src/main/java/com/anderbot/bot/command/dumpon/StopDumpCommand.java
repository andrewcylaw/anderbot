package com.anderbot.bot.command.dumpon;

import com.anderbot.bot.util.CommandResponseCode;
import sx.blah.discord.api.events.Event;

/**
 * Cancels any active "dump on" command.
 *
 * Created by andrew.law on 3/16/2018.
 */
public class StopDumpCommand extends DumpOnCommand {

    private StopDumpCommand(String identifier) {
        super(identifier);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        // TODO - get the user, stop dumping on them

        return CommandResponseCode.OK;
    }

}
