package com.example.mohammedabdelsattar.firstapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Model.Movie;

public class MainActivity extends AppCompatActivity {
    //
    ListView lvList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public ProgressDialog pDialog;

    public  ArrayList<Movie> movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // onSaveInstanceState(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
movieList=new ArrayList<>();

        new MovieManager().execute();

  /*      ArrayList<String> tempdata = new ArrayList<>();
        tempdata.add("i love apple");
        tempdata.add("i love bananaa");
        tempdata.add("i love date");

        ArrayAdapter<String> adapte = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, tempdata);


        lvList = (ListView) findViewById(R.id.listView);

        lvList.setAdapter(adapte);

       /* Picasso.with(this)
                .load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg")
                .into(lvList);*/



/*
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,ItemDetailsActivity.class);
                if(position == 0)
                {
                    intent.putExtra("tv1in","Mohammed Like apple");

                }
                else if(position == 1)
                {
                    intent.putExtra("tv1in","Amera Like bananaa");


                }
                else{

                    intent.putExtra("tv1in","Mohammed Like date");

                }
            }
        });

*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.mohammedabdelsattar.firstapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.mohammedabdelsattar.firstapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    public  void parseGrideData(String data){
        try {
            JSONObject jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("results");

            JSONObject obj;
            int ID;
            String original_title;
            String title;
            boolean adult;
            String poster_path;
            String backdrop_path;
            double  popularity;
            int vote_count;
            double vote_average;
            String overview;
            boolean video;

            for(int i=0 ;i<jsonArray.length() ; i++)
            {
                obj=jsonArray.getJSONObject(i);
                 ID=obj.getInt("id");
                 original_title=obj.getString("original_title");
                 title=obj.getString("title");
                adult=obj.getBoolean("adult");
                 poster_path=obj.getString("poster_path");

                 backdrop_path=obj.getString("backdrop_path");
                  popularity=obj.getDouble("popularity");
                vote_count=obj.getInt("vote_count");
                vote_average=obj.getDouble("vote_average");
                 overview=obj.getString("overview");
                 video= obj.getBoolean("video");

                movieList.add(new Movie(ID,original_title,title,adult,poster_path,backdrop_path, popularity, vote_count, vote_average, overview, video));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private class MovieManager extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(Void... arg0) {

            ServiceHandler serviceHandler = new ServiceHandler();
            String data = "";
            try {

                data = serviceHandler.makeServiceCall("http://api.themoviedb.org/3/movie/popular?api_key=839f7716ecbb918ca9aecafd1b0f65cb",
                        ServiceHandler.GET);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(pDialog.isShowing())
                pDialog.dismiss();


parseGrideData(result);

            GridView gv=(GridView) findViewById(R.id.gridView1);
            gv.setAdapter(new MovieGrideAdapter(MainActivity.this, movieList));


        }



    }


}
