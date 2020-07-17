package com.larouche.felix.billythemusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.time.temporal.ChronoUnit;

public class PingCommand extends Command {

    public PingCommand() {
        this.name = "ping";
        this.help = "Does the ping ping boi uWu";
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
