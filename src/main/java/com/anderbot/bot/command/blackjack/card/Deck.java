package com.anderbot.bot.command.blackjack.card;

import java.util.*;

public class Deck {

    private Stack<Card> cardDeck;

    public Deck() {
        this.cardDeck = new Stack<>();

        for(CardValue val : CardValue.values()) {
            for(CardSuit suit : CardSuit.values()) {
                this.cardDeck.add(new Card(suit, val));
            }
        }

        Collections.shuffle(this.cardDeck, new Random(System.nanoTime()));
    }

    public Card getNextCard() {
        return this.cardDeck.pop();
    }

    public int getRemainingCardCount() {
        return this.cardDeck.size();
    }

    // Probably will never need to call this ever
    public boolean hasCardsLeft() {
        return !this.cardDeck.isEmpty();
    }

}
