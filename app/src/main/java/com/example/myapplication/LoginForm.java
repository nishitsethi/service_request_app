package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginForm extends AppCompatActivity {

    EditText mobnum1, password1;
    Button login;
    DBHelper DB;
    TextView signup, adminLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login_form);
        mobnum1 = findViewById(R.id.mobnum1);
        password1 = findViewById(R.id.password1);
        login = findViewById(R.id.login);
        DB = new DBHelper(this);
        signup = findViewById(R.id.signup1);
        adminLoginButton = findViewById(R.id.adminLoginButton);

        SharedPreferences sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String mobnum = mobnum1.getText().toString();
                String password = password1.getText().toString();

                if (mobnum.equals("") || password.equals(""))
                    Toast.makeText(LoginForm.this, "Please enter all the fields "+mobnum+"; "+password, Toast.LENGTH_SHORT).show();
                else {
//                   Toast.makeText(LoginForm.this,"inside else "+mobnum+"; "+password, Toast.LENGTH_SHORT).show();

                    Boolean checkmobnumpassword = DB.checkmobnumpassword(mobnum, password);
//                    Boolean checkmobnumpassword=true;

 //                   Toast.makeText(LoginForm.this,"after checknumpass", Toast.LENGTH_SHORT).show();

                    if (checkmobnumpassword == true) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userid", mobnum1.getText().toString());
                        editor.apply();
                        Toast.makeText(LoginForm.this, "Log in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home_page.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginForm.this, "Invalid credentials", Toast.LENGTH_SHORT).show();


                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterForm.class));
            }


        });

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), loginAdmin.class));
            }


        });
            }}


