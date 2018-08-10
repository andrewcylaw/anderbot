package com.anderbot.bot.command.audio;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.command.audio.audiocomponents.DefaultAudioLoadResultHandler;
import com.anderbot.bot.command.audio.audiocomponents.GuildMusicManager;
import com.anderbot.bot.util.CommandResponseCode;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import sx.blah.discord.api.events.Event;

import java.util.HashMap;
import java.util.Map;

public class PlayCommand extends AbstractCommand implements Command{

    private AudioPlayerManager audioManager;
    private Map<Long, GuildMusicManager> guildMusicManagers; // Mapped to guild id

    public PlayCommand(String... identifiers) {
        super(identifiers);
        guildMusicManagers = new HashMap<>();
        audioManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(audioManager);
        AudioSourceManagers.registerLocalSource(audioManager);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        return play(event);
    }
    
    public CommandResponseCode play(Event event) {
        super.parseMessageReceivedEvent(event);

        if(getArgs().isEmpty()) {
            return CommandResponseCode.MISSING_TRACK;
        }

        // One player per guild
        GuildMusicManager manager = guildMusicManagers.computeIfAbsent(getGuild().getLongID(), v -> new GuildMusicManager(audioManager));
        getGuild().getAudioManager().setAudioProvider(manager.getAudioProvider());
        String trackUrl = getArgs().get(0);

        audioManager.loadItemOrdered(manager, trackUrl, new DefaultAudioLoadResultHandler(getChannel(), manager, trackUrl));


        return CommandResponseCode.OK;
    }

}
