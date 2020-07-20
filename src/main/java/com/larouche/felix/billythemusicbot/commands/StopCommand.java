/**
 * This class let's the user cancel everything the bot is doing and make the bot disconnect from the user's channel.
 * Copyright 2020 Félix Larouche <flaro058@uottawa.ca>
 */

package com.larouche.felix.billythemusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.larouche.felix.billythemusicbot.AudioPlayerSendHandler;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */

public class StopCommand extends Command {

    public StopCommand(){
        this.name = "stop";
        this.help = "Stops the current song and leaves the channel.";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {

        AudioPlayerSendHandler handler = (AudioPlayerSendHandler)commandEvent.getGuild().getAudioManager().getSendingHandler();
        handler.stopTrack();
        commandEvent.getGuild().getAudioManager().closeAudioConnection();
        commandEvent.reply(commandEvent.getClient().getSuccess() + "I have now stopped playing the requested song.");
    }
}
