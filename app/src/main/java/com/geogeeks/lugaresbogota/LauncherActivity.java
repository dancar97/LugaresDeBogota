package com.geogeeks.lugaresbogota;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;

public class LauncherActivity extends AppCompatActivity {



    private ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_laucher);

        imagen = (ImageView) findViewById(R.id.imageView3);

        imagen.setImageDrawable(getDrawable(R.drawable.ico));

    }

    public void onClickRank(View v)
    {
        Intent myIntent = new Intent(LauncherActivity.this, RankingList.class);
        LauncherActivity.this.startActivity(myIntent);

    }



    public void onClickMap(View v)
    {


        Intent myIntent = new Intent(LauncherActivity.this, MainActivity.class);
        LauncherActivity.this.startActivity(myIntent);
    }
    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
        finish();
    }
}