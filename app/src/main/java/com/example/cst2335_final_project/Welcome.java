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


        //set welcome + users name
//        TextView textView = findViewById(R.id.text_view);
//        String name = getIntent().getStringExtra("text");
//        if (name == null || name.trim().isEmpty()) {
//            textView.setText(welcome + "!");
//        } else {
//            textView.setText(welcome + " " + name + "!");
//        }


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