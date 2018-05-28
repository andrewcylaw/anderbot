package com.anderbot.bot.command.blackjack;

import com.anderbot.bot.command.blackjack.card.Card;
import com.anderbot.bot.command.blackjack.card.CardValue;

import java.util.List;

/**
 * Manages a player's hand. Includes methods for hand value checks, hand bust checks, etc
 */
public class BlackjackHand {

    public enum BlackjackHandStatus {
        PLAYING,
        BUST,
        BLACKJACK
    }

    private static final int BLACKJACK = 21;
    private List<Card> hand;

    // Starting hand has 2 cards
    public BlackjackHand(Card card1, Card card2) {
        this.hand.add(card1);
        this.hand.add(card2);
    }

    public int getMinHandValue() {
        return this.hand.stream().mapToInt(card -> card.getCardValue().getValue()).sum();
    }

    public BlackjackHandStatus getBlackjackHandStatus() {
        int handValue = this.hand.stream().anyMatch(c -> c.getCardValue().equals(CardValue.ACE))
                ? getMinHandValue()
                : getMinHandValue() + 10;

        if(handValue == BLACKJACK) {
            return BlackjackHandStatus.BLACKJACK;
        } else if (handValue > BLACKJACK) {
            return BlackjackHandStatus.BUST;
        }
        return BlackjackHandStatus.PLAYING;
    }

    public BlackjackHand addCard(Card card) {
        this.hand.add(card);
        return this;
    }

}
