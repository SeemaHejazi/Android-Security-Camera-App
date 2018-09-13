package ca.seemahejazi.photosecurity.project2v2;

/**
 * Created by Seema on 2016-11-23.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import ca.seemahejazi.photosecurity.project2v2.Server.C;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ca.seemahejazi.photosecurity.project2v2.Server.PhotoRestClient;


/**
 * Created by bryanmierdel on 2016-11-12.
 * Edited by seemahejazi on 2016-11-22.
 */

public class ViewActivity extends AppCompatActivity {

    public final String TAG = ViewActivity.class.getSimpleName();

    private ArrayList<ImageItem> mImages;
    private ImageAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        TextView welcomeMsg = (TextView) findViewById(R.id.welcomeMsg);
        welcomeMsg.append(" Dave!");

        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);

        mImages = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(ViewActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter= new ImageAdapter(ViewActivity.this, mImages);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.v(TAG, "onResume();");
        loadImages();
    }

    public void logoutClick(View view) {
        Intent openNewWindow = new Intent(ViewActivity.this, MainActivity.class);
        finish();
        startActivity(openNewWindow);
    }

    protected void loadImages() {
        Log.v(TAG, "LoadImages();");

        PhotoRestClient.get().getPhotos(C.API_KEY, new Callback<Res_LoadPhotos>() {
            @Override
            public void success(Res_LoadPhotos res_loadPhotos, Response response) {
                if (res_loadPhotos.isSuccess()) {
                    mImages.clear();

                    for (ImageItem imgItem : res_loadPhotos.getPhotos()) {
                        mImages.add(imgItem);
                    }

                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(ViewActivity.this, "Issue loading", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ViewActivity.this, "Issue Loading 2", Toast.LENGTH_SHORT).show();
                Log.e(TAG, error.toString());
            }
        });
    }
}