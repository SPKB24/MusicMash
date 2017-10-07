package com.sohit.musicmash;

/**
 * Created by sohit on 10/2/17.
 */

public class CustomItem {

    private String video_title = "";
    private String video_channel = "";
    private String video_description = "";
    private String video_id = "";
    private String video_image_url = "";


    public CustomItem(final String _videoTitle, final String _videoChannel, final String _videoId, final String _videoDescription) {
        video_title = _videoTitle;
        video_channel = _videoChannel;
        video_description = _videoDescription;
        video_id = _videoId;
    }

    public CustomItem(final String _videoTitle, final String _videoChannel, final String _imageUrl, final String _videoId, final String _videoDescription) {
        video_title = _videoTitle;
        video_channel = _videoChannel;
        video_image_url = _imageUrl;
        video_id = _videoId;
        video_description = _videoDescription;

    }

    public String getVideoTitle() {
        return video_title;
    }

    public String getVideoChannel() {
        return video_channel;
    }

    public String getImageUrl() {
        return video_image_url;
    }

    public String getVideoId() {
        return video_id;
    }

    public String getVideoDescription() {
        return video_description;
    }
}
