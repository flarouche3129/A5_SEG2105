package com.larouche.felix.billythemusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.larouche.felix.billythemusicbot.AudioPlayerSendHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;

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
