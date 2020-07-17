package com.larouche.felix.billythemusicbot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.larouche.felix.billythemusicbot.commands.PingCommand;
import com.larouche.felix.billythemusicbot.commands.PlayCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {
    private static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private static JDA bot;

    public static void main(String[] args) {

        CommandClientBuilder builder = new CommandClientBuilder();

        // Set your bot's prefix
        builder.setPrefix("!");
        builder.setAlternativePrefix("+");
        builder.setOwnerId("260831732595556354");

        // Add commands
        builder.addCommands(
                new PingCommand(),
                new PlayCommand()
        );

        // Customize per-guild unique settings
        // builder.setGuildSettingsManager(new MyBotsGuildSettingsManager());

        CommandClient commandClient = builder.build();


        try {
            bot = JDABuilder.createDefault(BOT_TOKEN).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        bot.addEventListener(commandClient);


        System.out.println(bot.getSelfUser());
    }
}
