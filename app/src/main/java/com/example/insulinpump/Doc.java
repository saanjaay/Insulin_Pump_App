package com.example.insulinpump;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.Security;
import java.util.Map;

public class Doc extends AppCompatActivity {
    private EditText user, password;
    private TextView tv;
    private Button button;
    FirebaseDatabase database;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);
        tv = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button2);
        user = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        database = FirebaseDatabase.getInstance();
        users = database.getReference().child("Doctors");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
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
        Intent intent = new Intent(this,doc_signup.class);
        startActivity(intent);
    }
    private void login() {
        final String username=user.getText().toString();
        final String passw=password.getText().toString();
        Query query = users.orderByChild("name").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    for (DataSnapshot user : dataSnapshot.getChildren()) {

                        data usersBean = user.getValue(data.class);

                        if (usersBean.password.equals(passw) ){
                            Intent intent=new Intent(Doc.this,patient_list.class);
                            startActivity(intent);
                            Toast.makeText(Doc.this, "Login Successfull", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Doc.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(Doc.this, "UserName not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
