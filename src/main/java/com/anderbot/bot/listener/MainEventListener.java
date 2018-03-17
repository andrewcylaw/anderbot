package com.anderbot.bot.listener;

import com.anderbot.bot.CommandHandler;
import com.anderbot.bot.util.BotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import static com.anderbot.bot.util.CommandResponseCode.FAILED_INVALID_COMMAND;
import static com.anderbot.bot.util.MessageUtils.checkValidCommand;
import static com.anderbot.bot.util.MessageUtils.getCommand;

/**
 * Main event listener.
 *
 * Created by andrew.law on 3/15/2018.
 */
@Component
public class MainEventListener implements IListener<MessageReceivedEvent> {

    private CommandHandler commandHandler;

    @Autowired
    private MainEventListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        IMessage eventMsg = event.getMessage();

        if(checkValidCommand(eventMsg)) {
            if(commandHandler.handle(getCommand(eventMsg), event).equals(FAILED_INVALID_COMMAND)) {
                BotUtils.sendMessage(event.getChannel(), FAILED_INVALID_COMMAND.getMessage());
            }
        }
    }

}
