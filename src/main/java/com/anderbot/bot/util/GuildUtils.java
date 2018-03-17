package com.anderbot.bot.util;

import sx.blah.discord.handle.impl.obj.Message;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IEmoji;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Utilities for handling guild operations.
 *
 * Created by andrew.law on 3/16/2018.
 */
public class GuildUtils {

    public static IUser getUserFromMention(IGuild guild, String userId) {
        return guild.getUserByID(getUserIdFromMention(userId));
    }

    // Note: UserId is of form <@123...>
    private static long getUserIdFromMention(String userId) {
        return Long.parseLong(userId.substring(2, userId.length() - 1));
    }

}
