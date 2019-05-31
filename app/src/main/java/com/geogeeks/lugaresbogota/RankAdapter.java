package com.geogeeks.lugaresbogota;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geogeeks.lugaresbogota.model.Rank;


import java.io.InputStream;
import java.util.List;


public class RankAdapter extends ArrayAdapter<Rank> {

    private Context context;
    private List<Rank> rankList;


    private class DownloadImageTask1 extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask1(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public RankAdapter(Context context, int resource, List<Rank> objects) {
        super(context, resource, objects);
        this.context = context;
        this.rankList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row_item, parent, false);

        // Display Sismo name
        Rank rank = rankList.get(position);


        TextView tv_item1 = (TextView)view.findViewById(R.id.row_item_txt2);
        TextView tv_item2 = (TextView)view.findViewById(R.id.row_item_txt3);
        ImageView tv_item3 = (ImageView) view.findViewById(R.id.imageView2);



        tv_item1.setText("Nombre: " + rank.getMagnitud());
        tv_item2.setText("Calificación promedio: "+ rank.getProfundidad());

        Display image;



        tv_item3.setImageBitmap(rank.getBitmap());

        return view;

    }






}
