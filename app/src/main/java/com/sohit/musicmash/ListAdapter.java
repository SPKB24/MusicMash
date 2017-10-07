package com.sohit.musicmash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by sohit on 10/2/17.
 */

public class ListAdapter extends ArrayAdapter<CustomItem> {

    private Context context;
    private int backgroundColorId = R.color.colorPrimary;

    public ListAdapter(Context context, ArrayList<CustomItem> items, int _backgroundColorId) {
        super(context, 0, items);
        backgroundColorId = _backgroundColorId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final CustomItem currentItem = getItem(position);

        TextView mainTextView = listItemView.findViewById(R.id.main_content_view);
        mainTextView.setText(currentItem.getVideoTitle());

        TextView secondaryTextView = listItemView.findViewById(R.id.secondary_content_view);
        secondaryTextView.setText(currentItem.getVideoChannel());

        // This should hopefully magically work... ideally
        ImageView imageView = listItemView.findViewById(R.id.image);
        new DownloadImageTask(imageView).execute(currentItem.getImageUrl());

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), backgroundColorId);
        textContainer.setBackgroundColor(color);

        // Set an onclick listener to play the correct video
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YouTubePlayVideo.class);
                intent.putExtra("video_id", currentItem.getVideoId());
                intent.putExtra("video_title", currentItem.getVideoTitle());
                intent.putExtra("video_channel", currentItem.getVideoChannel());
                intent.putExtra("video_description", currentItem.getVideoDescription());
                context.startActivity(intent);
            }
        });

        return listItemView;
    }

    /* Taken from stackoverflow */
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
