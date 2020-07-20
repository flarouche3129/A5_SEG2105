/**
 * This class let's the user add songs to be played to the bot.
 */

package com.larouche.felix.billythemusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.larouche.felix.billythemusicbot.AudioPlayerSendHandler;
import com.larouche.felix.billythemusicbot.models.YoutubeVideo;
import com.larouche.felix.billythemusicbot.services.QueuedTrack;
import com.larouche.felix.billythemusicbot.services.YouTubeSearch;
import com.sedmelluq.discord.lavaplayer.player.*;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.managers.AudioManager;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */

public class PlayCommand extends Command {

    public PlayCommand() {
        this.name = "play";
        this.help = "Plays the song requested in your discord channel";
        this.arguments = "Song to be played";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {

        //Need to be in a channel to play a song
        if (commandEvent.getMember().getVoiceState().getChannel() == null) {
            commandEvent.reply("Must be in a voice channel when issuing play");
            return;
        }

        //Statement to allow the user to unpause the current song by typing !play again 
        if (commandEvent.getArgs().isEmpty() && commandEvent.getMessage().getAttachments().isEmpty()){
            AudioPlayerSendHandler handler = (AudioPlayerSendHandler)commandEvent.getGuild().getAudioManager().getSendingHandler();
            if (handler.getPlayer().getPlayingTrack() !=null && handler.getPlayer().isPaused()){
                handler.getPlayer().setPaused(false);
                commandEvent.replySuccess("Resumed : " + handler.getPlayer().getPlayingTrack().getInfo().title);
                return;
            }
        }

        //creates everything needed to make a song play in the discord
        YoutubeVideo video = YouTubeSearch.search(commandEvent.getArgs());
        commandEvent.reply("Title: " + video.getTitle() + ", Link: https://www.youtube.com/watch?v=" + video.getVideoID());

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioPlayer player = playerManager.createPlayer();
        AudioPlayerSendHandler trackHandler = new AudioPlayerSendHandler(player);
        player.addListener(trackHandler);


        AudioManager manager = commandEvent.getGuild().getAudioManager();
        manager.setSendingHandler(new AudioPlayerSendHandler(player));
        manager.openAudioConnection(commandEvent.getMember().getVoiceState().getChannel());



        playerManager.loadItem(video.getVideoID(), new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                trackHandler.addTrack(new QueuedTrack(audioTrack));
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                //maybe implement later, for now no playlist or anything
            }

            @Override
            public void noMatches() {
                commandEvent.reply("No matches found for this request.");
            }

            @Override
            public void loadFailed(FriendlyException e) {
                commandEvent.reply("OOPS");
            }
        });
    }
}
