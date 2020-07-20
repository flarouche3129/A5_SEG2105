/**
 * This class let's the user check if the Bot is currently listening for events, and if so the ping with the user and the ping with the websocket.
 * Copyright 2020 Félix Larouche <flaro058@uottawa.ca>
 */

package com.larouche.felix.billythemusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.time.temporal.ChronoUnit;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */

public class PingCommand extends Command {

    public PingCommand() {
        this.name = "ping";
        this.help = "Helper method to see if the bot is responding to commands.";
        this.guildOnly = false;
    }


    @Override
    protected void execute(CommandEvent event) {
        event.reply("Pong!", m -> {
                    long pingTime = event.getMessage().getTimeCreated().until(m.getTimeCreated(), ChronoUnit.MILLIS);
                    m.editMessage("Ping: " + pingTime + "ms" + "\nWebsocket: " + event.getJDA().getGatewayPing() + "ms").queue();
                }
        );
    }
}
