package com.anderbot.bot.command.dump;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.util.BotUtils;
import com.anderbot.bot.util.ChatUtils;
import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.util.MessageUtils;
import com.vdurmont.emoji.EmojiManager;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * "Dumps" on a specific user by reacting to every message that they post.
 *
 * See {@link com.anderbot.bot.message.help.DumpHelp} for syntax and usage.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class DumpCommand extends AbstractCommand implements Command {

    private Map<IUser, Set<ReactionEmoji>> dumpEmoji; // Emojis to dump with (anderbot can only react with emoji once)
    private Map<IUser, DumpStatus> dumpStatus; // Users being dumped on

    public DumpCommand(String identifier) {
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
            return CommandResponseCode.INVALID_COMMAND;
        }

        IUser targetUser = ChatUtils.getUserFromMention(guild, args.get(1));
        if(targetUser == null) {
            return CommandResponseCode.INVALID_USER;
        }

        switch(args.get(0).toLowerCase()) {
            case "add":
                if(args.size() < 3) {
                    return CommandResponseCode.INVALID_COMMAND;
                } else if (!EmojiManager.isEmoji(args.get(2))) {
                    return CommandResponseCode.INVALID_EMOJI;
                }
                this.dumpAdd(targetUser, ReactionEmoji.of(args.get(2)));
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
                return CommandResponseCode.INVALID_COMMAND;
        }

        return CommandResponseCode.OK;
    }

    // Dumps on the user for every message that they send
    public CommandResponseCode dump(MessageReceivedEvent event) {
        IUser user = event.getAuthor();

        if(dumpEmoji.get(user) != null && dumpStatus.get(user) != null && dumpStatus.get(user).equals(DumpStatus.STARTED)) {
            dumpEmoji.get(user).forEach(em -> BotUtils.reactToMessage(event.getMessage(), em));
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
        if(dumpEmoji.get(user) != null && !dumpEmoji.get(user).isEmpty()) {
            String emojis = dumpEmoji.get(user)
                    .stream()
                    .map(ReactionEmoji::getName)
                    .collect(Collectors.joining());

            BotUtils.sendMessage(event.getChannel(), String.format("User [%s] is being dumped on with the following: %s", user.getName(), emojis));
        } else {
            BotUtils.sendMessage(event.getChannel(), String.format("User [%s] is not being dumped on by any emojis at the moment. Why not add some? :clap:", user.getName()));
        }
    }

}
