package com.example.cst2335_final_project;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class User extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
//        getSupportActionBar().hide();
        String welcome = getResources().getString(R.string.welcome);
        String saved = getResources().getString(R.string.name_saved);


        // retrieve saved name
        SharedPreferences sharedPreferences = getSharedPreferences("userName", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        EditText edit_text = findViewById(R.id.edit_text);
        edit_text.setText(name);

        //        copy here
//        //navbar stuff

//toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.user));
        //For NavigationDrawer:
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    // send users name to welcome
    protected void onPause() {
        super.onPause();
        EditText editText = findViewById(R.id.edit_text);
        String text = editText.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("userName", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", text);
        editor.apply();
    }

    //toast and redirect to Welcome
    public void onWelcomeClick(View view) {
        String saved = getResources().getString(R.string.name_saved);
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),saved,Toast.LENGTH_LONG).show();
    }

    public void showDialog(View view) {
        String help = getResources().getString(R.string.help);
        String wtd = getResources().getString(R.string.what_to_do);
        AlertDialog.Builder builder = new AlertDialog.Builder(User.this);
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