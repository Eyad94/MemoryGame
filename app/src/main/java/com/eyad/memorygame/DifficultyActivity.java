package com.eyad.memorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.content.Intent;

public class DifficultyActivity extends AppCompatActivity {

    String username;
    int age;
    private static final String TAG1 = DifficultyActivity.class.getSimpleName();
    TextView username_display;
    TextView age_display;
    Button easy;
    Button medium;
    Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        //getting extras
        Bundle extras = getIntent().getExtras();
        username = extras.getString("EXTRA_USERNAME");
        age = Integer.valueOf(extras.getString("EXTRA_AGE"));

        //initiallizing components
        username_display = (TextView)findViewById(R.id.username_textview);
        age_display = (TextView)findViewById(R.id.userage_textview);
        easy = (Button)findViewById(R.id.easy_button);
        medium = (Button)findViewById(R.id.medium_button);
        hard = (Button)findViewById(R.id.hard_button);

        username_display.setText(username);
        age_display.setText(String.valueOf(age));

        final Bundle extras1 = new Bundle();
        extras1.putString("EXTRA_USERNAME",username);

        //easy level chosen
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("easy");
                Intent easy_intent = new Intent(DifficultyActivity.this,EasyActivity.class);
                easy_intent.putExtras(extras1);
                startActivity(easy_intent);
            }
        });

        //medium level chosen
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("medium");
                Intent medium_intent = new Intent(DifficultyActivity.this,MediumActivity.class);
                medium_intent.putExtras(extras1);
                startActivity(medium_intent);
            }
        });

        //hard level chosen
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("hard");
                Intent hard_intent = new Intent(DifficultyActivity.this,HardActivity.class);
                hard_intent.putExtras(extras1);
                startActivity(hard_intent);

            }
        });


    }

    private void showToast(String text){
        Toast.makeText(DifficultyActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
