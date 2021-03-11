package com.example.shoppy_friend;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    public void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Places.initialize(getApplicationContext(), api);
        //PlacesClient placesClient = Places.createClient();



        initUI();
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F00_HomePage()).commit();
            navigationView.setCheckedItem(R.id.nav_rss);
        }

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_rss:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F01_Shopping_List_Fragment()).commit();
                break;
            case R.id.nav_my_favorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F02_My_favourites_Fragment()).commit();
                break;
            case R.id.nav_purchase_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F03_Purchase_history_Fragment()).commit();
                break;
            case R.id.nav_wallet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F04_Wallet_Fragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F05_About_Fragment()).commit();
                break;
            case R.id.nav_deconnexion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new F06_Deconnexion_Fragment()).commit();
                break;

           //case R.id.nav_accueil:
             //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapsFragment()).commit();
               // break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}