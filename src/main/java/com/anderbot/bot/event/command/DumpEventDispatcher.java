package com.anderbot.bot.event.command;

import com.anderbot.bot.event.CommandEvent;
import com.anderbot.bot.event.EventDispatcher;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DumpEventDispatcher implements EventDispatcher {

    private HashMap<String, CommandEvent> dumpEventMap;

    public DumpEventDispatcher(HashMap<String, CommandEvent> dumpEventMap) {
        this.dumpEventMap = dumpEventMap;
    }

    public CommandEvent getEvent(String identifier) {
        return dumpEventMap.get(identifier);
    }

    public static class DumpAddEvent   implements CommandEvent {}
    public static class DumpClearEvent implements CommandEvent {}
    public static class DumpStartEvent implements CommandEvent {}
    public static class DumpStopEvent  implements CommandEvent {}
    public static class DumpCheckEvent implements CommandEvent {}

}
