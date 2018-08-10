package com.anderbot.bot.command.roll;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.util.BotUtils;
import com.anderbot.bot.util.CommandResponseCode;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

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
        super.parseMessageReceivedEvent(event);

        switch(getArgs().size()) {
            case 0:
                this.rollDice(DEFAULT_DICE_SIZE, DEFAULT_NUM_ROLLS, getMessageReceivedEvent());
                break;
            case 1:
                try {
                    this.rollDice(Integer.parseInt(getArgs().get(0)), DEFAULT_NUM_ROLLS, getMessageReceivedEvent());
                } catch (NumberFormatException e) {
                    return CommandResponseCode.INVALID_NUMBER;
                }
                break;
            case 2:
                try {
                    this.rollDice(Integer.parseInt(getArgs().get(0)), Integer.parseInt(getArgs().get(1)), getMessageReceivedEvent());
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
