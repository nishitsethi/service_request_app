package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class managerhomepage extends AppCompatActivity {

    TextView plumbingStat, electricalStat, gardeningStat, carpentryStat, totalStat, generateReport;
    EditText dateFilterFrom, dateFilterTo;
    Button btnfilter;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerhomepage);

        db = new DBHelper(this);
        plumbingStat = findViewById(R.id.tvPlumbing);
        electricalStat = findViewById(R.id.tvElectrical);
        gardeningStat = findViewById(R.id.tvGardening);
        carpentryStat = findViewById(R.id.tvCarpentry);
        totalStat = findViewById(R.id.tvTotal);
        generateReport = findViewById(R.id.btnToreport);
        dateFilterFrom = findViewById(R.id.dateFrom);
        dateFilterTo = findViewById(R.id.dateTo);
        btnfilter = findViewById(R.id.btnFilter);

        int[] numPlumbingStat =new int[]{0,0,0};
        int[] numElectricalStat =new int[]{0,0,0};
        int[] numGardeningStat =new int[]{0,0,0};
        int[] numCarpentryStat =new int[]{0,0,0};
        int[] numTotalStat =new int[]{0,0,0};
        String dateFrom,dateTo;

        dateFrom = "2002-12-02";
        dateTo = "2023-01-02";

        try{
            Cursor cursor = db.viewStat(dateFrom,dateTo);

            if (cursor.getCount()==0){
                Toast.makeText(managerhomepage.this, "No data to show", Toast.LENGTH_SHORT).show();

                plumbingStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
                electricalStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
                gardeningStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
                carpentryStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
                totalStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");


            } else {
                while (cursor.moveToNext()){
                    if(cursor.getString(0).equals("Plumbing")){
                        if(cursor.getString(1).equals("Active")){
                            numPlumbingStat[0]=Integer.parseInt(cursor.getString(2));
                        } else {
                            numPlumbingStat[1]=Integer.parseInt(cursor.getString(2));
                        }
                    } else if(cursor.getString(0).equals("Electrical")){
                        if(cursor.getString(1).equals("Active")){
                            numElectricalStat[0]=Integer.parseInt(cursor.getString(2));
                        } else {
                            numElectricalStat[1]=Integer.parseInt(cursor.getString(2));
                        }
                    } else if(cursor.getString(0).equals("Gardening")){
                        if(cursor.getString(1).equals("Active")){
                            numGardeningStat[0]=Integer.parseInt(cursor.getString(2));
                        } else {
                            numGardeningStat[1]=Integer.parseInt(cursor.getString(2));
                        }
                    } else if(cursor.getString(0).equals("Gardening")){
                        if(cursor.getString(1).equals("Active")){
                            numCarpentryStat[0]=Integer.parseInt(cursor.getString(2));
                        } else {
                            numCarpentryStat[1]=Integer.parseInt(cursor.getString(2));
                        }
                    }

                }
                numPlumbingStat[2] = numPlumbingStat[0] + numPlumbingStat[1];
                numElectricalStat[2] = numElectricalStat[0] + numElectricalStat[1];
                numGardeningStat[2] = numGardeningStat[0] + numGardeningStat[1];
                numCarpentryStat[2] = numCarpentryStat[0] + numCarpentryStat[1];

                numTotalStat[0]=numPlumbingStat[0] + numCarpentryStat[0] + numElectricalStat[0] + numGardeningStat[0];
                numTotalStat[1]=numPlumbingStat[1] + numCarpentryStat[1] + numElectricalStat[1] + numGardeningStat[1];
                numTotalStat[2] = numTotalStat[0] + numTotalStat[1];
                Toast.makeText(managerhomepage.this, "before set text" , Toast.LENGTH_SHORT).show();

                plumbingStat.setText("Total requests: "+numPlumbingStat[2]+" \nActive request: "+numPlumbingStat[0]+" \nCompleted requests: "+numPlumbingStat[1]);
                electricalStat.setText("Total requests: "+numElectricalStat[2]+" \nActive request: "+numElectricalStat[0]+" \nCompleted requests: "+numElectricalStat[1]);
                gardeningStat.setText("Total requests: "+numGardeningStat[2]+" \nActive request: "+numGardeningStat[0]+" \nCompleted requests: "+numGardeningStat[1]);
                carpentryStat.setText("Total requests: "+numCarpentryStat[2]+" \nActive request: "+numCarpentryStat[0]+" \nCompleted requests: "+numCarpentryStat[1]);
                totalStat.setText("Total requests: "+numTotalStat[2]+" \nActive request: "+numTotalStat[0]+" \nCompleted requests: "+numTotalStat[1]);

            }

        } catch(Exception e){
            Toast.makeText(managerhomepage.this,e.toString(), Toast.LENGTH_SHORT).show();
        }
        generateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), managerReport.class));
            }


        });

        btnfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] numPlumbingStat =new int[]{0,0,0};
                        int[] numElectricalStat =new int[]{0,0,0};
                        int[] numGardeningStat =new int[]{0,0,0};
                        int[] numCarpentryStat =new int[]{0,0,0};
                        int[] numTotalStat =new int[]{0,0,0};
                        String dateFrom,dateTo;

                        dateFrom = dateFilterFrom.getText().toString();
                        dateTo = dateFilterTo.getText().toString();

                        try{
                            Cursor cursor = db.viewStat(dateFrom,dateTo);

                            if (cursor.getCount()==0){
                                Toast.makeText(managerhomepage.this, "No data to show", Toast.LENGTH_SHORT).show();

                                plumbingStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
                                electricalStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
                                gardeningStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
                                carpentryStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
                                totalStat.setText("Total requests: 0 \nActive request: 0 \nCompleted requests: 0");
//
                    } else {
                        while (cursor.moveToNext()){
                            if(cursor.getString(0).equals("Plumbing")){
                                if(cursor.getString(1).equals("Active")){
                                    numPlumbingStat[0]=Integer.parseInt(cursor.getString(2));
                                } else {
                                    numPlumbingStat[1]=Integer.parseInt(cursor.getString(2));
                                }
                            } else if(cursor.getString(0).equals("Electrical")){
                                if(cursor.getString(1).equals("Active")){
                                    numElectricalStat[0]=Integer.parseInt(cursor.getString(2));
                                } else {
                                    numElectricalStat[1]=Integer.parseInt(cursor.getString(2));
                                }
                            } else if(cursor.getString(0).equals("Gardening")){
                                if(cursor.getString(1).equals("Active")){
                                    numGardeningStat[0]=Integer.parseInt(cursor.getString(2));
                                } else {
                                    numGardeningStat[1]=Integer.parseInt(cursor.getString(2));
                                }
                            } else if(cursor.getString(0).equals("Gardening")){
                                if(cursor.getString(1).equals("Active")){
                                    numCarpentryStat[0]=Integer.parseInt(cursor.getString(2));
                                } else {
                                    numCarpentryStat[1]=Integer.parseInt(cursor.getString(2));
                                }
                            }

                        }
                        numPlumbingStat[2] = numPlumbingStat[0] + numPlumbingStat[1];
                        numElectricalStat[2] = numElectricalStat[0] + numElectricalStat[1];
                        numGardeningStat[2] = numGardeningStat[0] + numGardeningStat[1];
                        numCarpentryStat[2] = numCarpentryStat[0] + numCarpentryStat[1];

                        numTotalStat[0]=numPlumbingStat[0] + numCarpentryStat[0] + numElectricalStat[0] + numGardeningStat[0];
                        numTotalStat[1]=numPlumbingStat[1] + numCarpentryStat[1] + numElectricalStat[1] + numGardeningStat[1];
                        numTotalStat[2] = numTotalStat[0] + numTotalStat[1];

                        plumbingStat.setText("Total requests: "+numPlumbingStat[2]+" \nActive request: "+numPlumbingStat[0]+" \nCompleted requests: "+numPlumbingStat[1]);
                        electricalStat.setText("Total requests: "+numElectricalStat[2]+" \nActive request: "+numElectricalStat[0]+" \nCompleted requests: "+numElectricalStat[1]);
                        gardeningStat.setText("Total requests: "+numGardeningStat[2]+" \nActive request: "+numGardeningStat[0]+" \nCompleted requests: "+numGardeningStat[1]);
                        carpentryStat.setText("Total requests: "+numCarpentryStat[2]+" \nActive request: "+numCarpentryStat[0]+" \nCompleted requests: "+numCarpentryStat[1]);
                        totalStat.setText("Total requests: "+numTotalStat[2]+" \nActive request: "+numTotalStat[0]+" \nCompleted requests: "+numTotalStat[1]);

                    }

                } catch(Exception e){
                    Toast.makeText(managerhomepage.this,e.toString(), Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}