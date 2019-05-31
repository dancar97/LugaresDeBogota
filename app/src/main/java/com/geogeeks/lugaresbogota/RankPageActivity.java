package com.geogeeks.lugaresbogota;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RankPageActivity extends AppCompatActivity {

    private RatingBar simpleRatingBar;
    private TextView texto;
    private String lat;
    private String lon;
    private String name;
    private EditText mails;
    private String foto;
    private static final String REGISTER_URL
            ="http://geoapps.esri.co/AdminAppLugaresBogotaPHP/registro.php";
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
         foto = intent.getStringExtra("foto");
         texto.setText(name);
         mails = (EditText) findViewById(R.id.editText);

    }


    public void onClickRankSend(View v)
    {

        Float ratingNumber = simpleRatingBar.getRating();

       // Toast.makeText(this, ratingNumber + " " + mails.getText() + " " + name +" "+ lat + " "+lon, Toast.LENGTH_LONG).show();
        registerUser();


        Intent intent = new Intent(this, PantallaDeAgradecimiento.class);

        startActivity(intent);


    }



    private void registerUser() {

        /*
        String url = REGISTER_URL;

        final ProgressDialog pDialog = new ProgressDialog(RankPageActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        RequestQueue rq = Volley.newRequestQueue(RankPageActivity.this);

        JSONObject params = new JSONObject();
        try {
            params.put("nombre", "Ajay K K");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, params, //Not null.
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("err", response.toString());
                        // pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("aasdasd", "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

// Adding request to request queue
        rq.add(jsonObjReq);



*/


       // url = "http://httpbin.org/post";
        final ProgressDialog pDialog = new ProgressDialog(RankPageActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        RequestQueue rq = Volley.newRequestQueue(RankPageActivity.this);
        rq.start();
        StringRequest postRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error+"as");
                        pDialog.hide();
                    }
                }
        ) {


            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", mails.getText()+":"+name);
                params.put("correo", name);
                params.put("lat", lat);
                params.put("long", lon);
                params.put("foto", foto);
                params.put("rank", simpleRatingBar.getRating()+"");

                return checkParams(params);
            };


            private Map<String, String> checkParams(Map<String, String> map){
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                    if(pairs.getValue()==null){
                        map.put(pairs.getKey(), "sd");
                    }
                }
                return map;
            }




        };
       rq.add(postRequest);

    }



    }


