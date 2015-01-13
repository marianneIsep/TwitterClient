package com.isep.project.utils;

import com.isep.project.model.Tweet;
import com.isep.project.model.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marianne on 31/12/14.
 */
public class WSConfiguration {

    private static final Logger log = Logger.getLogger(WSConfiguration.class);
    public static WebResource initClientWS()
    {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        return client.resource(getBaseUri());
    }

    public static URI getBaseUri(){
        return UriBuilder.fromUri("http://localhost:8080/rest/").build();

    }

    public List<User> getUsers() {

        /* Get the JSON code from the server */
        WebResource ws = WSConfiguration.initClientWS().path("services").path("getUsers");
        ClientResponse cr = ws.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String jsonResponse = cr.getEntity(String.class);

        /* Initialize listUser, jsonArray & mapper */
        JSONArray jsonArray = null;
        List<User> listUser = new ArrayList<User>();
        ObjectMapper mapper = new ObjectMapper();

        /* Insert jsonResponse in jsonArray */
        try {
            jsonArray = new JSONArray(jsonResponse);
        } catch (JSONException e) {
            log.error("Creation of jsonArray : " + e.getMessage());
        }


        /* Transform each item of the jsonArray in java object (user) */
        for (int i=0 ; i < jsonArray.length(); i++)
        {
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                log.error("jsonArray in getUsers : " + e.getMessage());
            }
            try {
                listUser.add(mapper.readValue(obj.toString(), User.class));
            } catch (IOException e) {
                log.error("JSON mapper in getUsers : " + e.getMessage());
            }
        }

        return listUser;
    }

    public List<Tweet> getTweetsFromUser(long userId){

        /* Get the JSON code from the server */
        WebResource ws = WSConfiguration.initClientWS().path("services").path("getTweets").path(Long.toString(userId));
        ClientResponse cr = ws.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String jsonResponse = cr.getEntity(String.class);

        /* Initialize listTweets, jsonArray & mapper */
        JSONArray jsonArray = null;
        List<Tweet> listTweets = new ArrayList<Tweet>();
        ObjectMapper mapper = new ObjectMapper();

        /* Insert jsonResponse in jsonArray */
        try {
            jsonArray = new JSONArray(jsonResponse);
        } catch (JSONException e) {
            log.error("Creation of jsonArray : " + e.getMessage());
        }


        /* Transform each item of the jsonArray in java object (tweet) */
        for (int i=0 ; i < jsonArray.length(); i++)
        {
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                log.error("jsonArray in getTweets : " + e.getMessage());
            }
            try {
                listTweets.add(mapper.readValue(obj.toString(), Tweet.class));
            } catch (IOException e) {
                log.error("JSON mapper in getTweets : " + e.getMessage());
            }
        }
        log.info(listTweets.size());
        return listTweets;

    }

    public boolean addTweets(){return false;}

}
