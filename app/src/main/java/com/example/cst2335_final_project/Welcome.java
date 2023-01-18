package com.example.cst2335_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Welcome extends AppCompatActivity {

    private Button button_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        String welcome = getResources().getString(R.string.welcome);
        String info = getResources().getString(R.string.info);

        //set snackbar on long click
        button_user = findViewById(R.id.button_user);
        button_user.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.root), info, Snackbar.LENGTH_SHORT);
                snackbar.show();
                return true;
            }
        });

        //set welcome + users name
        TextView textView = findViewById(R.id.text_view);
        String name = getIntent().getStringExtra("text");
        if (name == null || name.trim().isEmpty()) {
            textView.setText(welcome + "!");
        } else {
            textView.setText(welcome + " " + name + "!");
        }


    }

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


}