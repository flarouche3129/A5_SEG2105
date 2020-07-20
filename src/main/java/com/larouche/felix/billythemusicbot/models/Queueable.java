/**
 * This interface gives every QueuedTrack a identifier, aka who queued the song.
 * Copyright 2020 Félix Larouche <flaro058@uottawa.ca>
 */
package com.larouche.felix.billythemusicbot.models;
/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */

public interface Queueable {

    public long getIdentifier();
}
