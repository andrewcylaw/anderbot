package com.anderbot.bot.command.help;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.message.help.EmbeddedHelp;
import com.anderbot.bot.util.BotUtils;
import com.anderbot.bot.util.CommandResponseCode;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.util.DiscordException;

import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends AbstractCommand implements Command {

    private Map<String, EmbeddedHelp> embeddedHelpMap = new HashMap<>();

    public HelpCommand(String identifier) {
        super(identifier);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        super.parseMessageReceivedEvent(event);
        EmbeddedHelp embeddedHelp;

        if(getArgs().isEmpty() || (embeddedHelp = embeddedHelpMap.get(getArgs().get(0))) == null) {
            return CommandResponseCode.INVALID_COMMAND;
        }

        try {
            BotUtils.sendMessage(getMessageReceivedEvent().getChannel(), embeddedHelp);
        } catch (DiscordException e) {
            e.printStackTrace();
            return CommandResponseCode.INVALID_HELP;
        }

        return CommandResponseCode.OK;
    }

    // For Spring property injection
    public void setEmbeddedHelpMap(Map<String, EmbeddedHelp> embeddedHelpMap) {
        this.embeddedHelpMap = embeddedHelpMap;
    }
}
