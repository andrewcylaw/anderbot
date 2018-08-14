package com.anderbot.bot.command.audio.audiocomponents;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import sx.blah.discord.handle.obj.IChannel;


/**
 * Base class originally written by sedmelluq as part of the lavaplayer demo.
 * 
 * Created by andrew.law on 8/8/2018.
 */
public class GuildMusicManager {
    
    private IChannel designatedChannel;
    private final AudioPlayer player;
    private final TrackScheduler scheduler;

    public GuildMusicManager(AudioPlayerManager manager) {
        this.player = manager.createPlayer();
        this.scheduler = new TrackScheduler(player);
        this.player.addListener(scheduler);
    }
    
    public IChannel getDesignatedChannel() {
        return designatedChannel;
    }
    
    public void setDesignatedChannel(IChannel designatedChannel) {
        this.designatedChannel = designatedChannel;
    }

    public AudioProvider getAudioProvider() {
        return new AudioProvider(player);
    }

    public AudioPlayer getPlayer() {
        return player;
    }

    public TrackScheduler getScheduler() {
        return scheduler;
    }
}
