package com.example.cst2335_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //For NavigationDrawer:
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
////        if (id == R.id.home) {
////            // Bring user to MainActivity if they aren't already on it
////            if (!(this instanceof Welcome)) {
////                Intent intent = new Intent(this, Welcome.class);
////                startActivity(intent);
////            }
////        }
//
////        if (id == R.id.dad_joke_text) {
////            // Bring user to DadJoke
////            if (!(this instanceof DadJoke)) {
////                Intent intent = new Intent(this, DadJoke.class);
////                startActivity(intent);
////            }
////        }
//
//        if (id == R.id.exit) {
//            // Close all open activities and exit
//            finishAffinity();
//        }
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
////        String item1 = getString(R.string.item1);
////        String item2 = getString(R.string.item2);
////        String message = null;
//        //Look at your menu XML file. Put a case for every id in that file:
////        switch(item.getItemId())
////        {
////            //what to do when the menu item is selected:
////            case R.id.cap:
////                message = item1;
////                break;
////            case R.id.spiderman:
////                message = item2;
////                break;
////
////        }
////        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//        return true;
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


}

