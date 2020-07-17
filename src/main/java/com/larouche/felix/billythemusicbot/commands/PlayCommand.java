package com.larouche.felix.billythemusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.larouche.felix.billythemusicbot.models.YoutubeVideo;
import com.larouche.felix.billythemusicbot.services.YouTubeSearch;

public class PlayCommand extends Command {

    public PlayCommand() {
        this.name = "play";
        this.help = "Test play command, only replies dumb shit for now";
        this.arguments = "Song to be played";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {

        //noinspection ConstantConditions
        if (commandEvent.getMember().getVoiceState().getChannel() == null) {
            commandEvent.reply("Must be in a voice channel when issuing play");
            return;
        }

        YoutubeVideo video = YouTubeSearch.search(commandEvent.getArgs());
        commandEvent.reply("Title: " + video.getTitle() + ", Link: https://www.youtube.com/watch?v=" + video.getVideoID());
    }
}
