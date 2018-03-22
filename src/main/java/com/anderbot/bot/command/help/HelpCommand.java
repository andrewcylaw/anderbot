package com.anderbot.bot.command.help;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.message.help.EmbeddedHelp;
import com.anderbot.bot.util.BotUtils;
import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.util.MessageUtils;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpCommand extends AbstractCommand implements Command {

    private Map<String, EmbeddedHelp> embeddedHelpMap = new HashMap<>();

    public HelpCommand(String identifier) {
        super(identifier);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
        List<String> args = MessageUtils.getArgs(messageReceivedEvent.getMessage());
        EmbeddedHelp embeddedHelp;

        if(args.isEmpty() || (embeddedHelp = embeddedHelpMap.get(args.get(0))) == null) {
            return CommandResponseCode.INVALID_COMMAND;
        }

        try {
            BotUtils.sendMessage(messageReceivedEvent.getChannel(), embeddedHelp);
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
