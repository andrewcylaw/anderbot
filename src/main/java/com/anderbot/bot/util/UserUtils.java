package com.anderbot.bot.util;

import sx.blah.discord.handle.impl.obj.Channel;
import sx.blah.discord.handle.impl.obj.Guild;
import sx.blah.discord.handle.impl.obj.User;

/**
 * Utilities for handling users.
 *
 * Created by andrew.law on 3/16/2018.
 */
public class UserUtils {

    private static User getUser(Guild guild, String userId) {
//         return guild.getUsers()

        return null;
    }

    // Note: UserId is of form <@123...>
    private static Long getUserId(String userId) {
        return Long.parseLong(userId.substring(2, userId.length() - 1));
    }

}
