package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Pastrequests extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView request_list;
    TextView formobnumpast;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastrequests);
        SharedPreferences sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid", "");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_pastrequests, null, false);


        db = new DBHelper(this);

        listItem = new ArrayList<>();
        request_list = findViewById(R.id.request_list);
        formobnumpast = findViewById(R.id.formobnumpast);


        formobnumpast.setText("For Mobile number: " + userId);


        viewData(userId);

    }

    private void viewData(String userId) {
        Toast.makeText(Pastrequests.this, "userid: " + userId, Toast.LENGTH_SHORT).show();
        Cursor cursor = db.viewCompletedRequest(userId);


        if (cursor.getCount()==0){
            Toast.makeText(Pastrequests.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(Pastrequests.this, "inside else "+Long.toString(cursor.getCount()) , Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
                listItem.add(
                        "Request Num: #"+cursor.getString(0)
                                +"\n Mobile Number "+cursor.getString(1)
                                +"\n Request Type "+cursor.getString(2)
                                +"\n Full name :"+cursor.getString(3)
                                +"\n Request Description :"+cursor.getString(4)
                                +"\n House Number:"+cursor.getString(5)
                                +"\n Request On: "+cursor.getString(7)
                                +"\n Completed On: "+cursor.getString(8)
//                        +"Status:"+cursor.getString(7)

                );



            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem);
            request_list.setAdapter(adapter);


        }
    }




}