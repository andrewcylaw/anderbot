package com.anderbot.bot.event;

public interface EventDispatcher {

    CommandEvent getEvent(String identifier);

}
