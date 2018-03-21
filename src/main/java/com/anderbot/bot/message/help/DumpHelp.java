package com.anderbot.bot.message.help;

import org.springframework.stereotype.Component;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

@Component
public class DumpHelp implements EmbeddedHelp {

    @Override
    public EmbedObject getEmbeddedHelp() {
        EmbedBuilder builder = new EmbedBuilder();

        builder.withTitle("`>dump`");
        builder.withDescription("anderbot \"dumps\" on users by reacting to every message whenever a user that is being dumped on sends a message in chat. Please use the @user annotation when adding a user.");
        builder.withFooterText("Examples: >dump add @anderbot :key: | >dump stop @anderbot");

        // Descriptions of each option
        builder.appendField("`add <user> <emoji>`", "```Adds the given emoji to the list of emoji to dump on the user with. " +
                "Starts dumping on the user if they are not already being dumped on.```", false);
        builder.appendField("`clear <user>`", "Removes all the emojis that are currently being dumped on the given user.```", false);
        builder.appendField("`start | stop <user>`", "```Tells anderbot to either start or stop dumping on the given user." +
                "Does not add or remove any of the emojis that are being dumped on the given user.```", false);
        builder.appendField("`check <user>`", "```Prints all of the emojis that are being dumped on right now for a given user.```", false);

        return builder.build();
    }

}
