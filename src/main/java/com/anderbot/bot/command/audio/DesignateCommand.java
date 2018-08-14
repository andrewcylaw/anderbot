package com.anderbot.bot.command.audio;

import com.anderbot.bot.command.AbstractCommand;
import com.anderbot.bot.command.Command;
import com.anderbot.bot.command.audio.audiocomponents.DefaultAudioLoadResultHandler;
import com.anderbot.bot.command.audio.audiocomponents.GuildMusicManager;
import com.anderbot.bot.util.CommandResponseCode;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import org.springframework.beans.factory.annotation.Autowired;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IGuild;

import java.util.HashMap;
import java.util.Map;

/**
 * "Designates" a text channel for convenience of parsing audio commands.
 * After a text channel is "designated", the >___ commands are not needed anymore.
 * 
 * The goal is to have some basic logic that parses tracks automatically, show queues etc,
 * without needing to type >___ all the time.
 */
public class DesignateCommand extends AbstractCommand implements Command {

    private AudioDao audioDao;
    
    public DesignateCommand(String... identifiers) {
        super(identifiers);
    }

    @Override
    public CommandResponseCode handle(Event event) {
        super.parseMessageReceivedEvent(event);
        
        if(getArgs().isEmpty()) {
            return CommandResponseCode.MISSING_CHANNEL;
        }

        return CommandResponseCode.OK;
    }
    
    @Autowired
    public void setAudioDao(AudioDao audioDao) {
        this.audioDao = audioDao;
    }
}
