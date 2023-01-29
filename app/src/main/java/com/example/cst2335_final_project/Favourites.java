package com.example.cst2335_final_project;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Favourites extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DatabaseHelper databaseHelper;
    private ListView listView;
    private ArrayList<String> urlList;
    private ArrayAdapter<String> adapter;
    ArrayList<Integer> positionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        //        copy here
//        //navbar stuff

//toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.fav));
        //For NavigationDrawer:
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //database
        databaseHelper = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.favListView);
        urlList = new ArrayList<>();

        //semi-working
        // retrieve data from the database
        Cursor cursor = databaseHelper.getData();
        while (cursor.moveToNext()) {
            urlList.add(cursor.getString(0));
        }

        // set the adapter for the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, urlList);
        listView.setAdapter(adapter);

        // delete from favourites using database -- semi-working one
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedUrl = urlList.get(position);
                databaseHelper.deleteData(selectedUrl);
                urlList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(Favourites.this, "Deleted from favorites", Toast.LENGTH_SHORT).show();

                return true;
            }
        });


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





    public void showDialog(View view) {
        String help = getResources().getString(R.string.help);
        String wtd = getResources().getString(R.string.what_to_do_fav);
        AlertDialog.Builder builder = new AlertDialog.Builder(Favourites.this);
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