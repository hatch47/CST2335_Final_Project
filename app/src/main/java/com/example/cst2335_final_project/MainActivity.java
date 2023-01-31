package com.example.cst2335_final_project;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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


    public static final String WEBURL_SELECTED = "WEBURL";
    public static final String WEBTITLE_SELECTED = "WEBTITLE";
    private DatabaseHelper databaseHelper;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        new DownloadTask().execute();


        //progressbar
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        };
        handler.postDelayed(runnable, 2000);


        EditText editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                new DownloadTask().execute();
            }
        });

//        ListView listView = findViewById(R.id.list_view);


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


//        //database semi-working
        databaseHelper = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.list_view);

//        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i,long l) {
//                String fav = getString(R.string.addtofav);
//
//                // get the selected item
//                String selectedItem = (String) adapterView.getItemAtPosition(i);
//                Log.d("logging ", selectedItem);
////                String urlSelected = (String) adapterView.getItemAtPosition(i);
//                databaseHelper.insertData(selectedItem);
//                Toast.makeText(MainActivity.this, fav, Toast.LENGTH_SHORT).show();
//
//                return true;
//            }
//        });


        //testing new
//        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                String itemValue = list_view.getItemAtPosition(position).toString();
//                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
//                boolean isInserted = databaseHelper.insertData(itemValue);
//                Log.d("MainActivity", "onItemLongClick: itemValue = " + itemValue);
//                if (isInserted)
//                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });







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


    // retrieve using Async
    private class DownloadTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... urls) {
            // Fetch and download the data from the URL
            EditText editText = findViewById(R.id.editText);
            try {
                //to change the url so it ends with q= and then add the edittext value to the end
                String searchTerm = editText.getText().toString();
                URL url = new URL("https://content.guardianapis.com/search?api-key=4f732a4a-b27e-4ac7-9350-e9d0b11dd949&q=" + searchTerm);


                //once it gets working, switch this to the url with searchterm
//                URL url = new URL("https://content.guardianapis.com/search?api-key=4f732a4a-b27e-4ac7-9350-e9d0b11dd949&q=Tesla");
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

        @Override
        protected void onPostExecute(JSONObject json) {
            Log.i("Post Exectute", json.toString());

            ArrayList<String> webTitles = new ArrayList<>();
            ArrayList<String> webUrls = new ArrayList<>();
            JSONArray results = null;
            try {
                JSONObject newJson = json.getJSONObject("response");
                results = newJson.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject character = results.getJSONObject(i);

                    String webTitle = character.getString("webTitle");
                    String webUrl = character.getString("webUrl");

                    webTitles.add(webTitle);
                    webUrls.add(webUrl);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Context context = MainActivity.this;
            ListView theList = (ListView) findViewById(R.id.list_view);

            boolean isTablet = findViewById(R.id.fragmentLocation) != null; //check if the FrameLayout is loaded

//            ArrayAdapter<String> theAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, webTitles); //comment out to test new way
            MyAdapter theAdapter = new MyAdapter(context, webTitles, webUrls);
            theList.setAdapter(theAdapter);




            //set url and title to db
            theList.setOnItemLongClickListener((list, item, position, id) -> {
                String fav = getString(R.string.addtofav);

                // get the selected item
                String selectedItem = (String) webTitles.get(position) + "\n URL: " + webUrls.get(position);
//                String selectedItem = (String) webUrls.get(position);

                Log.d("logging ", selectedItem);
//                String urlSelected = (String) adapterView.getItemAtPosition(i);
                databaseHelper.insertData(selectedItem);
                Toast.makeText(MainActivity.this, fav, Toast.LENGTH_SHORT).show();


                return true;
            });





            // old one that gets the starwars names


         //  //fragments
            theList.setOnItemClickListener((list, item, position, id) -> {
                //Create a bundle to pass data to the new fragment
                Bundle dataToPass = new Bundle();
                dataToPass.putString(WEBURL_SELECTED, webUrls.get(position) );
                dataToPass.putString(WEBTITLE_SELECTED, webTitles.get(position) );


                if(isTablet)
                {
                    DetailFragment dFragment = new DetailFragment(); //add a DetailFragment
                    dFragment.setArguments( dataToPass ); //pass it a bundle for information
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentLocation, dFragment) //Add the fragment in FrameLayout
                            .commit(); //actually load the fragment.
                }
                else //isPhone
                {
                    Intent nextActivity = new Intent(MainActivity.this, EmptyActivity.class);
                    nextActivity.putExtras(dataToPass); //send data to next activity
                    startActivity(nextActivity); //make the transition
                }
            });


        }

    }

            public void showDialog (View view){
                String help = getResources().getString(R.string.help);
                String wtd = getResources().getString(R.string.what_to_do_browse);
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















