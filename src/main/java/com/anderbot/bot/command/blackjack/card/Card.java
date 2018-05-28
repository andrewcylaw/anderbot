package com.anderbot.bot.command.blackjack.card;

public class Card {

    private CardSuit cardSuit;
    private CardValue cardValue;

    public Card(CardSuit cardSuit, CardValue value) {
        this.cardSuit = cardSuit;
        this.cardValue = value;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }
}
