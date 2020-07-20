package com.larouche.felix.billythemusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.larouche.felix.billythemusicbot.AudioPlayerSendHandler;

public class PauseCommand extends Command {

    public PauseCommand(){
        this.name = "pause";
        this.help = "Pauses the current song.";

    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        AudioPlayerSendHandler handler = (AudioPlayerSendHandler)commandEvent.getGuild().getAudioManager().getSendingHandler();
        if (handler.getPlayer().isPaused()){
            commandEvent.reply("This player is already paused! Use " + commandEvent.getClient().getPrefix() + "play to unpause!");
            return;
        }
        handler.getPlayer().setPaused(true);
        commandEvent.replySuccess("Paused " + handler.getPlayer().getPlayingTrack().getInfo().title + ", type " + commandEvent.getClient().getPrefix() + "play to unpause!");
    }

}