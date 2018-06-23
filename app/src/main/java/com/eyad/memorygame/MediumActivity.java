package com.eyad.memorygame;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;
import android.os.Handler;
import java.util.ArrayList;

import tyrantgit.explosionfield.ExplosionField;


public class MediumActivity extends AppCompatActivity   implements LocationListener,SensorEventListener {

    final String COFFEE = "coffee";
    final String BEACH = "beach";
    final String BUNNY = "bunny";
    final String DOG = "dog";
    final String BOAT = "boat";
    final String BEAR = "bear";
    final String COINS = "coins";
    final String MOUNTAIN = "mountain";

    String username;
    Boolean beach_pressed = false;
    Boolean bunny_pressed = false;
    Boolean dog_pressed = false;
    Boolean boat_pressed = false;
    Boolean bear_pressed = false;
    Boolean coins_pressed = false;
    Boolean mountain_pressed = false;
    Boolean coffee_pressed= false;

    Boolean something_pressed = false;
    
    int number_of_matches = 0;
    final Handler handler_m = new Handler();
    ArrayList unmatched_pairs_list = new ArrayList();

    TextView username_display;
    TextView timer_display;
    TextView won_textView;

    ImageButton button1_beach;
    ImageButton button13_beach;

    ImageButton button2_bunny;
    ImageButton button4_bunny;

    ImageButton button3_dog;
    ImageButton button14_dog;

    ImageButton button5_boat;
    ImageButton button9_boat;

    ImageButton button6_bear;
    ImageButton button16_bear;

    ImageButton button7_coins;
    ImageButton button11_coins;

    ImageButton button8_mountain;
    ImageButton button15_mountain;

    ImageButton button10_coffee;
    ImageButton button12_coffee;


    ResultsDataSource DB;
    LocationManager locationManager;
    double longitude;
    double latitude;

    ExplosionField explosionField;
    final Handler handler_e = new Handler();

    SensorManager sm= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        //Data
        DB = new ResultsDataSource(this);
        getLocation();

        // get reference to SensorManager
        sm= (SensorManager) getSystemService(SENSOR_SERVICE);

        //hiding winning message
        won_textView = (TextView)findViewById(R.id.won_m_textView);
        won_textView.setVisibility(View.INVISIBLE);

        //getting content from bundle
        Bundle extras_m = getIntent().getExtras();
        username = extras_m.getString("EXTRA_USERNAME");

        //initializing components
        timer_display = (TextView)findViewById(R.id.timer_textView);
        username_display = (TextView)findViewById(R.id.username_m_textview);
        username_display.setText(username);
        applyButtons();
        

        //announcing number of cards
        Toast.makeText(getApplicationContext(), "Medium level: 16 cards in 45 seconds", Toast.LENGTH_LONG).show();


        //Animation
        explosionField = ExplosionField.attach2Window(this);

