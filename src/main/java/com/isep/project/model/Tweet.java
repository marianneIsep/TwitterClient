package com.isep.project.model;

import java.sql.Timestamp;

/**
 * Created by Marianne on 31/12/14.
 */

public class Tweet {
    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    private long tweetId;
    private Timestamp date;
    private String message;
    private User author;

}