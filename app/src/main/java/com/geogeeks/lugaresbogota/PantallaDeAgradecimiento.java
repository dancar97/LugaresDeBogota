package com.geogeeks.lugaresbogota;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class PantallaDeAgradecimiento extends AppCompatActivity {

    private RatingBar simpleRatingBar;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agradecimiento_layout);


    }

    public void onClickView(View v)
    {

        Intent myIntent = new Intent(PantallaDeAgradecimiento.this, MainActivity.class);
        PantallaDeAgradecimiento.this.startActivity(myIntent);
    }


    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(PantallaDeAgradecimiento.this, MainActivity.class);
        startActivity(intent);
    }
}