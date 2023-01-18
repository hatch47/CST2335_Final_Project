package com.example.cst2335_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();

        String saved = getResources().getString(R.string.name_saved);
        EditText edit_text = findViewById(R.id.edit_text);


        String name = getIntent().getStringExtra("text");
        edit_text.setText(name);

        }

    // send users name to welcome
    protected void onPause() {
        super.onPause();
        EditText editText = findViewById(R.id.edit_text);
        String text = editText.getText().toString();
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("text", text);
        startActivity(intent);


    }

    //toast and redirect to Welcome
    public void onWelcomeClick(View view) {
        String saved = getResources().getString(R.string.name_saved);
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),saved,Toast.LENGTH_LONG).show();
    }
}