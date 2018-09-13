package ca.seemahejazi.photosecurity.project2v2.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by keithweaver on 2016-11-21.
 */

public class PhotoRestClient {
    private static PhotoAPI sSIClient;

    public static PhotoAPI get(){
        if (sSIClient == null){
            setupClient();
        }
        return sSIClient;
    }

    private static void setupClient(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Object.class, new NaturalDeserializer())
                .create();

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(PhotoAPI.ENDPOINT);
        builder.setClient(new OkClient(getHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setConverter(new GsonConverter(gson));

        RestAdapter restAdapter = builder.build();
        sSIClient = restAdapter.create(PhotoAPI.class);
    }

    private static OkHttpClient getHttpClient(){
        OkHttpClient client = new OkHttpClient();
        return client;
    }
}