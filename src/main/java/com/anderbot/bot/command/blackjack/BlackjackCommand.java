package com.anderbot.bot.command.blackjack;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.command.blackjack.BlackjackHand.BlackjackHandStatus;
import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Blackjack command.
 * There is only one game going on at a time, and users join after rounds.
 *
 * Manages turns, printing out messages, etc.
 * The actual blackjack game logic can be found in {@link BlackjackGame}
 */
public class BlackjackCommand extends AbstractCommand implements Command {

    private BlackjackGame blackjackGame;
    private Map<IUser, BlackjackHand> playerHand;
    private Map<IUser, BlackjackHandStatus> playerStatus;

    public BlackjackCommand(String... identifiers) {
        super(identifiers);
        this.playerHand = new HashMap<>();
        this.playerStatus = new HashMap<>();
    }

    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
        IMessage message = messageReceivedEvent.getMessage();
        IGuild guild = message.getGuild();
        List<String> args = MessageUtils.getArgs(message);

        IUser player = messageReceivedEvent.getAuthor();

        // Start game, wait for x seconds for players to join (max like 4 or something)
        // Hit, fold, no splitting
        // TODO - error check for author not having a hand
        switch(args.get(0).toLowerCase()) {
            case "join":
                playerHand.put(player, blackjackGame.getNewHand());
            case "hit":
                BlackjackHand hand = blackjackGame.hit(playerHand.get(player));
                playerStatus.put(player, hand.getBlackjackHandStatus());
            default:
                break;
        }

        return CommandResponseCode.INVALID_NUMBER;
    }

    @Autowired
    private void setBlackjackGame(BlackjackGame blackjackGame) {
        this.blackjackGame = blackjackGame;
    }

}
