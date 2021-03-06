package com.anderbot.bot.message.help;

import org.springframework.stereotype.Component;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

@Component
public class RollHelp implements EmbeddedHelp {

    @Override
    public EmbedObject getEmbeddedHelp() {
        EmbedBuilder builder = new EmbedBuilder();

        builder.withTitle("`>roll`")
                .withDescription("Can never go wrong with roll, right?")
                .withFooterText("Examples: >roll 10 5 | >roll 600")
                .appendField("\u200B\n--", "\u200B", false)
                .appendField("`roll [size of roll] [number of rolls]`", "Rolls a roll with the given size and number of times. " +
                        "If size is not given, rolls a 6 sided die. If number of rolls is not given, rolls it once.", false)
                .appendField("\u200B", "--", false);

        return builder.build();
    }

}
