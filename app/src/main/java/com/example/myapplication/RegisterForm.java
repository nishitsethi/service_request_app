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

public class RegisterForm extends AppCompatActivity {

    EditText fullname,hsnumber, mobnum, email, password, repassword;
    TextView signin;
    Button btnRegister;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        fullname = findViewById(R.id.fullname);
        hsnumber =  findViewById(R.id.hsnumber);
        mobnum =  findViewById(R.id.mobnum);
        email =  findViewById(R.id.email);
        password =  findViewById(R.id.password);
        repassword =  findViewById(R.id.repassword);
        btnRegister =  findViewById(R.id.btnRegister);
        signin =findViewById(R.id.AlreadyHaveAccount);
        DB = new DBHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE);

        btnRegister.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String mob = RegisterForm.this.mobnum.getText().toString();
                String pass = RegisterForm.this.password.getText().toString();
                String repass = RegisterForm.this.repassword.getText().toString();
                String fulname = RegisterForm.this.fullname.getText().toString();
                String email = RegisterForm.this.email.getText().toString();
                String hsnum = RegisterForm.this.hsnumber.getText().toString();



                if(mob.equals("")||pass.equals("")||repass.equals("")||fulname.equals("")||email.equals("") ||hsnum.equals(""))
                    Toast.makeText(RegisterForm.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkmobnum = DB.checkmobnum(mob);
                        if (checkmobnum==false){
                            Boolean insert = DB.insertData(mob,pass,fulname,email,hsnum);

                            if (insert==true) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userid", mobnum.getText().toString());
                                editor.apply();
                                Toast.makeText(RegisterForm.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home_page.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterForm.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterForm.this, "User already exists! please log in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterForm.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }


//                startActivity(new Intent(RegisterForm.this,LoginForm.class));

            }

        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginForm.class);
                startActivity(intent);
            }
        });

    }
}