package com.example.insulinpump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class patient_login extends AppCompatActivity {
    private EditText user, password;
    FirebaseDatabase database;
    DatabaseReference users;
    private TextView tv;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        user=(EditText) findViewById(R.id.editText) ;
        password=(EditText) findViewById(R.id.editText2) ;
        database = FirebaseDatabase.getInstance();
        users = database.getReference().child("Patients");
        button = (Button) findViewById(R.id.button2) ;
        tv = (TextView)findViewById(R.id.textView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();



            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void open()
    {
        Intent intent =  new Intent(this,pat_signup.class);
        startActivity(intent);
    }
    private void login() {
        final String username=user.getText().toString();
        final String passw=password.getText().toString();
        Query query = users.orderByChild("name").equalTo(username);
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();

            return;
        }
        if (passw.isEmpty()) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_LONG).show();

            return;
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    for (DataSnapshot user : dataSnapshot.getChildren()) {

                        User usersBean = user.getValue(User.class);

                        if (usersBean.password.equals(passw) ){
                            Intent intent=new Intent(patient_login.this,patient_medication.class);
                            startActivity(intent);
                            Toast.makeText(patient_login.this, "Login Successful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(patient_login.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(patient_login.this, "UserName not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
