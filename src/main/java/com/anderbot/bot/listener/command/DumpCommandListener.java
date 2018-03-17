package com.anderbot.bot.listener.command;

import com.anderbot.bot.command.dump.DumpCommand;
import com.anderbot.bot.util.CommandResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Listens in on all events and checks if it has to dump.
 */
@Component
public class DumpCommandListener implements IListener<MessageReceivedEvent> {

    private DumpCommand dumpCommand;

    @Autowired
    private DumpCommandListener(DumpCommand dumpCommand) {
        this.dumpCommand = dumpCommand;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        CommandResponseCode dumpAttempt = dumpCommand.dump(event);
    }
}
