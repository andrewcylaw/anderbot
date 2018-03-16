package com.anderbot.bot;


import com.anderbot.bot.util.BotUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * Experimental file.
 *
 * Created by andrew.law on 3/15/2018.
 */
public class Main {

    private static final String contextFile = "command-context.xml";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextFile);

        IDiscordClient sampleClient = BotUtil.getClient("dummy token");
        IListener<MessageReceivedEvent> listener = new MessageEventListener();

        sampleClient.getDispatcher().registerListener(listener);
        sampleClient.login();

        // -----

    }

}
