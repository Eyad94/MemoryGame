package com.eyad.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class HomeActivity extends AppCompatActivity {

    String username = "";
    int age = -1;
    private static final String TAG = HomeActivity.class.getSimpleName();
    EditText username_input;
    EditText age_input;
    Button   startgame_button;
    Button   results_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username_input = (EditText)findViewById(R.id.username_textbox);
        age_input = (EditText)findViewById(R.id.age_textbox);
        startgame_button = (Button)findViewById(R.id.startgame_button);

        startgame_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = username_input.getText().toString();
                age = Integer.valueOf(age_input.getText().toString());
                Intent difficultyIntent = new Intent(HomeActivity.this, DifficultyActivity.class);

                Bundle extras = new Bundle();
                extras.putString("EXTRA_USERNAME", username);
                extras.putString("EXTRA_AGE", String.valueOf(age));
                difficultyIntent.putExtras(extras);
                Log.d(TAG, "after creating dif intent and bundle before starting activity");
                startActivity(difficultyIntent);
                Log.d(TAG, "after creating dif intent and also after starting activity");
                //HomeActivity.this.startActivity(difficultyIntent);
                //finish();
            }

        });

        results_button = (Button)findViewById(R.id.results_button);
        results_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultsIntent = new Intent(HomeActivity.this, TransfersActivity.class);
                startActivity(resultsIntent);
            }
        });

    }

    private void showToast(String text){
        Toast.makeText(HomeActivity.this, text, Toast.LENGTH_SHORT).show();
        //showToast(username);
        //showToast(String.valueOf(age));
    }
}
