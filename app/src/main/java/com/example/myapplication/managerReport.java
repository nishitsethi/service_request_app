package com.example.myapplication;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Environment;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class managerReport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String RequestType;
    Button btnDownload;
    DBHelper db;

    private DatePickerDialog datePickerDialog,datePickerDialog2  ;
    private Button dateButton, dateButton1;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_manager_report);
            initDatePicker();
            dateButton = findViewById(R.id.btnStartdate);
            dateButton1 = findViewById(R.id.btnEnddate);
            btnDownload = findViewById(R.id.btnDownloadreport);
            spinner2 = findViewById(R.id.spinner2);
            dateButton1.setText(makeDateString(19, 01, 2022));
            dateButton.setText(makeDateString(19, 01, 2022));


            spinner2.setOnItemSelectedListener(this);


            List<String> categories = new ArrayList<String>();
            categories.add("Plumbing");
            categories.add("Electricity");
            categories.add("Carpentry");
            categories.add("Gardening");


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner2.setAdapter(dataAdapter);


            btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(managerReport.this, "after btnDownload " , Toast.LENGTH_SHORT).show();

                    try {
                        exportEmailInCSV();
                    } catch (IOException e) {
                        Toast.makeText(managerReport.this, "in catch " , Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }
            });

        }



    private void initDatePicker()
    {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton1.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog2 = new DatePickerDialog(this, style, dateSetListener2, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {

        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {


        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }
    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    public void openDatePicker2(View view){
        datePickerDialog2.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        RequestType = item;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void exportEmailInCSV() throws IOException {
        {

            File folder = new File(
//                    Environment.getExternalStorageDirectory()
//                    +
                            "/sdcard/FolderNISHIT/");

            boolean var = false;
            if (!folder.exists())
                var = folder.mkdir();

            System.out.println("" + var);
            Toast.makeText(managerReport.this, "after mkdir " + "" + var, Toast.LENGTH_SHORT).show();


            final String filename = folder.toString() + "/" + "testNISHIT.csv";

//            // show waiting screen
//            CharSequence contentTitle = getString(R.string.app_name);
//            final ProgressDialog progDailog = ProgressDialog.show(
//                    MailConfiguration.this, contentTitle, "even geduld aub...",
//                    true);//please wait
//            final Handler handler = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {



//
//                }
//            };

//            new Thread() {
//            public void run() {
            try {

                FileWriter fw = new FileWriter(filename);

                Cursor cursor = db.viewElectricalRequest();

                fw.append("Request Num");
                fw.append(',');

                fw.append("Mobile number");
                fw.append(',');

                fw.append("Request type");
                fw.append(',');

                fw.append("Full name");
                fw.append(',');

                fw.append("Request description");
                fw.append(',');

                fw.append("House Number");
                fw.append(',');

                fw.append('\n');

                if (cursor.moveToFirst()) {
                    do {
                        fw.append(cursor.getString(0));
                        fw.append(',');

                        fw.append(cursor.getString(1));
                        fw.append(',');

                        fw.append(cursor.getString(2));
                        fw.append(',');

                        fw.append(cursor.getString(3));
                        fw.append(',');

                        fw.append(cursor.getString(4));
                        fw.append(',');

                        fw.append(cursor.getString(5));
                        fw.append(',');
//
//                                fw.append(cursor.getString(6));
//                                fw.append(',');
//
//                                fw.append(cursor.getString(7));
//                                fw.append(',');
//
//                                fw.append(cursor.getString(8));
//                                fw.append(',');
//
//                                fw.append(cursor.getString(9));
//                                fw.append(',');
//
//                                fw.append(cursor.getString(10));
//                                fw.append(',');

                        fw.append('\n');

                    } while (cursor.moveToNext());
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }

                // fw.flush();
                fw.close();

            } catch (Exception e) {
            }
//                    handler.sendEmptyMessage(0);
//                    progDailog.dismiss();
//            }
//            }.start();

        }

    }

}




