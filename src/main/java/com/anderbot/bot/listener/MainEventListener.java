package com.anderbot.bot.listener;

import com.anderbot.bot.CommandHandler;
import com.anderbot.bot.util.BotUtils;
import com.anderbot.bot.util.CommandResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import static com.anderbot.bot.util.CommandResponseCode.NOT_A_COMMAND;
import static com.anderbot.bot.util.CommandResponseCode.OK;
import static com.anderbot.bot.util.MessageUtils.checkValidCommand;
import static com.anderbot.bot.util.MessageUtils.getCommandIdentifier;

/**
 * Main event listener.
 *
 * Created by andrew.law on 3/15/2018.
 */
@Component
public class MainEventListener implements IListener<MessageReceivedEvent> {

    private CommandHandler commandHandler;

    private MainEventListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        IMessage eventMsg = event.getMessage();

        if(checkValidCommand(eventMsg)) {
            CommandResponseCode responseCode = commandHandler.handle(getCommandIdentifier(eventMsg), event);

            // Nothing to print if not message is not a command or if it executed normally
            if(!responseCode.equals(OK) && !responseCode.equals(NOT_A_COMMAND)) {
                BotUtils.sendMessage(event.getChannel(), responseCode.getMessage());
            }
        }
    }

}
