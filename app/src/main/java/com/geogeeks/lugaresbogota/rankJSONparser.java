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

                rank.setMagnitud(jsonObject.getString("mag"));
                rank.setProfundidad(jsonObject.getString("depth"));
                rank.setLatitud(jsonObject.getString("latitude1"));
                rank.setLongitud(jsonObject.getString("longitude1"));
                rank.setDescripcion(jsonObject.getString("description"));
                rank.setFecha(jsonObject.getString("time_UTC"));
                rank.setMapa(jsonObject.getString("mapURL"));

                rankList.add(rank);
            }
            return rankList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}