        //countdown timer
        new CountDownTimer(45000, 1000) {//change back to 45000
            public void onTick(long millisUntilFinished) {
                timer_display.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timer_display.setText("Done");
                Toast.makeText(getApplicationContext(), "You did not make it in time", Toast.LENGTH_LONG).show();

                explosionField.explode(button1_beach);
                explosionField.explode(button2_bunny);
                explosionField.explode(button3_dog);
                explosionField.explode(button4_bunny);
                explosionField.explode(button5_boat);
                explosionField.explode(button6_bear);
                explosionField.explode(button7_coins);
                explosionField.explode(button8_mountain);
                explosionField.explode(button9_boat);
                explosionField.explode(button10_coffee);
                explosionField.explode(button11_coins);
                explosionField.explode(button12_coffee);
                explosionField.explode(button13_beach);
                explosionField.explode(button14_dog);
                explosionField.explode(button15_mountain);
                explosionField.explode(button16_bear);

                handler_e.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do this after 1000ms=1sec
                        onBackPressed();
                    }
                }, 1000);


            }
        }.start();

        button1_beach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button1_beach,BEACH);
            }
        });

        button2_bunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button2_bunny,BUNNY);
            }
        });

        button3_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button3_dog,DOG);
            }
        });

        button4_bunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button4_bunny,BUNNY);
            }
        });

        button5_boat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button5_boat,BOAT);
            }
        });

        button6_bear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button6_bear,BEAR);
            }
        });

        button7_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button7_coins,COINS);
            }
        });

        button8_mountain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button8_mountain,MOUNTAIN);
            }
        });

        button9_boat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button9_boat,BOAT);
            }
        });

        button10_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button10_coffee,COFFEE);
            }
        });

        button11_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button11_coins,COINS);
            }
        });

        button12_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button12_coffee,COFFEE);
            }
        });

        button13_beach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button13_beach,BEACH);
            }
        });

        button14_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button14_dog,DOG);
            }
        });

        button15_mountain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button15_mountain, MOUNTAIN);
            }
        });

        button16_bear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickHandler(button16_bear,BEAR);
            }
        });

    }

    public void buttonClickHandler(ImageButton clicked_button, String value) {
        final ImageButton item = clicked_button;//needs to be declared like this because 'item' used in delay
        item.setEnabled(false);

        switch (value){
            
            case COFFEE:

                item.setImageResource(R.drawable.coffee_icon);
                if(coffee_pressed){
                    coffee_pressed=false;
                    something_pressed=false;
                    //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                    unmatched_pairs_list.remove(button10_coffee);
                    unmatched_pairs_list.remove(button12_coffee);
                    checkGameEnd();
                }else if(!something_pressed){
                    something_pressed=true;
                    coffee_pressed=true;
                }else{
                    something_pressed=false;
                    resetBooleans();
                    //delay here
                    handler_m.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do this after 1000ms=1sec
                            for (Object btn : unmatched_pairs_list) {
                                ((ImageButton) btn).setImageResource(R.drawable.q_mark_blue);
                                ((ImageButton)btn).setEnabled(true);
                            }
                        }
                    }, 1000);
                }
                break;

            case BEACH:

                item.setImageResource(R.drawable.beach_icon);
                if(beach_pressed){
                    beach_pressed=false;
                    something_pressed=false;
                    //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                    unmatched_pairs_list.remove(button1_beach);
                    unmatched_pairs_list.remove(button13_beach);
                    checkGameEnd();
                }else if(!something_pressed){
                    something_pressed=true;
                    beach_pressed=true;
                }else{
                    something_pressed=false;
                    resetBooleans();
                    //delay here
                    handler_m.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do this after 1000ms=1sec
                            for (Object btn : unmatched_pairs_list) {
                                ((ImageButton) btn).setImageResource(R.drawable.q_mark_blue);
                                ((ImageButton)btn).setEnabled(true);
                            }
                        }
                    }, 1000);
                }
                break;

            case BUNNY:

                item.setImageResource(R.drawable.bunny_icon);
                if(bunny_pressed){
                    bunny_pressed=false;
                    something_pressed=false;
                    //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                    unmatched_pairs_list.remove(button2_bunny);
                    unmatched_pairs_list.remove(button4_bunny);
                    checkGameEnd();
                }else if(!something_pressed){
                    something_pressed=true;
                    bunny_pressed=true;
                }else{
                    something_pressed=false;
                    resetBooleans();
                    //delay here
                    handler_m.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do this after 1000ms=1sec
                            for (Object btn : unmatched_pairs_list) {
                                ((ImageButton) btn).setImageResource(R.drawable.q_mark_blue);
                                ((ImageButton)btn).setEnabled(true);
                            }
                        }
                    }, 1000);
                }
                break;

            case DOG:

                item.setImageResource(R.drawable.dog_icon);
                if(dog_pressed){
                    dog_pressed=false;
                    something_pressed=false;
                    //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                    unmatched_pairs_list.remove(button3_dog);
                    unmatched_pairs_list.remove(button14_dog);
                    checkGameEnd();
                }else if(!something_pressed){
                    something_pressed=true;
                    dog_pressed=true;
                }else{
                    something_pressed=false;
                    resetBooleans();
                    //delay here
                    handler_m.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do this after 1000ms=1sec
                            for (Object btn : unmatched_pairs_list) {
                                ((ImageButton) btn).setImageResource(R.drawable.q_mark_blue);
                                ((ImageButton)btn).setEnabled(true);
                            }
                        }
                    }, 1000);
                }
                break;

            case BOAT:

                item.setImageResource(R.drawable.boat_icon);
                if(boat_pressed){
                    boat_pressed=false;
                    something_pressed=false;
                    //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                    unmatched_pairs_list.remove(button5_boat);
                    unmatched_pairs_list.remove(button9_boat);
                    checkGameEnd();
                }else if(!something_pressed){
                    something_pressed=true;
                    boat_pressed=true;
                }else{
                    something_pressed=false;
                    resetBooleans();
                    //delay here
                    handler_m.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do this after 1000ms=1sec
                            for (Object btn : unmatched_pairs_list) {
                                ((ImageButton) btn).setImageResource(R.drawable.q_mark_blue);
                                ((ImageButton)btn).setEnabled(true);
                            }
                        }
                    }, 1000);
                }
                break;

            case BEAR:

                item.setImageResource(R.drawable.bear_icon);
                if(bear_pressed){
                    bear_pressed=false;
                    something_pressed=false;
                    //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                    unmatched_pairs_list.remove(button6_bear);
                    unmatched_pairs_list.remove(button16_bear);
                    checkGameEnd();
                }else if(!something_pressed){
                    something_pressed=true;
                    bear_pressed=true;
                }else{
                    something_pressed=false;
                    resetBooleans();
                    //delay here
                    handler_m.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do this after 1000ms=1sec
                            for (Object btn : unmatched_pairs_list) {
                                ((ImageButton) btn).setImageResource(R.drawable.q_mark_blue);
                                ((ImageButton)btn).setEnabled(true);
                            }
                        }
                    }, 500);
                }
                break;

            case COINS:

                item.setImageResource(R.drawable.coins_icon);
                if(coins_pressed){
                    coins_pressed=false;
                    something_pressed=false;
                    //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                    unmatched_pairs_list.remove(button7_coins);
                    unmatched_pairs_list.remove(button11_coins);
                    checkGameEnd();
                }else if(!something_pressed){
                    something_pressed=true;
                    coins_pressed=true;
                }else{
                    something_pressed=false;
                    resetBooleans();
                    //delay here
                    handler_m.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do this after 1000ms=1sec
                            for (Object btn : unmatched_pairs_list) {
                                ((ImageButton) btn).setImageResource(R.drawable.q_mark_blue);
                                ((ImageButton)btn).setEnabled(true);
                            }
                        }
                    }, 1000);
                }
                break;

            case MOUNTAIN:

                item.setImageResource(R.drawable.mountains_icon);
                if(mountain_pressed){
                    mountain_pressed=false;
                    something_pressed=false;
                    //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                    unmatched_pairs_list.remove(button8_mountain);
                    unmatched_pairs_list.remove(button15_mountain);
                    checkGameEnd();
                }else if(!something_pressed){
                    something_pressed=true;
                    mountain_pressed=true;
                }else{
                    something_pressed=false;
                    resetBooleans();
                    //delay here
                    handler_m.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do this after 1000ms=1sec
                            for (Object btn : unmatched_pairs_list) {
                                ((ImageButton) btn).setImageResource(R.drawable.q_mark_blue);
                                ((ImageButton)btn).setEnabled(true);
                            }
                        }
                    }, 1000);
                }
                break;
        }
        
    }

    public void resetBooleans(){
        beach_pressed    = false;
        bunny_pressed    = false;
        dog_pressed      = false;
        boat_pressed     = false;
        bear_pressed     = false;
        coins_pressed    = false;
        mountain_pressed = false;
        coffee_pressed   = false;

    }

    public void applyButtons(){
        button1_beach=    (ImageButton)findViewById(R.id.m_button1);
        button2_bunny=    (ImageButton)findViewById(R.id.m_button2);
        button3_dog=      (ImageButton)findViewById(R.id.m_button3);
        button4_bunny=    (ImageButton)findViewById(R.id.m_button4);
        button5_boat=     (ImageButton)findViewById(R.id.m_button5);
        button6_bear=     (ImageButton)findViewById(R.id.m_button6);
        button7_coins=    (ImageButton)findViewById(R.id.m_button7);
        button8_mountain= (ImageButton)findViewById(R.id.m_button8);
        button9_boat=     (ImageButton)findViewById(R.id.m_button9);
        button10_coffee=   (ImageButton)findViewById(R.id.m_button10);
        button11_coins=   (ImageButton)findViewById(R.id.m_button11);
        button12_coffee=   (ImageButton)findViewById(R.id.m_button12);
        button13_beach=   (ImageButton)findViewById(R.id.m_button13);
        button14_dog=     (ImageButton)findViewById(R.id.m_button14);
        button15_mountain=(ImageButton)findViewById(R.id.m_button15);
        button16_bear=    (ImageButton)findViewById(R.id.m_button16);

        unmatched_pairs_list.add(button1_beach);
        unmatched_pairs_list.add(button2_bunny);
        unmatched_pairs_list.add(button3_dog);
        unmatched_pairs_list.add(button4_bunny);
        unmatched_pairs_list.add(button5_boat);
        unmatched_pairs_list.add(button6_bear);
        unmatched_pairs_list.add(button7_coins);
        unmatched_pairs_list.add(button8_mountain);
        unmatched_pairs_list.add(button9_boat);
        unmatched_pairs_list.add(button10_coffee);
        unmatched_pairs_list.add(button11_coins);
        unmatched_pairs_list.add(button12_coffee);
        unmatched_pairs_list.add(button13_beach);
        unmatched_pairs_list.add(button14_dog);
        unmatched_pairs_list.add(button15_mountain);
        unmatched_pairs_list.add(button16_bear);
    }
    
    public void checkGameEnd(){
        number_of_matches++;
        if(number_of_matches==8){
            won_textView.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "YOU WON THE GAME!", Toast.LENGTH_LONG).show();


            button1_beach.animate().rotation(button1_beach.getRotation()-360).start();
            button2_bunny.animate().rotation(button2_bunny.getRotation()-360).start();
            button3_dog.animate().rotation(button3_dog.getRotation()-360).start();
            button4_bunny.animate().rotation(button4_bunny.getRotation()-360).start();
            button5_boat.animate().rotation(button5_boat.getRotation()-360).start();
            button6_bear.animate().rotation(button6_bear.getRotation()-360).start();
            button7_coins.animate().rotation(button7_coins.getRotation()-360).start();
            button8_mountain.animate().rotation(button8_mountain.getRotation()-360).start();
            button9_boat.animate().rotation(button9_boat.getRotation()-360).start();
            button10_coffee.animate().rotation(button10_coffee.getRotation()-360).start();
            button11_coins.animate().rotation(button11_coins.getRotation()-360).start();
            button12_coffee.animate().rotation(button12_coffee.getRotation()-360).start();
            button13_beach.animate().rotation(button13_beach.getRotation()-360).start();
            button14_dog.animate().rotation(button14_dog.getRotation()-360).start();
            button15_mountain.animate().rotation(button15_mountain.getRotation()-360).start();
            button16_bear.animate().rotation(button16_bear.getRotation()-360).start();


            handler_e.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do this after 1000ms=1sec
                    onBackPressed();
                }
            }, 2000);

        }
    }



    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences("MyPrefs", 0);
        int id = settings.getInt("id", 1);

        DB.open();
        // DB.clearDatabase();
        DB.insertResult(new Result(id + "", username, latitude +"", longitude +"", number_of_matches));
        DB.close();


        id++;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("id", id);
        editor.commit();

    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
