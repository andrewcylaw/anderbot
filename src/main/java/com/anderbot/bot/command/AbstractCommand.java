package com.anderbot.bot.command;

/**
 * A generic command class with some useful defaults.
 */
public abstract class AbstractCommand implements Command {

    private String identifier;

    public AbstractCommand(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

}
