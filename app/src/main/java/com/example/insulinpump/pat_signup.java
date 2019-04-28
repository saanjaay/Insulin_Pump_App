package com.example.insulinpump;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class pat_signup extends AppCompatActivity {
    private EditText editText1,editText2,editText3,editText4;
    private ProgressBar progressBar;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_signup);
        editText1=(EditText) findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText3);
        editText3=(EditText)findViewById(R.id.editText2);
        editText4=(EditText)findViewById(R.id.editText4);
        button =(Button) findViewById(R.id.button4);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }
    public void submit() {
        final String name = editText1.getText().toString().trim();
        final String email = editText2.getText().toString().trim();
        final String password = editText3.getText().toString().trim();
        final String doctorcode = editText4.getText().toString().trim();
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();

            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_LONG).show();

            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter workplace", Toast.LENGTH_LONG).show();

            return;
        }
        if (doctorcode.isEmpty()) {
            Toast.makeText(this, "Enter your Doctor's code", Toast.LENGTH_LONG).show();

            return;
        }

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(doctorcode)) {
            progressBar.setVisibility(View.VISIBLE);
            Pat judge = new Pat(name, email, password, doctorcode);
            FirebaseDatabase.getInstance().getReference("Patients")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(judge).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(@NonNull Task<Void> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(pat_signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        Intent i = new Intent(pat_signup.this, patient_login.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(pat_signup.this, "Registration Failed,Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }}
