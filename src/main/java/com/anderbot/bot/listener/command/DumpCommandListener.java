package com.anderbot.bot.listener.command;

import com.anderbot.bot.command.dump.DumpCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Listens in on all events and checks if it has to command.
 */
@Component
public class DumpCommandListener implements IListener<MessageReceivedEvent> {

    private DumpCommand dumpCommand;

    @Override
    public void handle(MessageReceivedEvent event) {
        dumpCommand.dump(event);
    }
    
    @Autowired
    public void setDumpCommand(DumpCommand dumpCommand) {
        this.dumpCommand = dumpCommand;
    }
}
