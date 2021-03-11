package com.example.shoppy_friend;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class F00_HomePage extends Fragment implements OnMapReadyCallback {

    public static final String MAPVIEW_BUNDLE_KEY ="MapViewBundleKey";
    private ConstraintLayout mConstraintLayout;
    private MapView mMapView;
    private EditText find_Your_Shop;
    private EditText where_Is_The_Delivery_Point;
    private Button button_OK_00_Accueil;


    /** String apiKey = getString(R.string.api_key);
        if (!Places.isInitialized()) {
        Places.initialize(getApplicationContext(), apiKey);
    }

    // Create a new Places client instance.
    placesClient = Places.createClient(this);
        **/

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_00_home_page, container, false);
        mConstraintLayout = view.findViewById(R.id.map);
        mMapView = (MapView)view.findViewById(R.id.mapView);


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);



        find_Your_Shop = (EditText) view.findViewById (R.id.editText_Find_Your_Shop);
        where_Is_The_Delivery_Point = (EditText) view.findViewById(R.id.editText_Where_Is_Delivery_Point);
        where_Is_The_Delivery_Point.setVisibility(View.GONE);

        find_Your_Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConstraintLayout.setElevation(40);
                where_Is_The_Delivery_Point.setVisibility(View.VISIBLE);
            }
        });


        return view;
    };


    public void onSaveInstanceSate(Bundle outState) {

        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle== null){
            mapViewBundle.equals(new Bundle());
            outState.putBundle(MAPVIEW_BUNDLE_KEY,mapViewBundle);
        }

        mMapView.onSaveInstanceState((mapViewBundle));
    }

    @Override
    public  void  onResume () {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public  void  onStart () {
        super.onStart();
        mMapView.onStart();
    }
    @Override
    public  void  onStop () {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public  void  onPause () {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public  void  onDestroy () {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMinZoomPreference(12.0f);
        googleMap.setMaxZoomPreference(20.0f);
        LatLng franprix = new LatLng(48.8873154,2.387141);
        googleMap.addMarker(new MarkerOptions().position(franprix).title("Franprix"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(franprix));
    };



}
