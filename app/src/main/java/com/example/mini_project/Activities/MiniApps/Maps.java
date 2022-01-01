package com.example.mini_project.Activities.MiniApps;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mini_project.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity  implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Get a handle to the fragment and register the callback.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.706008, 10.202827))
                .title("HOME")
                .snippet("MoeBrm's Home \n Address: 05, Salwa street ElMourouj 5, Ben Arous 2074"));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.8163, 10.0610))
                .title("ISAMM")
                .snippet("Higher Institute of Arts and Multimedia Manouba \n Address: ISAMM, Campus Universitaire de la Manouba, 2010"));
        googleMap.setOnInfoWindowClickListener(this);
    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getSnippet(),
                Toast.LENGTH_SHORT).show();
    }

}