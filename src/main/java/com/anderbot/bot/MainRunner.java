package com.anderbot.bot;


import com.anderbot.bot.util.BotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.IDiscordClient;

/**
 * Experimental file.
 *
 * Created by andrew.law on 3/15/2018.
 */
@Component
public class MainRunner {

    private static final String CONTEXT = "/context.xml";

    // TODO - parse this via command args instead
    private static final String TOKEN = "";
    private MessageEventListener messageEventListener;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONTEXT);
        MainRunner mainRunner = applicationContext.getBean(MainRunner.class);

        mainRunner.initBot(TOKEN);
    }

    private void initBot(String token) {
        IDiscordClient client = BotUtils.getClient(token);

        client.getDispatcher().registerListener(messageEventListener);
        client.login();
    }

    @Autowired
    public void setMessageEventListener(MessageEventListener messageEventListener) {
        this.messageEventListener = messageEventListener;
    }

}
