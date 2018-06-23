package com.eyad.memorygame;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class TransfersActivity extends FragmentActivity {

    FragmentManager mFragmentManager;
    TransfersStep1Fragment transfersStep1Fragment;
    TransfersStep2Fragment transfersStep2Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        mFragmentManager = getSupportFragmentManager();

        transfersStep1Fragment = new TransfersStep1Fragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.your_placeholder, transfersStep1Fragment);
        ft.commit();
    }



    public void moveToFragmentTwo(){

        mFragmentManager = getSupportFragmentManager();
        if (transfersStep2Fragment == null){
            transfersStep2Fragment = new TransfersStep2Fragment();
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //ft.setCustomAnimations(R.animator.fade_in,R.animator.fade_in);
        ft.replace(R.id.your_placeholder,transfersStep2Fragment);
        ft.commit();
    }


    public void moveToFragmentOne(){

        mFragmentManager = getSupportFragmentManager();
        if (transfersStep1Fragment == null){
            transfersStep1Fragment = new TransfersStep1Fragment();
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //ft.setCustomAnimations(R.animator.fade_in,R.animator.fade_in);
        ft.replace(R.id.your_placeholder,transfersStep1Fragment);
        ft.commit();

    }

}
