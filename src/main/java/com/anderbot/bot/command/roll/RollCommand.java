package com.anderbot.bot.command.roll;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.util.BotUtils;
import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.util.MessageUtils;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Every bot needs a good roll utility.
 *
 * Notes:
 * >roll roll-size num-times
 *   - defaults to 6-sided once if no args
 *   - defaults to roll-size once if one arg
 *
 */
public class RollCommand extends AbstractCommand implements Command {

    private static int DEFAULT_DICE_SIZE = 6;
    private static int DEFAULT_NUM_ROLLS = 1;

    public RollCommand(String identifier) {
        super(identifier);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
        IMessage message = messageReceivedEvent.getMessage();
        List<String> args = MessageUtils.getArgs(message);

        switch(args.size()) {
            case 0:
                this.rollDice(DEFAULT_DICE_SIZE, DEFAULT_NUM_ROLLS, messageReceivedEvent);
                break;
            case 1:
                try {
                    this.rollDice(Integer.parseInt(args.get(0)), DEFAULT_NUM_ROLLS, messageReceivedEvent);
                } catch (NumberFormatException e) {
                    return CommandResponseCode.INVALID_NUMBER;
                }
                break;
            case 2:
                try {
                    this.rollDice(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)), messageReceivedEvent);
                } catch (NumberFormatException e) {
                    return CommandResponseCode.INVALID_NUMBER;
                }
                break;
            default:
                return CommandResponseCode.INVALID_COMMAND;
        }

        return CommandResponseCode.OK;
    }

    private void rollDice(int diceSize, int numRolls, MessageReceivedEvent event) {
        int totalRoll = 0;

        for(int i = 0; i < numRolls; i++) {
            totalRoll += ThreadLocalRandom.current().nextInt(diceSize);
        }

        BotUtils.sendMessage(event.getChannel(), String.format(":game_die: **%s** rolled **%d**.", event.getAuthor(), totalRoll));
    }

}
