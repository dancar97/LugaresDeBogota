package com.geogeeks.lugaresbogota;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.geogeeks.lugaresbogota.model.Rank;

import java.util.ArrayList;
import java.util.List;


public class rankJSONparser {
    public static List<Rank> parseFeed(String content){
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<Rank> rankList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i ++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Rank rank = new Rank();

                rank.setMagnitud(jsonObject.getString("correo"));
                rank.setProfundidad(jsonObject.getString("avg"));
               // rank.setLatitud(jsonObject.getString("foto"));
                //rank.setLongitud(jsonObject.getString("long"));


                rankList.add(rank);
            }
            return rankList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}