package com.eyad.memorygame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;


public class TransfersStep1Fragment extends Fragment {

    private ResultsDataSource DB;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =   inflater.inflate(R.layout.fr_transfers_step1,container,false);

        Button btn = view.findViewById(R.id.fr_transfer1btn_click);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TransfersActivity actvity = (TransfersActivity)getActivity();
                actvity.moveToFragmentTwo();

            }
        });

        DB = new ResultsDataSource(this.getActivity());
        DB.open();

        listView = (ListView) view.findViewById(R.id.listView);

        ArrayList<String> results = DB.getResults();
        DB.close();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, results);
        listView.setAdapter(arrayAdapter);



        return view;
    }
}
