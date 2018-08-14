package com.anderbot.bot.command.audio;

import com.anderbot.bot.command.audio.audiocomponents.GuildMusicManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Simplified "dao" for audio related commands to share the same persisted data.
 * 
 * Created by andrew.law on 8/10/2018.
 */
@Component
public class AudioDao {

    private Map<Long, GuildMusicManager> guildMusicManagers; // Mapped to guild id
    
    public AudioDao() {
        this.guildMusicManagers = new HashMap<>();
    }
    
    // Adds and returns a guild music manager
    public GuildMusicManager addGuildMusicManager(Long guildId, AudioPlayerManager audioPlayerManager) {
        return guildMusicManagers.computeIfAbsent(guildId, v -> new GuildMusicManager(audioPlayerManager));
    }
    
    public GuildMusicManager getGuildMusicManager(Long guildId) {
        return guildMusicManagers.get(guildId);
    }
    
}
