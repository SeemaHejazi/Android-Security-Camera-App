package ca.seemahejazi.photosecurity.project2v2;

/**
 * Created by Seema on 2016-11-23.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ca.seemahejazi.photosecurity.project2v2.ImageItem;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<ImageItem> mImages;
    private Context mContext;

    public ImageAdapter(Context c, ArrayList<ImageItem> n) {
        this.mImages = n;
        this.mContext = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        //set layout content w code here
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ImageItem currImage = mImages.get(i);
        //Render image using Picasso library

        Picasso.with(mContext).load(currImage.getUrl()).error(R.drawable.star)
                .placeholder(R.drawable.star).into(viewHolder.mainImage);

        //Setting text view title
        //String takenOn = "Date of Photo: " + currImage.getCreated().substring(0,9) + " Time: " + currImage.getCreated().substring(11,16);
        viewHolder.textView.setText("HI");
    }

    @Override
    public int getItemCount() {
        if (mImages != null) {
            return mImages.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        protected ImageView mainImage;
        protected TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.mainImage = (ImageView) view.findViewById(R.id.mainImage);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }

}
