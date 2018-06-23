package com.eyad.memorygame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TransfersStep2Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=  inflater.inflate(R.layout.fr_transfers_step2,container,false);

        Button btn = v.findViewById(R.id.fr_transfer2btn_click);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransfersActivity actvity = (TransfersActivity)getActivity();
                actvity.moveToFragmentOne();
            }
        });


        return v;
    }
}
