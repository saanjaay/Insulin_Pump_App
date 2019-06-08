package com.example.insulinpump;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class patient_medication extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private Bundle bundle;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    FirebaseDatabase database;
    DatabaseReference users;
    String username;
    String tdd1,tba1,rba1,iba1,tbo1,rbo1,ibo1;
    private TextView tdd,tba,tbo,battery,timeba,timebo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_medication);
        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        database = FirebaseDatabase.getInstance();
        bundle=getIntent().getExtras();
        Intent i=getIntent();
        i.getStringExtra("name");
        username=bundle.getString("name");
        users = database.getReference().child("Patients");
        Query query = users.orderByChild("name").equalTo(username);
        timeba = findViewById(R.id.textView30);
        timebo = findViewById(R.id.textView31);
        tdd = (TextView)findViewById(R.id.textView33);
        tba = (TextView)findViewById(R.id.textView25);
        battery = (TextView)findViewById(R.id.textView29);

        tbo = (TextView)findViewById(R.id.textView26);

        /*i.getStringExtra("tdd");
        i.getStringExtra("tba");
        i.getStringExtra("rba");
        i.getStringExtra("iba");
        i.getStringExtra("tbo");
        i.getStringExtra("rbo");
        i.getStringExtra("ibo");
        tdd1=bundle.getString("tdd");
        tba1=bundle.getString("tba");
        rba1=bundle.getString("rba");
        iba1=bundle.getString("iba");
        tbo1=bundle.getString("tbo");
        rbo1=bundle.getString("rbo");
        ibo1=bundle.getString("ibo");*/
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {

                        User val = user.getValue(User.class);
                        String[] time=val.basal.split("@",2);
                        String[] time1=val.bolus.split("@",2);
                        battery.setText(val.battery);
                        tdd.setText(val.tdd);
                        timeba.setText(time[1]);
                        tba.setText(time[0]);
                        tbo.setText(time1[0]);
                        timebo.setText(time1[1]);


                    }
                }
                else {
                    Toast.makeText(patient_medication.this, "Data not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
