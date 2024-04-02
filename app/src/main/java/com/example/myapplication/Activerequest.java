package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Activerequest extends AppCompatActivity {
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView request_list;
    TextView formobnum;
    DBHelper db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activerequest);

        SharedPreferences sharedPreferences = getSharedPreferences("userid", Context.MODE_PRIVATE);

        String userId = sharedPreferences.getString("userid", "");






        db = new DBHelper(this);

        listItem = new ArrayList<>();

        request_list = findViewById(R.id.request_list);
        formobnum = findViewById(R.id.formobnumpast);


        formobnum.setText("For Mobile number: " + userId );





        request_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which_item = position;

//                Toast.makeText(Activerequest.this, "inside LONGLIS "+updItem, Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(Activerequest.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to mark this request as Completed?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String updItem = listItem.get(which_item);
                                updItem = updItem.split("#")[1];
                                updItem = updItem.split("\\n")[0];
                                String completetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
//                                String completetime = "2004-12-12 12:23:41";
                                Boolean updated = db.setRequestComplete(updItem, completetime);
                                if (updated==true){
                                    listItem.remove(which_item);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(Activerequest.this, "Request Number #"+updItem+" has been marked as Completed", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Activerequest.this, "Unable to update the request status at the moment ", Toast.LENGTH_SHORT).show();
                                }




                            }
                        })
                        .setNegativeButton("No", null)
                        .show();



                return true;
            }
        });




        viewData(userId);

    }

    private void viewData(String userId) {

        Cursor cursor = db.viewData(userId);

//        Toast.makeText(Activerequest.this, "inside view data "+Long.toString(cursor.getCount()), Toast.LENGTH_SHORT).show();

        if (cursor.getCount()==0){
            Toast.makeText(Activerequest.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(Activerequest.this, "inside else "+Long.toString(cursor.getCount()) , Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()){
                listItem.add(
                        "Request Num: #"+cursor.getString(0)
                        +"\n Mobile Number: "+cursor.getString(1)
                        +"\n Request Type: "+cursor.getString(2)
                        +"\n Full name: "+cursor.getString(3)
                        +"\n Request Description: "+cursor.getString(4)
                        +"\n House Number: "+cursor.getString(5)
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