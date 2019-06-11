package com.example.insulinpump;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class patient_list extends AppCompatActivity {
    public Spinner spinner;
    DatabaseReference myRef,myy;
    TextView textView,textView1;
    ListView listView;
    List<String> list= new ArrayList<>();
    ArrayList<String> valuesbasal= new ArrayList<>();
    List<String> valuesbolus= new ArrayList<>();
    List<String> valuestdd= new ArrayList<>();
    String[] namesArr;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        myRef=FirebaseDatabase.getInstance().getReference("Patientsname");
        spinner = (Spinner) findViewById(R.id.spinner2);
        textView = findViewById(R.id.textView7);
        listView = findViewById(R.id.li);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemReselectedListener(navListener);
        ChildEventListener childEventListener = new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {


                Name user = dataSnapshot.getValue(Name.class);
                list.add(user.name);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(patient_list.this, android.R.layout.simple_spinner_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

//                String text = spinner.getSelectedItem().toString();
//                textView.setText(text);
                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        };
        myRef.addChildEventListener(childEventListener);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {


                String text=spinner.getSelectedItem().toString();
                myy=FirebaseDatabase.getInstance().getReference(text).child("medication");
                ChildEventListener childEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                        Value val = dataSnapshot.getValue(Value.class);
                        i++;
                        String[] da=val.data.split("@",4);

                        valuesbasal.add("\n  Shot \t"+ i +"\n\n             " + da[0] + "   -   "+da[1]+" Units \n\n"+ "             Time   :   "+ da[3] + "\n\n" + "             Date   :   " + da[2]+"\n");

                        ArrayAdapter<String> mHistory = new ArrayAdapter<String>(patient_list.this, android.R.layout.simple_list_item_1, valuesbasal);
                        listView.setAdapter(mHistory);
                        ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();







//                String text = spinner.getSelectedItem().toString();
//                textView.setText(text);
                        // ...
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {


                        // ...
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {


                        // ...
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                };
                myy.addChildEventListener(childEventListener);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

//        myRef.addValueEventListener(new ValueEventListener() {
//
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Name user = postSnapshot.getValue(Name.class);
//                    list.add(user.name);
//                    namesArr = new String[list.size()];
//                    for (int i = 0; i < list.size(); i++) {
//                        Log.d("li" ,list.get(i));
//                        namesArr[i] = list.get(i);
//                    }
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });

//        String str[]=new String[list.size()];




    }
    private BottomNavigationView.OnNavigationItemReselectedListener navListener =
            new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch(menuItem.getItemId())
                    {
                        case R.id.nav_medication:
                            selectedFragment = new MedicationFragment();
                            break;
                        case R.id.nav_pro:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.nav_Cal:
                            selectedFragment = new FoodFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                }
            };
}
