package com.anderbot.bot.command.blackjack.card;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.util.CommandResponseCode;
import sx.blah.discord.api.events.Event;

public class BlackjackCommand extends AbstractCommand implements Command {

    public BlackjackCommand(String... identifiers) {
        super(identifiers);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        return CommandResponseCode.INVALID_NUMBER;
    }

}
