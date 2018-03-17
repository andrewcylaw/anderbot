package com.anderbot.bot.command.dump;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.util.BotUtils;
import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.util.GuildUtils;
import com.anderbot.bot.util.MessageUtils;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import java.util.*;

/**
 * "Dumps" on a specific user by reacting to every message that they post.
 * (Does not react to other commands to this bot)
 *
 * Syntax: dump [start|stop|clear|check] user
 *              [add] user emoji
 *
 * Created by andrew.law on 3/15/2018.
 */
public class DumpCommand extends AbstractCommand implements Command {

    private Map<IUser, Set<ReactionEmoji>> dumpEmoji; // Emojis to dump with (anderbot can only react with emoji once)
    private Map<IUser, DumpStatus> dumpStatus; // Users being dumped on


    protected DumpCommand(String identifier) {
        super(identifier);
        this.dumpEmoji = new HashMap<>();
        this.dumpStatus = new HashMap<>();
    }

    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
        IMessage message = messageReceivedEvent.getMessage();
        IGuild guild = message.getGuild();
        List<String> args = MessageUtils.getArgs(message);

        if(args.size() < 2) {
            return CommandResponseCode.FAILED_INVALID_COMMAND;
        }

        IUser targetUser = GuildUtils.getUserFromMention(guild, args.get(1));

        if(targetUser == null) {
            return CommandResponseCode.FAILED_INVALID_COMMAND;
        }

        switch(args.get(0).toLowerCase()) {
            case "add":
                if(args.size() < 3) {
                    return CommandResponseCode.FAILED_INVALID_COMMAND;
                }
                this.dumpAdd(targetUser, ReactionEmoji.of(args.get(2)));
                this.dump(messageReceivedEvent);
                break;
            case "clear":
                this.dumpClear(targetUser);
                break;
            case "start":
                this.dumpStart(targetUser);
                break;
            case "stop":
                this.dumpStop(targetUser);
                break;
            case "check":
                this.dumpCheck(targetUser, messageReceivedEvent);
                break;
            default:
                return CommandResponseCode.FAILED_INVALID_COMMAND;
        }

        return CommandResponseCode.OK;
    }

    // Dumps on the user for every message that they send
    public CommandResponseCode dump(MessageReceivedEvent event) {
        IUser user = event.getAuthor();

        if(dumpStatus.get(user) != null && dumpStatus.get(user).equals(DumpStatus.STARTED)) {
            dumpEmoji.get(user).forEach(em -> RequestBuffer.request(() -> {
                try {
                    event.getMessage().addReaction(em);
                } catch (DiscordException e) {
                    // Remove invalid emojis
                    BotUtils.sendMessage(event.getChannel(), "[error] Invalid emoji provided");
                    dumpEmoji.get(user).remove(em);
                    e.printStackTrace();
                }
            }));
        }



        return CommandResponseCode.OK;
    }

    private void dumpAdd(IUser user, ReactionEmoji emoji) {
        dumpEmoji.computeIfAbsent(user, u -> new HashSet<>()).add(emoji);
        this.dumpStart(user);
    }

    private void dumpClear(IUser user) {
        dumpEmoji.put(user, new HashSet<>());
    }

    private void dumpStart(IUser user) {
        dumpStatus.put(user, DumpStatus.STARTED);
    }

    private void dumpStop(IUser user) {
        dumpStatus.put(user, DumpStatus.STOPPED);
    }

    private void dumpCheck(IUser user, MessageReceivedEvent event) {
        StringBuilder collectEmoji = new StringBuilder();
        dumpEmoji.get(user).forEach(e -> collectEmoji.append(e.getName()));

        BotUtils.sendMessage(event.getChannel(), String.format("User [%s] is being dumped on with the following: %s", user.getName(), collectEmoji.toString()));
    }

}
