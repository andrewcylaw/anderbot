package com.anderbot.bot.util;

/**
 * Command response codes, which allow the handlers and commands to fail (a bit more) gracefully.
 *
 * Created by andrew.law on 3/15/2018.
 */
public enum CommandResponseCode {
    INVALID_COMMAND (":x:**[error]** Invalid command given. Please check that you are using the right number of arguments"),
    INVALID_USER    (":x:**[error]** Invalid user given."),
    INVALID_EMOJI   (":x:**[error]** Invalid emoji given."),
    INVALID_HELP    (":x:**[error]** Invalid command given."),
    INVALID_NUMBER  (":x:**[error]** Invalid number given."),
    NOT_A_COMMAND   (""),
    OK              ("");

    String message;

    CommandResponseCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
