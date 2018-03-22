package com.anderbot.bot.util;

import com.vdurmont.emoji.EmojiManager;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

/**
 * Utilities for handling anything specific to chat messages/reactions/etc.
 *
 * Created by andrew.law on 3/16/2018.
 */
public class ChatUtils {

    public static IUser getUserFromMention(IGuild guild, String userMention) {
        Long userId = getUserIdFromMention(userMention);

        return userId == null ? null :guild.getUserByID(userId);
    }

    // Note: UserId is of form <@123...>
    private static Long getUserIdFromMention(String userId) throws NumberFormatException {
        try {
            return Long.parseLong(userId.substring(2, userId.length() - 1));
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
