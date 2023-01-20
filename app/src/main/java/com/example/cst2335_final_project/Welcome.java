package com.example.cst2335_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

//public class Welcome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public class Welcome extends AppCompatActivity {

    private Button button_user;
    private Button button_guardian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        String welcome = getResources().getString(R.string.welcome);
        String info = getResources().getString(R.string.info);
        String info2 = getResources().getString(R.string.info2);

        //set snackbar on long click
        button_user = findViewById(R.id.button_user);
        button_guardian = findViewById(R.id.button_guardian);
        button_user.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.root), info, Snackbar.LENGTH_SHORT);
                snackbar.show();
                return true;
            }
        });

        button_guardian.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.root), info2, Snackbar.LENGTH_SHORT);
                snackbar.show();
                return true;
            }
        });

        // retrieve saved name
        SharedPreferences sharedPreferences = getSharedPreferences("userName", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        TextView textView = findViewById(R.id.text_view);
        textView.setText(welcome + " " + name + "!");


//        //navbar stuff
//        //toolbar
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        //For NavigationDrawer:
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
//                drawer, toolbar, R.string.open, R.string.close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = findViewById(R.id.nav_view);
////        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//
//        navigationView.setNavigationItemSelectedListener(this);


    }

//    //more navbar
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.home) {
//            // Bring user to MainActivity if they aren't already on it
//            if (!(this instanceof Welcome)) {
//                Intent intent = new Intent(this, Welcome.class);
//                startActivity(intent);
//            }
//        }
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
//    //more navbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    protected void onPause() {
        super.onPause();

        TextView textView = findViewById(R.id.text_view);
        String text = textView.getText().toString();

        }



    public void onTheGuardianClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onUserClick(View view) {

        Intent intent = new Intent(this, User.class);
        startActivity(intent);

    }

    //delete this button later
    public void onTheClick(View view) {

        Intent intent = new Intent(this, About.class);
        startActivity(intent);

    }


    public void showDialog(View view) {
        String help = getResources().getString(R.string.help);
        String wtd = getResources().getString(R.string.what_to_do);
        AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);
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