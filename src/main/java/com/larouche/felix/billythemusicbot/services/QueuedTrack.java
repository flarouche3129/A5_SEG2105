/**
 * This class is used by the AudioPlayerSendHandler to add tracks to be played.
 */

package com.larouche.felix.billythemusicbot.services;

import com.larouche.felix.billythemusicbot.models.Queueable;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */

public class QueuedTrack implements Queueable {
    private final AudioTrack track;
    public QueuedTrack(AudioTrack track){
        this.track = track;

    }

    public QueuedTrack(AudioTrack track, long owner){
        this.track = track;
        this.track.setUserData(owner);
    }


    @Override
    public long getIdentifier() {
        return track.getUserData(Long.class);
    }

    public AudioTrack getTrack(){
        return track;
    }

}
