package com.anderbot.bot.command.blackjack.card;

public enum CardSuit {

    DIAMONDS(1, ":diamonds:"),
    CLUBS   (2, ":clubs:"),
    HEARTS  (3, ":heart:"),
    SPADES  (4, ":spades:");

    private int value;
    private String emojiStr;

    CardSuit(int value, String emojiStr) {
        this.value = value;
        this.emojiStr = emojiStr;
    }

    public String getEmojiStr() {
        return this.emojiStr;
    }

}
