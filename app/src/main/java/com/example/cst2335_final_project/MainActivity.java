package com.example.cst2335_final_project;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        new DownloadTask().execute();

        ListView listView = findViewById(R.id.list_view);


        //        copy here
//        //navbar stuff

//toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.browse));
        //For NavigationDrawer:
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // for asynctask
        ListView list_view = findViewById(R.id.list_view);
        new DownloadTask().execute();
//        new DownloadTask(list_view).execute();

    }


    // more navbar
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Bring user to new Activity
            Intent intent = new Intent(this, Welcome.class);
            startActivity(intent);
        }

        if (id == R.id.browse) {
            // Bring user to new Activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.heart) {
            // Bring user to new Activity
            Intent intent = new Intent(this, Favourites.class);
            startActivity(intent);
        }

        if (id == R.id.User) {
            // Bring user to new Activity
            Intent intent = new Intent(this, User.class);
            startActivity(intent);
        }

        if (id == R.id.Info) {
            // Bring user to new Activity
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//    to here

    //Retrieve data using AsyncTask
    private class DownloadTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... urls) {
            // Fetch and download the data from the URL

            try {
                URL url = new URL("https://content.guardianapis.com/search?api-key=4f732a4a-b27e-4ac7-9350-e9d0b11dd949&q=Tesla"); // replace later so not only tesla
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

//                 Parse the JSON data and extract the names
//                of the characters

                JSONObject json = new JSONObject(result.toString());


                return json;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                Log.i("result", e.getMessage());
                return null;
            }

        }


        // almost working
        @Override
        protected void onPostExecute(JSONObject json) {
            ArrayList<String> titles = new ArrayList<>();

            JSONArray results = null;
            try {
                results = json.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject character = results.getJSONObject(i);
                    String webTitle = character.getString("webTitle");

                    titles.add(webTitle);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


//            Context context = MainActivity.this;
//            ListView theList = (ListView) findViewById(R.id.theList);
//            boolean isTablet = findViewById(R.id.fragmentLocation) != null; //check if the FrameLayout is loaded
//
//            ArrayAdapter<String> theAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, names);
//            theList.setAdapter(theAdapter);
//            theList.setOnItemClickListener((list, item, position, id) -> {
//                //Create a bundle to pass data to the new fragment
//                Bundle dataToPass = new Bundle();
//                dataToPass.putString(NAME_SELECTED, names.get(position) );
//                dataToPass.putString(MASS_SELECTED, masses.get(position) );
//                dataToPass.putString(HEIGHT_SELECTED, heights.get(position) );
//
//
//                if(isTablet)
//                {
//                    DetailFragment dFragment = new DetailFragment(); //add a DetailFragment
//                    dFragment.setArguments( dataToPass ); //pass it a bundle for information
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.fragmentLocation, dFragment) //Add the fragment in FrameLayout
//                            .commit(); //actually load the fragment.
//                }
//                else //isPhone
//                {
//                    Intent nextActivity = new Intent(MainActivity.this, EmptyActivity.class);
//                    nextActivity.putExtras(dataToPass); //send data to next activity
//                    startActivity(nextActivity); //make the transition
//                }
//            });
//
//
        }

    }


    public void showDialog(View view) {
        String help = getResources().getString(R.string.help);
        String wtd = getResources().getString(R.string.what_to_do);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(help);
        builder.setMessage(wtd);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do something here
            }
        });
        builder.show();
    }
}















