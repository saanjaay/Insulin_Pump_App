package com.example.insulinpump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button,button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button7);
        button1 = (Button)findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPat();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoc();
            }
        });

    }
    public void openDoc() {
        Intent intent = new Intent(this, Doc.class);
        startActivity(intent);
    }
    public void openPat() {
        Intent intent = new Intent(this, patient_login.class);
        startActivity(intent);
    }
}


