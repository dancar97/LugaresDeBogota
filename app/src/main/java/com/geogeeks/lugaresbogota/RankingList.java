package com.geogeeks.lugaresbogota;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.geogeeks.lugaresbogota.model.Rank;

//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.List;

public class RankingList extends ListActivity {


    ProgressBar pb;
    List<MyBgTask> tasks;
    List<Rank> rankList;
    Button btn_fetchdata;

    public ListView listaClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaClick = (ListView) findViewById(android.R.id.list);

        btn_fetchdata = (Button)findViewById(R.id.activity_main_btn_fetchdata);

        pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();
        requestData("https://srvags.sgc.gov.co/VolcanesSGCJson/Volcanes/sismos/events.json");
        /*listaClick.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i = new Intent(RankingList.this, ImageMapHandler.class);
                String strName = rankList.get(position).getMapa();
                i.putExtra("STRING_I_NEED", strName);

                startActivity(i);
                Toast.makeText(getApplicationContext(),
                        "Cargando " + rankoList.get(position).getMapa(), Toast.LENGTH_LONG)
                        .show();


            }



        });
*/
        btn_fetchdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOnline()) {
                    requestData("https://srvags.sgc.gov.co/VolcanesSGCJson/Volcanes/sismos/events.json");
                } else {
                    Toast.makeText(RankingList.this, "Network is not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestData(String uri) {
        MyBgTask task = new MyBgTask();
        task.execute(uri);
    }

    public void updateDisplay(){
        RankAdapter adapter;
        adapter = new RankAdapter(this, R.layout.row_item, rankList);

        listaClick.setAdapter(adapter);


    }


    protected boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo !=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }
    }










    private class MyBgTask extends AsyncTask<String, String, List<Rank>> {

        @Override
        protected void onPreExecute() {
            //updateDisplay();
            if (tasks.size() == 0) {                        // to check whether tasks array empty or not.
                // if empty then start progressbar on execute
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<Rank> doInBackground(String... params) {

            String content = HttpManager.getData(params[0]);
            rankList = rankJSONparser.parseFeed(content);

            for(Rank rank : rankList){
                try{
                   /*String imgURL = PHOTO_BASE_URL + sismo.getPhoto();
                    InputStream in = (InputStream)new URL(imgURL).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    sismo.setBitmap(bitmap);
                    in.close();*/
                }
                catch (Exception e){
                    e.printStackTrace();

                }
            }
            return rankList;

        }

        @Override
        protected void onPostExecute(List<Rank> result) {

            // PointList = SismoJSONParser.parseFeed(result);

            updateDisplay();
            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }
}