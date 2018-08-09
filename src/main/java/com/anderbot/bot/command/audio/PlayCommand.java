package com.anderbot.bot.command.audio;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.util.MessageUtils;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayCommand extends AbstractCommand implements Command{

    private AudioPlayerManager audioManager;
    private Map<IGuild, GuildMusicManager> guildMusicManagers;

    public PlayCommand(String... identifiers) {
        super(identifiers);
        guildMusicManagers = new HashMap<>();
        audioManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(audioManager);
        AudioSourceManagers.registerLocalSource(audioManager);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
        IMessage message = messageReceivedEvent.getMessage();
        IGuild guild = message.getGuild();
        IChannel channel = messageReceivedEvent.getChannel();
        List<String> args = MessageUtils.getArgs(message);

        // One player per guild
        GuildMusicManager manager = guildMusicManagers.putIfAbsent(guild, new GuildMusicManager(audioManager));
        guild.getAudioManager().setAudioProvider(manager.getAudioProvider());
        String trackUrl = args.get(0);

        audioManager.loadItemOrdered(manager, trackUrl, new DefaultAudioLoadResultHandler(channel, manager, trackUrl));


        return CommandResponseCode.OK;
    }
}
