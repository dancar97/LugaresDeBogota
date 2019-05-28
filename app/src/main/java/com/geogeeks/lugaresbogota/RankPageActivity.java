package com.geogeeks.lugaresbogota;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;

import java.util.HashMap;
import java.util.Map;

public class RankPageActivity extends AppCompatActivity {

    private RatingBar simpleRatingBar;
    private TextView texto;
    private String lat;
    private String lon;
    private String name;
    private EditText mails;
    private static final String REGISTER_URL
            ="http://geoapps.esri.co/SismosAppEsriPHP/adminSismos/registro.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_layout);
        Intent intent = getIntent();
        texto = (TextView) findViewById(R.id.textView3);
        simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar);

         lat = intent.getStringExtra("lat");
         lon = intent.getStringExtra("lon");
         name = intent.getStringExtra("name");
         texto.setText(name);
         mails = (EditText) findViewById(R.id.editText);

    }


    public void onClickRankSend(View v)
    {

        Float ratingNumber = simpleRatingBar.getRating();

        Toast.makeText(this, ratingNumber + " " + mails.getText() + " " + name +" "+ lat + " "+lon, Toast.LENGTH_LONG).show();

    }



    private void registerUser(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RankPageActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RankPageActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("nombre",name);
                params.put("rank",simpleRatingBar.getRating()+"");
                params.put("longitud", lon);
                params.put("latitud", lat);
                params.put("mail", mails.getText()+"");
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

































}