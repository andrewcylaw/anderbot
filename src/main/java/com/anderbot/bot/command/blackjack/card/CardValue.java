package com.anderbot.bot.command.blackjack.card;

public enum CardValue {

    ACE   (1,  ":regional_indicator_a:"), // Special case
    TWO   (2,  ":two:"),
    THREE (3,  ":three:"),
    FOUR  (4,  ":four:"),
    FIVE  (5,  ":five:"),
    SIX   (6,  ":six:"),
    SEVEN (7,  ":seven:"),
    EIGHT (8,  ":eight:"),
    NINE  (9,  ":nine:"),
    TEN   (10, ":ten:"),
    JACK  (10, ":regional_indicator_j:"),
    QUEEN (10, ":regional_indicator_q"),
    KING  (10, ":regional_indicator_k");

    private int value;
    private String emojiStr;

    CardValue(int value, String emojiStr) {
        this.emojiStr = emojiStr;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getEmojiStr() {
        return this.emojiStr;
    }



}
