/**
 * This class handles the song that was requested, downloads it and plays it in the discord channel.
 * Copyright 2020 Félix Larouche <flaro058@uottawa.ca>
 */


package com.larouche.felix.billythemusicbot;

import com.larouche.felix.billythemusicbot.services.QueuedTrack;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.managers.AudioManager;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */


public class AudioPlayerSendHandler extends AudioEventAdapter implements AudioSendHandler {

    private final AudioPlayer audioPlayer;
    private AudioManager manager;
    private AudioFrame lastFrame;
    private final List<AudioTrack> queue = new LinkedList<>();



    public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public AudioPlayer getPlayer(){
        return audioPlayer;
    }

    public void addTrack(QueuedTrack qtrack){
        if (audioPlayer.getPlayingTrack() == null){
            audioPlayer.playTrack(qtrack.getTrack());
             return;
        }
        else{
            queue.add(qtrack.getTrack());
        }

    }

    public List<AudioTrack> getQueue(){
        return queue;
    }

    public void stopTrack(){
        audioPlayer.stopTrack();
    }


    @Override
    public void onPlayerPause(AudioPlayer player){
    }
    @Override
    public void onPlayerResume(AudioPlayer player){

    }
    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track){
    }
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason){
        if (queue.isEmpty()){
            manager.closeAudioConnection();
        }
    }
    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException e){
        //will throw and exception if an already playing track crasged or whatever
    }
    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long tresholdMs){
        //The track couldn't provide any audio
    }

    @Override
    public boolean canProvide() {
        lastFrame = audioPlayer.provide();
        return lastFrame != null;
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        return ByteBuffer.wrap(lastFrame.getData());
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}
