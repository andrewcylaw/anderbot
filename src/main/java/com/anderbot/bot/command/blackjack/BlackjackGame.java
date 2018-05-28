package com.anderbot.bot.command.blackjack;

import com.anderbot.bot.command.blackjack.card.Deck;
import org.springframework.stereotype.Component;

import static com.anderbot.bot.command.blackjack.BlackjackGame.BlackjackState.*;

/**
 * Manages the actual gameplay and rules of a single Blackjack game. (ie, nothing to do with discord)
 * Connects a deck of cards to a Blackjack hand.
 */
@Component
public class BlackjackGame {

    protected enum BlackjackState {
        WAITING_FOR_PLAYERS,
        PLAYER_TURN, // Per player -> hit, check bust
        DEALER_TURN,
        END_ROUND
    }

    private Deck deck;
    private BlackjackState state;

    public BlackjackGame() {
        this.deck = new Deck();
        this.state = WAITING_FOR_PLAYERS;
    }

    public BlackjackHand getNewHand() {
        return new BlackjackHand(deck.getNextCard(), deck.getNextCard());
    }

    public BlackjackHand hit(BlackjackHand hand) {
        return hand.addCard(deck.getNextCard());
    }



}
