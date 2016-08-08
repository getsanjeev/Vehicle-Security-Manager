package com.alpha.sanjeev.vehiclesecuritymanager;

/**
 * Created by sanjeev on 17/6/16.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class frag_1 extends Fragment
{




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_1, container, false);

        Button track_button = (Button) view.findViewById(R.id.track_button);
        Button place_button = (Button) view.findViewById(R.id.places);

        final EditText lat = (EditText) view.findViewById(R.id.lat);
        final EditText lon = (EditText) view.findViewById(R.id.lon);

        track_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitude = lat.getText().toString();
                String longitude = lon.getText().toString();

                SharedPreferences mango = getActivity().getSharedPreferences("mango", Context.MODE_PRIVATE);
                SharedPreferences.Editor my__editor = mango.edit();
                my__editor.putString("phi", latitude);
                my__editor.putString("xi", longitude);
                my__editor.commit();
                Intent intent = new Intent(getActivity(), show_map.class);
                startActivity(intent);

            }
        });

        place_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), places.class);
                startActivity(intent);

            }
        });


        return view;

    }
}

