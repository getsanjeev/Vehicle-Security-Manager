package com.alpha.sanjeev.vehiclesecuritymanager;

/**
 * Created by sanjeev on 10/7/16.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class show_map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double latitude;
    Double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_page);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        SharedPreferences mango = getSharedPreferences("mango", MODE_PRIVATE);
        String lat = mango.getString("phi", "sanjev");
        String lon = mango.getString("xi", "pank");
       latitude = Double.parseDouble(lat);
       longitude = Double.parseDouble(lon);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng coordinate = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(coordinate).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
        mMap.animateCamera(yourLocation);
    }
}
