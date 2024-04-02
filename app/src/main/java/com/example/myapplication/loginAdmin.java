package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class loginAdmin extends AppCompatActivity {

    EditText adminMobnum;
    EditText adminPass;
    Button adminSigninButton;
    TextView userLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        adminMobnum = findViewById(R.id.adminMobnum);
        adminPass = findViewById(R.id.adminPass);
        userLoginButton = findViewById(R.id.userLoginButton);
        adminSigninButton = findViewById(R.id.adminSigninButton);


        adminSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobnumAdmin = adminMobnum.getText().toString();
                String passAdmin = adminPass.getText().toString();

                if (mobnumAdmin.equals("") || passAdmin.equals(""))
                    Toast.makeText(loginAdmin.this, "Please enter all the fields "+mobnumAdmin+" "+passAdmin, Toast.LENGTH_SHORT).show();
                else {
                    if (mobnumAdmin.equals("admin") && passAdmin.equals("admin")) {
                        Toast.makeText(loginAdmin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), homeAdmin.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(loginAdmin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }



            }
        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginForm.class));
            }
        });

        };



    });
}}