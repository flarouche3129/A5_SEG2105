package com.larouche.felix.billythemusicbot.models;

import lombok.Getter;
import lombok.Setter;

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
