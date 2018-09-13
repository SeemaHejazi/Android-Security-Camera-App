package ca.seemahejazi.photosecurity.project2v2.Server;

import ca.seemahejazi.photosecurity.project2v2.Res_LoadPhotos;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Seema on 2016-11-23.
 * does this work?
 */

public interface PhotoAPI {
    String ENDPOINT = "https://keithweaver.ca/posts/";

    @FormUrlEncoded
    @POST("/27/load-photos")
    void getPhotos(@Field("apikey") String apikey, Callback<Res_LoadPhotos> callback);

}
