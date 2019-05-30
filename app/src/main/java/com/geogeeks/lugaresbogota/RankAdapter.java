package com.geogeeks.lugaresbogota;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.geogeeks.lugaresbogota.model.Rank;


import java.util.List;


public class RankAdapter extends ArrayAdapter<Rank> {

    private Context context;
    private List<Rank> rankList;

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

        TextView tv_item = (TextView)view.findViewById(R.id.row_item_txt1);
        TextView tv_item1 = (TextView)view.findViewById(R.id.row_item_txt2);
        TextView tv_item2 = (TextView)view.findViewById(R.id.row_item_txt3);
        TextView tv_item3 = (TextView)view.findViewById(R.id.row_item_txt4);
        TextView tv_item4 = (TextView)view.findViewById(R.id.row_item_txt5);

        tv_item.setText("Fecha: " + rank.getFecha());
        tv_item1.setText("Magnitud: " + rank.getMagnitud());
        tv_item2.setText("Profundidad: "+ rank.getProfundidad());
        tv_item3.setText("Ubicación: Latitud:" + rank.getLatitud()+" Longitud" + rank.getLongitud());
        tv_item4.setText("Descripción: "+ rank.getDescripcion());
        //Display image
        //ImageView img = (ImageView)view.findViewById(R.id.row_item_img);
        //img.setImageBitmap(sismo.getBitmap());

        return view;

    }



}
