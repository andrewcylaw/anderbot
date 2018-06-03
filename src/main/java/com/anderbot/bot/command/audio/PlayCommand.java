package com.anderbot.bot.command.audio;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.util.CommandResponseCode;
import com.anderbot.bot.util.MessageUtils;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import sx.blah.discord.api.events.Event;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayCommand extends AbstractCommand implements Command{

    private AudioPlayerManager audioManager;
    private Map<IGuild, AudioPlayer> guildAudioPlayers;
    private Map<IGuild, TrackScheduler> guildTrackSchedulers;

    public PlayCommand(String... identifiers) {
        super(identifiers);
        guildAudioPlayers = new HashMap<>();
        guildTrackSchedulers = new HashMap<>();
        audioManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(audioManager);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
        IMessage message = messageReceivedEvent.getMessage();
        IGuild guild = message.getGuild();
        List<String> args = MessageUtils.getArgs(message);

        switch(args.get(0).toLowerCase()) {
            case "play":
                // One player per guild
                AudioPlayer player = guildAudioPlayers.putIfAbsent(guild, audioManager.createPlayer());
                TrackScheduler scheduler = guildTrackSchedulers.putIfAbsent(guild, new TrackScheduler());

                scheduler.queueTrack(null);

        }

        return CommandResponseCode.OK;
    }
}
