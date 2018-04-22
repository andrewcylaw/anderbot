package com.anderbot.bot.command.blackjack.card;

public enum CardSuit {

    DIAMONDS(":diamonds:"),
    CLUBS   (":clubs:"),
    HEARTS  (":heart:"),
    SPADES  (":spades:");

    private String emojiStr;

    CardSuit(String emojiStr) {
        this.emojiStr = emojiStr;
    }

    public String getEmojiStr() {
        return this.emojiStr;
    }

}
