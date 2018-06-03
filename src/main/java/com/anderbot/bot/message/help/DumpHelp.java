package com.anderbot.bot.message.help;

import org.springframework.stereotype.Component;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

@Component
public class DumpHelp implements EmbeddedHelp {

    @Override
    public EmbedObject getEmbeddedHelp() {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.withTitle("`>command`")
                .withDescription("anderbot \"dumps\" on users by reacting to every message that certain users send messages in chat. Please use the @user annotation when adding a user.")
                .withFooterText("Examples: >command add @anderbot :key: | >command stop @anderbot")
                .appendField("\u200B\n--", "\u200B", false)
                .appendField("`add <user> <emoji>`", "Adds the given emoji to the list of emoji to command on the user with.\n\u200B", false)
                .appendField("clear <user>", "Removes all the emojis that are currently being dumped on the given user.\n\u200B", false)
                .appendField("start | stop <user>", "Tells anderbot to either start or stop dumping on the given user." +
                        "Does not add or remove any of the emojis that are being dumped on the given user.\n\u200B", false)
                .appendField("`check <user>`", "Prints all of the emojis that are being dumped on right now for a given user.", false)
                .appendField("\u200B", "--", false);

        return embedBuilder.build();
    }

}
