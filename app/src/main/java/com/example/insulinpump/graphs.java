package com.example.insulinpump;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class graphs extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_medication);

        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.home:
                Intent h = new Intent(this, patient_medication.class);
                startActivity(h);
                break;
            case R.id.profile:
                Intent j = new Intent(this, profile.class);
                startActivity(j);
                break;
            case R.id.preset:
                Intent i = new Intent(this, preset.class);
                startActivity(i);
                break;
            case R.id.diet:
                Intent l = new Intent(this, diet.class);
                startActivity(l);
                break;
            case R.id.graphs:
                Intent m = new Intent(this, graphs.class);
                startActivity(m);
                break;
            case R.id.settings:
                Intent k = new Intent(this, Settings.class);
                startActivity(k);
                break;
            case R.id.logout:
                Intent g = new Intent(this, patient_login.class);
                startActivity(g);
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

