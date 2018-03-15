package com.anderbot.bot.Util;

/**
 * Command response codes, which allow the handlers and commands to fail (a bit more) gracefully.
 *
 * Created by andrew.law on 3/15/2018.
 */
public enum CommandResponseCode {
    FAILED_INTERNAL,
    FAILED_INVALID_COMMAND,
    OK
}
