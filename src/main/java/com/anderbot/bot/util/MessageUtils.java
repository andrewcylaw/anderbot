package com.anderbot.bot.util;

import sx.blah.discord.handle.obj.IMessage;

import java.util.Arrays;
import java.util.List;

/**
 * Utilities for parsing messages.
 *
 * Created by andrew.law on 3/16/2018.
 */
public class MessageUtils {

    private static final String ARG_DELIMITER = " ";
    private static final String BOT_PREFIX = ">";

    public static boolean checkValidCommand(IMessage message) {
        return message.getContent().startsWith(BOT_PREFIX);
    }

    public static String getCommandIdentifier(IMessage message) {
        String command = getMessageTokens(message).get(0);

        return command.substring(1, command.length());
    }

    public static List<String> getArgs(IMessage message) {
        List<String> args = getMessageTokens(message);

        return args.subList(1, args.size());
    }

    private static List<String> getMessageTokens(IMessage message) {
        return Arrays.asList(message.getContent().split(ARG_DELIMITER));
    }

}
