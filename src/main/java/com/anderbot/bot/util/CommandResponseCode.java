package com.anderbot.bot.util;

/**
 * Command response codes, which allow the handlers and commands to fail (a bit more) gracefully.
 *
 * Created by andrew.law on 3/15/2018.
 */
public enum CommandResponseCode {
    FAILED_INVALID_COMMAND ("[error] Invalid command given."),
    OK ("");

    String message;

    CommandResponseCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
