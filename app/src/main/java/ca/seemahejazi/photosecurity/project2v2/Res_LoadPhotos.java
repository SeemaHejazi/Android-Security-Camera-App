package ca.seemahejazi.photosecurity.project2v2;

import java.util.ArrayList;

/**
 * Created by Seema on 2016-11-23.
 */

public class Res_LoadPhotos {
    private boolean success;
    private String message;
    private ArrayList<ImageItem> photos;

    public Res_LoadPhotos(boolean success, String message, ArrayList<ImageItem> photos) {
        this.success = success;
        this.message = message;
        this.photos = photos;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<ImageItem> getPhotos() {
        return photos;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPhotos(ArrayList<ImageItem> photos) {
        this.photos = photos;
    }
}
