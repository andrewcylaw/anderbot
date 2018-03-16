package com.anderbot.bot.command.dumpon;

import com.anderbot.bot.command.Command;
import com.anderbot.bot.util.BotUtils;
import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.util.MessageUtils;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * "Dumps" on a specific user by reacting to every message that they post.
 * (Does not react to other commands to this bot)
 *
 * Syntax: dumpOn <user> <emoji>
 *
 * Created by andrew.law on 3/15/2018.
 */
public class DumpOnCommand implements Command {

    private String identifier;
    private Map<IUser, ReactionEmoji> dumpWithEmojis; // Emoji to dump with
    private Map<IUser, Boolean> dumpOnUsers; // Users being dumped on

    // TODO - dump on users by their id
    protected DumpOnCommand(String identifier) {
        this.identifier = identifier;
        this.dumpWithEmojis = new HashMap<>();
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
        IMessage message = messageReceivedEvent.getMessage();
        List<String> args = MessageUtils.getArgs(message);

        if(args.size() < 2) {
            return CommandResponseCode.FAILED_INVALID_COMMAND;
        }

        String username = args.get(0);
        ReactionEmoji emoji = ReactionEmoji.of(args.get(1));
        List<IUser> targetUsers = messageReceivedEvent.getGuild().getUsersByName(username);

        if(targetUsers.size() > 1) {
            BotUtils.sendMessage(messageReceivedEvent.getChannel(), "[error] More than one user with name: " + username);
            return CommandResponseCode.FAILED_INVALID_COMMAND;
        }

        this.setDump(targetUsers.get(0), true);
        this.dumpWithEmojis.put(targetUsers.get(0), emoji);

        return CommandResponseCode.OK;
    }

    // Sets whether or not the given user is dumped on
    private void setDump(IUser user, boolean dumpOn) {
        if(dumpOnUsers.get(user) != null) {
            dumpOnUsers.put(user, dumpOn);
        }
    }

    // Dumps on the user for every message that they send
    @EventSubscriber
    private void dump(MessageReceivedEvent event) {

//        if(dumpOnUsers.get(user)) {
//            dumpWithEmojis.putIfAbsent(targetUsers.get(0), emoji);
//            BotUtils.reactToMessage(message, dumpWithEmojis.get(targetUsers.get(0)));
//        }
    }

}
