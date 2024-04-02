package com.example.myapplication;

import android.database.Cursor;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Plumbing extends AppCompatActivity {


    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView listview;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing);

        listview = findViewById(R.id.listviewPlumbing);
        listItem = new ArrayList<>();
        db = new DBHelper(this);

        viewData();



    }
    private void viewData() {
        Cursor cursor = db.viewPlumbingRequest();

//        Toast.makeText(Pastrequests.this, "inside view data "+Long.toString(cursor.getCount()), Toast.LENGTH_SHORT).show();

        if (cursor.getCount()==0){
            Toast.makeText(Plumbing.this, "No data to show", Toast.LENGTH_SHORT).show();
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
            listview.setAdapter(adapter);


        }
    }


}