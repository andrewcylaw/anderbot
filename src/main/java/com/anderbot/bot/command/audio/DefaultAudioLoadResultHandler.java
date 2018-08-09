package com.anderbot.bot.command.audio;

import com.anderbot.bot.util.BotUtils;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import sx.blah.discord.handle.obj.IChannel;

/**
 * Created by andrew.law on 8/8/2018.
 */
public class DefaultAudioLoadResultHandler implements AudioLoadResultHandler {

    private IChannel channel;
    private GuildMusicManager guildMusicManager;
    private String trackUrl;

    public DefaultAudioLoadResultHandler(IChannel channel, GuildMusicManager guildMusicManager, String trackUrl) {
        this.channel = channel;
        this.guildMusicManager = guildMusicManager;
        this.trackUrl = trackUrl;
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        BotUtils.sendMessage(channel, "Adding to queue " + track.getInfo().title);
        playTrack(channel, track);        
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        AudioTrack firstTrack = playlist.getSelectedTrack();

        if (firstTrack == null) {
            firstTrack = playlist.getTracks().get(0);
        }

        BotUtils.sendMessage(channel, "Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")");
        playTrack(channel, firstTrack);
    }

    @Override
    public void noMatches() {
        BotUtils.sendMessage(channel, "Nothing found by " + trackUrl);
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        BotUtils.sendMessage(channel, "Could not play: " + exception.getMessage());
    }
    
    private void playTrack(IChannel channel, AudioTrack track) {
        BotUtils.connectToVoiceChannel(channel.getGuild());
        guildMusicManager.getScheduler().queueTrack(track);
    }

}
