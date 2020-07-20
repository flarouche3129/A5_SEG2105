/**
 * This class creates a YouTubeVideo from the data the YouTubeSearch provides, and makes it more tangible and easy to handle for the other classes.
 */

package com.larouche.felix.billythemusicbot.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */


public class YoutubeVideo {

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    String videoID;

    @Getter
    @Setter
    String duration;

    public YoutubeVideo(String title, String videoID, String duration) {
        this.title = title;
        this.videoID = videoID;
        this.duration = duration;
    }

    public YoutubeVideo(String title, String videoID) {
        this.title = title;
        this.videoID = videoID;
    }
}
