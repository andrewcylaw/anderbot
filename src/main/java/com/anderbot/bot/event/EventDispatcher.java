package com.anderbot.bot.event;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Verifies that the user "sub-command" is valid.
 * (eg, for "dump add @user :emoji:", checks that "add" is a valid command)
 */
@Component
public class EventDispatcher {
    
    private Map<String, CommandEvent> commandEventMap;
    
    public EventDispatcher(Map<String, CommandEvent> commandEventMap) {
        this.commandEventMap = commandEventMap;
    }

    public CommandEvent getCommandEvent(String identifier) {
        return this.commandEventMap.get(identifier);
    }

    // Individual events for each command
    public static class DumpAddEvent   implements CommandEvent {}
    public static class DumpClearEvent implements CommandEvent {}
    public static class DumpStartEvent implements CommandEvent {}
    public static class DumpStopEvent  implements CommandEvent {}
    public static class DumpCheckEvent implements CommandEvent {}
    
    public static class AudioPlayEvent implements CommandEvent {}
    public static class AudioQueueEvent implements CommandEvent {}
    public static class AudioSearchEvent implements CommandEvent {}
    public static class AudioDesignateChannelEvent implements CommandEvent {}

}
