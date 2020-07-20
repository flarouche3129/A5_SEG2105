/**
 * This class creates an instance of a bot using the JDAbuilder.
 */

package com.larouche.felix.billythemusicbot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.larouche.felix.billythemusicbot.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */


public class Bot {
    private static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private static JDA bot;

    public static void main(String[] args) {

        CommandClientBuilder builder = new CommandClientBuilder();

        // Set the bot's prefixes
        builder.setPrefix("!");
        builder.setAlternativePrefix("+");
        builder.setOwnerId("260831732595556354");

        // Add commands
        builder.addCommands(
                new PingCommand(),
                new PlayCommand(),
                new PauseCommand(),
                new StopCommand(),
                new VolumeCommand()
        );

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
