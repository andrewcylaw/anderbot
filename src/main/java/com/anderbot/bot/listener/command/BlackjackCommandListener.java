package com.anderbot.bot.listener.command;

import com.anderbot.bot.command.blackjack.BlackjackCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

@Component
public class BlackjackCommandListener implements IListener<MessageReceivedEvent> {

    private BlackjackCommand blackjackCommand;

    @Autowired
    private BlackjackCommandListener(BlackjackCommand blackjackCommand) {
        this.blackjackCommand = blackjackCommand;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        // Waiting for players to hit, etc
    }

}
