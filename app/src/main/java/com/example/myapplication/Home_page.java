package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Home_page extends AppCompatActivity {

    Button btnRaise, btnActive, btnPast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnRaise = findViewById(R.id.btnRaise);
        btnActive = findViewById(R.id.btnActive);
        btnPast = findViewById(R.id.btnPast);

        btnRaise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Raiserequest.class);
                startActivity(intent);
            }


        });
        btnActive.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activerequest.class);
                startActivity(intent);
            }
        });

        btnPast.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pastrequests.class);
                startActivity(intent);
            }

        }
        );
    }
}