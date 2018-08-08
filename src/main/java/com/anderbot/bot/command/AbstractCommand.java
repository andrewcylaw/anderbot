package com.anderbot.bot.command;

import com.anderbot.bot.event.CommandEvent;
import com.anderbot.bot.event.EventDispatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * A generic command class with some useful defaults.
 */
public abstract class AbstractCommand implements Command {

    private EventDispatcher eventDispatcher;
    private List<String> identifiers;

    public AbstractCommand(String... identifiers) {
        this.identifiers = Arrays.asList(identifiers);
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }
    
    public CommandEvent getCommandEvent(String command) {
        return this.eventDispatcher.getCommandEvent(this.identifiers.get(0) + command);
    }
    
    @Autowired
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }
}
