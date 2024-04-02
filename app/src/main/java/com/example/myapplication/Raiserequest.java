package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

import android.widget.Button;
import android.widget.*;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;








public class Raiserequest extends AppCompatActivity implements OnItemSelectedListener{
    String RequestType;
//  Date currenttime = Calender.getInstance().getTime();
//    Time time = new Time();
//    time.setToNow();
    String requesttime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raiserequest);


/*

        Spinner drpType = findViewById(R.id.spinner1);

        String[] items = new String[]{"Plumbing", "Electricity"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        drpType.setAdapter(adapter);
        drpType.setOnItemSelectedListener(this);
*/

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);


        spinner.setOnItemSelectedListener(this);


        List<String> categories = new ArrayList<String>();
        categories.add("Plumbing");
        categories.add("Electricity");
        categories.add("Carpentry");
        categories.add("Gardening");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);



        EditText txtDescription,txtNameRaiseRequest, txtMobNumRaiseRequest, txtAddressRaiseRequest;

        txtDescription = findViewById(R.id.editTextTextMultiLine);
        txtNameRaiseRequest = findViewById(R.id.txtNameRaiseRequest);
        txtMobNumRaiseRequest = findViewById(R.id.txtMobNumRaiseRequest);
        txtAddressRaiseRequest = findViewById(R.id.txtAddressRaiseRequest);
        DBHelper DB = new DBHelper(this);
        Button btnSubmit;

        btnSubmit =  findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {



                String strDescription = txtDescription.getText().toString();
                String strName = txtNameRaiseRequest.getText().toString();
                String strMobNum = txtMobNumRaiseRequest.getText().toString();
                String strAddress = txtAddressRaiseRequest.getText().toString();



                if(strDescription.equals("")||strName.equals("")||strMobNum.equals("")||strAddress.equals(""))
                    Toast.makeText(Raiserequest.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {


                    long insert;
                    insert = -1;

                    try {
                        insert = DB.insertRequest(strMobNum, RequestType, strName, strDescription, strAddress, "Active", requesttime);
                    } catch (Exception e) {
                        // This will catch any exception, because they are all descended from Exception
                        System.out.println("Error " + e.getMessage());
                        Toast.makeText(Raiserequest.this, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
                        //return null;
                    }

//                    Toast.makeText(Raiserequest.this,"after insert "+strMobNum+" "+
//                            RequestType+" "+strName+" "+strAddress+" "+strDescription+" "+Long.toString(insert), Toast.LENGTH_SHORT).show();

                    if (insert!=-1) {

                        Toast.makeText(Raiserequest.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home_page.class);
                        startActivity(intent);
                    }
                    if (insert==-1) {

                        Toast.makeText(Raiserequest.this, "insert false", Toast.LENGTH_SHORT).show();

                    }

                }

            }




        })




    ;}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        RequestType = item;
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



}