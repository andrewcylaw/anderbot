package com.anderbot.bot.command.audio.audiocomponents;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import sx.blah.discord.handle.audio.AudioEncodingType;
import sx.blah.discord.handle.audio.IAudioProvider;

/**
 * Class originally written by sedmelluq as part of the lavaplayer demo.
 *
 * Created by andrew.law on 8/8/2018.
 */
public class AudioProvider implements IAudioProvider {
    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    /**
     * @param audioPlayer Audio player to wrap.
     */
    public AudioProvider(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public boolean isReady() {
        if (lastFrame == null) {
            lastFrame = audioPlayer.provide();
        }

        return lastFrame != null;
    }

    @Override
    public byte[] provide() {
        if (lastFrame == null) {
            lastFrame = audioPlayer.provide();
        }

        byte[] data = lastFrame != null ? lastFrame.getData() : null;
        lastFrame = null;

        return data;
    }

    @Override
    public int getChannels() {
        return 2;
    }

    @Override
    public AudioEncodingType getAudioEncodingType() {
        return AudioEncodingType.OPUS;
    }
}