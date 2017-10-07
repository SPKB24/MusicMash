package com.sohit.musicmash;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sohit on 10/7/17.
 */

public class YouTubeVideoItem {

    private String video_title;
    private String video_channel;
    private String video_id;
    private String video_thumbnail;

    public YouTubeVideoItem(final String _title, final String _channel, final String _id, final String _thumbnail) {
        this.video_title = _title;
        this.video_channel = _channel;
        this.video_id = _id;
        this.video_thumbnail = _thumbnail;
    }

    public String get_title() {
        return video_title;
    }

    public String getVideo_channel() {
        return video_channel;
    }

    public String get_id() {
        return video_id;
    }

    public String get_thumbnail() {
        return video_thumbnail;
    }

    public static String createYoutubeUrl(final String query, final int numResults) {
        String first = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=";
        String second = "&q=";
        String third = "&type=video&key=";
        return first + numResults + second + query + third + YouTubeConfig.YOUTUBE_API_KEY;
    }

    public static JSONObject stringToJson(final String input) {
        try {
            JSONObject toReturn = new JSONObject(input);
            return toReturn;
        } catch (Exception e ) {
            return null;
        }
    }

    public static ArrayList<CustomItem> parseJson(JSONObject jsonObject) {
        ArrayList<CustomItem> toReturn = new ArrayList<>();

        System.out.println("STARTING PARSEJSON");
        try {
            System.out.println("Whats happening??");
            JSONArray array = jsonObject.getJSONArray("items");

            System.out.println(array.length());

            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);

                CustomItem customItem = new CustomItem(
                        jsonGetVideoTitle(item),
                        jsonGetVideoChannel(item),
                        jsonGetVideoThumbnailUrl(item),
                        jsonGetVideoId(item));

                toReturn.add(customItem);
            }
        } catch (JSONException e) {
            // something
            System.out.println("ERROR: " + e.getMessage());
        }

        return toReturn;
    }

    private static String jsonGetVideoId(JSONObject item) {
        try {
            return item.getJSONObject("id").getString("videoId");
        } catch (Exception e) {
            return null;
        }
    }

    private static String jsonGetVideoTitle(JSONObject item) {
        try {
            return item.getJSONObject("snippet").getString("title");
        } catch (Exception e) {
            return null;
        }
    }

    private static String jsonGetVideoChannel(JSONObject item) {
        try {
            return item.getJSONObject("snippet").getString("channelTitle");
        } catch (Exception e) {
            return null;
        }
    }

    private static String jsonGetVideoThumbnailUrl(JSONObject item) {
        try {
            return item.getJSONObject("snippet").getJSONObject("thumbnails")
                    .getJSONObject("medium").getString("url");
        } catch (Exception e) {
            return null;
        }
    }
}
