/**
 * This class uses the google API to fetch the song requested by the user as a JSON object, and the parses that said object to find the data required, aka the title, url and duration of the song requested.
 * Copyright 2020 Félix Larouche <flaro058@uottawa.ca>
 */


package com.larouche.felix.billythemusicbot.services;

import com.larouche.felix.billythemusicbot.models.YoutubeVideo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Félix Larouche <flaro058@uottawa.ca>
 */


public class YouTubeSearch {

    public static YoutubeVideo search (String query){
        URL url = null;
        HttpURLConnection conn;
        int responseCode;
        StringBuilder inline = new StringBuilder();
        JSONParser parse = new JSONParser();

        YoutubeVideo video = null;

        query = query.replaceAll(" ", "%20");



        try {
            url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q=" + query + "&key=AIzaSyAhoVP4OH1UmCOe0AzgXCcCALkkDA2Gtgg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            assert url != null;
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            responseCode = conn.getResponseCode();


            if (responseCode != 200) throw new RuntimeException("HttpResponseCode " + responseCode);

            // Parsing http response into string
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()){
                inline.append(sc.nextLine());
            }
            sc.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            JSONObject jsonObject = (JSONObject)parse.parse(inline.toString());
            JSONArray items = (JSONArray) jsonObject.get("items");
            JSONObject firstVideo = (JSONObject) items.get(0);
            JSONObject snippet = (JSONObject) firstVideo.get("snippet");
            String title = (String) snippet.get("title");

            JSONObject id = (JSONObject) firstVideo.get("id");
            String vidId = (String) id.get("videoId");

            video = new YoutubeVideo(title, vidId);


            System.out.println("Title : " + title +  ", Video ID : " + vidId );

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return video;
    }


}
