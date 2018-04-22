package com.anderbot.bot.command;

import java.util.Arrays;
import java.util.List;

/**
 * A generic command class with some useful defaults.
 */
public abstract class AbstractCommand implements Command {

    private List<String> identifiers;

    public AbstractCommand(String... identifiers) {
        this.identifiers = Arrays.asList(identifiers);
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }
}
