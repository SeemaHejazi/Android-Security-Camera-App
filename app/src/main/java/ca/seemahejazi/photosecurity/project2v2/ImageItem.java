package ca.seemahejazi.photosecurity.project2v2;

import java.util.ArrayList;

/**
 * Created by Seema on 2016-11-23.
 */

public class ImageItem {
    private String url;
    private String username;
    private String description;
    private int likes;
    private ArrayList<String> comments;

    public ImageItem(String url, String username, String description, int likes, ArrayList<String> comments) {
        this.url = url;
        this.username = username;
        this.description = description;
        this.likes = likes;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}