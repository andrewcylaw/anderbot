package com.anderbot.bot.command.audio.audiocomponents;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.PriorityQueue;

/**
 * A "trackScheduler" that wraps a queue of tracks.
 */
public class TrackScheduler extends AudioEventAdapter {

    private PriorityQueue<AudioTrack> trackQueue;
    private final AudioPlayer player;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        trackQueue = new PriorityQueue<>();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(endReason.mayStartNext) {
            nextTrack();
        }
    }

    public void nextTrack() {
        player.startTrack(trackQueue.poll(), false);
        // TODO - print a nice message
    }

    public void queueTrack(AudioTrack track) {
        if (!player.startTrack(track, true)) {
            trackQueue.offer(track);
        }
    }

}
