package com.example.insulinpump;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class doc_signup extends AppCompatActivity
{
    private EditText username,email,password;
    private Button button;
    private ProgressBar progressBar;
    FirebaseDatabase database;
    DatabaseReference doctors;
    private FirebaseAuth mAuth;
    private long maxid=0;

    public boolean a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_signup);
        username=(EditText) findViewById(R.id.editText);
        email=(EditText) findViewById(R.id.editText3);
        password=(EditText) findViewById(R.id.editText2);
        button=(Button) findViewById(R.id.button4);
        progressBar = (ProgressBar)findViewById(R.id.progressBar) ;
        progressBar.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeration();
            }
        });
        database =FirebaseDatabase.getInstance();
        doctors = database.getReference().child("Doctors");
        doctors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    maxid = dataSnapshot.getChildrenCount();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void registeration()
    {
        final String name=username.getText().toString().trim();
        final String emailid=email.getText().toString().trim();
        final String pass=password.getText().toString().trim();
        if(name.isEmpty()) {
            Toast.makeText(this,"Please enter username", Toast.LENGTH_LONG).show();

            return;
        }
        if(emailid.isEmpty()) {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();

            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()){
            Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_LONG).show();

            return;
        }


        if(pass.isEmpty()) {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();

            return;
        }

        if(pass.length()<6)
        {
            Toast.makeText(this,"Password should be atleast 6 characters long",Toast.LENGTH_LONG).show();

            return;
        }

        mAuth.fetchProvidersForEmail(emailid).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            public  void onComplete(@NonNull Task <ProviderQueryResult> task){
                boolean check = !task.getResult().getProviders().isEmpty();
                if(check)
                {
                    Toast.makeText(doc_signup.this,"This email has been registered already",Toast.LENGTH_LONG).show();
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    data user = new data(name, emailid, pass);
                    doctors.child(String.valueOf(maxid + 1)).setValue(user);
                    Toast.makeText(doc_signup.this, "Registered Successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(doc_signup.this, Doc.class);
                    startActivity(i);


                }


            }
        });

    }
}
