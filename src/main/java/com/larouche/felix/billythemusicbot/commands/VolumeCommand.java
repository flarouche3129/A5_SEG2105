/**
 * This class let's the user change and see the current volume of the song.
 * Copyright 2020 Félix Larouche <flaro058@uottawa.ca>
 */

package com.larouche.felix.billythemusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.larouche.felix.billythemusicbot.AudioPlayerSendHandler;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */

public class VolumeCommand extends Command {

    public VolumeCommand(){
        this.name = "volume";
        this.help = "Can be used to see the current volume or set a volume.";
        this.arguments = "Volume to be set at.";
    }


    @Override
    protected void execute(CommandEvent commandEvent) {
        AudioPlayerSendHandler handler = (AudioPlayerSendHandler)commandEvent.getGuild().getAudioManager().getSendingHandler();
        int volume = handler.getPlayer().getVolume();
        if (commandEvent.getArgs().isEmpty()){
            commandEvent.reply("Current volume is : " + volume );
        }
        else{
            int newVolume;
            try{
                newVolume = Integer.parseInt(commandEvent.getArgs());
            }catch (NumberFormatException e){
                newVolume = -1;
            }
            if (newVolume < 0 || newVolume > 150){
                commandEvent.reply(commandEvent.getClient().getError() + "Volume must be between 0 and 150 !");
            }
            else{
                handler.getPlayer().setVolume(newVolume);
                commandEvent.reply("The volume has been changed from : " + volume + " to : " + newVolume + " !");
            }
        }
    }
}
