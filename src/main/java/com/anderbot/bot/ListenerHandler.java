package com.anderbot.bot;

import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.events.IListener;

import java.util.List;

/**
 * Spring IoC for creating listeners.
 */
@Component
public class ListenerHandler {

    private List<IListener<Event>> listeners;

    private ListenerHandler(List<IListener<Event>> events) {
        this.listeners = events;
    }

    public List<IListener<Event>> getListeners() {
        return this.listeners;
    }
}
