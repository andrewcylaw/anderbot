package com.anderbot.bot.command.help;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.message.help.EmbeddedHelp;
import com.anderbot.bot.util.CommandResponseCode;
import sx.blah.discord.api.events.Event;

import java.util.Map;

public class HelpCommand extends AbstractCommand implements Command {

    Map<String, EmbeddedHelp> embeddedHelpMap;

    public HelpCommand(String identifier) {
        super(identifier);
    }

    // TODO Spring factory method to setup the help messages


    @Override
    public CommandResponseCode handle(Event event) {
        return null;
    }
}
