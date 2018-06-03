package com.anderbot.bot.command.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import javafx.scene.media.AudioTrack;

import java.util.PriorityQueue;

/**
 * A "trackScheduler" that wraps a queue of tracks.
 */
public class TrackScheduler extends AudioEventAdapter {

    private PriorityQueue<AudioTrack> trackQueue;

    public TrackScheduler() {
        trackQueue = new PriorityQueue<>();
    }

    @Override
    public void onPlayerPause(AudioPlayer player) {
        super.onPlayerPause(player);
    }

    @Override
    public void onPlayerResume(AudioPlayer player) {
        super.onPlayerResume(player);
    }

    @Override
    public void onTrackStart(AudioPlayer player, com.sedmelluq.discord.lavaplayer.track.AudioTrack track) {
        super.onTrackStart(player, track);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, com.sedmelluq.discord.lavaplayer.track.AudioTrack track, AudioTrackEndReason endReason) {
        super.onTrackEnd(player, track, endReason);
    }

    @Override
    public void onTrackException(AudioPlayer player, com.sedmelluq.discord.lavaplayer.track.AudioTrack track, FriendlyException exception) {
        super.onTrackException(player, track, exception);
    }

    @Override
    public void onTrackStuck(AudioPlayer player, com.sedmelluq.discord.lavaplayer.track.AudioTrack track, long thresholdMs) {
        super.onTrackStuck(player, track, thresholdMs);
    }

    public AudioTrack getNextTrack() {
        return trackQueue.poll();
    }

    public void queueTrack(AudioTrack track) {
        trackQueue.add(track);
    }

}
