package com.example.cst2335_final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();
        String welcome = getResources().getString(R.string.welcome);
        String saved = getResources().getString(R.string.name_saved);


        // retrieve saved name
        SharedPreferences sharedPreferences = getSharedPreferences("userName", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        EditText edit_text = findViewById(R.id.edit_text);
        edit_text.setText(name);
    }



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